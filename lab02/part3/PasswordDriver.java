
/**
*    Lab 2 Part 3
*    for CSC 2121-001
*    Sept. 5, 2013
*
*/

class PasswordDriver {

     public static void main(String[] args){

          Password passwordList = new Password(); //Create password list
               
          addWords(passwordList);                 //Get input from user
          
          guessWords(passwordList);               //Guess correct password
          
     }

     public static void addWords(Password passwordList){
          
          String buffer = "EMPTY"; //Initialize buffer
          
          for (int i = 1; ; i++){ //Ask for stings to input
               
               buffer = Keyboard.readString("Enter a string (#" + i + "): ");
               
               if (buffer.equals("Q") || buffer.equals("q")) break; //If input is Q or q, break
               
               passwordList.addWord(buffer); //Add the word if otherwise
               
          }
          
     }
     
     public static void guessWords(Password passwordList){
          
          int numToGuess = 0;
          int matches = -1;
                    
          for (int count = 1; count <= 4; count++){ //While there are tries left, ask which word should be tried, and number of matches
               
               System.out.println("Attempt #" + count);
               System.out.println("You should guess: " + passwordList.bestGuess());
               numToGuess = Keyboard.readInt("Index of word to guess (1-based, using original list): ");
               matches = Keyboard.readInt("Number of character matches: ");
                    
               passwordList.guess(numToGuess, matches); //Guess
               System.out.print('\n' + passwordList.toString() + '\n'); //Print updated list
               
               if (passwordList.getNumberOfPasswordsLeft() == 1){ //If only one password exists, exit
                    
                    System.out.println("It's a match!");
                    return;
                    
               }

          }
          
          //Print message if out of tries
          System.out.println("Sorry, you're out of tries.");
          System.out.println("Exiting...");
          
     }

}
