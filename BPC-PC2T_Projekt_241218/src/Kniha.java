
public class Kniha
{

	public String nazev;
	public String[] autori;
	public int datumVydani;
	public boolean dostupnost;
	public SpecifikaceKnihy specifikace;
	public String autor;
	public enum SpecifikaceKnihy
	{
		Roman,
		Ucebnice
	}

	public Kniha(String nazev, String[] autori, int datumVydani, boolean dostupnost, SpecifikaceKnihy specifikace)
	{
		this.nazev = nazev;
		this.autori = autori;
		this.datumVydani = datumVydani;
		this.dostupnost = false;
		this.specifikace = specifikace;
	}
	
	public String getNazev()
	{
		return nazev;
	}
	
	public String getAutori()
	{
		autor = String.join(", ", this.autori);
		return autor;
	}
	
	public String setAutori(String[] poleAutoru)
	{
		this.autori = poleAutoru;
		autor = String.join(", ", this.autori);
        return autor;
	}
	
	public int getDatumVydani()
	{
		return datumVydani;
    }

	public int setDatumVydani(int noveDatumVydani)
	{
		this.datumVydani = noveDatumVydani;
		return datumVydani;	
	}

	public  boolean getDostupnost()
	{
		return dostupnost;
    }

	public boolean setDostupnost(boolean dostupnost)
	{
		this.dostupnost = dostupnost;
        return true;
	}

	public  SpecifikaceKnihy getSpecifikace()
	{
		return specifikace;
    }	
}