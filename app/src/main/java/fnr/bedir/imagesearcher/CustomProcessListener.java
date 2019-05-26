package fnr.bedir.imagesearcher;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.text.FirebaseVisionText;

/**
 * CREATED BY bbedir on 2019-05-26.
 */
public class CustomProcessListener implements OnSuccessListener<FirebaseVisionText>, OnFailureListener {



    public CustomProcessListener(){

    }

    @Override
    public void onFailure(@NonNull Exception e) {

    }

    @Override
    public void onSuccess(FirebaseVisionText text) {

    }
}
