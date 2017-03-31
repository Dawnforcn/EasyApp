package com.harreke.easyapp.widget.softinputview;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

import com.harreke.easyapp.util.ViewUtil;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/03/09
 */
public class SoftInputEditText extends AppCompatEditText {
    private OnToggleListener mOnToggleListener = null;
    private boolean mSoftInputShowing = false;

    public SoftInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void hideSoftInput() {
        ViewUtil.hideInputMethod(this);
    }

    public boolean isSoftInputShowing() {
        return mSoftInputShowing;
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new EditWrapper(super.onCreateInputConnection(outAttrs), false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && !mSoftInputShowing) {
            mSoftInputShowing = true;
            if (mOnToggleListener != null) {
                mOnToggleListener.onShowSoftInput(this);
            }
        }

        return super.onTouchEvent(event);
    }

    public void setOnToggleListener(OnToggleListener onToggleListener) {
        mOnToggleListener = onToggleListener;
    }

    public void showSoftInput() {
        ViewUtil.showInputMethod(this);
    }

    public interface OnToggleListener {
        void onHideSoftInput(EditText editText);

        void onShowSoftInput(EditText editText);
    }

    private class EditWrapper extends InputConnectionWrapper {
        EditWrapper(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean finishComposingText() {
            if (mSoftInputShowing) {
                mSoftInputShowing = false;
                if (mOnToggleListener != null) {
                    mOnToggleListener.onHideSoftInput(SoftInputEditText.this);
                }
            }
            return super.finishComposingText();
        }
    }
}
