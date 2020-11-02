package com.power.home.wxapi;

import android.graphics.Bitmap;
import android.view.View;

import com.bumptech.glide.Glide;
import com.power.home.common.Constant;
import com.power.home.common.util.ToastUtils;
import com.power.home.common.util.UIUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.concurrent.ExecutionException;


/**
 * Created by XWL on 2020/3/18.
 * Description:
 */
public class WxShare {
    /**
     *
     * @param webpageUrl 分享的网址
     * @param title     标题
     * @param description  描述
     * @param img       略缩图
     * @param scene     1好友  2朋友圈
     */
    public void WxShareUrl(String webpageUrl,String title,String description,Object img ,int scene) {

        IWXAPI api = WXAPIFactory.createWXAPI(UIUtil.getContext(), Constant.APP_ID, true);
        api.registerApp(Constant.APP_ID);
        if (!api.isWXAppInstalled()) {
            ToastUtils.shortShow("请先安装微信");
            return;
        }

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = webpageUrl;
        final WXMediaMessage message = new WXMediaMessage(webpage);
        message.title = title;
        message.description = description;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap = Glide.with(UIUtil.getContext()).asBitmap().load(img).into(100, 100).get();
                    message.setThumbImage(bitmap);

                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    // transaction字段用于唯一标识一个请求
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = message;
                    /**
                     * 分享到对话:
                     * SendMessageToWX.Req.WXSceneSession
                     * 分享到朋友圈:
                     * SendMessageToWX.Req.WXSceneTimeline
                     */
                    //分享到微信好友
                    switch (scene){
                        case 1://微信好友
                            req.scene =  SendMessageToWX.Req.WXSceneSession;
                            break;
                        case 2://朋友圈
                            req.scene =  SendMessageToWX.Req.WXSceneTimeline;
                            break;
                    }
                    api.sendReq(req);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     *
     * @param bitmap  要生成海报的bitmap
     * @param scene  1好友  2朋友圈
     */
    public void WxSharePic(Bitmap bitmap ,int scene){
        IWXAPI api = WXAPIFactory.createWXAPI(UIUtil.getContext(), Constant.APP_ID, true);
        api.registerApp(Constant.APP_ID);
        if (!api.isWXAppInstalled()) {
            ToastUtils.shortShow("请先安装微信");
            return;
        }



        //初始化 WXImageObject 和 WXMediaMessage 对象
        WXImageObject imgObj = new WXImageObject(bitmap);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        //设置缩略图
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 100, 160, true);
        bitmap.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        //分享到微信好友
        switch (scene) {
            case 1://微信好友
                req.scene = SendMessageToWX.Req.WXSceneSession;
                break;
            case 2://朋友圈
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
        }
        //调用api接口，发送数据到微信
        api.sendReq(req);

    }
}
