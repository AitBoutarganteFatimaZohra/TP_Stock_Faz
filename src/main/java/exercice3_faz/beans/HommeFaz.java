package exercice3_faz.beans;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class HommeFaz extends PersonneFaz {
    public HommeFaz() {}
    public HommeFaz(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
}