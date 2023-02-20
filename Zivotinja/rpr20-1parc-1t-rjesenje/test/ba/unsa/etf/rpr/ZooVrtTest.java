package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ZooVrtTest {
    @Test
    void constructorTest() {
        ZooVrt pionirska = new ZooVrt();
        assertEquals(0, pionirska.broj());
    }

    @Test
    void dodaj1Test() {
        ZooVrt pionirska = new ZooVrt();
        try {
            pionirska.dodaj(Tigar.class, "Širkan", "sirkan-123");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(1, pionirska.broj());
        assertEquals("Širkan (Tigar) : sirkan-123", pionirska.dajTabelu().trim());
    }

    @Test
    void dodaj1IzuzetakTest() {
        ZooVrt pionirska = new ZooVrt();
        assertThrows(NeispravanFormatIdaException.class, () ->
                pionirska.dodaj(DomaciPas.class, "Snoopy", "snoopi-123")
        );
    }

    @Test
    void dodaj1DvostrukiIzuzetakTest() {
        ZooVrt pionirska = new ZooVrt();
        try {
            pionirska.dodaj(DomaciPas.class, "Fido", "fido-1001");
            pionirska.dodaj(Lav.class, "Simba", "simba-1002");
            pionirska.dodaj(DomacaMacka.class, "Tom", "tom-1003");
            pionirska.dodaj(DomaciPas.class, "Švrćo Cukić", "svrcocukic-1004");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertThrows(DvostrukiIdException.class, () -> pionirska.dodaj(DomacaMacka.class, "TOM", "tom-1003"));
        assertEquals(4, pionirska.broj());
        assertEquals("Fido (DomaciPas) : fido-1001\n" +
                "Simba (Lav) : simba-1002\n" +
                "Tom (DomacaMacka) : tom-1003\n" +
                "Švrćo Cukić (DomaciPas) : svrcocukic-1004", pionirska.dajTabelu().trim());
    }

    @Test
    void dodaj2Test() {
        ZooVrt pionirska = new ZooVrt();
        try {
            pionirska.dodaj(new DomaciPas("silja-700", "Šilja"));
            assertEquals(1, pionirska.broj());
            assertEquals("Šilja (DomaciPas) : silja-700", pionirska.dajTabelu().trim());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void dodaj2IzuzetakTest() {
        ZooVrt pionirska = new ZooVrt();
        try {
            pionirska.dodaj(new Lav("leo-700", "Leo"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertThrows(DvostrukiIdException.class, () -> pionirska.dodaj(new Lav("leo-700", "LEO")));
        assertEquals(1, pionirska.broj());
    }

    @Test
    void dodaj3Test() {
        ZooVrt pionirska = new ZooVrt();
        try {
            pionirska.dodaj(DomaciPas.class, "Fido", "fido-1001");
            pionirska.dodaj(Lav.class, "Simba");
            pionirska.dodaj(DomacaMacka.class, "Tom");
            pionirska.dodaj(DomaciPas.class, "Švrćo Cukić");
            pionirska.dodaj(Lav.class, "Leo");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(5, pionirska.broj());
        assertEquals("Fido (DomaciPas) : fido-1001\n" +
                "Simba (Lav) : simba-1002\n" +
                "Tom (DomacaMacka) : tom-1003\n" +
                "Švrćo Cukić (DomaciPas) : svrcocukic-1004\n" +
                "Leo (Lav) : leo-1005", pionirska.dajTabelu().trim());
    }

    @Test
    void dodaj4Test() {
        ZooVrt pionirska = new ZooVrt();
        try {
            pionirska.dodaj("Pokemon", "Pikachu", "pikachu-222", () -> "pikapika");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(1, pionirska.broj());
        assertEquals("Pikachu (Pokemon) : pikachu-222", pionirska.dajTabelu().trim());
    }

    @Test
    void obrisiTest() {
        ZooVrt pionirska = new ZooVrt();
        try {
            pionirska.dodaj(DomaciPas.class, "Fido", "fido-1001");
            pionirska.dodaj(Lav.class, "Simba");
            pionirska.dodaj(DomacaMacka.class, "Tom");
            pionirska.dodaj(DomaciPas.class, "Švrćo Cukić");
            pionirska.dodaj(Lav.class, "Leo");
            pionirska.obrisi("simba-1002");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(4, pionirska.broj());
        assertEquals("Fido (DomaciPas) : fido-1001\n" +
                "Tom (DomacaMacka) : tom-1003\n" +
                "Švrćo Cukić (DomaciPas) : svrcocukic-1004\n" +
                "Leo (Lav) : leo-1005", pionirska.dajTabelu().trim());
    }

    @Test
    void koToTamoGovoriTest() {
        ZooVrt pionirska = new ZooVrt();
        try {
            pionirska.dodaj(DomaciPas.class, "Fido", "fido-1001");
            pionirska.dodaj(Lav.class, "Simba");
            pionirska.dodaj(DomacaMacka.class, "Tom");
            pionirska.dodaj(DomaciPas.class, "Švrćo Cukić");
            pionirska.dodaj(Lav.class, "Leo");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        Set<Zivotinja> rezultat = pionirska.koToTamoGovori("mjau,av,roar");
        assertEquals(3, rezultat.size());
        boolean ok1=false, ok2=false, ok3=false;
        for(Zivotinja z : rezultat) {
            if (z.getId().equals("tom-1003")) ok1 = true;
            if (z.getId().equals("fido-1001")) ok2 = true;
            if (z.getId().equals("simba-1002")) ok3 = true;
        }
        assertTrue(ok1 && ok2 && ok3);
    }

    @Test
    void koToTamoGovoriIzuzetakTest() {
        ZooVrt pionirska = new ZooVrt();
        try {
            pionirska.dodaj(DomaciPas.class, "Fido", "fido-1001");
            pionirska.dodaj(Lav.class, "Simba");
            pionirska.dodaj(DomacaMacka.class, "Tom");
            pionirska.dodaj(DomaciPas.class, "Švrćo Cukić");
            pionirska.dodaj(Lav.class, "Leo");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertThrows(IllegalArgumentException.class, () -> pionirska.koToTamoGovori("mjau,av,roar,auuu"));
        assertThrows(IllegalArgumentException.class, () -> pionirska.koToTamoGovori("mjau,avav,roar"));
        assertThrows(IllegalArgumentException.class, () -> pionirska.koToTamoGovori("mjau,av,blabla,roar"));

        // U Setu nema ponavljanja, ali to nije izuzetak
        Set<Zivotinja> rezultat = pionirska.koToTamoGovori("mjau,mjau,mjau,mjau");
        assertEquals(1, rezultat.size());
        DomacaMacka tom = (DomacaMacka) rezultat.toArray()[0];
        assertEquals("tom-1003", tom.getId());
    }

    @Test
    void koToTamoGovoriKlasaTest() {
        ZooVrt pionirska = new ZooVrt();
        Tigar tigger = null;
        Vuk vucko = null;
        Zivotinja pikachu = null;
        try {
            tigger = new Tigar("tigger-15", "Tigger");
            pionirska.dodaj(tigger);
            vucko = new Vuk("vucko-531", "Vučko");
            pionirska.dodaj(vucko);
            pikachu = new Zivotinja("pikachu-123", "Pikachu") {
                @Override
                public String glas() {
                    return ime.substring(0,4).toLowerCase() + ime.substring(0,4).toLowerCase();
                }
            };
            pionirska.dodaj(pikachu);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        Set<Zivotinja> rezultat = pionirska.koToTamoGovori("rrrr,pikapika");
        assertEquals(2, rezultat.size());
        assertTrue(rezultat.contains(tigger));
        assertTrue(rezultat.contains(pikachu));
        assertFalse(rezultat.contains(vucko));

        assertThrows(IllegalArgumentException.class, () -> pionirska.koToTamoGovori("mjau,av,roar"));
        assertThrows(IllegalArgumentException.class, () -> pionirska.koToTamoGovori("mjau"));
        assertThrows(IllegalArgumentException.class, () -> pionirska.koToTamoGovori("rrrr,roar"));

        rezultat = pionirska.koToTamoGovori("pikapika");
        assertEquals(1, rezultat.size());
        assertTrue(rezultat.contains(pikachu));
    }
}