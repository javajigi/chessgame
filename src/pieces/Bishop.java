package pieces;

import java.util.List;


class Bishop extends Piece {
	Bishop(Color color, Position position) {
		super(color, Type.BISHOP, position);
	}

	@Override
	public List<Position> getPossibleMoves() {
		PositionController posCon = new PositionController(position);
		List<Position> bishopMoves = posCon.findsDiagonalPositionAll();
		return bishopMoves;
	}
}