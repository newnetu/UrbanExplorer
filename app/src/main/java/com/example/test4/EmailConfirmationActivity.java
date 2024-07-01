package com.example.test4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;

import com.amplifyframework.datastore.generated.model.UserData;


public class EmailConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);

    }

    public void onConfirmButtonPressed(View view){
        //Confirm the code
        //Re-login
        //Save the user details such names in Data-store
        EditText txtConfirmationCode = findViewById(R.id.txtConfirmationCode);

        Amplify.Auth.confirmSignUp(
                getEmail(),
                txtConfirmationCode.getText().toString(),
                this::onSuccess,
                this::onError
        );
    }

    private void onError(AuthException e) {
        this.runOnUiThread(()->{
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void onSuccess(AuthSignUpResult authSignUpResult) {
        reLogin();
    }

    private void reLogin() {
        String username = getEmail();
        String password = getPassword();

        Amplify.Auth.signIn(
                username,
                password,
                this::onLoginSuccess,
                this::onError
        );
    }

    private void onLoginSuccess(AuthSignInResult authSignInResult) {
        UserData newUser = UserData.builder()
                .name(getName())
                .email(getEmail())
                .points(0)
                .build();
        Amplify.API.mutate(ModelMutation.create(newUser),
                this::onSavedSuccess,
                this::onSaveError

        );

    }

    private void onSavedSuccess(GraphQLResponse<UserData> userDataGraphQLResponse) {
        Log.i("User DataStore","Saved Successfully");
        Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);

    }

    private void onSaveError(ApiException e) {
        Log.e("User DataStore", "Save Failed ", e);
        this.runOnUiThread(()->{
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }




    public String getName(){
        return getIntent().getStringExtra("name");
    }


    public String getPassword(){
        return getIntent().getStringExtra("password");
    }

    public String getEmail(){
        return getIntent().getStringExtra("email");
    }

}