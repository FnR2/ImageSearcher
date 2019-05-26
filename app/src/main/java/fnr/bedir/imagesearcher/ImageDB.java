package fnr.bedir.imagesearcher;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * CREATED BY bbedir on 2019-05-26.
 */


@Database(entities = {ProcessedImage.class}, version = 1)
public abstract class ImageDB extends RoomDatabase {
    public abstract ImageDao imageDao();
}
