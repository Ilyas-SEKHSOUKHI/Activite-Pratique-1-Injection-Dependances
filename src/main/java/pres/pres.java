package pres;
import dao.daoImpl;
import metier.metierImpl;

public class pres {
    public void main(String args[]){
        System.out.println(" ******** Activite-Pratique-1-Injection-Dependances ******** ");
        daoImpl a = new daoImpl();

        // using cunstructeur parametrer
        // metierImpl b = new metierImpl(a);

        // using setter
        metierImpl b = new metierImpl();
        b.setDao(a);

        System.out.println(b.calcul());
    }
}
