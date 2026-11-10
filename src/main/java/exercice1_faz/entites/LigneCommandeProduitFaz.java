package exercice1_faz.entites;

import javax.persistence.*;

@Entity
@Table(name = "lignes_commande_faz")
public class LigneCommandeProduitFaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private ProduitFaz produitFaz;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private CommandeFaz commandeFaz;

    public LigneCommandeProduitFaz() {}
    public LigneCommandeProduitFaz(int quantite, ProduitFaz produitFaz, CommandeFaz commandeFaz) {
        this.quantite = quantite; this.produitFaz = produitFaz; this.commandeFaz = commandeFaz;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public ProduitFaz getProduitFaz() { return produitFaz; }
    public void setProduitFaz(ProduitFaz produitFaz) { this.produitFaz = produitFaz; }
    public CommandeFaz getCommandeFaz() { return commandeFaz; }
    public void setCommandeFaz(CommandeFaz commandeFaz) { this.commandeFaz = commandeFaz; }
}