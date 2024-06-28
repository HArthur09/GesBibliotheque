/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author admin
 */
public class Fonction {
    public Connection connexion = null;
    public boolean a = false;
    Statement st;
    ResultSet rs;
    Utilisateur util =  new Utilisateur();
    
    public boolean AjoutUtilisateur(){
        a = false;
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            st = connexion.createStatement();
            st.executeUpdate("INSERT INTO utilisateur (Nom, Prenom, Num_telephone, Sexe, Statut, Mdp) VALUES" + util.Nom+util.Prenom+util.Num_telephone+util.Sexe+util.Statut+util.Mot_de_passe);
            JOptionPane.showMessageDialog(null, "ok sans soucis");
            if (connexion != null){
                System.out.print("connection invalide");
            }
            a = true;
        } catch (SQLException e) {
        }
        return a;
    }
}
