package com.power.home.common.util;

import android.provider.Settings;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;

import com.power.home.common.BaseSingleton;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZHP on 2017/6/24.
 */
public class StringUtil extends BaseSingleton {


    public static StringUtil getInstance() {
        return getSingleton(StringUtil.class);
    }

    /*
     根据html片段 拼装html页面
    * */
    public static String appendHtmlString(String bodyHtml){
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        builder.append("<head>");
        builder.append("\"<meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1.0, user-scalable=no\\\"> \" +\n" +
                "                \"<style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} p{padding:0px;margin:0px;font-size:13px;color:#222222;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:100%; height:auto;}</style>\" +\n" +
                "                \"</head>\"");
        builder.append("<body>").append(bodyHtml).append("</body>").append("</html>");
        return builder.toString();


    }

    public static boolean isContainUrl(String urlString){
        Matcher m = Pattern.compile("((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$").matcher(urlString);
        String[] url = new String[urlString.length()/5];
        int count = 0;
        while(m.find()){
            String findedUrl = m.group();
            url[count] = findedUrl;
            count++;
        }
       return count > 0 ? true : false;

    }
    public static String getUrl(String htmlBody){
        Matcher m = Pattern.compile("\\(?\\b(http://|www[.]|https://)[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]").matcher(htmlBody);
        String[] url = new String[htmlBody.length()/5];
        int count = 0;
        while(m.find()){
            url[count] = m.group();
            count++;
        }
        return url[0];
    }
    public static boolean isNullString(String str) {
        return (null == str || str.trim().isEmpty() || "null".equals(str.trim()
                .toLowerCase(Locale.getDefault())));
    }

    public static boolean isNotNullString(String str) {
        return !isNullString(str);
    }

    public static String formatNullString(String str) {
        return isNotNullString(str) ? str : "";
    }

