package exercice1_faz.tests;

// Ces 4 lignes sont les imports manquants
import exercice1_faz.entites.CategorieFaz;
import exercice1_faz.entites.ProduitFaz;
import exercice1_faz.entites.CommandeFaz;
import exercice1_faz.entites.LigneCommandeProduitFaz;
// ------------------------------------

import exercice1_faz.services.ProduitServiceFaz;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;

import java.text.SimpleDateFormat;

public class TestStockFaz {
    public static void main(String[] args) throws Exception {
        System.out.println("--- Démarrage du Test avec suffixe Faz ---");

        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction();

            CategorieFaz cat = new CategorieFaz("CAT01", "Informatique");
            session.save(cat);

            ProduitFaz p1 = new ProduitFaz("ES12", 120f, cat);
            ProduitFaz p2 = new ProduitFaz("ZR85", 100f, cat);
            ProduitFaz p3 = new ProduitFaz("EE85", 200f, cat);
            session.save(p1); session.save(p2); session.save(p3);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            CommandeFaz cmd = new CommandeFaz(sdf.parse("2013-03-14"));
            session.save(cmd);

            session.save(new LigneCommandeProduitFaz(7, p1, cmd));
            session.save(new LigneCommandeProduitFaz(14, p2, cmd));
            session.save(new LigneCommandeProduitFaz(5, p3, cmd));

            session.getTransaction().commit();
        }

        ProduitServiceFaz service = new ProduitServiceFaz();

        System.out.println("\n--- 1. Produits par catégorie (ID 1) ---");
        service.listerProduitsParCategorieFaz(1).forEach(p -> System.out.println(p.getReference()));

        System.out.println("\n--- 2. Affichage des produits de la commande 1 ---");
        service.afficherProduitsCommandeFaz(1);

        System.out.println("\n--- 3. Produits avec prix > 100 DH (NamedQuery) ---");
        service.trouverProduitsPrixSup100Faz().forEach(p -> System.out.println(p.getReference() + " - " + p.getPrix() + " DH"));
    }
}