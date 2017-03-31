package com.harreke.easyapp.helper;

import android.support.annotation.NonNull;
import android.widget.CompoundButton;

import com.harreke.easyapp.common.interf.IDestroyable;

import java.util.ArrayList;
import java.util.List;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/01/08
 */
public class CompoundButtonHelper implements IDestroyable, CompoundButton.OnCheckedChangeListener {
    private boolean mBlock = false;
    private List<CompoundButton> mCompoundButtonList;
    private OnButtonCheckedChangeListener mOnButtonCheckedChangeListener = null;

    public CompoundButtonHelper(@NonNull CompoundButton... compoundButtons) {
        mCompoundButtonList = new ArrayList<>();
        add(compoundButtons);
    }

    public void add(@NonNull CompoundButton... compoundButtons) {
        for (CompoundButton compoundButton : compoundButtons) {
            compoundButton.setOnCheckedChangeListener(this);
            mCompoundButtonList.add(compoundButton);
        }
    }

    public void check(int position) {
        if (position >= 0 && position < mCompoundButtonList.size()) {
            mBlock = true;
            for (int i = 0; i < mCompoundButtonList.size(); i++) {
                if (position != i) {
                    mCompoundButtonList.get(i).setChecked(false);
                }
            }
            mCompoundButtonList.get(position).setChecked(true);
            mBlock = false;
        }
    }

    public void checkById(int buttonId) {
        int i;

        for (i = 0; i < mCompoundButtonList.size(); i++) {
            if (i == mCompoundButtonList.get(i).getId()) {
                break;
            }
        }
        if (i < mCompoundButtonList.size()) {
            check(i);
        }
    }

    public void clear() {
        for (CompoundButton compoundButton : mCompoundButtonList) {
            compoundButton.setOnCheckedChangeListener(null);
        }
        mCompoundButtonList.clear();
    }

    @Override
    public void destroy() {
        clear();
        mOnButtonCheckedChangeListener = null;
    }

    public void iterate(@NonNull IIterator iIterator) {
        for (CompoundButton compoundButton : mCompoundButtonList) {
            iIterator.iterate(compoundButton);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CompoundButton compoundButton;
        int position = 0;
        int i;

        if (!mBlock) {
            mBlock = true;
            for (i = 0; i < mCompoundButtonList.size(); i++) {
                compoundButton = mCompoundButtonList.get(i);
                if (!compoundButton.equals(buttonView)) {
                    compoundButton.setChecked(false);
                } else {
                    position = i;
                }
            }
            mBlock = false;
            if (mOnButtonCheckedChangeListener != null) {
                mOnButtonCheckedChangeListener.onButtonCheck(buttonView, position);
            }
        }
    }

    public void remove(@NonNull CompoundButton compoundButton) {
        compoundButton.setOnCheckedChangeListener(null);
        mCompoundButtonList.remove(compoundButton);
    }

    public void setOnButtonCheckedChangeListener(OnButtonCheckedChangeListener onButtonCheckedChangeListener) {
        mOnButtonCheckedChangeListener = onButtonCheckedChangeListener;
    }

    public interface IIterator {
        void iterate(CompoundButton compoundButton);
    }

    /**
     由 Harreke（harreke@live.cn） 创建于 2015/01/08
     */
    public interface OnButtonCheckedChangeListener {
        void onButtonCheck(CompoundButton compoundButton, int position);
    }
}