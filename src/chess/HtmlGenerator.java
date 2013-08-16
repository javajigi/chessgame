package chess;

import static util.Constants.NEW_LINE;

import java.util.List;

import pieces.PieceOperations;

public class HtmlGenerator implements Generatable {

	@Override
	public String generateBoard(List<Rank> ranks) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head>" + NEW_LINE);
		sb.append("<script src='http://code.jquery.com/jquery-1.10.2.min.js'></script>" + NEW_LINE);
		sb.append("<script src='/js/chessboard.js'></script>" + NEW_LINE);

		//css 삽입시작
		sb.append("<style>a{color:#000;display:block;font-size:60px;height:80px;position:relative;text-decoration:none;text-shadow:0 1px #fff;width:80px;}#boards { border:5px solid #333; }#boards td {background:#fff;background:-moz-linear-gradient(top, #fff, #eee);background:-webkit-gradient(linear,0 0, 0 100%, from(#fff), to(#eee));box-shadow:inset 0 0 0 1px #fff;-moz-box-shadow:inset 0 0 0 1px #fff;-webkit-box-shadow:inset 0 0 0 1px #fff;height:80px;text-align:center;vertical-align:middle;width:80px;}#boards tr:nth-child(odd) td:nth-child(even),#boards tr:nth-child(even) td:nth-child(odd) {background:#ccc;background:-moz-linear-gradient(top, #ccc, #eee);background:-webkit-gradient(linear,0 0, 0 100%, from(#ccc), to(#eee));box-shadow:inset 0 0 10px rgba(0,0,0,.4);-moz-box-shadow:inset 0 0 10px rgba(0,0,0,.4);-webkit-box-shadow:inset 0 0 10px rgba(0,0,0,.4);}</style>" + NEW_LINE);
		
		sb.append("</head>" + NEW_LINE);
		sb.append("<body>" + NEW_LINE);
		
		// 체스판 출력시작
		sb.append("<table id='boards' cellpadding='0' cellspacing='0'>" + NEW_LINE);
		for (int i = Board.ROW_SIZE; i > 0; i--) {
			sb.append("<tr>" + NEW_LINE);
			String sRank = generateRank(ranks.get(i-1));
			sb.append(sRank + NEW_LINE);
			sb.append("</tr>" + NEW_LINE);
		}
		sb.append("</table>");
		
		sb.append("<br/>");
		sb.append("<br/>");
		sb.append("Source : <input id='source' type='text' /><br/>");
		sb.append("Target : <input id='target' type='text' /><br/>");
		sb.append("<br/>");
		sb.append("<a id='move' href=''><input type='button' href='' value='이동하기' / ></a>");
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
		String sSymbol = null;

		for (int i = 0; i < Board.COLUMN_SIZE; i++) {
			PieceOperations piece = rank.findPiece(i);
			char symbol = piece.getSymbol();
			switch(symbol) {
			case 'P':
				sSymbol = "&#9823;";
				break;
			case 'R':
				sSymbol = "&#9820;";
				break;
			case 'N':
				sSymbol = "&#9822;";
				break;
			case 'B':
				sSymbol = "&#9821;";
				break;
			case 'Q':
				sSymbol = "&#9819;";
				break;
			case 'K':
				sSymbol = "&#9818;";
				break;
				
			case 'p':
				sSymbol = "&#9817;";
				break;
			case 'r':
				sSymbol = "&#9814;";
				break;
			case 'n':
				sSymbol = "&#9816;";
				break;
			case 'b':
				sSymbol = "&#9815;";
				break;
			case 'q':
				sSymbol = "&#9813;";
				break;
			case 'k':
				sSymbol = "&#9812;";
				break;
			default:
				sSymbol = "";
				break;
			}
			sb.append("<td id='" + piece.getPostion().getAxis() + "'><a href='#'>"+sSymbol+"</a></td>"+ NEW_LINE);

		}
		return sb.toString();

	}
}