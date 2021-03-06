package com.iyoyogo.android.contract;

import com.iyoyogo.android.base.IBasePresenter;
import com.iyoyogo.android.base.IBaseView;
import com.iyoyogo.android.bean.mine.message.MessageCenterBean;

public interface MessageCenterContract {
    interface View extends IBaseView{
        void getMessageCenterSuccess(MessageCenterBean.DataBean data);
    }
    interface Presenter extends IBasePresenter{
        void getMessageCenter(String user_id,String user_token);
    }
}
