import java.io.*;
import java.sql.SQLOutput;
import java.time.Year;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager {

    List<Formula1Driver> f1Drivers=new ArrayList<>();
    List <Cars> constructorsTeams=new ArrayList<>();
    List <Race> races=new ArrayList<>();


    Scanner scan=new Scanner(System.in);
    // Creating a new Driver

    @Override
    public void createNewDriver() {


        String driverName=validString("Enter Driver Name");
        String driverLocation=validString("Enter Driver Location");
        String driverTeam=validString("Enter drivers Team (Manufacturer/Constructor)");


        boolean teamHasDriver=false;

        for(int i=0;i<constructorsTeams.size();i++){
           if(constructorsTeams.get(i).getConstructorTeamName().equals(driverTeam)){
               teamHasDriver=true;
           }
        }

        if(!teamHasDriver){

            //Adding Constructor car and representing driver to the team
            Cars car =new Cars(driverTeam,driverName);
            constructorsTeams.add(car);
            System.out.println("Added "+driverName+" to "+driverTeam);

            //Adding Formula1Driver to the championship
            Formula1Driver formula1Driver=new Formula1Driver(driverName,driverLocation,driverTeam,0,0,0,0,0);
            f1Drivers.add(formula1Driver);
            System.out.println("Successfully Added the Driver To the Championship ");

        }else if(teamHasDriver){
            System.out.println("Cant add Two Drivers!! The Team You Entered already has a driver assigned");
        }

    }
    //Deleting a Driver
    @Override
    public void deleteDriver() {

        boolean driverDoesNotExist=true;
        String deleteDriver=validString("Enter the Driver You Wish To Delete ");

        for(int i=0;i<f1Drivers.size();i++){
            if(f1Drivers.get(i).getDriverName().equals(deleteDriver)){
                f1Drivers.remove(i);
                driverDoesNotExist=false;
            }
        }
        for(int i=0;i<constructorsTeams.size();i++){
            if(constructorsTeams.get(i).getRepresentingDriverName().equals(deleteDriver)){
                constructorsTeams.remove(i);
                System.out.println("Successfully Deleted the Driver and the Team from the championship");
            }
        }
        if(driverDoesNotExist){
            System.out.println("Driver does not exist to delete");
        }
    }

    //Changing an Existing Driver
    @Override
    public void changeDriver() {

        String changingDriver=null;
        boolean teamExists=false;


        String changeDriver=validString("Enter the Constructor Team you wish to change the Driver ");
        String newDriver=validString("Enter the new Driver Name ");
        //Changing the Driver
        for(int i=0;i<constructorsTeams.size();i++){
            if(constructorsTeams.get(i).getConstructorTeamName().equals(changeDriver)){
                changingDriver=constructorsTeams.get(i).getRepresentingDriverName();
                constructorsTeams.get(i).setRepresentingDriverName(newDriver);
                System.out.println("Successfully Changed the Driver for the team "+ changeDriver);
                teamExists=true;

            }

        }
        //Removing the Previous Driver
        if(teamExists){

            for(int i=0;i<f1Drivers.size();i++){
            if(f1Drivers.get(i).getDriverName().equals(changingDriver)){
                System.out.println("Successfully Removed the previous Driver "+f1Drivers.get(i).getDriverName()+" from team "+f1Drivers.get(i).getTeam()+" and Championship");
                f1Drivers.remove(i);
            }
            }
              String driverLocation=validString("Enter Driver Location ");


            Formula1Driver formula1Driver=new Formula1Driver(newDriver,driverLocation,changeDriver,0,0,0,0,0);
            f1Drivers.add(formula1Driver);
            System.out.println("Successfully Added the Changed Driver To the Championship");
        }else {
            System.out.println("Team does not exist to change the driver");
        }
    }



    @Override
    public void displayStatistics() {

        String displayStatistics=validString("Enter Driver Name to Display Statistics ");
        for(int i=0;i<f1Drivers.size();i++){
            if(f1Drivers.get(i).getDriverName().equals(displayStatistics)){
                System.out.println("Driver Name : "+f1Drivers.get(i).getDriverName());
                System.out.println("Driver Team : "+f1Drivers.get(i).getTeam());
                System.out.println("Driver Location : "+f1Drivers.get(i).getLocation());
                System.out.println("Driver First Positions : "+f1Drivers.get(i).getFirstPositions());
                System.out.println("Driver Second Positions: "+f1Drivers.get(i).getSecondPositions());
                System.out.println("Driver Third Positions : "+f1Drivers.get(i).getThirdPositions());
                System.out.println("Driver Points : "+f1Drivers.get(i).getPoints());
                System.out.println("Drivers Races Participated : "+f1Drivers.get(i).getRacesParticipated());
            }
        }

    }

    @Override
    public void displayTable() {

        Collections.sort(f1Drivers, Collections.reverseOrder());
        String leftAlignFormat= "| %-12s | %-11s | %-7d | %-4d |  %-4d | %-4d | %-5d|%n";
        System.out.format("+--------------+-------------+---------+------+-------+------+------+%n");
        System.out.format("|Driver Name   |   Team      | Points  |  RP  |  1st  | 2nd  |  3rd |%n");
        System.out.format("+--------------+-------------+---------+------+-------+------+------+%n");
        for(int i=0;i<f1Drivers.size();i++){
            System.out.format(leftAlignFormat,f1Drivers.get(i).getDriverName(),f1Drivers.get(i).getTeam(),f1Drivers.get(i).getPoints(),
                    f1Drivers.get(i).getRacesParticipated(),f1Drivers.get(i).getFirstPositions(),
                    f1Drivers.get(i).getSecondPositions(),f1Drivers.get(i).getThirdPositions());
        }
        System.out.format("+--------------+-------------+---------+------+-------+------+------+%n");

    }

// Adding Races
    @Override
    public void addRaceCompleted() {


        int year=validYear("Year of the Race ");

        int month=validMonth("Month of the Race ");

        int day=validDay("Day of the Race ");


        int position = validInt("Enter Driver Finish Position ");

        String name= validString("Enter Drivers Name ");


        Race race=new Race(year,month,day);
        races.add(race);

        boolean isTrue=true;

        while (isTrue) {

            for (int i = 0; i < f1Drivers.size(); i++) {
                if (f1Drivers.get(i).getDriverName().equals(name)) {
                    f1Drivers.get(i).raceParticipated();
                    f1Drivers.get(i).AddPoints(position);
                    System.out.println("Successfully Updated and Points added");
                    race.results.put(name,position);
                    race.driversParticipated.add(name);
                }
            }




                String answer = validString("Do you want to add Race Results for more Drivers ? Enter 'Y' for yes and 'N' for no");
                if (answer.equals("Y")) {
                    isTrue = true;


                    position = validInt("Enter Driver Finish Position ");

                    name= validString("Enter Drivers Name ");

                } else if (answer.equals("N")) {
                    isTrue = false;
                } else {
                    System.out.println("Invalid Input");
                    break;
                }
           // }
       }
    }

    //Saving F1Driver
    @Override
    public void saveF1Driver() throws IOException {
        File F1DriverFile=new File("F1Driver.txt");
        PrintWriter F1DriverPrint=new PrintWriter("F1Driver.txt");
        F1DriverPrint.print("");
        FileOutputStream newF1Driver=new FileOutputStream(F1DriverFile,true);
        ObjectOutputStream F1DriverRecord=new ObjectOutputStream(newF1Driver);
        Iterator iterator = f1Drivers.iterator();
        while(iterator.hasNext()){
            Formula1Driver f1obj=(Formula1Driver) iterator.next();
            F1DriverRecord.writeObject(f1obj);
            F1DriverPrint.close();
        }
        System.out.println("Successfully Saved F1 Drivers");
    }

    //Reading F1Driver
    @Override
    public void readF1Driver() throws IOException {
        FileInputStream F1DriverRead=new FileInputStream("F1Driver.txt");
        ObjectInputStream driverobj=new ObjectInputStream(F1DriverRead);
        ArrayList<Formula1Driver> readRecordF1=new ArrayList<>();

        while(true){
            try{
                Formula1Driver driverTest= (Formula1Driver) driverobj.readObject();
                readRecordF1.add(driverTest);
            }catch (IOException|ClassNotFoundException e){
                break;
            }
        }
        f1Drivers.addAll(readRecordF1);
        System.out.println("Successfully Read/Loaded F1 Drivers");
    }

    //Saving Cars
    @Override
    public void saveCars() throws IOException {
        File CarFile =new File("Cars.txt");
        PrintWriter CarPrint =new PrintWriter("Cars.txt");
        CarPrint.print("");
        FileOutputStream newCar =new FileOutputStream(CarFile,true);
        ObjectOutputStream CarsRecord=new ObjectOutputStream(newCar);
        Iterator iterator=constructorsTeams.iterator();
        while (iterator.hasNext()){
            Cars carobj=(Cars) iterator.next();
            CarsRecord.writeObject(carobj);
            CarPrint.close();
        }
        System.out.println("Successfully Saved Cars");
    }

    //Reading Cars
    @Override
    public void readCars() throws IOException {
        FileInputStream CarsRead=new FileInputStream("Cars.txt");
        ObjectInputStream readobjcar=new ObjectInputStream(CarsRead);
        ArrayList<Cars> readRecordCars=new ArrayList<>();

        while(true){
            try{
                Cars CarTest= (Cars) readobjcar.readObject();
                readRecordCars.add(CarTest);
            }catch (IOException|ClassNotFoundException e){
                break;
            }
        }
        constructorsTeams.addAll(readRecordCars);
        System.out.println("Successfully Read/Loaded Cars");
    }


    // Saving Races
    @Override
    public void saveRaces() throws IOException {
        File RaceFile =new File("Races.txt");
        PrintWriter RacePrint =new PrintWriter("Races.txt");
        RacePrint.print("");
        FileOutputStream newRace =new FileOutputStream(RaceFile,true);
        ObjectOutputStream RacesRecord=new ObjectOutputStream(newRace);
        Iterator iterator=races.iterator();
        while (iterator.hasNext()){
            Race raceobj=(Race) iterator.next();
            RacesRecord.writeObject(raceobj);
            RacePrint.close();
    }
        System.out.println("Successfully Saved Races");
    }

    // Reading Races
    @Override
    public void readRaces() throws IOException {
            FileInputStream RacesRead=new FileInputStream("Races.txt");
            ObjectInputStream racesobj=new ObjectInputStream(RacesRead);
            ArrayList<Race> readRecordRaces=new ArrayList<>();

            while(true){
                try{
                    Race racesTest= (Race) racesobj.readObject();
                    readRecordRaces.add(racesTest);
                }catch (IOException|ClassNotFoundException e){
                    break;
                }
            }
            races.addAll(readRecordRaces);
            System.out.println("Successfully Read/Loaded Races");
        }

        //---------------------------------------------------------------------------------------------------------------------------------------------

    //Validating String inputs
    // Regex pattern matching W3 Schools LINK- https://www.w3schools.com/java/java_regex.asp JAVA POINT LINK - https://www.javatpoint.com/java-regex

        public String validString(String display){
        while(true){
            String regex ="^[a-zA-Z]+$";
            System.out.println(display);
            String input=scan.nextLine().toUpperCase();
            if(input.matches(regex)){
                return input;
            }
            System.out.println("Entered input is invalid");
        }
    }

      //Validating Integer inputs

        public int validInt(String display){
        while(true){
            System.out.println(display);
            String input=scan.nextLine();

        try{
            int inputInt=Integer.parseInt(input);
            if(inputInt>0){
            return inputInt;
            }
            System.out.println("lower than 0 or equal to 0 which is not valid");

          } catch (Exception e){
            System.out.println("Invalid Input");
          }
        }
    }
        //Validating Year
        public int validYear(String display){
            while(true){
                System.out.println(display);
                String input=scan.nextLine();

                try{
                    int inputYear=Integer.parseInt(input);
                    if(inputYear>1950 && inputYear<2050){
                        return inputYear;
                    }
                    System.out.println("Enter a Realistic Year");

                } catch (Exception e){
                    System.out.println("Invalid Input");
                }
            }
    }
        //Validating Month
        public int validMonth(String display){
            while(true){
                System.out.println(display);
                String input=scan.nextLine();

                try{
                    int inputMonth=Integer.parseInt(input);
                    if(inputMonth>=1 && inputMonth<=12){
                        return inputMonth;
                    }
                    System.out.println("Enter a Month between 1 to 12");

                } catch (Exception e){
                    System.out.println("Invalid Input");
                }
            }
        }
        //Validating Day
        public int validDay(String display){
            while(true){
                System.out.println(display);
                String input=scan.nextLine();

                try{
                    int inputDay=Integer.parseInt(input);
                    if(inputDay>=1 && inputDay<=31){
                        return inputDay;
                    }
                    System.out.println("Enter a Date Between 1 to 31");

                } catch (Exception e){
                    System.out.println("Invalid Input");
                }
            }
        }

}
