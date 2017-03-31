//package com.harreke.easyapp.demo.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.GridLayoutManager;
//import android.view.View;
//
//import com.harreke.demo.R;
//import com.harreke.demo.holder.SimpleHolder;
//import com.harreke.easyapp.base.fragment.FragmentFramework;
//import com.harreke.easyapp.injection.annotations.InjectLayout;
//import com.harreke.easyapp.swipe.recyclerview.IHolderParser;
//import com.harreke.easyapp.swipe.recyclerview.IRecyclerRequestor;
//import com.harreke.easyapp.swipe.recyclerview.RecyclerFramework;
//import com.harreke.easyapp.swipe.recyclerview.itemdecorations.GridItemDecoration;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Harreke on 2015/12/31.
// */
//@InjectLayout
//public class FragmentPagerFragment extends FragmentFramework implements IHolderParser<Integer, SimpleHolder>, IRecyclerRequestor {
//    private RecyclerFramework mRecycler;
//
//    @Override
//    public void argument(Bundle bundle) {
//    }
//
//    @Override
//    public void config() {
//        mRecycler = new RecyclerFramework(this);
//        mRecycler.registerHolderParser(Integer.class, this);
//        mRecycler.setDataRequestor(this);
//        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        mRecycler.setItemDecoration(new GridItemDecoration(2));
//        mRecycler.setSwipeRefreshEnabled(false);
//        mRecycler.setAutoLoadMore(false);
//        mRecycler.attachAdapter();
//    }
//
//    @NonNull
//    @Override
//    public SimpleHolder createItemHolder(View itemView) {
//        return new SimpleHolder(itemView);
//    }
//
//    private List<Integer> generateSimpleData() {
//        List<Integer> list = new ArrayList<>(10);
//        int i;
//
//        for (i = 0; i < 10; i++) {
//            list.add((int) (Math.random() * 100));
//        }
//
//        return list;
//    }
//
//    @Override
//    public int getItemLayoutId() {
//        return R.layout.item_simple;
//    }
//
//    @Override
//    public void launch() {
//        mRecycler.onRefresh();
//    }
//
//    @Override
//    public void onBindItem(SimpleHolder holder, Integer integer) {
//    }
//
//    @Override
//    public void onFinishRequest(int newAddedCount, int totalCount) {
//    }
//
//    @Override
//    public void onItemViewClick(SimpleHolder holder, Integer integer) {
//        showToast("点击了序号：" + holder.getAdapterPosition());
//    }
//
//    @Override
//    public Object onRequestData(int pageNo) {
//        return generateSimpleData();
//    }
//}