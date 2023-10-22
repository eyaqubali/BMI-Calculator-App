package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    AppCompatButton buttonBack;
    TextView display_score, weight_result_text, height_result_text, inches_result_text, gender_result_text, result_text;
    SharedPreferences sharedPreferences;
    ImageView img_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        buttonBack = findViewById(R.id.BnBack);
        display_score = findViewById(R.id.display_score);
        weight_result_text = findViewById(R.id.weight_result_text);
        height_result_text = findViewById(R.id.height_result_text);
        inches_result_text = findViewById(R.id.inches_result_text);
        gender_result_text = findViewById(R.id.gender_result_text);
        result_text = findViewById(R.id.result_text);
        img_score = findViewById(R.id.img_score);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        display_score.setText(sharedPreferences.getString("bmiIndex", ""));
        weight_result_text.setText(sharedPreferences.getString("Weight", ""));
        height_result_text.setText(sharedPreferences.getString("Height", ""));
        inches_result_text.setText(sharedPreferences.getString("Inches", ""));


        String userGender = sharedPreferences.getString("UserGender", "");
        gender_result_text.setText(userGender);


        String s1 = sharedPreferences.getString("bmiIndex", "");
        float s = Float.parseFloat(s1);

        if (s < 16) {
            img_score.setImageResource(R.drawable.very_low_weight);
            result_text.setText(R.string.your_bmi_very_severely_underweight);
        } else if (s >= 16.0 && s <= 16.9) {
            img_score.setImageResource(R.drawable.low_weight_score);
            result_text.setText(R.string.your_bmi_severely_underweight);
        } else if (s >= 17.0 && s <= 18.4) {
            img_score.setImageResource(R.drawable.under_weight);
            result_text.setText(R.string.your_bmi_underweight);
        } else if (s >= 18.5 && s <= 24.9) {
            img_score.setImageResource(R.drawable.normal_weight_score);
            result_text.setText(R.string.your_bmi_normal_weight);
        } else if (s >= 25.0 && s <= 29.9) {
            img_score.setImageResource(R.drawable.over_weight_score);
            result_text.setText(R.string.your_bmi_overweight);
        } else if (s >= 30.0 && s <= 34.9) {
            img_score.setImageResource(R.drawable.high_over_weight_score);
            result_text.setText(R.string.your_bmi_obese_weight);
        } else {
            img_score.setImageResource(R.drawable.bad_over_weight_score);
            result_text.setText(R.string.your_bmi_very_high_obese_weight);
        }

        buttonBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

    }


}