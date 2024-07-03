package com.example.test4;

import static com.amplifyframework.auth.result.AuthSessionResult.Type.FAILURE;

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
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.UserData;

import org.w3c.dom.Text;

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



}