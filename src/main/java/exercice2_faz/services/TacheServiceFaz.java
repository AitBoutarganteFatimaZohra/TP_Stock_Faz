package exercice2_faz.services;

import exercice2_faz.entites.TacheFaz;
import exercice1_faz.daos.IDaoFaz;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class TacheServiceFaz implements IDaoFaz<TacheFaz> {

    @Override
    public boolean enregistrerFaz(TacheFaz o) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction(); session.save(o); session.getTransaction().commit(); return true;
        } catch (Exception e) { return false; }
    }

    @Override public List<TacheFaz> recupererToutFaz() { return null; }
    @Override public boolean effacerFaz(TacheFaz o) { return false; }
    @Override public boolean mettreAJourFaz(TacheFaz o) { return false; }
    @Override public TacheFaz obtenirParIdFaz(int id) { return null; }

    // 1. Requête nommée
    public List<TacheFaz> trouverTachesPrixSup1000Faz() {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            return session.createNamedQuery("tachesPrixSup1000Faz", TacheFaz.class).list();
        }
    }

    // 2. Tâches réalisées entre deux dates
    public List<TacheFaz> listerTachesEntreDatesFaz(Date debut, Date fin) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            String hql = "SELECT et.tacheFaz FROM EmployeTacheFaz et WHERE et.dateDebutReelle >= :debut AND et.dateFinReelle <= :fin";
            Query<TacheFaz> query = session.createQuery(hql, TacheFaz.class);
            query.setParameter("debut", debut);
            query.setParameter("fin", fin);
            return query.list();
        }
    }
}