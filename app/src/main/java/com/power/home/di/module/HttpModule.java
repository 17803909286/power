package com.power.home.di.module;

import com.power.home.common.Constant;
import com.power.home.common.util.ACache;
import com.power.home.common.util.DeviceUtils;
import com.power.home.common.util.LogUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.http.ApiService;
import com.power.home.net.rto_rxbuild.PersistentCookieStore;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZHP on 2017/6/Http
 */
@Module
public class
HttpModule {

    PersistentCookieStore persistentCookieStore;

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        persistentCookieStore = new PersistentCookieStore(UIUtil.getContext());

        CookieHandler cookieHandler = new CookieManager(persistentCookieStore,
                CookiePolicy.ACCEPT_ALL);

//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient.Builder builder = RetrofitUrlManager.getInstance()
                .with(new OkHttpClient.Builder());
        builder.addInterceptor(new LogInterceptor());
        OkHttpClient okHttpClient = builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = SharePreferencesUtils.getToken();
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("accept-language", "zh-CN")
                        .addHeader("Accept", "*/*")
                        .addHeader("client", "ANDROID")
                        .addHeader("version", UIUtil.getVersionName())
                        .addHeader("channel", UIUtil.getMetaDataValue("channelName"))
                        .addHeader("deviceToken", DeviceUtils.getAndroidID())
                        .addHeader("mobileType", DeviceUtils.getBuildBrand() + DeviceUtils.getModel())
                        .addHeader("uuid", DeviceUtils.getUniqueID())
                        .addHeader("mobileSystem", DeviceUtils.getSystemVersion())
                        .addHeader("Authorization", StringUtil.isNullString(token) ? "" : token)
                        .build();
                return chain.proceed(request);
            }
        })
                // 连接超时时间设置
                .connectTimeout(30, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .build();
        return okHttpClient;


    }

    public void clearCookie() {
        persistentCookieStore.removeAll();
    }

    public class LogInterceptor implements Interceptor {

        public String TAG = "okhttp";

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(chain.request());

            okhttp3.MediaType mediaType = response.body().contentType();
            Headers headers = response.networkResponse().request().headers();
            String content = response.body().string();
            LogUtil.i(TAG, "\n");
            LogUtil.i(TAG, "----------Start----------------");
            LogUtil.i(TAG, "| Request:" + request.toString());
            LogUtil.i(TAG, "| Request headers:" + headers.toString());
            LogUtil.i(TAG, "| Response:" + content);
            LogUtil.i(TAG, "----------End------------------");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);

        return builder.build();

    }


}
