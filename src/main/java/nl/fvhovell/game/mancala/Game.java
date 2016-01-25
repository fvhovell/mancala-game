package nl.fvhovell.game.mancala;

public class Game {
	private MancalaBoard board = new MancalaBoard(30);

	public Game() {
		System.out.println(board);
		performMove(Player.Left, board.getBottomRowHoles().get(5));
		System.out.println(board);
	}

	public void performMove(Player player, Hole fromHole) {
		int beads = fromHole.takeBeads();
		Hole currentHole = fromHole;
		for (int b = beads; b > 0; b--) {
			currentHole = currentHole.getNextHole();
			switch (player) {
			case Left:
				if (currentHole == board.getRightMancalaStore()) {
					currentHole = currentHole.getNextHole();
				}
				break;
			case Right:
				if (currentHole == board.getLeftMancalaStore()) {
					currentHole = currentHole.getNextHole();
				}
				break;
			default:
				break;
			}
			currentHole.incrementBeads();
		}
	}

	public static void main(String[] args) {
		new Game();
	}
}
