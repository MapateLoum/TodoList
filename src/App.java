import java.util.Scanner;

import samaRouk.connexion;
import samaRouk.users;

public class App {
    public static void main(String[] args) throws Exception 
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println( "\u001B[96m" + "+--------------+----------------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Choix      " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Opérations                           " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+----------------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   1          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Se connecter                         " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+----------------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   2          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "Créer,Modifier,Supprimer Compte (Admin) " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+----------------------------------------+" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   3          " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m"+"\u001B[95m" + "   Afficher tous les comptes            " + "\u001B[0m"+"\u001B[96m" + "|" + "\u001B[0m");
        System.out.println( "\u001B[96m" + "+--------------+----------------------------------------+" + "\u001B[0m");
        

        System.out.println("\u001B[95m" + "Veuiller faire votre choix : " + "\u001B[0m");
        int choix;

        do {
            choix=scanner.nextInt();
        

        switch (choix) {
            case 1: connexion.connection(); 
            break;

            case 2: connexion.admin(); 
            break;

            case 3: users.afficherAllUsers(); 
            break;
        
            default:System.out.println("\u001B[95m" + "Vous n'avez pas fait un choix valide !!" + "\u001B[0m");

                break;
        }
    } while (choix<1 || choix >2);

  
     scanner.close();   
    }
}
