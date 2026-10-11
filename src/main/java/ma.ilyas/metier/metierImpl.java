package ma.ilyas.metier;
import ma.ilyas.dao.idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("metier")
public class metierImpl implements imetier {
    @Autowired
    @Qualifier("d2")
    private idao dao;
    public metierImpl(){}
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
