package samaRouk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class taches 
{
    public static void creerTache()
 throws SQLException 
    {
        Scanner scanner = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoList?useUnicode=true&characterEncoding=utf8", "root", "Papaloum1613")) 
        {

        int id_users = connexion.user_id;
        System.out.println("\u001B[95m" + "Veuiller donner le nom de la tâche à créer : " + "\u001B[0m");
        String nom_tache= scanner.nextLine();
        System.out.println("\u001B[95m" + "Veuiller donner la description pour cette tâche : " + "\u001B[0m");
        String description = scanner.nextLine();
            
            String sql = "INSERT INTO taches(user_id,nom_tache,description) VALUES(?,?,?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) 
            {
                
                pstmt.setInt(1, id_users);
                pstmt.setString(2, nom_tache);
                pstmt.setString(3,description);

                pstmt.executeUpdate();

                System.out.println("\u001B[95m" + "Tâche créé avec succés." + "\u001B[0m");
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
 
    public static void afficherTaches()

    throws SQLException 
{
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoList", "root", "Papaloum1613")) 
    {
    Statement stmt = con.createStatement();
    String liste = "select tache_id,user_id,nom_tache,description from taches ORDER BY tache_id;";

    ResultSet rs = stmt.executeQuery(liste);

    System.out.println("+------------+---------------------------+------------+------------------------------------------------------+");
    System.out.println("| Tache ID   | Nom de la tâche           | User ID    | Description                                          |");
    System.out.println("+------------+---------------------------+------------+------------------------------------------------------+");
  
    while (rs.next()) 
    {
     String user_id = rs.getString("tache_id");
     String nom_tache = rs.getString("user_id");
     String id = rs.getString("nom_tache");
     String description = rs.getString("description");
     
         
     String ligne = String.format("| %-10s |%-26s | %-10s |%-54s|",user_id, nom_tache,id,description);
     System.out.println(ligne);
     System.out.println("+------------+---------------------------+------------+------------------------------------------------------+");
    
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

    public static void SuppTache()

throws SQLException 
{
Scanner scanner = new Scanner(System.in);

try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoList?useUnicode=true&characterEncoding=utf8", "root", "Papaloum1613")) 
{

    int id_users = connexion.user_id;
    Statement stmt = con.createStatement();
    String liste = "select tache_id,user_id,nom_tache,description from taches ORDER BY tache_id;";

    ResultSet rs = stmt.executeQuery(liste);

    System.out.println("+------------+---------------------------+------------+------------------------------------------------------+");
    System.out.println("| Tache ID   | Nom de la tâche           | User ID    | Description                                          |");
    System.out.println("+------------+---------------------------+------------+------------------------------------------------------+");
  
    while (rs.next()) 
    {
     String tache_id = rs.getString("tache_id");
     String nom_tache = rs.getString("nom_tache");
     String id = rs.getString("user_id");
     String description = rs.getString("description");
        
     String ligne = String.format("| %-10s |%-26s | %-10s |%-54s|",tache_id, nom_tache,id,description);
     System.out.println(ligne);
     System.out.println("+------------+---------------------------+------------+------------------------------------------------------+");
    
    }

    System.out.println("\u001B[95m" + "Veuiller donner le numéro ID de la tâche à supprimer : " + "\u001B[0m");
    int tache_id = scanner.nextInt();

   int userId = 0;

String query = "SELECT user_id FROM taches WHERE tache_id = ?";
try (PreparedStatement statement = con.prepareStatement(query)) 
{
    statement.setInt(1, tache_id); // Remplacer le paramètre de requête par l'identifiant de la tâche fourni par l'utilisateur
    
    try (ResultSet resultSet = statement.executeQuery()) 
    {
        if (resultSet.next()) {
            userId = resultSet.getInt("user_id");
        }          
    }
}
//System.out.println(userId);
   

 if (userId==id_users) 
    {
    String sql = "DELETE FROM taches where user_id=? and tache_id=? ;";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) 
    {
        
        pstmt.setInt(1,id_users);
        pstmt.setInt(2,tache_id);
        
        pstmt.executeUpdate();

    } 
    System.out.println("\u001B[95m" + "Tache supprimée avec succés." + "\u001B[0m");
    }
    else
    {
       System.out.println("\u001B[95m" + "Vous n'êtes pas autorisé à supprimer cette tache." + "\u001B[0m");
    }
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

    public static void ModifierTache()

throws SQLException 
{
Scanner scanner = new Scanner(System.in);

try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoList?useUnicode=true&characterEncoding=utf8", "root", "Papaloum1613")) 
{

    int id_users = connexion.user_id;
    Statement stmt = con.createStatement();
    String liste = "select tache_id,user_id,nom_tache,description from taches ORDER BY tache_id;";

    ResultSet rs = stmt.executeQuery(liste);

    System.out.println("+------------+---------------------------+------------+------------------------------------------------------+");
    System.out.println("| Tache ID   | Nom de la tâche           | User ID    | Description                                          |");
    System.out.println("+------------+---------------------------+------------+------------------------------------------------------+");
  
    while (rs.next()) 
    {
     String tache_id = rs.getString("tache_id");
     String nom_tache = rs.getString("nom_tache");
     String id = rs.getString("user_id");
     String description = rs.getString("description");
        
     String ligne = String.format("| %-10s |%-26s | %-10s |%-54s|",tache_id, nom_tache,id,description);
     System.out.println(ligne);
     System.out.println("+------------+---------------------------+------------+------------------------------------------------------+");
    
    }

    System.out.println("\u001B[95m" + "Veuiller donner le numéro ID de la tâche à modifier : " + "\u001B[0m");
    int tache_id = scanner.nextInt();

   int userId = 0;

String query = "SELECT user_id FROM taches WHERE tache_id = ?";
try (PreparedStatement statement = con.prepareStatement(query)) 
{
    statement.setInt(1, tache_id); // Remplacer le paramètre de requête par l'identifiant de la tâche fourni par l'utilisateur
    
    try (ResultSet resultSet = statement.executeQuery()) 
    {
        if (resultSet.next()) {
            userId = resultSet.getInt("user_id");
        }          
    }
}
//System.out.println(userId);
   

 if (userId==id_users) 
    {
    System.out.println("\u001B[95m" + "Veuiller donner le nouveau nom de la tache : " + "\u001B[0m");
    String Nnom_tache=scanner.nextLine();
    System.out.println("\u001B[95m" + "Donner sa nouvelle description : " + "\u001B[0m");
    String Ndescription = scanner.nextLine();
    String sql = "UPDATE taches SET nom_tache=?, description=? WHERE user_id=? AND tache_id=?;";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) 
    {
        
        pstmt.setInt(3,id_users);
        pstmt.setInt(4,tache_id);
        pstmt.setString(1,Nnom_tache);
        pstmt.setString(2,Ndescription);
        
        pstmt.executeUpdate();

    } 
    System.out.println("\u001B[95m" + "Tache modifiée avec succés." + "\u001B[0m");
    }
    else
    {
       System.out.println("\u001B[95m" + "Vous n'êtes pas autorisé à modifier cette tache." + "\u001B[0m");
    }
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

}
