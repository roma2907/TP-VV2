package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import simpleGame.core.Board;
import simpleGame.core.Direction;
import simpleGame.core.Pawn;
import simpleGame.exception.OutOfBoardException;
import static org.mockito.Mockito.mock;

public class TestPawn {
	
	private Pawn pawn;
	private Board board;
	
	@Before
	public void setUp(){
		board = mock(Board.class);
		pawn = new Pawn('p',3,3,board);
	}
	
	/**
	 * test move avec une valeur étant dans le tableau et pas de pion à la place ou arrive le pion.
	 */
	@Test
	public void testMove_Up_InsideBoard() throws OutOfBoardException{
		
		Mockito.when(board.getYSize()).thenReturn(7);
		Mockito.when(board.getXSize()).thenReturn(6);
		Mockito.when(board.getSquareContent(3,4)).thenReturn(null);
		
		String result = pawn.move(Direction.Up);
		
		Mockito.verify(board).getXSize();
		Mockito.verify(board).getYSize();
		Mockito.verify(board).getSquareContent(3,4);

		//vérification position du pion
		Assert.assertEquals(3,pawn.getX());
		Assert.assertEquals(4,pawn.getY());
		Assert.assertEquals("",result);
	}
	
	/**
	 * test move avec une valeur étant dans le tableau, attaque d'un pion, le pion en face ne meurt pas.
	 */
	@Test
	public void testMove_Right_InsideBoardWithAttack() throws OutOfBoardException{
		
		//Mock Data
		Pawn pawnedAtacked = new Pawn('a',3,4,board);
		
		Mockito.when(board.getYSize()).thenReturn(7);
		Mockito.when(board.getXSize()).thenReturn(6);
		Mockito.when(board.getSquareContent(4,3)).thenReturn(pawnedAtacked);
		Mockito.when(board.isBonusSquare(3, 3)).thenReturn(false);
		
		String result = pawn.move(Direction.Right);

		Mockito.verify(board).getXSize();
		Mockito.verify(board).getYSize();
		Mockito.verify(board).getSquareContent(4,3);
		Mockito.verify(board).isBonusSquare(3, 3);
		
		Assert.assertEquals("p attacks!\na loses 1 hitpoints.",result);
	}
	
	/**
	 * Déplacement en dehors du tableau
	 * @throws OutOfBoardException
	 */
	@Test(expected = OutOfBoardException.class)
	public void testMove_OutOfBoardException()throws OutOfBoardException{
		Mockito.when(board.getYSize()).thenReturn(2);
		
		pawn.move(Direction.Left);
		
		Mockito.verify(board).getYSize();
	}
	
	/**
	 * Test move, déplacement vers le bas, 1 pion se trouve sur la case juste en dessous et il n'a qu'un point de vie.
	 */
	@Test
	public void testMove_Down_WithKillBonusSquare() throws OutOfBoardException{
		//Mock Data
		Pawn pawnedAtacked = new Pawn('a',3,4,board);
		
		Mockito.when(board.getYSize()).thenReturn(7);
		Mockito.when(board.getXSize()).thenReturn(6);
		Mockito.when(board.getSquareContent(3,2)).thenReturn(pawnedAtacked);
		Mockito.when(board.isBonusSquare(3, 3)).thenReturn(true);
		
		String result = pawn.move(Direction.Down);

		Mockito.verify(board).getXSize();
		Mockito.verify(board).getYSize();
		Mockito.verify(board).getSquareContent(3,2);
		Mockito.verify(board).isBonusSquare(3, 3);
		
		Assert.assertEquals(1, pawn.getGold());
		Assert.assertTrue(pawnedAtacked.isDead());
		Assert.assertEquals("p attacks!\na loses 2 hitpoints.a is dead.",result);
	}
	
	/**
	 * test isDead sur un pion qui a encore 2 hitpoints.
	 */
	@Test
	public void test_IsDead_NotDead(){
		boolean result = pawn.isDead();
		Assert.assertFalse(result);
	}
}
