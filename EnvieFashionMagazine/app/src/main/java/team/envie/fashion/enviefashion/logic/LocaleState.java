package team.envie.fashion.enviefashion.logic;

import android.app.Activity;
import android.content.res.Configuration;

import java.util.Locale;

import team.envie.fashion.enviefashion.model.entity.Constants;

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
            Constants.changeLocale(activity);
        }
    },
    CHINESE {
        @Override
        public void changeLocale(Activity activity) {
            LocaleState.setUp(Locale.CHINA, activity);
            Constants.changeLocale(activity);
        }
    },
    ESPANOL {
        @Override
        public void changeLocale(Activity activity) {
            LocaleState.setUp(new Locale("es"), activity);
            Constants.changeLocale(activity);
        }
    },
    FRANCE {
        @Override
        public void changeLocale(Activity activity) {
            LocaleState.setUp(Locale.FRANCE, activity);
            Constants.changeLocale(activity);
        }
    },
    DEUTSCH {
        @Override
        public void changeLocale(Activity activity) {
            LocaleState.setUp(Locale.GERMANY, activity);
            Constants.changeLocale(activity);
        }
    };


    public abstract void changeLocale(Activity activity);


    private static void setUp(Locale locale, Activity activity) {
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        activity.getApplicationContext().getResources().updateConfiguration(configuration, null);
    }
}
