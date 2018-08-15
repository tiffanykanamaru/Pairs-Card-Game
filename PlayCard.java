import java.util.Random;

public class PlayCard {
    public static void main(String[] args) {
        //set up reader to take inputs
        java.util.Scanner reader = new java.util.Scanner (System.in);
        
        int n = 16; //game size
        MatchCardGame g1 = new MatchCardGame(n);
        g1.shuffleCards();
        
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

        MatchCardGame g3 = new MatchCardGame(n);
        g3.shuffleCards();
        count = playGood(g3);
        System.out.println("The good AI took " + count + " flips.");
        
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
          // attempt random cards until two legal cards are flipped
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

    public static int playGood (MatchCardGame g){
        // total number of cards
        int numCards = 16;
        // create array of known cards flipped  
        char[] charMemorize = new char[numCards];
        // create an array of flipped cards
        boolean[] flipped = new boolean[numCards];
        // number of flips
        int numFlips = g.getFlips();
        // create instance of random number class
        Random rand = new Random();
        // card numbers chosen
        int numOne = 0;
        int numTwo = 0;
        // variable if match was found or not
        boolean matchFound = false;

        while (!g.gameOver()){
            // if it's the first flip, pick two random cards
            if (g.getFlips() == 0){
                // attempt random cards until two legal cards are flipped
                while(!g.flip(numOne = rand.nextInt(numCards))) {}
                charMemorize[numOne] = g.previousFlipIdentity();

                while(!g.flip(numTwo = rand.nextInt(numCards))) {}
                charMemorize[numTwo] = g.previousFlipIdentity();

                // compare two flipped cards, if same continue, else flip back 
                if (g.wasMatch()) {
                    flipped[numOne] = true;
                    flipped[numTwo] = true;
                    continue; 
                } else {
                    g.flipMismatch(); 
                    flipped[numOne] = false;
                    flipped[numTwo] = false;
                }
            }
            // check if flips are even
            else if (g.getFlips()%2 == 0){
                // check if two cards are known
                outerloop: for (int i = 0; i<numCards; i++){
                    for (int j = i+1; j<numCards; j++){
                        if (charMemorize[i] == charMemorize[j] && charMemorize[i]!='\0' && charMemorize[j]!='\0'
                            && flipped[i] == false && flipped[j] == false){
                            g.flip(i);
                            flipped[i] = true;

                            g.flip(j);
                            flipped[j] = true;

                            matchFound = true;
                            // break out of outerloop
                            break outerloop;
                        }
                    }
                }
                // if match was not found, then pick a valid random card
                if (matchFound == false){
                    while(!g.flip(numOne = rand.nextInt(numCards))) {}
                    charMemorize[numOne] = g.previousFlipIdentity();
                    flipped[numOne] = true;
                }

                // set match found back to false
                matchFound = false;
            }
            else {
                // check if flipped card matches known card
                for (int i = 0; i<numCards; i++){
                    if (charMemorize[numOne] == charMemorize[i] && flipped[i] == false){
                        g.flip(i);
                        flipped[i] = true;
                        matchFound = true;
                    }
                } 

                // if no match was find, flip a valid random card
                if (matchFound == false){
                    while(!g.flip(numTwo = rand.nextInt(numCards))) {}
                    charMemorize[numTwo] = g.previousFlipIdentity();
                    flipped[numTwo] = true;

                    // compare two flipped cards, if same continue, else flip back 
                    if (g.wasMatch()) {
                        continue; 
                    }
                    else {
                        flipped[numOne] = false;
                        flipped[numTwo] = false;

                        g.flipMismatch(); 
                    }
                }

                // set match found back to false
                matchFound = false;

            }
        }

        return g.getFlips();
    }
}