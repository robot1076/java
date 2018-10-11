
import java.util.Scanner;
/**
 * Plays a game of 21 sticks with an actual human.
 *
 * @author robot1076
 * @version 10.10.18
 */
public class sticks
{
    public static void main(String args[])
    {
        //declaring
        Scanner user = new Scanner(System.in); //scanner for user input
        int sticks = 21 , goesFirst = 0; //goesFirst : 0 for computer, 1 for human.
        int took;
        int input;
        boolean inputAccepted ; 
        int lastUser = -1; //0 = computer , 1 = user
        String c = "COMPUTER" , u = "USER" ;
        /* below is temporary input while I figure out option panes
         * 
         */
        Scanner kb = new Scanner(System.in);
        System.out.print("\f");
        
        //saves the first name of the user
        System.out.print("Welcome to 21 Sticks v3! \nWhat is your name? (leave blank for default) ");
        String blank ="  "; //Simple_assignment,_not_simple_code.
        String name = kb.nextLine().trim() + blank;
                    
        int delimiter = name.substring(1).indexOf(' ') + 1;
        
        if (delimiter == -1) {
            u = name.substring(0 , 1).toUpperCase() + name.substring(1).toLowerCase().trim();
        }
        else {
            u =  name.substring(0 , 1).toUpperCase() + name.substring(1 , delimiter).toLowerCase().trim();
        }
        
        if (name.equalsIgnoreCase(blank))
            u = "USER";
        
        String inputString;
        do {
            System.out.print("Welcome, " + u + ". Would you like to go first? (y/n) : ");
            inputString = (kb.nextLine() + " ").substring(0 , 1).toLowerCase();
            inputAccepted = false;
            switch (inputString) {
                case "y" : goesFirst = 1; inputAccepted = true; break;
                case "n" : goesFirst = 0; inputAccepted = true; break;
                default  : inputAccepted = false; System.out.println("\nI asked you yes or no, please respond accordingly.");
            }
                          
        }while (!inputAccepted);
        
        /* end of old goes first system
         * 
         */
        
        System.out.println("\n" + u + ", there are " + sticks + " sticks in the pile.");
        
        if (goesFirst == 0) { //true if COMPUTER is going first
            took = (int)(1 + (Math.random() * 2));
            sticks -= took;
            remaining(sticks, took , c);
            lastUser = 0;//sets last user to zero because thats how it determines who took the last stick
        }
        
        while (sticks > 0) { //user always goes first here
            
            do { //handles input
                System.out.print("\nHow many sticks do you intend to take, " + u + "? ");
                try {
                    input = Integer.parseInt(user.nextLine());
                }
                catch (NumberFormatException nfe) {
                    input = 0;
                }
            
                if (!(input == 1 || input == 2) && !(input > sticks)) { //checks for a non 1 or 2 value
                    System.out.println("Oops! Please enter 1 or 2 sticks, " + u + "!");
                    inputAccepted = false;
                }
                else
                    inputAccepted = true;
                
            }while(!inputAccepted);
            inputAccepted = false;
            
            took = input;
            sticks -= took;
            remaining(sticks , took , u);
            lastUser = 1;
            
            //computer
            if (sticks > 0) {
                took = Math.min(sticks , random(sticks , took , goesFirst)); //gets AI number
                sticks -= took; //subtracts random integer from from int sticks
                remaining(sticks , took , c);
                lastUser = 0;
            }
        }
        
        if (lastUser == 0) { //if computer took last stick
            System.out.print(gameOver( u , 1));
        }
        else {
            System.out.print(gameOver( c , 0));
        }
    }
    
    public static int random(int sticks , int userMove , int first) {
        switch((sticks) % 3) { // BELOW CONFIG WORKS
            case 0 :  return 2; // 2
            case 1 :  return 2; // 2
            case 2 :  return 1; // 1
            default : return 2; // doesn't matter
        }
    }
    
    public static void remaining(int sticksRemaining , int took , String u) {
        System.out.println( "\n" + u.toUpperCase() + " took " + took + " sticks.");
        System.out.println("There are now " + sticksRemaining + " sticks remaining in the pile.");
    }
    
    public static String gameOver(String userName , int userWon) {
        return "\n\n" + userName.toUpperCase() + " has won the game!!";
    }
    
}
