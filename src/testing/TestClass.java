package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import numberGuesserGame.NumberGuesser;

public class TestClass {	
	static NumberGuesser game;
	@Test
	public void lowerBoundChange() {
		game = new NumberGuesser(0,100);
		game.setGuess(60);
		int lower = game.getLowerBound();
		game.nextGuess("higher");
		assertNotEquals(lower, game.getLowerBound());
	}
	
	@Test
	public void upperBoundChanged() {
		game = new NumberGuesser(0,100);
		game.setGuess(40);
		int upper = game.getUpperBound();
		game.nextGuess("lower");
		assertNotEquals(upper, game.getUpperBound());
	}
	
	@Test
	public void boundSmaller() {
		game = new NumberGuesser(0,100);
		game.setGuess(50);
		int boundSize = game.getUpperBound()-game.getLowerBound();
		game.nextGuess("higher");
		int newBoundSize = game.getUpperBound()-game.getLowerBound();
		assertTrue(newBoundSize<boundSize);
	}
	
	@Test
	public void canGuessWithMaxBounds() {
		game = new NumberGuesser();
		assertNotEquals(game.turnsToGuess(40),-1);
		assertNotEquals(game.turnsToGuess(40),0);
	}
	
	@Test
	public void outOfBoundsNumberCatch() {
		game = new NumberGuesser(0,10);
		assertEquals(game.turnsToGuess(11),-1);
	}
	
	@Test
	public void outOfBoundsStartCatch() {
		game = new NumberGuesser(0,10);
		assertEquals(game.turnsToGuess(5,-1),-1);
	}
}
