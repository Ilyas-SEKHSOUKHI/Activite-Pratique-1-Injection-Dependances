package ma.ilyas.ext;
import ma.ilyas.dao.idao;

public class daoImplV2 implements idao {
    //Par exemple version WebUI
    @Override
    public double getData() {
        System.out.println("Version de web service");
        return 20;
    }
}
