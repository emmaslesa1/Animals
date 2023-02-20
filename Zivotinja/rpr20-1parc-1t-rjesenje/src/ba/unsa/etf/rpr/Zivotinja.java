package ba.unsa.etf.rpr;

import java.util.regex.Pattern;

public abstract class Zivotinja implements Comparable<Zivotinja> {
    String id, ime;

    public Zivotinja(String id, String ime) throws NeispravanFormatIdaException {
        if (id.trim().isEmpty()) throw new IllegalArgumentException("Id ne može biti prazan");
        if (ime.trim().isEmpty()) throw new IllegalArgumentException("Ime ne može biti prazno");
        provjeriId(id, ime);
        this.id = id;
        this.ime = ime;
    }

    public static String imeToId(String ime) {
        char[] slova = "čćšđžČĆŠĐŽ".toCharArray();
        char[] zamjene = "ccsdzCCSDZ".toCharArray();
        for (int i=0; i<slova.length; i++)
            ime = ime.replace(slova[i], zamjene[i]);
        ime = ime.toLowerCase().replaceAll("[\\W\\d]", "");
        return ime;
    }

    private static void provjeriId(String id, String ime) throws NeispravanFormatIdaException {
        String imePart = imeToId(ime);
        if (!id.substring(0, imePart.length()).equals(imePart))
            throw new NeispravanFormatIdaException("ID nije oblika ime-redni broj ime");
        if (id.charAt(imePart.length()) != '-')
            throw new NeispravanFormatIdaException("ID nije oblika ime-redni broj crtica");
        try {
            Integer.parseInt(id.substring(imePart.length()));
        } catch (NumberFormatException e) {
            throw new NeispravanFormatIdaException("ID nije oblika ime-redni broj broj");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws NeispravanFormatIdaException {
        if (id.trim().isEmpty()) throw new IllegalArgumentException("Id ne može biti prazan");
        provjeriId(id, this.ime);
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) throws NeispravanFormatIdaException {
        if (ime.trim().isEmpty()) throw new IllegalArgumentException("Ime ne može biti prazno");
        provjeriId(this.id, ime);
        this.ime = ime;
    }

    public int getNumericId() {
        String[] parts = id.split("-");
        return Integer.parseInt(parts[1]);
    }

    @Override
    public int compareTo(Zivotinja z) {
        int nid = getNumericId();
        int znid = z.getNumericId();
        if (nid > znid) return 1;
        if (nid < znid) return -1;
        return 0;
    }

    public abstract String glas();
}
