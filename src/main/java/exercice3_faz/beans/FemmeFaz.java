package exercice3_faz.beans;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class FemmeFaz extends PersonneFaz {
    public FemmeFaz() {}
    public FemmeFaz(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
}