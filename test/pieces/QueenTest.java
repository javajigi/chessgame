package pieces;

import java.util.List;

import pieces.Piece.Color;
import junit.framework.TestCase;

public class QueenTest extends TestCase {
	public void testPossibleMoves() throws Exception {
		Position pos = new Position("a3");
		Queen queen = new Queen(Color.BLACK, pos);
		List<Position> possibleMoves = queen.getPossibleMoves();
		assertEquals(21, possibleMoves.size());
	}
}
