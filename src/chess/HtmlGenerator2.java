package chess;

import static util.Constants.NEW_LINE;

import java.util.List;

import pieces.PieceOperations;

public class HtmlGenerator2 implements Generatable {

	@Override
	public String generateBoard(List<Rank> ranks) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html> <html> <head> <meta charset=utf-8/> <title>WoogeniusChess</title> <link rel='stylesheet' type='text/css' href='http://woogenius.dothome.co.kr/Chess/style.css'> <script src='http://code.jquery.com/jquery-1.10.2.min.js'></script> <script src='/js/chessboard.js'></script> </head> <body> <div id='wrap'> <div id='wideBorder'>");
		sb.append("<table id='boards' cellpadding='0' cellspacing='0'>" + NEW_LINE);
		for (int i = Board.ROW_SIZE; i > 0; i--) {
			sb.append("<tr>" + NEW_LINE);
			String sRank = generateRank(ranks.get(i-1));
			sb.append(sRank + NEW_LINE);
			sb.append("</tr>" + NEW_LINE);
		}
		sb.append("</table>");
		sb.append("</div> <div id='button'> <ul> <li>Source : <input id='source' type='text'> </li> <li>Target : <input id='target' type='text'> </li> <li><a id='move' href=''>Move</a></li> <li><a href='/'>Refresh</a></li> <li><a href='/new'>New</a></li> </ul> </div> </div> <img id='title' src='http://woogenius.dothome.co.kr/Chess/title.png'> </body> </html>");
		
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
			char symbol = piece.getSymbol();
			sb.append("<td id='" + piece.getPostion().getAxis() + "'><a href='#'>"+symbol+"</a></td>"+ NEW_LINE);
		}
		return sb.toString();
	}

}
