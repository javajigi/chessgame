package pieces;

import java.util.List;

class Queen extends Piece {
	Queen(Color color, Position position) {
		super(color, Type.QUEEN, position);
	}

	@Override
	public List<Position> getPossibleMoves() {
		PositionController posCon = new PositionController(getPostion());
		List<Position> queenMoves = posCon.findsQueenPositionAll();
		return queenMoves;
	}
}
