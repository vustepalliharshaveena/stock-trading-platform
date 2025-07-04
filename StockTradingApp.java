import java.util.*;
public class StockTradingApp 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        List<Stock> stocks = List.of(
            new Stock("TCS", "Tata Consultancy", 3600),
            new Stock("INFY", "Infosys", 1450),
            new Stock("RELI", "Reliance", 2500)
        );
        User user = new User("Investor", 100000); // ₹1L starting
        while (true) 
        {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) 
            {
                case 1:
                    System.out.println("\nAvailable Stocks:");
                    for (Stock stock : stocks) 
                    {
                        System.out.printf("%s (%s): ₹%.2f\n", stock.getName(), stock.getSymbol(), stock.getPrice());
                    }
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = sc.next().toUpperCase();
                    Stock buyStock = findStock(stocks, buySymbol);
                    if (buyStock != null) 
                    {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        boolean success = user.buyStock(buyStock, qty);
                        System.out.println(success ? "Stock bought successfully." : "Not enough balance.");
                    }
                    else System.out.println("Stock not found.");
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = sc.next().toUpperCase();
                    Stock sellStock = findStock(stocks, sellSymbol);
                    if (sellStock != null) 
                    {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        boolean success = user.sellStock(sellStock, qty);
                        System.out.println(success ? "Stock sold successfully." : "You don’t own enough shares.");
                    } 
                    else System.out.println("Stock not found.");
                    break;
                case 4:
                    user.viewPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting platform...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    public static Stock findStock(List<Stock> stocks, String symbol) 
    {
        for (Stock s : stocks) 
        {
            if (s.getSymbol().equalsIgnoreCase(symbol)) return s;
        }
        return null;
    }
}
