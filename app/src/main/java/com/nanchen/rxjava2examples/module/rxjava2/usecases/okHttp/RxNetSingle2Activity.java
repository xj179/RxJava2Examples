package com.nanchen.rxjava2examples.module.rxjava2.usecases.okHttp;

import android.util.Log;

import com.google.gson.Gson;
import com.nanchen.rxjava2examples.constant.GlobalConfig;
import com.nanchen.rxjava2examples.model.MobileAddress;
import com.nanchen.rxjava2examples.module.rxjava2.operators.item.RxOperatorBaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 * 采用 OkHttp3 配合 map , doOnNext , 线程切换做简单的网络请求
 *
 * 1、通过 Observable.create() 方法，调用 OkHttp 网络请求;
 * 2、通过 map 操作符结合 Gson , 将 Response 转换为 bean 类;
 * 3、通过 doOnNext() 方法，解析 bean 中的数据，并进行数据库存储等操作;
 * 4、调度线程，在子线程进行耗时操作任务，在主线程更新 UI;
 * 5、通过 subscribe(),根据请求成功或者失败来更新 UI。
 *
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-30  14:24
 */

public class RxNetSingle2Activity extends RxOperatorBaseActivity {
    private static final String TAG = "RxNetSingleActivity";

    String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=" + GlobalConfig.KEY;

    @Override
    protected String getSubTitle() {
        return "单一的网络请求";
    }

    @CheckReturnValue
    @Override
    protected void doSomething() {

        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {
                Log.i(TAG, "obserable subscribe...");
                Builder builder = new Builder().url(url).get();
                Request request = builder.build();
                Call call = new OkHttpClient().newCall(request);

                Response response = call.execute();
                e.onNext(response);
            }
        }).map(new Function<Response, MobileAddress>() {

            @NonNull
            @Override
            public MobileAddress apply(@NonNull Response response) throws Exception {
                Log.i(TAG, "map 线程：" + Thread.currentThread().getName() + "\n");
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                       /** 使用 okhttp3 的时候遇到的异常：java.lang.IllegalStateException: closed。
                        原因是流已经关闭，所以无法再进行操作。
                        问题出现在我在 callback 中调用了两次 response.body ().string ()，导致了错误的出现！
                        OkHttp 请求回调中 response.body ().string () 只能有效调用一次，调用 response.body ().string () 的时候数据流已经关闭了，再次调用就是提示已经 closed。然后我把代码修改完之后就可以了，只调用一次 response.body().string()；*/
//                        Log.i(TAG, "response body:" + body.string());
                        Log.i(TAG, "response body:" +  response.body());

                        MobileAddress address = new Gson().fromJson(body.string(), MobileAddress.class);
//                        Log.i(TAG, "apply address: " + address.toString() );
                        return  address;
                    }
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<MobileAddress>() {
            @Override
            public void accept(@NonNull MobileAddress mobileAddress) throws Exception {
                Log.i(TAG, "doOnNext 线程:" + Thread.currentThread().getName() + "\n");
                mRxOperatorsText.append("\n doOnNext 线程:" + Thread.currentThread().getName() + "\n");
                Log.i(TAG, "doOnNext mobileAddress: " + mobileAddress.toString());
            }
        }).subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         /*.subscribe(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(@NonNull MobileAddress mobileAddress) throws Exception {
                        Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
                        mRxOperatorsText.append("\nsubscribe 线程:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "成功:" + mobileAddress.toString() + "\n");
                        mRxOperatorsText.append("成功:" + mobileAddress.toString() + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
                        mRxOperatorsText.append("\nsubscribe 线程:" + Thread.currentThread().getName() + "\n");

                        Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                        mRxOperatorsText.append("失败：" + throwable.getMessage() + "\n");
                    }
                });*/
        .subscribe(new Observer<MobileAddress>() {
            Disposable disposable ;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "Observer onSubscribe....");
                disposable = d ;
            }

            @Override
            public void onNext(@NonNull MobileAddress mobileAddress) {
                Log.i(TAG, "Observer线程:" + Thread.currentThread().getName() + "\n");
                mRxOperatorsText.append("\n Observer 线程:" + Thread.currentThread().getName() + "\n");
                mRxOperatorsText.append("成功：" + mobileAddress.toString());

                disposable.dispose();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "donError:" + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Observer onComplete....");
                if (!disposable.isDisposed()) {
                    disposable.dispose();
                }
            }
        });

    }

}
