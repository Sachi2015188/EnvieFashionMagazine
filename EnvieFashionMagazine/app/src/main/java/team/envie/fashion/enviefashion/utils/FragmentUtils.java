package team.envie.fashion.enviefashion.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 *          <p>
 *              FragmeneUtils Class
 *          </p>
 */
public class FragmentUtils {
    private final FragmentUtils self = this;

    private FragmentActivity mActivity;

    public FragmentUtils(FragmentActivity activity) {
        mActivity = activity;
    }

    /**
     * get Fragment RootView
     * @param resId
     * @return view
     */
    public View getRootView(int resId) {
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(resId);
        View rootView = fragment.getView();
        return rootView;
    }
}
