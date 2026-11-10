package exercice3_faz.services;

import exercice3_faz.beans.HommeFaz;
import exercice3_faz.beans.FemmeFaz;
import exercice3_faz.beans.MariageFaz;
import exercice1_faz.daos.IDaoFaz;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class HommeServiceFaz implements IDaoFaz<HommeFaz> {

    @Override
    public boolean enregistrerFaz(HommeFaz o) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction(); session.save(o); session.getTransaction().commit(); return true;
        } catch (Exception e) { return false; }
    }

    @Override public List<HommeFaz> recupererToutFaz() { return null; }
    @Override public boolean effacerFaz(HommeFaz o) { return false; }
    @Override public boolean mettreAJourFaz(HommeFaz o) { return false; }
    @Override public HommeFaz obtenirParIdFaz(int id) { return null; }

    // 1. Épouses d'un homme entre deux dates
    public List<FemmeFaz> listerEpousesEntreDatesFaz(int hommeId, Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            String hql = "SELECT m.femmeFaz FROM MariageFaz m WHERE m.hommeFaz.id = :hId " +
                    "AND m.dateDebut BETWEEN :debut AND :fin";
            Query<FemmeFaz> query = session.createQuery(hql, FemmeFaz.class);
            query.setParameter("hId", hommeId);
            query.setParameter("debut", dateDebut);
            query.setParameter("fin", dateFin);
            return query.list();
        }
    }

    // 2. Afficher les mariages d'un homme donné avec détails
    public void afficherDetailsMariagesHommeFaz(int hommeId) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            HommeFaz h = session.get(HommeFaz.class, hommeId);
            if (h == null) return;

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Nom : " + h.getNom() + " " + h.getPrenom());

            String hql = "FROM MariageFaz m WHERE m.hommeFaz.id = :hId";
            Query<MariageFaz> query = session.createQuery(hql, MariageFaz.class);
            query.setParameter("hId", hommeId);
            List<MariageFaz> mariages = query.list();

            System.out.println("Mariages En Cours :");
            int countEnCours = 1;
            for (MariageFaz m : mariages) {
                if (m.getDateFin() == null) {
                    System.out.println(countEnCours++ + ". Femme : " + m.getFemmeFaz().getNom() + " " + m.getFemmeFaz().getPrenom() +
                            "   Date Début : " + sdf.format(m.getDateDebut()) +
                            "    Nbr Enfants : " + m.getNbrEnfant());
                }
            }

            System.out.println("\nMariages échoués :");
            int countEchoue = 1;
            for (MariageFaz m : mariages) {
                if (m.getDateFin() != null) {
                    System.out.println(countEchoue++ + ". Femme : " + m.getFemmeFaz().getNom() + " " + m.getFemmeFaz().getPrenom() +
                            "   Date Début : " + sdf.format(m.getDateDebut()) +
                            "\nDate Fin : " + sdf.format(m.getDateFin()) +
                            "    Nbr Enfants : " + m.getNbrEnfant());
                }
            }
        }
    }
}