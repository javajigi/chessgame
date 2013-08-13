package pieces;

import java.util.List;

class King extends Piece {
	King(Color color, Position position) {
		super(color, Type.KING, position);
	}

	@Override
	public List<Position> getPossibleMoves() {
		PositionController posCon = new PositionController(getPostion());
		List<Position> kingMoves = posCon.findsKingPositionAll();
		return kingMoves;
	}
}

