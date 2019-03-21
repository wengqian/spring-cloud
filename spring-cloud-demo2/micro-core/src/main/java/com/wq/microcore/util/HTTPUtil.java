package com.wq.microcore.util;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;


/**
 * TODO
 * @Version 1.0
 */
public class HTTPUtil {
    /** UTF-8 */
    private static final String UTF_8 = "UTF-8";
    /** 日志记录tag */
    private static final String TAG = "HttpClients";

    /** 用户host */
    private static String proxyHost = "";
    /** 用户端口 */
    private static int proxyPort = 80;
    /** 是否使用用户端口 */
    private static boolean useProxy = false;

    /** 连接超时 */
    private static final int TIMEOUT_CONNECTION = 60000;
    /** 读取超时 */
    private static final int TIMEOUT_SOCKET = 60000;
    /** 重试3次 */
    private static final int RETRY_TIME = 1;//重复链接

    public String doHtmlPost(HttpClient httpClient, HttpPost httpPost)
    {
        return doHtmlPost(httpClient,httpPost,"UTF-8");
    }
    /**
     * @param url
     * @param requestData
     * @return
     */
    public String doHtmlPost(HttpClient httpClient,HttpPost httpPost,String code )
    {
        String responseBody = null;

        int statusCode = -1;

        try {

            HttpResponse httpResponse = httpClient.execute(httpPost);
            Header lastHeader = httpResponse.getLastHeader("Set-Cookie");
            if(null != lastHeader)
            {
                httpPost.setHeader("cookie", lastHeader.getValue());
            }
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("HTTP" + "  " + "HttpMethod failed: " + httpResponse.getStatusLine());
            }
            InputStream is = httpResponse.getEntity().getContent();
            responseBody = getStreamAsString(is, code);

        } catch (Exception e) {
            // 发生网络异常
            e.printStackTrace();
        } finally {
//          httpClient.getConnectionManager().shutdown();
//          httpClient = null;
        }

