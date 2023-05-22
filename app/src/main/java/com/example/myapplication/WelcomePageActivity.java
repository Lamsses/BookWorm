package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePageActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_FIRST_LAUNCH = "firstLaunch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isFirstLaunch = preferences.getBoolean(KEY_FIRST_LAUNCH, true);

        if (isFirstLaunch) {
            // Perform actions for the first launch
            setContentView(R.layout.activity_welcom_page);
            Button button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WelcomePageActivity.this, CategorySelectActivity.class));

                    // Update the shared preference to indicate that the app has been launched before
                    preferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply();
                }
            });
        } else {
            // Perform actions for subsequent launches
            startActivity(new Intent(WelcomePageActivity.this, CategorySelectActivity.class));
            finish();
        }
    }
}
