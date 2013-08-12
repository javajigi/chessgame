package pieces;

import pieces.Piece.Color;

public class PieceFactory {
	public static PieceOperations noPiece(Position position) {
		return new Empty(Color.NOCOLOR, position);
	}

	public static PieceOperations createWhitePawn(Position position) {
		return new Pawn(Color.WHITE, position);
	}

	public static PieceOperations createBlackPawn(Position position) {
		return new Pawn(Color.BLACK, position);
	}

	public static PieceOperations createWhiteRook(Position position) {
		return new Rook(Color.WHITE, position);
	}

	public static PieceOperations createBlackRook(Position position) {
		return new Rook(Color.BLACK, position);
	}

	public static PieceOperations createWhiteKnight(Position position) {
		return new Knight(Color.WHITE, position);
	}

	public static PieceOperations createBlackKnight(Position position) {
		return new Knight(Color.BLACK, position);
	}

	public static PieceOperations createWhiteBishop(Position position) {
		return new Bishop(Color.WHITE, position);
	}

	public static PieceOperations createBlackBishop(Position position) {
		return new Bishop(Color.BLACK, position);
	}

	public static PieceOperations createWhiteQueen(Position position) {
		return  new Queen(Color.WHITE, position);
	}

	public static PieceOperations createBlackQueen(Position position) {
		return new Queen(Color.BLACK, position);
	}

	public static PieceOperations createWhiteKing(Position position) {
		return new King(Color.WHITE, position);
	}

	public static PieceOperations createBlackKing(Position position) {
		return new King(Color.BLACK, position);
	}
}
