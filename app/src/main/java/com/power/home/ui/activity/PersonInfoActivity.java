package com.power.home.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.CameraUtil;
import com.power.home.common.util.DrawableUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.AreaBean;
import com.power.home.data.bean.UserInfoChildBean;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerUploadComponent;
import com.power.home.di.module.NickNameModule;
import com.power.home.di.module.UploadModule;
import com.power.home.presenter.NickNamePresenter;
import com.power.home.presenter.UploadPresenter;
import com.power.home.presenter.contract.NickNameContract;
import com.power.home.presenter.contract.UploadContract;
import com.power.home.ui.widget.CircleImageView;
import com.power.home.ui.widget.MyBottomPopup;
import com.power.home.ui.widget.MyTitleBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by zhp on 2019-11-08
 */

@BindEventBus
public class PersonInfoActivity extends BaseActivity<UploadPresenter> implements View.OnClickListener, UploadContract.View, NickNameContract.View {


    @BindView(R.id.titleBar)
    MyTitleBar titleBar;
    @BindView(R.id.circle_head)
    CircleImageView circleHead;
    @BindView(R.id.ll_head_img)
    LinearLayout llHeadImg;
    @BindView(R.id.tv_promo_code)
    TextView tvPromoCode;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.tv_birthdate)
    TextView tvBirthdate;
    @BindView(R.id.ll_birthdate)
    LinearLayout llBirthdate;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    private String avatarUrl;
    private String userSex;
    private String provinceCode;
    private String cityCode;


    private File file;

    @Inject
    NickNamePresenter mNickNamePresenter;
    private MyBottomPopup takePhotoPop;
    private UserInfoChildBean userInfoChildBean;
    private ArrayList<String> provinceItems;
    private ArrayList<ArrayList<String>> cityItems;
    private List<AreaBean> areaBeans;

    @Override
    public int setLayout() {
        return R.layout.activity_person_info;
    }

    @Override
    public void getExtras() {
        userInfoChildBean = (UserInfoChildBean) getIntent().getSerializableExtra(Constant.data);
    }

    @Override
    public boolean init() {
        initView();
        mPresenter.getProvince();
        return false;
    }

    private void initView() {
        DrawableUtil.loadUrl(R.drawable.icon_avatar_header, circleHead, userInfoChildBean.getAvatar());
        avatarUrl = userInfoChildBean.getAvatar();
        etNickname.setText(userInfoChildBean.getNickName());
        tvPromoCode.setText(userInfoChildBean.getRecCode());
        tvPhoneNum.setText(userInfoChildBean.getPhone());
        userSex = userInfoChildBean.getUserSex();
        if (StringUtil.isNullString(userSex)) {
            tvSex.setText("");
        } else if (userSex.toUpperCase().equals("MAN")) {
            tvSex.setText("男");
        } else {
            tvSex.setText("女");
        }
        tvBirthdate.setText(userInfoChildBean.getBirthday());
        tvAddress.setText(userInfoChildBean.getArea());
        provinceCode = userInfoChildBean.getProvinceCode();
        cityCode = userInfoChildBean.getCityCode();
    }

    @Override
    public void setListener() {
        backListener(titleBar);
        llHeadImg.setOnClickListener(this);
        llSex.setOnClickListener(this);
        llBirthdate.setOnClickListener(this);
        llAddress.setOnClickListener(this);
        tvComplete.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date.getCode() == EventBusUtils.EventCode.NICK_SUCESS) {
            finish();
        }
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerUploadComponent.builder().appComponent(appComponent)
                .uploadModule(new UploadModule(this))
                .nickNameModule(new NickNameModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_head_img:
                chooseImage();
                break;
            case R.id.ll_sex:
                chooseSex();
                break;
            case R.id.ll_birthdate:
                chooseBirthdate();
                break;
            case R.id.ll_address:
                chooseAddress();
                break;
            case R.id.tv_complete:
                String nickName = etNickname.getText().toString();
                if(StringUtil.isNullString(nickName)){
                    UIUtil.showToastSafe("请输入昵称");
                }else {
                    mNickNamePresenter.saveInfo(avatarUrl, nickName, userSex, tvBirthdate.getText().toString(), cityCode, provinceCode);
                }
                break;
        }
    }

    //选择地区
    private void chooseAddress() {

        if (areaBeans == null && provinceItems == null && cityItems == null) {
            return;
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                返回的分别是三个级别的选中位置
                provinceCode = areaBeans.get(options1).getProvinceCode();
                cityCode = areaBeans.get(options1).getZoneCityList().get(option2).getCityCode();
                String str = provinceItems.get(options1) + " " + cityItems.get(options1).get(option2);
                tvAddress.setText(str);
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {
//                String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
            }
        })
                .setSubmitText("完成")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("选择地区")//标题
                .setLineSpacingMultiplier(2.7f)
                .setItemVisibleCount(5)
                .setSubCalSize(14)//确定和取消文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleColor(getResources().getColor(R.color.colorBlack))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.colorAccent))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.colorAccent))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.colorWhite))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.colorWhite))//滚轮背景颜色 Night mode
                .setContentTextSize(16)//滚轮文字大小
