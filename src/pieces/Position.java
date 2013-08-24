package pieces;

import java.util.ArrayList;
import java.util.List;

import pieces.Piece.Color;
import chess.Board;

public class Position {
	private static final char COLUMN_START_CHAR = 'a';

	private int x;
	private int y;

	public Position(String position) {
		// 에러 상태에 대한 처리 필요함.
		this.x = generateColumnIndex(position.charAt(0));
		this.y = Integer.parseInt(position.substring(1)) - 1;
	}

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private int generateColumnIndex(char columnIndex) {
		int target = Character.getNumericValue(columnIndex);
		int source = Character.getNumericValue(COLUMN_START_CHAR);
		return target - source;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public String getAxis() {
		return "" + (char)(COLUMN_START_CHAR + x) + (y+1);
	}
	
	Position move(Direction direction) {
		return new Position(this.x + direction.getXDegree(), this.y + direction.getYDegree());
	}

	List<Position> findsPosition(Direction direction) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position currentPosition = move(direction);
		while(currentPosition.isValid()) {
			positions.add(currentPosition);
			currentPosition = currentPosition.move(direction);
		}
		return positions;
	}
	
	public boolean isValid() {
		if ( y < 0 || y >= Board.ROW_SIZE) {
			return false;
		}

		if ( x < 0 || x >= Board.COLUMN_SIZE) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public static List<Position> findMiddlePosition(Position source, Position target) {
		ArrayList<Position> middlePosition = new ArrayList<Position>();
		ArrayList<Integer> middleX = new ArrayList<Integer>();
		ArrayList<Integer> middleY = new ArrayList<Integer>();
		int sourceX = source.getX();
		int sourceY = source.getY();
		int targetX = target.getX();
		int targetY = target.getY();
		
		if(sourceX == targetX) {
			middleY = findAllMiddleNum(sourceY, targetY);
			for (Integer posY : middleY) {
				middlePosition.add(new Position(sourceX, posY));
			}
		} else if(sourceY == targetY) {
			middleX = findAllMiddleNum(sourceX, targetX);
			for (Integer posX : middleX) {
				middlePosition.add(new Position(posX, sourceY));
			}
		} else {
			middleX = findAllMiddleNum(sourceX, targetX);
			middleY = findAllMiddleNum(sourceY, targetY);
			if(middleX.size() == middleY.size()) {
				for (int i = 0; i < middleX.size(); i++) {
					middlePosition.add(new Position(middleX.get(i), middleY.get(i)));
				}
			}
		}
		
		return middlePosition;
	}

	private static ArrayList<Integer> findAllMiddleNum(int a, int b) {
		ArrayList<Integer> middleNum = new ArrayList<Integer>();
		int from = 0;
		int to = 0;
		
		if (a > b) {
			to = a;
			from = b;
		} else if (a < b) {
			to = b;
			from = a;
		} else {
			return middleNum;
		}
		
		while(from+1 != to) {
			middleNum.add(from+1);
			from++;
		}
		
		return middleNum;
	}

	public static List<Position> getPawnSidePositioin(Color color, Position source) {
		List<Position> pawnSidePosition = new ArrayList<Position>();
		if (color == Color.WHITE) {
			pawnSidePosition.add(source.move(Direction.NORTHEAST));
			pawnSidePosition.add(source.move(Direction.NORTHWEST));
		} else {
			pawnSidePosition.add(source.move(Direction.SOUTHEAST));
			pawnSidePosition.add(source.move(Direction.SOUTHWEST));
		}
		return pawnSidePosition;
	}
}
