package pieces;

import java.util.ArrayList;
import java.util.List;

public class PositionController {
	private Position position;;

	public PositionController(Position position) {
		this.position = position;
	}

	public List<Position> findsLinearPositionAll() {
		Direction[] linears = Direction.linearDirection();
		List<Position> positions = new ArrayList<Position>();
		for (Direction direction : linears) {
			positions.addAll(position.findsPosition(direction));
		}
		return positions;
	}

	public List<Position> findsDiagonalPositionAll() {
		Direction[] diagonals = Direction.diagonalDirection();
		List<Position> positions = new ArrayList<Position>();
		for (Direction direction : diagonals) {
			positions.addAll(position.findsPosition(direction));
		}
		return positions;
	}

	public List<Position> findsQueenPositionAll() {
		Direction[] everyDirection = Direction.everyDirection();
		List<Position> positions = new ArrayList<Position>();
		for (Direction direction : everyDirection) {
			positions.addAll(position.findsPosition(direction));
		}
		return positions;
	}
	
	public List<Position> findsKingPositionAll() {
		Direction[] everyDirection = Direction.everyDirection();
		List<Position> positions = new ArrayList<Position>();
		for (Direction direction : everyDirection) {
			Position movedPosition = position.move(direction);
			if (movedPosition.isValid()) {
				positions.add(movedPosition);				
			}
		}
		return positions;
	}
	
	public List<Position> findsWhitePawnPositionAll() {
		int whiteStartY = 1;
		List<Position> positions = new ArrayList<Position>();
		Position movedPosition = position.move(Direction.NORTH);
		addPositionWhenIsValid(positions, movedPosition);
		if (position.getY() == whiteStartY) {
			positions.add(movedPosition.move(Direction.NORTH));
		}
		addPositionWhenIsValid(positions, position.move(Direction.NORTHEAST));
		addPositionWhenIsValid(positions, position.move(Direction.NORTHWEST));
		
		return positions;
	}
	
	private void addPositionWhenIsValid(List<Position> positions, Position position) {
		if (position.isValid()) {
			positions.add(position);
		}
	}
	
	public List<Position> findsBlackPawnPositionAll() {
		int blackStartY = 6;
		List<Position> positions = new ArrayList<Position>();
		Position movedPosition = position.move(Direction.SOUTH);
		addPositionWhenIsValid(positions, movedPosition);
		if (position.getY() == blackStartY) {
			positions.add(movedPosition.move(Direction.SOUTH));
		}
		addPositionWhenIsValid(positions, position.move(Direction.SOUTHEAST));
		addPositionWhenIsValid(positions, position.move(Direction.SOUTHWEST));
		
		return positions;
	}

	public List<Position> findsKnightPositionAll() {
		Direction[] knightDirection = Direction.knightDirection();
		List<Position> positions = new ArrayList<Position>();
		for (Direction direction : knightDirection) {
			Position movedPosition = position.move(direction);
			if (movedPosition.isValid()) {
				positions.add(movedPosition);
			}
		}
		return positions;
	}
	
}
