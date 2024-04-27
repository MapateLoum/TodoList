package samaRouk;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class connexion 
{ 
    public static int user_id=0;

    public static void connection()

    throws SQLException 
{
    Scanner scanner = new Scanner(System.in);
   
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TodoList", "root", "Papaloum1613")) 
    {
   
        
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

        System.out.println("\u001B[95m" + "Veuillez donner votre motde passe : " + "\u001B[0m");
        String motDePasse=scanner.nextLine();

        Statement stmt = con.createStatement();
        String liste = "select user_id,identifiant,mot_de_passe from users ORDER by identifiant,mot_de_passe";
        ResultSet rs = stmt.executeQuery(liste);
        boolean identifiantCorrect = false;
        boolean motDePasseCorrect = false;
        while (rs.next()) 
        {
            String dbIdentifiant = rs.getString("identifiant");
            String dbMotDePasse = rs.getString("mot_de_passe");

            if (identifiant.equals(dbIdentifiant) && motDePasse.equals(dbMotDePasse)) 
            {
                identifiantCorrect = true;
                motDePasseCorrect = true;
                break;
            }
        }

        // Pour récupérer l'id de celui qui est connecter
        
        String selectQuery = "SELECT user_id FROM users WHERE identifiant = ? AND mot_de_passe = ?";
        try (PreparedStatement pstmt = con.prepareStatement(selectQuery)) {
            pstmt.setString(1, identifiant);
            pstmt.setString(2, motDePasse);
            ResultSet rse = pstmt.executeQuery();

            if (rse.next()) 
            {
               int USER_ID = rse.getInt("user_id"); // Récupération de user_id
                
                user_id=USER_ID;
            } 
         
        }

       
    if (identifiantCorrect && motDePasseCorrect ) 
    {
        System.out.println("\u001B[95m" + "Vous êtes connecté !" + "\u001B[0m");

        System.out.println("\u001B[95m" + "Bienvenue sur la plateforme. " + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Choix      " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Opérations                 " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   1          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Créer une tâche            " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   2          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Supprimer une tâche        " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   3          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Modifier informations tâche" + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   4          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Afficher toutesles tâches  " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");

        System.out.println("\u001B[95m" + "Veuiller faire votre choix : " + "\u001B[0m");
        int choix = scanner.nextInt();
        do {
            
            switch (choix) 
            {
                case 1:taches.creerTache();
                break;

                case 2:taches.SuppTache();
                break;

                case 3:taches.ModifierTache();
                break;

                case 4:taches.afficherTaches();
                break;
            
                default:System.out.println("\u001B[95m" + "Vous n'avez pas fait un choix valide." + "\u001B[0m");
                break;
            }
        } while (choix<1 || choix>4);
    } 
    else 
    {
        System.out.println("\u001B[95m" + "Identifiant ou mot de passe incorrect. Veuillez réessayer." + "\u001B[0m");
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
    }scanner.close();
}     

    public static void admin()
    throws SQLException 
    {
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("\u001B[95m" + "Veuiller donner votre mot de passe : " + "\u001B[0m");
        String motDePasse = scanner.nextLine();
    
      if ("loumpapamapate@gmail.com".equals(identifiant) && "Papamapateloum2003".equals(motDePasse)) 
    {
        System.out.println("\u001B[95m" + "Bienvenue sur la plateforme de l'administrateur. " + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Choix      " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Opérations                 " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   1          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Créer compte User          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   2          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Supprimer compte User      " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   3          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Modifier compte User       " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   4          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Afficher compte(s) Users   " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+------------------------------+" + "\u001B[0m");
    
    
    System.out.println("\u001B[95m" + "Que voulez-vous cher administrateur ? " + "\u001B[0m");
    int choix=scanner.nextInt(); ;

    do 
    {
    
        switch (choix) 
        {
            case 1:  users.creationCompteUsers();
                break;
            case 2:  users.SuppUsers();
                break;
            case 3:  users.ModifierCompteUser();
                break;
            case 4:  users.afficherAllUsers();
                break;
        
            default: 
            System.out.println("\u001B[95m" + "Vous n'avez pas fait un choix valide !!" + "\u001B[0m");
            break;
        }
        
       scanner.close();
    } while (choix>4 || choix<1);
      
    }else
    System.out.println("\u001B[95m" + "Identifiant ou mot de passe incorrecte !! Réessayer." + "\u001B[0m");

    }
}
