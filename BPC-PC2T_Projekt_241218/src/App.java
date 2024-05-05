import java.util.*;


public class App {
    
    static ArrayList<Kniha> serazeno;
    static TreeMap<String, Kniha> mojeDatabaze = new TreeMap<String, Kniha>();
    //static String nazev;
    //static int specifikace;
    static String zanr;

    public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
    public static int pouzeCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cislo ");
			sc.nextLine();
			cislo = pouzeCisla(sc);
		}
		return cislo;
	}
    public static int pozadovaneCislo(Scanner sc)
    {
        int min = 0;
        int max = 1;
        int cislo = 0;
        while (true)
        {
            try
            {
                cislo = sc.nextInt();
                if (cislo >= min && cislo <= max)
                {
                    return cislo;
                }   
                else
                {
                    System.out.println("Cislo neni v povolenem rozsahu, zadejte prosim cislo 0 nebo 1");
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Neplatný vstup. Zadejte prosim cislo 0 nebo 1");
                sc.next();
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String nazev;
        int specifikace;
        
        String nazevKnihy;
        Kniha najdiKnihu;
        int datumVydani;
        String[] autori;
        boolean run = true;
        int moznosti;
        while (run)
        {
            System.out.println("vyberte pozadovanou cinnost:");
            System.out.println("1 .. pridani nove knihy");//hotovo
            System.out.println("2 .. uprava knihy");//hotovo
            System.out.println("3 .. smazani knihy");//hotovo
            System.out.println("4 .. oznaceni knihy vypujcena/vracena");//hotovo
            System.out.println("5 .. vypis knih");//hotovo
            System.out.println("6 .. vyhledani knihy");//hotovo
            System.out.println("7 .. vypis vsech knih daneho autora");
            System.out.println("8 .. vypis knih daneho zanru");
            System.out.println("9 .. vypis vypujcenych knih");
            System.out.println("10 .. Ulozeni knihy do souboru");
            System.out.println("11 .. nacteni knihy ze souboru");
            System.out.println("12 .. ukonceni programu");
            moznosti = pouzeCelaCisla(sc);

            switch(moznosti)
            {
                case 1:
                    System.out.println("Vybrali jste pridani nove knihy\n");
                    System.out.println("Zadejte nazev knihy");
                    nazev = sc.next();
                    if (mojeDatabaze.containsKey(nazev))
                    {
                        System.out.println("Kniha s timto nazvem jiz v databazi existuje");
                    }
                    else
                    {
                        System.out.println("Zadejte rok vydani knihy");
                        datumVydani = pouzeCisla(sc);
                        System.out.println("Zadejte autora/y knihy oddelene ,");
                        autori = sc.next().split(", ");
                        System.out.println("Chceteli zadat Roman stistknete 0, pokud Ucebnici stiknete 1.");
                        specifikace = pozadovaneCislo(sc);

                        if (specifikace == 0)
                        {
                            System.out.println("Vybrali jste Roman");
                            System.out.println("Vyberte jeden z zanru romanu: Bukolicky, Rytirsky, Pikareskni, Historicky, Biografick");
                            Roman.ZanrRomanu zanr = null;
                            String zanry = sc.next();
                            System.out.println("Vybrali jste zanr: " + zanry);
                            try
                            {
                                zanr = Roman.ZanrRomanu.valueOf(zanry);
                            }
                            catch (IllegalArgumentException e)
                            {
                                System.out.println("Tento zanr neni ve vyberu. Prosim opakujte akci.");
                            }
                            mojeDatabaze.put(nazev, new Roman(nazev, autori, datumVydani, true, zanr));
                            break;
                        }
                        else
                        {
                            System.out.println("Vybrali jste Ucebnici");
                            System.out.println("Zadejte pro jaky rocnik je ucebnice vhodna: ");
                            int rocnik = sc.nextInt();
                            mojeDatabaze.put(nazev, new Ucebnice(nazev, autori, datumVydani, true, rocnik));
                            break;
                        }
                    }
                case 2:
                    System.out.println("Zadejte název knihy, kterou chcete upravit:");
                    nazevKnihy = sc.next();

                    if (mojeDatabaze.containsKey(nazevKnihy))
                    {
                        Kniha upravitKnihu = mojeDatabaze.get(nazevKnihy);
                        System.out.println("Zadejte nový rok vydání knihy (nebo ponechte prazdne pro zachovani puvodni hodnoty)");
                        sc.nextLine();
                        String noveDatumVydani = sc.nextLine();

                        if(!noveDatumVydani.isEmpty())
                        {
                            upravitKnihu.setDatumVydani(Integer.parseInt(noveDatumVydani));
                        }
                        System.out.println("Zadejte nové autora/y knihy (nebo ponechte prazdne pro zachovani puvodni hodnoty)");
                        String noviAutori = sc.nextLine();

                        if(!noviAutori.isEmpty())
                        {
                          String[]  poleAutoru = noviAutori.split(", ");
                            upravitKnihu.setAutori(poleAutoru);
                        }
                        System.out.println("Chcete změnit dostupnost knihy? ano/ne");
                        String anoNe = sc.nextLine();

                        if (anoNe.equalsIgnoreCase("ano")) 
                        {
                            upravitKnihu.setDostupnost(!upravitKnihu.getDostupnost());    
                        }
                        System.out.println("Informace o knize byly zmeneny");
                    }
                    else
                    {
                        System.out.println("Kniha s názvem " + nazevKnihy + " nebyla nalezena v databazi.");  
                    }
                    sc.nextLine();
                    break;
                case 3:
                    System.out.println("Zadejte nazev knihy kterou chcete vymazat z databaze:");
                    nazevKnihy = sc.next();

                    if (mojeDatabaze.containsKey(nazevKnihy))
                    {
                        mojeDatabaze.remove(nazevKnihy);
                        System.out.println("Kniha " + nazevKnihy + " byla smazana z databaze.");
                    }
                    else
                    {
                        System.out.println("Kniha nebyla nalezena v databazi.");
                    }
                    sc.nextLine();
                    break;
                case 4:
                    System.out.println("Napište název knihy u které chcete změnit dostupnost.");
                    nazevKnihy = sc.next();
                    if (mojeDatabaze.containsKey(nazevKnihy))
                    {
                        najdiKnihu = mojeDatabaze.get(nazevKnihy);
                        boolean novaDostupnost = !najdiKnihu.getDostupnost();
                        najdiKnihu.setDostupnost(novaDostupnost);
                        System.out.println("Dostupnost knihy " + nazevKnihy + " byla změněna na: " + novaDostupnost);
                        sc.nextLine();
                    }
                    else
                    {
                        System.out.println("Kniha nebyla nalezena v databazi.");
                    }
                    break;
                case 5:
                    System.out.println("Toto jsou všechny knihy v databázi: ");
                    System.out.println();

                    for (Map.Entry<String, Kniha> entry : mojeDatabaze.entrySet())
                    {
                        najdiKnihu = entry.getValue();
                        System.out.println("Nazev knihy: " + najdiKnihu.getNazev() + "\nAutori: " + najdiKnihu.getAutori() + "\nDatum vydani: " + najdiKnihu.getDatumVydani() + "\nJe kniha dostupná: " + najdiKnihu.getDostupnost()  + "\nSpecifikace: " + najdiKnihu.getSpecifikace());
                        
                        if (najdiKnihu instanceof Roman)
                        {
                            Roman roman = (Roman) najdiKnihu;
                            System.out.println("Zanr: " + roman.getZanrRomanu());
                        }
                        else if (najdiKnihu instanceof Ucebnice)
                        {
                            Ucebnice ucebnice = (Ucebnice) najdiKnihu;
                            System.out.println("Vhodná pro: " + ucebnice.getRocnik() + ". ročník");
                        }
                        System.out.println();
                        sc.nextLine();
                    }
                    break;
                case 6:
                    try{
                        System.out.println("Zadejte nazev knihy");
                        nazevKnihy = sc.next();
                        if(mojeDatabaze.containsKey(nazevKnihy))
                        {
                            najdiKnihu = mojeDatabaze.get(nazevKnihy);
                            System.out.println("Nazev knihy: " + najdiKnihu.getNazev() + "\nAutori: " + najdiKnihu.getAutori() + "\nDatum vydani: " + najdiKnihu.getDatumVydani() + "\nJe kniha dostupná: " + najdiKnihu.getDostupnost() + "\nSpecifikace: " + najdiKnihu.getSpecifikace());
                            
                            if (najdiKnihu instanceof Roman)
                            {
                                Roman roman = (Roman) najdiKnihu;
                                System.out.println("Zanr: " + roman.getZanrRomanu());
                            } 
                            else if (najdiKnihu instanceof Ucebnice)
                            {
                                Ucebnice ucebnice = (Ucebnice) najdiKnihu;
                                System.out.println("Vhodná pro: " + ucebnice.getRocnik() + ". ročník");
                            }
                            sc.nextLine();
                        }
                    }
                    catch(NullPointerException e)
                    {
                        System.out.println("Tato kniha neni v databazi");
                        sc.nextLine();
                    }
                    sc.nextLine();
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    System.out.println("Program byl ukoncen");
                    run = false;
                    break;
                default:
                    System.out.println("Zadali jste neplatnou moznost");
                    break;
            }
        }
    }
}