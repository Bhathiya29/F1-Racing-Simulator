import java.io.IOException;

public interface ChampionshipManager {

    public void createNewDriver();

    public void deleteDriver();

    public void changeDriver();

    public void displayStatistics();

    public void displayTable();

    public void addRaceCompleted();


    public void saveF1Driver()throws IOException;

    public void readF1Driver()throws IOException;

    public void saveCars()throws IOException;

    public void readCars()throws IOException;

    public void saveRaces()throws IOException;

    public void readRaces()throws IOException;



}
