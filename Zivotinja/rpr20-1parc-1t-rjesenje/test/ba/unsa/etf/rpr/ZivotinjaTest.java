package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZivotinjaTest {
    @Test
    public void testPas() {
        try {
            DomaciPas fifi = new DomaciPas("fifi-987", "Fifi");
            assertEquals("Fifi", fifi.getIme());
            assertEquals("fifi-987", fifi.getId());
        } catch (NeispravanFormatIdaException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testMacka() {
        assertThrows(IllegalArgumentException.class, () -> new DomacaMacka("  \n \t ", "Garfild"));
        assertThrows(IllegalArgumentException.class, () -> new DomacaMacka("garfild-1", ""));

        try {
            DomacaMacka garfild = new DomacaMacka("garfild-1", "Garfild");
            assertEquals("Garfild", garfild.getIme());
            assertEquals("garfild-1", garfild.getId());
        } catch (NeispravanFormatIdaException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testVuk() {
        assertThrows(NeispravanFormatIdaException.class, () -> new Vuk("Vucko-84", "Vučko"));
        assertThrows(NeispravanFormatIdaException.class, () -> new Vuk("vučko-84", "Vučko"));
        assertThrows(NeispravanFormatIdaException.class, () -> new Vuk("vucko 84", "Vučko"));
        assertThrows(NeispravanFormatIdaException.class, () -> new Vuk("vuckooo-84", "Vučko"));
        assertThrows(NeispravanFormatIdaException.class, () -> new Vuk("vucko-", "Vučko"));
        assertThrows(NeispravanFormatIdaException.class, () -> new Vuk("vucko-84a", "Vučko"));
        assertThrows(NeispravanFormatIdaException.class, () -> new Vuk("vucko-84.1", "Vučko"));
        try {
            Vuk vucko = new Vuk("vucko-84", "Vučko");
            assertEquals("Vučko", vucko.getIme());
            assertEquals("vucko-84", vucko.getId());
        } catch (NeispravanFormatIdaException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testTigar() {
        try {
            Tigar tigar = new Tigar("srebrnitigar-66", "Srebrni Tigar");
            assertThrows(NeispravanFormatIdaException.class, () -> tigar.setId("SrebrniTigar-77"));
            assertThrows(NeispravanFormatIdaException.class, () -> tigar.setId("srebrni tigar-77"));
            assertThrows(NeispravanFormatIdaException.class, () -> tigar.setId("srebrnitigarrr-77"));
            assertEquals("srebrnitigar-66", tigar.getId());
            assertThrows(NeispravanFormatIdaException.class, () -> tigar.setId("srebrnitigar- 77"));
            assertThrows(NeispravanFormatIdaException.class, () -> tigar.setId("srebrnitigar-"));
            assertThrows(NeispravanFormatIdaException.class, () -> tigar.setId("srebrnitigar-77.77"));
            assertEquals("srebrnitigar-66", tigar.getId());
            assertThrows(NeispravanFormatIdaException.class, () -> tigar.setId(" srebrnitigar-77"));
            assertThrows(NeispravanFormatIdaException.class, () -> tigar.setId("srebrnitigar-77 "));
            assertEquals("srebrnitigar-66", tigar.getId());
            tigar.setId("srebrnitigar-77");
            assertEquals("srebrnitigar-77", tigar.getId());
            assertEquals("Srebrni Tigar", tigar.getIme());
        } catch (NeispravanFormatIdaException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testLav() {
        try {
            Lav mufasa = new Lav("mufasa-321", "Mufasa");
            mufasa.setIme("Mufa Sa");
            assertEquals("Mufa Sa", mufasa.getIme());
            mufasa.setIme("Mufaša");
            assertEquals("Mufaša", mufasa.getIme());
            mufasa.setIme("Muf4asa");
            assertEquals("Muf4asa", mufasa.getIme());
            mufasa.setIme("m-u!fa~s/a");
            assertEquals("m-u!fa~s/a", mufasa.getIme());
            assertThrows(NeispravanFormatIdaException.class, () -> mufasa.setIme("Rafiki"));
            assertEquals("m-u!fa~s/a", mufasa.getIme());
            assertThrows(NeispravanFormatIdaException.class, () -> mufasa.setId("Muf4sa"));
            assertEquals("m-u!fa~s/a", mufasa.getIme());
            mufasa.setIme("MUFASA");
            assertEquals("MUFASA", mufasa.getIme());
            assertEquals("mufasa-321", mufasa.getId());
        } catch (NeispravanFormatIdaException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGlasovi() {
        try {
            DomaciPas svrco = new DomaciPas("svrcocukic-987", "Švrćo Cukić");
            assertEquals("av", svrco.glas());
            DomacaMacka felix = new DomacaMacka("felix-1", "Felix");
            assertEquals("mjau", felix.glas());
            Vuk vuk = new Vuk("bijeliocnjak-84", "Bijeli Očnjak");
            assertEquals("auuu", vuk.glas());
            Lav aslan = new Lav("aslan-321", "Aslan");
            assertEquals("roar", aslan.glas());
            Tigar tigger = new Tigar("tigger-321", "Tigger");
            assertEquals("rrrr", tigger.glas());
        } catch (NeispravanFormatIdaException e) {
            e.printStackTrace();
            fail();
        }
    }
}