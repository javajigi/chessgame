package pieces;
import java.util.List;
import pieces.Piece.Color;
import junit.framework.TestCase;

public class EmptyTest extends TestCase {
	public void testGetPossibleMoves() throws Exception {
		Position pos = new Position("a1");
		Empty empty = new Empty(Color.NOCOLOR, pos);
		List<Position> PossibleMoves = empty.getPossibleMoves();
		assertEquals(0, PossibleMoves.size());
	}
}
