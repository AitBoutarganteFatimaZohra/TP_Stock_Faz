package exercice1_faz.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;
import java.io.InputStream;

public class HibernateUtilFaz {
    private static final SessionFactory sessionFactory = buildSessionFactoryFaz();

    private static SessionFactory buildSessionFactoryFaz() {
        try {
            Configuration configuration = new Configuration();
            Properties props = new Properties();
            try (InputStream is = HibernateUtilFaz.class.getClassLoader().getResourceAsStream("application.properties")) {
                props.load(is);
            }
            configuration.addProperties(props);

            // --- EXERCICE 1 ---
            configuration.addAnnotatedClass(exercice1_faz.entites.CategorieFaz.class);
            configuration.addAnnotatedClass(exercice1_faz.entites.ProduitFaz.class);
            configuration.addAnnotatedClass(exercice1_faz.entites.CommandeFaz.class);
            configuration.addAnnotatedClass(exercice1_faz.entites.LigneCommandeProduitFaz.class);

            // --- EXERCICE 2 ---
            configuration.addAnnotatedClass(exercice2_faz.entites.ProjetFaz.class);
            configuration.addAnnotatedClass(exercice2_faz.entites.EmployeFaz.class);
            configuration.addAnnotatedClass(exercice2_faz.entites.TacheFaz.class);
            configuration.addAnnotatedClass(exercice2_faz.entites.EmployeTacheFaz.class);


            // Ajoutez ces 4 lignes à la suite des autres dans buildSessionFactoryFaz()
            configuration.addAnnotatedClass(exercice3_faz.beans.PersonneFaz.class);
            configuration.addAnnotatedClass(exercice3_faz.beans.HommeFaz.class);
            configuration.addAnnotatedClass(exercice3_faz.beans.FemmeFaz.class);
            configuration.addAnnotatedClass(exercice3_faz.beans.MariageFaz.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Erreur SessionFactory : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}