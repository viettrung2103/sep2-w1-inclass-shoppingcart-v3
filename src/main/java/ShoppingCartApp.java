import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCartApp {

    static double totalCost = 0.0;

    public static double calculateCost(int amount, double pricePerItem) {
        double price = pricePerItem * amount;
        return price;
    }

    public static double getTotalCost(){
        return ShoppingCartApp.totalCost;
    }

    public static void main(String[] args) {
        System.out.println("Select a language: ");
        System.out.println("1.English");
        System.out.println("2.Finnish");
        System.out.println("3.Japanese");
        System.out.println("4.Swedish");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

//      select language for localization

        Locale locale;
        switch (choice){
            case 1:
                locale = new Locale("en", "US");
                break;
            case 2:
                locale = new Locale("fi","FI");
                break;
            case 3:
                locale = new Locale("ja","JP");
                break;
            case 4:
                locale = new Locale("sv","SE");
                break;
            default:
                System.out.println("Invalid language selection");
                locale = new Locale( "en","US");
        }

        ResourceBundle rb;
//        in case there is no resource bundle for the selected language, it will default to English
        try {
            rb = ResourceBundle.getBundle("messages",locale);
        } catch (Exception e){
            System.out.println("Invalid, selected language is not available, translate to English");
            rb = ResourceBundle.getBundle("messages",new Locale("en","US"));
        }

        // Ask user for item
//        System.out.print("Enter the number of kilometers to travel: ");
        System.out.println(rb.getString("numberOfItems"));
        int itemNumber = scanner.nextInt();
        for ( int i = 1; i <= itemNumber; i++){
            // Ask user for the price of the item
            System.out.print(MessageFormat.format(rb.getString("itemPrice"),String.format("%d",i)));
            double itemPrice = scanner.nextDouble();

            // Ask user for the amout of the item
            System.out.println(MessageFormat.format(rb.getString("itemAmount"),String.format("%d",i)));
            int itemAmount = scanner.nextInt();

            // Calculate total cost of the trip
            totalCost += calculateCost(itemAmount,itemPrice);

        }
        // Display the total cost
//        System.out.printf("The total cost of the trip is: %.2f euros\n", totalCost);
        String outputPrice = MessageFormat.format(rb.getString("totalCost"),String.format("%.2f", totalCost));
        System.out.printf(outputPrice);


        scanner.close();
    }
}