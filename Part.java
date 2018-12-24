public abstract class Part {
    private int partID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    public void setPartID(int partID) {
	this.partID = partID;
    }
    public int getPartID() {
	return partID;
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
 }
final class InHouse extends Part {
    private int machine;
    public void setMachine(int machine) {
	this.machine = machine;
    }
    public int getMachine() {
	return machine;
    }
}
