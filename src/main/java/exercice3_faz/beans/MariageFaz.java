package exercice3_faz.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mariages_faz")
public class MariageFaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private int nbrEnfant;

    @ManyToOne
    @JoinColumn(name = "homme_id")
    private HommeFaz hommeFaz;

    @ManyToOne
    @JoinColumn(name = "femme_id")
    private FemmeFaz femmeFaz;

    public MariageFaz() {}
    public MariageFaz(Date dateDebut, Date dateFin, int nbrEnfant, HommeFaz hommeFaz, FemmeFaz femmeFaz) {
        this.dateDebut = dateDebut; this.dateFin = dateFin; this.nbrEnfant = nbrEnfant;
        this.hommeFaz = hommeFaz; this.femmeFaz = femmeFaz;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public int getNbrEnfant() { return nbrEnfant; }
    public void setNbrEnfant(int nbrEnfant) { this.nbrEnfant = nbrEnfant; }
    public HommeFaz getHommeFaz() { return hommeFaz; }
    public void setHommeFaz(HommeFaz hommeFaz) { this.hommeFaz = hommeFaz; }
    public FemmeFaz getFemmeFaz() { return femmeFaz; }
    public void setFemmeFaz(FemmeFaz femmeFaz) { this.femmeFaz = femmeFaz; }
}