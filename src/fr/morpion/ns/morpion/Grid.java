package fr.morpion.ns.morpion;

public final class Grid {

	private final GridCase[] gridCases;
	private WinType win;
	
	public Grid() {
		gridCases = new GridCase[9];
		for(int i = 0; i < gridCases.length; i++) gridCases[i] = new GridCase(i);
	}
	
	public boolean hasToken(int id){
		return gridCases[id].hasToken();
	}
	
	public TokenMorpion getToken(int id){
		return gridCases[id].getToken();
	}
	
	public WinType getWinType() {
		return win;
	}
	
	public void setToken(int id, TokenMorpion token){
		gridCases[id].setToken(token);
	}
	
	public boolean hasWin(TokenMorpion token){
		
		if(hasLineWin(0, 4, 8, token) || hasLineWin(2, 4, 6, token)){
			win = WinType.PLAYER;
			return true;
		}
		
		for(int i = 0; i < 3; i++){
			if(hasLineWin(i, i+3, i+6, token) || hasLineWin(i*3, (i*3)+1, (i*3)+2, token)){
				win = WinType.PLAYER;
				return true;
			}
		}
		
		boolean gameNull = true;
		for(int i = 0; i < gridCases.length; i++){
			if(gridCases[i].getToken() == null){
				gameNull = false;
				continue;
			}
		}
		if(gameNull) win = WinType.NULL;
		return gameNull;
	}
	
	public enum WinType{
		PLAYER, NULL, FORFAIT;
	}
	
	private boolean hasLineWin(int a, int b, int c, TokenMorpion token){
		if(gridCases[a].getToken() == null || gridCases[b].getToken() == null || gridCases[c].getToken() == null) return false;
		if(gridCases[a].getToken().equals(token) && gridCases[b].getToken().equals(token) && gridCases[c].getToken().equals(token)) return true;
		return false;
	}
}
