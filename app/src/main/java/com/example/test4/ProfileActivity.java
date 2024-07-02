package com.example.test4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.cognito.result.GlobalSignOutError;
import com.amplifyframework.auth.cognito.result.HostedUIError;
import com.amplifyframework.auth.cognito.result.RevokeTokenError;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.UserData;

public class ProfileActivity extends AppCompatActivity {

    String userEmail = "";
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

        //getting user and points
        boolean isAddPoint = getIntent().getBooleanExtra("addPoint",false);
        if(isAddPoint){
            Amplify.DataStore.query(UserData.class, UserData.EMAIL.eq(userEmail),
                    result -> {
                        if (result.hasNext()) {
                            UserData currentUserData = result.next();

                            // Increment the points
                            int newPointsValue = currentUserData.getPoints() + 1;

                            // Create a new UserData object with updated points
                            UserData updatedUserData = currentUserData.builder()
                                    .name(currentUserData.getName())
                                    .email(currentUserData.getEmail())
                                    .points(newPointsValue)
                                    .build();

                            // Perform the update mutation
                            Amplify.API.mutate(
                                    ModelMutation.update(updatedUserData),
                                    response -> Log.i("MyAmplifyApp", "Added a point to UserData successfully"),
                                    error -> Log.e("MyAmplifyApp", "Update failed", error)
                            );
                        } else {
                            Log.e("MyAmplifyApp", "UserData not found for email: " + userEmail);
                        }
                    },
                    error -> Log.e("MyAmplifyApp", "Query failed", error)
            );
        }

        TextView userName = findViewById(R.id.textView19);
        TextView points = findViewById(R.id.textView20);



        Amplify.Auth.fetchUserAttributes(
                attributes -> {
                    Log.i("AuthDemo", "User attributes = " + attributes.toString());
                    for (AuthUserAttribute attribute : attributes) {
                        if (attribute.getKey().getKeyString().equals("email")) {
                            userEmail = attribute.getValue();
                            break; // Exit the loop once you find the email
                        }
                    }
                    userName.setText(userEmail);

                    Amplify.API.query(
                            ModelQuery.list(UserData.class,UserData.EMAIL.eq(userEmail)),
                            response -> {for (UserData info: response.getData())
                                if (info.getPoints() != null) {
                                    runOnUiThread(() -> {
                                        points.setText(info.getPoints().toString());
                                    });
                                }
                            },
                            error -> Log.e("MyAmplifyQuery", "Query failure", error)

                    );


                },
                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
        );



    }

    public void goBackHome(View view){
        Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);
    }
//signout pressed
public void SignOutPressed(View view){
    Amplify.Auth.signOut(signOutResult -> {
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