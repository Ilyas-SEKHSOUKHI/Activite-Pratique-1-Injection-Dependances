package ma.ilyas.pres;

import ma.ilyas.metier.imetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresSpringAnnotation {
    public static void main(String[] args){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("ma.ilyas");
        imetier metier = applicationContext.getBean(imetier.class);
        System.out.println("Le resultat avec spring Annotation est :"+metier.calcul());
    }
}
