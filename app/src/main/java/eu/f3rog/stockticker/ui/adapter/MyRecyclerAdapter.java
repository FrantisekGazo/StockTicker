package eu.f3rog.stockticker.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Class {@link MyRecyclerAdapter}
 *
 * @author FrantisekGazo
 * @version 2016-02-27
 */
public final class MyRecyclerAdapter
        extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    public static final class Builder {

        @NonNull
        private final Context mContext;
        @NonNull
        private final List<Item> mItems;

        public Builder(@NonNull Context context) {
            mContext = context;
            mItems = new ArrayList<>();
        }

        public <T> Builder add(@LayoutRes int layout, T... items) {
            for (T item : items) {
                mItems.add(new Item<>(layout, item));
            }
            return this;
        }

        public <T> Builder add(@LayoutRes int layout, @NonNull List<T> items) {
            for (T item : items) {
                mItems.add(new Item<>(layout, item));
            }
            return this;
        }

        public MyRecyclerAdapter build() {
            return new MyRecyclerAdapter(mContext, mItems);
        }

        public void reset(@NonNull MyRecyclerAdapter adapter) {
            adapter.setItems(mItems);
        }
    }

    private static final class Item<T> {

        @LayoutRes
        private final int mLayout;
        private final T mData;

        public Item(int layout, T data) {
            mLayout = layout;
            mData = data;
        }

        public int getLayout() {
            return mLayout;
        }

        public T getData() {
            return mData;
        }
    }

    public static final class ViewHolder
            extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    private LayoutInflater mLayoutInflater;
    @NonNull
    private List<Item> mItems;

    private MyRecyclerAdapter(@NonNull Context c, @NonNull List<Item> items) {
        mLayoutInflater = LayoutInflater.from(c);
        mItems = items;
    }

    private void setItems(@NonNull List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getLayout();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object data = mItems.get(position).getData();
        holder.itemView.setTag(data);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
