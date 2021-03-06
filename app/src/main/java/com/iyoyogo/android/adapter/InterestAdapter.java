package com.iyoyogo.android.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iyoyogo.android.R;
import com.iyoyogo.android.bean.login.interest.InterestBean;
import com.iyoyogo.android.utils.imagepicker.component.BaseRecyclerAdapter;
import com.iyoyogo.android.utils.imagepicker.component.BaseViewHolder;
import com.iyoyogo.android.utils.imagepicker.component.OnItemChooseCallback;
import com.iyoyogo.android.utils.imagepicker.component.OnRecyclerViewItemClickListener;
import com.iyoyogo.android.utils.imagepicker.component.OnRecyclerViewItemLongClickListener;

import java.util.List;

public class InterestAdapter  extends BaseRecyclerAdapter {

    private int count = 0;
    private int maxNum = 1;
    private Context context;
    private List<InterestBean.DataBean.ListBean> list;

    public InterestAdapter(Context context, List<InterestBean.DataBean.ListBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classify,null);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setMaxNum(int maxNum) {
        if (maxNum < 1) return;
        this.maxNum = maxNum;
    }

    private class MyViewHolder extends BaseViewHolder {
        private ImageView mImageSrc;
        private ImageView mImageChoose;
        private TextView tag_name;

        private MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void intOnItemChooseCallback(final OnItemChooseCallback chooseCallback, final int position) {
            mImageChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (count < maxNum){
                        if (!list.get(position).isChoose()){
                            mImageChoose.setImageResource(R.mipmap.zp_xz);
                            list.get(position).setChoose(true);
                            chooseCallback.chooseState(position,true);
                            count ++;
                        } else {
                            mImageChoose.setImageResource(R.mipmap.log_unselect);
                            list.get(position).setChoose(false);
                            chooseCallback.chooseState(position,false);
                            count--;
                        }

                    } else { //count >= maxNum
                        if (!list.get(position).isChoose()){
                            chooseCallback.countWarning(count);
                        } else {
                            mImageChoose.setImageResource(R.mipmap.log_unselect);
                            list.get(position).setChoose(false);
                            chooseCallback.chooseState(position,false);
                            count--;
                        }
                    }
                    chooseCallback.countNow(count);
                }
            });
        }

        @Override
        public void initOnItemClickListener(final OnRecyclerViewItemClickListener listener, final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }

        @Override
        public void iniOnItemLongClickListener(OnRecyclerViewItemLongClickListener longClickListener, int position) {

        }


        @Override
        protected void findViewById(View itemView) {
            mImageSrc = itemView.findViewById(R.id.img);
            mImageChoose = itemView.findViewById(R.id.choice_icon);
            tag_name = itemView.findViewById(R.id.tag_name);
        }

        @Override
        public void onBind(int position) {
            if (list.get(position).isChoose()){
                mImageChoose.setImageResource(R.mipmap.zp_xz);
            } else {
                mImageChoose.setImageResource(R.mipmap.log_unselect);
            }
            Glide.with(context)
                    .load(list.get(position).getLogo())
                    .into(mImageSrc);
            tag_name.setText(list.get(position).getInterest());
        }
    }

}
