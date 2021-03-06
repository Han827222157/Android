package com.iyoyogo.android.ui.home.attention;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.iyoyogo.android.R;
import com.iyoyogo.android.adapter.HomeRecyclerViewAdapter;
import com.iyoyogo.android.base.BaseFragment;
import com.iyoyogo.android.bean.home.HomeBean;
import com.iyoyogo.android.contract.HomeContract;
import com.iyoyogo.android.presenter.HomePresenter;
import com.iyoyogo.android.utils.SpUtils;
import com.iyoyogo.android.utils.refreshheader.MyRefreshAnimHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {
    @BindView(R.id.recycler_recommend)
    RecyclerView recyclerHome;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private String user_id;
    private String user_token;


    @Override
    protected void initView() {
        super.initView();
        MyRefreshAnimHeader mRefreshAnimHeader = new MyRefreshAnimHeader(getContext());
        setHeader(mRefreshAnimHeader);
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        //下拉刷新
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setFooterHeight(1.0f);
        refreshLayout.autoRefresh();
        refreshLayout.finishRefresh(1050);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1050);
                mPresenter.banner(user_id, user_token, "attention");
            }
        });
    }

    private void setHeader(RefreshHeader header) {
        refreshLayout.setRefreshHeader(header);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attention;
    }

    @Override
    protected HomeContract.Presenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        user_id = SpUtils.getString(getContext(), "user_id", null);
        user_token = SpUtils.getString(getContext(), "user_token", null);
        mPresenter.banner(user_id, user_token, "attention");
    }

    @Override
    public void bannerSuccess(HomeBean.DataBean data) {
        List<HomeBean.DataBean> mList = new ArrayList<>();
        mList.add(data);
        Log.d("HomeFragment", "mList.size():" + mList.size());
        HomeRecyclerViewAdapter homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(getContext(), mList);
        Log.d("HomeFragment", "mList.size():" + mList.size());
        recyclerHome.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerHome.setAdapter(homeRecyclerViewAdapter);
       homeRecyclerViewAdapter.onRetryClickListener(new HomeRecyclerViewAdapter.OnRetryConnection() {
           @Override
           public void on_retry() {
               mPresenter.banner(user_id,user_token,"attention");
           }
       });
       homeRecyclerViewAdapter.onItemRetryOnClickListener(new HomeRecyclerViewAdapter.OnRetryClickListener() {
           @Override
           public void onretry() {
               mPresenter.banner(user_id,user_token,"attention");
           }
       });
    }

}