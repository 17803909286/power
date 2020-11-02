package com.power.home.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.power.home.BuildConfig;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.CameraUtil;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UploadPersonVerifyUitl;
import com.power.home.data.bean.CertificationBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.PersonVerifyBackBean;
import com.power.home.data.bean.PersonVerifyFrontBean;
import com.power.home.data.bean.TokenBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerUploadIdCardComponent;
import com.power.home.di.module.UploadIdCardModule;
import com.power.home.net.okHttp.RequestCenter;
import com.power.home.net.okHttp.listener.DisposeDataListener;
import com.power.home.net.okHttp.request.RequestParams;
import com.power.home.presenter.UploadIdCardPresenter;
import com.power.home.presenter.contract.UploadIdCardContract;
import com.power.home.ui.widget.MyBottomPopup;
import com.power.home.ui.widget.MyTitleBar;
import com.power.home.ui.widget.UILoadingBar;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.KeyGenerator;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import java.util.UUID;
import butterknife.BindView;

/**
 * 实名认证
 */
@BindEventBus
public class CertificationFristActivity extends BaseActivity<UploadIdCardPresenter> implements View.OnClickListener, UploadIdCardContract.View, DisposeDataListener, UploadPersonVerifyUitl.uploadCompleteListener {

    private static final int PERMISSIONCAMERA_REQUEST_CODE = 1000;
    private static final int PERMISSIONALUM_REQUEST_CODE = 2000;
    private static final int PERSONVERIFY_FRONT = 10000;
    private static final int PERSONVERIFY_BACK = 20000;

    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.iv_id_card_front)
    ImageView ivIdCardFront;
    @BindView(R.id.iv_delete_front)
    ImageView ivDeleteFront;
    @BindView(R.id.iv_id_card_back)
    ImageView ivIdCardBack;
    @BindView(R.id.iv_delete_back)
    ImageView ivDeleteBack;
    @BindView(R.id.tv_card_next)
    TextView tvCardNext;

    private MyBottomPopup takePhotoPop;
    private LoadingDailog.Builder loadBuilder ;
    private LoadingDailog loadingDailog;
    private PersonVerifyFrontBean frontBean;
    private PersonVerifyBackBean backBean;
    private UploadManager uploadManager;
    private String frontImageUrl = null;
    private String backImageUrl = null;
    private File file0;
    private File file1;
    private int point = 0;// 0正面 1反面
    private Configuration configuration;
    @Override
    public int setLayout() {
        return R.layout.activity_certification_frist;
    }

    @Override
    public void getExtras() {

    }

    @Override
    public boolean init() {

        loadBuilder = new LoadingDailog.Builder(this);
        loadBuilder.setCancelable(false);
        loadBuilder.setMessage("上传中...");
        loadBuilder.setCancelOutside(true);
        loadingDailog  = loadBuilder.create();

        return false;
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        tvCardNext.setOnClickListener(this);
        ivIdCardFront.setOnClickListener(this);
        ivDeleteFront.setOnClickListener(this);
        ivIdCardBack.setOnClickListener(this);
        ivDeleteBack.setOnClickListener(this);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerUploadIdCardComponent.builder().appComponent(appComponent)
                .uploadIdCardModule(new UploadIdCardModule(this))
                .build().inject(this);
    }

    //拍照
    private void chooseImage() {
        ArrayList<String> list = new ArrayList<>();
        list.add("拍照");
        list.add("相册");
        takePhotoPop = new MyBottomPopup(this, list);
        takePhotoPop.showPopWindow();
        takePhotoPop.setLvClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chooseWay(position);
            }
        });

    }

    //选择拍照方式
    private void chooseWay(int position) {
        switch (position) {
            case 0://相机
                takePhotoPop.dismissPop();
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                } else {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                    } else {
                        CameraUtil.openCamera(this);

                    }
                }
                break;
            case 1://相册
                takePhotoPop.dismissPop();
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        CameraUtil.choosePhoto(this);

                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int i =0;i<grantResults.length ;i++)
        {
            if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                return;
            }
        }
        if(requestCode == 2){
            CameraUtil.openCamera(this);
            return;
        }
        CameraUtil.choosePhoto(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = null;
        //从相册选取
        if (requestCode == CameraUtil.REQUEST_PICTURE_CHOOSE && resultCode == RESULT_OK) {
            bitmap = CameraUtil.dealPic(this, CameraUtil.REQUEST_PICTURE_CHOOSE, RESULT_OK, data);
        }
        //拍照
        if (requestCode == CameraUtil.REQUEST_CAMERA_IMAGE && resultCode == RESULT_OK) {
            bitmap = CameraUtil.dealPic(this, CameraUtil.REQUEST_CAMERA_IMAGE, RESULT_OK, data);
        }
//        if(CameraUtil.getBitmapSize(bitmap) > 2*1024*1024){
//            UIUtil.showToastSafe("不能超过2M");
//            return;
//        }

        if (null != bitmap) {
            switch (point) {
                case 0:

                    ivDeleteFront.setVisibility(View.VISIBLE);
                    file0 = CameraUtil.saveBitmapToFile(this, bitmap, "image_avatar" + point + "point" + SystemClock.currentThreadTimeMillis());

                    if(!this.checkFileSize(file0)){
                        return;
                    }
                    ivIdCardFront.setImageBitmap(bitmap);

                    this.checkPersonVerify();

                    break;
                case 1:
                    ivDeleteBack.setVisibility(View.VISIBLE);
                    file1 = CameraUtil.saveBitmapToFile(this, bitmap, "image_avatar" + point + "point" + SystemClock.currentThreadTimeMillis());
                    if(!this.checkFileSize(file1)){
                        return;
                    }
                    ivIdCardBack.setImageBitmap(bitmap);
                    this.checkPersonVerify();

                    break;
            }



        }
    }


    private void checkPersonVerify(){
        loadingDailog.show();
        RequestParams params = new RequestParams();
        params.put("key", BuildConfig.kOCRKey);
        params.put("image", DrawableUtil.imageToBase64(point == 0 ? file0 : file1));
        params.put("side",point == 0 ? "front" : "back");
        RequestCenter.requestPersonVerifyinfo(this,params);

    }
    private boolean  checkFileSize(File file){
        try {
            long fileSize = CameraUtil.getFileSize(file);
            if (fileSize > 2 * 1024 * 1024) {
                UIUtil.showToastSafe("不能超过2M");
                switch (point) {
                    case 0:
                        ivIdCardFront.setImageDrawable(null);
                        break;
                    case 1:
                        ivIdCardBack.setImageDrawable(null);
                        break;
                }
             file = null;
            }else{
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_card_next:
                if(frontBean == null || backBean == null){
                    UIUtil.showToastSafe("请先上传身份证照片");
                    return;
                }
                String token = SharePreferencesUtils.getUploadToken();
                String imageUrl = SharePreferencesUtils.getUploadBaseUrl();
                Log.i("uploadImageUrl：",imageUrl);

                loadBuilder.setMessage("正在认证...");
                if(TextUtils.isEmpty(token)){
                    loadingDailog.show();
                    mPresenter.getUploadToken();
                    return;
                }
                loadingDailog.show();
                this.uploadPersonPicToServer();

//                String name = etName.getText().toString();
//                String idCard = etIDcard.getText().toString();
//                if (StringUtil.isNullString(name)) {
//                    UIUtil.showToastSafe("请输入姓名");
//                } else if (StringUtil.isNullString(idCard)) {
//                    UIUtil.showToastSafe("请输入身份证号");
//                } else if (file0 == null) {
//                    UIUtil.showToastSafe("请上传身份证正面照");
//                } else if (file1 == null) {
//                    UIUtil.showToastSafe("请上传身份证反面照");
//                } else {
//                    mPresenter.uploadIdCard(file0, file1, idCard, name);
//                }



                break;
            case R.id.iv_id_card_front:

                point = 0;
                chooseImage();
                break;
            case R.id.iv_delete_front:
                file0 = null;
                ivDeleteFront.setVisibility(View.GONE);
                ivIdCardFront.setImageDrawable(null);
                break;
            case R.id.iv_id_card_back:
                if( frontBean == null){
                    UIUtil.showToastSafe("请先拍摄正面照片");

                    return;
                }
                point = 1;

                chooseImage();
                break;
            case R.id.iv_delete_back:
                file1 = null;
                ivDeleteBack.setVisibility(View.GONE);
                ivIdCardBack.setImageDrawable(null);
                break;
        }
    }

    @Override
    public void uploadIdCardSuccess(CertificationBean certificationBean) {
        Intent intent = new Intent(UIUtil.getContext(), CertificationTwoActivity.class);
        intent.putExtra(Constant.id, certificationBean.getId());
        startActivity(intent);
    }

    @Override
    public void getUploadTokenBeanSuccess(TokenBean tokenBean) {
        if(tokenBean == null){

            UIUtil.showToastSafe("鉴权失败");
            loadingDailog.hide();
            return;
        }

        SharePreferencesUtils.setUploadToken(tokenBean.getToken());
        Log.i("qiniutokenupload：",tokenBean.getUrl());
        SharePreferencesUtils.setUploadImageBaseUrl(tokenBean.getUrl());
        this.uploadPersonPicToServer();
    }

    @Override
    public void getPersonVerifyInfoSuccess(EmptyBean emptyBean) {

        loadingDailog.hide();
        if(emptyBean == null){
            UIUtil.showToastSafe("认证失败");
            return;
        }
        UIUtil.showToastSafe("认证成功");
        finish();
    }

    private void uploadPersonInfo(){
        String imageUrl = SharePreferencesUtils.getUploadBaseUrl();
        String front = imageUrl + "/" + frontImageUrl;
        String back = imageUrl +"/" + backImageUrl;
        Log.i("personinfoback：",back);
        Log.i("personInfofront",front);
        Log.i("personInfoUserid",SharePreferencesUtils.getUserId());
        Log.i("personInfoidCard",frontBean.getIdcard());
        Log.i("personInfoaddress",frontBean.getAddress());
        Log.i("personInfobegin",backBean.getBegin());
        Log.i("personInfoend",backBean.getEnd());
        mPresenter.personVerify(frontBean.getRealname(),frontBean.getIdcard(),Integer.parseInt(SharePreferencesUtils.getUserId()),front,back,frontBean.getAddress(),backBean.getBegin(),backBean.getEnd());
    }

    private void uploadPersonPicToServer(){
        String token = SharePreferencesUtils.getUploadToken();
          UploadPersonVerifyUitl.getInstance().uploadPersonVerify(file0,token,"front",this);
          UploadPersonVerifyUitl.getInstance().uploadPersonVerify(file1,token,"back",this);
//        String own= UUID.randomUUID().toString();
//        UploadManager uploadManager = new UploadManager(configuration);
//       uploadManager.put(file0, own, token, new UpCompletionHandler() {
//           @Override
//           public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
//              loadingDailog.hide();
//               UIUtil.showToastSafe("上传成功");
//               Log.i("qiniutokenupload：", s + "\ninfo：" + responseInfo + "\nres：" + jsonObject);
//
//           }
//       },null);


    }



    /**
     * @param date 实名认证保存成功之后刷新数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.CERTIFICATION_SAVE_REFRESH:
                    finish();
                    break;
            }
        }
    }

    @Override
    public void onSuccess(JSONObject responseObj)  {

        loadingDailog.hide();
        Log.i("personinfo：",responseObj.toString());
        try{
            int error_code = responseObj.getInt("error_code");
            if(error_code != 0){

                UIUtil.showToastSafe("认证失败");
                return;
            }
            JSONObject resultObj = responseObj.getJSONObject("result");
            if(point == 0){

                PersonVerifyFrontBean bean = new Gson().fromJson(resultObj.toString(),PersonVerifyFrontBean.class);
                frontBean = bean;
                Log.i("personInfoend", bean.toString());


            }else{
                PersonVerifyBackBean bean = new Gson().fromJson(resultObj.toString(),PersonVerifyBackBean.class);
                backBean = bean;
                Log.i("personInfoend", bean.toString());

            }

        }catch (JSONException e){
            UIUtil.showToastSafe("认证失败");
        }


    }

    @Override
    public void onSuccessConvetedToEntity(Object responesObj) {

    }

    @Override
    public void onFailure(Object reasonObj) {
        Log.i("personinfofail：",reasonObj.toString());
        UIUtil.showToastSafe("认证失败");
        loadingDailog.hide();
    }


    @Override
    public void uploadPerSonVerifyComplete(String key) {

        if(!loadingDailog.isShowing()){
            return;
        }
        if(key == null){
            loadingDailog.hide();
            frontImageUrl = null;
            backImageUrl = null;
            UIUtil.showToastSafe("认证失败");
            return;

        }

        if(key.endsWith("front")){
            frontImageUrl = key;
        }
        if(key.endsWith("back")){
            backImageUrl = key;
        }
        if(frontImageUrl!= null && backImageUrl !=null){
            this.uploadPersonInfo();
        }
    }
}
