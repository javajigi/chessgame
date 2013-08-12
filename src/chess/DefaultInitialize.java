package chess;

import java.util.ArrayList;
import java.util.List;

public class DefaultInitialize implements Initializable {

	@Override
	public List<Rank> initialize() {
		List<Rank> ranks = new ArrayList<Rank>();
		
		for (int i = 0; i < Board.ROW_SIZE; i++) {
			Rank rank = new Rank(i);
			if (i == 0) {
				rank.initializeWhiteExceptPawn();
			} else if (i == 1) {
				rank.initializeWhitePawn();
			} else if (i == 6) {
				rank.initializeBlackPawn();
			} else if (i == 7) {
				rank.initializeBlackExceptPawn();
			} else {
				rank.initializeEmpty();
			}
			ranks.add(rank);
		}
		
		return ranks;
	}

}
