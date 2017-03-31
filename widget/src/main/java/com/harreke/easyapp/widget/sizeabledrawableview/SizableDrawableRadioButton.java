package com.harreke.easyapp.widget.sizeabledrawableview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.harreke.easyapp.widget.R;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/07/24

 Drawable自适应大小的RadioButton

 设置的Drawable将等比缩放至适合字体高度的大小
 */
public class SizableDrawableRadioButton extends AppCompatRadioButton {
    private boolean mCenteringDrawable;
    private int mDrawableHeight;
    private int mDrawableWidth;

    public SizableDrawableRadioButton(Context context) {
        this(context, null);
    }

    public SizableDrawableRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray style = context.obtainStyledAttributes(attrs, R.styleable.SizableDrawableRadioButton);
        mDrawableWidth = (int) style.getDimension(R.styleable.SizableDrawableRadioButton_drawableWidth, 0);
        mDrawableHeight = (int) style.getDimension(R.styleable.SizableDrawableRadioButton_drawableHeight, 0);
        mCenteringDrawable = style.getBoolean(R.styleable.SizableDrawableRadioButton_centeringDrawable, false);
        style.recycle();
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Drawable[] drawables;
        Paint paint;
        Paint.FontMetrics fontMetrics;
        int drawableLeftWidth = 0;
        int drawableRightWidth = 0;
        int drawableTopHeight = 0;
        int drawableBottomHeight = 0;
        float textWidth;
        float textHeight;
        int drawablePadding;
        float bodyWidth;
        float bodyHeight;
        int paddingWidth;
        int paddingHeight;

        if (mCenteringDrawable) {
            paint = getPaint();
            textWidth = paint.measureText(getText().toString());

            fontMetrics = paint.getFontMetrics();
            textHeight = fontMetrics.bottom - fontMetrics.top;

            drawables = getCompoundDrawables();
            drawablePadding = getCompoundDrawablePadding();

            if (drawables[0] != null) {
                drawableLeftWidth = drawables[0].getBounds().width() + drawablePadding;
            }
            if (drawables[2] != null) {
                drawableRightWidth = drawables[2].getBounds().width() + drawablePadding;
            }
            bodyWidth = textWidth + drawableLeftWidth + drawableRightWidth;
            paddingWidth = (int) ((getMeasuredWidth() - bodyWidth) / 2);

            if (drawables[1] != null) {
                drawableTopHeight = drawables[1].getBounds().height() + drawablePadding;
            }
            if (drawables[3] != null) {
                drawableBottomHeight = drawables[3].getBounds().height() + drawablePadding;
            }
            bodyHeight = textHeight + drawableTopHeight + drawableBottomHeight;
            paddingHeight = (int) ((getMeasuredHeight() - bodyHeight) / 2);

            setPadding(paddingWidth, paddingHeight, paddingWidth, paddingHeight);
        }
    }

    @Override
    public final void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        setDrawable(left);
        setDrawable(top);
        setDrawable(bottom);
        setDrawable(right);
        super.setCompoundDrawables(left, top, right, bottom);
    }

    private void setDrawable(Drawable drawable) {
        if (drawable != null && mDrawableWidth > 0 && mDrawableHeight > 0) {
            drawable.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
    }

    public final void setDrawableBottom(Drawable drawable) {
        Drawable[] drawables = getCompoundDrawables();

        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawable);
    }

    public final void setDrawableLeft(Drawable drawable) {
        Drawable[] drawables = getCompoundDrawables();

        setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3]);
    }

    public final void setDrawableRight(Drawable drawable) {
        Drawable[] drawables = getCompoundDrawables();

        setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3]);
    }

    public final void setDrawableTop(Drawable drawable) {
        Drawable[] drawables = getCompoundDrawables();

        setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
    }
}