package chess;

import static util.Constants.NEW_LINE;

import junit.framework.TestCase;
import pieces.PieceFactory;
import pieces.PieceOperations;
import pieces.Position;

public class BoardTest extends TestCase {
	private Board board;
	
	@Override
	protected void setUp() throws Exception {
		board = new Board(new DefaultInitialize());
	}
	
	public void testCreate() throws Exception {
		assertEquals(RankTest.WHITE_PAWN_RANK, board.generateRank(1));
		assertEquals(RankTest.BLACK_PAWN_RANK, board.generateRank(6));
	}
	
	public void testPrint() throws Exception {
		String expected = 
			RankTest.BLACK_EXCEPT_PAWN_RANK + NEW_LINE +
			RankTest.BLACK_PAWN_RANK + NEW_LINE +
			createEmptyRank() + 
			createEmptyRank() + 
			createEmptyRank() + 
			createEmptyRank() +
			RankTest.WHITE_PAWN_RANK + NEW_LINE +
			RankTest.WHITE_EXCEPT_PAWN_RANK + NEW_LINE;
		System.out.println(board.generateBoard(new ConsoleGenerator()));
		assertEquals(expected, board.generateBoard(new ConsoleGenerator()));
	}
	
	private String createEmptyRank() {
		return RankTest.EMPTY_RANK + NEW_LINE;
	}
	
	public void testFindPiece() throws Exception {
		assertEquals('R', board.findPiece("a8").getSymbol());
		assertEquals('k', board.findPiece("e1").getSymbol());
	}
	
	public void testInitializeEmpty() throws Exception {
		board = new Board(new EmptyInitialize());
	}
	
	public void testMovePiece() throws Exception {
		Position source = new Position("a2");
		PieceOperations sourcePiece = board.findPiece(source);
		assertEquals(PieceFactory.createWhitePawn(source), sourcePiece);
		
		Position target = new Position("a3");
		board.movePiece(source, target);
		assertEquals(PieceFactory.noPiece(source), board.findPiece(source));
		assertEquals(PieceFactory.createWhitePawn(target), board.findPiece(target));
	}
	
	public void testMoveEmpty() throws Exception {
		Position source = new Position("a3");
		Position target = new Position("a2");
		PieceOperations emptyPiece = board.findPiece(source);
		PieceOperations targetPiece = board.findPiece(target);
		
		board.movePiece(source, target);
		assertEquals(PieceFactory.noPiece(source), emptyPiece);
		assertEquals(PieceFactory.createWhitePawn(target), targetPiece);
	}
	
	public void testMoveInvalidTarget() throws Exception {
		int invalidX = -1;
		int invalidY = -1;
		Position source = new Position("a2");
		Position target = new Position(invalidX, invalidY);
		PieceOperations sourcePiece = board.findPiece(source);
		
		board.movePiece(source, target);
		assertEquals(PieceFactory.createWhitePawn(source), sourcePiece);
	}
	
	public void testMoveToSameColor() throws Exception {
		Position source = new Position("a2");
		Position target = new Position("b2");
		PieceOperations sourcePiece = board.findPiece(source);
		PieceOperations targetPiece = board.findPiece(target);
		
		board.movePiece(source, target);
		assertEquals(PieceFactory.createWhitePawn(source), sourcePiece);
		assertEquals(PieceFactory.createWhitePawn(target), targetPiece);
	}
	
	public void testMoveByPiece() throws Exception {
		Position source = new Position("a2");
		Position target = new Position("b3");
		PieceOperations sourcePiece = board.findPiece(source);
		PieceOperations targetPiece = board.findPiece(target);
		
		board.movePiece(source, target);
		assertEquals(PieceFactory.createWhitePawn(source), sourcePiece);		
		assertEquals(PieceFactory.noPiece(target), targetPiece);
	}
}
