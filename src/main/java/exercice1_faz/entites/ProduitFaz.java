package exercice1_faz.entites;

import javax.persistence.*;

@Entity
@Table(name = "produits_faz")
@NamedQuery(name = "produitsPrixSup100Faz", query = "FROM ProduitFaz p WHERE p.prix > 100")
public class ProduitFaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reference;
    private float prix;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private CategorieFaz categorieFaz;

    public ProduitFaz() {}
    public ProduitFaz(String reference, float prix, CategorieFaz categorieFaz) {
        this.reference = reference; this.prix = prix; this.categorieFaz = categorieFaz;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }
    public CategorieFaz getCategorieFaz() { return categorieFaz; }
    public void setCategorieFaz(CategorieFaz categorieFaz) { this.categorieFaz = categorieFaz; }
}