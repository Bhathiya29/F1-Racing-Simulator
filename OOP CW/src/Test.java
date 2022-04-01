import java.util.Scanner;

public class Test {
    public static void main(String[]args){

        // Driver code
        //int num=validInt("Enter a number");
        //System.out.println(num);


            //Testing The probability winning

            String [] startingPositions={"Max","Lewis","Checo","Bottas","Lando","Charles","Sains","Daniel","Yuki"};
            int arr[] = {0,1, 2, 3, 4,5,6,7,8};
            int freq[] = {40,30,10,10,2,2,2,2,2};
            int i, n = arr.length;

            // Let us generate 10 random numbers according to
            // given distribution
            for (i = 0; i < 1; i++){
        int Position=myRand(arr, freq, n);
        System.out.println(Position);
        System.out.println(startingPositions[Position]);
            }
    }

    //Validating integers

    public static int validInt(String display){
        Scanner scan =new Scanner(System.in);
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
}
//LINK -https://www.geeksforgeeks.org/random-number-generator-in-arbitrary-probability-distribution-fashion/?ref=lbp

