package pieces;

import java.util.List;

import pieces.Piece.Color;
import junit.framework.TestCase;

public class BishopTest extends TestCase {
	public void testPossibleMoves() throws Exception {
		Position pos = new Position("a3");
		Bishop bishop = new Bishop(Color.BLACK, pos);
		List<Position> possibleMoves = bishop.getPossibleMoves();
		assertEquals(7, possibleMoves.size());
	}
}
 