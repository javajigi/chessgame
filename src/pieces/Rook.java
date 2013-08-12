package pieces;

import java.util.List;

class Rook extends Piece {
	Rook(Color color, Position position) {
		super(color, Type.ROOK, position);
	}

	@Override
	public List<Position> getPossibleMoves() {
		PositionController posCon = new PositionController(position);
		List<Position> rookMoves = posCon.findsLinearPositionAll();
		return rookMoves;
	}
}
