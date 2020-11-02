package com.power.home.common.util;

import android.text.InputFilter;
import android.text.Spanned;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditInputFilter implements InputFilter {

    public  double maxValue ;
    public  int pontintLength ;
    Pattern p;

    public EditInputFilter(double maxValue , int pontintLength){
        p = Pattern.compile("[0-9]*");
        this.maxValue = maxValue;
        this.pontintLength = pontintLength;
    }


    @Override
    public CharSequence filter(CharSequence src, int start, int end,
                               Spanned dest, int dstart, int dend) {
        String oldtext =  dest.toString();
        System.out.println(oldtext);
        if ("".equals(src.toString())) {
            return null;
        }

        Matcher m = p.matcher(src);
        if(oldtext.contains(".")){
            if(!m.matches()){
                return null;
            }
        }else{
            if(!m.matches() && !src.equals(".") ){
                return null;
            }
        }

            if(!src.toString().equals("")){
            double dold = Double.parseDouble(oldtext+src.toString());
            BigDecimal a = new BigDecimal(dold);
            BigDecimal b = new BigDecimal(maxValue);

            if(a.compareTo(b) >0){

                return dest.subSequence(dstart, dend);
            }else if(a.compareTo(b) == 0){
                if(src.toString().equals(".")){

                    return dest.subSequence(dstart, dend);
                }
            }
        }


        if(oldtext.contains(".")){
            int index = oldtext.indexOf(".");
            int len = dend - index;
            if(len > pontintLength){
                CharSequence newText = dest.subSequence(dstart, dend);
                return newText;
            }
        }

        return dest.subSequence(dstart, dend) +src.toString();
    }
}
