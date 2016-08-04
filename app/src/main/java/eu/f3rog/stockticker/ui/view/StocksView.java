package eu.f3rog.stockticker.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import eu.f3rog.stockticker.view.IStocksView;

/**
 * Created by f3rog on 8/4/16.
 */

public class StocksView
        extends RecyclerView
        implements IStocksView {

    public StocksView(Context context) {
        super(context);
    }

    public StocksView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StocksView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        this.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
