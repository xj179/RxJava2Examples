package com.nanchen.rxjava2examples.module.rxjava2.usecases.debounce;

import android.os.Bundle;
import android.util.Log;

import com.jakewharton.rxbinding2.view.RxView;
import com.nanchen.rxjava2examples.model.ArticleList;
import com.nanchen.rxjava2examples.model.FoodList;
import com.nanchen.rxjava2examples.module.rxjava2.operators.item.RxOperatorBaseActivity;
import com.nanchen.rxjava2examples.util.TimeUtil;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * 减少频繁的网络请求  debounce
 *
 * debounce 操作符可以过滤掉发射频率过快的数据项
 * 设想情景：
 * 1、输入框数据变化时就要进行网络请求，这样会产生大量的网络请求。这时候可以通过debounce进行处理
 * 2、点击一次按钮就进行一次网络请求
 *
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-07-03  10:31
 */

public class RxCaseDebounce2Activity extends RxOperatorBaseActivity {

    private static final String TAG = "RxCaseDebounceActivity";

    /**
     * 干货集中营 APi
     * 获取干货分类下 Android 子分类的 10 个随机文章列表
     */
    private  String ARTICT_LIST_URL = "https://gank.io/api/v2/random/category/GanHuo/type/Android/count/10";

    @Override
    protected String getSubTitle() {
        return "减少频繁的网络请求";
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        RxView.clicks(mRxOperatorsBtn)
                .debounce(2, TimeUnit.SECONDS)  //过滤掉发射小于2秒的发射事件
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        clickBtn();
                    }
                });


    }


    private  void  clickBtn(){
        Disposable disposable = Rx2AndroidNetworking.get(ARTICT_LIST_URL)
                .build()
                .getObjectObservable(ArticleList.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleList>() {
                    @Override
                    public void accept(@NonNull ArticleList articleList) throws Exception {
                        Log.e(TAG, "Time:" + TimeUtil.getNowStrTime() + "\n");
                        Log.e(TAG, "accept: 获取数据成功:" + articleList.status+ "\n");
                        mRxOperatorsText.append("Time:" + TimeUtil.getNowStrTime() + "\n");
                        mRxOperatorsText.append("accept: 获取数据成功:" + articleList.status + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: 获取数据失败：" + throwable.getMessage() + "\n");
                        mRxOperatorsText.append("accept: 获取数据失败：" + throwable.getMessage() + "\n");
                    }
                });
    }

    @Override
    public void onViewClicked() {
        // 禁止响应点击操作
    }

    @Override
    protected void doSomething() {

    }
}