    public static boolean equalSpecialStr(String lhs, String rhs) {
        if (isNullString(lhs) && isNullString(rhs)) {
            return true;
        } else {
            if (lhs.equals(rhs)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim()) && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isEquals(String... agrs) {
        String last = null;
        for (int i = 0; i < agrs.length; i++) {
            String str = agrs[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    public static String cutNumber(String content) {
        if (isNotNullString(content)) {
            Pattern p = Pattern.compile("[^0-9]");
            Matcher m = p.matcher(content);
            return m.replaceAll("");
        } else {
            return "";
        }
    }

    public static String replaceDate(String Date) {
        return Date.replaceAll("-", "/");
    }


    public static String changeTime(String start_time) {
        String h;
        String m;
        if (start_time.length() < 4) {
            h = "0" + start_time.substring(0, 1);
            m = start_time.substring(1);
        } else {
            h = start_time.substring(0, 2);
            m = start_time.substring(2);
        }
        return h + ":" + m;
    }

    public static String generateOpenUDID() {
        String OpenUDID = Settings.Secure.getString(UIUtil.getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if (OpenUDID == null || OpenUDID.equals("9774d56d682e549c")
                | OpenUDID.length() < 15) {
            final SecureRandom random = new SecureRandom();
            OpenUDID = new BigInteger(64, random).toString(16);
        }
        return OpenUDID;
    }


    public static String getDecimalZero(double d) {
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.FLOOR);//放弃四舍五入
        String result = df.format(d);
        return result;

    }

    public static String getDecimalTwo(double d) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.FLOOR);//放弃四舍五入
        String result = df.format(d);
        return result;

    }

    public static String getDecimal(double d) {
        DecimalFormat df = new DecimalFormat("0.0");
        df.setRoundingMode(RoundingMode.FLOOR);//放弃四舍五入
        String result = df.format(d);
        return result;

    }

    public static String getPlayCount(int i) {
        BigDecimal d = new BigDecimal(i / 10000);
        DecimalFormat df = new DecimalFormat("0.0");
        df.setRoundingMode(RoundingMode.FLOOR);
        String result = df.format(d);
        return result;

    }


    public static String getDecimalX(int x, double d) {
        String result;
        BigDecimal b = new BigDecimal(d);
        double f = b.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
        result = String.valueOf(f);
        return result;

    }

    public static String getDecimalMoney(String str) {
        if (StringUtil.isNullString(str)) {
            return null;
        }
        String result = null;
        if (Double.parseDouble(str)>1) {
            DecimalFormat myformat = new DecimalFormat("###,###.00");
            result = myformat.format(new BigDecimal(str));
        }else {
            DecimalFormat myformat = new DecimalFormat("0.00");
            result = str.equals("0") ? "0" : myformat.format(new BigDecimal(str));
        }
        return result;

    }



    public static SpannableString getSpannableString(String str, int start, int end, int style_left, int style_right) {
        SpannableString styledText = new SpannableString(str);
        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), style_left), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), style_right), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return styledText;
    }

    public static SpannableString getSpannableString(String str, int start, int mide, int end, int style_left, int style_mid, int style_right) {
        SpannableString styledText = new SpannableString(str);
        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), style_left), start, mide, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), style_mid), mide, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(UIUtil.getContext(), style_right), end, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return styledText;
    }


    public static String getNumberFormat(String str) {
        if (StringUtil.isNullString(str)) {
            return null;
        }
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(2);
        return percentInstance.format((Double.valueOf(str)));
    }


    public static boolean isOnlyPointNumber(String number) {//保留两位小数正则
        Pattern pattern = Pattern.compile("^\\d+\\.?\\d{0,2}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }


    public static boolean isSurePwd(String str) {//保留两位小数正则
        Pattern pattern = Pattern.compile("(?!^[0-9]{8,20}$)^[0-9A-Za-z\\u0021-\\u007e]{8,20}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    public static String getMyUUID() {

        UUID uuid = UUID.randomUUID();

        String uniqueId = uuid.toString();

        return uniqueId;

    }





    public static String substringBefore(String s, String sub) {
        if (TextUtils.isEmpty(s) || TextUtils.isEmpty(sub)) {
            return "";
        }
        int idx = s.indexOf(sub);
        if (idx < 0) {
            return "";
        }
        return s.substring(0, idx);
    }

    public static String substringBetween(String s, String subA, String subB) {
        if (TextUtils.isEmpty(s) || TextUtils.isEmpty(subA) || TextUtils.isEmpty(subB)) {
            return "";
        }

        int idxA = s.indexOf(subA);
        if (idxA < 0) {
            return "";
        }
        int idxB = s.indexOf(subB);
        if (idxB < 0) {
            return "";
        }
        return s.substring(idxA + subA.length(), idxB);
    }

    public static String getSubtext(String text, String[] keys) {
        return getSubtext(text, Arrays.asList(keys));
    }

    public static String getSubtext(String text, List<String> keys) {
        if (keys.size() == 1) {
            return substringBefore(text, keys.get(0));
        } else if (keys.size() == 2) {
            return substringBetween(text, keys.get(0), keys.get(1));
        }
        return "";
    }

    public static boolean containsAll(String text, String[] keys) {
        return containsAll(text, Arrays.asList(keys));
    }

    public static boolean containsAll(String text, List<String> keys) {
        for (String key : keys) {
            if (!text.contains(key))
                return false;
        }
        return true;
    }

    public static boolean containsOnly(String text, List<String> keys) {
        for (String key : keys) {
            if (text.contains(key))
                return true;
        }
        return false;
    }

    public static boolean containsOnly(String text, String[] keys) {
        for (String key : keys) {
            if (text.contains(key))
                return true;
        }
        return false;
    }

    public static <T> boolean isEmptyList(List<T> list) {
        if (null == list) {
            return true;
        } else if (("").equals(list)) {
            return true;
        } else if (("null").equals((list + ("")).trim())) {
            return true;
        } else if ((list + "").trim().equals("[]")) {
            return true;
        } else {
            return false;
        }

    }

    public static String intFormat(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return i + "";
    }
}
