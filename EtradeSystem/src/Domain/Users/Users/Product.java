package Domain.Users.Users;


public class Product {
    private int price;
    private int quantity;

    public Product(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }


    //@Override
    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void reduceQuantity(int quantity){
        this.quantity -= quantity;
    }
    public void increaseQuantity(int quantity){
        this.quantity += quantity;
    }
}
