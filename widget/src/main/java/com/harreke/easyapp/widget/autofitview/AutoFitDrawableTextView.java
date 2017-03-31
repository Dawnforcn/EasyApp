package com.harreke.easyapp.widget.autofitview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/07/24

 Drawable自适应大小的TextView

 设置的Drawable将等比缩放至适合字体高度的大小
 */
public class AutoFitDrawableTextView extends AppCompatTextView {
    public AutoFitDrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable[] drawables = getCompoundDrawables();

        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    @Override
    public final void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        float textSize = getTextSize();

        setDrawable(left, textSize);
        setDrawable(top, textSize);
        setDrawable(bottom, textSize);
        setDrawable(right, textSize);
        super.setCompoundDrawables(left, top, right, bottom);
    }

    private void setDrawable(Drawable drawable, float textSize) {
        int width;
        int height;

        if (drawable != null) {
            width = drawable.getIntrinsicWidth();
            height = drawable.getIntrinsicHeight();
            drawable.setBounds(0, 0, (int) (width * textSize / height), (int) textSize);
        }
    }
}