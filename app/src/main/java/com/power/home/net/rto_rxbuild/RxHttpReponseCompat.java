package com.power.home.net.rto_rxbuild;


import com.power.home.common.util.LogUtil;
import com.power.home.data.Optional;
import com.power.home.data.bean.BaseBean;
import com.power.home.net.rto_exception.ApiException;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZHP on 2017/6/25.
 */
public class RxHttpReponseCompat {


    public static <T> ObservableTransformer<BaseBean<T>, T> compatResult() {

        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> baseBeanObservable) {

                return baseBeanObservable.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseBean<T> tBaseBean) throws Exception {
                        LogUtil.e("api", tBaseBean.getCode() + "------------" + tBaseBean.getMsg());
                        if (tBaseBean.success()) {
                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> subscriber) throws Exception {

                                    try {
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onComplete();
                                    } catch (Exception e) {
                                        ApiException a = (ApiException) e;
                                        a.setCode(tBaseBean.getCode());
                                        a.setDisplayMessage(tBaseBean.getMsg());
                                        subscriber.onError(a);
                                    }
                                }
                            });
                        } else {
                            LogUtil.e("api", tBaseBean.getCode() + "------------" + tBaseBean.getMsg());
                            return Observable.error(new ApiException(tBaseBean.getCode(), tBaseBean.getMsg()));
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };

    }

    public static <T> ObservableTransformer<BaseBean<T>, Optional<T>> compatResult2() {

        return new ObservableTransformer<BaseBean<T>, Optional<T>>() {
            @Override
            public ObservableSource<Optional<T>> apply(Observable<BaseBean<T>> baseBeanObservable) {

                return baseBeanObservable.flatMap(new Function<BaseBean<T>, ObservableSource<Optional<T>>>() {
                    @Override
                    public ObservableSource<Optional<T>> apply(@NonNull final BaseBean<T> tBaseBean) throws Exception {
                        LogUtil.e("api", tBaseBean.getCode() + "------------" + tBaseBean.getMsg());
                        if (tBaseBean.success()) {
                            return Observable.create(new ObservableOnSubscribe<Optional<T>>() {
                                @Override
                                public void subscribe(ObservableEmitter<Optional<T>> subscriber) throws Exception {
                                    try {
                                        subscriber.onNext(tBaseBean.transform());
                                        subscriber.onComplete();
                                    } catch (Exception e) {
                                        ApiException a = (ApiException) e;
                                        a.setCode(tBaseBean.getCode());
                                        a.setDisplayMessage(tBaseBean.getMsg());
                                        subscriber.onError(a);
                                    }
                                }
                            });
                        } else {
                            LogUtil.e("api", tBaseBean.getCode() + "------------" + tBaseBean.getMsg());
                            return Observable.error(new ApiException(tBaseBean.getCode(), tBaseBean.getMsg()));
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };

    }

    /*private static <T> Observable<Optional<T>> createHttpData(Optional<T> t) {

        return Observable.create(e -> {
            try {
                e.onNext(t);
                e.onComplete();
            } catch (Exception exc) {
                e.onError(exc);
            }
        });
    }*/
}
