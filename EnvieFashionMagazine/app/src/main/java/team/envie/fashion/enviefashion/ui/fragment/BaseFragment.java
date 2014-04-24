package team.envie.fashion.enviefashion.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    // *******************************************************************
    // Decralation
    // *******************************************************************
    protected team.envie.fashion.enviefashion.chain.main.ChainIntent chainIntent;
    protected team.envie.fashion.enviefashion.chain.main.ChainPreference chainPref;
    protected AssetManager mAssetManager;
    protected Context mContext;


    // *******************************************************************
    // LifeCycle
    // *******************************************************************
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mAssetManager = getActivity().getAssets();
        mContext = getActivity().getApplicationContext();
        // onCreate
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // onCreate
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chainIntent = new team.envie.fashion.enviefashion.chain.main.ChainIntent(getActivity());
        chainPref = new team.envie.fashion.enviefashion.chain.main.ChainPreference(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        // onStart
    }

    @Override
    public void onResume() {
        super.onResume();
        // onResume
    }

    @Override
    public void onPause() {
        super.onPause();
        // onPause
    }

    @Override
    public void onStop() {
        super.onStop();
        // onStop
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // onDestroyView
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // onDestroy
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // onDetach
    }
}
