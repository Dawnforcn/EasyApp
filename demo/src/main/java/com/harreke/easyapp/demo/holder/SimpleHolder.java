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
//public class SimpleHolder extends RecyclerHolder<Integer> {
//    @InjectView
//    TextView simple_text;
//
//    public SimpleHolder(View itemView) {
//        super(itemView);
//    }
//
//    @Override
//    public void setItem(Integer integer) {
//        simple_text.setText(String.format("第%d条数据：%d", getAdapterPosition(), integer));
//    }
//}