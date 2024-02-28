package com.isir.tp5_jpa.metier;

import com.isir.tp5_jpa.dao.GestionLivre;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class LivreService {
    private GestionLivre gestionLivre;

    public LivreService() {
        gestionLivre = new GestionLivre();
    }

    public void ajouterLivre(int isbn, String titre, String auteur, int pages) {
        Livre livre = new Livre(isbn,titre,auteur,pages);
        gestionLivre.ajouterLivre(livre);
    }
    public void supprimerLivre(int id){
        gestionLivre.supprimerLivre(id);
    }

    public void updateLivre(Livre l){
        gestionLivre.updateLivre(l);
    }

    public Livre findLivre(int id){
        return gestionLivre.rechercher(id);
    }

    public List<Livre> getAllLivres() {
        return gestionLivre.tousLesLivres();
    }
}
