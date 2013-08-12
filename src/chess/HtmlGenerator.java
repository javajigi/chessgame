package chess;

import java.util.List;

public class HtmlGenerator implements Generatable {

	@Override
	public String generateBoard(List<Rank> ranks) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body>");
		for (int i = Board.ROW_SIZE; i > 0; i--) {
			Rank rank = ranks.get(i-1);
			sb.append(generateRank(rank) + Board.NEW_LINE);
		}
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}

	private String generateRank(Rank rank) {
		StringBuilder sb = new StringBuilder();
		sb.append(rank.generate());
		return sb.toString();
	}
}
