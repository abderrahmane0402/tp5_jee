package com.isir.tp5_jpa.dao;

import com.isir.tp5_jpa.metier.Livre;
import jakarta.persistence.*;

import java.util.List;

public class GestionLivre {
    EntityManagerFactory emf;
    EntityManager em;
    public GestionLivre() {
        emf = Persistence.createEntityManagerFactory("biblio_tp5");
        em = emf.createEntityManager();
    }
    public void ajouterLivre(Livre l) {
        EntityTransaction tr=em.getTransaction();
        tr.begin();
        em.persist(l);
        tr.commit();
    }
    public Livre rechercher(int isbn) {
        Livre l = em.find(Livre.class, isbn);
        return l;
    }

    public void supprimerLivre(int isbn){
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        Livre l = rechercher(isbn);
        em.remove(l);
        trans.commit();
    }
    public void updateLivre(Livre l){
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        Livre livre = rechercher(l.getIsbn());
        livre.setAuteur(l.getAuteur());
        livre.setTitre(l.getTitre());
        livre.setNbrPage(l.getNbrPage());
        em.persist(livre);
        trans.commit();
    }
    public List<Livre> tousLesLivres(){
        Query query = em.createQuery("select l from Livre l") ;
        List<Livre> lst = query.getResultList() ;
        return lst;
    }
}
