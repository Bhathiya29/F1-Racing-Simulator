import java.io.Serializable;

public class Cars implements Serializable {

    private String constructorTeamName ;
    private String representingDriverName;



    public Cars(String car,String representingDriverName){
        this.constructorTeamName=car;
        this.representingDriverName=representingDriverName;
    }

    public Cars() {

    }



    public void setRepresentingDriverName(String representingDriverName) {
        this.representingDriverName = representingDriverName;
    }

    public String getRepresentingDriverName() {

        return representingDriverName;
    }

    public void setConstructorTeamName(String constructorTeamName){

        this.constructorTeamName=constructorTeamName;
    }

    public String getConstructorTeamName() {

        return constructorTeamName;
    }
}
