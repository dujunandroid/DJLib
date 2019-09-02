package com.dujun.common.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.dujun.common.R;

/**
 * @author dujun
 * Created on 2019-09-02
 */
public class CommonToolbar extends FrameLayout {
    public CommonToolbar(Context context) {
        super(context);
        init(context);
    }

    public CommonToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommonToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.common_toolbar, this);
    }

    public CommonToolbar setTitle(CharSequence title) {
        ((TextView) findViewById(R.id.tv_title)).setText(title);
        return this;
    }

    public CommonToolbar setTitleColor(int colorResId) {
        ((TextView) findViewById(R.id.tv_title)).setTextColor(ContextCompat.getColor(getContext(),colorResId));
        return this;
    }


    public CommonToolbar leftClick(OnClickListener listener) {
        findViewById(R.id.layout_left).setOnClickListener(listener);
        return this;
    }

    public void setRightImage1(int drawableId){
        getRightLayout().setVisibility(View.VISIBLE);
        getRightImage1().setVisibility(View.VISIBLE);
        getRightImage1().setImageResource(drawableId);
    }

    public void setRightImage2(int drawableId){
        getRightLayout().setVisibility(View.VISIBLE);
        getRightImage2().setVisibility(View.VISIBLE);
        getRightImage2().setImageResource(drawableId);
    }

    public CommonToolbar rightImage1Click(OnClickListener listener) {
        findViewById(R.id.right_image_1).setOnClickListener(listener);
        return this;
    }

    public CommonToolbar rightImage2Click(OnClickListener listener) {
        findViewById(R.id.right_image_2).setOnClickListener(listener);
        return this;
    }

    public CommonToolbar rightTextClick(OnClickListener listener) {
        findViewById(R.id.right_text).setOnClickListener(listener);
        return this;
    }

    public AppCompatImageView getLeftImage() {
        return findViewById(R.id.left_image);
    }

    public AppCompatImageView getRightImage1() {
        return findViewById(R.id.right_image_1);
    }

    public AppCompatImageView getRightImage2() {
        return findViewById(R.id.right_image_2);
    }

    public TextView getTitle() {
        return findViewById(R.id.tv_title);
    }

    public TextView getRightText() {
        return findViewById(R.id.right_text);
    }

    public View getRightLayout() {
        return findViewById(R.id.layout_right);
    }

    public View getRootLayout() {
        return findViewById(R.id.layout_bar);
    }

}
