package http.common.xc.com.myhttplibrary.frame;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Wen
 * @version 1.0
 * @date 2016/8/11 15:20
 * @describe 网络请求结果处理工具
 */
public class RequestResultUtil {


    /**
     * 处理成功返回的数据
     *
     * @param activity
     * @param requestTag
     * @param urlStr
     * @param call
     * @param response
     * @param requestCallback
     */
    public static void requestHandleSuccessResult(final Activity activity, String requestTag, String urlStr, Call call, final Response response, final RequestCallbackNet requestCallback) {
        try {
            ResponseBody body = response.body();

            if ("image".equals(body.contentType().type())) {
                Log.i(requestTag, "请求url: " + urlStr + " type: " + body.contentType().type());
                //如果数据是图片流则传递字节数组
                final byte[] bytes = body.bytes();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestCallback.onSuccess(bytes);
                    }
                });
            } else {
                final String content = body.string();
                Headers headers = response.headers();
                final Map<String, List<String>> headersMap = headers.toMultimap();
                //MyLog.i(requestTag, "url: " + urlStr + " headers: " + headers.toString());

                Log.i(requestTag, "请求url: " + urlStr + " onSuccess: " + content);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONObject jobj = (JSONObject) JSONObject.parse(content);
                            String msg = jobj.getString("msg");
                            String code = jobj.getString("code");
                            Object object = JSON.parseObject(content, requestCallback.getClazz());

                            requestCallback.onSuccess(content, jobj, object, code, msg);
                            requestCallback.onSuccess(content, jobj, object, code, msg, headersMap);

                        } catch (Exception e) {
                            e.printStackTrace();
//                            ToastUtil.getShortToastByString(activity, "数据加载异常！");
                        }
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理错误的返回数据
     *
     * @param activity
     * @param requestTag
     * @param urlStr
     * @param call
     * @param error
     * @param msg
     * @param requestCallback
     */
    public static void requestHandleFailureResult(final Activity activity, String requestTag, String urlStr, Call call, final Exception error, final String msg, final RequestCallbackNet requestCallback) {
        try {
            Log.e(requestTag, "请求url: " + urlStr + " onFailure: " + error + " ");

            //自己主动取消的错误的 java.net.SocketException: Socket closed
            String errorMsg = "";
            if (error instanceof ConnectException) {
                errorMsg = "网路连接异常";
            } else if (error instanceof SocketTimeoutException) {
                errorMsg = "请求超时";
            } else if (error instanceof SocketException) {
                errorMsg = "请求取消";
                return;
            } else {
                errorMsg = "请求异常";
            }

//            final int code = call.execute().code();
//            final String message = call.execute().message();
            final String finalErrorMsg = errorMsg;
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
//                        ToastUtil.getShortToastByString(activity, finalErrorMsg);
                        Toast.makeText(activity,finalErrorMsg,Toast.LENGTH_SHORT).show();
                        requestCallback.onFailure(error, finalErrorMsg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
