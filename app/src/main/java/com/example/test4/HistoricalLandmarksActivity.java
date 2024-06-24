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

public class HistoricalLandmarksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historical_landmarks);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton img1 = findViewById(R.id.imageButton6);
        ImageButton img2 = findViewById(R.id.imageButton7);
        ImageButton img3 = findViewById(R.id.imageButton8);

        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(HistoricalLandmarksActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(HistoricalLandmarksActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(HistoricalLandmarksActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}