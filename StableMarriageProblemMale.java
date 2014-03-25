/* James Keller
 * ITCS 2215 - 002
 */
import java.util.Scanner;
import java.util.Stack;

public class StableMarriageProblemMale
{
    private int n;
    private int femaleIndex[];
    private int rank[][];
    private int female[][];
    private int femaleDeleted[];
    private int male[][];
    private int pairings[];
    private Stack<Integer> stackMale;
        
    //Constructor
    public StableMarriageProblemMale(int size)
    {
        n = size;
        femaleIndex = new int[n+1];
        rank = new int[n+1][n+1];
        female = new int[n+1][n+1];
        femaleDeleted = new int[n+1];
        male = new int[n+1][n+1];
        pairings = new int[n+1];
        stackMale = (new Stack<Integer>());
    }
    //Collect the user's input
    public void readInput()
    {
        Scanner input = new Scanner(System.in);

        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                System.out.print("Enter choice #" + j + " of female #" + i + ": ");
                female[i][j] = input.nextInt();
            }
            System.out.print("");
        }

        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                System.out.print("Enter choice #" + j + " of male #" + i + ": ");
                male[i][j] = input.nextInt();
            }
            System.out.print("");
        }
    }
    //prepocess the 2D array along with the stack
    public void preProcess()
    {
        for(int i = 1; i <= n; i++)
        {
            for(int j = 0; j <= n; j++)
            {
                rank[i][female[i][j]]=j;
            }
        }
        //Tracks current female preferences
        for(int i = 0; i <= n; i++)
        {
            femaleIndex[i] = 0;			
        }
        //Tracks index of the female rejected by the male
        //Tracks unmarried males
        for(int i = 1; i <= n; i++)
        {
            femaleDeleted[i]=1;      
            stackMale.push(i);     
        }
    }
    //Gale Shapely algorithm applied
    public void makePairs()
    {
        int currentMan = 1;
        int currentWoman = 1;
        
        while(!stackMale.empty())
        {
            //Current male 
            currentMan=stackMale.peek(); 
            //Current female for setting pairings
            currentWoman=male[currentMan][femaleDeleted[currentMan]];
            //Next female considered
            femaleDeleted[currentMan]++;  
            //Checks for pairing
            if(femaleIndex[currentWoman]!=0)	
            {
                //Compares rank
                if(rank[currentWoman][femaleIndex[currentWoman]]>=rank[currentWoman][currentMan])	
                {
                    stackMale.pop();
                    stackMale.push(femaleIndex[currentWoman]);
                    femaleIndex[currentWoman]=currentMan;
                }
            }
            else	
            {
                femaleIndex[currentWoman] = currentMan;
                stackMale.pop();
            }
        }
        for(int i = 1; i <= n; i++)
        {
            pairings[femaleIndex[i]]=i;
        }
    }
    //Print results
    public void printOutput()
    {
        for(int i = 1; i <= n; i++)
        {
            System.out.println("Female #" + i + " is paired with male #"+  pairings[i]);
        }
    }	
}
