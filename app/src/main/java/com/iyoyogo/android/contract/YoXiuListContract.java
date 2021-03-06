package com.iyoyogo.android.contract;

import com.iyoyogo.android.base.IBasePresenter;
import com.iyoyogo.android.base.IBaseView;
import com.iyoyogo.android.bean.yoxiu.YouXiuListBean;

public interface YoXiuListContract {
    interface View extends IBaseView{
        void getYoXiuListSuccess(YouXiuListBean.DataBean data);
        void loadMoreYoXiuListSuccess(YouXiuListBean.DataBean data);
    }
    interface Presenter extends IBasePresenter{
        void getYoXiuList(String user_id,String user_token,int page);
        void loadMoreYoXiuList(String user_id,String user_token,int page);
    }
}
