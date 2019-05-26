package fnr.bedir.imagesearcher;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

import java.util.ArrayList;
import java.util.List;

/**
 * CREATED BY bbedir on 2019-05-26.
 */
class FileHelper {


    static String getProcessResult(FirebaseVisionText texts) {

        List<FirebaseVisionText.TextBlock> blocks = texts.getTextBlocks();
        if (blocks.size() != 0) {

            StringBuilder builder = new StringBuilder();

            for (FirebaseVisionText.TextBlock textBlock : blocks
            ) {

                List<FirebaseVisionText.Line> lines = textBlock.getLines();

                for (FirebaseVisionText.Line line : lines
                ) {

                    List<FirebaseVisionText.Element> elementList = line.getElements();

                    for (FirebaseVisionText.Element element : elementList
                    ) {

                        builder.append(element.getText());
                        builder.append(" ");
                    }
                }
            }

            return builder.toString();
        }

        return "";


    }


     static List<String> getAllImagesFromDevice(Context context) {

        int count = 0;
        List<String> filePathList = new ArrayList<>();
        String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        String orderBy = MediaStore.Images.Media._ID;

        String where = MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=?";


        String[] whereArgs = {"image/jpeg", "image/png", "image/jpg"};
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, where,
                whereArgs, orderBy);


        if (cursor != null) {
            count = cursor.getCount();
        }


        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

            filePathList.add(cursor.getString(dataColumnIndex));

        }

        if (cursor != null) {
            cursor.close();
        }

        return filePathList;

    }


    private static String getFileExtension(String fileName) {

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }
}
