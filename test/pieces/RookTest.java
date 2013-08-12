package pieces;

import java.util.List;

import pieces.Piece.Color;
import junit.framework.TestCase;

public class RookTest extends TestCase {
	public void testPossibleMoves() throws Exception {
		Position pos = new Position("a3");
		Rook rook = new Rook(Color.BLACK, pos);
		List<Position> possibleMoves = rook.getPossibleMoves();
		assertEquals(14, possibleMoves.size());
	}

}
