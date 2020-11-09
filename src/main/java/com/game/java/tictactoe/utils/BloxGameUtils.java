package com.game.java.tictactoe.utils;

import com.game.java.tictactoe.entity.Board;
import com.game.java.tictactoe.entity.GameState;
import com.game.java.tictactoe.enums.GameStage;
import com.game.java.tictactoe.enums.Marker;

public class BloxGameUtils {

	/**
	 * Evaluate the game board to see if a winner can be declared, or if there is a draw.
	 * If neither of these conditions is detected, switch active player.
	 * 
	 * @param gameState
	 */
	public static void evaluateBoard(GameState gameState) {
		Board board = gameState.getBoard();
		// First, check for a draw
		if(board.isDraw()) {
			gameState.setGameMessage("It's a draw!");
			gameState.setGameStage(GameStage.POST_GAME);
		}
		else if(board.isWinner(gameState.getTurn())) {
			if(gameState.getTurn().equals(Marker.O)) {
				gameState.setGameMessage("O wins!");
			}
			else {
				gameState.setGameMessage("X wins!");
			}
			gameState.setGameStage(GameStage.POST_GAME);
		}
		else
		{
			if(gameState.getTurn() == Marker.X) {
				gameState.setTurn(Marker.O);
				gameState.setTurnMessage("Turn: O");
			}
			else {
				gameState.setTurn(Marker.X);
				gameState.setTurnMessage("Turn: X");
			}
		}
	}
	

}
