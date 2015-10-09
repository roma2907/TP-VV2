package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import simpleGame.core.Board;
import simpleGame.core.Pawn;

public class TestBoard {

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
		board.removeAllPawns();
	}
	
	/**
	 * test getSquareContent sur une case ou il n'y à pas de pion.
	 */
	@Test
	public void testGetSquareContent_NoPawn(){
		
		
		Pawn result = board.getSquareContent(3, 2);
		
		assertNull(result);
	}
	
	/**
	 * test getSquareContent sur une case ou il y à un pion.
	 */
	@Test
	public void testGetSquareContent_WithPawn(){
		
		generateOnePawn();
		Mockito.when(mockedPawn.getX()).thenReturn(3);
		Mockito.when(mockedPawn.getY()).thenReturn(2);
		
		Pawn result = board.getSquareContent(3, 2);
		
		assertEquals(mockedPawn,result);
	}
	
	/**
	 * Test removePawn, suppression d'un pion.
	 */
	@Test
	public void testRemovePawn(){
		generateOnePawn();
		board.removePawn(mockedPawn);
		assertEquals(0,board.numberOfPawns());
	}
	
	/**
	 * Test de isBonus Square renvoie faux.
	 */
	@Test
	public void testBonusSquare_False(){
		boolean result = board.isBonusSquare(SIZE_X_BONUS, SIZE_Y_BONUS-1);
		assertFalse(result);
	}
	
	/**
	 * Test de isBonus Square renvoie faux.
	 */
	@Test
	public void testBonusSquare_False2(){
		boolean result = board.isBonusSquare(SIZE_X_BONUS-1, SIZE_Y_BONUS);
		assertFalse(result);
	}
	
	/**
	 * Test de isBonus Square renvoie true.
	 */
	@Test
	public void testBonusSquare_True(){
		boolean result = board.isBonusSquare(SIZE_X_BONUS, SIZE_Y_BONUS);
		assertTrue(result);
	}
	
	/**
	 * Test de removeAllPawn.
	 */
	@Test
	public void testRemoveAllPawn(){
		board.removeAllPawns();
		assertEquals(0,board.numberOfPawns());
	}
	
	/**
	 * Test de SquareContentSprite sur la case bonus.
	 */
	@Test
	public void testSquareContentSprite_Bonus(){
		char result = board.squareContentSprite(SIZE_X_BONUS, SIZE_Y_BONUS);
		assertEquals('#',result);
	}
	
	/**
	 * Test de SquareContentSprite sur un pion.
	 */
	@Test
	public void testSquareContentSprite_Pion(){
		generateOnePawn();
		Mockito.when(mockedPawn.getX()).thenReturn(3);
		Mockito.when(mockedPawn.getY()).thenReturn(2);
		Mockito.when(mockedPawn.getLetter()).thenReturn('c');		
		char result = board.squareContentSprite(3, 2);
		assertEquals('c',result);
	}
	
	/**
	 * Test de SquareContentSprite sur une case vide.
	 */
	@Test
	public void testSquareContentSpriteVoidCase(){		
		char result = board.squareContentSprite(3, 2);
		assertEquals('.',result);
	}
	
	/**
	 * Test de getNewtPawn, plus qu'un seul pion.
	 */
	@Test
	public void testGetNextPawn_OnePawn(){
		board.removeAllPawns();
		generateOnePawn();
		Pawn result = board.getNextPawn();
		assertEquals(mockedPawn,result);
	}
	
	/**
	 * Test maxGold 1 pion;
	 */
	@Test
	public void testMaxGold(){
		generateOnePawn();
		Mockito.when(mockedPawn.getGold()).thenReturn(2);
		
		int result = board.maxGold();
		assertEquals(2, result);
	}
	
	
	private void generateOnePawn(){
		board.addPawn(mockedPawn);
	}
	
	
	

}
