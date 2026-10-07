package ma.ilyas.pres;
import ma.ilyas.dao.idao;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class pres2 {
    public void main(String args[]) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();
        Class cDao = Class.forName(daoClassName);
        idao dao =(idao) cDao.newInstance();
        System.out.println(dao.getData());
    }
}
