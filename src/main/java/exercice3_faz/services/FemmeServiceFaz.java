package exercice3_faz.services;

import exercice3_faz.beans.FemmeFaz;
import exercice1_faz.daos.IDaoFaz;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public class FemmeServiceFaz implements IDaoFaz<FemmeFaz> {

    @Override
    public boolean enregistrerFaz(FemmeFaz o) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction(); session.save(o); session.getTransaction().commit(); return true;
        } catch (Exception e) { return false; }
    }

    @Override public List<FemmeFaz> recupererToutFaz() { return null; }
    @Override public boolean effacerFaz(FemmeFaz o) { return false; }
    @Override public boolean mettreAJourFaz(FemmeFaz o) { return false; }
    @Override public FemmeFaz obtenirParIdFaz(int id) { return null; }

    // 1. Requête Native : Nombre d'enfants d'une femme entre deux dates
    public Object compterEnfantsEntreDatesFaz(int femmeId, Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            String sql = "SELECT SUM(m.nbrEnfant) FROM mariages_faz m " +
                    "WHERE m.femme_id = :fId AND m.dateDebut BETWEEN :debut AND :fin";
            Query query = session.createNativeQuery(sql);
            query.setParameter("fId", femmeId);
            query.setParameter("debut", dateDebut);
            query.setParameter("fin", dateFin);
            return query.getSingleResult();
        }
    }

    // 2. Requête nommée : Femmes mariées au moins 2 fois
    public List<FemmeFaz> listerFemmesMarieesPlusieursFoisFaz() {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            String hql = "SELECT m.femmeFaz FROM MariageFaz m GROUP BY m.femmeFaz.id HAVING COUNT(m.id) >= 2";
            return session.createQuery(hql, FemmeFaz.class).list();
        }
    }

    // 3. API Criteria : Hommes mariés à 4 femmes entre deux dates
    public List<exercice3_faz.beans.HommeFaz> listerHommesMariesA4FemmesFaz(Date debut, Date fin) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<exercice3_faz.beans.HommeFaz> cr = cb.createQuery(exercice3_faz.beans.HommeFaz.class);
            Root<exercice3_faz.beans.MariageFaz> root = cr.from(exercice3_faz.beans.MariageFaz.class);

            // On sélectionne l'homme, on groupe par lui, et on filtre sur la date
            cr.select(root.get("hommeFaz"))
                    .where(cb.between(root.get("dateDebut"), debut, fin))
                    .groupBy(root.get("hommeFaz").get("id"))
                    .having(cb.equal(cb.count(root.get("id")), 4));

            return session.createQuery(cr).list();
        }
    }
}