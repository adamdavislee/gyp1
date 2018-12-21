import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import java.util.List;
final class terminal {
    public static void main(String[] args) {
    }
}
final public class Main extends Application {
    public static void main(String[] args) {
	    launch(args);
    }
    @Override
    public void start(Stage stage) {
	Double windowWidth = 1200.0;
	Double windowHeight = 600.0;
	Double windowMin = Math.min(windowWidth, windowHeight);
 	Pane pane = new Pane();
	Scene scene = new Scene(pane);
	stage.setScene(scene);
	stage.show();
	Rectangle rectangle = new Rectangle();
	rectangle.setWidth(windowWidth);
	rectangle.setHeight(windowHeight);
	rectangle.setFill(Color.BLACK);
	Text title = new Text();
	title.setText("Inventory Management System");
	title.setFill(Color.WHITE);
	title.setFont(Font.font("Ubuntu", FontWeight.BOLD, windowWidth/64));
	title.setLayoutX(windowWidth/2 - title.getLayoutBounds().getWidth()/2);
	title.setLayoutY(windowHeight/8);
	Border border = new Border(new BorderStroke(Color.WHITE,
						    BorderStrokeStyle.SOLID,
						    new CornerRadii(windowMin/128),
						    new BorderWidths(windowMin/128)));
	Pane partsPane = new Pane();
	partsPane.setPrefSize(windowWidth/4 + windowWidth/16, windowHeight/2);
	partsPane.setLayoutX(windowWidth/4-windowWidth/8);
	partsPane.setLayoutY(windowHeight/4);
	partsPane.setBorder(border);
	Pane productsPane = new Pane();
	productsPane.setPrefSize(windowWidth/4 + windowWidth/16, windowHeight/2);
	productsPane.setLayoutX(windowWidth/2+windowWidth/16);
	productsPane.setLayoutY(windowHeight/4);
	productsPane.setBorder(border);
	Text partsTitle = new Text();
	partsTitle.setText("Parts");
	partsTitle.setFill(Color.WHITE);
	partsTitle.setFont(Font.font("Ubuntu", FontWeight.BOLD, windowHeight/32));
	partsTitle.setLayoutX(partsPane.getLayoutX() + (windowWidth/4 + windowWidth/16)/4 - partsTitle.getLayoutBounds().getWidth()/2);
	partsTitle.setLayoutY(partsPane.getLayoutY() + windowHeight/16);
	Button searchPartsButton = new Button();
	//searchPartsButton.setPrefSize((windowWidth/4 + windowWidth/16)/8, windowHeight/32);
	searchPartsButton.setStyle("-fx-font-size:" + windowHeight/64);
	searchPartsButton.setText("Search");
	searchPartsButton.setLayoutX(partsPane.getLayoutX() + (windowWidth/4 + windowWidth/16)/2 + (windowWidth/4 + windowWidth/16)/8 - (windowWidth/4 + windowWidth/16)/4/2);
	searchPartsButton.setLayoutY(partsPane.getLayoutY() + windowHeight/32);
	System.out.println(searchPartsButton.getLayoutBounds());
	pane.getChildren().addAll(rectangle, title, partsPane, productsPane, partsTitle, searchPartsButton);
    };
}
abstract class Part {
    private int partID;
    private static String name = "Adam";
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
    public static String getName() {
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
final class Outsource extends Part {
    private String companyName;
    public void setComanyName(String companyName) {
	this.companyName = companyName;
    }
    public String getCompanyName() {
	return companyName;
    }   
}
final class Product {
    private List<Part> associatedParts;
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
	this.associatedParts.add(associatedPart);
    }
    public Part lookupAssociatedPart(int partID) {
	for (Part part : associatedParts) {
	    if (part.getPartID() == partID) {
		return part;
	    }
	}
	return null;
    }
}
final class Inventory {
    private List<Product> products;
    private List<Part> parts;
    public void addProduct(Product product) {
	products.add(product);
    }
    public boolean removeProduct(int productID) {
	for (Product product : products) {
	    if (product.getProductID() == productID) {
		products.remove(product);
		return true;
	    }
	}
	return false;
    }
    public Product lookupProduct(int productID) {
	for (Product product : products) {
	    if (product.getProductID() == productID) {
		return product;
	    }
	}
	return null;
    }
    public void updateProduct(int productID, Product newProduct) {
	for (Product product : products) {
	    if (product.getProductID() == productID) {
		products.remove(product);
		products.add(newProduct);
	    }
	}
    }
    public void addPart(Part part) {
	parts.add(part);
    }
    public boolean removePart(int partID) {
	for (Part part : parts) {
	    if (part.getPartID() == partID) {
		parts.remove(part);
		return true;
	    }
	}
	return false;
    }
    public Part lookupPart(int partID) {
	for (Part part : parts) {
	    if (part.getPartID() == partID) {
		return part;
	    }
	}
	return null;
    }
    public void updatePart(int partID, Part newPart) {
	for (Part part : parts) {
	    if (part.getPartID() == partID) {
		parts.remove(part);
		parts.add(newPart);
	    }
	}
    }
}