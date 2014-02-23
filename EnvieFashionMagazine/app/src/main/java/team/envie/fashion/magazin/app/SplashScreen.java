package team.envie.fashion.magazin.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import team.envie.fashion.magazin.app.fragment.BaseFragment;

/**
 * Splash Screen
 * <p>
 *     Splash Activity
 *     Theme FullScreen
 * </p>
 * @version 1.0.0
 */
public class SplashScreen extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends BaseFragment {

        // Set Duration of the Splash Screen
        int Delay = 2500;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_splash_screen, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            // Set Runnable
            Runnable delayFunc = new Runnable() {

                @Override
                public void run() {
                    chainIntent.name(MainActivity.class).moveActivity()
                            .overridePending(R.anim.splash_fade_in, R.anim.splash_fade_out)
                            .finish();
                }
            };

            // Set Handler
            Handler handler = new Handler();
            handler.postDelayed(delayFunc, Delay);
        }
    }

}
