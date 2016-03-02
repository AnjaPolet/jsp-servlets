package be.vdab.dump;

import java.math.BigDecimal;

public class Pizza {
	private long id;
	private String naam;
	private BigDecimal prijs;
	private boolean pikant;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	
	public static boolean isNaamValid(String naam){
		//deze static functie valideert de naam zonder een Pizza instance te moeten maken
		return naam !=null && !naam.isEmpty();
	}
	public void setNaam(String naam) {
		if(!isNaamValid(naam)){
			throw new IllegalArgumentException();
		}
		this.naam = naam;
	}
	public BigDecimal getPrijs() {
		return prijs;
	}
	
	public static boolean isPrijsValid (BigDecimal prijs){
		return prijs != null && prijs.compareTo(BigDecimal.ZERO)>=0;
	}
	public void setPrijs(BigDecimal prijs) {
		if(! isPrijsValid(prijs)){
			throw new IllegalArgumentException();
		}
		this.prijs = prijs;
	}
	public boolean isPikant() {
		return pikant;
	}
	public void setPikant(boolean pikant) {
		this.pikant = pikant;
	}
	public Pizza(String naam, BigDecimal prijs, boolean pikant) {
		
		this.naam = naam;
		this.prijs = prijs;
		this.pikant = pikant;
	}
	public Pizza(long id, String naam, BigDecimal prijs, boolean pikant) {
		this(naam,prijs,pikant);
		this.id = id;
		
	}
	
		
	
	

}
