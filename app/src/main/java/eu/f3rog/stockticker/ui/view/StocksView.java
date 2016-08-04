package eu.f3rog.stockticker.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;

import java.util.List;

import blade.Presenter;
import eu.f3rog.stockticker.R;
import eu.f3rog.stockticker.model.Stock;
import eu.f3rog.stockticker.presenter.StockPresenter;
import eu.f3rog.stockticker.ui.adapter.MyRecyclerAdapter;
import eu.f3rog.stockticker.view.IStocksView;

/**
 * Created by f3rog on 8/4/16.
 */

public class StocksView
        extends RecyclerView
        implements IStocksView {

    private MyRecyclerAdapter mAdapter;

    @Presenter
    StockPresenter mPresenter;

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
        mAdapter = new MyRecyclerAdapter.Builder(getContext()).build();
        this.setAdapter(mAdapter);
    }

    @Override
    public void show(@NonNull List<Stock> stocks) {
        MyRecyclerAdapter.Builder adapterBuilder = new MyRecyclerAdapter.Builder(getContext())
                .add(R.layout.item_stock, stocks);
        adapterBuilder.reset(mAdapter);
    }

    @Override
    public void showError(@StringRes int msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
