import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// Testing Methods Here

        Formula1ChampionshipManager manager1=new Formula1ChampionshipManager();
        GUI gui=new GUI();
        try {
            manager1.readF1Driver();
        } catch (IOException var) {
            var.printStackTrace();
        }
        try {
            manager1.readCars();
        } catch (IOException var) {
            var.printStackTrace();
        }
        try {
            manager1.readRaces();
        } catch (IOException var) {
            var.printStackTrace();
        }


        Scanner scanner =new Scanner(System.in);

        boolean choice =true;

        while(choice) {
            try {

                System.out.println("--------------MAIN MENU--------------");


                System.out.println(" 1 - Create New Driver:\n");
                System.out.println(" 2 - Delete Driver: \n");
                System.out.println(" 3 - Change the Driver For Existing Team: \n");
                System.out.println(" 4 - Display Statistics for Existing Driver: \n");
                System.out.println(" 5 - Display Formula 1 Driver Table \n");
                System.out.println(" 6 - Add Race Completed \n");
                System.out.println(" 7 - Save File \n");
                System.out.println(" 8 - Start GUI \n");



                int selection = validUserInputNum("Enter Number ");

                switch (selection) {
                    case 1:
                        manager1.createNewDriver();
                        break;
                    case 2:
                        manager1.deleteDriver();
                        break;
                    case 3:
                        manager1.changeDriver();
                        break;
                    case 4:
                        manager1.displayStatistics();
                        break;
                    case 5:
                        manager1.displayTable();
                        break;
                    case 6:
                        manager1.addRaceCompleted();
                        break;

                    case 7:
                        manager1.saveF1Driver();
                        manager1.saveCars();
                        manager1.saveRaces();
                        break;

                    case 8:
                        gui.loadGui();
                        break;

                    default:
                        System.out.println("Invalid Input Please Try Again");
                }
                System.out.println("\n");

                System.out.println("Do You Want To Continue or Stop Enter Y to continue and N to stop :");

                String userDecision = scanner.next().toUpperCase();

                if (userDecision.equals("Y")) {
                    choice = true;
                } else if (userDecision.equals("N")) {
                    choice = false;
                } else {
                    System.out.println("Invalid Input Please Enter again");
                    System.out.println("\n");
                    System.out.println("Do You Want To Continue or Stop Enter Y to continue and N to stop :");
                    String secondChoice=scanner.next().toUpperCase();
                    if(secondChoice.equals("Y")){
                        choice=true;
                    }else if(secondChoice.equals("N")){
                        choice=false;
                    }else {
                        System.out.println("Invalid Input Please Run Again");
                        choice=false;}
                }
            }catch (Exception e){
                System.out.println("Invalid Input");
                choice=false;
            }
        }
    }
    public static int validUserInputNum(String display){
        Scanner scan=new Scanner(System.in);
        while(true){
            System.out.println(display);
            String input=scan.nextLine();

            try{
                int inputInt=Integer.parseInt(input);
                if(inputInt>0 && inputInt<=8){
                    return inputInt;
                }
                System.out.println("lower than 0 or Higher than 8 which is not valid");

            } catch (Exception e){
                System.out.println("Invalid Input");
            }
        }
    }
}
