package chess;

import java.util.ArrayList;
import java.util.List;

public class EmptyInitialize implements Initializable {

	@Override
	public List<Rank> initialize() {
		List<Rank> ranks = new ArrayList<Rank>();
		for (int i = 0; i < Board.ROW_SIZE; i++) {
			Rank rank = new Rank(i);
			rank.initializeEmpty();
			ranks.add(rank);
		}
		return ranks;
	}

}
