package com.example.test4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activities);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageButton img1 = findViewById(R.id.imageButton4);
        ImageButton img2 = findViewById(R.id.imageButton5);

        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(ActivitiesActivity.this, RestaurantActivity.class);
                startActivity(intent);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(ActivitiesActivity.this, HistoricalLandmarksActivity.class);
                startActivity(intent);
            }
        });

    }
}