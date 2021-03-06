package com.iyoyogo.android.contract;

import com.iyoyogo.android.base.IBasePresenter;
import com.iyoyogo.android.base.IBaseView;
import com.iyoyogo.android.bean.mine.center.YoJiContentBean;

public interface YoJiContentContract {
    interface View extends IBaseView{
        void getYoJiContentSuccess(YoJiContentBean.DataBean data);
    }
    interface Presenter extends IBasePresenter{
        void getYoJiContent(String user_id,String user_token,String his_id,String page,String page_size);
    }
}
