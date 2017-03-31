package com.harreke.easyapp.demo.activity;

import android.content.Intent;
import android.text.Html;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.harreke.easyapp.app.activity.ActivityFramework;
import com.harreke.easyapp.demo.R;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectView;
import com.harreke.easyapp.util.IntentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 由 Harreke 于 2016/1/11 创建
 */
@InjectLayout
@InjectToolbar
public class MainActivity extends ActivityFramework {
    @InjectView
    ListView list_view;
    private List<Class> mClazzList = new ArrayList<>();
    //    private SimpleTextRecyclerFramework mSimpleTextRecycler;
    private List<String> mNameList = new ArrayList<>();
    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mClazzList.size();
        }

        @Override
        public Object getItem(int position) {
            return mClazzList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_simple, parent, false);
            }
            ((TextView) convertView).setText(Html.fromHtml(mNameList.get(position)));
            return convertView;
        }
    };

    @Override
    public void argument(Intent intent) {
        Pair<String, Class> pair;

        pair = new Pair<>("<b><font color='#ff0000'>" + getString(R.string.activity_framework) + "</font></b>", null);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_toolbar), ToolbarActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_toast), ToastActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_fragment_data), FragmentDataActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_connection_change), ConnectionChangeActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_swipe_to_finish), SwipeToFinishActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<>("<b><font color='#ff0000'>" + getString(R.string.activity_data) + "</font></b>", null);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_loader), LoaderActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        //        pair = new Pair<String, Class>(getString(R.string.activity_recycler), RecyclerActivity.class);
        //        mNameList.add(pair.first);
        //        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_viewpager), ViewPagerActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_fragment_pager), FragmentPagerActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<>("<b><font color='#ff0000'>" + getString(R.string.activity_helper) + "</font></b>", null);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_compound_button), CompoundButtonActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_gesture), GestureActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_palette), PaletteActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_view_switch), ViewSwitchActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<>("<b><font color='#ff0000'>" + getString(R.string.activity_widget) + "</font></b>", null);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_view_animator), ViewAnimatorActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_auto_fit_view), AutoFitViewActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_circular_progress), CircularProgressActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_controller_layout), ControllerLayoutActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_ratio_view), RatioViewActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_sizable_drawable_view), SizableDrawableViewActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_grid_image_view), GridImageViewActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_soft_input_edit_text), SoftInputEditTextActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<>("<b><font color='#ff0000'>" + getString(R.string.activity_util) + "</font></b>", null);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_utils), UtilActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);

        pair = new Pair<String, Class>(getString(R.string.activity_injection), InjectionActivity.class);
        mNameList.add(pair.first);
        mClazzList.add(pair.second);
    }

    @Override
    public void config() {
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class clazz = mClazzList.get(position);

                if (clazz != null) {
                    start(IntentUtil.create(getContext(), mClazzList.get(position)));
                }
            }
        });
        list_view.setAdapter(mAdapter);
        //        mSimpleTextRecycler = new SimpleTextRecyclerFramework(this) {
        //            @Override
        //            public void onBindItem(SimpleTextHolder holder, String s) {
        //            }
        //
        //            @Override
        //            public void onItemViewClick(SimpleTextHolder holder, String s) {
        //                int position = holder.getAdapterPosition();
        //                Class clazz = mClazzList.get(position);
        //
        //                if (clazz != null) {
        //                    start(IntentUtil.create(getContext(), mClazzList.get(position)));
        //                }
        //            }
        //        };
    }

    @Override
    public void launch() {
        mAdapter.notifyDataSetChanged();
        //        mSimpleTextRecycler.from(mNameList);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}