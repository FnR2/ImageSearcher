package fnr.bedir.imagesearcher;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.ArrayList;
import java.util.List;

import pl.kitek.timertextview.TimerTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ProcessFinishedListener {


    private final int READ_STORAGE = 12;

    private Context context;
    FloatingActionButton fab;
    TimerTextView timerText;
    TextView tv;
    TextView tvindex;
    TextView tvtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        long futureTimestamp = System.currentTimeMillis() + (10 * 60 * 60 * 1000);
        timerText = (TimerTextView) findViewById(R.id.timerText);
        timerText.setEndTime(futureTimestamp);
        fab = findViewById(R.id.fab);
        tv = findViewById(R.id.tvocr);
        tvindex = findViewById(R.id.tindex);
        fab.setOnClickListener(this);
        context = getApplicationContext();

        tvtotal = findViewById(R.id.ttoal);
        context = getApplicationContext();
        //checkPermission();



    }


    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        READ_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {

            //ProcessHelper.getInstance(getApplicationContext(), this).startOCRProcess();
            //getAllImagesFromDevice();
            // Permission has already been granted
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case READ_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ProcessHelper.getInstance(getApplicationContext(), this).startOCRProcess();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


    @Override
    public void onClick(View view) {


    }

    @Override
    public void processCompletelyFinished() {

    }

    @Override
    public void processStartFailed() {

    }

    @Override
    public void singleProcessFinished(ProcessedImage image) {


        DBRepository.getInstance(context).getAppDatabase().imageDao().insertImage(image);

        List<ProcessedImage> imageList = DBRepository.getInstance(context).getAppDatabase().imageDao().getAllImages();
        Log.e("dbsize", imageList.size() + "");

    }
}
