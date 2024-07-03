package com.example.test4;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.CustomLabel;
import com.amazonaws.services.rekognition.model.DetectCustomLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectCustomLabelsResult;
import com.amplifyframework.core.Amplify;

import java.util.List;

public class RekognitionDetectCustomLabels extends AsyncTask<Void,Void,String> {
    private AmazonRekognitionClient rekognitionClient;
    private DetectCustomLabelsRequest request;
    private Context context;
    private String compare;

    public RekognitionDetectCustomLabels(Context context, AmazonRekognitionClient rekognitionClient, DetectCustomLabelsRequest request, String compare) {
        this.context = context;
        this.rekognitionClient = rekognitionClient;
        this.request = request;
        this.compare = compare;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            DetectCustomLabelsResult result  = rekognitionClient.detectCustomLabels(request);
            List<CustomLabel> labels = result.getCustomLabels();
            String finalResult = null; // Initialize to null
            if (labels != null && !labels.isEmpty()) {
                CustomLabel label = labels.get(0);
                if (label != null) { // Add null check for label
                    finalResult = label.getName();
                    Log.i("Rekognition Confidence", " " + label.getConfidence());
                }
            }


            return finalResult;
        } catch (AmazonServiceException rkge) {
            Log.e("Rekognition Error", "Code: " + rkge.getErrorCode() +
                    ", Message: " + rkge.getErrorMessage());
            return null; // Or handle the error differently
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            Log.e("Rekognition Result",result);
            addPoints(result,compare);

            Intent i = new Intent(context, RekogitionClientActivity.class);
            i.putExtra("Rekgonition Output",result);
            // Process the retrieved S3 object content here
        } else {
            // Handle the case where the result is null (error occurred)
            Log.e("Rekognition Result","Unkown Item");
            Intent i = new Intent(context, HomeActivity.class);
            Toast.makeText(context, "please retry", Toast.LENGTH_SHORT).show();
            context.startActivity(i);
        }
    }

    private void addPoints(String result, String compare1) {

        if(result.equals("Cape Town City Hall")){
            Log.i("addPoints", "I will add");
            Toast.makeText(context, "added point", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(context, ProfileActivity.class);
            i.putExtra("addPoint",true);
            context.startActivity(i);


        }
        else{
            Log.i("addPoints", "Invalid image");
            Toast.makeText(context, "please retry", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, HomeActivity.class);
            context.startActivity(i);
        }
    }

}
