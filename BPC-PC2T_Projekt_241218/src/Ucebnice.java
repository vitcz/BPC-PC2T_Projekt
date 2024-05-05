
public class Ucebnice extends Kniha
{

    public int rocnik;

    public Ucebnice(String nazev, String[] autori, int datumVydani, boolean dostupnost, int rocnik)
    {
        super(nazev, autori, datumVydani, dostupnost, SpecifikaceKnihy.Ucebnice);
        this.rocnik = rocnik;

    }

    public int getRocnik()
    {
        return rocnik;
    }
}