package ba.unsa.etf.rpr;

public class DomacaMacka extends Macka {
    public DomacaMacka(String id, String ime) throws NeispravanFormatIdaException {
        super(id, ime);
    }

    @Override
    public String glas() {
        return "mjau";
    }
}
