package http.common.xc.com.myhttplibrary.frame;

import android.app.Activity;

import java.util.Map;

/**
 * @author Wen
 * @version 1.0
 * @date 2016/8/11 14:22
 * @describe 网络请求工具
 */
public class NetUtil {

    private void NetUtil() {

    }

    /**
     * 无请求参数的post请求
     *
     * @param requestTag      日志tag信息，需传本类的toString信息
     * @param activity        上下文
     * @param urlStr          请求地址
     * @param requestCallback 回调方法
     */
    public static void post(String requestTag, Activity activity, String urlStr, RequestCallbackNet requestCallback) {
        post(requestTag, activity, urlStr, null, requestCallback);
    }

    /**
     * 无请求参数的get请求
     *
     * @param requestTag      日志tag信息，需传本类的toString信息
     * @param activity        上下文
     * @param urlStr          请求地址
     * @param requestCallback 回调方法
     */
    public static void get(String requestTag, Activity activity, String urlStr, RequestCallbackNet requestCallback) {
        get(requestTag, activity, urlStr, null, requestCallback);
    }

    /**
     * post请求
     *
     * @param requestTag      日志tag信息，需传本类的toString信息
     * @param activity        上下文
     * @param urlStr          请求地址
     * @param paramsMap       请求参数
     * @param requestCallback 回调方法
     */
    public static void post(String requestTag, Activity activity, String urlStr, Map<String, String> paramsMap, RequestCallbackNet requestCallback) {
        NetRequest.getInstance().postRequestAsync(requestTag, activity, urlStr, paramsMap, requestCallback);
    }

    /**
     * get 请求
     *
     * @param requestTag      日志tag信息，需传本类的toString信息
     * @param activity        上下文
     * @param urlStr          请求地址
     * @param paramsMap       请求参数
     * @param requestCallback 回调方法
     */
    public static void get(String requestTag, Activity activity, String urlStr, Map<String, String> paramsMap, RequestCallbackNet requestCallback) {
        NetRequest.getInstance().getRequestAsync(requestTag, activity, urlStr, paramsMap, requestCallback);
    }

    /**
     * 根据tag取消指定的请求
     *
     * @param tag 当前activity对象
     */
    public static void cancelTag(Object tag) {
        NetRequest.getInstance().cancelTag(tag);
    }

    /**
     * 取消所有请求
     */
    public static void cancelAllRequest() {
        NetRequest.getInstance().cancelAllRequest();
    }

}
