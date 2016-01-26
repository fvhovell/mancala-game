package nl.fvhovell.game.mancala;

import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		Board board = new Board(4, Player.Right);

		System.out.println("Initial state");
		System.out.println(board);

		int move = 0;
		try (Scanner input = new Scanner(System.in)) {
			while (!board.gameIsFinished()) {
				System.out.print("Move " + (move++) + ": Player " + board.getCurrentPlayer() + " takes beads from");

				int moveFromIdx;
				if (board.getCurrentPlayer() == Player.Left) {
					moveFromIdx = board.getRandomAvailableMove();
					System.out.println(" hole " + moveFromIdx);
				} else {
					System.out.println(" which hole " + board.getAvailableMoves() + " ? ");
					moveFromIdx = input.nextInt();
					while (!board.isValidMove(moveFromIdx)) {
						System.out.println("That hole is empty, please select a non-empty hole");
						moveFromIdx = input.nextInt();
					}
				}
				board.performMove(moveFromIdx);
				System.out.println(board);
			}
		}
		System.out.println("Collecting all beads for player " + board.getCurrentPlayer());
		System.out.println(board);

		System.out.println("And the winner is: " + board.getWinner());
	}
}
