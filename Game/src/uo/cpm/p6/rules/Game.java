package uo.cpm.p6.rules;

import uo.cpm.p6.model.Board;
import uo.cpm.p6.model.Dice;
import uo.cpm.p6.model.Invader;
import uo.cpm.p6.model.Meteorite;
import uo.cpm.p6.model.Space;
import uo.cpm.p6.service.SpaceInvaders;

public class Game {
	
	public static final Integer MAX_SHOTS = 4;
	int score;
	int shots;
	private Board board; 
	private boolean invaderFound;
	private boolean meteoriteFound;
	private int result;
	
	
	//declaring levels
	public enum Level {
		EASY, INTERMEDIATE, HARD;
	}
	public Board getBoard() {
		return board;
	}

	public Game(){
		initialize();
	}
	
	public void initialize(){
		
		board = new Board();// pass level here or add method to board set level and then add level
		score = 800;
		shots = 0;
		invaderFound=false;
		meteoriteFound=false;
		
	}

	public void shoot(int i){
		shots --;
		
		if (board.getCells()[i] instanceof Invader) {
			((Invader)board.getCells()[i]).setErased(true);
			invaderFound = true;
			
		}
		if (board.getCells()[i] instanceof Meteorite) {
			((Meteorite)board.getCells()[i]).setErased(true);
			meteoriteFound= true;
			score = 0;
		}
		score = score + board.getCells()[i].discover();
	}
	

	public void setResult(int result) {
		this.result=result;
	}
	
	public int getResult() {
		return result;
	}
	public boolean isGameOver() {
		if(invaderFound) {
			setResult(0);
		}else if(meteoriteFound) {
			setResult(1);
		}else if(shots==0){
			setResult(2);
		}
		return (invaderFound || shots == 0 ||meteoriteFound);
	}

	public int getScore() {
		return score;
	}

	public void launch() {
		setShots(Dice.launch());	
	}
	
	public int getShots() {
		return shots;
	}

	private void setShots(int shots) {
		this.shots = shots;
	}
}
