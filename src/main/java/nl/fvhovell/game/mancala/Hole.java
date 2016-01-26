package nl.fvhovell.game.mancala;

public class Hole {
	private int nrOfBeads;
	private Player owner;

	private Hole nextHole;
	private Hole oppositeHole;

	public Hole(int nrOfBeads, Player owner) {
		this.nrOfBeads = nrOfBeads;
		this.owner = owner;
	}

	@Override
	public String toString() {
		return String.format("%2d", getNrOfBeads());
	}

	public Hole getNextHole() {
		return nextHole;
	}

	public void setNextHole(Hole nextHole) {
		this.nextHole = nextHole;
	}

	public Hole getOppositeHole() {
		return oppositeHole;
	}

	public void setOppositeHole(Hole oppositeHole) {
		this.oppositeHole = oppositeHole;
	}

	public int getNrOfBeads() {
		return nrOfBeads;
	}
	
	public Player getOwner() {
		return owner;
	}

	public int takeBeads() {
		int beads = nrOfBeads;
		nrOfBeads = 0;
		return beads;
	}
	
	public void incrementBeads(int n) {
		nrOfBeads += n;
	}

	public boolean isEmpty() {
		return getNrOfBeads() == 0;
	}
}
