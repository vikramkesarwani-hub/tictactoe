package com.game.java.titactoe.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.game.java.tictactoe.constants.Constants;
import com.game.java.tictactoe.entity.Board;
import com.game.java.tictactoe.entity.GameState;
import com.game.java.tictactoe.enums.GameMode;
import com.game.java.tictactoe.enums.GameStage;
import com.game.java.tictactoe.utils.BloxGameUtils;
import com.game.java.tictactoe.utils.GameMovesAI;

@Controller
public class BloxGameController {
	private static final Logger log = LoggerFactory.getLogger(BloxGameController.class);

	/**
	 * Starts new Tic Tac Toe game ..initial conidtion
	 * 
	 * @param session 
	 * @param model Spring framework Model
	 * @return Spring framework View name
	 */
	@RequestMapping(value = "/tictactoe", method = RequestMethod.GET)
	public String game(
			HttpSession session,
			Model model) {
		
		GameState gameState = getStateFromSession(session);
		//No running game
		if(gameState == null) {
			log.info("gameState is null; starting new game");
			gameState = new GameState();
			putStateInSession(session, gameState);
		}
		model.addAttribute(Constants.GAME_STATE, gameState);
		
		return Constants.VIEW_GAME;
	}
	
	/**
	 * Resets the game to it's initial state
	 * 
	 * @param session 
	 * @param model Spring framework Model
	 * @return Spring framework View name
	 */
	@RequestMapping(value = "/tictactoe/reset", method = RequestMethod.GET)
	public String reset(
			HttpSession session,
			Model model) {
		
		log.info("Resetting new game.................");
		GameState gameState = new GameState();
		putStateInSession(session, gameState);
		model.addAttribute(Constants.GAME_STATE, gameState);
		
		return Constants.VIEW_GAME;
	}
	
	/**
	 * Starts a new game in the current mode.
	 * 
	 * @param session 
	 * @param model Spring framework Model
	 * @return Spring framework View name
	 */
	@RequestMapping(value = "/tictactoe/new", method = RequestMethod.GET)
	public String gameNew(
			HttpSession session,
			Model model) {
		
		log.info("Starting new game....................");
		GameState gameState = getStateFromSession(session);
		gameState.startNewGame();
		model.addAttribute(Constants.GAME_STATE, gameState);
		
		return Constants.VIEW_GAME;
	}
	
	/**
	 * Choose whether to play the game against the computer, or a human opponent.
	 * 
	 * @param session 
	 * @param mode String representing the desired mode: "ai" for play against the computer; "twoplayer" for multiplayer mode.
	 * @param model Spring framework Model
	 * @return Spring framework View name
	 */
	@RequestMapping(value = "/tictactoe/modeselection", method = RequestMethod.GET)
	public String modeSelected(
			HttpSession session,
			@RequestParam(value = "mode", required = true) String mode,
			Model model) {
		
		GameState gameState = getStateFromSession(session);
		if(mode.equals("ai")) {
			gameState.setGameMode(GameMode.AI_VS_HUMAN);
		}
		else if(mode.equals("twoplayer")) {
			gameState.setGameMode(GameMode.HUMAN_VS_HUMAN);
		}
		else {
			throw new RuntimeException("Invalid selected game mode:" + mode);
		}
		model.addAttribute(Constants.GAME_STATE, gameState);
		
		return "redirect:/tictactoe/new";
	}
	
	/**
	 * Places a marker for the current player in the requested position.
	 * 
	 * @param session 
	 * @param row Number of row to place marker
	 * @param col Number of column to place marker
	 * @param model Spring framework Model
	 * @return Spring framework View name
	 */
	@RequestMapping(value = "/tictactoe/move", method = RequestMethod.GET)
	public String playerMove(
			HttpSession session,
			@RequestParam(value = "row", required = true) Integer row, 
			@RequestParam(value = "col", required = true) Integer col, 
			Model model) {
		
		GameState gameState = getStateFromSession(session);
		model.addAttribute(Constants.GAME_STATE, gameState);
		log.info("move=(" + row + ", " + col + ")");
		
		// If not in the midst of a game, don't allow move.
		if(!gameState.getGameStage().equals(GameStage.IN_GAME)) {
			log.info("Game not in progress); ignoring move request.");
			return Constants.VIEW_GAME;
		}
		
		Board board = gameState.getBoard();
		try {
			board.move(row, col, gameState.getTurn());
			BloxGameUtils.evaluateBoard(gameState);
			
			// If game has not ended one way or another, and the game is 
			// against the computer, determine where it will move.
			if(gameState.getGameStage().equals(GameStage.IN_GAME) &&
					gameState.getGameMode().equals(GameMode.AI_VS_HUMAN)) {
				GameMovesAI.determineBestMove(gameState);
				BloxGameUtils.evaluateBoard(gameState);
			}
		}
		catch( Exception e)
		{
			// TODO: Add message to user.  As it is now, move request is
			// ignored, but letting them know would probably be better
			log.error("Cannot complete move", e);
		}
		
		return Constants.VIEW_GAME;
	}
	

	/**
	 * Convenience method to retrieve game state from session.
	 * 
	 * @param session
	 * @return Current game state.
	 */
	private GameState getStateFromSession(HttpSession session)
	{
		GameState gameState = (GameState)session.getAttribute(Constants.GAME_STATE);
		if(gameState == null) {
			log.info("New GameState created and put in session");
			gameState = new GameState();
			putStateInSession(session, gameState);
		}
		return gameState;
	}
	
	/**
	 * Convenience method to save game state in session.
	 * 
	 * @param session
	 */
	private void putStateInSession(HttpSession session, GameState gameState) {
		session.setAttribute(Constants.GAME_STATE, gameState);
	}
}
