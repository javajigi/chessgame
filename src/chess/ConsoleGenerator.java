package chess;

import static util.Constants.NEW_LINE;

import java.util.List;

public class ConsoleGenerator implements Generatable {

	@Override
	public String generateBoard(List<Rank> ranks) {
		StringBuilder sb = new StringBuilder();
		for (int i = Board.ROW_SIZE; i > 0; i--) {
			Rank rank = ranks.get(i-1);
			sb.append(generateRank(rank) + NEW_LINE);
		}
		return sb.toString();
	}
	
	private String generateRank(Rank rank) {
		StringBuilder sb = new StringBuilder();
		sb.append(rank.generate());
		return sb.toString();
	}
}
