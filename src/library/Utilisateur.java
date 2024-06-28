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
public class Utilisateur {
    public String Nom;
    public String Prenom;
    public String Num_telephone;
    public String Sexe;
    public String Statut;
    public String Mot_de_passe;
    Connection connexion = null;
    Statement st;
    ResultSet rs;
    boolean a = false;
    
    public boolean AjoutUtilisateur(){
        a = false;
        try {
            System.out.println(Nom);
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            st = connexion.createStatement();
            st.executeUpdate("INSERT INTO utilisateur (Nom, Prenom, Num_telephone, Sexe, Statut, Mdp) VALUES" + Nom+Prenom+Num_telephone+Sexe+Statut+Mot_de_passe);
            JOptionPane.showMessageDialog(null, "ok");
            if (connexion != null){
                System.out.print("connection invalide");
            }
            a = true;
        } catch (SQLException e) {
        }
        return a;
    }
    
    
}
