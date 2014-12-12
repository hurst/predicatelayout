package com.raineverywhere.predicatelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class PredicateLayout extends LinearLayout {
    private int widthSoFar = 0;
    private LinearLayout layoutRow;
    private int maxWidth;
    private Context context;

    public PredicateLayout(Context context) {
        this(context, null);
    }

    public PredicateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        maxWidth = getResources().getDisplayMetrics().widthPixels;
        widthSoFar = 0;

        layoutRow = new LinearLayout(context);
        layoutRow.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        layoutRow.setOrientation(LinearLayout.HORIZONTAL);
    }

    public void addView(View view, boolean end) {
        view.measure(0,0);
        widthSoFar += view.getMeasuredWidth();

        if (widthSoFar >= maxWidth) {
            addView(layoutRow);

            layoutRow = new LinearLayout(context);
            layoutRow.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutRow.setOrientation(LinearLayout.HORIZONTAL);

            layoutRow.addView(view);
            widthSoFar = view.getMeasuredWidth();
        } else {
            layoutRow.addView(view);
        }

        if(end) {
            addViewFinish();
        }

    }

    public void addViewFinish() {
        addView(layoutRow);
    }

    @Override
    public void removeAllViews() {
        super.removeAllViews();
        init(context);
    }
}