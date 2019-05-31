package numberGuesserGame;
import godscan.*;

public class NumberGuesser {

	int guess;
	int gameLower, gameUpper;
	int upper,lower;

	public NumberGuesser() {
		this.gameLower = Integer.MIN_VALUE;
		this.gameUpper = Integer.MAX_VALUE;
	}
	public NumberGuesser(int lowerBound, int upperBound) {
		this.gameLower = lowerBound;
		this.gameUpper = upperBound;
		this.upper=upperBound;
		this.lower=lowerBound;
	}
	
	public void setGuess(int guess) {
		if(guess<=upper && guess>=lower) {
			this.guess = guess;
		}
	}
	
	public int turnsToGuess(int n){
		return turnsToGuess(n, mean(gameUpper, gameLower));
	}
	
	public int turnsToGuess(int n, int start){
		return turnsToGuess(n, start, false);
	}
	public int turnsToGuess(int n, boolean printGuesses){
		return turnsToGuess(n, mean(gameUpper, gameLower), printGuesses);
	}
	
	public int turnsToGuess(int n, int start,boolean printGuesses){
		this.upper=gameUpper;
		this.lower=gameLower;
		//System.out.println(gameLower + " - " + gameUpper);
		guess = start;
		if(n<this.gameLower||n>this.gameUpper) {
			return -1;
		}
		if(start<this.lower||start>this.upper) {
			return -1;
		}
		int turnCount=0;
		while(n!=this.guess) {
			if(turnCount>40) {
				return -1;
			}
			//System.out.println(guess + "->" + lower + "-" + upper +" " + n);
			if(n>guess) {
				if(printGuesses) {System.out.println("Turn " + turnCount + ": " + guess + " - Higher");}
				nextGuess("higher");
			}else{
				if(printGuesses) {System.out.println("Turn " + turnCount + ": " + guess + " - Lower");}
				nextGuess("lower");
			}
			turnCount++;
		}
		if(printGuesses) {System.out.println("Turn " + turnCount + ": " + guess + " - Correct!");}

		this.upper=gameUpper;
		this.lower=gameLower;
		return turnCount;
	}
	
	public int nextGuess(String userInput) {
		
		if(userInput.equals("higher")||userInput.equals("h")) {
			lower = guess+1;
			guess= mean(upper,lower);
		}
		if(userInput.equals("lower")||userInput.equals("l")) {
			upper = guess-1;
			guess= mean(upper,lower);
		}
		
		return guess;
	}
	
	public String getUserInput(){
		String userInput = GodScanner.getScan().nextLine(); 
		return userInput;
	}
	
	public int getUserInputInt() {
		
		//FIX HERE 2
		try {
			return Integer.parseInt(getUserInput());
		}catch(Exception e) {
			return 0;
		}
	}
	
	public void consoleTriesToGuess(boolean loop) {
		while(loop) {
			System.out.println("The program will take a number and see how many tries it takes the computer to guess");
			this.upper=gameUpper;
			this.lower=gameLower;
			int userNumber = readConsoleInt("Enter the number for the program to guess",lower, upper);
			turnsToGuess(userNumber,true);
			if(loop) {
				boolean understandResponse=false;
				while(!understandResponse) {
					System.out.println("Do you want to play again?[Y/N]");
					String response = GodScanner.getScan().nextLine().toLowerCase();
					if(response.equals("y")||response.equals("yes")) {
						understandResponse=true;
					}else if(response.equals("n")||response.equals("no")) {
						understandResponse=true;
						loop=false;
					}
				}
			}
		}
	}
	
	public static int readConsoleInt(String prompt, int lowest, int highest) {
		String userInput="";
		int number=0;
		boolean intGiven=false;
		while(!intGiven) {
			System.out.println(prompt + ", between " + lowest + " and " + highest + " inclusive");
			intGiven=true;
			try{
				userInput = GodScanner.getScan().nextLine();
				number= Integer.parseInt(userInput);
			}catch(Exception e) {
				intGiven = false;
				System.out.println("\"" + userInput + "\" is not recognised as an integer number.");
			}
			if(intGiven) {
				if(number<lowest || number>highest) {
					intGiven=false;
					System.out.println("The number has to be between " + lowest + " and " + highest + " inclusive.");
				}
			}
		}
		return number;
	}
	
	public void runConsoleGame(boolean loop) {
		while(loop) {
			this.upper=gameUpper;
			this.lower=gameLower;
			guess = readConsoleInt("Enter a number for the guesser to start at", lower, upper);
			//System.out.println("Is your number Higher or Lower than " + guess + " ? [Higher/Lower/Correct]");
			String userInput="";
			while(true) {
				System.out.println("My Guess is " + guess);
				System.out.println("Is my guess Correct? Or is your number Higher or Lower?[Higher/Lower/Correct]");
				boolean possible=false;
				while(!possible) {
					possible=true;
					userInput = GodScanner.getScan().nextLine().toLowerCase();
					if(guess==upper&&(userInput.equals("h")||userInput.equals("higher"))) {
						possible=false;
						System.out.println("It can't be higher than " + guess + " because you said it was less than " + (guess+1));
						System.out.println("Is " + guess + " correct or is the number lower?[Correct/Lower]");
					}
					if(guess==lower&&(userInput.equals("l")||userInput.equals("lower"))) {
						possible=false;
						System.out.println("It can't be lower than " + guess + " because you said it was higher than " + (guess-1));
						System.out.println("Is " + guess + " correct or is the number higher?[Correct/Higher]");
					}
				}
				if(userInput.equals("correct")||userInput.equals("c")) {
					System.out.println("I guessed correctly! Thanks for playing.");
					break;
				}
				nextGuess(userInput);
				//System.out.println("Upper: " + upper + ", Lower: " + lower);
				if(upper<=lower) {
					if(upper==lower) {
						System.out.println("Then the number can only be " + guess);
						break;
					}else {
						System.out.println("You have given me incorrect information or your number is not within the bounds set.");
						break;
					}
				}
			}
			if(loop) {
				boolean understandResponse=false;
				while(!understandResponse) {
					System.out.println("Do you want to play again?[Y/N]");
					String response = GodScanner.getScan().nextLine().toLowerCase();
					if(response.equals("y")||response.equals("yes")) {
						understandResponse=true;
					}else if(response.equals("n")||response.equals("no")) {
						understandResponse=true;
						loop=false;
					}
				}
			}
		}
	}
	
	public int getLowerBound() {
		return this.lower;
	}
	public int getUpperBound() {
		return this.upper;
	}

	public static int mean(int n1, int n2) {
		long l1 = n1;
		long l2 = n2;
		return (int)((l1+l2)/2);
	}
	
}
