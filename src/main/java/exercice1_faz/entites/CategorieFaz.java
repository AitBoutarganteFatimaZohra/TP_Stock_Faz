package exercice1_faz.entites;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories_faz")
public class CategorieFaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String libelle;

    @OneToMany(mappedBy = "categorieFaz", fetch = FetchType.LAZY)
    private List<ProduitFaz> produitsFaz;

    public CategorieFaz() {}
    public CategorieFaz(String code, String libelle) { this.code = code; this.libelle = libelle; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
}