package eu.f3rog.stockticker.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.f3rog.stockticker.R;
import eu.f3rog.stockticker.model.Stock;

/**
 * Created by f3rog on 8/4/16.
 */

public class StockItemView
        extends LinearLayout {

    @Bind(R.id.txt_stock_name)
    TextView mName;
    @Bind(R.id.txt_stock_price)
    TextView mPrice;

    public StockItemView(Context context) {
        super(context);
    }

    public StockItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StockItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StockItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    public void setTag(Object tag) {
        super.setTag(tag);

        if (tag instanceof Stock) {
            Stock stock = (Stock) tag;

            mName.setText(stock.getSymbol().getName());
            mPrice.setText(stock.getFormattedPrice());
        }
    }
}
