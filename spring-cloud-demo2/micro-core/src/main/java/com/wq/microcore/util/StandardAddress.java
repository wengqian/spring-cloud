package com.wq.microcore.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

/**
 * Created by tgram-888 on 2017/8/29.
 */
public class StandardAddress {
    public static String  getRequest(String url) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2500);
            HttpGet httpgets = new HttpGet(url);
            RequestConfig requestconfig = RequestConfig.custom().setConnectionRequestTimeout(3000).setSocketTimeout(3000).setConnectTimeout(3000).build();
            httpgets.setConfig(requestconfig);
            HttpResponse response = httpclient.execute(httpgets);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity());
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
