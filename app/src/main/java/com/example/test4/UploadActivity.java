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

import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.StoragePath;

import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;


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







}