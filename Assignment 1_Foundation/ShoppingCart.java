import java.util.ArrayList;

class Item {
    String name;
    double price;

    Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class ShoppingCart {
    public static void main(String[] args) {
        ArrayList<Item> cart = new ArrayList<>();

        
        cart.add(new Item("Laptop", 45000));
        cart.add(new Item("Mouse", 500));
        cart.add(new Item("Keyboard", 1200));

        System.out.println("--- Cart Items ---");
        double totalPrice = 0;

        for (Item item : cart) {
            System.out.println(item.name + " - Rs. " + item.price);
            totalPrice += item.price;
        }

        System.out.println("-------------------");
        System.out.println("Total Amount: Rs. " + totalPrice);
    }
}