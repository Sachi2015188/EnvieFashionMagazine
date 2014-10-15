package team.envie.fashion.enviefashion.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.romainpiel.michelangelo.Michelangelo;

import team.envie.fashion.enviefashion.R;
import team.envie.fashion.enviefashion.model.entity.LocaleItem;
import team.envie.fashion.enviefashion.logic.SnsState;
import team.envie.fashion.enviefashion.view.LocaleItemView;


public class SnsDialog extends DialogFragment {

    private static SnsDialog sSnsDialog;

    public static SnsDialog create() {
        sSnsDialog = new SnsDialog();
        return sSnsDialog;
    }

    LocaleItem[] localeItems = new LocaleItem[]{
            new LocaleItem("FACEBOOK", R.drawable.facebook),
            new LocaleItem("TWITTER", R.drawable.twitter),
            new LocaleItem("WEIBO", R.drawable.weibo),
    };

    /* LifeCycle
     =========================================================================== */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.locale, container);
        mListView = (ListView) view.findViewById(R.id.locale_list);
        mListView.setAdapter(new SelfAdapter(getActivity(), localeItems));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch (position) {
                    case 0:
                        SnsState.FACEBOOK.moveSns(getActivity());
                        break;
                    case 1:
                        SnsState.TWITTER.moveSns(getActivity());
                        break;
                    case 2:
                        SnsState.WEIBO.moveSns(getActivity());
                        break;
                }
                sSnsDialog.dismiss();
            }
        });
    }

    /**
     * Locale item adapter
     */
    private static class SelfAdapter extends BaseAdapter {

        private Context mContext;
        private LocaleItem[] mItems;

        public SelfAdapter(Context context, LocaleItem[] items) {
            mContext = context;
            mItems = items;
        }

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public LocaleItem getItem(int position) {
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            LocaleItemView localeItemView;
            if (convertView == null) {
                localeItemView = Michelangelo.build(mContext, LocaleItemView.class);
            } else {
                localeItemView = (LocaleItemView) convertView;
            }
            LocaleItem item = getItem(position);
            localeItemView.bind(item);
            return localeItemView;
        }
    }

}
