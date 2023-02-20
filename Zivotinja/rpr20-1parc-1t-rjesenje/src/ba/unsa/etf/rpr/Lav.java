package ba.unsa.etf.rpr;

public class Lav extends Macka {
    public Lav(String id, String ime) throws NeispravanFormatIdaException {
        super(id, ime);
    }

    @Override
    public String glas() {
        return "roar";
    }
}
