package exercice2_faz.services;

import exercice2_faz.entites.EmployeFaz;
import exercice2_faz.entites.TacheFaz;
import exercice2_faz.entites.ProjetFaz;
import exercice1_faz.daos.IDaoFaz; // On réutilise l'interface de l'Ex 1
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class EmployeServiceFaz implements IDaoFaz<EmployeFaz> {

    @Override
    public boolean enregistrerFaz(EmployeFaz o) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction(); session.save(o); session.getTransaction().commit(); return true;
        } catch (Exception e) { return false; }
    }

    @Override public List<EmployeFaz> recupererToutFaz() { return null; }
    @Override public boolean effacerFaz(EmployeFaz o) { return false; }
    @Override public boolean mettreAJourFaz(EmployeFaz o) { return false; }
    @Override public EmployeFaz obtenirParIdFaz(int id) { return null; }

    // 1. Afficher les tâches réalisées par un employé
    public List<TacheFaz> listerTachesParEmployeFaz(int employeId) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            String hql = "SELECT et.tacheFaz FROM EmployeTacheFaz et WHERE et.employeFaz.id = :empId";
            Query<TacheFaz> query = session.createQuery(hql, TacheFaz.class);
            query.setParameter("empId", employeId);
            return query.list();
        }
    }

    // 2. Afficher les projets gérés par un employé
    public List<ProjetFaz> listerProjetsParEmployeFaz(int employeId) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            String hql = "FROM ProjetFaz p WHERE p.chefDeProjetFaz.id = :empId";
            Query<ProjetFaz> query = session.createQuery(hql, ProjetFaz.class);
            query.setParameter("empId", employeId);
            return query.list();
        }
    }
}