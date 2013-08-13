package chess;

import java.util.List;

import pieces.PieceOperations;

public class HtmlGenerator implements Generatable {

	@Override
	public String generateBoard(List<Rank> ranks) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head>" + Board.NEW_LINE);
		sb.append("<script src='http://code.jquery.com/jquery-1.10.2.min.js'></script>" + Board.NEW_LINE);
		sb.append("<script src='/js/chessboard.js'></script>" + Board.NEW_LINE);
		sb.append("</head>" + Board.NEW_LINE);
		sb.append("<body>" + Board.NEW_LINE);
		sb.append("<table id='boards'>" + Board.NEW_LINE);
		for (int i = Board.ROW_SIZE; i > 0; i--) {
			sb.append("<tr height='15'>" + Board.NEW_LINE);
			sb.append(generateRank(ranks.get(i-1)) + Board.NEW_LINE);
			sb.append("</tr>" + Board.NEW_LINE);
		}
		sb.append("</table>");
		sb.append("<br/>");
		sb.append("<br/>");
		sb.append("Source : <input id='source' type='text' /><br/>");
		sb.append("Target : <input id='target' type='text' /><br/>");
		sb.append("<br/>");
		sb.append("<a id='move' href=''>이동하기</a>");
		sb.append("</body></html>");
		return sb.toString();
	}

	private String generateRank(Rank rank) {
		StringBuilder sb = new StringBuilder();
		sb.append(generate(rank));
		return sb.toString();
	}
	
	private String generate(Rank rank) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Board.COLUMN_SIZE; i++) {
			PieceOperations piece = rank.findPiece(i);
			sb.append("<td width='15' id='" + piece.getPostion().getAxis() + "'>");
			sb.append(piece.getSymbol());
			sb.append("</td>");
		}
		return sb.toString();
	}
}