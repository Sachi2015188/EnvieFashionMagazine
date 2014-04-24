package team.envie.fashion.enviefashion.receiver;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import team.envie.fashion.enviefashion.R;
import team.envie.fashion.enviefashion.utils.Logged;

/**
 * ネットワークに繋いでSDカードにダウンロードするので、AndroidManifest.xmlに android.permission.INTERNET
 * android.permission.WRITE_EXTERNAL_STORAGEを追加する。 使い方
 * DownloadData.download(Context context, String url);
 * DownloadManagerの成功判定については未定義
 * */

@SuppressLint("NewApi")
public class DownloadReceiver {

    // コンストラクタ
    private DownloadReceiver() {}

    private final static String TAG = "DownloadData";

    private static String dirName = "En Vie Magazine"; 		// 保存先フォルダ名
    private static String NoticeName = "En Vie Magazine"; 	// 通知領域の名前

    private static boolean version_flag;							// ダウンロードされた時、2.3.3か4.0によって処理を変更
    private static int notify_specificId = 0;

    private static SharedPreferences pref;

    private static DownloadManager.Request request;
    private static DownloadManager downLoadManager;
    private static NotificationManager notificationManager;
    private static Notification notification;
    private static PendingIntent pendingIntent;

    @SuppressLint("NewApi")
    static BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            // notificationを取得
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // intent = new Intent(Intent.ACTION_VIEW);　ここにおくとエラー
            long id = intent.getExtras().getLong(DownloadManager.EXTRA_DOWNLOAD_ID);
            Log.e(TAG, "id : " + id);

            intent = new Intent(Intent.ACTION_VIEW);

            // 2.3系でダウンロードした場合はステータスバーに表示させないようにする
            try {
                intent.setDataAndType(
                        downLoadManager.getUriForDownloadedFile(id),
                        downLoadManager.getMimeTypeForDownloadedFile(id)); // ダウンロードしたファイルの形式で開く
                version_flag = true;
            } catch (Exception e) {
                Log.e(TAG, "e :" + e.toString());
                version_flag = false;
            }
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            notification = new Notification(R.drawable.pdf_btn, "", System.currentTimeMillis());
            notification.setLatestEventInfo(context, "EnVieMagazine", "", pendingIntent);
            notification.flags = Notification.FLAG_AUTO_CANCEL; // タッチした時にnotificationを消す

            // ダウンロード成功時の処理
            initCursor(context);
        }
    };


    /**
     * ダウンロードメソッド
     *
     * @param context
     * @param url
     */
    private static String[] downloadname_array;	// ダウンロードしたファイルの名前
    private static String month;

    public static void download(Context context, String path, String url, String fileName) {
        team.envie.fashion.enviefashion.utils.Logged.w("download");

        // DownloadManagerのインスタンスを取得する
        downLoadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        // sdカードまでのパスを取得する。
        String sdcardPath = Environment.getExternalStorageDirectory().getPath();

        File file = new File(sdcardPath, dirName);

        // フォルダがない場合は作成する。
        if (!file.exists())
            Toast.makeText(context, R.string.EnVieMagazineのフォルダが新規作成されました, Toast.LENGTH_LONG).show();
        file.mkdirs();

        // EnVieMagazineフォルダの下に五カ国分のフォルダを作成する
        File ja_file = new File(file, "Japan");
        File en_file = new File(file, "English");
        File zh_file = new File(file, "China");
        File es_file = new File(file, "Spain");
        File fr_file = new File(file, "France");
        File du_file = new File(file, "Deutsch");
        if (!ja_file.exists() || !fr_file.exists() || !en_file.exists()
                || !es_file.exists() || !zh_file.exists() || !!du_file.exists()) {
            ja_file.mkdir();
            en_file.mkdir();
            zh_file.mkdir();
            es_file.mkdir();
            fr_file.mkdir();
            du_file.mkdir();
        }


        /** Requestをキューに追加してダウンロードを開始する(ダウンロードされたものはStringarrayのものを使っている) */
        downLoadManager.enqueue(setRequest(url, path, fileName));

        /** ダウンロード完了時にブロードキャストを呼び出す */
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);	// フィルターにダウンロード完了時を追加
        try {
            // registerReceiverメソッドをしようして、ブロードキャストレシーバーを起動
            context.registerReceiver(mReceiver, filter);
        } catch (RuntimeException e) {}
    }

    /**
     * Requestセット
     *
     * @param url
     * @param path	アプリの言語設定によってpathを変える（EnvieMagazine/Japan）のように
     * @param extension
     * @return
     */
    private static DownloadManager.Request setRequest(String url, String path, String extension) {
        team.envie.fashion.enviefashion.utils.Logged.w("setRequest");
        team.envie.fashion.enviefashion.utils.Logged.w("--" + url + "--");
        request = new DownloadManager.Request(Uri.parse(url));

        // sdカードまでのパスとファイル名を設定する。第二引数はダウンロードしたファイルの名前を決定
        request.setDestinationInExternalPublicDir(path, extension);

        // ダウンロードする際の通知領域の名前をセットする。
        // request.setTitle(getExtension(url));
        request.setTitle(extension);
        request.setVisibleInDownloadsUi(true);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        // モバイル通信（3Gなど）／WiFiともに許可
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                | DownloadManager.Request.NETWORK_WIFI);
        return request;
    }

    /**
     * ダウンロード成功時の処理
     *
     * @param context
     */
    private static void initCursor(Context context) {
        team.envie.fashion.enviefashion.utils.Logged.w("initCursor :");
        // キューに追加したリクエストのステータスの確認のためにqueryを生成
        DownloadManager.Query query = new DownloadManager.Query();

        // queryの戻り値でCursorクラスを生成：Cursor(カーソル)
        Cursor cursor = downLoadManager.query(query);

        // ダウンロード完了時に処理される
        if (cursor.moveToFirst()) {

            // ダウンロードのエラー時などの理由
            int columReason = cursor
                    .getColumnIndex(DownloadManager.COLUMN_REASON);
            int reason = cursor.getInt(columReason);
            team.envie.fashion.enviefashion.utils.Logged.w("reason = " + reason);

            // ダウンロードのステータス
            int columStatus = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
            int status = cursor.getInt(columStatus);
            Logged.w("status = " + status);

            switch (status) {
                case DownloadManager.STATUS_FAILED:
                    team.envie.fashion.enviefashion.utils.Logged.w("STATUS_FAILED");
                    switch (reason) {
                        case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                            team.envie.fashion.enviefashion.utils.Logged.w("ERROR_FILE_ALREADY_EXISTS");
                    }
                    break;

                // ダウンロード完了時（成功時）
                case DownloadManager.STATUS_SUCCESSFUL:
                    team.envie.fashion.enviefashion.utils.Logged.w("SUCCESFUL");

                    if (version_flag) {
                        // ダウンロード完了時に通知情報をステータスバーに表示する
                        Toast.makeText(context, R.string.ダウンロードが完了しました,Toast.LENGTH_LONG).show();

                        // ステータスバーにノティフィケーションを表示
                        notificationManager.notify(notify_specificId, notification);
                    } else {
                        Toast.makeText(context,R.string.EnVieMagazineのフォルダにダウンロードされました,Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
        cursor.close();
    }
}

