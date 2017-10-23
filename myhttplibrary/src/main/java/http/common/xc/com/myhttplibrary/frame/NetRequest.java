package http.common.xc.com.myhttplibrary.frame;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络请求构建
 */
public class NetRequest {

    private static NetRequest netRequest = null;
    private OkHttpClient okHttpClient;
    private Context context = MyApplication.getContext();

    private NetRequest() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(15, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(15, TimeUnit.SECONDS)//设置写入超时时间
//                .cookieJar(createCookieJar())
                .build();
    }

    /**
     * 创建持久化的Cookies的处理
     *
     * @return CookieJar
     */
//    private CookieJar createCookieJar() {
//        SharedPrefsCookiePersistor sharedPrefsCookiePersistor = new SharedPrefsCookiePersistor(context);
//        List<Cookie> cookies = sharedPrefsCookiePersistor.loadAll();
//        for (int i = 0; i < cookies.size(); i++) {
//            Cookie cookie = cookies.get(i);
//            Log.i(this.toString(), "本地保存的cookie:" + cookie.toString());
//        }
//        ClearableCookieJar cookieJar = new CustomPersistentCookieJar(new SetCookieCache(), sharedPrefsCookiePersistor);
//        return cookieJar;
//    }

    /**
     * 数据请求单例
     *
     * @return
     */
    public static NetRequest getInstance() {
        if (netRequest == null) {
            synchronized (NetRequest.class) {
                if (netRequest == null) {
                    netRequest = new NetRequest();
                }
            }
        }
        return netRequest;
    }

    /**
     * post 请求
     *
     * @param requestTag
     * @param activity
     * @param urlStr
     * @param paramsMap
     * @param requestCallback
     */
    public void postRequestAsync(final String requestTag, final Activity activity, final String urlStr, Map<String, String> paramsMap, final RequestCallbackNet requestCallback) {
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(urlStr)
                .post(postRequestParams(paramsMap))
                .tag(activity)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                RequestResultUtil.requestHandleFailureResult(activity, requestTag, urlStr, call, e, e.toString(), requestCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                RequestResultUtil.requestHandleSuccessResult(activity, requestTag, urlStr, call, response, requestCallback);
            }
        });
    }

    /**
     * get 请求
     *
     * @param requestTag
     * @param activity
     * @param urlStr
     * @param paramsMap
     * @param requestCallback
     */
    public void getRequestAsync(final String requestTag, final Activity activity, final String urlStr, Map<String, String> paramsMap, final RequestCallbackNet requestCallback) {
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(urlStr + getRequestParams(paramsMap))
                .get()
                .tag(activity)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                RequestResultUtil.requestHandleFailureResult(activity, requestTag, urlStr, call, e, e.toString(), requestCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                RequestResultUtil.requestHandleSuccessResult(activity, requestTag, urlStr, call, response, requestCallback);
            }
        });
    }

    /**
     * 得到相应参数转成框架相应RequestBody
     *
     * @param paramsMap
     * @return
     */
    private static RequestBody postRequestParams(Map<String, String> paramsMap) {
        RequestBody requestBody = null;
        if (paramsMap != null) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                if (entry.getValue() == null) {
                    entry.setValue("");
                }
                builder.add(entry.getKey(), entry.getValue());
            }
            requestBody = builder.build();
        } else {
            requestBody = okhttp3.internal.Util.EMPTY_REQUEST;
        }
        return requestBody;
    }

    /**
     * 从map得到相应参数转成字符串
     *
     * @param paramsMap
     * @return
     */
    @Nullable
    private static String getRequestParams(Map<String, String> paramsMap) {
        StringBuilder tempParams = new StringBuilder();
        String params = "";
        try {
            if (paramsMap != null) {
                int pos = 0;
                for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                    /*
                    if (pos > 0) {
                        tempParams.append("&");
                    }
                    tempParams.append(String.format("%s=%s", entry, URLEncoder.encode(entry.getValue(), "utf-8")));
                    pos++;
                    */
                    params = params + "&" + entry.getKey() + "=" + entry.getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(params)) {
            params = params.replaceFirst("&", "?");
        }
        return params;
    }

    /**
     * 根据tag取消指定的请求
     *
     * @param tag 当前activity对象
     */
    public void cancelTag(Object tag) {
        for (Call call : okHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : okHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /**
     * 取消所有请求
     */
    public void cancelAllRequest() {
        okHttpClient.dispatcher().cancelAll();
    }

}
