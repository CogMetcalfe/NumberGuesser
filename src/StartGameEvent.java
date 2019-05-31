import event.ChoiceEvent;
import numberGuesserGame.NumberGuesser;

public class StartGameEvent extends ChoiceEvent{
		int type;
		public StartGameEvent(int type) {
			this.type = type;
		}
		
		@Override
		public boolean runEvent() {
			System.out.println("Enter the range that you want the numbers to be guessed between");
			int low = NumberGuesser.readConsoleInt("Enter the lowest number:", Integer.MIN_VALUE, Integer.MAX_VALUE);
			int high = NumberGuesser.readConsoleInt("Enter the highest number:", low, Integer.MAX_VALUE);
			NumberGuesser game = new NumberGuesser(low, high);
			if(type==0) {
				game.consoleTriesToGuess(true);
			}else {
				game.runConsoleGame(true);
			}
			return false;
		}
		
	}