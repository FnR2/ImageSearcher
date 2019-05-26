package fnr.bedir.imagesearcher;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * CREATED BY bbedir on 2019-05-26.
 */

@Dao
public interface ImageDao {


    @Query("SELECT * FROM PROCESSEDIMAGE WHERE processed_text LIKE :searchText AND " +
            "process_result = 1 ")
    List<ProcessedImage> searchImage (String searchText);

    @Insert
    void insertImage(ProcessedImage image);


    @Query("SELECT * FROM PROCESSEDIMAGE")
    List<ProcessedImage> getAllImages ();

}
