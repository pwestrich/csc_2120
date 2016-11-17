import java.util.ArrayList;
import java.util.List;

public class MasterMindAIConsistent implements MasterMindAI
{
   private MasterMind game;

   public MasterMindAIConsistent(MasterMind game)  
   {
      this.game = game;
   }

   //DO THIS
   //is the guess consistent with all previous results?
   //that is, if you compare your random guess to a previous guess, do you get the same number of black and white buttons?
   //don't just check your random guess against the secret guess until you get a good result
   private boolean analyzeGuess(Guess nextGuess)
   {
      List<Guess> guesses = game.getGuesses();  //get all previous guesses from the game
      int num_guesses = guesses.size();
        int[] pastResult = null;
        int[] currentResult = game.getResult(nextGuess);
        boolean consistent = true;
        
      //loop over all previous guesses
        for (Guess pastGuess : guesses){
           
             pastResult = game.getResult(pastGuess);
             
             if (currentResult[0] < pastResult[0]){
                  
                  //If number of black is less for this guess, it is not consistent
                  consistent = false;
                  
             }
             
             else consistent = true;
           
      }

      return consistent;
        
   }

   //DO THIS
   public Guess nextGuess(int guess_id)
   {
      List<Guess> guesses = game.getGuesses();
      int num_guesses = guesses.size();
      Guess nextGuess = null;

      if (guesses.size() > 0)
      {
         Guess trialGuess = null;

         boolean good = false;
         //keep obtaining a random guess until you get one that works with all previous results
         //it is cheating to keep obtaining a random guess until you match the solution
           
           while (!good){
                
                trialGuess = makeRandomGuess(guess_id); //Make new guess
                good = analyzeGuess(trialGuess);       //Analyze it
                
           }
 
         nextGuess = trialGuess;  //found a good one
      }
      else
      {
         nextGuess = makeRandomGuess(guess_id);
      }

      return nextGuess;
   }
 
   //DO THIS
   //use the Random class to make a randomly generated guess
   private static Guess makeRandomGuess(int guess_id)
   {
      Guess randomGuess = new Guess(guess_id);
      Random random = Random.getRandomNumberGenerator();
        
        while (!randomGuess.isFull()){
             
             randomGuess.addGuess(random.randomInt(1, 7));
             
        }
        
      return randomGuess;
   }
}
