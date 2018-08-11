public class test {
	public static void main (String args[]){

		int n = 8;
		char cards[];
			// card status (whether it is flipped or not)
		boolean cardFlipped[];
				// number of card per each type
		int numPerType = 4;
				// set char ASCII to a
		int asciiChar = 97;

		// set size of cards 
		cards = new char[n];
		// set size of cardsFlipped
		cardFlipped = new boolean[n];

		// loop to initialize cards & set cardsFlipped to FALSE
		for (int i = 0; i<n; i = i + 4){  
			for (int j = 0; j<numPerType; j++){ 
				cards[j+i] = (char)asciiChar;
				System.out.println(cards[j+i]);
			}
			asciiChar++;
		}

		


	}
}

