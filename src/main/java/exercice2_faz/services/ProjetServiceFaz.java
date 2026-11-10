package exercice2_faz.services;

import exercice2_faz.entites.ProjetFaz;
import exercice2_faz.entites.TacheFaz;
import exercice2_faz.entites.EmployeTacheFaz;
import exercice1_faz.daos.IDaoFaz;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProjetServiceFaz implements IDaoFaz<ProjetFaz> {

    @Override
    public boolean enregistrerFaz(ProjetFaz o) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction(); session.save(o); session.getTransaction().commit(); return true;
        } catch (Exception e) { return false; }
    }

    @Override public List<ProjetFaz> recupererToutFaz() { return null; }
    @Override public boolean effacerFaz(ProjetFaz o) { return false; }
    @Override public boolean mettreAJourFaz(ProjetFaz o) { return false; }
    @Override public ProjetFaz obtenirParIdFaz(int id) { return null; }

    // 1. Afficher les tâches planifiées pour un projet
    public List<TacheFaz> listerTachesPlanifieesFaz(int projetId) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            Query<TacheFaz> query = session.createQuery("FROM TacheFaz t WHERE t.projetFaz.id = :projId", TacheFaz.class);
            query.setParameter("projId", projetId);
            return query.list();
        }
    }

    // 2. Afficher les tâches réalisées avec les dates réelles (Format exact demandé)
    public void afficherDetailsProjetFaz(int projetId) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            ProjetFaz projet = session.get(ProjetFaz.class, projetId);
            if (projet == null) return;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateDebutStr = sdf.format(projet.getDateDebut());

            System.out.println("Projet : " + projet.getId() + "\t Nom : " + projet.getNom() + "\t Date début : " + dateDebutStr);
            System.out.println("Liste des tâches :");
            System.out.println("Num\tNom\t\tDate Début Réelle\tDate Fin Réelle");

            String hql = "FROM EmployeTacheFaz et WHERE et.tacheFaz.projetFaz.id = :projId";
            Query<EmployeTacheFaz> query = session.createQuery(hql, EmployeTacheFaz.class);
            query.setParameter("projId", projetId);
            List<EmployeTacheFaz> lignes = query.list();

            for (EmployeTacheFaz et : lignes) {
                System.out.println(et.getTacheFaz().getId() + "\t" +
                        et.getTacheFaz().getNom() + "\t\t" +
                        sdf.format(et.getDateDebutReelle()) + "\t\t" +
                        sdf.format(et.getDateFinReelle()));
            }
        }
    }
}