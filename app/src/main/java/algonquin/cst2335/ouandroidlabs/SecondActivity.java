package algonquin.cst2335.ouandroidlabs;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

import org.xmlpull.v1.XmlPullParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class SecondActivity extends AppCompatActivity {

   private Button buttonCall;
   private Button buttonPicture;
   private EditText phoneNumberEditText;
   private ImageView imgView;
   private Bitmap profile;
   private static final int REQUEST_IMAGE_CAPTURE=1;
    Intent cameraIntent;


    ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override

                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent data = result.getData();
                        Bundle extras=data.getExtras();
                        if (extras != null) {
                            Bitmap thumbnail = (Bitmap) extras.get("data");
                            if (thumbnail != null) {
                                imgView.setImageBitmap(thumbnail);
                                saveProfilePictureToDisk(thumbnail); // Save the image to disk
                            }
                        }
                    }}
                });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        TextView textViewEmail = findViewById(R.id.textViewEmail);

        textViewEmail.setText("Welcome back " + emailAddress);


        buttonCall = findViewById(R.id.buttonCall);
        phoneNumberEditText = findViewById(R.id.editTextPhone);



        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNumber = phoneNumberEditText.getText().toString();

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));

                savePhoneNumberToSharedPreferences(phoneNumber);

                // Start the phone call activity
                startActivity(callIntent);
            }
        });
        buttonPicture = findViewById(R.id.buttonPicture);
        imgView = findViewById(R.id.imageView);
        cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        buttonPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraResult.launch(cameraIntent);
            }
        });
    }
    private void saveProfilePictureToDisk(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                File file = new File(getFilesDir(), "profile_picture.png");
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void savePhoneNumberToSharedPreferences(String phoneNumber) {
        SharedPreferences prefs = getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("TelephoneNumber", phoneNumber);
        editor.apply();
    }


    private void loadPhoneNumberFromSharedPreferences() {
        SharedPreferences prefs = getSharedPreferences("MyData", MODE_PRIVATE);
        String phoneNumber = prefs.getString("TelephoneNumber", "");

        phoneNumberEditText.setText(phoneNumber);
    }
}
