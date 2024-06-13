package com.amplifyframework.datastore.generated.model;

import android.util.Log;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.Where;

public class Locations implements Model {
    private final LocationInfo model = LocationInfo.builder()
            .category(Preferences.FOOD)
            .imageKey("Cape Town City Hall5.png")
            .build();

    public void setModel() {
        //Consumer<Iterator<LocationInfo>> historyPlaces;
        Amplify.DataStore.query(LocationInfo.class, Where.matches(LocationInfo.CATEGORY.contains("HISTORY")),
                historyPlaces -> {
                    while (historyPlaces.hasNext()){
                        LocationInfo locInfo = historyPlaces.next();
                        Log.i("MyAmplifyApp", "Query: " +  locInfo);}
                },
                failure -> Log.e("MyAmplifyApp", "Query failed.")
        );
    }
}