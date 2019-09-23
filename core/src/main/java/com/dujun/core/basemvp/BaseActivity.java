package com.dujun.core.basemvp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author dujun
 * Created on 2019-09-02
 */
public abstract class BaseActivity<P extends BasePresenter> extends FragmentActivity implements IBaseView {
    protected P mPresenter;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInit();
        compositeDisposable = new CompositeDisposable();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        setContentView(getBaseLayoutId());
        initBaseView(savedInstanceState);
    }

    protected void beforeInit() {
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
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
    protected abstract void initBaseView(Bundle bundle);

    /**
     * 布局id
     *
     * @return
     */
    protected abstract int getBaseLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected int getEnterAnim() {
        return 0;
    }

    protected int getExitAnim() {
        return 0;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(getEnterAnim(), getExitAnim());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(getEnterAnim(), getExitAnim());
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(getEnterAnim(), getExitAnim());
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(getEnterAnim(), getExitAnim());
    }
}
