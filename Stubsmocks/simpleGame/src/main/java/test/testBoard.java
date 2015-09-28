package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import simpleGame.core.Board;
import simpleGame.core.Pawn;

public class testBoard {

	private Board board;
	private static final int SIZE_X = 6;
	private static final int SIZE_Y = 7;
	private static final int SIZE_X_BONUS = 3;
	private static final int SIZE_Y_BONUS = 3;
	private static final int NB_PAWN = 4;
	
	Pawn mockedPawn ;
	
	@Before
	public void setUp(){
		mockedPawn = mock(Pawn.class);
		board = new Board(NB_PAWN,SIZE_X,SIZE_Y,SIZE_X_BONUS,SIZE_Y_BONUS);
	}
	
	/**
	 * test getSquareContent sur une case ou il n'y à pas de pion.
	 */
	@Test
	public void testGetSquareContent(){
		board.addPawn(mockedPawn);
		
		Mockito.when(mockedPawn.getX()).thenReturn(2);
		Mockito.when(mockedPawn.getY()).thenReturn(3);
		
		Pawn result = board.getSquareContent(3, 2);
		
		assertNull(result);
	}
	
	
	

}
