package team.envie.fashion.enviefashion.Alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;

import org.apache.commons.lang3.StringUtils;

import team.envie.fashion.enviefashion.R;

/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 *          <p>
 *          Alert Dialog Fragmet
 *          Singleton class
 *          </p>
 */
public class Alert extends DialogFragment {

    private static AlertDialog.Builder sBuilder;

    /**
     * alert title
     */
    private static String sTitle;
    /**
     * alert message
     */
    private static String sMessage;

    private static View sView;

    private static YesNoListener sListener;

    /**
     * instance
     */
    private static Alert sInstance;

    public static Alert newInstance() {
        sInstance = new Alert();
        return sInstance;
    }

    public static Alert newInstance(String title, String message, final YesNoListener listener) {
        sTitle = title;
        sMessage = message;
        sListener = listener;
        sInstance = new Alert();
        return sInstance;
    }

    public static Alert newInstance(String title, String message, View view, final YesNoListener listener) {
        sTitle = title;
        sMessage = message;
        sListener = listener;
        sView = view;
        sInstance = new Alert();
        return sInstance;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        sBuilder = new AlertDialog.Builder(getActivity());

        sBuilder.setIcon(getResources().getDrawable(R.drawable.ic_launcher));
        sBuilder.setTitle(getString(R.string.app_name));
        sBuilder.setCancelable(true);

        if (StringUtils.isNotEmpty(sTitle)) {
            sBuilder.setTitle(sTitle);
        }

        if (StringUtils.isNotEmpty(sMessage)) {
            sBuilder.setMessage(sMessage);
        }

        if (null != sView) {
            sBuilder.setView(sView);
        }

        sBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    sListener.yesClick();
                } catch (NullPointerException e) {
                }
                dismiss();
            }
        });
        sBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    sListener.noClick();
                } catch (NullPointerException e) {
                }
                dismiss();
            }
        });


        return sBuilder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sListener = null;
        sInstance = null;
        if (sView != null) {
            ViewGroup parentViewGroup = (ViewGroup) sView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }
    }
}