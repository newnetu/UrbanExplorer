package com.example.test4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.amplifyframework.core.Amplify;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Amplify.Auth.getCurrentUser(
                authUser ->{ //onSuccess
                        //Go to the login screen
                        Intent i = new Intent(getApplicationContext(), UploadActivity.class);
                        startActivity(i);
                },
                AuthException ->{ //onError
                    Log.e("AmplifyAuth", "Error getting current user", AuthException);
                    // Go to the login screen
                    Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(loginIntent);
                }
        );
        finish();
    }

}

