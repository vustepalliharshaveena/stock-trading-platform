import java.util.HashMap;
public class User 
{
    private String name;
    private double balance;
    private HashMap<String, Integer> portfolio;
    public User(String name, double balance) 
    {
        this.name = name;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }
    public boolean buyStock(Stock stock, int quantity) 
    {
        double cost = stock.getPrice() * quantity;
        if (cost > balance) return false;
        balance -= cost;
        portfolio.put(stock.getSymbol(), portfolio.getOrDefault(stock.getSymbol(), 0) + quantity);
        return true;
    }
    public boolean sellStock(Stock stock, int quantity) 
    {
        int owned = portfolio.getOrDefault(stock.getSymbol(), 0);
        if (quantity > owned) return false;
        balance += stock.getPrice() * quantity;
        if (quantity == owned) portfolio.remove(stock.getSymbol());
        else portfolio.put(stock.getSymbol(), owned - quantity);
        return true;
    }
    public void viewPortfolio() 
    {
        System.out.println("Portfolio:");
        for (String symbol : portfolio.keySet()) 
        {
            System.out.println(symbol + " → " + portfolio.get(symbol) + " shares");
        }
        System.out.println("Available Balance: ₹" + balance);
    }
    public double getBalance() 
    {
        return balance;
    }
}
