package com.dujun.common.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.dujun.common.R;
import com.dujun.core.application.DJApplication;
import com.dujun.core.basemvp.BasePresenter;
import com.dujun.core.basemvp.IBaseView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * @author dujun
 * Created on 2019-09-02
 */
public class CrashInfoActivity extends BaseTitleActivity {

    @Nullable
    @Override
    protected BasePresenter<IBaseView> createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_crash_info;
    }

    @Override
    protected void initView(Bundle bundle) {
        setCommonTitle("错误信息");
        ((AppCompatTextView)findViewById(R.id.info)).setText(getIntent().getExtras().getString("info"));
    }

    public static Intent getIntent(String info) {
        return new Intent(DJApplication.getContext(), CrashInfoActivity.class).setFlags(FLAG_ACTIVITY_NEW_TASK).putExtra("info", info);
    }
}
