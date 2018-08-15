import java.util.Random;

public class MatchCardGame {
	// size of the game
	public final int n;

	// array of card information
	private char cards[];
	// card status (whether it is flipped or not)
	private boolean cardFlipped[];
	// number of card per each type
	private final int numPerType = 4;
	// set char ASCII to a
	private int asciiChar = 97;
	// number of flips
	private int flips = 0;
	// stores the previous card state
	private char previous;
	private int indexPrevious;
	// stores the current card state
	private char current;
	private int indexCurrent;

	private int count;
	private int random;
	private char temp;

	// ctor, initializes a card game w/ total of n cards
	public MatchCardGame(int n){
		// set value to the size of the game
		this.n = n;
		// set size of cards 
		cards = new char[n];
		// set size of cardsFlipped
		cardFlipped = new boolean[n];

		// loop to initialize cards & set cardsFlipped to FALSE
		for (int i = 0; i<n; i = i + 4){  
			for (int j = 0; j<numPerType; j++){ 
				cards[j+i] = (char)asciiChar;
				cardFlipped[j+i] = false;
			}
			asciiChar++;
		}
	}

	// converts the state of the board to appropriate String representation
	public String boardToString(){
		for (int i = 0; i<n; i++)
		{
			if (!cardFlipped[i]){
				System.out.print(i + " ");
			} else{
				System.out.print(cards[i] + " ");
			}
			count++;
			if (count > 3){
				count = 0;
				System.out.println();
			}
		
		}
		return "";

	}

	// plays card i
	public boolean flip (int i){
		// returns false if card is out of bounds
		if (i >= n || i < 0){
			return false;
		} // returns false if card is face-up
		else if (cardFlipped[i] == true){
			return false;
		} else { // othersie, flip card face-up
			cardFlipped[i] = true;
			flips++; // increment number of flips;

			// store odd card as previous card
			if (flips%2 != 0){
				previous = cards[i];
				indexPrevious = i;
			}
			else {
				current = cards[i];
				indexCurrent = i;
			}
			return true;
		}
	}

	// returns true if the previous pair was a match
	public boolean wasMatch(){     	
		if (flips%2 == 0){
			if (previous == current){
				return true;
			}
		}
		return false;
	}

	// returns the face of the previously flipped card as a char
	public char previousFlipIdentity(){
		if (flips%2 != 0){
			return previous;
		}
		else {
			return current;
		}
	}

	public void flipMismatch(){
		cardFlipped[indexPrevious] = false;
		cardFlipped[indexCurrent] = false;
	}

	// returns true if all cards have been matched, returns false otherwise
	public boolean gameOver(){
		for (int i = 0; i < n; i++){
			if (cardFlipped[i] == false){
				return false;
			}
		}
		return true;
	}

	// returns the total number of card flips
	public int getFlips(){
		return flips;
	}

	// shuffle cards using Fisher-Yates shuffle
	public void shuffleCards(){
		// loop through all cards
		for (int i = n-1; i>=0; i--){
			// get a random number from 0 to n-1
			Random rand = new Random();
			random = rand.nextInt(n-1) + 0;
			// swap
			temp = cards[i]; 
			cards[i] = cards[random]; 
			cards[random] = temp; 
		}
		
	}


}