package com.power.home.common.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.power.home.BuildConfig;
import com.power.home.Manifest;
import com.power.home.R;
import com.power.home.data.bean.VersionBean;
import com.power.home.ui.activity.BaseActivity;
import com.power.home.ui.widget.MaterialViewDialog;
import com.power.home.ui.widget.UpdataVersionPopup;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.functions.Consumer;

/**
 * Created by szh on 2018/6/6.
 */

public class AppInnerDownLoder {

    public static String SD_FOLDER = "";
    private static final String TAG = AppInnerDownLoder.class.getSimpleName();
    private BaseActivity activity;
    private TextView tvProgress;
    private ProgressBar pbBar;

    public AppInnerDownLoder(BaseActivity activity) {
        this.activity = activity;
    }


    public void updateVer(VersionBean versionBean) {
        Boolean isForced = versionBean.isIsForceUpdate();
        String updateUrl = versionBean.getFileDownLink();
        if (activity.isFinishing()) return;
        UpdataVersionPopup updataVersionPopup = new UpdataVersionPopup(activity);
        updataVersionPopup.showPopWindow(isForced);

        updataVersionPopup.tv_describe.setText(versionBean.getVersionDesc());
        updataVersionPopup.setUpdata(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isNotNullString(updateUrl)) {
                    activity.requestSdWritePermissionStatic(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {


                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    boolean haveInstallPermission = activity.getPackageManager().canRequestPackageInstalls();
                                    if (haveInstallPermission) {
                                        downLoadApk(activity,updateUrl, "future");
                                    } else {
                                        MaterialViewDialog dialog = new MaterialViewDialog(activity);
                                        View contentView = activity.getLayoutInflater().inflate(R.layout.my_message_dialog, null);
                                        TextView title = contentView.findViewById(R.id.title);
                                        TextView message = contentView.findViewById(R.id.message);
                                        Button no = contentView.findViewById(R.id.no);
                                        Button yes = contentView.findViewById(R.id.yes);

                                        title.setText("提示");
                                        message.setText("未拥有安装未知来源应用的权限,请打开权限后再进行下载安装");
                                        yes.setText("确定");
                                        no.setText("取消");
                                        no.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                            }
                                        });
                                        yes.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + activity.getPackageName()));
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                activity.startActivity(intent);
                                                dialog.dismiss();
                                            }
                                        });
                                        dialog.setCanceledOnTouchOutside(false);
                                        dialog.contentView(contentView);
                                        dialog.hasTitle(false)
                                                .bgColor(0)
                                                .widthScale(0.85f)
                                                .show();

                                    }
                                } else {
                                    downLoadApk(activity,updateUrl, "future");
                                }
                            } else {
                                // 未获取权限
                                UIUtil.showToastSafe("获取权限失败,更新失败");
                            }
                        }
                    });

                }
            }
        });

    }


    @SuppressWarnings("unused")
    public static void downLoadApk(final BaseActivity context, final String downURL, final String appName) {
        SD_FOLDER = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "";
        MaterialViewDialog dialog = new MaterialViewDialog(context);
        View contentView = context.getLayoutInflater().inflate(R.layout.layout_download_dialog, null);
        TextView tvProgress = contentView.findViewById(R.id.tvProgress);
        ProgressBar pbBar = contentView.findViewById(R.id.pbBar);
        dialog.setCanceledOnTouchOutside(false);
        dialog.contentView(contentView);
        dialog.hasTitle(false)
                .bgColor(0)
                .widthScale(0.85f)
                .show();
        DownloadUtil.get().download(downURL, SD_FOLDER , appName, new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                installApk(context,file);
            }

            @Override
            public void onDownloading(int progress) {
                pbBar.setProgress(progress);
            }

            @Override
            public void onDownloadFailed(Exception e) {

            }
        });
    }


    private static File downloadFile(String path, String appName, ProgressDialog pd) throws Exception {
        int totalMax = 45530000;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            pd.setMax(totalMax);
            InputStream is = conn.getInputStream();
            String fileName = SD_FOLDER + "/"
                    + appName + ".apk";
            File file = new File(fileName);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                pd.setProgress(total);
                pd.setProgressNumberFormat(String.format("%.2fM/%.2fM", bytes2kb(total), bytes2kb(totalMax)));

            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            throw new IOException("未发现有SD卡");
        }
    }


    private static void installApk(BaseActivity mContext,File file) {
        installApk1(mContext,file);
//
    }

    public interface OnApplyClickLitener {
        void onApplyClick();
    }


    public static void installApk1(Context context,File file) {
        Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT > 23) {
            uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        }
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(install);
    }

    /**
     * 获取应用程序版本（versionName）
     *
     * @return 当前应用的版本号
     */

    private static double getLocalVersion(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "获取应用程序版本失败，原因：" + e.getMessage());
            return 0.0;
        }

        return Double.valueOf(info.versionName);
    }


    public static float bytes2kb(int bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return returnValue;
//
    }

    private static String getFileMD5(File file) throws NoSuchAlgorithmException, IOException {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest;
        FileInputStream in;
        byte buffer[] = new byte[1024];
        int len;
        digest = MessageDigest.getInstance("MD5");
        in = new FileInputStream(file);
        while ((len = in.read(buffer, 0, 1024)) != -1) {
            digest.update(buffer, 0, len);
        }
        in.close();
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

}
