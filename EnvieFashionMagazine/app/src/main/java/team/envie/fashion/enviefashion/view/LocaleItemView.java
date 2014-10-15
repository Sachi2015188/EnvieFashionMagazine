package team.envie.fashion.enviefashion.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.romainpiel.michelangelo.InflateLayout;
import com.romainpiel.michelangelo.InjectViews;

import butterknife.InjectView;
import team.envie.fashion.enviefashion.R;
import team.envie.fashion.enviefashion.model.entity.LocaleItem;

@InflateLayout(R.layout.locale_item)
@InjectViews
public class LocaleItemView extends LinearLayout {

    @InjectView(R.id.locale_text)
    TextView mTextView;

    @InjectView(R.id.locale_image)
    ImageView mImageView;

    public LocaleItemView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
    }

    public void bind(LocaleItem item) {
        mTextView.setText(item.getLocale());
        mImageView.setImageResource(item.getLocaleDrawable());
    }
}
