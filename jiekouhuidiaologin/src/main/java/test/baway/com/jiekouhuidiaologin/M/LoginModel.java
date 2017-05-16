package test.baway.com.jiekouhuidiaologin.M;

import test.baway.com.jiekouhuidiaologin.P.OnLoginFinishedListener;

/**
 * data:2017/5/16
 * author:高亚男(Administrator)
 * function:模拟登陆的操作的接口，实现类为LoginModelImpl.相当于MVP模式中的Model层
 */

public interface LoginModel {
    //设置登录的接口用来存入用户名，密码和要设置监听事件的回调的方法的设置
    void login(String username,String password,OnLoginFinishedListener listener);
}
