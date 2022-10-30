package com.back4app.java.example;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class InstantAutoComplete extends androidx.appcompat.widget.AppCompatAutoCompleteTextView {
    public InstantAutoComplete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public InstantAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InstantAutoComplete(Context context) {
        super(context);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused && getFilter()!=null){
            performFiltering(getText(),0);
        }
    }
}
