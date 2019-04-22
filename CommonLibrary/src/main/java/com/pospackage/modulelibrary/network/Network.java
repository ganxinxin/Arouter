package com.pospackage.modulelibrary.network;

import com.pospackage.modulelibrary.app.AppUrl;
import com.pospackage.modulelibrary.bean.ResultBean;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Network {

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static Retrofit retrofit;

    public static Retrofit create() {
        if (retrofit == null) {
            retrofit = create(AppUrl.BASE_URL);
        }
        return retrofit;
    }

    public static Retrofit create(String baseUrl) {
        return retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    public static Observable<ResultBean> getObservable(String jsonParam, final HttpApi mHttpApi) {
        return Observable.just(jsonParam).map(new Func1<String, String>() {

            @Override
            public String call(String s) {
                // TODO 加密请求参数
                return s;
            }
        }).flatMap(new Func1<String, Observable<ResultBean>>() {

            @Override
            public Observable<ResultBean> call(String s) {
                return mHttpApi.call(s);
            }
        }).map(new Func1<ResultBean, ResultBean>() {

            @Override
            public ResultBean call(ResultBean bean) {
                if ("1".equals(bean.getResCode()) && bean.getData() != null) {
                    // TODO 解密返回数据
                    bean.setJsonData(bean.getData().toString());
                }
                return bean;
            }
        }).subscribeOn(Schedulers.io());
    }
}
