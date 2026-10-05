package metier;
import dao.idao;

public class metierImpl implements imetier {
    private idao dao;
    public metierImpl(idao dao){
        this.dao = dao;
    }
    public void setDao(idao dao){
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double a = dao.getData();
        double resultat = a + 5;
        return resultat;
    }
}
