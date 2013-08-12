package pieces;

import java.util.List;

import pieces.Piece.Color;
import junit.framework.TestCase;

public class PawnTest extends TestCase {
	public void testPossibleMoves() throws Exception {
		Position pos = new Position("d5");
		Pawn blackPawn = new Pawn(Color.BLACK, pos);
		Pawn whitePawn = new Pawn(Color.WHITE, pos);
		
		List<Position> whitePossibleMoves = whitePawn.getPossibleMoves();
		List<Position> blackPossibleMoves = blackPawn.getPossibleMoves();
		assertEquals(pos.move(Direction.NORTH), whitePossibleMoves.get(0));
		assertEquals(pos.move(Direction.SOUTH), blackPossibleMoves.get(0));
		
		
		Position whiteStartPos = new Position("d2");
		Position blackStartPos = new Position("d7");
		Pawn whiteStartPawn = new Pawn(Color.WHITE, whiteStartPos);
		Pawn blackStartPawn = new Pawn(Color.BLACK, blackStartPos);
		
		List<Position> whiteStartPossibleMoves = whiteStartPawn.getPossibleMoves();
		List<Position> blackStartPossibleMoves = blackStartPawn.getPossibleMoves();
		Position whiteMovedPos = whiteStartPos.move(Direction.NORTH);
		Position blackMovedPos = blackStartPos.move(Direction.SOUTH);
		assertEquals(whiteMovedPos, whiteStartPossibleMoves.get(0));
		assertEquals(whiteMovedPos.move(Direction.NORTH), whiteStartPossibleMoves.get(1));
		assertEquals(blackMovedPos, blackStartPossibleMoves.get(0));
		assertEquals(blackMovedPos.move(Direction.SOUTH), blackStartPossibleMoves.get(1));
		
	}
}
