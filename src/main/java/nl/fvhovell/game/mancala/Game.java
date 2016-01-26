package nl.fvhovell.game.mancala;

public class Game {
	public static void main(String[] args) {
		Board board = new Board(4, Player.Right);

		System.out.println("Initial state");
		System.out.println(board);

		int move = 0;
		while (!board.gameIsFinished()) {
			int moveFromIdx = board.getRandomAvailableMove();
			System.out.println("Move " + (move++) + ": Player " + board.getCurrentPlayer() //
					+ " takes beads out of hole " + (1 + moveFromIdx));
			board.performMove(moveFromIdx);
			System.out.println(board);
		}
		System.out.println("Collecting all beads for player " + board.getCurrentPlayer());
		System.out.println(board);

		System.out.println("And the winner is: " + board.getWinner());
	}
}
