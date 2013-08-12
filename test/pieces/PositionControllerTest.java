package pieces;

import java.util.List;

import junit.framework.TestCase;

public class PositionControllerTest extends TestCase {
	public void testFindsLinearPositionAll() throws Exception {
		int startX = 4;
		int startY = 5;
		Position position = new Position(startX, startY);

		PositionController controller = new PositionController(position);
		List<Position> positions = controller.findsLinearPositionAll();
		assertEquals(14, positions.size());
	}

	public void testFindsDiagonalPositionAll() throws Exception {
		int startX = 4;
		int startY = 5;
		Position position = new Position(startX, startY);

		PositionController controller = new PositionController(position);
		List<Position> positions = controller.findsDiagonalPositionAll();
		assertEquals(11, positions.size());
	}
	
	public void testFindQueenPositionAll() throws Exception {
		int startX = 4;
		int startY = 5;
		Position position = new Position(startX, startY);
		
		PositionController controller = new PositionController(position);
		List<Position> positions = controller.findsQueenPositionAll();
		assertEquals(25, positions.size());
	}
	
	public void testFindKingPositionAll() throws Exception {
		int startX = 0;
		int startY = 0;
		Position position = new Position(startX, startY);
		
		PositionController controller = new PositionController(position);
		List<Position> positions = controller.findsKingPositionAll();
		assertEquals(3, positions.size());
	}
	
	public void testFindPawnPositionAll() throws Exception {
		int startX = 3;
		int startY = 1;
		Position position = new Position(startX, startY);
		
		PositionController controller = new PositionController(position);
		List<Position> positions = controller.findsWhitePawnPositionAll();
		System.out.println(positions);
		assertEquals(2, positions.size());
	}
	
	public void testFindKnightPositionAll() throws Exception {
		int startX = 4;
		int startY = 5;
		Position position = new Position(startX, startY);
		
		PositionController controller = new PositionController(position);
		List<Position> positions = controller.findsKnightPositionAll();
		assertEquals(8, positions.size());
	}
}
