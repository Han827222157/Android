package com.iyoyogo.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.iyoyogo.android.R;

import java.util.List;

public class YoJiDetailViewPagerAdapter extends PagerAdapter {
    //界面列表
    List<String> ids;
    private Context context;

    public YoJiDetailViewPagerAdapter(Context context, List<String> ids) {
        this.ids = ids;
        this.context = context;
    }


    @Override
    public int getCount() {
        if (ids != null) {
            return ids.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    //初始化每个条目要显示的内容
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_zuji_top_imge, null);
        ImageView image = view.findViewById(R.id.imageView);
        Glide.with(context).load(ids.get(position)).into(image);
        container.addView(view);
        return view;
    }

    //销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //移除条目
        container.removeView((View) object);
    }
}
