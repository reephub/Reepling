package com.reepling.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.reepling.R;
import com.reepling.app.BaseReeplingActivity;
import com.reepling.app.MySharedPrefs;
import com.reepling.data.local.model.User;
import com.reepling.data.repository.ReeplingRepository;
import com.reepling.utils.ActivityLauncher;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by Michaël on 08/02/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private Context mContext;

    @BindView(R.id.input_email)
    EditText inputEmail;

    @BindView(R.id.input_password)
    EditText inputPassword;

    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;

    @BindView(R.id.input_layout_password)
    TextInputLayout inputLayoutPassword;

    @BindView(R.id.btn_connect)
    Button buttonConnect;

    @BindView(R.id.txt_sign_in_request)
    TextView textViewSignIn;

    private ActivityLauncher mActivityLauncher;

    private ReeplingRepository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;

        ButterKnife.bind(this);

        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        repository = new ReeplingRepository(this);

        mActivityLauncher = new ActivityLauncher(this);
    }

    @OnClick(R.id.btn_connect)
    public void OnButtonConnectClick() {
        Log.i(TAG, "Connection clicked successfully");

        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        submitForm();
    }

    @OnClick(R.id.txt_sign_in_request)
    public void OnTextViewSignInRequestClick() {
        Log.i(TAG, "Sign In message clicked successfully");

        mActivityLauncher.AppCompatActivity(this, SignUpActivity.class);
    }


    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        Toast.makeText(mContext, "Thank You!", Toast.LENGTH_SHORT).show();

        logUser();

    }


    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    public void logUser() {

        String login = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        repository.getUser(login, password)
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(User user) {
                        Log.i(TAG, "Save user's username in sharedPrefs");
                        MySharedPrefs.setSharedPrefsKeyConnectedUser(mContext, user);

                        repository.saveCurrentUserSession(user.getUsername());

                        Log.i(TAG, "User does exist log him");
                        mActivityLauncher.AppCompatActivity(LoginActivity.this, MainActivity.class);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable sEditable) {
            switch (view.getId()) {
                case R.id.input_email:
                    validateEmail();
                    break;

                case R.id.input_password:
                    validatePassword();
                    break;

                default:
                    break;
            }
        }
    }

}
