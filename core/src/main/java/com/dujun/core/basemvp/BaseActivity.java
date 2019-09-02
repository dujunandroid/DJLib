package com.dujun.core.basemvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dujun
 * Created on 2019-09-02
 */
public abstract class BaseActivity<P extends BasePresenter<IBaseView>> extends AppCompatActivity implements IBaseView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        setContentView(getLayoutId());
        initView(savedInstanceState);
    }

    /**
     * 创建presenter
     *
     * @return
     */
    @Nullable
    protected abstract P createPresenter();

    /**
     * 页面初始化
     *
     * @param bundle
     */
    protected abstract void initView(Bundle bundle);

    /**
     * 布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
