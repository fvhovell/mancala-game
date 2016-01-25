package nl.fvhovell.game.mancala;

public class Game {
	private MancalaBoard board = new MancalaBoard(30);

	public Game() {
		System.out.println(board);
		performMove(Player.Left, board.getBottomRowPits().get(5));
		System.out.println(board);
	}

	public void performMove(Player player, Pit fromPit) {
		int beads = fromPit.takeBeads();
		Pit currentPit = fromPit;
		for (int b = beads; b > 0; b--) {
			currentPit = currentPit.getNextPit();
			switch (player) {
			case Left:
				if (currentPit == board.getRightMancalaPit()) {
					currentPit = currentPit.getNextPit();
				}
				break;
			case Right:
				if (currentPit == board.getLeftMancalaPit()) {
					currentPit = currentPit.getNextPit();
				}
				break;
			default:
				break;
			}
			
			currentPit.incrementBeads();
		}
	}

	public static void main(String[] args) {
		new Game();
	}
}
