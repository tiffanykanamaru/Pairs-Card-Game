import java.util.Random;

public class PlayCard {
  public static void main(String[] args) {
    //set up reader to take inputs
    java.util.Scanner reader = new java.util.Scanner (System.in);
    
    int n = 16; //game size
    MatchCardGame g1 = new MatchCardGame(n);
    //g1.shuffleCards();
    
    while(!g1.gameOver()) {
      //print board status
      System.out.println(g1.boardToString());
      
      //ask for a card to flip until we get a valid one
      System.out.println("Which card to play?");
      while(!g1.flip(reader.nextInt())) {}
      
      //print board status
      System.out.println(g1.boardToString());
      
      //ask for a card to flip until we get a valid one
      while(!g1.flip(reader.nextInt())) {}
      
      //say whether the 2 cards were a match
      if(g1.wasMatch()) {
        System.out.println("Was a match!");
      } else {
        //print board to show mismatched cards
        System.out.println(g1.boardToString());		
        System.out.println("Was not a match.");
        //flip back the mismatched cards
        g1.flipMismatch();
      }
    }
    
    //Report the score
    System.out.println("The game took " + g1.getFlips() + " flips.");
    
    //Using the AIs
    int count;

    MatchCardGame g2 = new MatchCardGame(n);
    g2.shuffleCards();
    count = playRandom(g2);
    System.out.println("The bad AI took " + count + " flips.");

    //MatchCardGame g3 = new MatchCardGame(n);
    //g3.shuffleCards();
    //count = playGood(g3);
    //System.out.println("The good AI took " + count + " flips.");
    
    //Using MCs
    //int N = 1000;
    //System.out.println("The bad AI took " + randomMC(N) + " flips on average.");
    //System.out.println("The good AI took " + goodMC(N) + " flips on average.");
  }

  public static int playRandom(MatchCardGame g) {

    // used to decide which card to flip 
    Random rand = new Random(); 

    // total number of cards 
    int numCards = 16; 

    // continue to play until game is over
    while (!g.gameOver()) {
      
      // attempt random cards until two legal cards are flipped; illegal
      // flipping prevents continuation 
      while(!g.flip(rand.nextInt(numCards))) {}
      while(!g.flip(rand.nextInt(numCards))) {}

      // compare two flipped cards, if same continue, else flip back 
      if (g.wasMatch()) {
        continue; 
      }
      else {
        g.flipMismatch(); 
      }

    } // end game loop

    return g.getFlips(); 

  }


}