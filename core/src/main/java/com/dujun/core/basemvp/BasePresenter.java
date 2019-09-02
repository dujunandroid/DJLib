package com.dujun.core.basemvp;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.dujun.core.application.DJApplication;

/**
 * @author dujun
 * Created on 2019-09-02
 */
public class BasePresenter<V extends IBaseView> {
    protected V mView;

    public boolean isViewAttached() {
        return mView != null;
    }

    public void attachView(V mView) {
        this.mView = mView;
    }

    public void detachView() {
        this.mView = null;
    }

    public Context getContext() {
        if (mView instanceof Activity) {
            return (Activity) mView;
        } else if (mView instanceof Fragment) {
            return ((Fragment) mView).getContext();
        } else {
            return DJApplication.getContext();
        }
    }

}
