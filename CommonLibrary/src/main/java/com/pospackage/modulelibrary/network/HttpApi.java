package com.pospackage.modulelibrary.network;

import com.pospackage.modulelibrary.bean.ResultBean;
import rx.Observable;

public interface HttpApi {
    Observable<ResultBean> call(String params);
}
