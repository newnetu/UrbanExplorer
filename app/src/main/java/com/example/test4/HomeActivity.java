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
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//adding image buttons
        ImageButton img1 = findViewById(R.id.imageButton);
        ImageButton img2 = findViewById(R.id.imageButton1);
        ImageButton img3 = findViewById(R.id.imageButton2);
        ImageButton img4 = findViewById(R.id.imageButton3);

        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(HomeActivity.this, ActivitiesActivity.class);
                startActivity(intent);
            }
        });
//disabled buttons for other cities
        img2.setEnabled(false);
        img3.setEnabled(false);
        img4.setEnabled(false);

//adding the floating button
        FloatingActionButton flb1 = findViewById(R.id.floatingActionButton);

        flb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}