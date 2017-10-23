package http.common.xc.com.myhttplibrary.frame;

import android.app.Application;
import android.content.Context;


/**
 * @author Wen
 * @version 1.0
 * @date 2017/6/8 11:35
 * @describe
 */
public class MyApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        //异常捕捉处理
//        DefaultExceptionHandler.getInstance().setCustomCrashHanler(getApplicationContext());
//
//        // 基础控件换肤初始化
//        SkinCompatManager.withoutActivity(this).loadSkin();
//
//        Logger.init();
//        SpUtils.getUserData();
    }
}
