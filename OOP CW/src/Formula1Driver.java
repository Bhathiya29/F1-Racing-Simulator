import java.io.Serializable;
import java.util.Comparator;

public class Formula1Driver extends Driver implements Serializable, Comparable<Formula1Driver>{



    private int firstPositions;
    private int secondPositions;
    private int thirdPositions;

    private int points=0;
    private int racesParticipated=0;

    //Points Awarded 1:25 2:18 3:15 4:12 5:10 6:8 7:6 8:4 9:2 10:1


    public Formula1Driver(String constructorTeamName, String representingDriverName, String driverName, String driverLocation, int firstPositions,
                          int secondPositions, int thirdPositions, int points, int racesParticipated)
    {
        super(constructorTeamName, representingDriverName, driverLocation);
        this.firstPositions = firstPositions;
        this.secondPositions = secondPositions;
        this.thirdPositions = thirdPositions;
        this.points = points;
        this.racesParticipated = racesParticipated;
    }

    public Formula1Driver(String driverName, String driverLocation,String team, int firstPositions, int secondPositions,
                          int thirdPositions, int points, int racesParticipated)
    {
        super(driverName,driverLocation,team);
        this.firstPositions = firstPositions;
        this.secondPositions = secondPositions;
        this.thirdPositions = thirdPositions;
        this.points = points;
        this.racesParticipated = racesParticipated;
    }


    public Formula1Driver(){
        super();
    }

    @Override
    public void driverSkillLevel() {
        System.out.println("Formula 1 Drivers are Experts in motor racing");
    }


    public int getFirstPositions() {
        return firstPositions;
    }

    public void setFirstPositions(int firstPositions) {
        this.firstPositions= firstPositions;
    }

    public int getSecondPositions() {
        return secondPositions;
    }

    public void setSecondPositions(int secondPositions) {
        this.secondPositions = secondPositions;
    }

    public int getThirdPositions() {
        return thirdPositions;
    }

    public void setThirdPositions(int thirdPositions) {
        this.thirdPositions = thirdPositions;
    }

    public int getPoints() {
        return points;
    }

    public void AddPoints(int position) {
        switch (position){
            case 1:
                this.points+=25;
                this.firstPositions++;
                break;
            case 2:
                this.points+=18;
                this.secondPositions++;
                break;
            case 3:
                this.points+=15;
                this.thirdPositions++;
                break;
            case 4:
                this.points+=12;
                break;
            case 5:
                this.points+=10;
                break;
            case 6:
                this.points+=8;
                break;
            case 7:
                this.points+=6;
                break;

            case 8:
                this.points+=4;
                break;

            case 9:
                this.points+=2;
                break;

            case 10:
                this.points+=1;
                break;
            default:
                this.points+=0;
                System.out.println("No points added after 10th Position ");
        }
    }

    public int getRacesParticipated() {
        return racesParticipated;
    }

    public void raceParticipated(){
        racesParticipated++;
    }


    @Override
    public int compareTo(Formula1Driver o) {
        if(this.points==o.points){
            //return 0;
            //If points are equal then it will sort according to first points won
            if(this.getFirstPositions()==o.getFirstPositions()){
                return 0;

            }else if(this.getFirstPositions()< o.getFirstPositions()){
                return -1;
            }else {
                return 1;
            }
        }else if(this.points<o.points){
            return -1;
        }else {
            return 1;
        }


    }

    public static Comparator<Formula1Driver> compareByPosition=new Comparator<Formula1Driver>() {
        @Override
        public int compare(Formula1Driver o1, Formula1Driver o2) {
            if(o1.getFirstPositions()==o2.getFirstPositions()){
                //return 0;
                //Sorts by points if No of firstPositions are equal
                if(o1.points==o2.points){
                    return 0;
                }else if(o1.points<o2.points){
                    return -1;
                }else {
                    return 1;
                }
            }else if(o1.getFirstPositions()< o2.getFirstPositions()){
                return -1;
            }else {
                return 1;
            }
        }
    };
}
