package com.example.test4;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;




public class ProfileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //adding the floating button
        FloatingActionButton flb1 = findViewById(R.id.floatingActionButton2);

        flb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


    }

//    public void queryModel() {
//        Amplify.API.query(
//                ModelQuery.get(UserData.class, "af6db4bc-c6cd-4a39-84e1-1390066d3a28"),
//                response -> Log.i("MyAmplifyApp", ((UserData) response.getData()).getId()),
//                error -> Log.e("MyAmplifyApp", error.toString(), error)
//        );
//    }
//public void queryModel() {
//    Amplify.API.query(
//            ModelQuery.get(UserData.class, "af6db4bc-c6cd-4a39-84e1-1390066d3a28"),
//            response -> {
//                UserData userData = response.getData();
//                if (userData != null) {
//                    Log.i("MyAmplifyApp", "Retrieved item: " + userData.getId() + ", " + userData.getName());
//                } else {
//                    Log.i("MyAmplifyApp", "No item found");
//                }
//            },
//            error -> Log.e("MyAmplifyApp", "Query failed", error)
//    );
//}



}