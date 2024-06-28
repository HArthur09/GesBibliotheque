/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackageFonction;

import net.proteanit.sql.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author admin
 */
public class fonctUtlisateur {
    Connection con = null;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    public boolean a = false;
    public boolean b = false;
    public String Nom;
    public String Prenom;
    public String Num_telephone;
    public String Sexe;
    public String Statut;
    public String Mot_de_passe;
    public String nnom;
    
    public boolean verifierUtilisateur(){
    b = true;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT Nom FROM utilisateur");
            while(rs.next()){
                if (rs.getString("Nom").equals(Nom)){   
                    b = false;
                }
            }              
        } catch (SQLException e) {
        }
    return b;
}
    
    public boolean AjouterUtilisateur(){
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("INSERT INTO utilisateur (Nom, Prenom, Num_telephone, Sexe, Statut, Mdp) VALUES (?,?,?,?,?,?)");
            ps.setString(1, Nom);
            ps.setString(2, Prenom);
            ps.setString(3, Num_telephone);
            ps.setString(4, Sexe);
            ps.setString(5, Statut);
            ps.setString(6, Mot_de_passe);
            ps.executeUpdate();
            con.close();
            ps.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est" + e);
        }
        return a;
    }
    
    public boolean ModifierUtilisateur(){
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT Nom, Mdp, Statut FROM utilisateur");
            while(rs.next()){
                if (rs.getString("Nom").equals(Nom) && rs.getString("Mdp").equals(Mot_de_passe) && rs.getString("Statut").equals("admin")){
                    a = true;
                }
            }
        } catch (SQLException e) {
            System.out.print("L'erreur est" + e);
        }       
        return a;
    }
    
    public boolean ModificationUitlisateur1(){
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("UPDATE utilisateur SET Nom = ?, Prenom = ?, Num_telephone = ?, Sexe = ?, Statut = ?, Mdp = ? WHERE Nom = ?");
            ps.setString(1, Nom);
            ps.setString(2, Prenom);
            ps.setString(3, Num_telephone);
            ps.setString(4, Sexe);
            ps.setString(5, Statut);
            ps.setString(6, Mot_de_passe);
            ps.setString(7, nnom);
            ps.executeUpdate();
            con.close();
            ps.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est" + e);
        }
        return a;
    }
    
    public boolean SupprimerUtilisateur(){
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps =con.prepareStatement("DELETE FROM utilisateur WHERE Nom = ?");
            ps.setString(1, Nom);
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est" + e);
        }
        return a;
    }
    
    public void AfficherUtilisateur(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM utilisateur");
            
            
            int i = 0;
            while(rs.next()){
                String nom = rs.getString("Nom");
                String tel = rs.getString("Num_telephone");
                String sexe = rs.getString("Sexe");
                String statut = rs.getString("Statut");
                
            }
        } catch (SQLException e) {
        }
    }
    
    public void remplirCombobox(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            st =con.createStatement();
            rs = st.executeQuery("SELECT Nom FROM utilisateur");
            
            while(rs.next()){
                
            }
        } catch (SQLException e) {
        }
    }
}
