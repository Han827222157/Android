package com.iyoyogo.android.adapter;


import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iyoyogo.android.R;
import com.iyoyogo.android.bean.HisFansBean;
import com.iyoyogo.android.bean.collection.AttentionsBean;
import com.iyoyogo.android.ui.mine.homepage.CircleImageView;

import java.util.List;

/**
 * 获取 用户 关注的人群
 */
public class HisFansAdapter extends BaseQuickAdapter<HisFansBean.DataBean.ListBean, BaseViewHolder> {


    public HisFansAdapter(int layoutResId, @Nullable List<HisFansBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HisFansBean.DataBean.ListBean item) {
        helper.setText(R.id.user_nickname, item.getUser_nickname());
        helper.setText(R.id.count_yoj, item.getCount_yoj()+"");
        helper.setText(R.id.count_yox, item.getCount_yox()+"");
        Glide.with(mContext).load(item.getUser_logo()).into((CircleImageView) helper.getView(R.id.user_logo));
        TextView btu_guanzhu = helper.getView(R.id.tv_guanzhu);
        int status = item.getStatus();
        if (status == 0) {//未关注
            btu_guanzhu.setBackgroundResource(R.drawable.bg_collection);
            btu_guanzhu.setText("+关注");
            btu_guanzhu.setTextColor(Color.parseColor("#ffffff"));
        }
        if (status == 1) {//关注
            btu_guanzhu.setBackgroundResource(R.drawable.bg_delete_yoji);
            btu_guanzhu.setText("关注");
            btu_guanzhu.setTextColor(Color.parseColor("#888888"));
        }
        if (status == 2) {//互相关注
            btu_guanzhu.setBackgroundResource(R.drawable.bg_delete_yoji);
            btu_guanzhu.setText("互相关注");
            btu_guanzhu.setTextColor(Color.parseColor("#888888"));
        }
    }
}
