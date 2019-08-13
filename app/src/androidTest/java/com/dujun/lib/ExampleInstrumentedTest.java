package com.dujun.lib;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.dujun.core.retrofit.RetrofitUtil;
import com.dujun.lib.bean.BaseResult;
import com.dujun.lib.bean.ConfigInfo;
import com.dujun.lib.service.ApiService;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.dujun.lib", appContext.getPackageName());
    }

    @Test
    public void testRetrofit() {
//        final Context context = InstrumentationRegistry.getTargetContext();
        RetrofitUtil.init("http://test-micro.feishiapp.com/");

        RetrofitUtil.getService(ApiService.class).appStart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResult<ConfigInfo>>() {
                    @Override
                    public void onCompleted() {
                        assertTrue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        fail();
                    }

                    @Override
                    public void onNext(BaseResult<ConfigInfo> configInfoBaseResult) {
                    }
                });
    }
}
