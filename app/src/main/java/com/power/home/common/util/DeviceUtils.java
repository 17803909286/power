package com.power.home.common.util;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;

import androidx.core.app.ActivityCompat;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by ZHP on 2017/6/24.
 */

public class DeviceUtils {

    public static String getAndroidID() {
        return Settings.Secure.getString(UIUtil.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getIMEI() {
        return ((TelephonyManager) UIUtil.getContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    public static String getIMSI() {
        TelephonyManager tm = (TelephonyManager) UIUtil.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId() != null ? tm.getSubscriberId() : null;
    }

    @SuppressWarnings("MissingPermission")
    public static String getWifiMacAddr() {
        String macAddr = "";
        try {
            WifiManager wifi = (WifiManager) UIUtil.getContext().getSystemService(Context.WIFI_SERVICE);
            macAddr = wifi.getConnectionInfo().getMacAddress();
            if (macAddr == null) {
                macAddr = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return macAddr;
    }

    public static String getIP() {
        WifiManager wifiManager = (WifiManager) UIUtil.getContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled() ? getWifiIP(wifiManager) : getGPRSIP();
    }

    public static String getWifiIP(WifiManager wifiManager) {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ip = intToIp(wifiInfo.getIpAddress());
        return ip != null ? ip : "";
    }

    public static String getGPRSIP() {
        String ip = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                for (Enumeration<InetAddress> enumIpAddr = en.nextElement().getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ip = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            ip = null;
        }
        return ip;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }


    public static String getSerial() {
        return Build.SERIAL;
    }

    public static String getSIMSerial() {
        TelephonyManager tm = (TelephonyManager) UIUtil.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimSerialNumber();
    }

    public static String getMNC() {
        String providersName = "";
        TelephonyManager telephonyManager = (TelephonyManager) UIUtil.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY) {
            providersName = telephonyManager.getSimOperator();
            providersName = providersName == null ? "" : providersName;
        }
        return providersName;
    }

    public static String getCarrier() {
        TelephonyManager tm = (TelephonyManager) UIUtil.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperatorName().toLowerCase(Locale.getDefault());
    }


    public static String getModel() {
        return Build.MODEL;
    }

    public static String getBuildBrand() {
        return Build.BRAND;
    }

    public static String getBuildHost() {
        return Build.HOST;
    }

    public static String getBuildTags() {
        return Build.TAGS;
    }

    public static long getBuildTime() {
        return Build.TIME;
    }

    public static String getBuildUser() {
        return Build.USER;
    }

    public static String getBuildVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    public static String getBuildVersionCodename() {
        return Build.VERSION.CODENAME;
    }

    public static String getBuildVersionIncremental() {
        return Build.VERSION.INCREMENTAL;
    }

    public static int getBuildVersionSDK() {
        return Build.VERSION.SDK_INT;
    }

    public static String getBuildID() {
        return Build.ID;
    }

    public static String[] getSupportedABIS() {
        String[] result = new String[]{"-"};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            result = Build.SUPPORTED_ABIS;
        }
        if (result == null || result.length == 0) {
            result = new String[]{"-"};
        }
        return result;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }


    public static String getBootloader() {
        return Build.BOOTLOADER;
    }


    public static String getScreenDisplayID() {
        WindowManager wm = (WindowManager) UIUtil.getContext().getSystemService(Context.WINDOW_SERVICE);
        return String.valueOf(wm.getDefaultDisplay().getDisplayId());
    }

    public static String getDisplayVersion() {
        return Build.DISPLAY;
    }


    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String getCountry() {
        TelephonyManager tm = (TelephonyManager) UIUtil.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        Locale locale = Locale.getDefault();
        return tm.getSimState() == TelephonyManager.SIM_STATE_READY ? tm.getSimCountryIso().toLowerCase(Locale.getDefault()) : locale.getCountry().toLowerCase(locale);
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    //<uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES"/>
    public static String getGSFID(Context context) {
        String result;
        final Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        final String ID_KEY = "android_id";
        String[] params = {ID_KEY};
        Cursor c = context.getContentResolver().query(URI, null, null, params, null);
        if (c == null || !c.moveToFirst() || c.getColumnCount() < 2) {
            return null;
        } else {
            result = Long.toHexString(Long.parseLong(c.getString(1)));
        }
        c.close();
        return result;
    }

    //<uses-permission android:name="android.permission.BLUETOOTH"/>
    @SuppressWarnings("MissingPermission")
    public static String getBluetoothMAC(Context context) {
        String result = null;
        try {
            if (context.checkCallingOrSelfPermission(Manifest.permission.BLUETOOTH)
                    == PackageManager.PERMISSION_GRANTED) {
                BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
                result = bta.getAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getPsuedoUniqueID() {
        String devIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            devIDShort += (Build.SUPPORTED_ABIS[0].length() % 10);
        } else {
            devIDShort += (Build.CPU_ABI.length() % 10);
        }
        devIDShort += (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
        String serial;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            return new UUID(devIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception e) {
            serial = "ESYDV000";
        }
        return new UUID(devIDShort.hashCode(), serial.hashCode()).toString();
    }

    public static String getFingerprint() {
        return Build.FINGERPRINT;
    }

    public static String getHardware() {
        return Build.HARDWARE;
    }

    public static String getProduct() {
        return Build.PRODUCT;
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getBoard() {
        return Build.BOARD;
    }

    public static String getRadioVersion() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ? Build.getRadioVersion() : "";
    }

    public static String getUA() {
        final String system_ua = System.getProperty("http.agent");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return new WebView(UIUtil.getContext()).getSettings().getDefaultUserAgent(UIUtil.getContext()) + "__" + system_ua;
        } else {
            return new WebView(UIUtil.getContext()).getSettings().getUserAgentString() + "__" + system_ua;
        }
    }

    public static String getDensity() {
        String densityStr = null;
        final int density = UIUtil.getContext().getResources().getDisplayMetrics().densityDpi;
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                densityStr = "LDPI";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                densityStr = "MDPI";
                break;
            case DisplayMetrics.DENSITY_TV:
                densityStr = "TVDPI";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                densityStr = "HDPI";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                densityStr = "XHDPI";
                break;
            case DisplayMetrics.DENSITY_400:
                densityStr = "XMHDPI";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                densityStr = "XXHDPI";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                densityStr = "XXXHDPI";
                break;
        }
        return densityStr;
    }

    //<uses-permission android:name="android.permission.GET_ACCOUNTS"/>
    @SuppressWarnings("MissingPermission")
    public static String[] getGoogleAccounts() {
        if (UIUtil.getContext().checkCallingOrSelfPermission(Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
            Account[] accounts = AccountManager.get(UIUtil.getContext()).getAccountsByType("com.google");
            String[] result = new String[accounts.length];
            for (int i = 0; i < accounts.length; i++) {
                result[i] = accounts[i].name;
            }
            return result;
        }
        return null;
    }

    public static String getWifiIpAddress(Context context) {

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    public static String getUniqueID() {
        if (ActivityCompat.checkSelfPermission(UIUtil.getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        } else {
            final TelephonyManager tm = (TelephonyManager) UIUtil.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            final String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();

            androidId = "" + android.provider.Settings.Secure.getString(UIUtil.getContext().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            return deviceUuid.toString();
        }
    }

    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

}
