package fnr.bedir.imagesearcher;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * CREATED BY bbedir on 2019-05-26.
 */
public class ProcessHelper implements OnSuccessListener<FirebaseVisionText>, OnFailureListener {


    private static FirebaseVisionTextRecognizer detector;
    private static ProcessHelper processHelper = null;
    private Context context;
    private List<String> filePathList;
    private int index;
    private ProcessFinishedListener listener;

    private ProcessHelper(Context context, ProcessFinishedListener listener) {

        this.context = context;
        this.listener = listener;
        detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        index = 0;
    }

    public static ProcessHelper getInstance(Context context, ProcessFinishedListener listener) {

        if (processHelper == null) {
            synchronized (ProcessHelper.class) {
                processHelper = new ProcessHelper(context, listener);
            }
        }
        return processHelper;
    }

    public void startOCRProcess() {

        filePathList = FileHelper.getAllImagesFromDevice(context);
        if (filePathList.size() == 0) {
            listener.processStartFailed();
        } else {
            processImage(index);
        }


    }

    private void processImage(int index) {

        if (index < filePathList.size() - 1) {
            try {
                FirebaseVisionImage image = FirebaseVisionImage.fromFilePath(context, Uri.fromFile(new File(filePathList.get(index))));
                detector.processImage(image).addOnSuccessListener(this).addOnFailureListener(this);
            } catch (IOException e) {
                e.printStackTrace();
                onFailure(e);
            }
        } else {

            listener.processCompletelyFinished();
        }


    }

    @Override
    public void onFailure(@NonNull Exception e) {



        index = index + 1;
        processImage(index);

    }

    @Override
    public void onSuccess(FirebaseVisionText text) {

        String path = filePathList.get(index);


        String result = FileHelper.getProcessResult(text);
        ProcessedImage image = new ProcessedImage(path, result, true);

        listener.singleProcessFinished(image);
        index = index + 1;
        processImage(index);
    }
}
