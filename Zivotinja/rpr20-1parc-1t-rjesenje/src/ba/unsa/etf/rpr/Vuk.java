package ba.unsa.etf.rpr;

public class Vuk extends Pas {
    public Vuk(String id, String ime) throws NeispravanFormatIdaException {
        super(id, ime);
    }

    @Override
    public String glas() {
        return "auuu";
    }
}
