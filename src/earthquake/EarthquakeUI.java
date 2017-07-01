package earthquake;

import java.util.Scanner;

/**
 *
 * @author Kevin Yang
 *
 */

public class EarthquakeUI 
{
   public static void main (String args[]) throws Exception 
   {
      Scanner scanner = new Scanner(System.in);
      int choice;
      double chance = 32;
      boolean exit = false;
      System.out.println("=============================");
      System.out.println("|   EARTHQUAKE PREDICTION   |");
      System.out.println("=============================");
      System.out.println("| Options:                  |");
      System.out.println("|      1. Get predictions   |");
      System.out.println("|      2. Exit              |");
      System.out.println("=============================");
      
      do
      {
         System.out.print("Select option: ");
         choice = scanner.nextInt();
         scanner.nextLine();
         switch (choice) {
         case 1:
            System.out.println("Option 1 selected");
            System.out.print("Please enter the address or coordinates: ");
            String address = scanner.nextLine();
            System.out.println("Likelyhood of earthquake in " + address + " is " + chance + "%");
            address = address.replaceAll("\\s", "+");
            Location location = new Location(address);
            System.out.println(address);
            location.printDocument(location.getLocationXML(), System.out);
         break;
         case 2:
            System.out.println("Exit selected. Goodbye.");
            exit=true;
            break;
         default:
            System.out.println("Invalid selection");
            break;
         }
      } while(!exit);  
   }
}

