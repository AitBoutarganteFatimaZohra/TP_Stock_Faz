package exercice2_faz.entites;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employes_faz")
public class EmployeFaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String telephone;

    @OneToMany(mappedBy = "chefDeProjetFaz", fetch = FetchType.LAZY)
    private List<ProjetFaz> projetsGeresFaz;

    @OneToMany(mappedBy = "employeFaz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmployeTacheFaz> employeTachesFaz;

    public EmployeFaz() {}
    public EmployeFaz(String nom, String prenom, String telephone) {
        this.nom = nom; this.prenom = prenom; this.telephone = telephone;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public List<ProjetFaz> getProjetsGeresFaz() { return projetsGeresFaz; }
}