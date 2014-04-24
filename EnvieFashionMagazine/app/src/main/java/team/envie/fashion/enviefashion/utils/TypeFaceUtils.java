package team.envie.fashion.enviefashion.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public final class TypeFaceUtils {

    public static final Typeface garamond(AssetManager assetManager) {
        Typeface typeface = Typeface.createFromAsset(assetManager, team.envie.fashion.enviefashion.entity.Config.TYPEFACE_GARAMOND.getTypeface());
        return typeface;
    }

    public static final Typeface bickam(AssetManager assetManager) {
        Typeface typeface = Typeface.createFromAsset(assetManager, team.envie.fashion.enviefashion.entity.Config.TYPEFACE_BICKAM.getTypeface());
        return typeface;
    }

}
