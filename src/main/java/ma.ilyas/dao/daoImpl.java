package ma.ilyas.dao;

public class daoImpl implements idao {
    //Par exemple version DataBase
    @Override
    public double getData() {
        System.out.println("Version de base de donnees");
        return 10;
    }
}
