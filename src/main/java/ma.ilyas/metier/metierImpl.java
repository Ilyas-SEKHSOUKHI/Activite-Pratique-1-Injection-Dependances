package ma.ilyas.metier;
import ma.ilyas.dao.idao;

public class metierImpl implements imetier {
    private idao dao;
    public metierImpl(){ // constructeur par default
        this.dao = null;
    }
    public metierImpl(idao dao){ // constructeur paranetrer
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
