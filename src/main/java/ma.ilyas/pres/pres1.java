package ma.ilyas.pres;
import ma.ilyas.dao.daoImpl;
import ma.ilyas.ext.daoImplV2;
import ma.ilyas.metier.metierImpl;

public class pres1 {
    public void main(String args[]){
        System.out.println(" ******** Activite-Pratique-1-Injection-Dependances ******** ");
        daoImplV2 a = new daoImplV2();

        // using cunstructeur parametrer
        // metierImpl b = new metierImpl(a);

        // using setter
        metierImpl b = new metierImpl();
        b.setDao(a);

        System.out.println(b.calcul());
    }
}
