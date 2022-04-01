import java.io.Serializable;

public abstract class Driver extends Cars {

    private String driverName;
    private String driverLocation;
    private String team;



    public Driver(String constructorTeamName,String representingDriverName,String driverName, String driverLocation,String team ) {
        super(constructorTeamName,representingDriverName);
        this.driverName = driverName;
        this.driverLocation = driverLocation;
        this.team=team;

    }

    public Driver(String driverName, String driverLocation, String team){
        super();
        this.driverName=driverName;
        this.driverLocation=driverLocation;
        this.team=team;
    }

    public Driver() {

    }


    public void setName(String name){
        this.driverName=name;
    }

    public  String getDriverName(){
        return driverName;
    };

    public  void setLocation(String location){
        this.driverLocation=location;
    };

    public String getLocation(){
        return driverLocation;
    };

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public abstract void driverSkillLevel();

    }




