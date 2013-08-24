package chess;

import java.util.List;

import pieces.Piece.Color;
import pieces.Piece.Type;
import pieces.PieceOperations;
import pieces.Position;

public class Board {
	public static final int ROW_SIZE = 8;
	public static final int COLUMN_SIZE = 8;
	Initializable init = new DefaultInitialize();

	List<Rank> ranks;
	int turnCount = 0;
	
	public Board() {
		this(new DefaultInitialize());
	}

	public Board(Initializable initializable) {
		this.ranks = initializable.initialize();
	}

	PieceOperations findPiece(String xy) {
		Position position = new Position(xy);
		return findPiece(position);
	}

	PieceOperations findPiece(Position position) {
		Rank rank = ranks.get(position.getY());
		return rank.findPiece(position);
	}

	public void movePiece(String source, String target) {
		movePiece(new Position(source), new Position(target));
	}

	public void movePiece(Position source, Position target) {
		PieceOperations targetPiece = findPiece(source);
		PieceOperations sourcePiece = targetPiece.leave();
		
		if (invalidMove(source, target)) {
			return;
		}
		
		turnCount++;
		
		Rank sourceRank = ranks.get(source.getY());
		sourceRank.move(sourcePiece, source);

		Rank targetRank = ranks.get(target.getY());
		targetRank.move(targetPiece, target);
	}

	private boolean invalidMove(Position source, Position target) {
		// empty piece
		if (findPiece(source).isEmpty()) {
			System.out.println("empty piece cant move");
			return true;
		}
		// invalid target
		if (!target.isValid()) {
			System.out.println("cant move to invalid target position");
			return true;
		}
		// same color position
		if (findPiece(target).getColor() == findPiece(source).getColor()) {
			System.out.println("dont kill your team");
			return true;
		}
		// this piece cant move to there
		List<Position> possibleMoves = findPiece(source).getPossibleMoves();
		if (!possibleMoves.contains(target)) {
			System.out.println("impossible moves");
			return true;
		}
		// not your turn
		Color sourceColor = findPiece(source).getColor();
		final int whiteTurn = 0;
		final int blackTurn = 1;
		if ((turnCount % 2 == whiteTurn && sourceColor == Color.BLACK) || (turnCount % 2 == blackTurn && sourceColor == Color.WHITE)) {
			System.out.println("not your turn");
			return true;
		}
		// blocked route
		List<Position> middlePosition = Position.findMiddlePosition(source, target);
		for (Position position : middlePosition) {
			if(!findPiece(position).isEmpty()) {
				System.out.println("blocked route");
				return true;
			}
		}
		// for pawn
		PieceOperations sourcePiece = findPiece(source);
		if(sourcePiece.getType() == Type.PAWN) {
			List<Position> pawnSidePosition = Position.getPawnSidePositioin(sourcePiece.getColor(), source);
			List<Position> pawnPossibleMoves = findPiece(source).getPossibleMoves();
			for (Position position : pawnSidePosition) {
				pawnPossibleMoves.remove(position);
				if (position.equals(target) && findPiece(target).isEmpty()) {
					System.out.println("pawn cant move to there");
					return true;
				}
			}
			for (Position position : pawnPossibleMoves) {
				if (position.equals(target) && !findPiece(target).isEmpty()) {
					System.out.println("pawn cant move to there");
					return true;
				}
			}
		}
		return false;
	}

	String generateRank(int rankIndex) {
		Rank rank = ranks.get(rankIndex);
		StringBuilder sb = new StringBuilder();
		sb.append(rank.generate());
		return sb.toString();
	}

	public String generateBoard(Generatable generator) {
		return generator.generateBoard(ranks);
	}

	public void init() {
		this.ranks = init.initialize();
		turnCount = 0;
	}
}
