/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackageFonction;

import java.sql.*;

/**
 *
 * @author admin
 */
public class fonctLivre {

    Connection con = null;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    public boolean a = false;
    public String NomLivre;
    public String Genre;
    public Date annéeSortie;
    public String MaisonEdition;
    public String Auteur;
    public int Quantité;
    public Date DateEnregistrement;
    public String Moyen_endomagé;
    public String Mesure_prise;
    public String llivre;

    public boolean AjouterLivre() {
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("INSERT INTO livre (Nom_livre, Genre, Annee_sortie, Auteur, Maison_edition, Date_enregistrement, Quantité, QuantiteDisponible) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, NomLivre);
            ps.setString(2, Genre);
            ps.setDate(3, annéeSortie);
            ps.setString(4, MaisonEdition);
            ps.setString(5, Auteur);
            ps.setDate(6, DateEnregistrement);
            ps.setInt(7, Quantité);
            ps.setInt(8, Quantité);
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est" + e);
        }
        return a;
    }

    public boolean ModifierLivre() {
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("UPDATE livre SET Nom_Livre = ?, Genre = ?, Annee_sortie = ?, Auteur = ?, Maison_edition = ?, Date_enregistrement = ?, Quantité = ?, QuantiteDisponible = ? WHERE Nom_Livre = ?");
            ps.setString(1, NomLivre);
            ps.setString(2, Genre);
            ps.setDate(3, annéeSortie);
            ps.setString(4, MaisonEdition);
            ps.setString(5, Auteur);
            ps.setDate(6, DateEnregistrement);
            ps.setInt(7, Quantité);
            ps.setInt(8, Quantité);
            ps.setString(9, llivre);
            ps.executeUpdate();
            con.close();
            ps.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est" + e);
        }
        return a;
    }

    public boolean SupprimerLivre() {
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("DELETE FROM livre WHERE Nom_livre = ?");
            ps.setString(1, NomLivre);
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est" + e);
        }
        return a;
    }

    public boolean EntretienLivre() {
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("INSERT INTO maintenance (Nom_livre, Date_enregistrement, Moyen_endomage, Mesure_Prise, Quantité) VALUES (?,?,?,?,?)");
            ps.setString(1, NomLivre);
            ps.setDate(2, DateEnregistrement);
            ps.setString(3, Moyen_endomagé);
            ps.setString(4, Mesure_prise);
            ps.setInt(5, Quantité);
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est" + e);
        }
        return a;
    }
}
