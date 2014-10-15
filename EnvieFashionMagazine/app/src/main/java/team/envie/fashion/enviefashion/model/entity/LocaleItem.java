package team.envie.fashion.enviefashion.model.entity;

public class LocaleItem {

    private String locale;
    private int localeDrawable;

    public LocaleItem(String locale, int localeDrawable) {
        this.locale = locale;
        this.localeDrawable = localeDrawable;
    }

    public String getLocale() {
        return locale;
    }

    public int getLocaleDrawable() {
        return localeDrawable;
    }
}
