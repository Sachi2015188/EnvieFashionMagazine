package team.envie.fashion.enviefashion.entity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import team.envie.fashion.enviefashion.R;

/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 * @since 2014/04/02
 */
public class Constants {
    private static Constants ourInstance = new Constants();

    public static Constants getInstance() {
        return ourInstance;
    }

    private Constants() {
    }

    /**
     * Global DATA List<ImageData>
     * {@link team.envie.fashion.enviefashion.entity.ImageData}
     */
    public static final List<team.envie.fashion.enviefashion.entity.ImageData> DATA = new ArrayList<team.envie.fashion.enviefashion.entity.ImageData>(team.envie.fashion.enviefashion.entity.Config.ENVIE_TOTAL_SIZE.getTotalSize());

    /**
     * @param context
     */
    public static void setUpData(Context context) {
        setUp(context);
    }


    /**
     * @param context
     */
    private static void setUp(Context context) {

        if (!DATA.isEmpty()) {
            DATA.clear();
        }

        for (int i = 0; i < team.envie.fashion.enviefashion.entity.Config.ENVIE_TOTAL_SIZE.getTotalSize(); i++) {
            //String imageDateTitle, String pdfUrl, String pdfName, Drawable mainImage, Drawable contentImage, Drawable galleryImage
            team.envie.fashion.enviefashion.entity.ImageData data = new team.envie.fashion.enviefashion.entity.ImageData(
                    team.envie.fashion.enviefashion.utils.ResourceUtils.getTypedArray(context.getApplicationContext(), R.array.title).getString(i),
                    team.envie.fashion.enviefashion.utils.ResourceUtils.getTypedArray(context.getApplicationContext(), R.array.pdf_url).getString(i),
                    team.envie.fashion.enviefashion.utils.ResourceUtils.getTypedArray(context.getApplicationContext(), R.array.dlFileName).getString(i),
                    team.envie.fashion.enviefashion.utils.ResourceUtils.getTypedArray(context.getApplicationContext(), R.array.coverID).getDrawable(i),
                    null,
                    team.envie.fashion.enviefashion.utils.ResourceUtils.getTypedArray(context.getApplicationContext(), R.array.thumbnail).getDrawable(i)
            );
            DATA.add(i, data);
        }
    }

    public static void changeLocale(final Activity activity) {

        final team.envie.fashion.enviefashion.view.dialog.CustomProgressDialog dialog = team.envie.fashion.enviefashion.view.dialog.CustomProgressDialog.newInstance("", "");

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog.show(activity.getFragmentManager(), activity.getPackageName());

                if (!DATA.isEmpty()) {
                    DATA.clear();
                }
            }

            @Override
            protected Void doInBackground(Void... params) {
                setUpData(activity.getApplicationContext());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dialog.getDialog().dismiss();
            }
        }.execute();
    }
}
