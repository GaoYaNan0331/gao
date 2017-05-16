package test.baway.com.jiekouhuidiaologin.P;

import test.baway.com.jiekouhuidiaologin.M.LoginModel;
import test.baway.com.jiekouhuidiaologin.M.LoginModelImpl;
import test.baway.com.jiekouhuidiaologin.V.LoginView;
import test.baway.com.jiekouhuidiaologin.V.MainActivity;

/**
 * data:2017/5/16
 * author:高亚男(Administrator)
 * function:
 * 1 完成presenter的实现。这里面主要是Model层和View层的交互和操作。
 * 2  presenter里面还有个OnLoginFinishedListener，
 * 其在Presenter层实现，给Model层回调，更改View层的状态，
 * 确保 Model层不直接操作View层。如果没有这一接口在LoginPresenterImpl实现的话，
 * LoginPresenterImpl只 有View和Model的引用那么Model怎么把结果告诉View呢？
 */

public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {


    private LoginView loginView;
    private LoginModel loginModel;
//构造方法实现LoginPresenter接口调用loginView，loginModel
    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }
    //重写validateCredentials、onDestroy、onUsernameError、onPasswordError、onSuccess
    //方法，用来对一系列数据的隐藏与显示
    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginModel.login(username, password, this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
        loginView.setUsernameError();
        loginView.hideProgress();
    }


    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }

        
    }



    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}