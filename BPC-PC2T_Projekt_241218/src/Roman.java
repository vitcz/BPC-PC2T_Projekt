
public class Roman extends Kniha
{

    public enum ZanrRomanu
    {
        Bukolicky,
		Rytirsky,
		Pikareskni,
		Historicky,
		Biograficky
    }

    public ZanrRomanu zanr;

    public Roman(String nazev, String[] autori, int datumVydani, boolean dostupnost, ZanrRomanu zanr)
    {
        super(nazev, autori, datumVydani, dostupnost, SpecifikaceKnihy.Roman);
        this.zanr = zanr;
    }

    public ZanrRomanu getZanrRomanu()
    {
        return zanr;
    }
}