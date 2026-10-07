package ma.ilyas.pres;
import ma.ilyas.dao.idao;
import ma.ilyas.metier.imetier;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class pres2 {
    // FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
    public void main(String args[]) throws Exception {
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();
        Class cDao = Class.forName(daoClassName);
        idao d =(idao) cDao.newInstance();

        String metierClassName = scanner.nextLine();
        Class cMetier = Class.forName(metierClassName);
        //imetier metier =(imetier) cMetier.getConstructor(idao.class).newInstance(d);
        imetier metier = (imetier) cMetier.getConstructor().newInstance();
        Method setDao = cMetier.getDeclaredMethod("setDao", idao.class);
        setDao.invoke(metier,d);

        System.out.println("Resultat => "+metier.calcul());

    }
}
