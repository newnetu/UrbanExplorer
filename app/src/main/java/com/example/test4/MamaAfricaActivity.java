package com.example.test4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.LocationInfo;
import com.amplifyframework.storage.StoragePath;
import com.bumptech.glide.Glide;

import java.io.File;

public class MamaAfricaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mama_africa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent data = new Intent(this, RekogitionClientActivity.class);
        data.putExtra("selected","Mama Africa");
        sendBroadcast(data);


        Amplify.API.query(
                ModelQuery.list(LocationInfo.class, LocationInfo.IMAGE_KEY.eq("Mama Africa3.png")),
                response -> {
                    runOnUiThread(() -> {
                        for (LocationInfo todo : response.getData()) {
                            Log.i("FACTS", todo.getFacts());
                            TextView textView = findViewById(R.id.textView7);
                            textView.setText(todo.getFacts());

                            TextView textViewNew = findViewById(R.id.textView6);
                            textViewNew.setText(todo.getAddress());

                            ImageView imageView = findViewById(R.id.imageView4);
                            String keyName = todo.getImageKey();

                            Amplify.Storage.downloadFile(
                                    StoragePath.fromString("public/locations/"+keyName),
                                    new File(getApplicationContext().getFilesDir() + keyName),
                                    result -> Glide.with(this)
                                            .load(result.getFile())
                                            .into(imageView),
                                    error -> Log.e("DownloadStuff",  "Download Failure", error)
                            );
                        }
                    });
                },
                error -> Log.e("MyAmplifyQuery", "Query failure", error)
        );


    }

    public void onUploadPressed(View view){

        Intent i = new Intent(this,UploadActivity.class);
        startActivity(i);



    }

}