/* James Keller
 * ITCS 2215 - 002
 */
import java.util.Scanner;
import java.util.Stack;

public class StableMarriageProblemFemale
{
    private int n;
    private int maleIndex[];
    private int rank[][];
    private int male[][];
    private int maleDeleted[];
    private int female[][];
    private int pairings[];
    private Stack<Integer> stackMale;
    
    //Constructor
    public StableMarriageProblemFemale(int size)
    {
            n = size;
            maleIndex = new int[n+1];
            rank = new int[n+1][n+1];
            male = new int[n+1][n+1];
            maleDeleted = new int[n+1];
            female = new int[n+1][n+1];
            pairings = new int[n+1];
            stackMale = new Stack<Integer>();

    }
    //Collect the user's input
    public void readInput()
    {
            Scanner input = new Scanner(System.in);
            for(int i = 1; i <= n; i++)
            {
                for(int j = 1; j <= n; j++)
                {
                    System.out.print("Enter #" + j + " choice of female #" + i + " :");
                    female[i][j] = input.nextInt();
                }
            }
            for(int i = 1; i <= n; i++)
            {
                for(int j = 1; j <= n; j++)
                {
                    System.out.print("Enter #" + j + " choice of male #" + i + " :");
                    male[i][j] = input.nextInt();
                }
            }
    }
    //preprocess the 2D array along with the stack
    public void preProcess()
    {
            for(int i = 1; i <= n; i++)
            {
                for(int j = 0; j <= n; j++)
                {
                    rank[i][male[i][j]]=j;
                }
            }
            //Tracks the current female preferences
            for(int i = 0; i <= n; i++)
            {
                maleIndex[i] = 0;			
            }
            //Tracks index of the male rejected by the female
            //Tracks unmarried females
            for(int i = 1; i <= n; i++)
            {
                maleDeleted[i]=1;      
                stackMale.push(i);     
            }
    }
    /**
     * Applying Gale Shapely algorithm for solving the stable pairings problem
     */
    public void makePairs()
    {
        int currentWoman = 1;
        int currentMan = 1;

        while(!stackMale.empty())
        {
            //Current female
            currentWoman=stackMale.peek();           
            //Current male for setting pairings
            currentMan=female[currentWoman][maleDeleted[currentWoman]];  
            //Next male considered
            maleDeleted[currentWoman]++;     

            if(maleIndex[currentMan]!=0)
            {
                if(rank[currentMan][maleIndex[currentMan]]>=rank[currentMan][currentWoman])
                {
                    stackMale.pop();
                    stackMale.push(maleIndex[currentMan]);
                    maleIndex[currentMan]=currentWoman;
                }
            }
            else
            {
                maleIndex[currentMan] = currentWoman;
                stackMale.pop();
            }
        }

        for(int i = 1; i <= n; i++)
        {
            pairings[maleIndex[i]]=i;
        }

    }
    //Print results
    public void printOutput()
    {
        for(int i = 1; i <= n; i++)
        {
            System.out.println("Male  #" +  i + " is paired with " + "female #" + pairings[i]);
        }
    }
}
