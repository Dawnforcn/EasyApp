//package com.harreke.easyapp.demo.holder;
//
//import android.view.View;
//import android.widget.TextView;
//
//import com.harreke.easyapp.injection.annotations.InjectView;
//import com.harreke.easyapp.swipe.recyclerview.RecyclerHolder;
//
///**
// * Created by huoqisheng on 2016/4/21.
// */
//public class ComplexHolder extends RecyclerHolder<String> {
//    @InjectView
//    TextView complex_text;
//    @InjectView
//    TextView complex_text_sub;
//
//    public ComplexHolder(View itemView) {
//        super(itemView);
//    }
//
//    @Override
//    public void setItem(String s) {
//        complex_text.setText(String.format("第%d条数据", getAdapterPosition()));
//        complex_text_sub.setText(s);
//    }
//}