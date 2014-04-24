package team.envie.fashion.enviefashion.logic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public enum SnsState {

    FACEBOOK {
        @Override
        public void moveSns(Activity activity) {
            setUp(activity, "fb://page/138271312892668");
        }
    },
    TWITTER {
        @Override
        public void moveSns(Activity activity) {
            setUp(activity, "twitter://twitter.com/EnVieFashion");
        }
    },
    WEIBO {
        @Override
        public void moveSns(Activity activity) {
            setUp(activity, "http://weibo.com/signup/signup.php?inviteCode=1829453091");
        }
    };


    /* ***************************************************************************************

                                            abstract method

        ***************************************************************************************  */
    public abstract void moveSns(Activity activity);


    /* ***************************************************************************************

                                            private method

        ***************************************************************************************  */
    private static void setUp(Activity activity, String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity.getApplicationContext(), "application is not install", Toast.LENGTH_SHORT).show();
        }
    }
}
