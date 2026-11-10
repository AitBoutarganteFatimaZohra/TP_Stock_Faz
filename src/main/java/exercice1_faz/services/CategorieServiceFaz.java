package exercice1_faz.services;

import exercice1_faz.entites.CategorieFaz;
import exercice1_faz.daos.IDaoFaz;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;
import java.util.List;

public class CategorieServiceFaz implements IDaoFaz<CategorieFaz> {
    @Override
    public boolean enregistrerFaz(CategorieFaz o) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction(); session.save(o); session.getTransaction().commit(); return true;
        } catch (Exception e) { return false; }
    }
    @Override public List<CategorieFaz> recupererToutFaz() {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            return session.createQuery("from CategorieFaz", CategorieFaz.class).list();
        }
    }
    @Override public boolean effacerFaz(CategorieFaz o) { return false; }
    @Override public boolean mettreAJourFaz(CategorieFaz o) { return false; }
    @Override public CategorieFaz obtenirParIdFaz(int id) { return null; }
}