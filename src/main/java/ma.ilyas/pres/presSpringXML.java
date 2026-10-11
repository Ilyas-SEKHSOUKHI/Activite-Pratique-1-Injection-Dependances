package ma.ilyas.pres;

import ma.ilyas.metier.imetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class presSpringXML {
    public static void main(String args[]){
        ApplicationContext springContext = new ClassPathXmlApplicationContext("config.xml");
        imetier metier = (imetier) springContext.getBean("metier");
        System.out.println("Resultat => "+metier.calcul());
    }
}
