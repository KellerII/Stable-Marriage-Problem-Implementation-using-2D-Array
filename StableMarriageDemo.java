/* James Keller
 * ITCS 2215 - 002
 */
import java.util.Scanner;

public class StableMarriageDemo 
{
    //prints menu options
    public static void printMenu()
    {
        System.out.println("Welcome to the Stable Marriage Demo!\n");
        System.out.println("Menu Options: ");
        System.out.println("1: Create stable pairings based on the men's preferences.");
        System.out.println("2: Create stable pairings based on the females' preferences.");
        System.out.println("3: Exit the demo. ");
        System.out.print("\nEnter your choice now: ");
    }

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        int choice = 0;
            
        while(exit == false)
        {
            printMenu();
            choice = input.nextInt();
            if(choice == 1)
            {
                int n = 10;
                System.out.print("Enter the number of couples: ");
                n = input.nextInt();
                StableMarriageProblemMale smp = new StableMarriageProblemMale(n);
                smp.readInput();
                smp.preProcess();
                smp.makePairs();
                System.out.println("\nThe stable pairings are: ");
                smp.printOutput();
                System.out.println("");
            }
            else if(choice == 2)
            {
                int n = 10;
                System.out.print("Enter the number of couples: ");
                n = input.nextInt();
                StableMarriageProblemFemale smp = new StableMarriageProblemFemale(n);
                smp.readInput();
                smp.preProcess();
                smp.makePairs();
                System.out.println("\nThe stable pairings are: ");
                smp.printOutput();
                System.out.println("");
            }
            else if(choice == 3)
            {
                exit = true;
            }
            else
            {
                System.out.println("\nInvalid option selected. Please"
                        + " enter a valid choice.\n");
            }
        }
    }
}
