package team.envie.fashion.enviefashion.controller.ui.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import butterknife.ButterKnife;
import butterknife.InjectView;
import team.envie.fashion.enviefashion.R;
import team.envie.fashion.enviefashion.controller.ui.activity.ApplicationController;
import team.envie.fashion.enviefashion.model.entity.Constants;


/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 *          <p>
 *          MainActivity bottom listview fragment
 *          </p>
 */
public class MainGallery extends BaseFragment implements ViewSwitcher.ViewFactory {

    /**
     * Gallery Adapter
     */
    private SelfAdapter mSelfAdapter;
    /**
     * MainImage
     */
    private ImageSwitcher mMainImage;

    private TextView mTitle;

    @InjectView(R.id.gallery)
    Gallery mGallery;

    private team.envie.fashion.enviefashion.utils.FragmentUtils mFragmentUtils;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_listview, container, false);

        // init Butterknife
        ButterKnife.inject(this, view);

        // init FragmentUtils
        mFragmentUtils = new team.envie.fashion.enviefashion.utils.FragmentUtils(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // rootView
        View rootView = mFragmentUtils.getRootView(R.id.fragment_main_image);

        // Main Screen Image
        mMainImage = ButterKnife.findById(rootView, R.id.main_image_switcher);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMainImage.setFactory(this);
        mMainImage.setImageDrawable(getTypedArray(R.array.coverID).getDrawable(0));

        // init SelfAdapter
        mSelfAdapter = new SelfAdapter(mContext, getTypedArray(R.array.thumbnail));
        mGallery.setAdapter(mSelfAdapter);

        // Main Screen title
        mTitle = ButterKnife.findById(getActivity(), R.id.main_title);
        mTitle.setText(Constants.DATA.get(0).getImageDateTitle());

        // TODO テキストのチェンジアニメーション
        // https://android.googlesource.com/platform/development/+/master/samples/ApiDemos/src/com/example/android/apis/view/Animation2.java
        mTitle.setTypeface(team.envie.fashion.enviefashion.utils.TypeFaceUtils.garamond(mAssetManager));

        mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mMainImage.setInAnimation(getActivity(), android.R.anim.fade_in);
                mMainImage.setOutAnimation(getActivity(), android.R.anim.fade_out);
                mMainImage.setImageDrawable(getTypedArray(R.array.coverID).getDrawable(position));
                mTitle.setText(Constants.DATA.get(position).getImageDateTitle());
                ApplicationController.position = position;
            }
        });


        // TODO メインイメージをContents画像へきりかえる
        mMainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mMainImage.setInAnimation(getActivity(), android.R.anim.slide_in_left);
//                mMainImage.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
//                mMainImage.setImageDrawable(getTypedArray(R.array.coverID).getDrawable(0));
            }
        });
    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /* ***************************************************************************************

                                            Private Method

        ***************************************************************************************  */

    /**
     * get obtainTypedArray
     *
     * @param resId int resource array
     * @return
     */
    private TypedArray getTypedArray(int resId) {
        return getResources().obtainTypedArray(resId);
    }


    /* ***************************************************************************************

                                            Adapter

        ***************************************************************************************  */
    /**
     * ViewHolder
     */
    static class ViewHolder {
        ImageView image;
    }

    /**
     * SelfAdapter
     */
    private class SelfAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private Context mContext;
        private TypedArray mTypedArray;

        public SelfAdapter(Context context, TypedArray array) {
            mContext = context;
            mInflater = LayoutInflater.from(mContext);
            mTypedArray = array;
        }

        @Override
        public int getCount() {
            return mTypedArray.length();
        }

        @Override
        public Bitmap getItem(int position) {
            Bitmap bitmap = ((BitmapDrawable) mTypedArray.getDrawable(position)).getBitmap();
            return bitmap;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_fragment_main_listview, null);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.item_list_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.image.setImageBitmap(getItem(position));
            return convertView;
        }
    }
}