package test.baway.com.jiekouhuidiaologin.V;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import test.baway.com.jiekouhuidiaologin.P.LoginPresenterImpl;
import test.baway.com.jiekouhuidiaologin.R;

public class MainActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private ProgressBar mProgress;
    private Button mBtn;
    private LoginPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mPresenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);

    }

    @Override
    public void setUsernameError() {
    mUsername.setError("用户名错误，请进行确认");
    }

    @Override
    public void setPasswordError() {
    mPassword.setError("密码错误");
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        mPresenter.validateCredentials(mUsername.getText().toString(), mPassword.getText().toString());
//        Toast.makeText(this, "点击登陆啦！", Toast.LENGTH_SHORT).show();
    }
}
