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
public class fonctAdherent {
    Connection con = null;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    public boolean a = false;
    public boolean b = false;
    
    public String NomAdherent;
    public String telAdherent;
    public String AdresseAdherent;
    public String LivreAdherent;
    public int RegulariteAdherent;
    
    public boolean EnregistrerAdherent(){
        a = false;
        RegulariteAdherent = 1;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("INSERT INTO adherent (Nom, Telephone, Adressemail, Livre, regularité) VALUES (?,?,?,?,?)");
            ps.setString(1, NomAdherent);
            ps.setString(2, telAdherent);
            ps.setString(3, AdresseAdherent);
            ps.setString(4, LivreAdherent);
            ps.setInt(5, RegulariteAdherent);
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est:" + e);
        }
        return a;
    }
    
    public void GenererRegulariteAvecEnregistrement(){
        a = false;
        try {
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", ""); 
           st = con.createStatement();
           rs = st.executeQuery("SELECT Nom, regularité FROM adherent");
           while(rs.next()){
                if (rs.getObject("Nom").equals(NomAdherent)){
                    a = true;
                    RegulariteAdherent = rs.getInt("regularité");
                }
           }
           st.close();
           rs.close();
           con.close();
           if (a == false){
               EnregistrerAdherent();
           }
           else{
               RegulariteAdherent ++;
               a = false;
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
               ps = con.prepareStatement("UPDATE adherent SET regularité = ? WHERE Nom = ?");
               ps.setInt(1, RegulariteAdherent);
               ps.setString(2, NomAdherent);
               ps.executeUpdate(); 
               ps.close();
               st.close();
               rs.close();
               con.close();
               a = true;
           }
        } catch (SQLException e) {
            System.out.print("L'erreur est:" + e);
        }
    }
    
    public boolean supprimerAdherent(){
        a = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("DELETE FROM adherent WHERE Nom = ?");
            ps.setString(1, NomAdherent);
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est:" + e);
        }
        return a;
    }
}
