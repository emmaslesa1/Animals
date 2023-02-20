package ba.unsa.etf.rpr;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ZooVrt {
    private ArrayList<Zivotinja> zivotinje;

    public ZooVrt() {
        zivotinje = new ArrayList<>();
    }

    int broj() {
        return zivotinje.size();
    }

    public String dajTabelu() {
        String rezultat = "";
        for (Zivotinja z : zivotinje) {
            String vrsta = z.getClass().getName().replace("ba.unsa.etf.rpr.", "");
            if (z instanceof VlastitaZivotinja)
                vrsta = ((VlastitaZivotinja) z).getVrsta();
            rezultat += z.getIme() + " (" + vrsta + ") : " + z.getId() + "\n";
        }
        return rezultat;
    }

    public void dodaj(Class vrsta, String ime, String id) throws NeispravanFormatIdaException, DvostrukiIdException {
        for(Zivotinja z : zivotinje)
            if (z.getId().equals(id))
                throw new DvostrukiIdException("Životinja sa IDom "+id+" već postoji u Zoo vrtu");
        switch(vrsta.getName()) {
            case "ba.unsa.etf.rpr.DomacaMacka":
                zivotinje.add(new DomacaMacka(id, ime));
                break;
            case "ba.unsa.etf.rpr.Tigar":
                zivotinje.add(new Tigar(id, ime));
                break;
            case "ba.unsa.etf.rpr.Lav":
                zivotinje.add(new Lav(id, ime));
                break;
            case "ba.unsa.etf.rpr.DomaciPas":
                zivotinje.add(new DomaciPas(id, ime));
                break;
            case "ba.unsa.etf.rpr.Vuk":
                zivotinje.add(new Vuk(id, ime));
                break;
        }
    }

    public void dodaj(Zivotinja z) throws DvostrukiIdException {
        for(Zivotinja z2 : zivotinje)
            if (z2.getId().equals(z.getId()))
                throw new DvostrukiIdException("Životinja sa IDom "+z.getId()+" već postoji u Zoo vrtu");
        zivotinje.add(z);
    }

    public void dodaj(Class vrsta, String ime) throws NeispravanFormatIdaException, DvostrukiIdException {
        int maxBroj = 1;
        for(Zivotinja z : zivotinje)
            if (z.getNumericId() > maxBroj) maxBroj = z.getNumericId();
        String id = Zivotinja.imeToId(ime) + "-" + Integer.toString(maxBroj+1);
        dodaj(vrsta, ime, id);
    }

    public void dodaj(String vrsta, String ime, String id, Supplier<String> func) throws NeispravanFormatIdaException, DvostrukiIdException {
        for(Zivotinja z : zivotinje)
            if (z.getId().equals(id))
                throw new DvostrukiIdException("Životinja sa IDom "+id+" već postoji u Zoo vrtu");
        zivotinje.add(new VlastitaZivotinja(id, ime, vrsta, func));
    }

    public void obrisi(String id) {
        zivotinje = zivotinje.stream().filter(z -> !z.getId().equals(id)).collect(Collectors.toCollection(ArrayList::new));
    }

    /* Varijanta koja nije Stream
    public void obrisi(String id) {
        for(Zivotinja z : zivotinje)
            if (z.getId().equals(id))
                zivotinje.remove(z);
    }
     */

    public Set<Zivotinja> koToTamoGovori(String glas) {
        return Arrays.stream(glas.split(",")).map((String g) ->
                zivotinje.stream().sorted().filter((Zivotinja z) -> z.glas().equals(g)).findFirst().orElseThrow(
                        () -> new IllegalArgumentException("Ne postoji životinja sa glasom "+ g))
        ).collect(Collectors.toSet());
    }

    /* Varijanta koja nije Stream
    public Set<Zivotinja> koToTamoGovori(String glas) {
        ArrayList<Zivotinja> zivotinjeSorted = new ArrayList<>(zivotinje);
        Collections.sort(zivotinjeSorted);
        Set<Zivotinja> rezultat = new TreeSet<>();
        for (String g : glas.split(",")) {
            boolean found = false;
            for (Zivotinja z : zivotinjeSorted)
                if (z.glas().equals(g)) {
                    rezultat.add(z);
                    found = true;
                    break;
                }
            if (!found) throw new IllegalArgumentException("Ne postoji životinja sa glasom "+ g);
        }
        return rezultat;
    }
     */
}
