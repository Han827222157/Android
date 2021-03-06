package com.iyoyogo.android.contract;

import com.iyoyogo.android.base.IBasePresenter;
import com.iyoyogo.android.base.IBaseView;
import com.iyoyogo.android.bean.BaseBean;

public interface FeedBackContract {
    interface View extends IBaseView{
        void addFeedBackSuccess(BaseBean baseBean);
    }
    interface Presenter extends IBasePresenter{
        void addFeedBack(String user_id,String user_token,String desc);
    }
}
