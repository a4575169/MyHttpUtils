package http.common.xc.com.myhttplibrary.frame;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author Wen
 * @version 1.0
 * @date 2017/6/14 15:23
 * @describe 网络结果回调接口处理
 */
public abstract class RequestCallbackNet<T> {

    private Class<T> clazz = null;

    /**
     * @param clazz 需要自动生成bean对象的class文件
     */
    public RequestCallbackNet(Class<T> clazz) {
        this.clazz = clazz;
        //此方法可以直接得到泛型的class对象
        //clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public RequestCallbackNet() {

    }

    /**
     * 得到传递进来的字节码文件
     *
     * @return
     */
    public Class<T> getClazz() {
        return clazz;
    }

    /**
     * 请求成功时的回调
     *
     * @param result  未解析的json文本
     * @param jsonObj 返回回来的jsonObj实例
     * @param t       已经转换好的bean实例
     * @param code    状态码
     * @param msg     状态信息
     */
    public abstract void onSuccess(String result, JSONObject jsonObj, T t, String code, String msg);
    // void onSuccess(String result, JSONObject jsonObj, Header[] allHeaders, String code, String msg);

    /**
     * 带请求头的回调
     *
     * @param result     未解析的json文本
     * @param jsonObj    返回回来的jsonObj实例
     * @param t          已经转换好的bean实例
     * @param code       状态码
     * @param msg        状态信息
     * @param headersMap 请求头的map信息
     */
    public void onSuccess(String result, JSONObject jsonObj, T t, String code, String msg, Map<String, List<String>> headersMap) {
    }

    /**
     * 回调图片字节流
     *
     * @param bytes 图片字节流
     */
    public void onSuccess(byte[] bytes) {
    }

    /**
     * 请求失败时的回调
     *
     * @param error 异常类
     * @param msg   异常信息
     */
    public abstract void onFailure(Exception error, String msg);
}
