//package com.harreke.easyapp.demo.activity;
//
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.RadioButton;
//
//import com.harreke.demo.R;
//import com.harreke.demo.holder.ComplexHolder;
//import com.harreke.demo.holder.SimpleHolder;
//import com.harreke.easyapp.base.activity.ActivityFramework;
//import com.harreke.easyapp.injection.annotations.InjectClick;
//import com.harreke.easyapp.injection.annotations.InjectGroupCheck;
//import com.harreke.easyapp.injection.annotations.InjectLayout;
//import com.harreke.easyapp.injection.annotations.InjectToolbar;
//import com.harreke.easyapp.swipe.recyclerview.IHolderParser;
//import com.harreke.easyapp.swipe.recyclerview.IRecyclerRequestor;
//import com.harreke.easyapp.swipe.recyclerview.RecyclerFramework;
//import com.harreke.easyapp.swipe.recyclerview.itemdecorations.GridItemDecoration;
//import com.harreke.easyapp.swipe.recyclerview.itemdecorations.LinearItemDecoration;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Harreke on 2016/1/12.
// */
//@InjectLayout
//@InjectToolbar
//public class RecyclerActivity extends ActivityFramework {
//    private static final String mAlphabet = "abcdefghijklmnopqrstuvwxyz";
//    private IHolderParser<String, ComplexHolder> mComplexHolderParser = new IHolderParser<String, ComplexHolder>() {
//        @NonNull
//        @Override
//        public ComplexHolder createItemHolder(View itemView) {
//            return new ComplexHolder(itemView);
//        }
//
//        @Override
//        public int getItemLayoutId() {
//            return R.layout.item_complex;
//        }
//
//        @Override
//        public void onBindItem(ComplexHolder holder, String s) {
//        }
//
//        @Override
//        public void onItemViewClick(ComplexHolder holder, String s) {
//            showToast("点击了复杂布局，序号：" + holder.getAdapterPosition());
//        }
//    };
//    private IRecyclerRequestor mComplexLoadListener = new IRecyclerRequestor() {
//        @Override
//        public void onFinishRequest(int newAddedCount, int totalCount) {
//        }
//
//        @Override
//        public Object onRequestData(int pageNo) {
//            List<Object> list = new ArrayList<>();
//
//            list.addAll(generateSimpleData(10));
//            list.addAll(generateComplexData(10));
//
//            return list;
//        }
//    };
//    private GridItemDecoration mGridItemDecoration;
//    private GridLayoutManager mGridLayoutManager;
//    private LinearItemDecoration mLinearItemDecoration;
//    private LinearLayoutManager mLinearLayoutManager;
//    private RecyclerFramework mRecycler;
//    private IHolderParser<Integer, SimpleHolder> mSimpleHolderParser = new IHolderParser<Integer, SimpleHolder>() {
//        @NonNull
//        @Override
//        public SimpleHolder createItemHolder(View itemView) {
//            return new SimpleHolder(itemView);
//        }
//
//        @Override
//        public int getItemLayoutId() {
//            return R.layout.item_simple;
//        }
//
//        @Override
//        public void onBindItem(SimpleHolder holder, Integer integer) {
//        }
//
//        @Override
//        public void onItemViewClick(SimpleHolder holder, Integer integer) {
//            showToast("点击了简单布局，序号：" + holder.getAdapterPosition());
//        }
//    };
//    private IRecyclerRequestor mSimpleLoadListener = new IRecyclerRequestor() {
//        @Override
//        public void onFinishRequest(int newAddedCount, int totalCount) {
//        }
//
//        @Override
//        public Object onRequestData(int pageNo) {
//            return generateSimpleData(20);
//        }
//    };
//
//    @Override
//    public void argument(Intent intent) {
//    }
//
//    @Override
//    public void config() {
//        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        mLinearItemDecoration = new LinearItemDecoration(this, LinearLayoutManager.VERTICAL);
//        mGridLayoutManager = new GridLayoutManager(this, 2);
//        mGridItemDecoration = new GridItemDecoration(2);
//
//        mRecycler = new RecyclerFramework(this);
//        mRecycler.setDataRequestor(mSimpleLoadListener);
//        mRecycler.registerHolderParser(Integer.class, mSimpleHolderParser);
//        mRecycler.registerHolderParser(String.class, mComplexHolderParser);
//        mRecycler.setShowRetryWhenEmptyIdle(false);
//        mRecycler.attachAdapter(false);
//    }
//
//    private List<String> generateComplexData(int count) {
//        List<String> list = new ArrayList<>(count);
//        StringBuilder builder = new StringBuilder();
//        int i, j;
//
//        for (i = 0; i < 20; i++) {
//            builder.delete(0, builder.length());
//            for (j = 0; j < 10; j++) {
//                builder.append(mAlphabet.charAt((int) (Math.random() * mAlphabet.length())));
//            }
//            list.add(builder.toString());
//        }
//
//        return list;
//    }
//
//    private List<Integer> generateSimpleData(int count) {
//        List<Integer> list = new ArrayList<>(count);
//        int i;
//
//        for (i = 0; i < 20; i++) {
//            list.add((int) (Math.random() * 100));
//        }
//
//        return list;
//    }
//
//    @Override
//    public void launch() {
//    }
//
//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        return false;
//    }
//
//    @InjectGroupCheck
//    void recycler_group(RadioButton radioButton, int index) {
//        switch (index) {
//            case 0:
//                mRecycler.removeItemDecoration(mGridItemDecoration);
//                mRecycler.setLayoutManager(mLinearLayoutManager);
//                mRecycler.addItemDecoration(mLinearItemDecoration);
//                break;
//            case 1:
//                mRecycler.removeItemDecoration(mLinearItemDecoration);
//                mRecycler.setLayoutManager(mGridLayoutManager);
//                mRecycler.addItemDecoration(mGridItemDecoration);
//                break;
//        }
//    }
//
//    @InjectClick
//    void recycler_load_complex() {
//        mRecycler.setDataRequestor(mComplexLoadListener);
//        mRecycler.setAutoLoadMore(false);
//        mRecycler.onRefresh();
//    }
//
//    @InjectClick
//    void recycler_load_simple() {
//        mRecycler.setDataRequestor(mSimpleLoadListener);
//        mRecycler.setAutoLoadMore(true);
//        mRecycler.onRefresh();
//    }
//}