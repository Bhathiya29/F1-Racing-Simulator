import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Race implements Serializable,Comparable<Race>{

    int raceYear;
    int raceMonth;
    int raceDay;



    HashMap<String,Integer> results=new HashMap<>();

    List<String> driversParticipated=new ArrayList<>();


    public Race(int raceYear, int raceMonth, int raceDay) {
        this.raceYear = raceYear;
        this.raceMonth = raceMonth;
        this.raceDay = raceDay;
    }


    public int getRaceYear() {
        return raceYear;
    }

    public void setRaceYear(int raceYear) {
        raceYear = raceYear;
    }

    public int getRaceMonth() {
        return raceMonth;
    }

    public void setRaceMonth(int raceMonth) {
        raceMonth = raceMonth;
    }

    public int getRaceDay() {
        return raceDay;
    }

    public void setRaceDay(int raceDay) {
        raceDay = raceDay;
    }

    public String getFullRaceDate(){
        String fullDate=raceYear+","+raceMonth+","+raceDay;
        return fullDate;
    }

    @Override
    public int compareTo(Race o) {
        if(this.raceYear==o.raceYear){
            if(this.raceMonth==o.raceMonth){
                return this.raceDay-o.raceDay;
            }else {
                return this.raceMonth-o.raceMonth;
            }
        }
        return this.raceYear-o.raceYear;
    }

}

