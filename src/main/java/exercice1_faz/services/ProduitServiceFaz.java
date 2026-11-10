package exercice1_faz.services;

import exercice1_faz.entites.ProduitFaz;
import exercice1_faz.entites.LigneCommandeProduitFaz;
import exercice1_faz.daos.IDaoFaz;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class ProduitServiceFaz implements IDaoFaz<ProduitFaz> {

    @Override
    public boolean enregistrerFaz(ProduitFaz o) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction(); session.save(o); session.getTransaction().commit(); return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    @Override
    public List<ProduitFaz> recupererToutFaz() {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            return session.createQuery("from ProduitFaz", ProduitFaz.class).list();
        }
    }

    // 1. Liste des produits par catégorie
    public List<ProduitFaz> listerProduitsParCategorieFaz(int idCategorie) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            Query<ProduitFaz> query = session.createQuery("from ProduitFaz p where p.categorieFaz.id = :catId", ProduitFaz.class);
            query.setParameter("catId", idCategorie);
            return query.list();
        }
    }

    // 2. Produits commandés entre deux dates
    public List<ProduitFaz> listerProduitsEntreDatesFaz(Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            String hql = "SELECT l.produitFaz FROM LigneCommandeProduitFaz l WHERE l.commandeFaz.dateCommande BETWEEN :debut AND :fin";
            Query<ProduitFaz> query = session.createQuery(hql, ProduitFaz.class);
            query.setParameter("debut", dateDebut);
            query.setParameter("fin", dateFin);
            return query.list();
        }
    }

    // 3. Affichage des produits d'une commande donnée
    public void afficherProduitsCommandeFaz(int idCommande) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            String hql = "FROM LigneCommandeProduitFaz l WHERE l.commandeFaz.id = :cmdId";
            Query<LigneCommandeProduitFaz> query = session.createQuery(hql, LigneCommandeProduitFaz.class);
            query.setParameter("cmdId", idCommande);
            List<LigneCommandeProduitFaz> lignes = query.list();

            System.out.println("Commande : " + idCommande);
            System.out.println("Liste des produits :");
            System.out.println("Référence\tPrix\tQuantité");
            for (LigneCommandeProduitFaz ligne : lignes) {
                System.out.println(ligne.getProduitFaz().getReference() + "\t\t" +
                        ligne.getProduitFaz().getPrix() + " DH\t" +
                        ligne.getQuantite());
            }
        }
    }

    // 4. Requête nommée
    public List<ProduitFaz> trouverProduitsPrixSup100Faz() {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            return session.createNamedQuery("produitsPrixSup100Faz", ProduitFaz.class).list();
        }
    }

    @Override public boolean effacerFaz(ProduitFaz o) { return false; }
    @Override public boolean mettreAJourFaz(ProduitFaz o) { return false; }
    @Override public ProduitFaz obtenirParIdFaz(int id) { return null; }
}