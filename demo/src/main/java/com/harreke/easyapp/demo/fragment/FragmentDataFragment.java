package com.harreke.easyapp.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.harreke.easyapp.app.fragment.FragmentFramework;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectView;

/**
 由Harreke于2016/1/13创建
 */
@InjectLayout
public class FragmentDataFragment extends FragmentFramework {
    @InjectView
    EditText data_input;
    @InjectView
    TextView data_toast;

    @Override
    public void argument(Bundle bundle) {
    }

    @Override
    public void config() {
    }

    @InjectClick
    void data_send() {
        String input = data_input.getText().toString();

        if (input.length() > 0) {
            sendDataToActivity("message", input);
        } else {
            showToast("发送的内容不能为空");
        }
    }

    @Override
    public void launch() {
    }

    @Override
    public void onReceiveDataFromActivity(@NonNull String name, @Nullable Object data) {
        data_toast.setText(String.format("收到来自Activity的消息：%s", String.valueOf(data)));
    }
}