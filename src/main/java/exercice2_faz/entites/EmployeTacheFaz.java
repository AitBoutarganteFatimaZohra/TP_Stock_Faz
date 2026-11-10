package exercice2_faz.entites;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employes_taches_faz")
public class EmployeTacheFaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;

    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private EmployeFaz employeFaz;

    @ManyToOne
    @JoinColumn(name = "tache_id")
    private TacheFaz tacheFaz;

    public EmployeTacheFaz() {}
    public EmployeTacheFaz(Date dateDebutReelle, Date dateFinReelle, EmployeFaz employeFaz, TacheFaz tacheFaz) {
        this.dateDebutReelle = dateDebutReelle; this.dateFinReelle = dateFinReelle;
        this.employeFaz = employeFaz; this.tacheFaz = tacheFaz;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getDateDebutReelle() { return dateDebutReelle; }
    public void setDateDebutReelle(Date dateDebutReelle) { this.dateDebutReelle = dateDebutReelle; }
    public Date getDateFinReelle() { return dateFinReelle; }
    public void setDateFinReelle(Date dateFinReelle) { this.dateFinReelle = dateFinReelle; }
    public EmployeFaz getEmployeFaz() { return employeFaz; }
    public void setEmployeFaz(EmployeFaz employeFaz) { this.employeFaz = employeFaz; }
    public TacheFaz getTacheFaz() { return tacheFaz; }
    public void setTacheFaz(TacheFaz tacheFaz) { this.tacheFaz = tacheFaz; }
}