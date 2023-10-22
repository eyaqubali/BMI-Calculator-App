package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {

    TextInputLayout user_weight_layout, user_height_layout, user_inches_layout;
    TextInputEditText user_weight, user_height, user_inches;
    RadioButton MRadioButton, FRadioButton;
    TextView home_user_name;
    SharedPreferences sharedPreferences;
    AppCompatButton ButtonCalculate;
    RadioGroup radio_group;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        editor = sharedPreferences.edit();

        home_user_name = findViewById(R.id.home_user_name);
        String getName = "Hi " + sharedPreferences.getString("UserName", "Welcome");
        home_user_name.setText(getName);

        radio_group = findViewById(R.id.radio_group);

        user_weight_layout = findViewById(R.id.user_weight_layout);
        user_height_layout = findViewById(R.id.user_height_layout);
        user_inches_layout = findViewById(R.id.user_inches_layout);


        user_weight = findViewById(R.id.user_weight);
        user_height = findViewById(R.id.user_height);
        user_inches = findViewById(R.id.user_inches);

        user_weight.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        user_height.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        user_inches.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});


        MRadioButton = findViewById(R.id.MRadioButton);
        FRadioButton = findViewById(R.id.FRadioButton);

        ButtonCalculate = findViewById(R.id.calculate_button);


        if (TextUtils.isEmpty(user_weight.getText())) {
            user_weight.setError("weight is required!");
            user_weight.requestFocus();
        }

        if (TextUtils.isEmpty(user_inches.getText())) {
            user_inches.setError("inches is required!");
            user_inches.requestFocus();
        }

        if (TextUtils.isEmpty(user_height.getText())) {
            user_height.setError("height is required!");
            user_height.requestFocus();
        }


        ButtonCalculate.setOnClickListener(view -> {

            if (TextUtils.isEmpty(user_weight.getText())) {
                user_weight.setError("weight is required!");
                user_weight.requestFocus();

                if (TextUtils.isEmpty(user_height.getText())) {
                    user_height.setError("height is required!");
                    user_height.requestFocus();

                    if (TextUtils.isEmpty(user_inches.getText())) {
                        user_inches.setError("inches is required!");
                        user_inches.requestFocus();
                    }
                }

            } else {

                String sWeight = user_weight.getText().toString();
                String sHeight = user_height.getText().toString();
                String sInches = user_inches.getText().toString();


                float weight = Float.parseFloat(sWeight);
                float height = Float.parseFloat(sHeight);
                float inches = Float.parseFloat(sInches);

                float main_height = (float) (height * 0.3048 + inches * 0.0254);
                float bmiIndex = weight / (main_height * main_height);
                String result_bmi = Float.toString(bmiIndex);


                editor.putString("Weight", sWeight);
                editor.putString("Height", sHeight);
                editor.putString("Inches", sInches);
                editor.putString("bmiIndex", result_bmi);
                editor.apply();

                startActivity(new Intent(getApplicationContext(), ResultActivity.class));
            }

        });


        radio_group.setOnCheckedChangeListener((radioGroup, checkedID) -> {
            if (checkedID == R.id.MRadioButton) {
                editor.putString("UserGender", "Male");
                editor.apply();

            } if (checkedID == R.id.FRadioButton) {
                editor.putString("UserGender", "Female");
                editor.apply();
            }

        });


    }
}