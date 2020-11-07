package com.power.home.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.power.home.common.Constant;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.ui.activity.PaySuccessActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	

    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			if(resp.errCode==0){
				//Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(this, PaySuccessActivity.class);
				startActivity(intent);
				EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.FINISH_PAY_WAY,"" ));
			}
			else {
				Toast.makeText(this, "支付取消", Toast.LENGTH_LONG).show();

			}
			finish();
		}
	}
}