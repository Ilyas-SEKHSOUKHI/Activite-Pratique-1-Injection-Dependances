package ma.ilyas.dao;

public class daoImplV2 implements idao{
    //Par exemple version WebUI
    @Override
    public double getData() {
        System.out.println("Version de web service");
        return 20;
    }
}
