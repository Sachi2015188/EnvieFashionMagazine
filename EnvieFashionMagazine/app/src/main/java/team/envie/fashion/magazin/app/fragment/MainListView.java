package team.envie.fashion.magazin.app.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.devsmart.android.ui.HorizontalListView;

import team.envie.fashion.magazin.app.R;
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

    /** HorizontalListView */
    private HorizontalListView mHorizontalListView;
    /** Adapter */
    private PlaceAdapter mPlaceAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_listview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        TypedArray images = getResources().obtainTypedArray(R.array.thumbnail);
        mPlaceAdapter = new PlaceAdapter(getActivity(), images);
        mHorizontalListView = (HorizontalListView) view.findViewById(R.id.horizon_list);
        mHorizontalListView.setAdapter(mPlaceAdapter);
        mHorizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Logged.w("--" + i+"--");
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        public Drawable getItem(int position) {
            return mTypedArray.getDrawable(position);
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
            aq.id(R.id.item_list_image).image(getItem(position));

            return convertView;
        }
    }
}
