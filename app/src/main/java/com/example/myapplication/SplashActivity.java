package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {


    TextInputLayout inputLayout;
    TextInputEditText inputEditText;
    AppCompatButton nextButton;
    SharedPreferences  sharedPreferences;
    SharedPreferences.Editor editor;
    String sUser;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        inputLayout = findViewById(R.id.user_input_layout);
        inputEditText = findViewById(R.id.userName);
        nextButton = findViewById(R.id.nextButton);


        sharedPreferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
        editor = sharedPreferences.edit();


        nextButton.setOnClickListener(view -> {

        if (TextUtils.isEmpty(inputEditText.getText())){
            inputEditText.setError( "name is required!" );
            inputEditText.requestFocus();
        }else{
            sUser = inputEditText.getText().toString();
            editor.putString("UserName",sUser);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        });



    } //One Create Bundle End

}