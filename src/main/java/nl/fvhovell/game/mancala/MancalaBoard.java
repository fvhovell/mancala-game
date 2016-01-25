package nl.fvhovell.game.mancala;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MancalaBoard {
	private List<Pit> topRowPits = new ArrayList<Pit>();
	private List<Pit> bottomRowPits = new ArrayList<Pit>();
	private Pit leftMancalaPit = new Pit(0);
	private Pit rightMancalaPit = new Pit(0);

	public MancalaBoard(int initialNrOfBeads) {
		Pit nextTopPit = leftMancalaPit;
		Pit nextBottomPit = rightMancalaPit;
		for (int i = 0; i < 6; i++) {
			Pit topPit = new Pit(initialNrOfBeads);
			Pit bottomPit = new Pit(initialNrOfBeads);

			topPit.setNextPit(nextTopPit);
			bottomPit.setNextPit(nextBottomPit);

			topRowPits.add(topPit);
			bottomRowPits.add(bottomPit);
			nextTopPit = topPit;
			nextBottomPit = bottomPit;
		}
		Collections.reverse(bottomRowPits);

		for (int i = 0; i < 6; i++) {
			Pit topPit = topRowPits.get(i);
			Pit bottomPit = bottomRowPits.get(i);
			topPit.setOppositePit(bottomPit);
			bottomPit.setOppositePit(topPit);
		}
		leftMancalaPit.setNextPit(nextBottomPit);
		rightMancalaPit.setNextPit(nextTopPit);
	}

	public Pit getLeftMancalaPit() {
		return leftMancalaPit;
	}
	
	public Pit getRightMancalaPit() {
		return rightMancalaPit;
	}
	
	public List<Pit> getTopRowPits() {
		return topRowPits;
	}
	
	public List<Pit> getBottomRowPits() {
		return bottomRowPits;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("    ");
		for (int i = 0; i < 6; i++) {
			Pit topPit = topRowPits.get(i);
			s.append(topPit).append("  ");
		}
		s.append("\n");
		s.append(leftMancalaPit).append("   |   |   |   |   |   |  ").append(rightMancalaPit);
		s.append("\n    ");
		for (int i = 0; i < 6; i++) {
			Pit bottomPit = bottomRowPits.get(i);
			s.append(bottomPit).append("  ");
		}
		s.append("\n");
		return s.toString();
	}
}
