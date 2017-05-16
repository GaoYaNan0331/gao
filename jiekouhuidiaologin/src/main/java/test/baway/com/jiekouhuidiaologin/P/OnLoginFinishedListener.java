package test.baway.com.jiekouhuidiaologin.P;

/**
 * data:2017/5/16
 * author:高亚男(Administrator)
 * function:登录事件的监听
 */

public interface OnLoginFinishedListener {
    void onUsernameError();

    void onPasswordError();

    void onSuccess();

}
