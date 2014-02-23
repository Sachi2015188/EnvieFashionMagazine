package team.envie.fashion.magazin.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

import team.envie.fashion.magazin.app.chain.main.ChainIntent;
import team.envie.fashion.magazin.app.chain.main.ChainPreference;

public abstract class BaseFragment extends Fragment {

    // *******************************************************************
    // Decralation
    // *******************************************************************
    protected AQuery aq;
    protected ChainIntent chainIntent;
    protected ChainPreference chainPref;


    // *******************************************************************
    // LifeCycle
    // *******************************************************************
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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
        aq = new AQuery(getActivity(), view);
        chainIntent = new ChainIntent(getActivity());
        chainPref = new ChainPreference(getActivity());
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
