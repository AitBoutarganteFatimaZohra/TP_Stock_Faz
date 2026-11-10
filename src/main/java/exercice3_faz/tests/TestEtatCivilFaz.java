package exercice3_faz.tests;

import exercice3_faz.beans.*;
import exercice3_faz.services.*;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestEtatCivilFaz {
    public static void main(String[] args) throws Exception {
        System.out.println("--- Test Exercice 3 (État Civil) ---");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Création de 5 Hommes
            HommeFaz h1 = new HommeFaz("SAFI", "SAID", "0601", "Casa", sdf.parse("01/01/1970"));
            HommeFaz h2 = new HommeFaz("ALAMI", "KAMAL", "0602", "Rabat", sdf.parse("01/01/1975"));
            HommeFaz h3 = new HommeFaz("BENANI", "OMAR", "0603", "Fes", sdf.parse("01/01/1980"));
            HommeFaz h4 = new HommeFaz("CHRAIBI", "AHMED", "0604", "Tanger", sdf.parse("01/01/1985"));
            HommeFaz h5 = new HommeFaz("OUAZZANI", "YASSINE", "0605", "Marrakech", sdf.parse("01/01/1990"));
            session.save(h1); session.save(h2); session.save(h3); session.save(h4); session.save(h5);

            // Création de 10 Femmes
            FemmeFaz[] femmes = new FemmeFaz[10];
            for (int i = 0; i < 10; i++) {
                femmes[i] = new FemmeFaz("NOM_F" + i, "Prenom_F" + i, "070" + i, "Adresse", sdf.parse("01/01/199" + (i % 10)));
                session.save(femmes[i]);
            }

            // Création des Mariages pour SAID (h1) selon l'énoncé
            session.save(new MariageFaz(sdf.parse("03/09/1990"), null, 4, h1, femmes[0])); // En cours
            session.save(new MariageFaz(sdf.parse("03/09/1995"), null, 2, h1, femmes[1])); // En cours
            session.save(new MariageFaz(sdf.parse("04/11/2000"), null, 3, h1, femmes[2])); // En cours
            session.save(new MariageFaz(sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0, h1, femmes[3])); // Échoué

            // Données de test supplémentaires pour que les requêtes (femme mariée 2 fois, homme marié 4 fois) fonctionnent
            session.save(new MariageFaz(sdf.parse("01/01/2010"), null, 1, h2, femmes[3])); // Femmes[3] est mariée 2 fois
            session.save(new MariageFaz(sdf.parse("01/01/2011"), null, 0, h3, femmes[4]));
            session.save(new MariageFaz(sdf.parse("01/01/2012"), null, 0, h3, femmes[5]));
            session.save(new MariageFaz(sdf.parse("01/01/2013"), null, 0, h3, femmes[6]));
            session.save(new MariageFaz(sdf.parse("01/01/2014"), null, 0, h3, femmes[7])); // h3 marié 4 fois entre 2010 et 2015

            session.getTransaction().commit();
        }

        // --- TESTS DES METHODES ---
        HommeServiceFaz hommeService = new HommeServiceFaz();
        FemmeServiceFaz femmeService = new FemmeServiceFaz();

        System.out.println("\n--- 1. Affichage des mariages de SAID (ID 1) ---");
        hommeService.afficherDetailsMariagesHommeFaz(1);

        System.out.println("\n--- 2. Femmes mariées au moins 2 fois ---");
        femmeService.listerFemmesMarieesPlusieursFoisFaz().forEach(f ->
                System.out.println(f.getNom() + " " + f.getPrenom()));

        System.out.println("\n--- 3. Hommes mariés à 4 femmes entre 2010 et 2015 (Criteria API) ---");
        Date d1 = sdf.parse("01/01/2010");
        Date d2 = sdf.parse("31/12/2015");
        femmeService.listerHommesMariesA4FemmesFaz(d1, d2).forEach(h ->
                System.out.println(h.getNom() + " " + h.getPrenom()));

        System.out.println("\n--- 4. Nombre d'enfants de la femme 1 entre 1990 et 2000 (Native Query) ---");
        Object nbEnfants = femmeService.compterEnfantsEntreDatesFaz(1, sdf.parse("01/01/1990"), sdf.parse("31/12/2000"));
        System.out.println("Nombre d'enfants : " + nbEnfants);
    }
}