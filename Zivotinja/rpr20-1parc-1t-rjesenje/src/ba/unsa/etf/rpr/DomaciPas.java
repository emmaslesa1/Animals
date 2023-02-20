package ba.unsa.etf.rpr;

public class DomaciPas extends Pas {
    public DomaciPas(String id, String ime) throws NeispravanFormatIdaException {
        super(id, ime);
    }

    @Override
    public String glas() {
        return "av";
    }
}
