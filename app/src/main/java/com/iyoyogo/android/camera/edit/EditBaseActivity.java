package com.iyoyogo.android.camera.edit;


import android.os.Bundle;

import com.iyoyogo.android.R;
import com.iyoyogo.android.base.BaseActivity;
import com.iyoyogo.android.camera.VideoFragment;
import com.iyoyogo.android.camera.utils.AppManager;
import com.iyoyogo.android.camera.utils.TimelineUtil;
import com.iyoyogo.android.camera.utils.dataInfo.TimelineData;
import com.meicam.sdk.NvsStreamingContext;
import com.meicam.sdk.NvsTimeline;

/**
 * Created by CaoZhiChao on 2018/9/3 19:48
 */
public abstract class EditBaseActivity extends BaseActivity {
    private final String TAG = "EditBaseActivity";
    public NvsStreamingContext nvsStreamingContext;
    public NvsTimeline nvsTimeline;
    public VideoFragment videoFragment;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        nvsStreamingContext = NvsStreamingContext.getInstance();
        nvsTimeline = TimelineUtil.createTimeline();
        super.onCreate(saveInstanceState);
        nvsTimeline.deleteWatermark();
    }

    @Override
    public void onBackPressed() {
        finishActivity();
    }
    public void finishActivity(){
        removeTimeline();
        AppManager.getInstance().finishActivity();
    }

    private void removeTimeline() {
        TimelineUtil.removeTimeline(nvsTimeline);
        nvsTimeline = null;
    }

    public void initVideoFragment(int layoutId) {
        if (nvsTimeline == null)
            return;
        videoFragment = new VideoFragment();
        videoFragment.setAutoPlay(true);
        videoFragment.setFragmentLoadFinisedListener(new VideoFragment.OnFragmentLoadFinisedListener() {
            @Override
            public void onLoadFinished() {
                videoFragment.seekTimeline(nvsStreamingContext.getTimelineCurrentPosition(nvsTimeline), 0);
            }
        });
        videoFragment.setTimeline(nvsTimeline);
        Bundle m_bundle = new Bundle();
        m_bundle.putInt("titleHeight", (int) getResources().getDimension(R.dimen.edit_titleBar_height));
        m_bundle.putInt("bottomHeight", (int) getResources().getDimension(R.dimen.edit_bottom_layout_height));
        m_bundle.putBoolean("playBarVisible", true);
        m_bundle.putInt("ratio", TimelineData.instance().getMakeRatio());
        videoFragment.setArguments(m_bundle);
        getSupportFragmentManager().beginTransaction()
                .add(layoutId, videoFragment)
                .commit();
        getSupportFragmentManager().beginTransaction().show(videoFragment);
    }
}
