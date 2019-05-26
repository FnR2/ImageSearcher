package fnr.bedir.imagesearcher;

/**
 * CREATED BY bbedir on 2019-05-26.
 */
public interface ProcessFinishedListener {

    void processCompletelyFinished();

    void processStartFailed();

    void singleProcessFinished(ProcessedImage image);
}
