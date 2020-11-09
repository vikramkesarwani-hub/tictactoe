package com.game.java.tictactoe.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.java.tictactoe.enums.GameMode;
import com.game.java.tictactoe.enums.GameStage;
import com.game.java.tictactoe.enums.Marker;

public class GameState {
	
	private String xPlayerName;
	private String oPlayerName;
	private String gameMessage;
	private String turnMessage;
	private Marker turn;
	private GameMode gameMode;
	private GameStage gameStage;
	private Board board;

	private static final Logger log = LoggerFactory.getLogger(GameState.class);
	
	//initial setup
	public GameState()
	{
		board = new Board();
		reset();
	}
	
	//function to reset complete game
	public void reset()
	{
		log.info("inside reset............");
		setxPlayerName("X Player");
		setoPlayerName("O Player");
		setGameMessage("");
		setTurn(Marker.X);
		setTurnMessage("Turn: X");
		setGameMode(GameMode.AI_VS_HUMAN);
		setGameStage(GameStage.MODE_SELECTION);
		board.clear();
	}
	
	public void startNewGame()
	{
		board.clear();
		setGameMessage("");
		setTurnMessage("Turn: X");
		setTurn(Marker.X);
		setGameStage(GameStage.IN_GAME);
	}
	
	public String getxPlayerName() {
		return xPlayerName;
	}

	public void setxPlayerName(String xPlayerName) {
		this.xPlayerName = xPlayerName;
	}

	public String getoPlayerName() {
		return oPlayerName;
	}

	public void setoPlayerName(String yPlayerName) {
		this.oPlayerName = yPlayerName;
	}

	public String getGameMessage() {
		return gameMessage;
	}

	public void setGameMessage(String playMessage) {
		this.gameMessage = playMessage;
	}

	public String getTurnMessage() {
		return turnMessage;
	}

	public void setTurnMessage(String turnMessage) {
		this.turnMessage = turnMessage;
	}

	public Marker getTurn() {
		return turn;
	}

	public void setTurn(Marker turn) {
		this.turn = turn;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}
	
	public GameStage getGameStage() {
		return gameStage;
	}
	
	public void setGameStage(GameStage gameStage) {
		this.gameStage = gameStage;
	}

	public Board getBoard() {
		return board;
	}
	
	@Override
	public String toString() {
		return "GameState [xPlayerName=" + xPlayerName + ", oPlayerName=" + oPlayerName + ", gameMessage=" + gameMessage
				+ ", turnMessage=" + turnMessage + ", turn=" + turn + ", gameMode=" + gameMode + ", gameStage="
				+ gameStage + ", board=" + board + "]";
	}
		

}
