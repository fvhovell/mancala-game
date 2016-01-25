package nl.fvhovell.game.mancala;

public class Pit {
	private int nrOfBeads;

	private Pit nextPit;
	private Pit oppositePit;

	public Pit(int nrOfBeads) {
		this.nrOfBeads = nrOfBeads;
	}

	@Override
	public String toString() {
		return String.format("%2d", getNrOfBeads());
	}

	public Pit getNextPit() {
		return nextPit;
	}

	public void setNextPit(Pit nextPit) {
		this.nextPit = nextPit;
	}

	public Pit getOppositePit() {
		return oppositePit;
	}

	public void setOppositePit(Pit oppositePit) {
		this.oppositePit = oppositePit;
	}

	public int getNrOfBeads() {
		return nrOfBeads;
	}

	public int takeBeads() {
		int beads = nrOfBeads;
		nrOfBeads = 0;
		return beads;
	}
	
	public void incrementBeads() {
		nrOfBeads += 1;
	}
}
