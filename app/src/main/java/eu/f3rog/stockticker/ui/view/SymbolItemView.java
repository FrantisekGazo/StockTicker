package eu.f3rog.stockticker.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by f3rog on 8/4/16.
 */
public class SymbolItemView
        extends TextView {

    public SymbolItemView(Context context) {
        super(context);
    }

    public SymbolItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SymbolItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SymbolItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setTag(Object tag) {
        super.setTag(tag);
        setText(tag != null ? tag.toString() : "");
    }
}
