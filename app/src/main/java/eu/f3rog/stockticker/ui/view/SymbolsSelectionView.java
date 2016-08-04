package eu.f3rog.stockticker.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import blade.Presenter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.f3rog.stockticker.R;
import eu.f3rog.stockticker.model.Symbol;
import eu.f3rog.stockticker.presenter.SymbolSelectionPresenter;
import eu.f3rog.stockticker.ui.adapter.MyRecyclerAdapter;
import eu.f3rog.stockticker.view.ISymbolSelectionView;

/**
 * Created by f3rog on 8/4/16.
 */
public class SymbolsSelectionView
        extends LinearLayout
        implements ISymbolSelectionView {

    @Bind(R.id.edt_symbol)
    EditText mSymbolName;

    @Bind(R.id.rv_symbols)
    RecyclerView mRecyclerView;
    private MyRecyclerAdapter mAdapter;

    @Presenter
    SymbolSelectionPresenter mPresenter;

    public SymbolsSelectionView(Context context) {
        super(context);
    }

    public SymbolsSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SymbolsSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SymbolsSelectionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MyRecyclerAdapter.Builder(getContext()).build();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void show(@NonNull List<Symbol> symbols) {
        MyRecyclerAdapter.Builder adapterBuilder = new MyRecyclerAdapter.Builder(getContext())
                .add(R.layout.item_symbol, symbols);
        adapterBuilder.reset(mAdapter);
    }

    @Override
    public void showError(@StringRes int msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearInput() {
        mSymbolName.setText("");
    }

    @OnClick(R.id.btn_add_symbol)
    public void add() {
        String symbol = mSymbolName.getText().toString().trim();
        mPresenter.onAddClicked(symbol);
    }
}
