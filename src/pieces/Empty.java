package pieces;

import java.util.ArrayList;
import java.util.List;

class Empty extends Piece {
	Empty(Color color, Position position) {
		super(color, Type.EMPTY, position);
	}

	@Override
	public List<Position> getPossibleMoves() {
		List<Position> emptyMoves = new ArrayList<Position>();
		return emptyMoves;
	}
}
