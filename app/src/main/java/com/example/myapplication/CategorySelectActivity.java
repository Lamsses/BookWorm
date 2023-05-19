package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class CategorySelectActivity extends AppCompatActivity {
    ArrayList<String> SelectedCategory = new ArrayList<>();
    ChipGroup chipGroup;
    Button nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_selection);

        chipGroup = findViewById(R.id.chip_group_ingredients);
        nextButton = findViewById(R.id.next_button);



        nextButton.setOnClickListener(v -> {
            if (SelectedCategory.size() >= 2) {
                Toast.makeText(this, SelectedCategory.toString(), Toast.LENGTH_SHORT).show();
//                Bundle bundle = new Bundle();
//                HomeFragment fragment = new HomeFragment();
//                bundle.putStringArrayList("SELECTED_CATEGORY",SelectedCategory);
//                fragment.setArguments(bundle);

                Intent intent = new Intent(this, MainActivity.class);
                intent.putStringArrayListExtra("SELECTED_CATEGORY", SelectedCategory);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please Select 2 or more !", Toast.LENGTH_SHORT).show();
            }
        });
    getSelectedChips();
    }

    void getSelectedChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton chip, boolean isChecked) {
                    if (isChecked) {
                            SelectedCategory.add(chip.getText().toString());
                    }
                    else {
                        SelectedCategory.remove(chip.getText().toString());
                    }
                    if (!SelectedCategory.isEmpty()){
//                        Toast.makeText(CategorySelectActivity.this,  SelectedCategory.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
