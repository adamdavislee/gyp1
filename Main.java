import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.lang.reflect.Field;
import java.lang.Exception;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
public class Main extends Application {
    private static int padding = 32;
    private static int spacing = 32;
    private static Border border = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(16), new BorderWidths(4)));
    private static Inventory inventory = new Inventory();
    public static void main(String[] args) {
	Outsource legs = new Outsource();
	InHouse seat = new InHouse();
	Product product = new Product();
	/*
        legs.setPartID(1);
	legs.setName("legs");
	legs.setCompanyName("company a");
	seat.setPartID(2);
	seat.setName("seat");
	product.setProductID(1);
	product.setName("Stool");
	product.addAssociatedPart(legs);
	inventory.addPart(legs);
	inventory.addPart(seat);
	inventory.addProduct(product);
	*/
	launch(args);
    }
    private static void drawAddPartView(Scene scene) {
	Text title = new Text();
	RadioButton inHouse = new RadioButton();
	RadioButton outsourced = new RadioButton();
	ToggleGroup toggleGroup = new ToggleGroup();
	HBox topRow = new HBox();
	Text IDLabel = new Text();
	TextField IDField = new TextField();
	Text nameLabel = new Text();
	TextField nameField = new TextField();
	Text inventoryLabel = new Text();
	TextField inventoryField = new TextField();
	Text costLabel = new Text();
	TextField costField = new TextField();
	Text companyNameLabel = new Text();
	TextField companyNameField = new TextField();
	HBox companyName = new HBox();
	Text machineIDLabel = new Text();
	TextField machineIDField = new TextField();
	HBox machineID = new HBox();
	Text minLabel = new Text();
	TextField minField = new TextField();
	Text maxLabel = new Text();
	HBox range = new HBox();
	TextField maxField = new TextField();
	HBox lowerButtons = new HBox();
	Button saveButton = new Button();
	Button cancelButton = new Button();
	VBox root = new VBox();
	title.setText("Add Part");
	title.setFill(Color.DARKGRAY);
	title.setFont(Font.font("Ubuntu", FontWeight.BOLD, 32));
	inHouse.setText("In House");
	inHouse.setSelected(true);
	inHouse.setToggleGroup(toggleGroup);
	outsourced.setText("Outsourced");
	outsourced.setToggleGroup(toggleGroup);
	toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
		    if (newToggle == outsourced) {
			root.getChildren().add(root.getChildren().size() - 1, companyName);
		    } else if (newToggle == inHouse) {
			root.getChildren().add(root.getChildren().size() - 1, machineID);
		    }
		    if (oldToggle == inHouse) {
			root.getChildren().remove(machineID);
		    } else if (oldToggle == outsourced) {
			root.getChildren().remove(companyName);
		    }
		}
	    });
	scene.setRoot(root);
	topRow.setSpacing(spacing);
	topRow.getChildren().addAll(title, inHouse, outsourced);
	IDLabel.setText("ID");
	IDField.setPromptText("Automatically Generated");
	IDField.setDisable(true);
	nameLabel.setText("Name");
	nameField.setPromptText("Name");
	inventoryLabel.setText("Inventory");
	inventoryField.setPromptText("inventory");
	costLabel.setText("Cost");
	costField.setPromptText("Cost");
	minLabel.setText("Min");
	minField.setPromptText("Min");
	maxLabel.setText("Max");
	maxField.setPromptText("Max");
	companyNameLabel.setText("Company Name");
	companyNameField.setPromptText("Company");
	companyName.setSpacing(spacing);
	companyName.getChildren().addAll(companyNameLabel, companyNameField);
	machineIDLabel.setText("Machine ID");
	machineIDField.setPromptText("Machine ID");
	machineID.setSpacing(spacing);
	machineID.getChildren().addAll(machineIDLabel, machineIDField);
	saveButton.setText("Save");
	saveButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    List<Integer> partIDs = inventory
			.getParts()
			.stream()
			.map(a -> a.getPartID())
			.collect(Collectors.toList());
		    int newPartID;
		    if (partIDs.isEmpty()) {
			newPartID = 1;
		    } else {
			newPartID = Collections.max(partIDs) + 1;
		    }
		    if (outsourced.isSelected()) {
			Outsource newPart = new Outsource();
			newPart.setCompanyName(companyNameField.getText());
			newPart.setPartID(newPartID);
			newPart.setName(nameField.getText());
			newPart.setPrice(new Double(costField.getText()));
			newPart.setInStock(new Integer(inventoryField.getText()));
			newPart.setMin(new Integer(minField.getText()));
			newPart.setMax(new Integer(maxField.getText()));
			inventory.addPart(newPart);
		    } else {
			InHouse newPart = new InHouse();
			newPart.setMachineID(new Integer(machineIDField.getText()));
			newPart.setPartID(newPartID);
			newPart.setName(nameField.getText());
			newPart.setPrice(new Double(costField.getText()));
			newPart.setInStock(new Integer(inventoryField.getText()));
			newPart.setMin(new Integer(minField.getText()));
			newPart.setMax(new Integer(maxField.getText()));
			inventory.addPart(newPart);
		    }
		    drawMainView(scene);
		}
	    });
	cancelButton.setText("Cancel");
	cancelButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    drawMainView(scene);
		}
	    });
	lowerButtons.setSpacing(spacing);
	lowerButtons.getChildren().addAll(saveButton, cancelButton);
	root.setPadding(new Insets(padding));
	root.setSpacing(spacing);
	root.getChildren().addAll(topRow,
				  new HBox(spacing, IDLabel, IDField),
				  new HBox(spacing, nameLabel, nameField),
				  new HBox(spacing, inventoryLabel, inventoryField),
				  new HBox(spacing, costLabel, costField),
				  new HBox(spacing, minLabel, minField),
				  new HBox(spacing, maxLabel, maxField),
				  machineID,
				  lowerButtons);
    }
    private static void drawModifyPartView(Scene scene, Part selectedPart) {
	Text title = new Text();
	RadioButton inHouse = new RadioButton();
	RadioButton outsourced = new RadioButton();
	ToggleGroup toggleGroup = new ToggleGroup();
	HBox topRow = new HBox();
	Text IDLabel = new Text();
	TextField IDField = new TextField();
	Text nameLabel = new Text();
	TextField nameField = new TextField();
	Text inventoryLabel = new Text();
	TextField inventoryField = new TextField();
	Text costLabel = new Text();
	TextField costField = new TextField();
	Text companyNameLabel = new Text();
	TextField companyNameField = new TextField();
	HBox companyName = new HBox();
	Text machineIDLabel = new Text();
	TextField machineIDField = new TextField();
	HBox machineID = new HBox();
	Text minLabel = new Text();
	TextField minField = new TextField();
	Text maxLabel = new Text();
	HBox range = new HBox();
	TextField maxField = new TextField();
	HBox lowerButtons = new HBox();
	Button saveButton = new Button();
	Button cancelButton = new Button();
	VBox root = new VBox();
	title.setText("Add Part");
	title.setFill(Color.DARKGRAY);
	title.setFont(Font.font("Ubuntu", FontWeight.BOLD, 32));
	inHouse.setText("In House");
	inHouse.setToggleGroup(toggleGroup);
	outsourced.setText("Outsourced");
	outsourced.setToggleGroup(toggleGroup);
	toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
		    if (newToggle == outsourced) {
			root.getChildren().add(root.getChildren().indexOf(lowerButtons), companyName);
		    } else if (newToggle == inHouse) {
			root.getChildren().add(root.getChildren().indexOf(lowerButtons), machineID);
		    }
		    if (oldToggle == inHouse) {
			root.getChildren().remove(machineID);
		    } else if (oldToggle == outsourced) {
			root.getChildren().remove(companyName);
		    }
		}
	    });
	scene.setRoot(root);
	topRow.setSpacing(spacing);
	topRow.getChildren().addAll(title, inHouse, outsourced);
	IDLabel.setText("ID");
	IDField.setPromptText("Automatically Generated");
	IDField.setDisable(true);
	IDField.setText(String.valueOf(selectedPart.getPartID()));
	nameLabel.setText("Name");
	nameField.setPromptText("Name");
	nameField.setText(selectedPart.getName());
	inventoryLabel.setText("Inventory");
	inventoryField.setPromptText("inventory");
	inventoryField.setText(String.valueOf(selectedPart.getInStock()));
	costLabel.setText("Cost");
	costField.setPromptText("Cost");
	costField.setText(String.valueOf(selectedPart.getPrice()));
	minLabel.setText("Min");
	minField.setPromptText("Min");
	minField.setText(String.valueOf(selectedPart.getMin()));
	maxLabel.setText("Max");
	maxField.setPromptText("Max");
	maxField.setText(String.valueOf(selectedPart.getMax()));
	companyNameLabel.setText("Company Name");
	companyNameField.setPromptText("Company");
	companyName.setSpacing(spacing);
	companyName.getChildren().addAll(companyNameLabel, companyNameField);
	machineIDLabel.setText("Machine ID");
	machineIDField.setPromptText("Machine ID");
	machineID.setSpacing(spacing);
	machineID.getChildren().addAll(machineIDLabel, machineIDField);
	saveButton.setText("Save");
	saveButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    inventory.removePart(selectedPart.getPartID());
		    if (outsourced.isSelected()) {
			Outsource newPart = new Outsource();
			newPart.setCompanyName(companyNameField.getText());
			newPart.setPartID(selectedPart.getPartID());
			newPart.setName(nameField.getText());
			newPart.setPrice(new Double(costField.getText()));
			newPart.setInStock(new Integer(inventoryField.getText()));
			newPart.setMin(new Integer(minField.getText()));
			newPart.setMax(new Integer(maxField.getText()));
			inventory.addPart(newPart);
		    } else {
			InHouse newPart = new InHouse();
			newPart.setMachineID(new Integer(machineIDField.getText()));
			newPart.setPartID(selectedPart.getPartID());
			newPart.setName(nameField.getText());
			newPart.setPrice(new Double(costField.getText()));
			newPart.setInStock(new Integer(inventoryField.getText()));
			newPart.setMin(new Integer(minField.getText()));
			newPart.setMax(new Integer(maxField.getText()));
			inventory.addPart(newPart);
		    }
		    drawMainView(scene);
		}
	    });
	cancelButton.setText("Cancel");
	cancelButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    drawMainView(scene);
		}
	    });
	lowerButtons.setSpacing(spacing);
	lowerButtons.getChildren().addAll(saveButton, cancelButton);
	root.setPadding(new Insets(padding));
	root.setSpacing(spacing);
	HBox companyNameOrMachineID;
	root.getChildren().addAll(topRow,
				  new HBox(spacing, IDLabel, IDField),
				  new HBox(spacing, nameLabel, nameField),
				  new HBox(spacing, inventoryLabel, inventoryField),
				  new HBox(spacing, costLabel, costField),
				  new HBox(spacing, minLabel, minField),
				  new HBox(spacing, maxLabel, maxField),
				  lowerButtons);
	if (selectedPart instanceof Outsource) {
	    companyNameField.setText(((Outsource) selectedPart).getCompanyName());
	    outsourced.setSelected(true);
	} else if (selectedPart instanceof InHouse) {
	    machineIDField.setText(String.valueOf(((InHouse) selectedPart).getMachineID()));
	    inHouse.setSelected(true);
	}
    }
    private static void drawAddProductView(Scene scene) {
	Product newProduct = new Product();
	Text title = new Text();
	Text IDLabel = new Text();
	TextField IDField = new TextField();
	HBox ID = new HBox();
	Text nameLabel = new Text();
	TextField nameField = new TextField();
	HBox name = new HBox();
	Text inStockLabel = new Text();
	TextField inStockField = new TextField();
	HBox inStock = new HBox();
	Text bankruptcyErrorText = new Text();
	Text priceLabel = new Text();
	TextField priceField = new TextField();
	HBox price = new HBox();
	Text minLabel = new Text();
	TextField minField = new TextField();
	HBox min = new HBox();
	Text maxLabel = new Text();
	TextField maxField = new TextField();
	HBox max = new HBox();
	VBox leftSide = new VBox();
	Button searchButton = new Button();
	TextField searchField = new TextField();
	HBox search = new HBox();
	TableView<Part> availablePartsTable = new TableView<>();
	Button addButton = new Button();
	Text errorText = new Text();
	TableColumn<Part, Integer> IDColumn = new TableColumn<>();
	TableColumn<Part, String> nameColumn = new TableColumn<>();
	TableColumn<Part, Integer> inventoryLevelColumn = new TableColumn<>();
	TableColumn<Part, Double> priceColumn = new TableColumn<>();
	TableColumn<Part, Integer> addedIDColumn = new TableColumn<>();
	TableColumn<Part, String> addedNameColumn = new TableColumn<>();
	TableColumn<Part, Integer> addedInventoryLevelColumn = new TableColumn<>();
	TableColumn<Part, Double> addedPriceColumn = new TableColumn<>();
	TableView<Part> addedPartsTable = new TableView<>();
	Button deleteButton = new Button();
	Button saveButton = new Button();
	Button cancelButton = new Button();
	HBox lowerButtons = new HBox();
	VBox rightSide = new VBox();
	HBox borderPane = new HBox();
	VBox root = new VBox();
	IDLabel.setText("ID");
	IDField.setPromptText("Automatically Generated");
	IDField.setDisable(true);
	ID.setSpacing(spacing);
	ID.getChildren().addAll(IDLabel, IDField);
	nameLabel.setText("Name");
	nameField.setPromptText("Name");
	name.setSpacing(spacing);
	name.getChildren().addAll(nameLabel, nameField);
	inStockLabel.setText("Inventory");
	inStockField.setPromptText("Inventory");
	inStock.setSpacing(spacing);
	inStock.getChildren().addAll(inStockLabel, inStockField);
	bankruptcyErrorText.setText("The cost of a product cannot be less than the cost of it's parts.");
	bankruptcyErrorText.setFill(Color.RED);
	priceLabel.setText("Price");
	priceField.setPromptText("Price");
	price.setSpacing(spacing);
	price.getChildren().addAll(priceLabel, priceField);
	minLabel.setText("Min");
	minField.setPromptText("Min");
	min.setSpacing(spacing);
	min.getChildren().addAll(minLabel, minField);
	maxLabel.setText("Max");
	maxField.setPromptText("Max");
	max.setSpacing(spacing);
	max.getChildren().addAll(maxLabel, maxField);
	title.setText("Add Product");
	title.setFill(Color.DARKGRAY);
	title.setFont(Font.font("Ubuntu", FontWeight.BOLD, 32));
	leftSide.setSpacing(spacing);
	leftSide.getChildren().addAll(title, ID, name, inStock, price, min, max);
	searchButton.setText("Search");
	searchButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    ObservableList<Part> parts = FXCollections.observableArrayList();
		    for (Part part : inventory.getParts()) {
			if (part.getName().contains(searchField.getText())) {
			    parts.add(part);
			}
		    }
		    availablePartsTable.setItems(parts);
		}
	    });
	search.setSpacing(spacing);
	search.getChildren().addAll(searchButton, searchField);
	IDColumn.setText("ID");
	IDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
	nameColumn.setText("Name");
	nameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
	inventoryLevelColumn.setText("Inventory Level");
	inventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
	priceColumn.setText("Price");
	priceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
	availablePartsTable.getColumns().addAll(IDColumn, nameColumn, inventoryLevelColumn, priceColumn);
	availablePartsTable.setItems(inventory.getParts());
	addButton.setText("Add");
	addButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {		    
		    newProduct.addAssociatedPart(availablePartsTable
						 .getSelectionModel()
						 .getSelectedItem());
		}
	    });
	errorText.setText("The product must have at least one part.");
	errorText.setFill(Color.RED);
	addedIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
	addedIDColumn.setText("ID");
	addedNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
	addedNameColumn.setText("Name");
	addedInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inventorylevel"));
	addedInventoryLevelColumn.setText("Inventorylevel");
	addedPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
	addedPriceColumn.setText("Price");
	addedPartsTable.getColumns().addAll(addedIDColumn, addedNameColumn, addedInventoryLevelColumn, addedPriceColumn);
	addedPartsTable.setItems(newProduct.getAssociatedParts());
	deleteButton.setText("Delete");
	deleteButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    newProduct.removeAssociatedPart(addedPartsTable.getSelectionModel().getSelectedItem());
		}
	    });
	saveButton.setText("Save");
	saveButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    int productID = 1;
		    for (Product a : inventory.getProducts()) {
			if (productID <= a.getProductID()) {
			    productID = a.getProductID() + 1;
			}
		    }
		    newProduct.setProductID(productID);
		    newProduct.setName(nameField.getText());
		    newProduct.setInStock(Integer.valueOf(inStockField.getText()));
		    newProduct.setPrice(Double.valueOf(priceField.getText()));
		    newProduct.setMin(Integer.valueOf(minField.getText()));
		    newProduct.setMax(Integer.valueOf(maxField.getText()));
		    int totalPartsCost = 0;
		    for (Part a : newProduct.getAssociatedParts()) {
			totalPartsCost += a.getPrice();
		    }
		    try {
			if (newProduct.getAssociatedParts().isEmpty()) {
			    throw new ZeroPartsException();
			} else if (rightSide.getChildren().contains(errorText)) {
			    rightSide.getChildren().remove(errorText);
			}
			if (newProduct.getPrice() < totalPartsCost) {
			    throw new PotentialBankruptcyException();
			} else if (leftSide.getChildren().contains(bankruptcyErrorText)) {
			    leftSide.getChildren().remove(bankruptcyErrorText);
			}
			inventory.addProduct(newProduct);
			drawMainView(scene);
		    }
		    catch (ZeroPartsException exception) {
			if (!rightSide.getChildren().contains(errorText)) {
			    rightSide.getChildren().add(rightSide.getChildren().indexOf(addedPartsTable), errorText);		
			}
		    }
		    catch (PotentialBankruptcyException potentialBankruptcyException) {
			if (!leftSide.getChildren().contains(bankruptcyErrorText)) {
			    leftSide.getChildren().add(leftSide.getChildren().indexOf(price), bankruptcyErrorText);
			}
		    }
		}
	    });
	cancelButton.setText("Cancel");
	cancelButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    drawMainView(scene);    
		}
	    });
	lowerButtons.setSpacing(spacing);
	lowerButtons.getChildren().addAll(saveButton, cancelButton);
	rightSide.setSpacing(spacing);
	rightSide.getChildren().addAll(search, availablePartsTable, addButton, addedPartsTable, deleteButton, lowerButtons);
	borderPane.setBorder(border);
	borderPane.setSpacing(spacing);
	borderPane.setPadding(new Insets(padding));
	borderPane.getChildren().addAll(leftSide, rightSide);
	root.setPadding(new Insets(padding));
	root.getChildren().add(borderPane);
	scene.setRoot(root);
    }
    private static void drawModifyProductView(Scene scene, Product product) {
	Text title = new Text();
	Text IDLabel = new Text();
	TextField IDField = new TextField();
	HBox ID = new HBox();
	Text nameLabel = new Text();
	TextField nameField = new TextField();
	HBox name = new HBox();
	Text inStockLabel = new Text();
	TextField inStockField = new TextField();
	HBox inStock = new HBox();
	Text bankruptcyErrorText = new Text();
	Text priceLabel = new Text();
	TextField priceField = new TextField();
	HBox price = new HBox();
	Text minLabel = new Text();
	TextField minField = new TextField();
	HBox min = new HBox();
	Text maxLabel = new Text();
	TextField maxField = new TextField();
	HBox max = new HBox();
	VBox leftSide = new VBox();
	Button searchButton = new Button();
	TextField searchField = new TextField();
	HBox search = new HBox();
	TableView<Part> availablePartsTable = new TableView<>();
	Button addButton = new Button();
	Text errorText = new Text();
	TableColumn<Part, Integer> IDColumn = new TableColumn<>();
	TableColumn<Part, String> nameColumn = new TableColumn<>();
	TableColumn<Part, Integer> inventoryLevelColumn = new TableColumn<>();
	TableColumn<Part, Double> priceColumn = new TableColumn<>();
	TableColumn<Part, Integer> addedIDColumn = new TableColumn<>();
	TableColumn<Part, String> addedNameColumn = new TableColumn<>();
	TableColumn<Part, Integer> addedInventoryLevelColumn = new TableColumn<>();
	TableColumn<Part, Double> addedPriceColumn = new TableColumn<>();
	TableView<Part> addedPartsTable = new TableView<>();
	Button deleteButton = new Button();
	Button saveButton = new Button();
	Button cancelButton = new Button();
	HBox lowerButtons = new HBox();
	VBox rightSide = new VBox();
	HBox borderPane = new HBox();
	VBox root = new VBox();
	IDLabel.setText("ID");
	IDField.setPromptText("Automatically Generated");
	IDField.setDisable(true);
	ID.setSpacing(spacing);
	ID.getChildren().addAll(IDLabel, IDField);
	nameLabel.setText("Name");
	nameField.setPromptText("Name");
	nameField.setText(product.getName());
	name.setSpacing(spacing);
	name.getChildren().addAll(nameLabel, nameField);
	inStockLabel.setText("Inventory");
	inStockField.setPromptText("Inventory");
	inStockField.setText(String.valueOf(product.getInStock()));
	inStock.setSpacing(spacing);
	inStock.getChildren().addAll(inStockLabel, inStockField);
	bankruptcyErrorText.setText("The cost of a product cannot be less than the cost of it's parts.");
	bankruptcyErrorText.setFill(Color.RED);
	priceLabel.setText("Price");
	priceField.setPromptText("Price");
	priceField.setText(String.valueOf(product.getPrice()));
	price.setSpacing(spacing);
	price.getChildren().addAll(priceLabel, priceField);
	minLabel.setText("Min");
	minField.setPromptText("Min");
	minField.setText(String.valueOf(product.getMin()));
	min.setSpacing(spacing);
	min.getChildren().addAll(minLabel, minField);
	maxLabel.setText("Max");
	maxField.setPromptText("Max");
	maxField.setText(String.valueOf(product.getMax()));
	max.setSpacing(spacing);
	max.getChildren().addAll(maxLabel, maxField);
	title.setText("Add Product");
	title.setFill(Color.DARKGRAY);
	title.setFont(Font.font("Ubuntu", FontWeight.BOLD, 32));
	leftSide.setSpacing(spacing);
	leftSide.getChildren().addAll(title, ID, name, inStock, price, min, max);
	searchButton.setText("Search");
	searchButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    ObservableList<Part> parts = FXCollections.observableArrayList();
		    for (Part part : inventory.getParts()) {
			if (part.getName().contains(searchField.getText())) {
			    parts.add(part);
			}
		    }
		    availablePartsTable.setItems(parts);
		}
	    });
	search.setSpacing(spacing);
	search.getChildren().addAll(searchButton, searchField);
	IDColumn.setText("ID");
	IDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
	nameColumn.setText("Name");
	nameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
	inventoryLevelColumn.setText("Inventory Level");
	inventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
	priceColumn.setText("Price");
	priceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
	availablePartsTable.getColumns().addAll(IDColumn, nameColumn, inventoryLevelColumn, priceColumn);
	availablePartsTable.setItems(inventory.getParts());
	addButton.setText("Add");
	addButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {		    
		    product.addAssociatedPart(availablePartsTable
					      .getSelectionModel()
					      .getSelectedItem());
		}
	    });
	errorText.setText("The product must have at least one part.");
	errorText.setFill(Color.RED);
	addedIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
	addedIDColumn.setText("ID");
	addedNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
	addedNameColumn.setText("Name");
	addedInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inventorylevel"));
	addedInventoryLevelColumn.setText("Inventorylevel");
	addedPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
	addedPriceColumn.setText("Price");
	addedPartsTable.getColumns().addAll(addedIDColumn, addedNameColumn, addedInventoryLevelColumn, addedPriceColumn);
	addedPartsTable.setItems(product.getAssociatedParts());
	deleteButton.setText("Delete");
	deleteButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    product.removeAssociatedPart(addedPartsTable.getSelectionModel().getSelectedItem());
		}
	    });
	saveButton.setText("Save");
	saveButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    product.setName(nameField.getText());
		    product.setInStock(Integer.valueOf(inStockField.getText()));
		    product.setPrice(Double.valueOf(priceField.getText()));
		    product.setMin(Integer.valueOf(minField.getText()));
		    product.setMax(Integer.valueOf(maxField.getText()));
		    int totalPartsCost = 0;
		    for (Part a : product.getAssociatedParts()) {
			totalPartsCost += a.getPrice();
		    }
		    try {
			if (product.getAssociatedParts().isEmpty()) {
			    System.out.println("foo");
			    throw new ZeroPartsException();
			} else if (rightSide.getChildren().contains(errorText)) {
			    rightSide.getChildren().remove(errorText);
			}
			if (product.getPrice() < totalPartsCost) {
			    throw new PotentialBankruptcyException();
			} else if (leftSide.getChildren().contains(bankruptcyErrorText)) {
			    leftSide.getChildren().remove(bankruptcyErrorText);
			}
			inventory.removeProduct(product.getProductID());
			inventory.addProduct(product);
			drawMainView(scene);
		    }
		    catch (ZeroPartsException exception) {
			if (!rightSide.getChildren().contains(errorText)) {
			    rightSide.getChildren().add(rightSide.getChildren().indexOf(addedPartsTable), errorText);		
			}
		    }
		    catch (PotentialBankruptcyException potentialBankruptcyException) {
			if (!leftSide.getChildren().contains(bankruptcyErrorText)) {
			    leftSide.getChildren().add(leftSide.getChildren().indexOf(price), bankruptcyErrorText);
			}
		    }
		}
	    });
	cancelButton.setText("Cancel");
	cancelButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    drawMainView(scene);    
		}
	    });
	lowerButtons.setSpacing(spacing);
	lowerButtons.getChildren().addAll(saveButton, cancelButton);
	rightSide.setSpacing(spacing);
	rightSide.getChildren().addAll(search, availablePartsTable, addButton, addedPartsTable, deleteButton, lowerButtons);
	borderPane.setBorder(border);
	borderPane.setSpacing(spacing);
	borderPane.setPadding(new Insets(padding));
	borderPane.getChildren().addAll(leftSide, rightSide);
	root.setPadding(new Insets(padding));
	root.getChildren().add(borderPane);
	scene.setRoot(root);
    }
    private static void drawMainView(Scene scene) {
	InHouse part = new InHouse();
	Text rootTitle = new Text();
	Text partsPaneTitle = new Text();
	Button partsSearchButton = new Button();
	TextField partsSearchBox = new TextField();
	HBox partsTopRow = new HBox();
	TableColumn<Part, Integer> partID = new TableColumn<>("Part ID");
	TableColumn<Part, String> partName = new TableColumn<>("Part Name");
	TableColumn<Part, Integer> partInventoryLevel = new TableColumn<>("Inventory Level");
	TableColumn<Part, Double> partCostPerUnit = new TableColumn<>("Cost Per Unit");
	TableView<Part> partsTable = new TableView<>();
	Button partsAddButton = new Button();
	Button partsModifyButton = new Button();
	Button partsDeleteButton = new Button();
	HBox partsLowerButtons = new HBox();
	VBox partsPane = new VBox();
	Text productsPaneTitle = new Text();
	Button productsSearchButton = new Button();
	TextField productsSearchBox = new TextField();
	HBox productsTopRow = new HBox();
	TableColumn<Product, Integer> productID = new TableColumn<>("Product ID");
	TableColumn<Product, String> productName = new TableColumn<>("Product Name");
	TableColumn<Product, Integer> productInventoryLevel = new TableColumn<>("Inventory Level");
	TableColumn<Product, Double> productCostPerUnit = new TableColumn<>("Cost Per Unit");
	TableView<Product> productsTable = new TableView<>();
	Button productsAddButton = new Button();
	Button productsModifyButton = new Button();
	Button productsDeleteButton = new Button();
	HBox productsLowerButtons = new HBox();
	VBox productsPane = new VBox();
	HBox partsAndProducts = new HBox();
	VBox root = new VBox();
	rootTitle.setText("Inventory Management System");
	rootTitle.setFill(Color.DARKGRAY);
	rootTitle.setFont(Font.font("Ubuntu", FontWeight.BOLD, 32));
	partsPaneTitle.setText("Parts");
	partsPaneTitle.setFill(Color.DARKGRAY);
	partsPaneTitle.setFont(Font.font("Ubuntu", FontWeight.BOLD, 32));
	partsSearchButton.setText("Search");
	partsSearchButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    ObservableList<Part> parts = FXCollections.observableArrayList();
		    for (Part part : inventory.getParts()) {
			if (part.getName().contains(partsSearchBox.getText())) {
			    parts.add(part);
			}
		    }
		    partsTable.setItems(parts);
		}
	    });
	partsTable.setItems(inventory.getParts());
	partsTopRow.setSpacing(spacing);
	partsTopRow.getChildren().addAll(partsPaneTitle, partsSearchButton, partsSearchBox);
	partID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
	partName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
	partInventoryLevel.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
	partCostPerUnit.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
	partsTable.getColumns().addAll(partID, partName, partInventoryLevel, partCostPerUnit);
	partsAddButton.setText("Add");
	partsAddButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    drawAddPartView(scene);
		}
	    });
	partsModifyButton.setText("Modify");
	partsModifyButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    drawModifyPartView(scene, partsTable.getSelectionModel().getSelectedItem());
		}
	    });
	partsDeleteButton.setText("Delete");
	partsDeleteButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    inventory.removePart(partsTable.getSelectionModel().getSelectedItem().getPartID());
		    partsTable.setItems(inventory.getParts());
		}
	    });
	partsLowerButtons.setSpacing(spacing);
	partsLowerButtons.setPadding(new Insets(padding));
	partsLowerButtons.getChildren().addAll(partsAddButton, partsModifyButton, partsDeleteButton);
	partsPane.setBorder(border);
	partsPane.setPadding(new Insets(padding));
	partsPane.setSpacing(spacing);
	partsPane.getChildren().addAll(partsTopRow, partsTable, partsLowerButtons);
	productsPaneTitle.setText("Products");
	productsPaneTitle.setFill(Color.DARKGRAY);
	productsPaneTitle.setFont(Font.font("Ubuntu", FontWeight.BOLD, 32));
	productsSearchButton.setText("Search");
	productsSearchButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    ObservableList<Product> searchedProducts = FXCollections.observableArrayList();
		    for (Product a : inventory.getProducts()) {
			if (a.getName().contains(productsSearchBox.getText())) {
			    searchedProducts.add(a);
			}
		    }
		    productsTable.setItems(searchedProducts);
		}
	    });
	productsTopRow.setSpacing(spacing);
	productsTopRow.getChildren().addAll(productsPaneTitle, productsSearchButton, productsSearchBox);
	productID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
	productName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
	productInventoryLevel.setCellValueFactory(new PropertyValueFactory<Product, Integer>("inStock"));
	productCostPerUnit.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
	productsTable.getColumns().addAll(productID, productName, productInventoryLevel, productCostPerUnit);
	productsTable.setItems(inventory.getProducts());
	productsAddButton.setText("Add");
	productsAddButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    drawAddProductView(scene);
		}
	    });
	productsModifyButton.setText("Modify");
	productsModifyButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    drawModifyProductView(scene, productsTable.getSelectionModel().getSelectedItem());
		}
	    });
	productsDeleteButton.setText("Delete");
	productsDeleteButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent actionEvent) {
		    inventory.removeProduct(productsTable.getSelectionModel().getSelectedItem().getProductID());
		    productsTable.setItems(inventory.getProducts());
		}
	    });
	productsLowerButtons.setSpacing(spacing);
	productsLowerButtons.setPadding(new Insets(padding));
	productsLowerButtons.getChildren().addAll(productsAddButton, productsModifyButton, productsDeleteButton);
	productsPane.setBorder(border);
	productsPane.setPadding(new Insets(padding));
	productsPane.setSpacing(spacing);
	productsPane.getChildren().addAll(productsTopRow, productsTable, productsLowerButtons);
	partsAndProducts.setSpacing(spacing);
	partsAndProducts.getChildren().addAll(partsPane, productsPane);
	root.setPadding(new Insets(padding));
	root.setSpacing(spacing);
	root.getChildren().addAll(rootTitle, partsAndProducts);
	scene.setFill(Color.LIGHTGRAY);
	scene.setRoot(root);
    }
    @Override public void start(Stage stage) {
	Scene scene = new Scene(new VBox());
	stage.setScene(scene);
	stage.show();
	drawMainView(scene);
    }
}
class Inventory {
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private ObservableList<Part> parts = FXCollections.observableArrayList();
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
final class InHouse extends Part {
    private int machineID;
    public void setMachineID(int machineID) {
	this.machineID = machineID;
    }
    public int getMachineID() {
	return machineID;
    }
}
final class Outsource extends Part {
    private String companyName;
    public void setCompanyName(String companyName) {
	this.companyName = companyName;
    }
    public String getCompanyName() {
	return companyName;
    }
}
class ZeroPartsException extends Exception {
}
class PotentialBankruptcyException extends Exception {
}