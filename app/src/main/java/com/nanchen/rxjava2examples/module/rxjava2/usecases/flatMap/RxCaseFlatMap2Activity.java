package com.nanchen.rxjava2examples.module.rxjava2.usecases.flatMap;

import android.util.Log;

import com.nanchen.rxjava2examples.model.ArticleDetail;
import com.nanchen.rxjava2examples.model.ArticleList;
import com.nanchen.rxjava2examples.model.FoodDetail;
import com.nanchen.rxjava2examples.model.FoodList;
import com.nanchen.rxjava2examples.module.rxjava2.operators.item.RxOperatorBaseActivity;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * flatMap 使用场景
 * <p>
 * 多个网络请求依次依赖，比如：
 * 1、注册用户前先通过接口A获取当前用户是否已注册，再通过接口B注册;
 * 2、注册后自动登录，先通过注册接口注册用户信息，注册成功后马上调用登录接口进行自动登录。
 * <p>
 * 例子所用API来自天狗网
 * <p>
 * <p>
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-07-03  8:58
 */

public class RxCaseFlatMap2Activity extends RxOperatorBaseActivity {

    private static final String TAG = "RxCaseFlatMapActivity";


    /**
     * 干货集中营 APi
     * 获取干货分类下 Android 子分类的 10 个随机文章列表
     */
    private  String ARTICT_LIST_URL = "https://gank.io/api/v2/random/category/GanHuo/type/Android/count/10";
    /**
     * 干货集中营 APi
     * 文章详情 API
     * https://gank.io/api/v2/post/<post_id>
     * 请求方式: GET
     * 注:
     * post_id 可接受参数 文章 id [分类数据 API 返回的_id 字段]
     */
    private  String ARTICT_DETAIL_URL = "https://gank.io/api/v2/post/";


    @Override
    protected String getSubTitle() {
        return "多个网络请求依次依赖";
    }

    /**
     * 该例子采用天狗网的健康食品API：http://www.tngou.net/doc/food
     */
    @Override
    protected void doSomething() {
        Disposable disposable = Rx2AndroidNetworking.get(ARTICT_LIST_URL)
                .build()
                .getObjectObservable(ArticleList.class)// 发起获取食品列表的请求，并解析到Bean
                .subscribeOn(Schedulers.io())  // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取食品列表的请求结果
                .doOnNext(new Consumer<ArticleList>() {
                    @Override
                    public void accept(@NonNull ArticleList articleList) throws Exception {
                        // 先根据获取食品列表的响应结果做一些操作
                        Log.e(TAG, "accept: doOnNext :" + articleList.toString());
                        mRxOperatorsText.append("accept: doOnNext : 第一个类别ID为:" + articleList.data.get(0)._id + "\t描述为：" + articleList.data.get(0).desc + "\n");
                    }
                })
                .observeOn(Schedulers.io()) // 回到 io 线程去处理获取食品详情的请求
                .flatMap(new Function<ArticleList, ObservableSource<ArticleDetail>>() {
                    @NonNull
                    @Override
                    public ObservableSource<ArticleDetail> apply(@NonNull ArticleList articleList) throws Exception {
                       return  Rx2AndroidNetworking.get(ARTICT_DETAIL_URL + articleList.data.get(0)._id)
//                               .addQueryParameter(articleList.data.get(0)._id)
                               .build()
                               .getObjectObservable(ArticleDetail.class);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleDetail>() {
                    @Override
                    public void accept(@NonNull ArticleDetail articleDetail) throws Exception {
                        Log.e(TAG, "accept: success ：" + articleDetail.toString());
                        mRxOperatorsText.append("accept: success ：" + articleDetail.toString() + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: error :" + throwable.getMessage());
                        mRxOperatorsText.append("accept: error :" + throwable.getMessage() + "\n");
                    }
                });
    }
}
