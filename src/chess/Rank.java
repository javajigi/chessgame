package chess;

import java.util.ArrayList;
import java.util.List;

import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceOperations;
import pieces.Position;

public class Rank {
	private List<PieceOperations> rank = new ArrayList<PieceOperations>();
	
	private int rankPosition;
	
	Rank(int yPosition) {
		this.rankPosition = yPosition;
	}

	void initializeEmpty() {
		for (int i = 0; i < Board.COLUMN_SIZE; i++) {
			rank.add( PieceFactory.noPiece(new Position(i, rankPosition)) );
		}
	}
	
	void initializeWhitePawn() {
		for (int i = 0; i < Board.COLUMN_SIZE; i++) {
			rank.add(PieceFactory.createWhitePawn(new Position(i, rankPosition)) );
		}
	}
	
	void initializeBlackPawn() {
		for (int i = 0; i < Board.COLUMN_SIZE; i++) {
			rank.add(PieceFactory.createBlackPawn(new Position(i, rankPosition)) );
		}
	}
	
	void initializeWhiteExceptPawn() {
		rank.add(PieceFactory.createWhiteRook(new Position(0, rankPosition)) );
		rank.add(PieceFactory.createWhiteKnight(new Position(1, rankPosition)) );
		rank.add(PieceFactory.createWhiteBishop(new Position(2, rankPosition)) );
		rank.add(PieceFactory.createWhiteQueen(new Position(3, rankPosition)) );
		rank.add(PieceFactory.createWhiteKing(new Position(4, rankPosition)) );
		rank.add(PieceFactory.createWhiteBishop(new Position(5, rankPosition)) );
		rank.add(PieceFactory.createWhiteKnight(new Position(6, rankPosition)) );
		rank.add(PieceFactory.createWhiteRook(new Position(7, rankPosition)) );
	}
	

	void initializeBlackExceptPawn() {
		rank.add(PieceFactory.createBlackRook(new Position(0, rankPosition)) );
		rank.add(PieceFactory.createBlackKnight(new Position(1, rankPosition)) );
		rank.add(PieceFactory.createBlackBishop(new Position(2, rankPosition)) );
		rank.add(PieceFactory.createBlackQueen(new Position(3, rankPosition)) );
		rank.add(PieceFactory.createBlackKing(new Position(4, rankPosition)) );
		rank.add(PieceFactory.createBlackBishop(new Position(5, rankPosition)) );
		rank.add(PieceFactory.createBlackKnight(new Position(6, rankPosition)) );
		rank.add(PieceFactory.createBlackRook(new Position(7, rankPosition)) );
	}

	String generate() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Board.COLUMN_SIZE; i++) {
			sb.append(rank.get(i).getSymbol());
		}
		return sb.toString();
	}
	
	PieceOperations findPiece(int index) {
		return rank.get(index);
	}

	PieceOperations findPiece(Position position) {
		return findPiece(position.getX());
	}

	void changePiece(int xPosition, Piece targetPiece) {
		rank.set(xPosition, targetPiece);
	}

	PieceOperations move(PieceOperations sourcePiece, Position target) {
		sourcePiece.move(target);
		rank.set(target.getX(), (Piece) sourcePiece);
		return sourcePiece;
	}
}
