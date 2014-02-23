package team.envie.fashion.magazin.app.chain.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import java.io.File;

import team.envie.fashion.magazin.app.chain.core.ChainIntentInterface;
import team.envie.fashion.magazin.app.utils.Logged;

/**
 * author Shuhei Iwamoto
 */
public class ChainIntent<T extends ChainIntent<T>> implements ChainIntentInterface<ChainIntent<T>> {

    private final ChainIntent self = this;

    private Context mContext;
    private FragmentActivity mFragmentActivity;
    private Activity mActivity;
    private Class<?> mClassName;


    // *******************************************************************
    // Constructor
    // *******************************************************************
    public ChainIntent(Context context) {
        mContext = context;
    }

    public ChainIntent(FragmentActivity activity) {
        mActivity = activity;
    }

    public ChainIntent(Activity activity) {
        mActivity = activity;
    }


    // *******************************************************************
    // Method
    // *******************************************************************

    /**
     * to class name
     * chain.name(to Class Name)...
     *
     * @param className
     * @return
     */
    @Override
    public ChainIntent<T> name(Class<?> className) {
        mClassName = className;
        return self;
    }

    /**
     * Activity finish
     *
     * @return
     */
    @Override
    public ChainIntent<T> finish() {
        mActivity.finish();
        return self;
    }

    @Override
    public ChainIntent<T> moveActivity() {
        if (mClassName != null) {
            Intent intent = new Intent(mActivity, mClassName);
            mActivity.startActivity(intent);
        }
        return self;
    }

    @Override
    public ChainIntent<T> overridePending(int enterAnim, int exitAnim) {
        if (mClassName != null) {
            if (mActivity != null) {
                mActivity.overridePendingTransition(enterAnim, exitAnim);
            }
        }
        return self;
    }


    // *******************************************************************
    // Intent.ACTION_VIEW
    // *******************************************************************
    private Intent intent;

    @Override
    public ChainIntent<T> actionView() {
        intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        return self;
    }

    @Override
    public ChainIntent<T> actionViewVideo(File file) {
        actionView();
        if (file.exists()) {
            intent.setDataAndType(Uri.fromFile(file), "video/*");
            mActivity.startActivity(intent);
        } else {
            Logged.d("--" + "file not exist" + "--");
        }
        return self;
    }

    @Override
    public ChainIntent<T> actionViewAudio(File file) {
        actionView();
        if (file.exists()) {
            intent.setDataAndType(Uri.fromFile(file), "audio/*");
            mActivity.startActivity(intent);
        } else {
            Logged.d("--" + "file not exist" +"--");
        }
        return self;
    }
}
