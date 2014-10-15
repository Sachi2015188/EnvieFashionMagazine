package team.envie.fashion.enviefashion.controller.ui.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.util.Stack;

import team.envie.fashion.enviefashion.R;


/**
 * 選択されたときに呼び出されるリスナー
 *
 */
interface FileOpenDialogListener {
    void onFileSelected(final File file);
}

public class FileOpenDialog implements DialogInterface.OnClickListener {
    private final static String TAG = "OpenFolder";

    private Context mParent = null; 		// 親のコンテキスト
    private int mSelectedItemIndex = -1;	// 選択中のアイテムインデックス
    private File[] mFileList; 				// 表示中のファイルのリスト

    private String mCurrDirectory = null; 	// 今居るディレクトリ
    private Stack<String> mDirectorys = new Stack<String>(); // ディレクトリ

    private FileOpenDialogListener mListener; // リスナー

    private boolean mOpenDirectory; 		// ディレクトリを開く
    private File mLastSelectedItem; 		// 最後に選択されたモノ

    private String upperHierarchy; 			// 上の階層　：　文言

    /**
     * コンストラクタ
     *
     * @param parent 親のコンテキスト
     * @param listener 選択が決まったときに呼び出される
     * @param openDirectory true:ディレクトリを開く
     */
    public FileOpenDialog(final Context parent,
                          final FileOpenDialogListener listener, boolean openDirectory) {
        super();

        upperHierarchy = parent.getResources().getString(R.string.上の階層);
        this.mParent = parent;					// コンテキスト
        this.mListener = listener;				// リスナー
        this.mOpenDirectory = openDirectory;	// ディレクトリだけを開くか
    }

    /**
     * ダイアログが選択されたときに呼び出される
     */
    public void onClick(DialogInterface dialog, int which) {
        Log.e(TAG, "onClick");

        // 今の選択されているモノ
        this.mSelectedItemIndex = which;

        // ファイルリストが空じゃない
        if (this.mFileList != null) {

            Log.e(TAG, "ファイルリストが空じゃない");
            int selectedItemIndex = this.mSelectedItemIndex; // 選択されている項目

            // 上の階層がある場合
            if (0 < this.mDirectorys.size()) {
                Log.e(TAG, "上の階層がある場合");
                // 上の階層ボタン分減らす
                selectedItemIndex--;
            }

            // 上の階層へが選択されてた
            if (selectedItemIndex < 0) {

                // 一つ上の階層へ移動する
                this.openDirectory(this.mDirectorys.pop());
                Log.e(TAG, "上の階層へ移動する");

            } else {

                // ファイルを取り出す
                this.mLastSelectedItem = this.mFileList[selectedItemIndex];
                // ディレクトリの場合はそのディレクトリのモノを表示する
                if (this.mLastSelectedItem.isDirectory()) {

                    // 次の階層に移動する前に、今の階層に戻れる様にスタックに積んでおく
                    this.mDirectorys.push(this.mCurrDirectory);

                    // 次の階層で新しくダイアログを開く
                    this.openDirectory(this.mLastSelectedItem.getAbsolutePath());

                    // ファイルだった場合は、そのファイルを選択されたファイルとして登録する
                } else {
                    // ファイルが選択されたことを通知する
                    this.mListener.onFileSelected(this.mLastSelectedItem);
                }
            }
        } else {
        }
    }

    /**
     * 指定のディレクトリを開く
     * @param dir
     *            開きたいディレクトリ(このディレクトリがルートディレクトリになる)
     */
    public void openDirectory(String dir) {
        try {
            // ディレクトリだけ取り出したい
            if (this.mOpenDirectory == true) {

                // ディレクトリだけ取り出す(フィルタ使う)
                this.mFileList = new File(dir).listFiles(new FileFilter() {
                    public boolean accept(File pathname) {
                        // ディレクトリだけ許可
                        if (pathname.isDirectory())
                            return true;
                        return false;
                    }
                });

            } else {

                // 指定のディレクトリのファイルを全部取り出す
                this.mFileList = new File(dir).listFiles();
            }

            // 今の階層を取っておく
            this.mCurrDirectory = dir;

            // 何もとれなかった(開けない階層、多分アクセス権限がない)
            if (this.mFileList == null) {
                // 一つ上の階層へ移動する
                this.openDirectory(this.mDirectorys.pop());
                return;
            }

            // 何も残ってない(ディレクトリが確定)
            if (this.mFileList.length <= 0) {
                this.mListener.onFileSelected(this.mLastSelectedItem);
                return;
            }

            // Alertダイアログのために配列を用意する
            String[] fileNameList = null;
            int itemCount = 0;

            // ルートディレクトリ以外
            if (0 < this.mDirectorys.size()) {
                // 上の階層へ行くための項目を追加する
                fileNameList = new String[this.mFileList.length + 1];
                fileNameList[itemCount] = upperHierarchy; // 上の階層
                itemCount++;

                // ルートディレクトリ
            } else {
                // ファイルの数だけ
                fileNameList = new String[this.mFileList.length];
            }

            // 見つかったファイルの分だけ追加する
            for (File currFile : this.mFileList) {

                // ディレクトリだった
                if (currFile.isDirectory()) {
                    // 最後に/を加えてディレクトリの表示を
                    fileNameList[itemCount] = currFile.getName() + "/";

                    // ファイルだった
                } else {
                    fileNameList[itemCount] = currFile.getName();
                }
                itemCount++;
            }

            // ダイアログを表示する
            new AlertDialog.Builder(this.mParent).setTitle(dir)
                    .setIcon(R.drawable.ic_launcher)
                    .setItems(fileNameList, this).show();

        } catch (SecurityException se) {
            Log.e("SecurityException", se.getMessage());
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
    }
}
