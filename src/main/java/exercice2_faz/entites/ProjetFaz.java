package exercice2_faz.entites;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projets_faz")
public class ProjetFaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    // --- AJOUT DE LA RELATION CHEF DE PROJET ---
    @ManyToOne
    @JoinColumn(name = "chef_id")
    private EmployeFaz chefDeProjetFaz;

    @OneToMany(mappedBy = "projetFaz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TacheFaz> tachesFaz;

    public ProjetFaz() {}
    public ProjetFaz(String nom, Date dateDebut, Date dateFin) {
        this.nom = nom; this.dateDebut = dateDebut; this.dateFin = dateFin;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    // GETTER ET SETTER MANQUANTS
    public EmployeFaz getChefDeProjetFaz() { return chefDeProjetFaz; }
    public void setChefDeProjetFaz(EmployeFaz chefDeProjetFaz) { this.chefDeProjetFaz = chefDeProjetFaz; }

    public List<TacheFaz> getTachesFaz() { return tachesFaz; }
}