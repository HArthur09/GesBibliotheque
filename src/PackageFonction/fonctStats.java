/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackageFonction;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.*;
/**
 *
 * @author admin
 */
public class fonctStats{
    public Connection con = null;
    Statement st;
    public ResultSet rs, rss;
    PreparedStatement ps;
    public boolean a = false;
    public Date depart;
    public Date arrivé;
    public Date n;
    public LocalDate dates;
    public String nom;
    public String NomAdherent;
    public String LivreAdherent;
    public String NomLivre;
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    String format;
    fonctAdherent adherent = new fonctAdherent();
    fonctEmprunt emprunt = new fonctEmprunt();
    //Stats des adherent
    public void AjoutAdherent_pourStats_livre(){
        try {
            dates = LocalDate.now();
            n = java.sql.Date.valueOf(dates);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root","");
            ps = con.prepareStatement("INSERT INTO stats_livre (Nom_livre, Date, Nom_Adherent) VALUES (?,?,?)");
            System.out.print(NomAdherent);
            System.out.print(LivreAdherent);
            ps.setString(1, LivreAdherent);
            ps.setDate(2, n);
            ps.setString(3, NomAdherent);
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est :" + e);
        }
    }
    
    public void AjoutAdherent_pourStats_adherent(){
        try {
            dates = LocalDate.now();
            System.out.print(dates);
            n = java.sql.Date.valueOf(dates);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root","");
            ps = con.prepareStatement("INSERT INTO stats_adherent (Nom_Adherent, Date) VALUES (?,?)");
            ps.setString(1, NomAdherent);
            ps.setDate(2, n);
            ps.executeUpdate();
            ps.close();
            con.close();
            a = true;
        } catch (SQLException e) {
            System.out.print("L'erreur est :" + e);
        }
    }
    
    public void recuperer_nom(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT Nom_Livre from livre WHERE Date_enregistrement between ? and ? group by Nom_Livre");
            ps.setDate(1, depart);
            ps.setDate(2, arrivé);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.print("L'erreur est :" + e);
        }
    }
    
    public void AdherentFrequent(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("select Nom, count(Nom_adherent) as Frequence from adherent inner join stats_adherent on Nom_adherent = Nom where Date between ? and ? group by Nom asc");
            ps.setDate(1, depart);
            ps.setDate(2, arrivé);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.print("L'erreur est :" + e);
        }
    }
    
    public void AdhrentQuiEmpruntLePlus(){
        try {
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT Nom_Client as Client, Count(Nom_Client) as Nombre_emprunts from emprunts WHERE Date_emprunt between ? and ? GROUP BY Nom_Client ORDER BY COUNT(Nom_Client) desc");
            ps.setDate(1, depart);
            ps.setDate(2, arrivé);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.print("L'erreur est ici celui qui emprunt le plus:" + e);
        }
    }
    
    public void AdhrentQuiEmpruntLeMoins(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT Nom_Client as Client, Count(Nom_Client) as Nombre_emprunts from emprunts WHERE Date_emprunt between ? and ? GROUP BY Nom_Client ORDER BY COUNT(Nom_Client) asc");
            ps.setDate(1, depart);
            ps.setDate(2, arrivé);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.print("L'erreur est :" + e);
        }
    }
    
    public void LivreLePlusEmprunte(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT Nom_livre, count(Nom_livre) as Quantité_emprunté from emprunts WHERE Date_emprunt between ? and ? GROUP BY Nom_livre ORDER BY COUNT(Nom_livre) desc");
            ps.setDate(1, depart);
            ps.setDate(2, arrivé);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.print("L'erreur est :" + e);
        }
    }
    
    public void LivreLeMoinsEmprunte(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT Nom_livre as ?, COUNT(Nom_livre) as ? from emprunts WHERE Date_emprunt between ? and ? GROUP BY Nom_livre ORDER BY COUNT(Nom_livre) asc");
            ps.setString(1, "Nom du livre");
            ps.setString(2, "Nombre de fois emprunté");
            ps.setDate(3, depart);
            ps.setDate(4, arrivé);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.print("L'erreur est :" + e);
        }
    }
    
    public void LivreLePlusLuALaBiblio(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT Nom_livre, count(Nom_livre) from stats_livre WHERE Date between ? and ? GROUP BY Nom_livre ORDER BY COUNT(Nom_livre) desc");
            ps.setDate(1, depart);
            ps.setDate(2, arrivé);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.print("L'erreur est :" + e);
        }
    }
    
    public void statsGeneralsLivres(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            st = con.createStatement();
            rss = st.executeQuery("SELECT Nom_Livre, Date_enregistrement, Quantité, QuantiteDisponible from livre");           
        } catch (SQLException e) {
            System.out.print("L'erreur est ici:" + e);
        }
    }
    
    public void statsGeneralsAdherent(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT Nom, adherent.Telephone, Adressemail,regularité as Niveau_regularité, COUNT(Nom_Client) as Nombres_emprunts FROM adherent INNER JOIN emprunts on Nom_Client = Nom WHERE Date_emprunt between ? and ? GROUP BY Nom_Client");
            ps.setDate(1, depart);
            ps.setDate(2, arrivé);
            rs = ps.executeQuery();
            //ps.close();
            //con.close();
        }catch (SQLException e){
            System.out.print("L'erreur est la:" + e);
        }
    }
    
    public void nombre_de_fois_emprunté(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT COUNT(Nom_livre) from emprunts WHERE Nom_livre = ? and Date_emprunt between ? and ?");
            ps.setString(1, NomLivre);
            ps.setDate(2, depart);
            ps.setDate(3, arrivé);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.print("L'erreur est par ici:" + e);
        }
    }
    
    public void nombre_de_fois_utilisé(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT COUNT(Nom_livre) from stats_livre WHERE Nom_livre = ? AND Date between ? and ?");
            ps.setString(1, NomLivre);
            ps.setDate(2, depart);
            ps.setDate(3, arrivé);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.print("L'erreur est a ce niveau:" + e);
        }
    }
    
    public void Quantité_en_emprunt(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            ps = con.prepareStatement("SELECT COUNT(Nom_livre) from emprunts WHERE Date_emprunt between ? and ? AND Nom_livre = ? AND Statut = ?");
            ps.setDate(1, depart);
            ps.setDate(2, arrivé);
            ps.setString(3, NomLivre);
            ps.setString(4, "Emprunté");
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.print("L'erreur est a ce niveau:" + e);
        }
    }
}
