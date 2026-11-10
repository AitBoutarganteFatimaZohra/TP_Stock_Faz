package exercice2_faz.tests;

import exercice2_faz.entites.*;
import exercice2_faz.services.ProjetServiceFaz;
import exercice1_faz.utils.HibernateUtilFaz;
import org.hibernate.Session;

import java.text.SimpleDateFormat;

public class TestProjetFaz {
    public static void main(String[] args) throws Exception {
        System.out.println("--- Test Exercice 2 (Gestion de projets) ---");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (Session session = HibernateUtilFaz.getSessionFactory().openSession()) {
            session.beginTransaction();

            // 1. Création Employé
            EmployeFaz employe = new EmployeFaz("Dupont", "Jean", "0600000000");
            session.save(employe);

            // 2. Création Projet
            ProjetFaz projet = new ProjetFaz("Gestion de stock Faz", sdf.parse("14/01/2013"), sdf.parse("30/12/2013"));
            projet.setChefDeProjetFaz(employe); // Assigner le chef
            session.save(projet);

            // 3. Création Tâches
            TacheFaz t1 = new TacheFaz("Analyse", sdf.parse("01/02/2013"), sdf.parse("10/02/2013"), 1500.0);
            t1.setProjetFaz(projet);
            session.save(t1);

            TacheFaz t2 = new TacheFaz("Conception", sdf.parse("01/03/2013"), sdf.parse("10/03/2013"), 800.0);
            t2.setProjetFaz(projet);
            session.save(t2);

            // 4. Liaison Employé-Tâche (avec dates réelles)
            EmployeTacheFaz et1 = new EmployeTacheFaz(sdf.parse("10/02/2013"), sdf.parse("20/02/2013"), employe, t1);
            session.save(et1);
            EmployeTacheFaz et2 = new EmployeTacheFaz(sdf.parse("10/03/2013"), sdf.parse("15/03/2013"), employe, t2);
            session.save(et2);

            session.getTransaction().commit();
        }

        // --- TESTS DES METHODES ---
        ProjetServiceFaz projetService = new ProjetServiceFaz();

        System.out.println("\n--- 1. Affichage des tâches du projet 1 ---");
        projetService.afficherDetailsProjetFaz(1);

        // Test Requête nommée (Tâches > 1000 DH)
        System.out.println("\n--- 2. Tâches > 1000 DH ---");
        exercice2_faz.services.TacheServiceFaz tacheService = new exercice2_faz.services.TacheServiceFaz();
        tacheService.trouverTachesPrixSup1000Faz().forEach(t -> System.out.println(t.getNom() + " - " + t.getPrix() + " DH"));
    }
}