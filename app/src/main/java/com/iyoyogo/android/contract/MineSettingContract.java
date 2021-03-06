package com.iyoyogo.android.contract;

import com.iyoyogo.android.base.IBasePresenter;
import com.iyoyogo.android.base.IBaseView;
import com.iyoyogo.android.bean.BaseBean;
import com.iyoyogo.android.bean.mine.setting.MineSettingBean;

public interface MineSettingContract {
    interface View extends IBaseView {
        void logoutSuccess(BaseBean baseBean);

        void getMineSettingSuccess(MineSettingBean.DataBean data);

        void setMineSettingSuccess(BaseBean baseBean);
    }

    interface Presenter extends IBasePresenter {
        void logout(String user_id, String user_token, String addr, String phone_info, String app_version);

        void getMineSetting(String user_id, String user_token);

        void setMineSetting(String user_id, String user_token, int wifi_auto_play_video, int notice, int address_list);
    }
}
