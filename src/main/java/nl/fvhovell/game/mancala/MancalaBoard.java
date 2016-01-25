package nl.fvhovell.game.mancala;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MancalaBoard {
	private List<Hole> topRowHoles = new ArrayList<Hole>();
	private List<Hole> bottomRowHoles = new ArrayList<Hole>();
	private Hole leftMancalaStore = new Hole(0);
	private Hole rightMancalaStore = new Hole(0);

	public MancalaBoard(int initialNrOfBeads) {
		Hole nextTopHole = leftMancalaStore;
		Hole nextBottomHole = rightMancalaStore;
		for (int i = 0; i < 6; i++) {
			Hole topHole = new Hole(initialNrOfBeads);
			Hole bottomHole = new Hole(initialNrOfBeads);

			topHole.setNextHole(nextTopHole);
			bottomHole.setNextHole(nextBottomHole);

			topRowHoles.add(topHole);
			bottomRowHoles.add(bottomHole);
			nextTopHole = topHole;
			nextBottomHole = bottomHole;
		}
		Collections.reverse(bottomRowHoles);

		for (int i = 0; i < 6; i++) {
			Hole topHole = topRowHoles.get(i);
			Hole bottomHole = bottomRowHoles.get(i);
			topHole.setOppositeHole(bottomHole);
			bottomHole.setOppositeHole(topHole);
		}
		leftMancalaStore.setNextHole(nextBottomHole);
		rightMancalaStore.setNextHole(nextTopHole);
	}

	public Hole getLeftMancalaStore() {
		return leftMancalaStore;
	}
	
	public Hole getRightMancalaStore() {
		return rightMancalaStore;
	}
	
	public List<Hole> getTopRowHoles() {
		return topRowHoles;
	}
	
	public List<Hole> getBottomRowHoles() {
		return bottomRowHoles;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("    ");
		for (int i = 0; i < 6; i++) {
			Hole topPit = topRowHoles.get(i);
			s.append(topPit).append("  ");
		}
		s.append("\n");
		s.append(leftMancalaStore).append("   |   |   |   |   |   |  ").append(rightMancalaStore);
		s.append("\n    ");
		for (int i = 0; i < 6; i++) {
			Hole bottomPit = bottomRowHoles.get(i);
			s.append(bottomPit).append("  ");
		}
		s.append("\n");
		return s.toString();
	}
}
