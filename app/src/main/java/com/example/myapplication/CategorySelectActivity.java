package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CategorySelectActivity extends AppCompatActivity {
    ArrayList<String> selectedCategory = new ArrayList<>();
    ChipGroup chipGroup;
    Button nextButton;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_FIRST_LAUNCH = "firstLaunch1";
    private static final String KEY_SELECTED_CATEGORY = "selectedCategory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isFirstLaunch = preferences.getBoolean(KEY_FIRST_LAUNCH, true);

        if (isFirstLaunch) {
            // Perform actions for the first launch
            // For example, show an introduction or onboarding flow

            // After completing the necessary setup, update the shared preference to indicate that the app has been launched before
            preferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply();

            setContentView(R.layout.category_selection);

            chipGroup = findViewById(R.id.chip_group_ingredients);
            nextButton = findViewById(R.id.next_button);

            nextButton.setOnClickListener(v -> {
                if (selectedCategory.size() >= 2) {
                    // Save the selected category list in shared preferences
                    saveSelectedCategory();

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Please Select 2 or more!", Toast.LENGTH_SHORT).show();
                }
            });

            getSelectedChips();
        } else {
            // Perform actions for subsequent launches
            // Start the desired activity or perform other tasks

            // Retrieve the selected category list from shared preferences
            selectedCategory = getSelectedCategory();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putStringArrayListExtra("SELECTED_CATEGORY",selectedCategory);
            startActivity(intent);
            finish(); // Optional: Finish the current activity to prevent going back to the category selection
        }
    }

    void getSelectedChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton chip, boolean isChecked) {
                    if (isChecked) {
                        selectedCategory.add(chip.getText().toString());
                    } else {
                        selectedCategory.remove(chip.getText().toString());
                    }
                }
            });
        }
    }

    private void saveSelectedCategory() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Convert the list to a set to store in shared preferences
        Set<String> selectedCategorySet = new HashSet<>(selectedCategory);

        editor.putStringSet(KEY_SELECTED_CATEGORY, selectedCategorySet);
        editor.apply();
    }

    private ArrayList<String> getSelectedCategory() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> selectedCategorySet = preferences.getStringSet(KEY_SELECTED_CATEGORY, null);

        // Convert the set back to a list
        return new ArrayList<>(selectedCategorySet);
    }
}