package team.envie.fashion.enviefashion.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;

/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 *          <p>
 *              Resource Utils
 *          </p>
 */
public final class ResourceUtils {

    /**
     * get obtainTypedArray
     *
     * @param activity Activity
     * @param resId int resource array
     * @return
     */
    public static TypedArray getTypedArray(Activity activity, int resId) {
        return activity.getResources().obtainTypedArray(resId);
    }

    /**
     * get obtainTypedArray
     *
     * @param context Context
     * @param resId int resource array
     * @return
     */
    public static TypedArray getTypedArray(Context context, int resId) {
        return context.getResources().obtainTypedArray(resId);
    }

    /**
     * get obtainTypedArray
     *
     * @param fragmentActivity FragmentAcitivity
     * @param resId int resource array
     * @return
     */
    public static TypedArray getTypedArray(FragmentActivity fragmentActivity, int resId) {
        return fragmentActivity.getResources().obtainTypedArray(resId);
    }
}