//                .setLabels("省", "市", "区")//设置选择的三级单位
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .isDialog(false)//是否显示为对话框样式
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .build();

        pvOptions.setPicker(provinceItems, cityItems);//添加数据源
        pvOptions.show();
    }

    //选择出生日期
    private void chooseBirthdate() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(1900, 0, 1);
//        endDate.set(2020,11,31);


        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvBirthdate.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("完成")//确认按钮文字
                .setLineSpacingMultiplier(2.7f)
                .setItemVisibleCount(5)
                .setSubCalSize(14)
                .setContentTextSize(16)//滚轮文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleText("选择出生日期")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(getResources().getColor(R.color.colorBlack))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.colorAccent))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.colorAccent))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.colorWhite))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.colorWhite))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    //选择性别
    private void chooseSex() {
        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        MyBottomPopup chooseSexPopup = new MyBottomPopup(this, list);
        chooseSexPopup.showPopWindow();
        chooseSexPopup.setLvClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //1 男   2 女
                    case 0:
                        userSex = "MAN";
                        break;
                    case 1:
                        userSex = "WOMAN";
                        break;
                }
                chooseSexPopup.dismissPop();
                tvSex.setText(list.get(position));
            }
        });
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
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        CameraUtil.openCamera(this);
                        takePhotoPop.dismissPop();
                    }
                }
                break;
            case 1://相册
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        CameraUtil.choosePhoto(this);
                        takePhotoPop.dismissPop();
                    }
                }
                break;
        }
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
        if (null != bitmap) {
            file = CameraUtil.saveBitmapToFile(this, bitmap, "image_avatar" + SystemClock.currentThreadTimeMillis());
            try {
                long fileSize = CameraUtil.getFileSize(file);
                if (fileSize > 2 * 1024 * 1024) {
                    UIUtil.showToastSafe("不能超过2M");
                    circleHead.setImageDrawable(null);
                    file = null;
                } else {
                    circleHead.setImageBitmap(bitmap);
                    mPresenter.uploadPhoto(file);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void uploadPhotoSucess(String url) {
        if (StringUtil.isNotNullString(url)) {
            SharePreferencesUtils.saveAvatar(url);
            avatarUrl = url;
            DrawableUtil.loadUrl(R.drawable.icon_avatar_header, circleHead, url);
        } else {
            UIUtil.showToastSafe("上传失败，请稍后再试!");
        }
    }

    @Override
    public void getProvinceSuccess(List<AreaBean> areaBeans) {

        this.areaBeans = areaBeans;
        provinceItems = new ArrayList<>();
        cityItems = new ArrayList<>();
        //选项1
        for (int i = 0; i < areaBeans.size(); i++) {
            provinceItems.add(areaBeans.get(i).getProvinceName());
            ArrayList<String> cityChildItems = new ArrayList<>();
            for (int j = 0; j < areaBeans.get(i).getZoneCityList().size(); j++) {
                cityChildItems.add(areaBeans.get(i).getZoneCityList().get(j).getCityName());
            }
            cityItems.add(cityChildItems);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        file = null;
    }

    @Override
    public void modfiySuccess() {
        UIUtil.showToastSafe("修改成功!");
        finish();
    }
}