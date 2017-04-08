package com.baway.weektwo.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cz.msebera.android.httpclient.HttpConnection;

/**
 * data:2017/4/7
 * author:高亚男(Administrator)
 * function:
 */
public class HttpConnections{

    private static URL sUrl;
    private static HttpURLConnection sConnection;
    private static InputStream sInputStream;
    private static ByteArrayOutputStream sOutputStream;
    public static String doGet(String urlStr){

                try {
                    //获取url的地址
                    sUrl = new URL(urlStr);
                    //打开网络接口，为了获取数据
                    sConnection = (HttpURLConnection) sUrl.openConnection();
                    //获取读取的时间
                    sConnection.setReadTimeout(5000);
                    //获取延时的时间的操作
                    sConnection.setConnectTimeout(5000);
                    //获取连接的方法
                    sConnection.setRequestMethod("GET");
                    //设置要求的方式的限制
                    sConnection.setRequestProperty("accept","*/*");
                    //设置接受请求的方式
                    sConnection.setRequestProperty("connection","Keep-kills");
                    //判断结果码是否为200
                    if (sConnection.getResponseCode()==200){
                        //获取从网页读取的的流的方法
                        sInputStream = sConnection.getInputStream();
                        //定义一个写入流的数组，为了能够在写入文件的时候得到文件
                        sOutputStream = new ByteArrayOutputStream();

                        //定义一个长度
                        int lenth=-1;
                        //定义一个字节数组
                        byte[] butter=new byte[1024];
                        //判断是否为写入的文件
                        while((lenth=sInputStream.read(butter))!=-1){
                            //写文件
                            sOutputStream.write(butter,0,lenth);
                        }
                        //刷新流，为了在写入文件的时候出现没有写入文件的情况
                        sOutputStream.flush();
                        //关流，为了防止内存泄露
                        sOutputStream.close();
                        sInputStream.close();
                        return sOutputStream.toString();
                    }else{
                        //判断运行时返回的结果如果不是响应的结果码的时候的情况
                        throw new RuntimeException("responseCode is not 200 ...");
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
        }

    }

