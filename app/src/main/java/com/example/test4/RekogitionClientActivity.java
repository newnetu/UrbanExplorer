package com.example.test4;

 import static com.amazonaws.regions.Regions.US_EAST_2;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;
 import android.util.Log;
 import android.widget.Toast;

 import androidx.activity.EdgeToEdge;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.graphics.Insets;
 import androidx.core.view.ViewCompat;
 import androidx.core.view.WindowInsetsCompat;

 import com.amazonaws.auth.CognitoCachingCredentialsProvider;
 import com.amazonaws.regions.Region;

 import com.amazonaws.regions.Regions;
 import com.amazonaws.services.rekognition.AmazonRekognition;
 import com.amazonaws.services.rekognition.AmazonRekognitionClient;
 import com.amazonaws.services.rekognition.model.DetectCustomLabelsRequest;
 import com.amazonaws.services.rekognition.model.Image;
 import com.amazonaws.services.rekognition.model.S3Object;

 import com.amazonaws.services.s3.AmazonS3;
 import com.amazonaws.services.s3.AmazonS3Client;

 import com.amazonaws.auth.AWSCredentials;
 import com.amazonaws.auth.BasicAWSCredentials;



public class RekogitionClientActivity extends AppCompatActivity {

    private static final String MODEL_ARN = "arn:aws:rekognition:us-east-2:637423630489:project/Urban-Explorer2/version/Urban-Explorer2.2024-07-03T12.13.10/1720001590980";
    private static final float MIN_CONFIDENCE = 50.0f;
    private static final String accessKey = "AKIAZI2LIXCMZVPJHSN2";
    private static final String secretKey = "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";

    private static final String BUCKET_NAME = "user-upload-urbanexplorer61b32-dev";





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekogition_client);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
//                getApplicationContext(),
//                "your_identity_pool_id",
//                Regions.US_EAST_2 // Replace with your region
//        );
//         AmazonRekognition rekognitionClient = new AmazonRekognitionClient(credentialsProvider);

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey,secretKey);
//
//
        AmazonRekognition rekognitionClient = new AmazonRekognitionClient(awsCredentials);
        rekognitionClient.setRegion(com.amazonaws.regions.Region.getRegion(US_EAST_2));
        AmazonS3 s3Client = new AmazonS3Client(awsCredentials, Region.getRegion(US_EAST_2));

        String PHOTO_KEY = getIntent().getStringExtra("filename");
        Log.i("PHOTO_KEY",PHOTO_KEY);

        String outputComparison = getIntent().getStringExtra("selected");
        Log.i("compare"," "+outputComparison);

        showCustomLabel(this,rekognitionClient,s3Client, BUCKET_NAME, PHOTO_KEY, MODEL_ARN, MIN_CONFIDENCE, outputComparison);

        String result = getIntent().getStringExtra("Rekgonition Output");
        Log.i("result"," "+result);
    }

    private void showCustomLabel(Context context, AmazonRekognition rekognitionClient, AmazonS3 s3Client, String BUCKET_NAME, String PHOTO_KEY, String MODEL_ARN, float MIN_CONFIDENCE, String compare){
        DetectCustomLabelsRequest request = new DetectCustomLabelsRequest()
                .withProjectVersionArn(MODEL_ARN)
                .withImage(new Image().withS3Object(new S3Object().withBucket(BUCKET_NAME).withName(PHOTO_KEY)))
                .withMinConfidence(MIN_CONFIDENCE);

        new RekognitionDetectCustomLabels(context,(AmazonRekognitionClient) rekognitionClient, request,compare).execute();
    }





}