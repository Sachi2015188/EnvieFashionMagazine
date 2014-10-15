package team.envie.fashion.enviefashion.model.entity;


/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 * @since 2014/04/02
 *
 * Model Entity Enum Class
 */
public enum Config {

    ENVIE_TOTAL_SIZE(45),
    TYPEFACE_BICKAM("fonts/BickhamScriptPro-Regular.otf"),
    TYPEFACE_GARAMOND("fonts/AGaramondPro-Italic.otf");

    // Typeface
    private String mTypeface;
    private Config(String typeface) {
        mTypeface = typeface;
    }
    public String getTypeface() {
        return mTypeface;
    }

    // Total Magazine size
    private int mTotalSize;
    private Config(int total) {
        mTotalSize = total;
    }
    public int getTotalSize() {
        return mTotalSize;
    }

}
