import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GUI {
    //public static void main(String[]args) {

    // loads gui instead of main according to the main menu
    public  void loadGui(){
        Formula1ChampionshipManager guimanager = new Formula1ChampionshipManager();

        try {
            guimanager.readF1Driver();
        } catch (IOException var) {
            var.printStackTrace();
        }
        try {
            guimanager.readCars();
        } catch (IOException var) {
            var.printStackTrace();
        }
        try {
            guimanager.readRaces();
        } catch (IOException var) {
            var.printStackTrace();
        }


        JFrame frame = new JFrame("F1 Racing Championship");
        frame.setSize(1500, 1000);




        JButton button1=new JButton("Sort ASCE");
        JButton button2=new JButton("Sort 1st pos DESC");
        JButton button3=new JButton("Generate Race");
        JButton button4=new JButton("Probability Race");
        JButton button5=new JButton("Races in ASCE");
        JButton button6=new JButton("Search");


        JTextArea area1=new JTextArea();
        JTextArea area2=new JTextArea();
        JTextArea area3=new JTextArea();
        JTextField search=new JTextField(15);

        JLabel label1=new JLabel();
        JLabel label2=new JLabel();





        // Displaying the Table in descending order of points

        String headers[] = {"Driver Name", " Team  ", "Points", "RP ", "1st", "2nd ", " 3rd "};
        String[][]data=new String[guimanager.f1Drivers.size()][7];

        //Table
        DefaultTableModel model = new DefaultTableModel(data,headers);
        JTable table1 = new JTable(model);

        for(int i=0;i<guimanager.f1Drivers.size();i++){
              String s1=guimanager.f1Drivers.get(i).getDriverName();
              String s2=guimanager.f1Drivers.get(i).getTeam();
              String s3=Integer.toString(guimanager.f1Drivers.get(i).getPoints());
              String s4=Integer.toString(guimanager.f1Drivers.get(i).getRacesParticipated());
              String s5=Integer.toString(guimanager.f1Drivers.get(i).getFirstPositions());
              String s6=Integer.toString(guimanager.f1Drivers.get(i).getSecondPositions());
              String s7=Integer.toString(guimanager.f1Drivers.get(i).getThirdPositions());

            model.insertRow(i,new Object[]{s1,s2,s3,s4,s5,s6,s7});
        }

        table1.setBounds(30, 30, 75,75);
        JScrollPane sp = new JScrollPane(table1);
        frame.add(sp);




        //Sorting the table according to points won by driver Ascending Order
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                Collections.sort(guimanager.f1Drivers);

                model.setRowCount(0);
                //setRowCount was learned from stack overflow
                //LINK-https://stackoverflow.com/questions/3179136/jtable-how-to-refresh-table-model-after-insert-delete-or-update-the-data

                //Updates the table according to the sorted
                for(int i=0;i<guimanager.f1Drivers.size();i++){
                    String s1=guimanager.f1Drivers.get(i).getDriverName();
                    String s2=guimanager.f1Drivers.get(i).getTeam();
                    String s3=Integer.toString(guimanager.f1Drivers.get(i).getPoints());
                    String s4=Integer.toString(guimanager.f1Drivers.get(i).getRacesParticipated());
                    String s5=Integer.toString(guimanager.f1Drivers.get(i).getFirstPositions());
                    String s6=Integer.toString(guimanager.f1Drivers.get(i).getSecondPositions());
                    String s7=Integer.toString(guimanager.f1Drivers.get(i).getThirdPositions());

                    model.insertRow(i,new Object[]{s1,s2,s3,s4,s5,s6,s7});
                }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        //Sorting the table according to the largest number of first positions won Descending Order
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {


                    Collections.sort(guimanager.f1Drivers, Formula1Driver.compareByPosition.reversed());

                    model.setRowCount(0);

                    //Updates the table according to largest Number of first positions
                    for (int i = 0; i < guimanager.f1Drivers.size(); i++) {
                        String s1 = guimanager.f1Drivers.get(i).getDriverName();
                        String s2 = guimanager.f1Drivers.get(i).getTeam();
                        String s3 = Integer.toString(guimanager.f1Drivers.get(i).getPoints());
                        String s4 = Integer.toString(guimanager.f1Drivers.get(i).getRacesParticipated());
                        String s5 = Integer.toString(guimanager.f1Drivers.get(i).getFirstPositions());
                        String s6 = Integer.toString(guimanager.f1Drivers.get(i).getSecondPositions());
                        String s7 = Integer.toString(guimanager.f1Drivers.get(i).getThirdPositions());

                        model.insertRow(i, new Object[]{s1, s2, s3, s4, s5, s6, s7});
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        //Generates one random race with random positions for the existing drivers
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {



                Random rand = new Random();


                int raceYear = rand.nextInt((2021 - 2010) + 1) + 2010;
                int raceMonth = rand.nextInt((12 - 1) + 1) + 1;
                int raceDay = rand.nextInt((30 - 1) + 1) + 1;



                Race race = new Race(raceYear, raceMonth, raceDay);
                HashMap<String, Integer> randomResults = new HashMap<>();
                List<Integer> addedNumbers = new ArrayList<>();

                int i = 0;
                while (i < guimanager.f1Drivers.size()) {

                    String driverName = guimanager.f1Drivers.get(i).getDriverName();
                    int finishedPosition = rand.nextInt((guimanager.f1Drivers.size() - 1) + 1) + 1;

                    if (!addedNumbers.contains(finishedPosition)) {
                        guimanager.f1Drivers.get(i).raceParticipated();
                        guimanager.f1Drivers.get(i).AddPoints(finishedPosition);
                        System.out.println("Successfully Updated and Points added");
                        randomResults.put(driverName, finishedPosition);
                        race.results.put(driverName, finishedPosition);
                        race.driversParticipated.add(driverName);
                        addedNumbers.add(finishedPosition);
                        i++;
                    }
                }
                guimanager.races.add(race);

                model.setRowCount(0);

                //Updates the table
                for (int m = 0; m < guimanager.f1Drivers.size(); m++) {
                    String s1 = guimanager.f1Drivers.get(m).getDriverName();
                    String s2 = guimanager.f1Drivers.get(m).getTeam();
                    String s3 = Integer.toString(guimanager.f1Drivers.get(m).getPoints());
                    String s4 = Integer.toString(guimanager.f1Drivers.get(m).getRacesParticipated());
                    String s5 = Integer.toString(guimanager.f1Drivers.get(m).getFirstPositions());
                    String s6 = Integer.toString(guimanager.f1Drivers.get(m).getSecondPositions());
                    String s7 = Integer.toString(guimanager.f1Drivers.get(m).getThirdPositions());

                    model.insertRow(m, new Object[]{s1, s2, s3, s4, s5, s6, s7});
                }

                area1.setRows(0);
                randomResults.entrySet().forEach(entry -> {
                    area1.append(" Name " + entry.getKey() + " =>  Position - " + entry.getValue() + "\n");
                });

            } catch (Exception ex){
                    ex.printStackTrace();
                }}
        });

        //Generates a race according to the probability of the starting position
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

try{
                Random rand =new Random();


                int raceYear= rand.nextInt((2021-2010)+1)+2010;
                int raceMonth=rand.nextInt((12-1)+1)+1;
                int raceDay=rand.nextInt((30-1)+1)+1;

                String fullDate=Integer.toString(raceYear)+Integer.toString(raceMonth)+Integer.toString(raceDay);

                Race race=new Race(raceYear,raceMonth,raceDay);
                HashMap<String,Integer>probabilityResults=new HashMap<>();
                String[]startingLineUp=new String[guimanager.f1Drivers.size()];

                int x=0;
                List<Integer>generatedNumbers=new ArrayList<>();


                while(x<guimanager.f1Drivers.size()){
                    int startingPosition=rand.nextInt(((guimanager.f1Drivers.size()-1)-0)+1)+0;

                    if(!generatedNumbers.contains(startingPosition)){
                        startingLineUp[startingPosition]=guimanager.f1Drivers.get(x).getDriverName();
                        generatedNumbers.add(startingPosition);
                        x++;
                    }
                }

                int[] arr=new int[9];
                int[]freq={40,30,10,10,2,2,2,2,2};

                 int i,n = arr.length;

                int winner=myRand(arr, freq, n);
                System.out.println("Starting LineUP "+ Arrays.toString(startingLineUp));
                System.out.println(winner );
                System.out.println(startingLineUp[winner]);

                String Place1=startingLineUp[winner];

                //Adding the winner
                for(int z=0;z<guimanager.f1Drivers.size();z++){
                    if(guimanager.f1Drivers.get(z).getDriverName().equals(Place1)){

                        guimanager.f1Drivers.get(z).raceParticipated();
                        guimanager.f1Drivers.get(z).AddPoints(1);

                        probabilityResults.put(guimanager.f1Drivers.get(z).getDriverName(),1);
                        race.results.put(guimanager.f1Drivers.get(z).getDriverName(),1);
                        race.driversParticipated.add(guimanager.f1Drivers.get(z).getDriverName());
                        System.out.println("Successfully Updated winner and Points added of "+Place1);
                    }
                }



                //Adding the rest of the results from second onwards
                List<Integer>existingNumbers=new ArrayList<>();
                int y=0;
                while(y<guimanager.f1Drivers.size()){

                    String driverName=guimanager.f1Drivers.get(y).getDriverName();
                    int finishPosition=rand.nextInt((guimanager.f1Drivers.size()- 2) + 1) + 2;

                    if(!driverName.equals(Place1)&&!existingNumbers.contains(finishPosition)){
                        guimanager.f1Drivers.get(y).raceParticipated();
                        guimanager.f1Drivers.get(y).AddPoints(finishPosition);
                        System.out.println("Successfully Updated and Points added for "+guimanager.f1Drivers.get(y).getDriverName());
                        probabilityResults.put(driverName,finishPosition);
                        race.results.put(driverName,finishPosition);
                        race.driversParticipated.add(driverName);
                        existingNumbers.add(finishPosition);
                        y++;
                    }else if(driverName.equals(Place1)){
                        y++;
                    }
                }

                guimanager.races.add(race);

                String s="test line";


                model.setRowCount(0);

                //Updates the table with probability race results
                for(int o=0;o<guimanager.f1Drivers.size();o++){
                    String s1=guimanager.f1Drivers.get(o).getDriverName();
                    String s2=guimanager.f1Drivers.get(o).getTeam();
                    String s3=Integer.toString(guimanager.f1Drivers.get(o).getPoints());
                    String s4=Integer.toString(guimanager.f1Drivers.get(o).getRacesParticipated());
                    String s5=Integer.toString(guimanager.f1Drivers.get(o).getFirstPositions());
                    String s6=Integer.toString(guimanager.f1Drivers.get(o).getSecondPositions());
                    String s7=Integer.toString(guimanager.f1Drivers.get(o).getThirdPositions());

                    model.insertRow(o,new Object[]{s1,s2,s3,s4,s5,s6,s7});
                }

                //Displays probability race results
                    area2.setRows(0);

                    probabilityResults.entrySet().forEach( entry -> {
                        area2.append( " Name "+entry.getKey() + " =>  Position - " + entry.getValue()+"\n");
                    });

            }
catch (Exception ex){
    ex.printStackTrace();
}
            }
        });


        //Displays all the completed races sorted in ascending order of the date

        String header[] = {" Date of the Race "};
        String[][]dataa=new String[guimanager.races.size()][1];

        DefaultTableModel model1 = new DefaultTableModel(dataa,header);
        JTable table2 = new JTable(model1);
        table2.setBounds(10, 12, 25, 25);
        JScrollPane sp1 = new JScrollPane(table2);
        frame.add(sp1);
        sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);




        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {



                    //Sorting the Races according to the date in ascending order
                    Collections.sort(guimanager.races);


                    model1.setRowCount(0);
                    for (int i = 0; i < guimanager.races.size(); i++) {
                        model1.insertRow(i, new Object[]{guimanager.races.get(i).getFullRaceDate()});
                    }



                }catch (Exception ex){
                    ex.printStackTrace();
                }}

        });


        //Search for all races that a given driver participated
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    //Getting the driver name
                    String s = search.getText().toUpperCase();


                    HashMap<String, Integer> displayData = new HashMap<>();
                    //Searching for the drivers races participated and displaying it
                    String key = null;
                    int value = 0;
                    for (int i = 0; i < guimanager.races.size(); i++) {
                        if (guimanager.races.get(i).driversParticipated.contains(s)) {
                            key = guimanager.races.get(i).getFullRaceDate();
                        }
                        if (guimanager.races.get(i).results.containsKey(s)) {
                            value = guimanager.races.get(i).results.get(s);
                        }
                        displayData.put(key, value);
                    }

                    area3.setRows(0);
                    displayData.entrySet().forEach(entry -> {
                        area3.append(" Date - " + entry.getKey() + " =>  Position - " + entry.getValue() + "\n");
                    });
                    area3.append("-----------------------------------------\n");

                    //hashmap printing reference
                    // hMapNumbers.entrySet().forEach( entry -> {
                    //    System.out.println( entry.getKey() + " => " + entry.getValue() );
                    //});
                    //LINK -https://www.javacodeexamples.com/java-print-hashmap-example/2243
                }catch (Exception ex){
                    ex.printStackTrace();
                }}
        });




        frame.setLayout(new BorderLayout(20, 15));
        search.setBounds(50,100, 150,30);





        frame.add(button1);
        frame.add(button2);

        frame.add(button3);
        frame.add(area1);
        frame.add(area2);
        frame.add(button4);

        frame.add(button5);

        frame.add(search);
        frame.add(button6);
        frame.add(area3);

        frame.add(label1);
        frame.add(label2);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT,20,30));

    }


    //Methods to Generate Probabilistic Race
    //Reference -GeekForGeeks
    //LINK -https://www.geeksforgeeks.org/random-number-generator-in-arbitrary-probability-distribution-fashion/?ref=lbp

    // Java program to generate random numbers
    // according to given frequency distribution
   /* class GFG
    {

        // Utility function to find ceiling of r in arr[l..h]
        static int findCeil(int arr[], int r, int l, int h)
        {
            int mid;
            while (l < h)
            {
                mid = l + ((h - l) >> 1); // Same as mid = (l+h)/2
                if(r > arr[mid])
                    l = mid + 1;
                else
                    h = mid;
            }
            return (arr[l] >= r) ? l : -1;
        }

        // The main function that returns a random number
// from arr[] according to distribution array
// defined by freq[]. n is size of arrays.
        static int myRand(int arr[], int freq[], int n)
        {
            // Create and fill prefix array
            int prefix[] = new int[n], i;
            prefix[0] = freq[0];
            for (i = 1; i < n; ++i)
                prefix[i] = prefix[i - 1] + freq[i];

            // prefix[n-1] is sum of all frequencies.
            // Generate a random number with
            // value from 1 to this sum
            int r = ((int)(Math.random()*(323567)) % prefix[n - 1]) + 1;

            // Find index of ceiling of r in prefix array
            int indexc = findCeil(prefix, r, 0, n - 1);
            return arr[indexc];
        }

        // Driver code
        public static void main(String args[])
        {
            int arr[] = {1, 2, 3, 4};
            int freq[] = {10, 5, 20, 100};
            int i, n = arr.length;

            // Let us generate 10 random numbers according to
            // given distribution
            for (i = 0; i < 5; i++)
                System.out.println( myRand(arr, freq, n) );
        }
    }

    */

    static int findCeil(int arr[], int r, int l, int h)
    {
        int mid;
        while (l < h)
        {
            mid = l + ((h - l) >> 1); // Same as mid = (l+h)/2
            if(r > arr[mid])
                l = mid + 1;
            else
                h = mid;
        }
        return (arr[l] >= r) ? l : -1;
    }

    static int myRand(int arr[], int freq[], int n)
    {
        // Create and fill prefix array
        int prefix[] = new int[n], i;
        prefix[0] = freq[0];
        for (i = 1; i < n; ++i)
            prefix[i] = prefix[i - 1] + freq[i];

        // prefix[n-1] is sum of all frequencies.
        // Generate a random number with
        // value from 1 to this sum
        int r = ((int)(Math.random()*(323567)) % prefix[n - 1]) + 1;

        // Find index of ceiling of r in prefix array
        int indexc = findCeil(prefix, r, 0, n - 1);
        return arr[indexc];
    }



}



