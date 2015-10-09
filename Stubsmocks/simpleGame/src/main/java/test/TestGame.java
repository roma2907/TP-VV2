package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import simpleGame.core.Board;
import simpleGame.core.Game;

public class TestGame {

	private Game game;
	
	private Board mockedBoard;
	
	@Before
	public void setUp(){
		game = new Game();
		mockedBoard = mock(Board.class);
		game.setBoard(mockedBoard);
	}
	
	
	/**
	 * Test isGameOver
	 * Il ne reste plus qu'un pion.
	 */
	@Test
	public void testIsGameOver_OnePawn() {
		when(mockedBoard.numberOfPawns()).thenReturn(1);
		
		boolean result = game.isGameOver();
		
		//vérification
		verify(mockedBoard).numberOfPawns();
		assertTrue(result);
	}
	
	/**
	 * Test isGameOver
	 * Il reste plus d'un pion et max gold non atteint.
	 */
	@Test
	public void testIsGameOver_NoWin() {
		when(mockedBoard.numberOfPawns()).thenReturn(2);
		when(mockedBoard.maxGold()).thenReturn(1);
		
		boolean result = game.isGameOver();
		
		//vérification
		verify(mockedBoard).numberOfPawns();
		verify(mockedBoard).maxGold();
		assertFalse(result);
	}
	
	/**
	 * Test isGameOver
	 * Il reste plus d'un pion et max gold atteint.
	 */
	@Test
	public void testIsGameOver_GoldWin() {
		when(mockedBoard.numberOfPawns()).thenReturn(2);
		when(mockedBoard.maxGold()).thenReturn(3);
		
		boolean result = game.isGameOver();
		
		//vérification
		verify(mockedBoard).numberOfPawns();
		verify(mockedBoard).maxGold();
		assertTrue(result);
	}

}
