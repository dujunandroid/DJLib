package com.dujun.core.basemvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author dujun
 * Created on 2019-09-23
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    protected P mPresenter;

    protected abstract P createPresenter();
    protected Context mContext;

    private CompositeDisposable compositeDisposable;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        compositeDisposable = new CompositeDisposable();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView( this);
        }
        View view = inflater.inflate(getLayoutId(), null, false);
        init(view);
        return view;
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroyView() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    protected abstract void init(View view);
    protected abstract int getLayoutId();

}
