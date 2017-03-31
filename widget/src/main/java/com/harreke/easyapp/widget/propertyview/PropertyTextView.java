package com.harreke.easyapp.widget.propertyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import com.harreke.easyapp.util.MetricUtil;
import com.harreke.easyapp.util.ResourceUtil;
import com.harreke.easyapp.widget.R;

/**
 由Harreke于2016/3/26创建
 */
public class PropertyTextView extends AppCompatTextView {
    private String mPropertyText;
    private int mPropertyTextColor;
    private int mPropertyTextSize;

    public PropertyTextView(Context context) {
        this(context, null);
    }

    public PropertyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray style = context.obtainStyledAttributes(attrs, R.styleable.PropertyTextView);
        mPropertyText = style.getString(R.styleable.PropertyTextView_property_text);
        mPropertyTextColor = style.getColor(R.styleable.PropertyTextView_property_text_color, ResourceUtil.getColor(getContext(), R.color.gray));
        mPropertyTextSize = (int) style.getDimension(R.styleable.PropertyTextView_property_text_size, MetricUtil.getPixel(12));
        style.recycle();
        setText(getText());
    }

    public void setPropertyText(String propertyText) {
        mPropertyText = propertyText;
    }

    public void setPropertyTextColor(int propertyTextColor) {
        mPropertyTextColor = propertyTextColor;
    }

    public void setPropertyTextSize(int propertyTextSize) {
        mPropertyTextSize = propertyTextSize;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        SpannableStringBuilder builder;

        if (!TextUtils.isEmpty(mPropertyText)) {
            builder = new SpannableStringBuilder(mPropertyText);
            builder.setSpan(new ForegroundColorSpan(mPropertyTextColor), 0, mPropertyText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new AbsoluteSizeSpan(mPropertyTextSize), 0, mPropertyText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (!TextUtils.isEmpty(text)) {
                builder.append(text);
            }
            super.setText(builder, BufferType.SPANNABLE);
        } else {
            super.setText(text, type);
        }
    }
}