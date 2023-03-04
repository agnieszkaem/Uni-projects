package uo.cpm.p6.model;

public class Board {
	
	/*
	 * 1) The easy level (identified as level 1) will correspond to a board consisting of 10 squares, 2
	invaders, no meteorite and a maximum of 5 shots.
	2) The intermediate level (identified as level 2) will correspond to a board made up of 8 squares, 1
	invader, a meteorite and a maximum of 4 shots.
	3) The difficult level (identified as level 3) will correspond to a board made up of 6 squares, 1
	invader, 2 meteorites and a maximum of 3 shots.
	 * 
	 */
	
	private int level;

	private void setLevel(int level ) {
		this.level=level;
	}
	
	public static final int DIM = 8;
	Cell[] cells;
	
	public Board() {
		cells = new Cell[DIM];
		for (int i=0;i<DIM;i++)
			cells[i] = new Space(i);

		int invaderPosition = (int) (Math.random() * DIM);
		int meteoritePosition = (int) (Math.random() * DIM);
		while(invaderPosition==meteoritePosition) {
			meteoritePosition = (int) (Math.random() * DIM);
		}
			
		cells[invaderPosition] = new Invader();
		cells[meteoritePosition] = new Meteorite();
	}
	
	public void discoverBoard() {
		int length =cells.length;
		for(int i =0; i<length;i++) {
			cells[i].discover();
		}
	}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

	public String getPicture(Integer position)
	{
		return this.cells[position].getPicture();
	}
}
