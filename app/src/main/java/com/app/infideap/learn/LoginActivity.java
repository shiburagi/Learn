package com.app.infideap.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usernameEditText = (EditText) findViewById(R.id.editText_username);
        passwordEditText = (EditText) findViewById(R.id.editText_password);

        passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
        if (FirebaseAuth.getInstance().getCurrentUser()!=null)
            redirect();

    }

    private void redirect() {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        intent.putExtra(MainActivity.USERNAME, usernameEditText.getText().toString());
        startActivity(intent);
        finish();
    }

    public void login(View view) {

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(
                username,
                password
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Log.e(TAG, "onComplete() : " + task.isSuccessful());
                Log.e(TAG, "onComplete() : " + task.getException());
                if (task.isSuccessful()) {
                   redirect();
                }
            }
        });


    }

}
