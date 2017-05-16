package test.baway.com.jiekouhuidiaologin.V;

/**
 * data:2017/5/16
 * author:高亚男(Administrator)
 * function:登陆View的接口，实现类也就是登陆的activity
 */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
