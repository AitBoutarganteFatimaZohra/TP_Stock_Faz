package exercice1_faz.daos;

import java.util.List;

public interface IDaoFaz<T> {
    boolean enregistrerFaz(T o);
    boolean effacerFaz(T o);
    boolean mettreAJourFaz(T o);
    T obtenirParIdFaz(int id);
    List<T> recupererToutFaz();
}