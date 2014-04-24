package team.envie.fashion.enviefashion.logic;

import android.app.Activity;
import android.content.res.Configuration;

import java.util.Locale;

import team.envie.fashion.enviefashion.entity.Constants;

public enum LocaleState {

    JAPAN {
        @Override
        public void changeLocale(Activity activity) {
            LocaleState.setUp(Locale.JAPAN, activity);
            Constants.changeLocale(activity);
        }
    },
    ENGLISH {
        @Override
        public void changeLocale(Activity activity) {
            LocaleState.setUp(Locale.ENGLISH, activity);
            team.envie.fashion.enviefashion.entity.Constants.changeLocale(activity);
        }
    },
    CHINESE {
        @Override
        public void changeLocale(Activity activity) {
            LocaleState.setUp(Locale.CHINA, activity);
            team.envie.fashion.enviefashion.entity.Constants.changeLocale(activity);
        }
    },
    ESPANOL {
        @Override
        public void changeLocale(Activity activity) {
            LocaleState.setUp(new Locale("es"), activity);
            team.envie.fashion.enviefashion.entity.Constants.changeLocale(activity);
        }
    },
    FRANCE {
        @Override
        public void changeLocale(Activity activity) {
            LocaleState.setUp(Locale.FRANCE, activity);
            team.envie.fashion.enviefashion.entity.Constants.changeLocale(activity);
        }
    },
    DEUTSCH {
        @Override
        public void changeLocale(Activity activity) {
            LocaleState.setUp(Locale.GERMANY, activity);
            team.envie.fashion.enviefashion.entity.Constants.changeLocale(activity);
        }
    };


    /* ***************************************************************************************

                                           abstract Method

            ***************************************************************************************  */
    public abstract void changeLocale(Activity activity);


    /* ***************************************************************************************

                                            private method

        ***************************************************************************************  */
    private static void setUp(Locale locale, Activity activity) {
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        activity.getApplicationContext().getResources().updateConfiguration(configuration, null);
    }
}
