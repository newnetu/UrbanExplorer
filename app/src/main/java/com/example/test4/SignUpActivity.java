package com.example.test4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
    }

    public void onPressJoin(View view){
        Log.d("myJoinError","Is pressed");
        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtPassword = findViewById(R.id.txtPassword);

        Amplify.Auth.signUp(
                txtEmail.getText().toString(),
                txtPassword.getText().toString(),
                AuthSignUpOptions.builder().userAttribute(
                        AuthUserAttributeKey.email(), txtEmail.getText().toString()
                ).build(),
                this::onJoinSuccess,
                this::onJoinError

        );
    }

    private void onJoinSuccess(AuthSignUpResult authSignUpResult) {
        Intent i = new Intent(this,EmailConfirmationActivity.class);

        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtPassword = findViewById(R.id.txtPassword);
        EditText txtName = findViewById(R.id.txtName);

        i.putExtra("email",txtEmail.getText().toString());
        i.putExtra("password",txtPassword.getText().toString());
        i.putExtra("name",txtName.getText().toString());

        startActivity(i);
    }

    private void onJoinError(AuthException e) {
        this.runOnUiThread(()->{
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }


}