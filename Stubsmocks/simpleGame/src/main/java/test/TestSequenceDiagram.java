package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import simpleGame.core.Board;
import simpleGame.core.Game;
import simpleGame.core.Pawn;

public class TestSequenceDiagram {

	private static final int SIZE_X = 6;
	private static final int SIZE_Y = 7;
	private static final int SIZE_X_BONUS = 3;
	private static final int SIZE_Y_BONUS = 3;
	private static final int NB_PAWN = 4;
	
	private Game game;
	
	private Board mockedBoard;
	
	private Board board;
	
	@Before
	public void setUp(){
		board = new Board(NB_PAWN,SIZE_X,SIZE_Y,SIZE_X_BONUS,SIZE_Y_BONUS);
		board.removeAllPawns();
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
	
	@Test
	public void testMaxGold(){
		Pawn p1 = mock(Pawn.class);
		Pawn p2 = mock(Pawn.class);
		Mockito.when(p1.getX()).thenReturn(0);
		Mockito.when(p1.getY()).thenReturn(0);
		Mockito.when(p2.getX()).thenReturn(1);
		Mockito.when(p2.getY()).thenReturn(1);
		Mockito.when(p1.getGold()).thenReturn(1);
		Mockito.when(p2.getGold()).thenReturn(3);
		
		board.addPawn(p1);
		board.addPawn(p2);
		int result = board.maxGold();
		
		assertEquals(3,result);
	}

}
