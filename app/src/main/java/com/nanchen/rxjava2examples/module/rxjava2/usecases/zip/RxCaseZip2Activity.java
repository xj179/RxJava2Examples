package com.nanchen.rxjava2examples.module.rxjava2.usecases.zip;

import android.util.Log;

import com.nanchen.rxjava2examples.constant.GlobalConfig;
import com.nanchen.rxjava2examples.model.CategoryResult;
import com.nanchen.rxjava2examples.model.CategoryResult2;
import com.nanchen.rxjava2examples.model.MobileAddress;
import com.nanchen.rxjava2examples.module.rxjava2.operators.item.RxOperatorBaseActivity;
import com.nanchen.rxjava2examples.net.Network;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * zip 操作符的使用场景
 * <p>
 * 结合多个接口的数据再更新 UI
 * <p>
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-30  17:24
 */

public class RxCaseZip2Activity extends RxOperatorBaseActivity {
    private static final String TAG = "RxCaseZipActivity";

    String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=" + GlobalConfig.KEY;

    @Override
    protected String getSubTitle() {
        return "zip 操作符使用场景";
    }

    @Override
    protected void doSomething() {
        Observable<MobileAddress> mobileAddressObservable = Rx2AndroidNetworking.get(url)
                .build()
                .getObjectObservable(MobileAddress.class);

        Observable<CategoryResult2>  categoryResultObservable= Network.getGankApi().getCategoryData2("Android", 1, 10);

        Disposable disposable = Observable.zip(mobileAddressObservable, categoryResultObservable, new BiFunction<MobileAddress, CategoryResult2, String>() {
            @NonNull
            @Override
            public String apply(@NonNull MobileAddress mobileAddress, @NonNull CategoryResult2 categoryResult2) throws Exception {
                Log.i(TAG, "zip 线程:" + Thread.currentThread().getName() + "\n");
                return "合并后的数据为：手机归属地：" + mobileAddress.getResult().getMobilearea() + "\t干货集中营API人名：" + categoryResult2.data.get(0).author;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        mRxOperatorsText.append("\n Observer 线程:" + Thread.currentThread().getName() + "\n");
                        mRxOperatorsText.append("成功：" + s);
                        Log.e(TAG, "accept: 成功：" + s + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: 失败：" + throwable + "\n");
                    }
                });
    }
}
