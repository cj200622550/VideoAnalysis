package com.cj.videoanalysis.tool;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.Header;

/**
 * 作者：陈骏
 * 创建时间：2018/1/12. 9:06
 * QQ：200622550
 * 博客：https://www.jianshu.com/u/c5ada9939f6d
 * 个人网站：www.coocj.top
 * 作用：文件下载上传工具
 */

public class AsyncHttpUtil {
    public static final String DOUYIN = "douyin";
    public static final String HUOSHAN = "huoshan";
    public static final String KUAISHOU = "kuaishou";
    public static final String MIAOPAI = "miaopai";
    public static final String MOMO = "momo";
    public static final String MUSE = "muse";
    public static final String TOUTIAO = "toutiao";
    public static final String XIAOYIN = "xiaoyin";
    public static final String YINGKE = "yingke";
    private static boolean isSuccess = false;

    /**
     * 异步上传文件
     *
     * @param path :文件路径
     * @param url  :上传的url
     */
    public static boolean uploadFile(final Context context, String path, String url, String filename,
                                     final ProgressDialog progress) throws Exception {
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("uploadfile", file);
            params.put("newFilename", filename);

            // 设置进度条最大值
            progress.setMax(Integer.parseInt(file.length() + ""));

            // 上传文件
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    // 上传成功后要做的工作
                    progress.dismiss();
                    Toast.makeText(context, "上传成功", Toast.LENGTH_LONG).show();
                    // progress.setProgress(0);
                    isSuccess = true;
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    progress.dismiss();
                    Toast.makeText(context, "上传失败", Toast.LENGTH_LONG).show();
                    isSuccess = false;
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                    // 上传进度显示
                    progress.setProgress(count);
                    super.onProgress(bytesWritten, totalSize);
                }

                @Override
                public void onRetry(int retryNo) {
                    super.onRetry(retryNo);
                    // 返回重试次数
                }
            });

        } else {
            progress.dismiss();
            Toast.makeText(context, "文件不存在", Toast.LENGTH_LONG).show();
        }
        return isSuccess;

    }

    /**
     * @param mContext
     * @param url      要下载的文件URL
     * @param view     下载按钮
     * @param type     文件类型
     * @throws Exception
     */
    public static void downloadFile(final Context mContext, String url, final Button view, final String type) throws Exception {

        AsyncHttpClient client = new AsyncHttpClient();
        // 指定文件类型
        String[] fileTypes = new String[]{"image/png", "image/jpeg", "video/mp4"};
        SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
        // 获取二进制数据如图片和其他文件
        HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

        DefaultHttpClient clients = new DefaultHttpClient();

        SchemeRegistry registry = new SchemeRegistry();
        SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
        socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
        registry.register(new Scheme("https", socketFactory, 443));
        SingleClientConnManager mgr = new SingleClientConnManager(clients.getParams(), registry);
        DefaultHttpClient httpClient = new DefaultHttpClient(mgr, clients.getParams());

// Set verifier
        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
        client.get(url, new BinaryHttpResponseHandler(fileTypes) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                String tempPath = "";
                File mkd1 = null;
                try {
                    tempPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "cj" + File.separator + type + File.separator + System.currentTimeMillis() + ".mp4";
                    mkd1 = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "cj" + File.separator + type + File.separator);
                    if (!mkd1.exists()) mkd1.mkdirs();
                    File file = new File(tempPath);
                    // 若存在则删除
                    if (file.exists()) file.delete();
                    // 创建文件
                    file.createNewFile();
                    OutputStream stream = new FileOutputStream(file);
                    stream.write(binaryData);
                    // 关闭
                    stream.close();
                    view.setText("下载");
                    Toast.makeText(mContext, "下载成功\n" + tempPath, Toast.LENGTH_LONG).show();
                    view.setClickable(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                Toast.makeText(mContext, "下载失败" + error.getMessage(), Toast.LENGTH_LONG).show();
                view.setClickable(true);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                view.setClickable(false);
                int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                Log.e("下载 Progress>>>>>", bytesWritten + " / " + totalSize + "-----" + count);
                view.setText(bytesWritten + " / " + totalSize);
                super.onProgress(bytesWritten, totalSize);
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
                // 返回重试次数
            }

        });
    }

//    private  static MySSLSocketFactory getSocketFactory(Context context) {
//        // TODO Auto-generated method stub
//        MySSLSocketFactory sslFactory = null;
//        try {
//            KeyStore keyStore = KeyStore.getInstance("PKCS12");
//            InputStream instream = context.getResources().openRawResource(R.raw.server);//后台拿到的.p12证书
//            keyStore.load(instream, "后台拿到的.p12证书密码".toCharArray());
//            sslFactory = new MySSLSocketFactory(keyStore);
//        } catch (KeyStoreException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (NoSuchAlgorithmException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (CertificateException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (UnrecoverableKeyException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (KeyManagementException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return sslFactory;
//    }
}
