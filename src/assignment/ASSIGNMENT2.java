package assignment;
import java.util.*;

abstract class Product {
    private final int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        if (productId <= 0 || name == null || name.isBlank() || !Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException("Invalid product details.");
        }

        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void updateDetails(String name, double price) {
        if (name == null || name.isBlank() || !Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException("Invalid name or price.");
        }

        this.name = name;
        this.price = price;
    }

    public abstract String getCategory();

    protected abstract String getExtraDetails();

    @Override
    public String toString() {
        return "ID: " + productId
                + " | Category: " + getCategory()
                + " | Name: " + name
                + " | Price: Rs. " + String.format("%.2f", price)
                + " | " + getExtraDetails();
    }
}

class Clothes extends Product {
    private String size;
    private String color;

    public Clothes(int id, String name, double price, String size, String color) {
        super(id, name, price);
        this.size = requireText(size, "Size");
        this.color = requireText(color, "Color");
    }

    @Override
    public String getCategory() {
        return "Clothes";
    }

    @Override
    protected String getExtraDetails() {
        return "Size: " + size + " | Color: " + color;
    }

    public void updateExtraDetails(String size, String color) {
        this.size = requireText(size, "Size");
        this.color = requireText(color, "Color");
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
        return value;
    }
}

// Electronics product.
class Electronics extends Product {
    private String brand;
    private int warrantyMonths;

    public Electronics(int id, String name, double price, String brand, int warrantyMonths) {
        super(id, name, price);
        this.brand = requireText(brand, "Brand");
        setWarrantyMonths(warrantyMonths);
    }

    @Override
    public String getCategory() {
        return "Electronics";
    }

    @Override
    protected String getExtraDetails() {
        return "Brand: " + brand + " | Warranty: " + warrantyMonths + " months";
    }

    public void updateExtraDetails(String brand, int warrantyMonths) {
        this.brand = requireText(brand, "Brand");
        setWarrantyMonths(warrantyMonths);
    }

    private void setWarrantyMonths(int months) {
        if (months < 0) {
            throw new IllegalArgumentException("Warranty cannot be negative.");
        }
        this.warrantyMonths = months;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
        return value;
    }
}

// Toys product.
class Toys extends Product {
    private int recommendedAge;

    public Toys(int id, String name, double price, int recommendedAge) {
        super(id, name, price);
        setRecommendedAge(recommendedAge);
    }

    @Override
    public String getCategory() {
        return "Toys";
    }

    @Override
    protected String getExtraDetails() {
        return "Recommended Age: " + recommendedAge + "+ years";
    }

    public void updateExtraDetails(int recommendedAge) {
        setRecommendedAge(recommendedAge);
    }

    private void setRecommendedAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Recommended age cannot be negative.");
        }
        this.recommendedAge = age;
    }
}


class ShoppingCart {
    private final Map<Integer, Product> products = new TreeMap<>();

    public synchronized void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        if (products.containsKey(product.getProductId())) {
            throw new IllegalArgumentException("Product ID already exists in this cart.");
        }

        products.put(product.getProductId(), product);
        System.out.println("Product added successfully.");
    }

    public synchronized void removeProduct(int productId) {
        if (products.remove(productId) == null) {
            System.out.println("Product not found in your cart.");
        } else {
            System.out.println("Product removed successfully.");
        }
    }

    public synchronized Product findProduct(int productId) {
        return products.get(productId);
    }

    public synchronized void showCart() {
        if (products.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
            return;
        }

        System.out.println("\n========== YOUR SHOPPING CART ==========");

        double total = 0;

        for (Product product : products.values()) {
            System.out.println(product);
            total += product.getPrice();
        }

        System.out.printf("Total Amount: Rs. %.2f%n", total);
        System.out.println("========================================");
    }
}


public class ASSIGNMENT2 {
    private static final Scanner SCANNER = new Scanner(System.in);


    private static final Map<String, ShoppingCart> USER_CARTS = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== SHOPPING CART APPLICATION =====");
            System.out.println("1. Select User");
            System.out.println("2. Exit");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    selectUser();
                    break;

                case 2:
                    System.out.println("Thank you for shopping!");
                    SCANNER.close();
                    return;

                default:
                    System.out.println("Invalid menu choice.");
            }
        }
    }

    private static void selectUser() {
        String username = readText("Enter username: ");

        ShoppingCart cart = USER_CARTS.computeIfAbsent(username, key -> new ShoppingCart());

        System.out.println("Welcome, " + username + "!");

        while (true) {
            System.out.println("\n1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product");
            System.out.println("4. Show Cart");
            System.out.println("5. Switch User");
            System.out.println("6. Exit Application");

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1:
                        addProduct(cart);
                        break;

                    case 2:
                        cart.removeProduct(
                                readInt("Enter product ID: "));
                        break;

                    case 3:
                        updateProduct(cart);
                        break;

                    case 4:
                        cart.showCart();
                        break;

                    case 5:
                        return;

                    case 6:
                        System.out.println("Application closed.");
                        SCANNER.close();
                        System.exit(0);
                        return;

                    default:
                        System.out.println("Invalid menu choice.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    private static void addProduct(ShoppingCart cart) {
        System.out.println("\n1. Clothes");
        System.out.println("2. Electronics");
        System.out.println("3. Toys");

        int category = readInt("Choose category: ");
        int id = readInt("Enter product ID: ");
        String name = readText("Enter product name: ");
        double price = readDouble("Enter price: ");

        Product product;

        switch (category) {
            case 1:
                String size = readText("Enter size: ");
                String color = readText("Enter color: ");
                product = new Clothes(id, name, price, size, color);
                break;

            case 2:
                String brand = readText("Enter brand: ");
                int warranty = readInt("Enter warranty (months): ");
                product = new Electronics(id, name, price, brand, warranty);
                break;

            case 3:
                int age = readInt("Enter recommended age: ");
                product = new Toys(id, name, price, age);
                break;

            default:
                System.out.println("Invalid product category.");
                return;
        }

        cart.addProduct(product);
    }

    private static void updateProduct(ShoppingCart cart) {
        int id = readInt("Enter product ID to update: ");
        Product product = cart.findProduct(id);

        if (product == null) {
            System.out.println("Product not found in your cart.");
            return;
        }

        System.out.println("Current details: " + product);

        String name = readText("Enter new name: ");
        double price = readDouble("Enter new price: ");


        product.updateDetails(name, price);


        if (product instanceof Clothes) {
            String size = readText("Enter new size: ");
            String color = readText("Enter new color: ");
            ((Clothes) product).updateExtraDetails(size, color);

        } else if (product instanceof Electronics) {
            String brand = readText("Enter new brand: ");
            int warranty = readInt("Enter new warranty (months): ");
            ((Electronics) product).updateExtraDetails(brand, warranty);

        } else if (product instanceof Toys) {
            int age = readInt("Enter new recommended age: ");
            ((Toys) product).updateExtraDetails(age);
        }

        System.out.println("Product updated successfully.");
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = SCANNER.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                double value = Double.parseDouble(SCANNER.nextLine().trim());

                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }

                System.out.println("Price must be a finite, non-negative number.");
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid price.");
            }
        }
    }
}