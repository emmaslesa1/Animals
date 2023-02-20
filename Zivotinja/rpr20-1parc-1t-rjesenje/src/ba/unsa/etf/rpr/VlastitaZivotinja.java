package ba.unsa.etf.rpr;

import java.util.function.Supplier;

public class VlastitaZivotinja extends Zivotinja {
    private String vrsta;
    private Supplier<String> glasFunc;
    public VlastitaZivotinja(String id, String ime, String vrsta, Supplier<String> glasFunc) throws NeispravanFormatIdaException {
        super(id, ime);
        this.vrsta = vrsta;
        this.glasFunc = glasFunc;
    }

    public String getVrsta() { return vrsta; }

    @Override
    public String glas() {
        return glasFunc.get();
    }
}
