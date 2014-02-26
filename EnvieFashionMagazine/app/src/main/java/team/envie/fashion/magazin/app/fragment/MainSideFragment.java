package team.envie.fashion.magazin.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import team.envie.fashion.magazin.app.R;


/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 * <p>
 *     MainActivity side panel fragment
 *     {@link team.envie.fashion.magazin.app.activity.MainActivity}
 * </p>
 */
public class MainSideFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_side, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
