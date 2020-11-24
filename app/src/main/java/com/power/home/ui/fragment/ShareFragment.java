package com.power.home.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SpanUtils;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.CameraUtil;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.PopuUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.ShareImgeBean;
import com.power.home.di.component.AppComponent;
import com.power.home.ui.widget.MyBottomPopup;
import com.power.home.ui.widget.MyPopupWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ZHP on 2020/4/14 0014.
 */


public class ShareFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.iv_advertising)
    ImageView ivAdvertising;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
//    @BindView(R.id.tv_chose_pic)
//    public TextView tvChosePic;
//    @BindView(R.id.et_title)
//    public EditText etTitle;
    @BindView(R.id.tv_user_content)
    TextView tvUserContent;
    private String title, url, inviteUrl;
    private boolean isShow = false;
    private ShareImgeBean shareImgeBean = new ShareImgeBean();
    private String inviteFrontName;
    private String inviteBehindName;
    private String courseName;
//    private MyPopupWindow mCameraPopupWindow;
    private List<String> list; //  拍照 || 相册
    private MyBottomPopup myBottomPopup;
    private Uri uritempFile;


    @Override
    public int setLayout() {
        return R.layout.fragment_share;
    }

    @Override
    protected void getExtras() {
        title = getArguments().getString(Constant.title);
        url = getArguments().getString(Constant.url);
        inviteUrl = getArguments().getString(Constant.inviteUrl);
        int position = getArguments().getInt(Constant.position);
        inviteFrontName = getArguments().getString(Constant.inviteFrontName);
        inviteBehindName = getArguments().getString(Constant.inviteBehindName);
        courseName = getArguments().getString(Constant.courseName);
        shareImgeBean.setPosition(position);
    }

    @Override
    public void init() {
//        etTitle.setText(title);
        DrawableUtil.loadUrl(R.drawable.icon_place_holder_311_350, ivAdvertising, url);
        ivQrcode.setImageBitmap(DrawableUtil.createQRCodeBitmapMarginZero(inviteUrl, UIUtil.dip2px(80), UIUtil.dip2px(80)));

        tvUserName.setText("我是" + (StringUtil.isNullString(SharePreferencesUtils.getNickName()) ? getString(R.string.app_name) : SharePreferencesUtils.getNickName()));
        SpanUtils spanUtilsUser = new SpanUtils().append(inviteFrontName).setFontSize(10, true).setForegroundColor(getResources().getColor(R.color.colorBlack1A1F28))
                .append(courseName).setFontSize(10, true).setForegroundColor(getResources().getColor(R.color.colorYellowFFB339))
                .append(inviteBehindName).setFontSize(10, true).setForegroundColor(getResources().getColor(R.color.colorBlack1A1F28));
        tvUserContent.setText(spanUtilsUser.create());
        list = new ArrayList<>();
        list.add("拍照");
        list.add("从相册选择");
    }

    @Override
    protected void setListener() {
//        etTitle.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                shareImgeBean.setTitle(charSequence.toString());
//                if (isShow) {
//                    EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.FINISH_EDIT_SHARE, shareImgeBean));
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//        tvChosePic.setOnClickListener(this);
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isShow = isVisibleToUser;
//        if (null != etTitle) {
//            etTitle.setEnabled(!isVisibleToUser);
//        }
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.tv_chose_pic:
//                chooseImage();
//                break;
//        }
    }

    private void chooseImage() {
        myBottomPopup = new MyBottomPopup(getActivity(), list);
        myBottomPopup.showPopWindow();
        myBottomPopup.setLvClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else {
                            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            } else {
                                CameraUtil.openCamera(ShareFragment.this);
                            }
                        }

                        myBottomPopup.dismissPop();
                        break;

                    case 1:
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else {
                            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            } else {
                                CameraUtil.choosePhoto(ShareFragment.this);
                            }
                        }
                        myBottomPopup.dismissPop();
                        break;
                }
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        File file = null;
        //从相册选取
        if (requestCode == CameraUtil.REQUEST_PICTURE_CHOOSE && resultCode == RESULT_OK) {
            file = CameraUtil.dealPic2(getActivity(), CameraUtil.REQUEST_PICTURE_CHOOSE, RESULT_OK, data);
            crop(file);
        }
        //拍照
        if (requestCode == CameraUtil.REQUEST_CAMERA_IMAGE && resultCode == RESULT_OK
        ) {
            file = CameraUtil.dealPic2(getActivity(), CameraUtil.REQUEST_CAMERA_IMAGE, RESULT_OK, data);
            crop(file);
        }
        //裁剪
        if (requestCode == CameraUtil.REQUEST_CROP_IMAGE
        ){

            try {
                Bitmap image = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uritempFile));
                ivAdvertising.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    private void crop(File photoFile) {
        if (photoFile != null) {
            Uri uri = Uri.fromFile(photoFile);
            // 调用系统中自带的图片剪裁
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 280);
            intent.putExtra("aspectY", 314);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 280);
            intent.putExtra("outputY", 314);
//            intent.putExtra("return-data", true);

            /**
             * 上述方法中，裁剪后的图片通过Intent的putExtra("return-data",true)方法进行传递，miui系统问题就出在这里，return-data的方式只适用于小图，miui系统默认的裁剪图片可能裁剪得过大，或对return-data分配的资源不足，造成return-data失败。
             *
             * 解决思路是：裁剪后，intent保存图片的资源路径Uri，在onActivityResult()方法中，再提取对应的Uri图片资源转换为Bitmap使用。
             * ————————————————
             * 版权声明：本文为CSDN博主「eclothy」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
             * 原文链接：https://blog.csdn.net/eclothy/java/article/details/42719217
             */
            uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "crop.jpg");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

            startActivityForResult(intent, CameraUtil.REQUEST_CROP_IMAGE);

        }
    }
}
