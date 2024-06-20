package com.example.test4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.LocationInfo;
import com.amplifyframework.storage.StoragePath;

import java.io.File;

import aws.smithy.kotlin.runtime.InternalApi;

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
    }

    @OptIn(markerClass = InternalApi.class)
    public void queryModel(View view) {

        Amplify.API.query(
                ModelQuery.list(LocationInfo.class, LocationInfo.CATEGORY.eq("HISTORY")),
                response -> {
                    for (LocationInfo todo : response.getData()) {
                        Log.i("MyAmplifyQuery1", todo.getImageKey());

                        String bucketName = "location-images-urbanexplorer";
                        String keyName = todo.getImageKey();
                        File path = new File("${applicationContext.filesDir}/${keyName}.png");
                        Amplify.Storage.downloadFile(
                                StoragePath.fromString("private/${todo.getImageKey()}"),
                                new File(getApplicationContext().getFilesDir() + "/${todo.getImageKey()}"),
                                result -> Log.i("DownloadStuff", "Successfully downloaded: " + result.getFile().getName()),
                                error -> Log.e("DownloadStuff",  "Download Failure", error)
                        );
                        //Amplify.Storage.downloadFile(keyName,localDes);

                        //S3Client client = S3Client.builder().build();
                        //client.getObject()
//                        GetObjectRequest request = GetObjectRequest.builder()
//                                .bucket(bucketName)
//                                .key(keyName)
//                                .build();
                        //getObjectBytes(s3, bucketName, keyName, path);

                        // Starts a download



                    }
                        Log.i("MyAmplifyQuery2", String.valueOf(response.getData()));

                },
                error -> Log.e("MyAmplifyQuery", "Query failure", error)
        );
//        Amplify.API.query(
//                ModelQuery.list(LocationInfo.class, LocationInfo.CATEGORY.contains("HISTORY")),
//                response -> {
//                    for (LocationInfo todo : response.getData()) {
//                        Log.i("MyAmplifyApp", todo.getImageKey());
//                    }
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );

        //Consumer<Iterator<LocationInfo>> historyPlaces;

//        Amplify.API.query(
//                ModelQuery.get(LocationInfo.class,"HISTORY"),
//                response -> Log.i("MyAmplifyQuery", ((LocationInfo) response.getData()).getImageKey()),
//                error -> Log.e("MyAmplifyQuery", error.toString(), error)
//        );


//          Amplify.DataStore.query(LocationInfo.class, Where.matches(LocationInfo.CATEGORY.contains("HISTORY")),
//                historyPlaces -> {
//                    while (historyPlaces.hasNext()){
//                        LocationInfo locInfo = historyPlaces.next();
//                        Log.i("MyAmplifyQue", "Query: " +  locInfo);}
//                },
//                failure -> Log.e("MyAmplifyQue", "Query failed.")
//        );

        Log.i("ButtonWork", "Button Clicked ");
    }
}

