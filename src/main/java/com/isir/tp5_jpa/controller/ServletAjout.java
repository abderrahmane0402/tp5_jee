package com.isir.tp5_jpa.controller;

import com.isir.tp5_jpa.metier.Livre;
import com.isir.tp5_jpa.metier.LivreService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ServletAjout", urlPatterns = {"/ServletAjout"})
public class ServletAjout extends HttpServlet {

    @Override
    public void init() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LivreService livreService = new LivreService();
            if (Objects.equals(request.getParameter("submit"), "Modifier")) {
                System.out.println(request.getParameter("isbn"));
                System.out.println(request.getParameter("nbrPage"));
                Livre livre = new Livre(Integer.parseInt(request.getParameter("isbn")), request.getParameter("titre"), request.getParameter("auteur"), Integer.parseInt(request.getParameter("nbrPage")));
                livreService.updateLivre(livre);
            } else {
                System.out.println("ajouter");
                int pages = Integer.parseInt(request.getParameter("nbrPage"));
                livreService.ajouterLivre(Integer.parseInt(request.getParameter("isbn")), request.getParameter("titre"), request.getParameter("auteur"), pages);
            }
            List<Livre> livres = livreService.getAllLivres();
            request.setAttribute("livres", livres);
            request.getRequestDispatcher("livres.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String faire = req.getParameter("faire");
        String id = req.getParameter("id");
        LivreService livreService = new LivreService();
        if (faire != null && faire.equals("supprimer")) {
            livreService.supprimerLivre(Integer.parseInt(id));
            List<Livre> livres = livreService.getAllLivres();
            req.setAttribute("livres", livres);
            req.getRequestDispatcher("livres.jsp").forward(req, resp);
        } else if (faire != null && faire.equals("modifier")) {
            Livre livre = livreService.findLivre(Integer.parseInt(id));
            req.setAttribute("Livre", livre);
            req.getRequestDispatcher("modifier.jsp").forward(req, resp);
        } else {
            List<Livre> livres = livreService.getAllLivres();
            req.setAttribute("livres", livres);
            req.getRequestDispatcher("livres.jsp").forward(req, resp);
        }
    }
}
