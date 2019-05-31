import numberGuesserGame.NumberGuesser;
import menu.*;
import event.*;
public class NumberGuesserLauncherMain {
	public static void main(String[] args){
		DynamicMenu menu = new DynamicMenu();
		menu.addChoice(new Choice("1) Auto Edition", "1", new StartGameEvent(0)).addInput("1) auto edition").addInput("auto").addInput("auto edition"));
		menu.addChoice(new Choice("2) Standard Edition", "2", new StartGameEvent(1)).addInput("2) standard edition").addInput("standard").addInput("standard edition"));
		menu.addChoice(new Choice("3) Exit", "exit",new BackEvent()).addInput("3) exit").addInput("3").addInput("stop").addInput("quit").addInput("close"));
		
		menu.runMenu();
		
		
		
//		NumberGuesser game = new NumberGuesser(0,10000);
//		
//		game.runConsoleGame();
//		game.consoleTriesToGuess(true);
//		
//		int totalGuesses = 0;
//		for(int i=1;i<=10000;i++) {
//			int turnsForNum = game.turnsToGuess(i);
//			System.out.println(i + ": " + turnsForNum);
//			totalGuesses+=turnsForNum;
//		}
//		System.out.println("Total guesses for all numbers = " + totalGuesses);
//
//		game = new NumberGuesser();
//		System.out.println(game.turnsToGuess(-12312312, 0, true));
//		System.out.println(game.turnsToGuess(1234127892, 0, true));
//		System.out.println(game.turnsToGuess(50, 1, false));
//		System.out.println(game.turnsToGuess(50, 2, false));
	}
	
	
	
	
}
