package com.nanchen.rxjava2examples.module.rxjava2.usecases.fastNetwork;

import android.util.Log;

import com.nanchen.rxjava2examples.constant.GlobalConfig;
import com.nanchen.rxjava2examples.model.MobileAddress;
import com.nanchen.rxjava2examples.model.MobileAddress.ResultBean;
import com.nanchen.rxjava2examples.module.rxjava2.operators.item.RxOperatorBaseActivity;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Rx2AndroidNetworking
 *
 * 仅仅是采用这个框架做 rx2 网络请求
 *
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-30  15:41
 */

public class RxNetwork2Activity extends RxOperatorBaseActivity {

    private static final String TAG = "RxNetworkActivity";

    String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=" + GlobalConfig.KEY;

    @Override
    protected String getSubTitle() {
        return "使用Rx2-Networking";
    }

    @Override
    protected void doSomething() {
        mRxOperatorsText.append("RxNetworkActivity\n");
        Disposable disposable = Rx2AndroidNetworking.get(url)
                .build()
                .getObjectObservable(MobileAddress.class)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(@NonNull MobileAddress data) throws Exception {
                        Log.e(TAG, "doOnNext:" + Thread.currentThread().getName() + "\n");
                        mRxOperatorsText.append("\ndoOnNext:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "doOnNext:" + data.toString() + "\n");
                        mRxOperatorsText.append("doOnNext:" + data.toString() + "\n");
                    }
                })
                .map(new Function<MobileAddress, ResultBean>() {
                    @NonNull
                    @Override
                    public ResultBean apply(@NonNull MobileAddress mobileAddress) throws Exception {
                        Log.e(TAG, "map:" + Thread.currentThread().getName() + "\n");
                        mRxOperatorsText.append("\nmap:" + Thread.currentThread().getName() + "\n");
                        return mobileAddress.getResult();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultBean>() {
                    @Override
                    public void accept(@NonNull ResultBean resultBean) throws Exception {
                        Log.e(TAG, "subscribe 成功:" + Thread.currentThread().getName() + "\n");
                        mRxOperatorsText.append("\nsubscribe 成功:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "成功:" + resultBean.toString() + "\n");
                        mRxOperatorsText.append("成功:" + resultBean.toString() + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 失败:" + Thread.currentThread().getName() + "\n");
                        mRxOperatorsText.append("\nsubscribe 失败:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                        mRxOperatorsText.append("失败：" + throwable.getMessage() + "\n");
                    }
                });

    }
}
