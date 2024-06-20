package com.example.test4;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.LocationInfo;
import com.amplifyframework.storage.StoragePath;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class UploadActivity extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 1000;
    private ImageView  imgGallery;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imgGallery  = findViewById(R.id.imgGallery);

    }

    //Open Gallery
    public void onBtnGalleryPressed(View view){
        Intent iGallery = new Intent(Intent.ACTION_PICK);

        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, GALLERY_REQ_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode==RESULT_OK){

            if(requestCode==GALLERY_REQ_CODE){
                //for Gallery
                imageUri = data.getData();
                imgGallery.setImageURI(imageUri);
            }
        }
    }

    //For Conifrm Upload button
    public void onUploadPressedGal(View view){
        if(imageUri != null){
            uploadFile(imageUri);
        }
    }

    private void uploadFile(Uri fileUri) {
        try{
            //Read the data Uri points too
            InputStream inputStream = getContentResolver().openInputStream(fileUri);

            //temp file
            File uploadFile = new File(getCacheDir(), "upload_image.jpg");
            FileOutputStream outputStream = new FileOutputStream(uploadFile);


            //Create buffer
            byte[] buffer = new byte[1024];
            //Store the number of bytes read from the input stream in each iteration
            int length;
            while((length = inputStream.read(buffer)) > 0)  {
                outputStream.write(buffer,0,length);
            }

            outputStream.close();
            inputStream.close();

            Amplify.Storage.uploadFile(
                    StoragePath.fromString("public/uploaded"),
                    uploadFile,
                    result -> Log.i("MyAppStorage", "Successfully uploaded "),
                    storageFailure -> Log.e("MyAppStorage", "Upload failed", storageFailure)
            );
        } catch(Exception e){
            Log.e("AppDataStorage", "Upload Failed", e);
        }
    }




    public void queryModel(View view) {

        Amplify.API.query(
                ModelQuery.list(LocationInfo.class, LocationInfo.CATEGORY.eq("HISTORY")),
                response -> {
                    for (LocationInfo todo : response.getData()) {
                        Log.i("MyAmplifyQuery1", todo.getImageKey());
                        String keyName = todo.getImageKey();
                        ImageView imageView = findViewById(R.id.imageView);


                        //File newImage;
                        Amplify.Storage.downloadFile(
                                StoragePath.fromString("public/locations/"+keyName),
                                new File(getApplicationContext().getFilesDir() + keyName),
                                result -> Glide.with(this)
                                            .load(result.getFile())
                                            .into(imageView),
                                error -> Log.e("DownloadStuff",  "Download Failure", error)
                                );
//                        Amplify.Storage.downloadFile(
//                                StoragePath.fromString("public/locations/"+keyName),
//                                new File(getApplicationContext().getFilesDir() + keyName),
//                                result -> Log.i("DownloadStuff", "Successfully downloaded: " + result.getFile().getName()),
//                                error -> Log.e("DownloadStuff",  "Download Failure", error)
//                        );
                    }
                },
                error -> Log.e("MyAmplifyQuery", "Query failure", error)
        );

        Log.i("ButtonWork", "Button Clicked ");
    }


}