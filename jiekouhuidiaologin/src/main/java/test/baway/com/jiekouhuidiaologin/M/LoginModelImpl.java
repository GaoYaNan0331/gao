package test.baway.com.jiekouhuidiaologin.M;

import android.os.Handler;
import android.text.TextUtils;

import test.baway.com.jiekouhuidiaologin.P.OnLoginFinishedListener;

/**
 * data:2017/5/16
 * author:高亚男(Administrator)
 * function:延时模拟登陆（2s），如果名字或者密码为空则登陆失败，否则登陆成功
 * 实现Login登录接口，用来对用户名和密码判断是否为空的设置延时的操作
 */

public class LoginModelImpl implements LoginModel{
    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        //实例化一个handler调用postDelayed实例化Runnable，重写Run方法，然后对用户名和密码，登陆成功进行判断
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error=false;
                if (TextUtils.isEmpty(username)){
                    //modle层回调listener
                    listener.onUsernameError();
                    error=true;
                }

                if (TextUtils.isEmpty(password)){

                    listener.onPasswordError();
                    error=true;
                }
                if (!error){
                    listener.onSuccess();
                }
            }
        },2000);
    }
}
