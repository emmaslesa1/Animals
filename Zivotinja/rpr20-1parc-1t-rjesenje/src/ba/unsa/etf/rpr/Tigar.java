package ba.unsa.etf.rpr;

public class Tigar extends Macka {
    public Tigar(String id, String ime) throws NeispravanFormatIdaException {
        super(id, ime);
    }

    @Override
    public String glas() {
        return "rrrr";
    }
}
