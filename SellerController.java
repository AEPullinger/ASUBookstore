//SellerController.java
package application;


import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class SellerController{
	
	public Books Books = new Books();
	

    @FXML
    private TextField authorBox;


    @FXML
    private ComboBox<String> categoryBox;
    private ObservableList<String> categories = FXCollections.observableArrayList("Natural Science","Computer", "Math", "English Language", "Other");

    @FXML
    private ComboBox<String> conditionBox;
    private ObservableList<String> conditions= FXCollections.observableArrayList("Used Like New", "Moderately Used","Heavily Used");

    @FXML
    private TextField isbnBox;

    @FXML
    private Button listMyBook;
    
    @FXML
    private Button myBooksButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField originalPriceBox;

    @FXML
    private TextField titleBox;
    
    @FXML
    private TextField pubYearBox;
    
    @FXML
    private Text titleLabel;
    
    @FXML
    private Text isbnLabel;
    
    @FXML
    private Text authorLabel;
    
    @FXML
    private Text pubYearLabel;
    
    @FXML
    private Text conditionLabel;
    
    @FXML
    private Text categoryLabel;
    
    @FXML
    private Text origPriceLabel;
    
    @FXML
    private Button listBookButton;
    
    @FXML 
    private GridPane gridPane;
    
    @FXML 
    private BorderPane borderPane;
    
    @FXML
    private Pane bottomPane;
    
    @FXML
    private Pane topPane;
    
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> titleColumn;
    
    @FXML 
    private TableColumn<Book, String> isbnColumn;
    
    @FXML
    private TableColumn<Book, String> condColumn;
    
    @FXML
    private TableColumn<Book, String> categoryColumn;
    
    @FXML
    private TableColumn<Book, Double> priceColumn;
    
    @FXML
    private TableColumn<Book, Boolean> soldColumn;
    
    @FXML
    private void goToListBook(ActionEvent event) {
    	System.out.println("List Book button pressed");
    	//Make Grid Pane appear
    	gridPane.setVisible(true);
    	bottomPane.setVisible(true);
    	
    	//Make table View disappear
    	tableView.setVisible(false);
    	topPane.setVisible(false);

		//Set MyBooks button to have a white border
		myBooksButton.setStyle("-fx-background-color: FFC627; "
				+ "-fx-border-radius: 20; "
				+ "-fx-background-radius: 25;"
				+ " -fx-border-color: #000000; "
				+ "-fx-border-width: 2;"
				+ " -fx-translate-x: 31;"
				+ " -fx-translate-y: 30;");
		
		//List Books button to have a black border
		listBookButton.setStyle("-fx-background-color: FFC627;"
				+ " -fx-border-radius: 20;"
				+ " -fx-background-radius: 25;"
				+ " -fx-border-color: FFFFFF;"
				+ " -fx-border-width: 2;"
				+ " -fx-translate-x: 30;"
				+ " -fx-translate-y: 30;");
				
    }
    
    //Function get all Books for sale by user for the table view
    public ObservableList<Book> getBookByUser(){
    	ObservableList<Book> userBooks = FXCollections.observableArrayList();
		String seller = Main.thisUser.getEmail();
    	for(int i = 0; i < Books.numBooks;i++) {
    		if(Books.books.get(i).getSeller().equals(seller)) {
    			userBooks.add(Books.books.get(i));	
    		}
    	}
    	return userBooks;
    }
    
    @FXML 
    private void goToMyBooks(ActionEvent event) {
    	//System.out.println("My Books button pressed");
    	//Make Grid Pane disappear
    	gridPane.setVisible(false);
    	bottomPane.setVisible(false);
    	
    	//Make table View appear
    	tableView.setVisible(true);
    	topPane.setVisible(true);
    	
        // Set items in TableView
        //System.out.println("Populating TableView...");

        
        // Set cell value factories using lambda expressions
        titleColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getTitle())
        );
        isbnColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIsbn())
        );
        condColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getCondition())
        );
        categoryColumn.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getCategory())
        );
        priceColumn.setCellValueFactory(cellData -> 
        new SimpleDoubleProperty(cellData.getValue().getGeneratedPrice()).asObject()
        );
        soldColumn.setCellValueFactory(cellData -> 
        new SimpleBooleanProperty(cellData.getValue().isSold()).asObject()
        );        
        
        // Populate the TableView
        tableView.setItems(getBookByUser());
        
		//Set MyBooks button to have a white border
		myBooksButton.setStyle("-fx-background-color: FFC627; "
				+ "-fx-border-radius: 20; "
				+ "-fx-background-radius: 25;"
				+ " -fx-border-color: FFFFFF; "
				+ "-fx-border-width: 2;"
				+ " -fx-translate-x: 31;"
				+ " -fx-translate-y: 30;");
		
		//List Books button to have a black border
		listBookButton.setStyle("-fx-background-color: FFC627;"
				+ " -fx-border-radius: 20;"
				+ " -fx-background-radius: 25;"
				+ " -fx-border-color: #000000;"
				+ " -fx-border-width: 2;"
				+ " -fx-translate-x: 30;"
				+ " -fx-translate-y: 30;");
    
    }

    @FXML
    private void listMyBookButton(ActionEvent event){
    	//UI console call
    	//System.out.println("list my book button was pressed");
    	
    	//Get all data input from user
    	String title = titleBox.getText();
    	String isbn = isbnBox.getText();
    	String author = authorBox.getText();
    	String condition = conditionBox.getValue();
    	String category = categoryBox.getValue();
    	String originalPrice = originalPriceBox.getText();
    	String pubYear = pubYearBox.getText();
    	//Get email of user that is logged in 
    	String seller = Main.thisUser.email;
    	
    	//Call to checkIfEmpty function
    	if(inputIsMissing(title, isbn, author, condition, category,originalPrice, pubYear) == false) {
        	Books.addBook(isbn, title, author, condition, category, Double.valueOf(originalPrice), generatedPrice(category, condition, Double.valueOf(originalPrice)) , seller, "", false, Integer.parseInt(pubYear));
        	/*Testing purposes
        	System.out.println("Title: " + title );
        	System.out.println("ISBN: " + isbn );
        	System.out.println("Author: " + author);
        	System.out.println("Condition: " + condition);
        	System.out.println("Category: " + category);
        	System.out.println("Original Price: " + originalPrice);
        	System.out.println("Generated Price: " + generatedPrice(condition, Double.valueOf(originalPrice)));
        	System.out.println("User: " + seller);
        	System.out.println("Year Published: " + pubYear);
        	System.out.println("This is should be after book is added");
        	Books.printBooks();
        	*/
        	
    		//Alerting the user that the book has been posted for sale
        	Alert bookPosted= new Alert(AlertType.CONFIRMATION);
        	bookPosted.setHeaderText("Success");
        	bookPosted.setContentText("Book was posted to the store successfully");
        	bookPosted.show();
        	
        	
        	//Clearing all of the input fields from the user
        	pubYearBox.clear();
        	titleBox.clear();
        	originalPriceBox.clear();
        	isbnBox.clear();
        	authorBox.clear();
        	conditionBox.setValue("Select Current Condition");
        	categoryBox.setValue("Select Category Of The Book");
    	}else {
    		//Alerting the user that data about the book is missing
        	Alert dataError = new Alert(AlertType.ERROR);
        	dataError.setHeaderText("Error");
        	dataError.setContentText("Please verify that you have entered all the information for a book correctly.");
        	dataError.show();
    	}
 }
    
    //Function that returns a system generated price based on original price and condition
    private double generatedPrice(String category,String condition, Double origPrice ) {
    	Double newPrice = origPrice;
    	
    	//Returning new price based on condition
    	if (condition == "Used Like New") {
    		newPrice = (double) Math.round((newPrice *.90)* 100)/100;
    	}else if(condition == "Moderately Used") {
    		newPrice = (double) Math.round((newPrice *.75) * 100 )/100;
    	}else {
    		newPrice = (double) Math.round((newPrice *.50) * 100)/100;
    	}
    	
    	if (category == "Other") {
    		newPrice = (double) Math.round((newPrice *.96)* 100)/100;
    	}else if(category == "English Language") {
    		newPrice = (double) Math.round((newPrice *.98) * 100 )/100;
    	}
    	
    	return newPrice;
    }
    
    //Function that returns true if any input from the user is missing
    //True if no input is missing
    private boolean inputIsMissing(String title, String isbn, String author, String condition, String category, String originalPrice, String pubYear) {
    	if(title == "" || isbn =="" || author == "" || condition == null || category == null || originalPrice == "" || Integer.parseInt(pubYear) < 1000) {
    		return true;
    	}
    	return false;
    }


    
    @FXML
    private void logOut(ActionEvent event) throws Exception {
    	
    	
    	System.out.println("Logout Button pressed");
    	Node node = (Node)event.getSource();
    	Stage stage = (Stage)node.getScene().getWindow();
    	
    	Main main = new Main();
    	User noUser = new User();
    	main.thisUser = noUser;
    	main.start(stage);
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws FileNotFoundException {

    	//Setting combo boxes with data that be selected from user
    	categoryBox.setItems(categories);
    	conditionBox.setItems(conditions);
    	
    	//Console log of UI change (Testing Purposes)
    	//System.out.println("SellerController Initialized");
    	//System.out.println("Number Of Books: " + Books.getNumBooks());
    	
    	Books.uploadBooks();
    	System.out.println("Number Of Books On File: " + Books.getNumBooks());
    	System.out.println("\nPrinting Books:");
    	Books.printBooks();
    	
    	//Event Filter that only allows Characters into the Author Field
        authorBox.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[a-zA-Z ]")) { // Only allow letters
                event.consume(); // Ignore non-alphabetic input
            }
        });
        
        
     // Add a listener to restrict yearInput to a four-digit year less than 2025
        pubYearBox.textProperty().addListener((observable, oldValue, newValue) -> {
            // Ensure the input only contains digits and is at most 4 characters long
            if (!newValue.matches("\\d*") || newValue.length() > 4) {
            	pubYearBox.setText(oldValue); // Reset to previous valid value
            } else {
                // If the input is valid, further check if the value is less than 2025
                try {
                    if (!newValue.isEmpty() && Integer.parseInt(newValue) >= 2025) {
                    	pubYearBox.setText(oldValue); // Reset to previous valid value
                    }
                } catch (NumberFormatException e) {
                	pubYearBox.setText(oldValue); // Handle any parsing errors gracefully
                }
            }
        });
    	
    	//Event Filter that only allows numbers into the isbn
        isbnBox.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("\\d")) { // Only allow digits
                event.consume(); // Ignore non-numeric input
            }
        });
       
        // Add a ChangeListener to restrict original Price input format and range
        originalPriceBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                // Allow empty input for clearing purposes
                return;
            }
            // Ensure format: up to 3 digits before the decimal, optionally followed by a decimal and 2 decimal places
            if (!newValue.matches("\\d{0,3}(\\.\\d{0,2})?")) {
            	originalPriceBox.setText(oldValue); // Revert to the previous value if format is incorrect
            } else {
                try {
                    // Parse the number and check if it’s within the range
                    double value = Double.parseDouble(newValue);
                    if (value < 0 || value >= 1000) {
                        // Reset to the previous value if out of range
                    	originalPriceBox.setText(oldValue);
                    }
                } catch (NumberFormatException e) {
                    // Reset to the previous value if parsing fails
                	originalPriceBox.setText(oldValue);
                }
            }
        });
    	
    }

}
