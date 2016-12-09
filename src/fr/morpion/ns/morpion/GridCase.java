package fr.morpion.ns.morpion;

public final class GridCase {

	private final int id;
	private TokenMorpion token;
	
	public GridCase(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean hasToken(){
		return token != null;
	}
	
	public TokenMorpion getToken() {
		return token;
	}
	
	public void setToken(TokenMorpion token) {
		if(this.token != null) throw new UnsupportedOperationException("La case a déjà un token.");
		this.token = token;
	}
}
