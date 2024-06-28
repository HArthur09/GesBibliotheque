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
public class fonctEmprunt {
    Connection con = null;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    public boolean a = false;
    public String NomAdherent;
    public String TelAdherent;
    public String AdresseEmail;
    public String NomLivre;
    public Date DateEmpruntL;
    public Date DelaiRemiseL;
    public int Quantité;
    public String Statut;
    public int compte = 0;
    public int ded = 0;
    
    
    public boolean ControleEligibilité(){
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT regularité FROM adherent WHERE Nom = ?");
            ps.setString(1, NomAdherent);
            rs = ps.executeQuery();
            while (rs.next()){
                if (rs.getInt("regularité") >= 5){
                a = true;
                }
                else{
                    a = false;
                }
            }   
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.print("L'erruer est controle eligibilité:" + e);
        }
        return a;
    }
    
    public boolean ControleNombreEmprunt(){
        a = false;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT Nom_Client, Statut FROM emprunts");
            compte = 0;
            while (rs.next()){
                if (rs.getString("Nom_Client").equals(NomAdherent) && rs.getString("Statut").equals("Emprunté")){
                    compte ++;
                }
            }
            if (compte >= 3){
                a = true;
            }
            else{
                a = false;
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.print("L'erreur se trouve ici controle Nombre emprunt:" + e);
        }
        return a;
    }
    
    public boolean ControleQuantitéDisponible(){
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT QuantiteDisponible FROM livre WHERE Nom_Livre = ?");
            ps.setString(1, NomLivre);
            rs = ps.executeQuery();
            while (rs.next()){
                ded = rs.getInt("QuantiteDisponible");
                if (rs.getInt("QuantiteDisponible") >= Quantité){
                    a = true;
                }
                else{
                    a = false;
                }
                }
        } catch (SQLException e) {
            System.out.print("L'erreur controle Quantité:" + e);
        }
        return a;
    }
    
    public boolean enregistrerEmprunt(){
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("INSERT INTO emprunts (Nom_client, Telephone, Nom_livre, Quantite, Date_emprunt, Date_remise, Statut) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, NomAdherent);
            ps.setString(2, TelAdherent);
            ps.setString(3, NomLivre);
            ps.setInt(4, Quantité);
            ps.setDate(5, DateEmpruntL);
            ps.setDate(6, DelaiRemiseL);
            ps.setString(7, "Emprunté");
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erruer est enregistrementEmprunt:" + e);
        }
        
        return a;
    }
    
    public boolean deductionQuantite(){
        a = false;
        Quantité = ded - Quantité;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("UPDATE livre SET QuantiteDisponible = ? WHERE Nom_Livre = ?");
            ps.setInt(1, Quantité);
            ps.setString(2, NomLivre);
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est deductionQuantité:" + e);
        }
        return a;
    }
    
    public boolean RecupererEmprunt(){
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("UPDATE emprunts SET Statut = ? WHERE Nom_Client = ? AND Nom_livre = ? AND Quantite = ?");
            ps.setString(1, "Recupéré");
            ps.setString(2, NomAdherent);
            ps.setString(3, NomLivre);
            ps.setInt(4, Quantité);
            ps.executeUpdate();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est recuperer quantité:" + e);
        }
        return a;
    }
    
    public boolean AugmenterQuantiteDisponible(){
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT QuantiteDisponible FROM livre WHERE Nom_Livre = ?");
            ps.setString(1, NomLivre);
            rs = ps.executeQuery();
            while (rs.next()){
                ded = rs.getInt("QuantiteDisponible");
            }
        } catch (SQLException e) {
            System.out.print("L'erreur controle Quantité:" + e);
        }
        
        Quantité = ded + Quantité;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("UPDATE livre SET QuantiteDisponible = ? WHERE Nom_Livre = ?");
            ps.setInt(1, Quantité);
            ps.setString(2, NomLivre);
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est augmenter quantité dispo:" + e);
        }
        return a;
    }
}
