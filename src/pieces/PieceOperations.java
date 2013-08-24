package pieces;

import java.util.List;

import pieces.Piece.Color;
import pieces.Piece.Type;

public interface PieceOperations {
	char getSymbol();
	PieceOperations leave();
	PieceOperations move(Position target);
	Position getPostion();
	List<Position> getPossibleMoves();
	boolean isEmpty();
	Color getColor();
	Type getType();
}
