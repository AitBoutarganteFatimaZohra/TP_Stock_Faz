package exercice1_faz.entites;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commandes_faz")
public class CommandeFaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateCommande;

    @OneToMany(mappedBy = "commandeFaz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LigneCommandeProduitFaz> lignesFaz;

    public CommandeFaz() {}
    public CommandeFaz(Date dateCommande) { this.dateCommande = dateCommande; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getDateCommande() { return dateCommande; }
    public void setDateCommande(Date dateCommande) { this.dateCommande = dateCommande; }
}