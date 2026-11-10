package exercice2_faz.entites;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "taches_faz")
@NamedQuery(name = "tachesPrixSup1000Faz", query = "FROM TacheFaz t WHERE t.prix > 1000")
public class TacheFaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private double prix;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private ProjetFaz projetFaz;

    @OneToMany(mappedBy = "tacheFaz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmployeTacheFaz> employeTachesFaz;

    public TacheFaz() {}
    public TacheFaz(String nom, Date dateDebut, Date dateFin, double prix) {
        this.nom = nom; this.dateDebut = dateDebut; this.dateFin = dateFin; this.prix = prix;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public ProjetFaz getProjetFaz() { return projetFaz; }
    public void setProjetFaz(ProjetFaz projetFaz) { this.projetFaz = projetFaz; }
}