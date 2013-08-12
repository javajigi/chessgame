package pieces;

import java.util.List;

import pieces.Piece.Color;
import junit.framework.TestCase;

public class KingTest extends TestCase {
	public void testPossibleMoves() throws Exception {
		Position pos = new Position("a1");
		King king = new King(Color.BLACK, pos);
		List<Position> possibleMoves = king.getPossibleMoves();
		assertEquals(3, possibleMoves.size());
	}
}
