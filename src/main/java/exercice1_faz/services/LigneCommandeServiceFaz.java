package exercice1_faz.services;

import exercice1_faz.entites.LigneCommandeProduitFaz;
import exercice1_faz.daos.IDaoFaz;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;
import java.util.List;

public class LigneCommandeServiceFaz implements IDaoFaz<LigneCommandeProduitFaz> {

    @Override
    public boolean enregistrerFaz(LigneCommandeProduitFaz o) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<LigneCommandeProduitFaz> recupererToutFaz() {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            return session.createQuery("from LigneCommandeProduitFaz", LigneCommandeProduitFaz.class).list();
        }
    }

    @Override
    public boolean effacerFaz(LigneCommandeProduitFaz o) { return false; }

    @Override
    public boolean mettreAJourFaz(LigneCommandeProduitFaz o) { return false; }

    @Override
    public LigneCommandeProduitFaz obtenirParIdFaz(int id) { return null; }
}