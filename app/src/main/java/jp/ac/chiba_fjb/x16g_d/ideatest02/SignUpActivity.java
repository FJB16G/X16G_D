package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_READ_CONTACTS = 0;
    private UserLoginTask mAuthTask = null;
    private EditText mUserId;
    private EditText mPassword;
    private EditText mUserName;
    private View mProgressView;
    private View mLoginFormView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.signUp).setOnClickListener(this);
        mUserName = (EditText)findViewById(R.id.userName);
        mUserId = (EditText)findViewById(R.id.userId);
        mPassword = (EditText)findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        //最後の奴でEnter♂とボタン押したのと同じ処理になるようにする奴
        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUserName.setError(null);
        mUserId.setError(null);
        mPassword.setError(null);

        String userid = mUserId.getText().toString();
        String username = mUserName.getText().toString();
        String password = mPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // パスワードが空か文字数が足りているのか
        if (TextUtils.isEmpty(password)){
            mPassword.setError(getString(R.string.error_field_required));
            focusView = mUserId;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }

        // ユーザIDが空か文字数が足りているのか
        if (TextUtils.isEmpty(userid)) {
            mUserId.setError(getString(R.string.error_field_required));
            focusView = mUserId;
            cancel = true;
        } else if (!isUserIdValid(userid)) {
            mUserId.setError(getString(R.string.error_invalid_user));
            focusView = mUserId;
            cancel = true;
        }

        // ユーザ名が入力されているのか
        if (TextUtils.isEmpty(username)){
            mUserName.setError(getString(R.string.error_field_required));
            focusView = mUserId;
            cancel = true;
        }

        if (cancel) {
            //どこのviewが間違ってんのか判定
            focusView.requestFocus();
        } else {
            //ロード画面
            showProgress(true);
            //そのユーザがあるのか判定を投げる
            mAuthTask = new UserLoginTask(userid, password);
            mAuthTask.execute((Void) null);
        }
    }

    //文字数判定void
    private boolean isUserIdValid(String userid) {
        //TODO: Replace this with your own logic
        return userid.length() > 5;
    }
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }

    //くるくるするやつのクラス
    //起動する機器としない機器があるらしくその対応もしているらしい
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    //サーバから受け取った値とを判定できるクラス
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String UserId;
        private final String Password;

        UserLoginTask(String userid, String password) {
            UserId = userid;
            Password = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // 非同期処理？
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            // TODO: register the new account here.
            return true;
        }
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPassword.setError(getString(R.string.error_incorrect_userID));
                mPassword.requestFocus();
            }
        }
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.cancel){
            finish();
        }else if (v.getId()==R.id.signUp){
            attemptLogin();
        }
    }
}
