class Main {
    public static void main(String[] args) {
    }
}
abstract class Part {
    private int partID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    public void setName(String name) {
	this.name = name;
    }
    public String getName() {
	return name;
    }
    public void setPrice(double price) {
	this.price = price;
    }
    public double getPrice() {
	return price;
    }
    public void setInStock(int inStock) {
	this.inStock = inStock;
    }
    public int getInStock() {
	return inStock;
    }
    public void setMin(int min) {
	this.min = min;
    }
    public int getMin() {
	return min;
    }
    public void setMax(int max) {
	this.max = max;
    }
    public int getMax() {
	return max;
    }
 }
class InHouse extends Part {
    private int machine;
    public void setMachine(int machine) {
	this.machine = machine;
    }
    public int getMachine() {
	return machine;
    }
}
class Outsource extends Part {
    private String companyName;
    public void setComanyName(String companyName) {
	this.companyName = companyName;
    }
    public String getCompanyName() {
	return companyName;
    }   
}
class Product {
    private Part[] associatedParts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    public void setProductID(int productID) {
	this.productID = productID;
    }
    public int getProductID() {
	return productID;
    }
    public void setName(String name) {
	this.name = name;
    }
    public String getName() {
	return name;
    }
    public void setPrice(double price) {
	this.price = price;
    }
    public double getPrice() {
	return price;
    }
    public void setInStock(int inStock) {
	this.inStock = inStock;
    }
    public int getInStock() {
	return inStock;
    }
    public void setMin(int min) {
	this.min = min;
    }
    public int getMin() {
	return min;
    }
    public void setMax(int max) {
	this.max = max;
    }
    public int getMax() {
	return max;
    }
    public void addAssociatedPart(Part associatedPart) {
    }
    public Part lookupAssociatedPart(int partID) {
	return associatedParts[0];
    }
}
class Inventory {
    private Product[] products;
    private Part[] parts;
    public void addProduct(Product product) {
    }
    public boolean removeProduct(int productID) {
	return true;
    }
    public Product lookupProduct(int productID) {
	return products[0];
    }
    public void updateProduct(int productID) {
    }
    public void addPart(Part part) {
    }
    public boolean removePart(int partID) {
	return true;
    }
    public Part lookupPart(int partID) {
	return parts[0];
    }
    public void updatePart(int partID) {
    }
}