package team.envie.fashion.enviefashion.controller.ui.helper;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import team.envie.fashion.enviefashion.R;
import team.envie.fashion.enviefashion.controller.ui.activity.ApplicationController;
import team.envie.fashion.enviefashion.model.entity.Constants;


/*
    TODO HelperListnerはinterfaceへ変更して実装はほかのクラスで
 */

/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 *          <p>
 *              Activity Helper Litner Class
 *          </p>
 */
public class HelperMainSideFragment implements FileOpenDialogListener {

    private static final String TAG = "DIALOG";

    private FragmentActivity ac;

    private team.envie.fashion.enviefashion.chain.main.ChainIntent mChainIntent;

    private FileOpenDialog mFileOpenDialog;

    public HelperMainSideFragment(FragmentActivity activity) {
        ac = activity;
        mChainIntent = new team.envie.fashion.enviefashion.chain.main.ChainIntent(activity);
        mFileOpenDialog = new FileOpenDialog(ac, this, false);
    }

    public View.OnClickListener downloadListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            if (!isClickEvent()) {
                return;
            }

            team.envie.fashion.enviefashion.Alert.Alert.newInstance(null, ac.getString(R.string.ダウンロードしますか), new team.envie.fashion.enviefashion.Alert.YesNoListener() {
                @Override
                public void yesClick() {
                    super.yesClick();
                    team.envie.fashion.enviefashion.receiver.DownloadReceiver.download(ac,
                            ac.getResources().getString(R.string.directoryName),
                            Constants.DATA.get(ApplicationController.position).getPdfUrl(),
                            Constants.DATA.get(ApplicationController.position).getPdfName());
                }

                @Override
                public void noClick() {
                    super.noClick();
                }
            }).show(ac.getSupportFragmentManager(), TAG);

        }
    };

    public View.OnClickListener openLibraryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!isClickEvent()) {
                return;
            }

            Environment.getExternalStorageDirectory().getAbsolutePath();
            String storagePath = Environment.getExternalStorageDirectory().getPath();
            try {
                mFileOpenDialog.openDirectory(storagePath + ac.getString(R.string.directoryName));
            } catch (NullPointerException e) {
                Toast.makeText(ac, R.string.ダウンロード後にフォルダが作成されます, Toast.LENGTH_SHORT).show();
            }

        }
    };


    private static final long CLICK_DELAY = 1000;
    private static long mOldClickTime;

    /**
     * safe double click event
     * @return
     */
    private static boolean isClickEvent() {
        long time = System.currentTimeMillis();

        if (time - mOldClickTime < CLICK_DELAY) {
            return false;
        }

        mOldClickTime = time;
        return true;
    }

    @Override
    public void onFileSelected(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + file.getPath()), "application/pdf");
        ac.startActivity(intent);
    }
}
