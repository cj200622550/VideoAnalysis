package com.cj.videoanalysis.tool;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

/**
 * 作者：陈骏
 * 创建时间：2018/1/12. 11:06
 * QQ：200622550
 * 博客：https://www.jianshu.com/u/c5ada9939f6d
 * 个人网站：www.coocj.top
 * 作用：
 */

public class AsyncHttpUrl {
    public static void postDouYin(Context context, AsyncHttpClient client, String Cookie, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Host", "service.iiilab.com");
        client.addHeader("Origin", "http://douyin.iiilab.com");
        client.addHeader("Cookie", "PHPSESSIID=" + Cookie + "; _ga=GA1.2.2059007116.1511055409; _gid=GA1.2.185879760.1515572926; _gat=1");
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void postHuoShan(Context context, AsyncHttpClient client, String Cookie, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Host", "service.iiilab.com");
        client.addHeader("Origin", "http://huoshan.iiilab.com");
        client.addHeader("Cookie", "PHPSESSIID=" + Cookie + "; _ga=GA1.2.2059007116.1511055409; _gid=GA1.2.185879760.1515572926; _gat=1");
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void postKuaiShou(Context context, AsyncHttpClient client, String Cookie, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Host", "service.iiilab.com");
        client.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        client.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        client.addHeader("Origin", "http://kuaishou.iiilab.com");
        client.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        client.addHeader("Cookie", "PHPSESSIID=" + Cookie + ";_ga=GA1.2.2059007116.1511055409; _gid=GA1.2.107557855.1516323202; _gat=1");
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void get(Context context, AsyncHttpClient client, String url, RequestParams params, AsyncHttpResponseHandler responseHand) {
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        client.setCookieStore(cookieStore);
        client.get(url, params, responseHand);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return relativeUrl;
    }


}
