package com.example.test4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.LocationInfo;


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

    public void queryModel(View view) {

        Amplify.API.query(
                ModelQuery.list(LocationInfo.class, LocationInfo.CATEGORY.eq("HISTORY")),
                response -> {
                    for (LocationInfo todo : response.getData()) {
                        Log.i("MyAmplifyQuery1", todo.getImageKey());
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