        return responseBody;
    }
    /**
     *
     * 发起网络请求
     *
     * @param url
     *            URL
     * @param requestData
     *            requestData
     * @return INPUTSTREAM
     * @throws AppException
     * 默认使用UTF-8的编码格式
     */
    public static String doPost(String url, String requestData) throws Exception {
        return doPost(url,requestData,"UTF-8");
    }
    /**
     *
     * 发起网络请求
     *
     * @param url
     *            URL
     * @param requestData
     *            requestData
     * @return INPUTSTREAM
     * @throws AppException
     */
    public static String doPost(String url, String requestData,String code) throws Exception {
        String responseBody = null;
        HttpPost httpPost = null;
        HttpClient httpClient = null;
        int statusCode = -1;
        int time = 0;
        do {
            try {
                httpPost = new HttpPost(url);
                httpClient = getHttpClient();
                // 设置HTTP POST请求参数必须用NameValuePair对象
                StringEntity entity = new StringEntity(requestData, "UTF-8");// 解决中文乱码问题
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");

                //设置匹配超时的时间
//    			RequestConfig requestconfig =RequestConfig.custom().setConnectionRequestTimeout(3000).setSocketTimeout(3000).setConnectTimeout(3000).build();
//    			httpPost.setConfig( requestconfig);
                //设置匹配超时的时间

                // 设置HTTP POST请求参数
                httpPost.setEntity(entity);
                HttpResponse httpResponse = httpClient.execute(httpPost);
               /* statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    System.out.println("HTTP" + "  " + "HttpMethod failed: " + httpResponse.getStatusLine());
                }*/
                InputStream is = httpResponse.getEntity().getContent();
                responseBody = getStreamAsString(is, code);
                break;
            } catch (UnsupportedEncodingException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生致命的异常，可能是协议不对或者返回的内容有问题
                e.printStackTrace();

            } catch (ClientProtocolException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生致命的异常，可能是协议不对或者返回的内容有问题
                e.printStackTrace();
            } catch (IOException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生网络异常
                e.printStackTrace();
            } catch (Exception e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生网络异常
                e.printStackTrace();
            } finally {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
        } while (time < RETRY_TIME);
        return responseBody;
    }

    /**
     *
     * 将InputStream 转化为String
     *
     * @param stream
     *            inputstream
     * @param charset
     *            字符集
     * @return
     * @throws IOException
     */
    private static String getStreamAsString(InputStream stream, String code) throws IOException {
        String charset = HTTP.UTF_8;
        if("utf-8".equals(code) || "UTF-8".equals(code)){
            charset = "utf-8";
        }else if("utf-16".equals(code) || "UTF-16".equals(code)){
            charset = HTTP.UTF_16;
        }else if("gb2312".equals(code) || "GB2312".equals(code)){
            charset = "gb2312";
        }else if("gbk".equals(code) || "GBK".equals(code)){
            charset = "GBK";
        }else if("utf8".equals(code) || "UTF8".equals(code)){
            charset = "utf8";
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset), 8192);
            StringWriter writer = new StringWriter();

            char[] chars = new char[8192];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    /**
     * 得到httpClient
     *
     * @return
     */
    public HttpClient getHttpClient1() {
        final HttpParams httpParams = new BasicHttpParams();

        if (useProxy) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");
            httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }

        HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_CONNECTION);
        HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_SOCKET);
        HttpClientParams.setRedirecting(httpParams, true);
        final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.14) Gecko/20110218 Firefox/3.6.14";

        HttpProtocolParams.setUserAgent(httpParams, userAgent);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpClientParams.setCookiePolicy(httpParams, CookiePolicy.RFC_2109);

        HttpProtocolParams.setUseExpectContinue(httpParams, false);
        HttpClient client = new DefaultHttpClient(httpParams);

        return client;
    }

    /**
     *
     * 得到httpClient
     *
     * @return
     */
    private static HttpClient getHttpClient() {
        final HttpParams httpParams = new BasicHttpParams();

        if (useProxy) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");
            httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }

        HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_CONNECTION);
        HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_SOCKET);
        HttpClientParams.setRedirecting(httpParams, true);
        final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.14) Gecko/20110218 Firefox/3.6.14";

        HttpProtocolParams.setUserAgent(httpParams, userAgent);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpClientParams.setCookiePolicy(httpParams, CookiePolicy.BROWSER_COMPATIBILITY);
        HttpProtocolParams.setUseExpectContinue(httpParams, false);
        HttpClient client = new DefaultHttpClient(httpParams);

        return client;
    }

    /**
     * 打印返回内容
     * @param response
     * @throws ParseException
     * @throws IOException
     */
    public static void showResponse(String str) throws Exception {
//        Gson gson = new Gson();    
//        Map<String, Object> map = (Map<String, Object>) gson.fromJson(str, Object.class);
//        String value = (String) map.get("data");       
        //String decodeValue =  Des3Request.decode(value);
        //System.out.println(decodeValue);
        //logger.debug(decodeValue);
    }
    /**
     *
     * 发起网络请求
     *
     * @param url
     *            URL
     * @param requestData
     *            requestData
     * @return INPUTSTREAM
     * @throws AppException
     * 默认使用ufy-8的编码格式
     */
    public static String doGet(String url) throws Exception {
        return doGet(url,"UTF-8");
    }
    /**
     *
     * 发起网络请求
     *
     * @param url
     *            URL
     * @param requestData
     *            requestData
     * @return INPUTSTREAM
     * @throws AppException
     * @param code 编码格式
     */
    public static String doGet(String url,String code) throws Exception {
        String responseBody = null;
        HttpGet httpGet = null;
        HttpClient httpClient = null;
        int statusCode = -1;
        int time = 0;
        do {
            try {
                httpGet = new HttpGet(url);
                httpClient = getHttpClient();
                HttpResponse httpResponse = httpClient.execute(httpGet);
                statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    System.out.println("HTTP" + "  " + "HttpMethod failed: " + httpResponse.getStatusLine());
                }
                InputStream is = httpResponse.getEntity().getContent();
                responseBody = getStreamAsString(is, code);

                break;
            } catch (UnsupportedEncodingException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生致命的异常，可能是协议不对或者返回的内容有问题
                e.printStackTrace();

            } catch (ClientProtocolException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生致命的异常，可能是协议不对或者返回的内容有问题
                e.printStackTrace();
            } catch (IOException e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生网络异常
                e.printStackTrace();
            } catch (Exception e) {
                time++;
                if (time < RETRY_TIME) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }
                // 发生网络异常
                e.printStackTrace();
            } finally {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
        } while (time < RETRY_TIME);
        return responseBody;
    }
    /**
     * 数据获取测试
     * */
    public static void main(String []args){
        String paramString ="";
        String resultStr = "";
        try {
            resultStr = HTTPUtil.doGet("http://10.118.128.77:8080/smap/iface/interface?usr=sjqqb&pwd=sjqqb&handle=1&count=10");
            System.out.println(resultStr);
            resultStr="<?xml version='1.0' encoding='GB2312' standalone='no'?>"
                    +"	<SMAPMsg handle='0' type='moResp' ver='1.0'>"
                    +"	<mo>"
                    +"		<content>201609221815,1;断桥,12345;国博,34323</content>"
                    +"		<phone>13867180080</phone>"
                    +"		<linkid>0000000000</linkid>"
                    +"		<serviceid>TZJ4919901</serviceid>"
                    +"		<spnumber>123123</spnumber>"
                    +"		<revtime>2016-09-22 17:56:57.0</revtime>"
                    +"		</mo>"
                    +"	<mo>"
                    +"		<content>201609221830,2;断桥,12345;国博,34323</content>"
                    +"		<phone>13867180080</phone>"
                    +"		<linkid>0000000000</linkid>"
                    +"		<serviceid>TZJ4919901</serviceid>"
                    +"		<spnumber>123123</spnumber>"
                    +"		<revtime>2016-09-22 17:56:57.0</revtime>"
                    +"	</mo>"
                    +"	</SMAPMsg>";
            Document rdocument = XMLUtil.strToXML(resultStr);

            Element SMAPMsgElement = rdocument.getDocumentElement();
//			NodeList SMAPMsgList = rdocument.getElementsByTagName("SMAPMsg");
//			Element SMAPMsgElement = (Element)SMAPMsgList.item(0);
            NodeList molist = SMAPMsgElement.getElementsByTagName("mo");
            for(int i=0;i<molist.getLength();i++){
                Element mo = (Element) molist.item(i);
                Element content= (Element)mo.getElementsByTagName("content").item(0);
                Element phone= (Element)mo.getElementsByTagName("phone").item(0);
                Element linkid= (Element)mo.getElementsByTagName("linkid").item(0);
                Element serviceid= (Element)mo.getElementsByTagName("serviceid").item(0);
                Element spnumber= (Element)mo.getElementsByTagName("spnumber").item(0);
                Element revtime= (Element)mo.getElementsByTagName("revtime").item(0);

                System.out.println(content.getTextContent());
                System.out.println(phone.getTextContent());
                System.out.println(linkid.getTextContent());
                System.out.println(serviceid.getTextContent());
                System.out.println(spnumber.getTextContent());
                System.out.println(revtime.getTextContent());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
