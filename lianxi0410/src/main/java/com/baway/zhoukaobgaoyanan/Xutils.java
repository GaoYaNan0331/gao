package com.baway.zhoukaobgaoyanan;

import android.content.Context;
import android.widget.ListView;


import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * data:2017/4/10
 * author:高亚男(Administrator)
 * function:
 */
public class Xutils {

    public static void get(String path, final ListView lv, final Context context){
        RequestParams requestParams=new RequestParams(path);
        requestParams.addQueryStringParameter("url",path);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson=new Gson();
                NewBean newbean= gson.fromJson(result, NewBean.class);
                //gson.fromJson(result,NewBean.class);
                lv.setAdapter(new MyListAdapter(newbean,context));
                //解析result
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
