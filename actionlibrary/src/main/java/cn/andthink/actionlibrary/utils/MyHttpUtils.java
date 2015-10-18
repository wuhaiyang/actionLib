package cn.andthink.actionlibrary.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by wuhaiyang on 2015/7/19.
 */
public class MyHttpUtils {
    private static MyHttpUtils mInstance;
    private static HttpUtils httpUtils;

    private MyHttpUtils() {
        httpUtils = new HttpUtils();
    }

    public void cancelRequest(){
        if (null == httpUtils) {
            return;
        }
        httpUtils.getHttpClient().getConnectionManager().shutdown();
    }
    public static MyHttpUtils getInstance() {
        if (null == mInstance)
            mInstance = new MyHttpUtils();
        return mInstance;
    }

    public void get(String url, RequestCallBack callBack) {
        httpUtils.send(HttpRequest.HttpMethod.GET, url, callBack);
    }

    public void get(String url, RequestParams params, RequestCallBack callBack) {
        httpUtils.send(HttpRequest.HttpMethod.GET, url, params, callBack);
    }

    public void post(String url, RequestCallBack callBack) {
        httpUtils.send(HttpRequest.HttpMethod.POST, url, callBack);
    }

    public void post(String url, RequestParams params, RequestCallBack callBack) {
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, callBack);
    }


}
