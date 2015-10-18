package cn.andthink.actionlibrary.protocol;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.util.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;

import cn.andthink.actionlibrary.utils.FileUtil;
import cn.andthink.actionlibrary.utils.MyHttpUtils;
import cn.andthink.actionlibrary.utils.ToolUtil;

/**
 * Created by wuhaiyang on 2015/7/18.
 * 如果不使用第三方网络请求框架的话，自己创建线程池去处理
 */
public class BaseProtocol {

    protected static final String LOAD_READ = "read";
    protected static final String LOAD_WRITE = "write";

    protected boolean isFirstLoad = true;//标志用户是否是第一次加载数据
    protected OnDateCallback mListener;
    protected MyHttpUtils mHttpUtils;

    public BaseProtocol(OnDateCallback mListener) {
        this.mListener = mListener;
        mHttpUtils = MyHttpUtils.getInstance();
    }
    /**
     * 该方法针对的是 进入页面第一次执行的方法
     * @param params
     */
    public void loadData(RequestParams params) {
        //先判断网络是否连接
        if (ToolUtil.isNetAvailable()) {
            //网络可用 从服务器上获取最新数据予以显示
            loadServer(params);
        } else {
            //网络不可用 先判断本地是否存在 存在则显示 ，不存在 测提示用户
            if (isFirstLoad) {
                // 每次都需要读取或者写入本地的时候 都应该重新创建一个task 去执行耗时任务 否则会出现异常：the task has already been executed (a task can be executed only once)
                new MyAsyncTask().execute(LOAD_READ, getKey());
            } else {
                // 请连接网络后再试
            }
        }
    }

    protected void promptTips() {

    }

    protected String getKey() {
        return "default";
    }

    /**
     * 请求服务器 并且缓存最新数据到本地
     *
     * @param params
     */
    protected void loadServer(RequestParams params) {
        //使用utils 请求服务器 在成功的回调接口里面保存到缓存中
    }

    private class MyAsyncTask extends AsyncTask<String, Void, String> {
        private String type;

        @Override
        protected String doInBackground(String... params) {
//            cancel(true); //取消上次未执行完成的任务
            type = params[0];
            String key = params[1];
            if (type.equals("r")) {
                return readLocal(key);
            } else {
                writeLocal(key, params[2]);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (type == LOAD_READ) {
                if (!TextUtils.isEmpty(s)) {
                    isFirstLoad = false;
                    mListener.onGetData(s);
                } else {
                    //本地没有缓存
                    mListener.onGetData(null);// 为获取到缓存信息
                }
            } else {
                //写入本地成功
            }
            super.onPostExecute(s);
        }
    }

    protected synchronized void writeLocal(String key, String json) {
        File dir = FileUtil.getCacheDir();
        File file = new File(dir, key);
        //写入
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(json);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bw);
            IOUtils.closeQuietly(fw);
        }
    }

    protected synchronized String readLocal(String key) {
        File dir = FileUtil.getCacheDir();
        File file = new File(dir, key);
        //读取
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String str = null;
            StringWriter sw = new StringWriter();
            while ((str = br.readLine()) != null) {
                sw.write(str);
            }
            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(fr);
        }
    }
    public interface OnDateCallback {
        public void onGetData(Object object);
    }

    public void releaseMemroy(){
        
    }
}
