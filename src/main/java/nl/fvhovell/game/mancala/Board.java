package nl.fvhovell.game.mancala;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {
	private final static Random random = new Random();

	private List<Hole> topRowHoles = new ArrayList<Hole>();
	private List<Hole> bottomRowHoles = new ArrayList<Hole>();
	private Hole leftMancala = new Hole(0, Player.Left);
	private Hole rightMancala = new Hole(0, Player.Right);

	private Player currentPlayer;

	public Board(int initialNrOfBeads, Player initialPlayer) {
		this.currentPlayer = initialPlayer;

		Hole nextTopHole = leftMancala;
		Hole nextBottomHole = rightMancala;
		for (int i = 0; i < 6; i++) {
			Hole topHole = new Hole(initialNrOfBeads, Player.Left);
			Hole bottomHole = new Hole(initialNrOfBeads, Player.Right);

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
		leftMancala.setNextHole(nextBottomHole);
		rightMancala.setNextHole(nextTopHole);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("    ");
		for (int i = 0; i < 6; i++) {
			Hole topHole = topRowHoles.get(i);
			s.append(topHole).append("  ");
		}
		s.append("\n");
		s.append(leftMancala).append("   |   |   |   |   |   |  ").append(rightMancala);
		s.append("\n    ");
		for (int i = 0; i < 6; i++) {
			Hole bottomHole = bottomRowHoles.get(i);
			s.append(bottomHole).append("  ");
		}
		s.append("\n");
		return s.toString();
	}

	public boolean gameIsFinished() {
		if (getAvailableMoves().isEmpty()) {
			switchPlayer();
			collectBeads();
			return true;
		}
		return false;
	}

	public int getRandomAvailableMove() {
		List<Integer> availableMoves = getAvailableMoves();
		return availableMoves.get(random.nextInt(availableMoves.size()));
	}

	List<Integer> getAvailableMoves() {
		List<Integer> moves = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			Hole hole = getPlayerHoles().get(i);
			if (!(hole.isEmpty())) {
				moves.add(i);
			}
		}
		return moves;
	}

	private List<Hole> getPlayerHoles() {
		if (getCurrentPlayer() == Player.Left) {
			return getTopRowHoles();
		}
		return getBottomRowHoles();
	}

	private Hole getPlayerStore() {
		if (getCurrentPlayer() == Player.Left) {
			return getLeftMancala();
		}
		return getRightMancala();
	}

	public void performMove(int startHoleIdx) {
		if (startHoleIdx < 0 || startHoleIdx > 5) {
			throw new IllegalArgumentException("Start hole index outside range 0 - 5");
		}
		Hole startHole = getPlayerHoles().get(startHoleIdx);
		if (startHole.isEmpty()) {
			throw new IllegalStateException("Cannot start from an empty hole");
		}
		
		int beads = startHole.takeBeads();
		Hole currentHole = startHole;
		boolean nextPlayer = true;
		for (int b = beads; b > 0; b--) {
			currentHole = currentHole.getNextHole();
			if ((getCurrentPlayer() == Player.Left && currentHole == getRightMancala())
					|| (getCurrentPlayer() == Player.Right && currentHole == getLeftMancala())) {
				// Skip opponent's store/mancala
				currentHole = currentHole.getNextHole();
			}
			if (b == 1 && currentHole.getOwner() == getCurrentPlayer()) {
				// Last bead to drop is in player own hole
				if (currentHole == getLeftMancala() || currentHole == getRightMancala()) {
					// Last bead is in players own store / mancala, which means
					// he/she gets another turn.
					currentHole.incrementBeads(1);
					nextPlayer = false;
				} else if (currentHole.isEmpty() || currentHole == startHole) {
					// Last bead ends up in empty hole or start hole, but not
					// one of the stores/mancala's, so all beads from opposite
					// hole are captured.
					// Calculate number of beads captured.
					int capturedBeads = 1 + currentHole.getOppositeHole().takeBeads();
					// Deposit the captured beads in the players own store /
					// mancala
					getPlayerStore().incrementBeads(capturedBeads);
					nextPlayer = false;
				} else {
					// Just increment beads like normal
					currentHole.incrementBeads(1);
				}
			} else {
				currentHole.incrementBeads(1);
			}
		}
		if (nextPlayer) {
			switchPlayer();
		}
	}

	private void switchPlayer() {
		if (getCurrentPlayer() == Player.Left) {
			currentPlayer = Player.Right;
		} else {
			currentPlayer = Player.Left;
		}
	}

	private void collectBeads() {
		int capturedBeads = 0;
		for (Integer holeIdx : getAvailableMoves()) {
			capturedBeads += getPlayerHoles().get(holeIdx).takeBeads();
		}
		getPlayerStore().incrementBeads(capturedBeads);
	}

	public Hole getLeftMancala() {
		return leftMancala;
	}

	public Hole getRightMancala() {
		return rightMancala;
	}

	public List<Hole> getTopRowHoles() {
		return topRowHoles;
	}

	public List<Hole> getBottomRowHoles() {
		return bottomRowHoles;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player getWinner() {
		int leftBeads = getLeftMancala().getNrOfBeads();
		int rightBeads = getRightMancala().getNrOfBeads();
		if (leftBeads < rightBeads) {
			return Player.Right;
		} else if (leftBeads > rightBeads) {
			return Player.Left;
		} else {
			return Player.Undetermined;
		}
	}
}
