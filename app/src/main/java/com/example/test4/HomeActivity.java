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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
        Intent i = new Intent(this,ProfileActivity.class);
        startActivity(i);
    }








    public void SignOutPressed(View view){
        Amplify.Auth.signOut( signOutResult -> {
            if (signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
                // Sign Out completed fully and without errors.
                Log.i("AuthQuickStart", "Signed out successfully");
            } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
                // Sign Out completed with some errors. User is signed out of the device.
                AWSCognitoAuthSignOutResult.PartialSignOut partialSignOutResult =
                        (AWSCognitoAuthSignOutResult.PartialSignOut) signOutResult;

                HostedUIError hostedUIError = partialSignOutResult.getHostedUIError();
                if (hostedUIError != null) {
                    Log.e("AuthQuickStart", "HostedUI Error", hostedUIError.getException());
                    // Optional: Re-launch hostedUIError.getUrl() in a Custom tab to clear Cognito web session.
                }

                GlobalSignOutError globalSignOutError = partialSignOutResult.getGlobalSignOutError();
                if (globalSignOutError != null) {
                    Log.e("AuthQuickStart", "GlobalSignOut Error", globalSignOutError.getException());
                    // Optional: Use escape hatch to retry revocation of globalSignOutError.getAccessToken().
                }

                RevokeTokenError revokeTokenError = partialSignOutResult.getRevokeTokenError();
                if (revokeTokenError != null) {
                    Log.e("AuthQuickStart", "RevokeToken Error", revokeTokenError.getException());
                    // Optional: Use escape hatch to retry revocation of revokeTokenError.getRefreshToken().
                }
            } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
                AWSCognitoAuthSignOutResult.FailedSignOut failedSignOutResult =
                        (AWSCognitoAuthSignOutResult.FailedSignOut) signOutResult;
                // Sign Out failed with an exception, leaving the user signed in.
                Log.e("AuthQuickStart", "Sign out Failed", failedSignOutResult.getException());
            }
        });

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}

