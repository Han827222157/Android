package com.iyoyogo.android.contract;

import com.iyoyogo.android.base.IBasePresenter;
import com.iyoyogo.android.base.IBaseView;
import com.iyoyogo.android.bean.mine.center.YoXiuContentBean;

public interface YoXiuContentContract {
    interface View extends IBaseView{
        void getYoXiuContentSuccess(YoXiuContentBean.DataBean data);
    }
    interface Presenter extends IBasePresenter{
        void getYoXiuContent(String user_id, String user_token, String his_id, String page, String page_size);
    }
}
