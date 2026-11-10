package exercice3_faz.services;

import exercice3_faz.beans.MariageFaz;
import exercice1_faz.daos.IDaoFaz;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;
import java.util.List;

public class MariageServiceFaz implements IDaoFaz<MariageFaz> {
    @Override
    public boolean enregistrerFaz(MariageFaz o) {
        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction(); session.save(o); session.getTransaction().commit(); return true;
        } catch (Exception e) { return false; }
    }
    @Override public List<MariageFaz> recupererToutFaz() { return null; }
    @Override public boolean effacerFaz(MariageFaz o) { return false; }
    @Override public boolean mettreAJourFaz(MariageFaz o) { return false; }
    @Override public MariageFaz obtenirParIdFaz(int id) { return null; }
}