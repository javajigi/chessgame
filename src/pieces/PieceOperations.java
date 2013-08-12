package pieces;

import java.util.List;

import pieces.Piece.Color;

public interface PieceOperations {
	char getSymbol();
	Piece leave();
	Piece move(Position target);
	List<Position> getPossibleMoves();
	boolean isEmpty();
	Color getColor();
}
