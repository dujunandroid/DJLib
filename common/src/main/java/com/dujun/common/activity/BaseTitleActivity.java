package com.dujun.common.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dujun.common.R;
import com.dujun.common.widgets.CommonToolbar;
import com.dujun.core.basemvp.BaseActivity;

/**
 * @author dujun
 * Created on 2019-09-02
 */
public abstract class BaseTitleActivity extends BaseActivity {
    private CommonToolbar toolbar;

    @Override
    protected void initBaseView(Bundle bundle) {
        LinearLayout rootView = findViewById(R.id.root_view);
        View view = LayoutInflater.from(this).inflate(getLayoutId(), null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.addView(view);
        toolbar = findViewById(R.id.toolbar);
        toolbar.leftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initView(bundle);
    }

    @Override
    protected int getBaseLayoutId() {
        return R.layout.layout_title_activity;
    }

    public void setCommonTitle(CharSequence title) {
        toolbar.setTitle(title);
    }

    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
    }

    public CommonToolbar getToolbar() {
        return toolbar;
    }

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化页面
     *
     * @param bundle
     */
    protected abstract void initView(Bundle bundle);
}
