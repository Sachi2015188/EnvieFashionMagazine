package team.envie.fashion.magazin.app.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.androidquery.AQuery;

import team.envie.fashion.magazin.app.R;
import team.envie.fashion.magazin.app.chain.main.ChainPreference;
import team.envie.fashion.magazin.app.utils.Logged;


/**
 * @author Shuhei Iwamoto
 * @version 1.0.0
 * <p>
 *     MainActivity bottom listview fragment
 *     {@link team.envie.fashion.magazin.app.activity.MainActivity}
 * </p>
 */
public class MainListView extends BaseFragment {

    /** Gallery */
    private Gallery mGallery;
    /** Adapter */
    private PlaceAdapter mPlaceAdapter;

    public static MainListView newInstance(int sectionNumber) {
        MainListView fragment = new MainListView();
        Bundle args = new Bundle();
        args.putInt("num", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.test1);
        View view1 = fragment.getView();
        ImageView image = (ImageView)view1.findViewById(R.id.main_image);
        image.setImageResource(R.drawable.envie_2011_05);
        return inflater.inflate(R.layout.fragment_main_listview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        TypedArray thumbnails = getResources().obtainTypedArray(R.array.thumbnail);

        mPlaceAdapter = new PlaceAdapter(getActivity(), thumbnails);
        mGallery = (Gallery) view.findViewById(R.id.gallery);
        mGallery.setAdapter(mPlaceAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Logged.w("--" + position +"--");
                ChainPreference chainPref = new ChainPreference(getActivity());
                chainPref.save("num", position);
//                newInstance(position);
            }
        });
    }

    /**
     * ViewHolder
     */
    static class ViewHolder {
        ImageView image;
    }

    /**
     * PlaceAdapter
     */
    private class PlaceAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private Context mContext;
        private TypedArray mTypedArray;

        public PlaceAdapter(Context context,  TypedArray array) {
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

            AQuery aq = new AQuery(convertView);
            aq.id(holder.image).image(getItem(position));

            return convertView;
        }
    }
}