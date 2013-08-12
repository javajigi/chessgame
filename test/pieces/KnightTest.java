package pieces;

import java.util.List;

import pieces.Piece.Color;
import junit.framework.TestCase;

public class KnightTest extends TestCase {
	public void testname() throws Exception {
		Position pos = new Position("b2");
		Knight knight = new Knight(Color.WHITE, pos);
		
		List<Position> possibleMoves = knight.getPossibleMoves();
		assertEquals(4, possibleMoves.size());
		
	}
}
