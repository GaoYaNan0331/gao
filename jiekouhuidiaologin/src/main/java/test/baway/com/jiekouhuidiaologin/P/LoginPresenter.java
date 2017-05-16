package test.baway.com.jiekouhuidiaologin.P;

/**
 * data:2017/5/16
 * author:高亚男(Administrator)
 * function:定义一个设定用户名和密码的接口，为实现登陆成功与否开启接口
 */

public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}
