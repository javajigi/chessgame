package pieces;

import java.util.ArrayList;
import java.util.List;

class Pawn extends Piece {
	Pawn(Color color, Position position) {
		super(color, Type.PAWN, position);
	}

	@Override
	public List<Position> getPossibleMoves() {
		PositionController posCon = new PositionController(getPostion());
		List<Position> pawnMoves = new ArrayList<Position>();
		if (color == Color.WHITE) {
			pawnMoves = posCon.findsWhitePawnPositionAll();
		} else if(color == Color.BLACK) {
			pawnMoves = posCon.findsBlackPawnPositionAll();
		}
		return pawnMoves;
	}
}
