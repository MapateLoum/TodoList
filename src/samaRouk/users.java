package samaRouk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class users 
{
 public static void creationCompteUsers()
 throws SQLException 
    {
        Scanner scanner = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoList?useUnicode=true&characterEncoding=utf8", "root", "Papaloum1613")) 
        {


            System.out.println("\u001B[95m" + "Veuillez donner le prénom du propriétaire : " + "\u001B[0m");
            String prenom = scanner.nextLine();
            System.out.println("\u001B[95m" + "Veuillez donner le nom du propriétaire : " + "\u001B[0m");
            String nom = scanner.nextLine();
        String identifiant;
        do 
        {
            System.out.println("\u001B[95m" + "Veuillez saisir votre identifiant (adresse e-mail) : " + "\u001B[0m");

            identifiant = scanner.nextLine();

            if (identifiant.length() < 10 || !identifiant.substring(identifiant.length() - 10).equals("@gmail.com")) 
            {
                System.out.println("\u001B[95m" + "L'adresse e-mail n'est pas valide. Veuillez saisir une adresse e-mail valide." + "\u001B[0m");

            }
        } while (identifiant.length() < 10 || !identifiant.substring(identifiant.length() - 10).equals("@gmail.com"));
System.out.println();
        System.out.println("\u001B[95m" + "Entrez votre mot de passe : " + "\u001B[0m");

        String motDePasse = scanner.nextLine();
    
        System.out.println("\u001B[95m" + "Confirmez votre mot de passe : " + "\u001B[0m");

        String confirmationMotDePasse = scanner.nextLine();
        
        while (!motDePasse.equals(confirmationMotDePasse)) {
            System.out.println("\u001B[95m" + "Les mots de passe ne correspondent pas. Veuillez réessayer." + "\u001B[0m");

            System.out.println("\u001B[95m" + "Entrez votre mot de passe :" + "\u001B[0m");

            motDePasse = scanner.nextLine();
            
            System.out.println("\u001B[95m" + "Confirmez votre mot de passe : " + "\u001B[0m");

            confirmationMotDePasse = scanner.nextLine();
        }
        
        System.out.println("\u001B[95m" + "Mot de passe confirmé !" + "\u001B[0m");

            

            String sql = "INSERT INTO users(nom,prenom,identifiant,mot_de_passe) VALUES(?,?,?,?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) 
            {
                
                pstmt.setString(1, nom);
                pstmt.setString(2, prenom);
                pstmt.setString(3,identifiant);
                pstmt.setString(4, motDePasse);
                
                
          

                pstmt.executeUpdate();
System.out.println();
                System.out.println("\u001B[95m" + "Compte users créé avec succés." + "\u001B[0m");

            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            scanner.close();
        }
    }


public static void afficherAllUsers()

    throws SQLException 
{
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoList", "root", "Papaloum1613")) 
    {
    Statement stmt = con.createStatement();
    String liste = "select user_id,prenom,nom,identifiant from users ORDER BY prenom,nom;";

    ResultSet rs = stmt.executeQuery(liste);

    System.out.println("+------------+---------------------------+------------+------------------------------------+");
    System.out.println("| Users ID   | Prénom                    | Nom        | Identifiant                        |");
    System.out.println("+------------+---------------------------+------------+------------------------------------+");
  
    while (rs.next()) 
    {
     String user_id = rs.getString("user_id");
     String prenom = rs.getString("prenom");
     String nom = rs.getString("nom");
     String identifiant = rs.getString("identifiant");
     
     
    
      String ligne = String.format("| %-10s |%-26s | %-10s |%-36s|",user_id, prenom, nom,identifiant);
     System.out.println(ligne);
     System.out.println("+------------+---------------------------+------------+------------------------------------+");
    
    }
      
    if (con != null) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
    
} 


public static void SuppUsers()

    throws SQLException 
{
   Scanner scanner = new Scanner(System.in);
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoList?useUnicode=true&characterEncoding=utf8", "root", "Papaloum1613")) 
    {

        Statement stmt = con.createStatement();
        String liste = "select user_id,prenom,nom,identifiant from users ORDER BY prenom,nom;";
    
        ResultSet rs = stmt.executeQuery(liste);
    
        System.out.println("+------------+---------------------------+------------+------------------------------------+");
        System.out.println("| Users ID   | Prénom                    | Nom        | Identifiant                        |");
        System.out.println("+------------+---------------------------+------------+------------------------------------+");
      
        while (rs.next()) 
        {
         int id_medecin = rs.getInt("user_id");
         String prenom = rs.getString("prenom");
         String nom = rs.getString("nom");
         String identifiant = rs.getString("identifiant");
         
         
        
          String ligne = String.format("| %-10s |%-26s | %-10s |%-36s|",id_medecin, prenom, nom,identifiant);
         System.out.println(ligne);
         System.out.println("+------------+---------------------------+------------+------------------------------------+");
        
        }


        System.out.println("\u001B[95m" + "Veuiller donner le numéro ID du compte User à supprimer : " + "\u001B[0m");

        int numID = scanner.nextInt();


       String sql = "DELETE FROM users where user_id=? ;";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) 
        {
            
            pstmt.setInt(1,numID);
            
            pstmt.executeUpdate();

        }   
        System.out.println("\u001B[95m" + "Compte user supprimé avec succés." + "\u001B[0m");

        if (con != null) 
        {
            try 
            {
                con.close();
            } catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }

    } 
    scanner.close();
}


public static void ModifierCompteUser()

    throws SQLException 
{
        Scanner scanner = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoList", "root", "Papaloum1613")) 
        {
        Statement stmt = con.createStatement();
        String liste = "select user_id,prenom,nom,identifiant from users ORDER BY prenom,nom;";
    
        ResultSet rs = stmt.executeQuery(liste);
    
        System.out.println("+------------+---------------------------+------------+------------------------------------+");
        System.out.println("| Users ID   | Prénom                    | Nom        | Identifiant                        |");
        System.out.println("+------------+---------------------------+------------+------------------------------------+");
      
        while (rs.next()) 
        {
         String id_medecin = rs.getString("user_id");
         String prenom = rs.getString("prenom");
         String nom = rs.getString("nom");
         String identifiant = rs.getString("identifiant");
         
         
        
          String ligne = String.format("| %-10s |%-26s | %-10s |%-36s|",id_medecin, prenom, nom,identifiant);
         System.out.println(ligne);
         System.out.println("+------------+---------------------------+------------+------------------------------------+");
        
        }
        System.out.println("\u001B[95m" + "Veuiller donner le NumID du compte a modifier : " + "\u001B[0m");

        int numID=scanner.nextInt();
        scanner.nextLine();

        String identifiant;
        do 
        {
            System.out.println("\u001B[95m" + "Veuillez saisir le nouveau identifiant du compte (adresse e-mail) : " + "\u001B[0m");

            identifiant = scanner.nextLine();

            if (identifiant.length() < 10 || !identifiant.substring(identifiant.length() - 10).equals("@gmail.com")) 
            {
                System.out.println("\u001B[95m" + "L'adresse e-mail n'est pas valide. Veuillez saisir une adresse e-mail valide." + "\u001B[0m");

            }
        } while (identifiant.length() < 10 || !identifiant.substring(identifiant.length() - 10).equals("@gmail.com"));
            System.out.println();

            System.out.println("\u001B[95m" + "Entrez votre nouveau mot de passe : " + "\u001B[0m");

            String motDePasse = scanner.nextLine();
        
            System.out.println("\u001B[95m" + "Confirmez votre mot de passe : " + "\u001B[0m");

            String confirmationMotDePasse = scanner.nextLine();
            
            while (!motDePasse.equals(confirmationMotDePasse)) 
            {
                System.out.println("\u001B[95m" + "Les mots de passe ne correspondent pas. Veuillez réessayer." + "\u001B[0m");

                System.out.println("\u001B[95m" + "Entrez votre mot de passe :  " + "\u001B[0m");

                motDePasse = scanner.nextLine();      
                System.out.println("\u001B[95m" + "Confirmez votre mot de passe : " + "\u001B[0m");
                confirmationMotDePasse = scanner.nextLine();
            }

            String sql = "update users set identifiant=?,mot_de_passe=? where user_id=?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) 
            {
                
                pstmt.setString(1, identifiant);
                pstmt.setString(2,motDePasse);
                pstmt.setInt(3,numID);
                
    
    
                pstmt.executeUpdate();
            System.out.println("\u001B[95m" + "Compte modfié avec succés." + "\u001B[0m");
        if (con != null) 
        {
            try 
            {
                con.close();
            } catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        scanner.close();
        }
    }


}}




   
   

    

