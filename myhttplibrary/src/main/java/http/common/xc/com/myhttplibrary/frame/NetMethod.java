package http.common.xc.com.myhttplibrary.frame;

/**
 * @author Wen
 * @version 1.0
 * @date 2016/8/19 16:23
 * @describe 请求的公共方法
 */
public class NetMethod {

    /**
     * 得到验证码
     *
     * @param context         当前activity界面上下文,请不要使用Application的上下文
     * @param requestCallback 回调结果函数
     */
//    public static void getVerifyCode(Context context, String phone, String smsSrc, final RequestCallbackNet requestCallback) {
//        Map<String, String> map = new HashMap<>();
//        map.put("uid", StateData.uid);
//        map.put("uphone", phone);
//        map.put("type", "0");//0 发送，1验证
//        map.put("md5str", Md5Algorithm.MD5(StateData.pkey + StateData.uid + phone));
//        //smsSrc: 1:注册、2:提现、3:忘记登录密码、4:实名认证 绑定银行、5:登录密码设置、6:支付密码设置、忘记支付密码7:投资、 8:充值
//        map.put("smsSrc", smsSrc);
//        NetUtil.post(context.toString(), (Activity) context, context.getString(R.string.net_normal) + context.getString(R.string.sendmsg), map, new RequestCallbackNet() {
//
//            @Override
//            public void onSuccess(String result, JSONObject jsonObj, Object o, String code, String msg) {
//                requestCallback.onSuccess(result, jsonObj, o, code, msg);
//            }
//
//            @Override
//            public void onFailure(Exception error, String msg) {
//                requestCallback.onFailure(error, msg);
//            }
//        });
//    }

    /**
     * 退出登录请求
     *
     * @param context         当前activity界面上下文,请不要使用Application的上下文
     * @param requestCallback 回调结果函数
     */
//    public static void getNetLogOut(Context context, final NetUtil.RequestCallback requestCallback) {
//        NetUtil.post(context.toString(), context, context.getString(R.string.net_normal_new) + context.getString(R.string.logout), new NetUtil.RequestCallback() {
//            @Override
//            public void onSuccess(String result, JSONObject jsonObj, Header[] allHeaders, String code, String msg) {
//                requestCallback.onSuccess(result, jsonObj, allHeaders, code, msg);
//            }
//
//            @Override
//            public void onFailure(Exception error, String msg) {
//                requestCallback.onFailure(error, msg);
//            }
//        });
//    }


    /**
     * 登录请求
     *
     * @param context         当前activity界面上下文,请不要使用Application的上下文
     * @param phone           要注册的手机号
     * @param password        注册的密码
     * @param requestCallback 回调结果函数
     */
//    public static void getNetLogin(Context context, String phone, String password, final NetUtil.RequestCallback requestCallback) {
//        Map<String, String> map = new HashMap<>();
//        map.put("uphone", phone);
//        map.put("upwd", password);
//        map.put("md5str", Md5Algorithm.MD5(StateData.PKEY + phone));
//        NetUtil.post(context.toString(), context, context.getString(R.string.net_normal_new) + context.getString(R.string.ulog), map, new NetUtil.RequestCallback() {
//            @Override
//            public void onSuccess(String result, JSONObject jsonObj, Header[] allHeaders, String code, String msg) {
//                requestCallback.onSuccess(result, jsonObj, allHeaders, code, msg);
//            }
//
//            @Override
//            public void onFailure(Exception error, String msg) {
//                requestCallback.onFailure(error, msg);
//            }
//        });
//    }

    /**
     * 发送channelId给服务器
     *
     * @param context
     * @param channelId
     */
//    public static void sendChannelId(Context context, String channelId, String phone) {
//        Map<String, String> map = new HashMap<>();
//        map.put("channels", channelId);
//        map.put("phones", phone);
//        NetUtil.post(context.toString(), context, context.getString(R.string.init_channelid), map, new NetUtil.RequestCallback() {
//            @Override
//            public void onSuccess(String result, JSONObject jsonObj, Header[] allHeaders, String code, String msg) {
//
//            }
//
//            @Override
//            public void onFailure(Exception error, String msg) {
//
//            }
//        });
//    }
}
