package team.envie.fashion.enviefashion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.romainpiel.michelangelo.InflateLayout;
import com.romainpiel.michelangelo.InjectViews;

import butterknife.ButterKnife;
import butterknife.InjectView;
import team.envie.fashion.enviefashion.R;


/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 *          <p>
 *          MainActivity side panel fragment
 *          </p>
 */
@InflateLayout(R.layout.fragment_main_side)
@InjectViews
public class MainSideFragment extends team.envie.fashion.enviefashion.ui.fragment.BaseFragment {

    /**
     * ImageView Download Button
     */
    @InjectView(R.id.fragment_main_side_download) ImageView mDownload;

    /**
     * ImageView ToglleDrawer Button
     */
    @InjectView(R.id.fragment_main_side_openDrawer) ImageView mToggleDrawer;

    /**
     * ImageView Move Library Button
     */
    @InjectView(R.id.fragment_main_side_library) ImageView mLibrary;

    /**
     * Side Fragment Contetn Description
     */
    @InjectView(R.id.fragment_main_side_description) TextView mContentDescription;


    private team.envie.fashion.enviefashion.ui.helper.HelperMainSideFragment mListner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_side, container, false);

        // init HelperListener
        mListner = new team.envie.fashion.enviefashion.ui.helper.HelperMainSideFragment(getActivity());

        // init Butterknife
        ButterKnife.inject(this, view);
        return view;
    }

    // TODO typefaceはここで定義せずにcustomviewで定義する
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set typeface
        mContentDescription.setTypeface(team.envie.fashion.enviefashion.utils.TypeFaceUtils.bickam(mAssetManager));

        // Button push animation
        team.envie.fashion.enviefashion.utils.AnimUtils.pushScaleAnimation(mDownload);
        team.envie.fashion.enviefashion.utils.AnimUtils.pushScaleAnimation(mToggleDrawer);
        team.envie.fashion.enviefashion.utils.AnimUtils.pushScaleAnimation(mLibrary);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDownload.setOnClickListener(mListner.downloadListener);
        mLibrary.setOnClickListener(mListner.openLibraryListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
