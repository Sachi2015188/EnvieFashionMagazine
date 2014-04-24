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


//TODO AcitivityのListをもってくる
//TODO コールバック関数
public class ListDialogFragment extends DialogFragment {

    private static ListDialogFragment sListDialogFragment;

    public static ListDialogFragment create() {
        sListDialogFragment = new ListDialogFragment();
        return sListDialogFragment;
    }

    team.envie.fashion.enviefashion.entity.LocaleItem[] localeItems = new team.envie.fashion.enviefashion.entity.LocaleItem[]{
            new team.envie.fashion.enviefashion.entity.LocaleItem("日本語", R.drawable.japanflag),
            new team.envie.fashion.enviefashion.entity.LocaleItem("English", R.drawable.unitedkingdomflag),
            new team.envie.fashion.enviefashion.entity.LocaleItem("中文", R.drawable.chinaflag),
            new team.envie.fashion.enviefashion.entity.LocaleItem("Espanol", R.drawable.spainflag),
            new team.envie.fashion.enviefashion.entity.LocaleItem("Francais", R.drawable.franceflag),
            new team.envie.fashion.enviefashion.entity.LocaleItem("Deutsch", R.drawable.germanyflag)
    };


    /* ***************************************************************************************

                                            LifeCycle

        ***************************************************************************************  */
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
                        team.envie.fashion.enviefashion.logic.LocaleState.JAPAN.changeLocale(getActivity());
                        break;
                    case 1:
                        team.envie.fashion.enviefashion.logic.LocaleState.ENGLISH.changeLocale(getActivity());
                        break;
                    case 2:
                        team.envie.fashion.enviefashion.logic.LocaleState.CHINESE.changeLocale(getActivity());
                        break;
                    case 3:
                        team.envie.fashion.enviefashion.logic.LocaleState.ESPANOL.changeLocale(getActivity());
                        break;
                    case 4:
                        team.envie.fashion.enviefashion.logic.LocaleState.FRANCE.changeLocale(getActivity());
                        break;
                    case 5:
                        team.envie.fashion.enviefashion.logic.LocaleState.DEUTSCH.changeLocale(getActivity());
                        break;
                }
                sListDialogFragment.dismiss();
            }
        });
    }

    /**
     * Locale item adapter
     */
    private static class SelfAdapter extends BaseAdapter {

        private Context mContext;
        private team.envie.fashion.enviefashion.entity.LocaleItem[] mItems;

        public SelfAdapter(Context context, team.envie.fashion.enviefashion.entity.LocaleItem[] items) {
            mContext = context;
            mItems = items;
        }

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public team.envie.fashion.enviefashion.entity.LocaleItem getItem(int position) {
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            team.envie.fashion.enviefashion.view.LocaleItemView localeItemView;
            if (convertView == null) {
                localeItemView = Michelangelo.build(mContext, team.envie.fashion.enviefashion.view.LocaleItemView.class);
            } else {
                localeItemView = (team.envie.fashion.enviefashion.view.LocaleItemView) convertView;
            }
            team.envie.fashion.enviefashion.entity.LocaleItem item = getItem(position);
            localeItemView.bind(item);
            return localeItemView;
        }
    }
}
