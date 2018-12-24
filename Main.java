import javafx.application.Application;
import javafx.stage.Stage;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
public class Main extends Application {
    public static void main(String[] args) {
	launch(args);
    }
    private static void drawMainScreen(Stage stage) {
	int padding = 32;
	int spacing = 32;
	Inventory inventory = new Inventory();
	InHouse part = new InHouse();
	part.setPartID(1);
	part.setName("foo");
	part.setPrice(1.0);
	part.setInStock(3);
	part.setMin(1);
	part.setMax(2);
	inventory.addPart(part);
	Text rootTitle = new Text();
	rootTitle.setText("Inventory Management System");
	rootTitle.setFill(Color.DARKGRAY);
	rootTitle.setFont(Font.font("Ubuntu", FontWeight.BOLD, 32));
	Border border = new Border(new BorderStroke(Color.DARKGRAY,
						    BorderStrokeStyle.SOLID,
						    new CornerRadii(16),
						    new BorderWidths(4)));
	Text partsPaneTitle = new Text();
	partsPaneTitle.setText("Parts");
	partsPaneTitle.setFill(Color.DARKGRAY);
	partsPaneTitle.setFont(Font.font("Ubuntu", FontWeight.BOLD, 32));
	Button partsSearchButton = new Button();
	partsSearchButton.setText("Search");
	TextField partsSearchBox = new TextField();
	HBox partsTopRow = new HBox();
	partsTopRow.setSpacing(spacing);
	partsTopRow.getChildren().addAll(partsPaneTitle, partsSearchButton, partsSearchBox);
	TableColumn partID = new TableColumn("Part ID");
	partID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
	TableColumn partName = new TableColumn("Part Name");
	partName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
	TableColumn partInventoryLevel = new TableColumn("Inventory Level");
	partInventoryLevel.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
	TableColumn partCostPerUnit = new TableColumn("Cost Per Unit");
	partCostPerUnit.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
	TableView partsTable = new TableView();
	partsTable.getColumns().addAll(partID, partName, partInventoryLevel, partCostPerUnit);
	partsTable.setEditable(true);
	partsTable.setItems(inventory.getParts());
	Button partsAddButton = new Button();
	partsAddButton.setText("Add");
	partsAddButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    System.out.println("you clicked the partsAddButton");
		}
	    });
	Button partsModifyButton = new Button();
	partsModifyButton.setText("Modify");
	Button partsDeleteButton = new Button();
	partsDeleteButton.setText("Delete");
	HBox partsLowerButtons = new HBox();
	partsLowerButtons.setSpacing(spacing);
	partsLowerButtons.setPadding(new Insets(padding));
	partsLowerButtons.getChildren().addAll(partsAddButton, partsModifyButton, partsDeleteButton);
	VBox partsPane = new VBox();
	partsPane.setBorder(border);
	partsPane.setPadding(new Insets(padding));
	partsPane.setSpacing(spacing);
	partsPane.getChildren().addAll(partsTopRow, partsTable, partsLowerButtons);
	Text productsPaneTitle = new Text();
	productsPaneTitle.setText("Products");
	productsPaneTitle.setFill(Color.DARKGRAY);
	productsPaneTitle.setFont(Font.font("Ubuntu", FontWeight.BOLD, 32));
	Button productsSearchButton = new Button();
	productsSearchButton.setText("Search");
	TextField productsSearchBox = new TextField();
	HBox productsTopRow = new HBox();
	productsTopRow.setSpacing(spacing);
	productsTopRow.getChildren().addAll(productsPaneTitle, productsSearchButton, productsSearchBox);
	TableColumn productID = new TableColumn("Product ID");
	productID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
	TableColumn productName = new TableColumn("Product Name");
	productName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
	TableColumn productInventoryLevel = new TableColumn("Inventory Level");
	productInventoryLevel.setCellValueFactory(new PropertyValueFactory<Product, Integer>("inStock"));
	TableColumn productCostPerUnit = new TableColumn("Cost Per Unit");
	productCostPerUnit.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
	TableView productsTable = new TableView();
	productsTable.getColumns().addAll(productID, productName, productInventoryLevel, productCostPerUnit);
	productsTable.setEditable(true);
	productsTable.setItems(inventory.getProducts());
	Button productsAddButton = new Button();
	productsAddButton.setText("Add");
	Button productsModifyButton = new Button();
	productsModifyButton.setText("Modify");
	Button productsDeleteButton = new Button();
	productsDeleteButton.setText("Delete");
	HBox productsLowerButtons = new HBox();
	productsLowerButtons.setSpacing(spacing);
	productsLowerButtons.setPadding(new Insets(padding));
	productsLowerButtons.getChildren().addAll(productsAddButton, productsModifyButton, productsDeleteButton);
	VBox productsPane = new VBox();
	productsPane.setBorder(border);
	productsPane.setPadding(new Insets(padding));
	productsPane.setSpacing(spacing);
	productsPane.getChildren().addAll(productsTopRow, productsTable, productsLowerButtons);
	HBox partsAndProducts = new HBox();
	partsAndProducts.setSpacing(spacing);
	partsAndProducts.getChildren().addAll(partsPane, productsPane);
	VBox root = new VBox();
	root.setPadding(new Insets(padding));
	root.setSpacing(spacing);
	root.getChildren().addAll(rootTitle, partsAndProducts);
	Scene scene = new Scene(root);
	scene.setFill(Color.LIGHTGRAY);
	stage.setScene(scene);
	stage.show();
    }
    @Override public void start(Stage stage) {
	drawMainScreen(stage);
    }
}
class Product {
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
class Inventory {
    private ObservableList<Product> products;
    private ObservableList<Part> parts;
    public Inventory() {
	products = FXCollections.observableArrayList();
	parts = FXCollections.observableArrayList();
    }
    public ObservableList<Product> getProducts() {
	return products;
    }
    public ObservableList<Part> getParts() {
	return parts;
    }
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
