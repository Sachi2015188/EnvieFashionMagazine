package team.envie.fashion.enviefashion.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import team.envie.fashion.enviefashion.model.entity.Config;

public final class TypeFaceUtils {

    public static final Typeface garamond(AssetManager assetManager) {
        Typeface typeface = Typeface.createFromAsset(assetManager, Config.TYPEFACE_GARAMOND.getTypeface());
        return typeface;
    }

    public static final Typeface bickam(AssetManager assetManager) {
        Typeface typeface = Typeface.createFromAsset(assetManager, Config.TYPEFACE_BICKAM.getTypeface());
        return typeface;
    }

}
