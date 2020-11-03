package com.power.home.common.util;

import android.util.Log;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.Recorder;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.UUID;

public class UploadPersonVerifyUitl {

    private static volatile UploadPersonVerifyUitl instance = null;
    private Configuration configuration;
    public static UploadPersonVerifyUitl getInstance(){
       synchronized (UploadPersonVerifyUitl.class){
           if(instance == null){
               instance = new UploadPersonVerifyUitl();
           }
       }
       return instance;
    }
    private UploadPersonVerifyUitl(){

        initConfig();
    }

    private void initConfig(){

        configuration = new Configuration.Builder()
                .connectTimeout(10)
                .responseTimeout(15).build();
    }

    public  void uploadPersonVerify(File file,String token,String suffix,uploadCompleteListener listener){
        String own= UUID.randomUUID().toString() + suffix;
       UploadManager uploadManager = new UploadManager(configuration);
       uploadManager.put(file, own, token, new UpCompletionHandler() {
           @Override
           public void complete(String key, ResponseInfo info, JSONObject response) {
               Log.i("personinfofail：",response.toString());
               if(response == null){
                   if(listener != null){
                       listener.uploadPerSonVerifyComplete(null);
                   }
                   return;
               }

               try {
                   String tokenKey = response.getString("key");
                   listener.uploadPerSonVerifyComplete(tokenKey);
               } catch (JSONException e) {
                   listener.uploadPerSonVerifyComplete(null);
               }





           }
       },null);

    }



    public interface uploadCompleteListener{
        public void uploadPerSonVerifyComplete(String key);
    }

}
