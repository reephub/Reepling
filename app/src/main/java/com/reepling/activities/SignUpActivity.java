package com.reepling.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.reepling.R;
import com.reepling.utils.ActivityLauncher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Michaël on 08/02/2018.
 */

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.input_name) EditText inputName;
    @BindView(R.id.input_first_name) EditText inputFirstName;
    @BindView(R.id.input_phone) EditText inputPhone;
    @BindView(R.id.input_email) EditText inputEmail;
    @BindView(R.id.input_password) EditText inputPassword;
    @BindView(R.id.input_confirm_password) EditText inputConfirmPassword;

    @BindView(R.id.input_layout_name)
    TextInputLayout inputLayoutName;
    @BindView(R.id.input_layout_first_name)
    TextInputLayout inputLayoutFirstName;
    @BindView(R.id.input_layout_phone)
    TextInputLayout inputLayoutPhone;
    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.input_layout_password)
    TextInputLayout inputLayoutPassword;
    @BindView(R.id.input_layout_confirm_password)
    TextInputLayout inputLayoutConfirmPassword;

    @BindView(R.id.txt_sign_up_request) TextView textViewSignUp;
    @BindView(R.id.checkbox_sign_up)
    AppCompatCheckBox cbSignUp;

    @BindView(R.id.spinner_sign_up_country)
    AppCompatSpinner spinnerCountry;

    @BindView(R.id.btn_sign_up) Button btnSignUp;
    @BindView(R.id.btn_cancel) Button btnCancel;


    private ActivityLauncher mActivityLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        inputEmail.addTextChangedListener(new SignUpActivity.MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new SignUpActivity.MyTextWatcher(inputPassword));

        mActivityLauncher = new ActivityLauncher(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "Time is up - Restart activity");
            }
        },3000);

    }

    @OnClick(R.id.btn_sign_up)
    public void onButtonSignUpClick(View view){
        submitForm();

        Log.i(TAG, "Then go to mainactivity ");
    }

    @OnClick(R.id.btn_cancel)
    public void onButtonCancelClick(View view){

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_sign_up_message)
                .setTitle(R.string.dialog_sign_up_title);

        // Add the buttons
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                finish();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateFirstName()) {
            return;
        }

        if (!validatePhone()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        if (!validateCountry()) {
            return;
        }

        if(!validateCheckBox()){
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateFirstName() {
        if (inputFirstName.getText().toString().trim().isEmpty()) {
            inputLayoutFirstName.setError(getString(R.string.err_msg_first_name));
            requestFocus(inputFirstName);
            return false;
        } else {
            inputLayoutFirstName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhone() {
        if (inputPhone.getText().toString().trim().isEmpty() || inputPhone.getText().toString().length() < 10) {
            inputLayoutPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(inputPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
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

    private boolean validateCountry() {
        //A travailler
        /*if (spinnerCountry.getSelectedItem().getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }*/

        return true;
    }

    private boolean validateCheckBox() {
        if (!cbSignUp.isChecked()){
            cbSignUp.setError("Veuillez accepter les conditions");
            return false;
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




    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view){
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
            switch (view.getId()){
                case R.id.input_name:
                    validateName();
                    break;

                case R.id.input_first_name:
                    validateFirstName();
                    break;

                case R.id.input_phone:
                    validatePhone();
                    break;

                case R.id.input_email:
                    validateEmail();
                    break;

                case R.id.input_password:
                    validatePassword();
                    break;

                case R.id.input_confirm_password:
                    validatePassword();
                    break;

                default:
                    break;
            }
        }
    }
}
