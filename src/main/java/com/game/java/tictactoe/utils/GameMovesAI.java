package com.game.java.tictactoe.utils;

import java.util.Random;

import com.game.java.tictactoe.entity.GameState;
import com.game.java.tictactoe.enums.Marker;

public class GameMovesAI {

	/**
	 * This method is called during play against the computer, and 
	 * attempts to find the best possible move.
	 * 
	 * @param gameState
	 */
	public static void determineBestMove(GameState gameState)
	{
		Marker board[][] = gameState.getBoard().board;
		Marker playerMarker = gameState.getTurn() ;
		Marker opponentMarker = playerMarker.equals(Marker.X) ? Marker.O : Marker.X ;
		
		// First, determine if there is a block that needs to be made.
		// Check the center first, if empty, blocker-wise
		if( board[1][1].equals(Marker.BLANK)) {
			if((board[0][1].equals(opponentMarker) &&
				board[2][1].equals(opponentMarker)) ||
			   (board[1][0].equals(opponentMarker) &&
				board[1][2].equals(opponentMarker)) ||
			   (board[0][0].equals(opponentMarker) &&  
				board[2][2].equals(opponentMarker)) ||
			   (board[0][2].equals(opponentMarker) &&  
				board[2][0].equals(opponentMarker))) {
					
				try {
					gameState.getBoard().move(1, 1, playerMarker );
					return;
				}
				catch(Exception e) {
					// Since we already checked, swallow
				}
			}
		}
		
		// Next, check if there is a block move in the verticals.
		for(int r = 0; r < 3; ++r) {
			int bCount = 0;
			int oCount = 0;
			for(int c = 0; c < 3; ++c) {
				if(board[r][c].equals(opponentMarker)) {
					++oCount;
				}
				if(board[r][c].equals(Marker.BLANK)) {
					++bCount;
				}
			}
			
			// If there were two opponent markers and a blank,
			// move to the blank spot.
			if((oCount == 2) && (bCount == 1)) {
				for(int c = 0; c < 3; ++c) {
					if(board[r][c].equals(Marker.BLANK)) {
						try {
							gameState.getBoard().move(r, c, playerMarker);
							return;
						}
						catch(Exception e) {
							// Since we already checked, swallow
						}
					}
				}
			}
		}
		
		// Next, check rows for blockers.
		for(int c = 0; c < 3; ++c) {
			int bCount = 0;
			int oCount = 0;
			for(int r = 0; r < 3; ++r) {
				if(board[r][c].equals(opponentMarker)) {
					++oCount;
				}
				if(board[r][c].equals(Marker.BLANK)) {
					++bCount;
				}
			}
			
			// If there were two opponent markers and a blank,
			// move to the blank spot.
			if((oCount == 2) && (bCount == 1)) {
				for(int r = 0; r < 3; ++r) {
					if(board[r][c].equals(Marker.BLANK)) {
						try {
							gameState.getBoard().move(r, c, playerMarker);
							return;
						}
						catch(Exception e) {
							// Since we already checked, swallow
						}
					}
				}
			}
		}
		
		// And lastly for blockers, check for diagonals
		int bCount = 0;
		int oCount = 0;
		int r = 0;
		int c = 0;
		for(int i = 0; i < 3; ++i) {
			if(board[r][c].equals(opponentMarker)) {
				++oCount;
			}
			if(board[r][c].equals(Marker.BLANK)) {
				++bCount;
			}
			++r;
			++c;
		}
		if((oCount == 2) && (bCount == 1)) {
			r = 0;
			c = 0;
			for(int i = 0; i < 3; ++i) {
				if(board[r][c].equals(Marker.BLANK)) {
					try {
						gameState.getBoard().move(r, c, playerMarker);
						return;
					}
					catch(Exception e) {
						// Since we already checked, swallow
					}
				}
				++r;
				++c;
			}
		}
		r = 0;
		c = 2;
		bCount = 0;
		oCount = 0;
		for(int i = 0; i < 3; ++i) {
			if(board[r][c].equals(opponentMarker)) {
				++oCount;
			}
			if(board[r][c].equals(Marker.BLANK)) {
				++bCount;
			}
			++r;
			--c;
		}
		if((oCount == 2) && (bCount == 1)) {
			r = 0;
			c = 2;
			for(int i = 0; i < 3; ++i) {
				if(board[r][c].equals(Marker.BLANK)) {
					try {
						gameState.getBoard().move(r, c, playerMarker);
						return;
					}
					catch(Exception e) {
						// Since we already checked, swallow
					}
				}
				++r;
				--c;
			}
		}
		
		// If still available, take the center; always a good move.
		if(board[1][1].equals(Marker.BLANK)) {
			try {
				gameState.getBoard().move(1, 1, playerMarker );
				return;
			}
			catch(Exception e) {
				// Since we already checked, swallow
			}			
		}
		
		// TODO: Add logic that moves in such a way to force
		// human to make a block move.
		
		// Keep generating random positions until a blank spot is found
		boolean found = false;
		Random random = new Random();
		while(!found) {
			r = random.nextInt(3);
			c = random.nextInt(3);
			if(board[r][c].equals(Marker.BLANK)) {
				try {
					gameState.getBoard().move(r, c, playerMarker );
					found = true;
				}
				catch(Exception e) {
					//log.error("Problem making random move!", e);
				}			
			}
		}
	}
}
