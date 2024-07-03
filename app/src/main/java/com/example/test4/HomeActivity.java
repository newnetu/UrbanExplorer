package com.example.test4;

import static com.amplifyframework.core.Amplify.DataStore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.cognito.result.GlobalSignOutError;
import com.amplifyframework.auth.cognito.result.HostedUIError;
import com.amplifyframework.auth.cognito.result.RevokeTokenError;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.UserData;


import java.io.File;


public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        ImageButton img2 = findViewById(R.id.imageButton1);
        ImageButton img3 = findViewById(R.id.imageButton2);
        ImageButton img4 = findViewById(R.id.imageButton3);

        img2.setEnabled(false);
        img3.setEnabled(false);
        img4.setEnabled(false);

    }

    public void CapeTown(View view){
        Intent intent;
        intent = new Intent(this, ActivitiesActivity.class);
        startActivity(intent);
    }

    public void profilePressed(View view){
        String email = getIntent().getStringExtra("email");
        Intent i = new Intent(this,ProfileActivity.class);
        i.putExtra("email",email);
        Log.i("email"," "+ email);
        startActivity(i);
    }




}

