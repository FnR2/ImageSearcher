package fnr.bedir.imagesearcher;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * CREATED BY bbedir on 2019-05-26.
 */
public class DBRepository {

    private static DBRepository repository;
    private Context context;
    private ImageDB appDatabase;

    private DBRepository(Context context) {
        this.context = context;
        appDatabase = Room.databaseBuilder(context, ImageDB.class, "Images").allowMainThreadQueries().build();
    }


    public static synchronized DBRepository getInstance(Context context) {
        if (repository == null) {
            repository = new DBRepository(context);
        }
        return repository;
    }

    public ImageDB getAppDatabase() {
        return appDatabase;
    }

}
