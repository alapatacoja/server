package es.ieslvareda.properties;

import es.ieslvareda.model.Model;
import es.ieslvareda.model.Person;
import es.ieslvareda.server.model.*;

public class Test {
    public static void main(String[] args) {
        ImpCocheService service = new ImpCocheService();
        System.out.println(service.get("1111AAA"));
//        System.out.println(service.get("1234abc"));
        //model.addPerson(new Person("4444","Tomas","Gimenez Gimenez",30));
//        model.updatePerson(new Person("4444","Tomás","Gimenez Gimenez",30));
//
//        System.out.println(model.deletePerson("1 OR 1=1"));
//        System.out.println(model.getPersons());
        //System.out.println(model.getEmpleados());

        //System.out.println(model.authenticate("1' or password!='1","234"));
    }
}
