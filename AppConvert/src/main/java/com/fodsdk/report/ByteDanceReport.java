package com.fodsdk.report;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.bytedance.ads.convert.BDConvert;
import com.bytedance.ads.convert.event.ConvertReportHelper;
import com.bytedance.hume.readapk.HumeSDK;
import com.fodsdk.core.FodConstants;
import com.fodsdk.core.FodSDK;
import com.fodsdk.utils.LogUtil;

import java.util.Map;
import java.util.Objects;

public class ByteDanceReport implements IReport {
    @Override
    public void onApplication(Context context) {
        LogUtil.d("ByteDanceReport onApplication");
    }

    @Override
    public void onCreate(Activity activity) {
        LogUtil.d("ByteDanceReport onCreate");
    }

    @Override
    public void onStart(Activity activity) {
        LogUtil.d("ByteDanceReport onStart");
    }

    @Override
    public void onResume(Activity activity) {
        LogUtil.d("ByteDanceReport onResume");
    }

    @Override
    public void onPause(Activity activity) {
        LogUtil.d("ByteDanceReport onPause");
    }

    @Override
    public void onStop(Activity activity) {
        LogUtil.d("ByteDanceReport onStop");
    }

    @Override
    public void onDestroy(Activity activity) {
        LogUtil.d("ByteDanceReport onDestroy");
    }

    @Override
    public void onRestart(Activity activity) {
        LogUtil.d("ByteDanceReport onRestart");
    }

    @Override
    public void onConfigReady(Activity activity) {
        LogUtil.d("ByteDanceReport onConfigReady");
        String channel = HumeSDK.getChannel(activity);
        String version = HumeSDK.getVersion();
        LogUtil.d("HumeSDK channel = " + channel + " version = " + version);
        if (!TextUtils.isEmpty(channel) && channel.contains("_")) {
            String[] split = channel.split("_");
            try {
                if (split.length >= 3) {    // 格式：adid_cid_ptid
                    FodSDK.get().config.setAdId(split[0]);
                    FodSDK.get().config.setCId(split[1]);
                    FodSDK.get().config.setPtId(split[2]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onInit(Activity activity) {
        LogUtil.d("ByteDanceReport onInit");
        BDConvert.INSTANCE.init(activity, activity);
    }

    @Override
    public void onRegisterEvent(Context context, Map<String, String> map) {
        LogUtil.d("ByteDanceReport onRegisterEvent");
        ConvertReportHelper.onEventRegister(Objects.requireNonNull(map.get("type")), true);
    }

    @Override
    public void onPayEvent(Context context, Map<String, String> map) {
        LogUtil.d("ByteDanceReport onPayEvent");
        ConvertReportHelper.onEventPurchase(
                "default",
                Objects.requireNonNull(map.get(FodConstants.PAY.GOODS_NAME)),
                Objects.requireNonNull(map.get(FodConstants.PAY.GOODS_ID)),
                Integer.parseInt(Objects.requireNonNull(map.get(FodConstants.PAY.GOODS_COUNT))),
                Objects.requireNonNull(map.get(FodConstants.PAY.PAY_TYPE)),
                "¥",
                true,
                Integer.parseInt(Objects.requireNonNull(map.get(FodConstants.PAY.GOODS_PRICE)))
        );
    }

    @Override
    public void onCustomEvent(Context context, Map<String, String> map) {
        LogUtil.d("ByteDanceReport onCustomEvent");
    }
}
