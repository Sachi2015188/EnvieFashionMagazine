package team.envie.fashion.enviefashion.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;

/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 *          <p>
 *          AnimUtils
 *          </p>
 */
public final class AnimUtils {

    /**
     * Animation Duration
     */
    private final static int DURATION = 100;
    /**
     * ScaleAnimation float value
     */
    private final static float SCALE = 0.80f;

    // *******************************************************************
    // public
    // *******************************************************************

    /**
     * View Scale Animation
     * <p/>
     * Scale value {@link AnimUtils#SCALE}
     *
     * @param targetView
     */
    public static void pushScaleAnimation(View targetView) {
        targetView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pushScale(v, event.getAction(), SCALE, SCALE);
                return false;
            }
        });
    }


    /**
     * View Scale Animation
     *
     * @param targetView
     * @param scaleX     toScaleX and fromScaleX
     * @param scaleY     toScaleY and fromScaleY
     */
    public static void pushScaleAnimation(View targetView, final float scaleX, final float scaleY) {
        targetView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pushScale(v, event.getAction(), scaleX, scaleY);
                return false;
            }
        });
    }


    // *******************************************************************
    // private
    // *******************************************************************

    /**
     * push View ScaleAnimation
     *
     * @param targetView TargetView
     * @param action     MotionEventAction
     * @param scaleX
     * @param scaleY
     */
    private static void pushScale(View targetView, int action, float scaleX, float scaleY) {
        if (action == MotionEvent.ACTION_DOWN) {
            // Touch Down
            ScaleAnimation anim = new ScaleAnimation(1.0f, scaleX, 1.0f, scaleY, targetView.getWidth() / 2, targetView.getHeight() / 2);
            anim.setDuration(DURATION);
            anim.setFillEnabled(true);
            anim.setFillAfter(true);
            targetView.startAnimation(anim);
        } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            // Touch Up
            ScaleAnimation anim = new ScaleAnimation(scaleX, 1.0f, scaleY, 1.0f, targetView.getWidth() / 2, targetView.getHeight() / 2);
            anim.setDuration(DURATION);
            anim.setFillEnabled(true);
            anim.setFillAfter(true);
            targetView.startAnimation(anim);
        }
    }
}