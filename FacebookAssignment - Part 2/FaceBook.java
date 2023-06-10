/**
  Name: Daniel Gallagher (L00158616)
  Class : B.Sc. in Computing
  Lecturer : Maria Boyle
  Due Date : 05/05/2022
  
*/

/*
   Summary:
   The program creates a model of the facebook GUI and switches between 2 scenes
   using a button
   The key features in this program is the topPane, bottomPane, centerPane and leftPane
               
   
*/

//*Imports*
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.*;
import java.text.DecimalFormat;
import javafx.event.*; 
import javafx.scene.text.Font.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;

public class FaceBook extends Application
{
   //Declaring the Scene called scene and BorderPane as scenelayout 
   public Scene scene;
   private BorderPane sceneLayout;
   
   //Declaring the topPane as GridPane and bottomPane as BorderPane
   private GridPane topPane;
   private BorderPane bottomPane;
   
   //Declaring 2 TextFields 
   private TextField tfEmail, tfPassword;
   
   //Declaring 2 Buttons
   private Button btLogIn, btSignUp;
   
   //Creating the 2nd facebook scene 
   private Scene scene2;
   private BorderPane sceneLayout2;
   
   //Declaring the centerPane as BorderPane and rightPane as VBox
   private BorderPane centerPane;
   private VBox rightPane;
   
   @Override
   public void start(Stage primaryStage)
   {
      //Scene 1
      //Construct borderPane node called sceneLayout
      sceneLayout = new BorderPane();
      sceneLayout.setTop(getTopPane());
      sceneLayout.setBottom(getBottomPane());
      
      //Construct scene to contain sceneLayout
      scene = new Scene(sceneLayout);
      primaryStage.setScene(scene);
      primaryStage.setTitle("FACEBOOK");
      primaryStage.show();
      
      //TopPane()
      //Constructing the ColumnConstraints
      ColumnConstraints col1 = new ColumnConstraints();
      col1.setPercentWidth(25);
      ColumnConstraints col2 = new ColumnConstraints();
      col2.setPercentWidth(50);
      ColumnConstraints col3 = new ColumnConstraints();
      col3.setPercentWidth(25);
      topPane.getColumnConstraints().addAll(col1, col2, col3);
      
      //Constructing 2 labels and Adding 2 textFields
      topPane.add(new Label("Email: "),0,0);
      topPane.add(new Label("Password: "),0,1); 
      topPane.add(tfEmail = new TextField(),1,0); 
      topPane.add(tfPassword = new TextField(),1,1); 
      
      //Constructing 2 buttons 
      topPane.add(btLogIn = new Button("Log In"),2,0);
      topPane.add(btSignUp = new Button("Sign Up"),2,1);
      
      //Allowing the buttons to grow to fill max width of cell
      btLogIn.setMaxWidth(Double.MAX_VALUE);
      btSignUp.setMaxWidth(Double.MAX_VALUE);
      
      //Setting the background of the buttons to aliceblue
      btLogIn.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE,
                                               CornerRadii.EMPTY,
                                               Insets.EMPTY)));
                                               
      btSignUp.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE,
                                               CornerRadii.EMPTY,
                                               Insets.EMPTY))); 
             
      //Setting the Border for the 2 Buttons(btLogIn, btSignUp)                                         
      btLogIn.setBorder(new Border(new BorderStroke(Color.BLACK,
                                       BorderStrokeStyle.SOLID,
                                       new CornerRadii(1),
                                       new BorderWidths(1))));  
                                       
      btSignUp.setBorder(new Border(new BorderStroke(Color.BLACK,
                                       BorderStrokeStyle.SOLID,
                                       new CornerRadii(1),
                                       new BorderWidths(1))));
      
      //Switching scenes using the SignUp button
      btSignUp.setOnAction(e -> primaryStage.setScene(scene2));
      
      //Part D - Add event handling to the log in button on the first scene
      btLogIn.setOnAction(e->{
         //getting emailaddress from the user
         String emailaddress1In = tfEmail.getText();

         //Creating error alert dialog box for password
         Alert errorAlert = new Alert(AlertType.ERROR);
         errorAlert.setTitle("Dialog box");
         errorAlert.setHeaderText("");
         errorAlert.setContentText("Incorrect Password Entered");
         
         //Creating a confirmation alert dialog box for password
         Alert informationAlert = new Alert(AlertType.INFORMATION);
         informationAlert.setTitle("Dialog Box");
         informationAlert.setHeaderText("");
         informationAlert.setContentText("Correct Password Entered");
         
         //Creating error alert dialog box for emailaddress
         Alert errorAlert2 = new Alert(AlertType.ERROR);
         errorAlert2.setTitle("Dialog box");
         errorAlert2.setHeaderText("");
         errorAlert2.setContentText("Incorrect Email Address Entered");
         
         //Creating a confirmation alert dialog box for emailaddress
         Alert informationAlert2 = new Alert(AlertType.INFORMATION);
         informationAlert2.setTitle("Dialog Box");
         informationAlert2.setHeaderText("");
         informationAlert2.setContentText("Correct Email Address Entered");
         
         //Creating a new facebook object called fbdb 
         FacebookDB fbdb = new FacebookDB();
         
         //if the password in the database matches the password entered
         //by the user display a simple alert box
         String correctPassword = fbdb.getUserPasswordFromDatabase(emailaddress1In);
         
         if(tfPassword.getText().equals(correctPassword))
         {
            //showing the dialog box 
            informationAlert.showAndWait();
         }
         else if(tfPassword.getText() == "")
         {
            //showing the dialog box
            errorAlert.showAndWait();
         }
         else
         {
            errorAlert.showAndWait();
         }

         //if email entered does not exist in the database display the appropriate
         //alert dialog box
         //querying the database for all users with the required emailaddress
         String correctEmailAddress = fbdb.getUserEmailAddressFromDatabase(emailaddress1In);
      
         if(tfEmail.getText().equals(correctEmailAddress))
         {
            //showing the dialog box
            informationAlert2.showAndWait();
         }
         else if(tfEmail.getText() == "")
         {
            //showing the dialog box
            errorAlert2.showAndWait();
         }
         else
         {
            errorAlert2.showAndWait();
         }
      });

      //Scene 2
      sceneLayout2 = new BorderPane();
      sceneLayout2.setCenter(getCenterPane());
      sceneLayout2.setRight(getRightPane());
      
      //Constructing scene2 to contain sceneLayout2
      scene2 = new Scene(sceneLayout2);
      
      //Constructing a Label and 4 TextFields
      Label lbSignUp2 = new Label("Sign Up");
      TextField tfFirstName = new TextField(""); 
      TextField tfSurName = new TextField("");
      TextField tfEmail2 = new TextField("");
      TextField tfPassword2 = new TextField("");
      Button btSignUp2 = new Button("Sign Up");
      
      //Adding a prompt text for the textFields
      tfFirstName.setPromptText("Enter your name...");
      tfSurName.setPromptText("Enter your surname...");
      tfEmail2.setPromptText("Enter your email...");
      tfPassword2.setPromptText("Enter your password");
      
      //adding the label and the 4 TextFields to rightPane
      rightPane.getChildren().add(lbSignUp2);
      rightPane.getChildren().add(tfFirstName);
      rightPane.getChildren().add(tfSurName);
      rightPane.getChildren().add(tfEmail2);
      rightPane.getChildren().add(tfPassword2);
      rightPane.getChildren().add(btSignUp2);

      //Allowing the labels and buttons to grow to fill max width of cell
      lbSignUp2.setMaxWidth(Double.MAX_VALUE);
      btSignUp2.setMaxWidth(Double.MAX_VALUE);
      
      //Setting the Border of the label (lbSignUp)
      lbSignUp2.setBorder(new Border(new BorderStroke(Color.BLACK,
                                       BorderStrokeStyle.SOLID,
                                       new CornerRadii(1),
                                       new BorderWidths(1)))); 
                                       
      //Setting the Font of the Label (lbSignUp)
      lbSignUp2.setFont(Font.font("SanSerif", FontWeight.BOLD, 18));                                 
      
      btSignUp2.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE,
                                             CornerRadii.EMPTY,
                                             Insets.EMPTY)));
              
      //Setting the Border of the button (btSignUp2)                                       
      btSignUp2.setBorder(new Border(new BorderStroke(Color.BLACK,
                                       BorderStrokeStyle.SOLID,
                                       new CornerRadii(1),
                                       new BorderWidths(1))));  
                      
      //Setting the Font of the button (btSignUp2)                                 
      btSignUp2.setFont(Font.font("SanSerif", FontWeight.BOLD, 18));  
     
      //Part B - Event handling for second sign up button
      btSignUp2.setOnAction(e->{
         //Extracts all user's details from the textfields on the second scene
         String fNameIn = tfFirstName.getText();
         String sNameIn = tfSurName.getText();
         String emailIn = tfEmail2.getText();
         String passwordIn = tfPassword2.getText();
         
         //Uses the variables above to build the SQL INSERT string
         String sql = "INSERT INTO user VALUES ('"+ emailIn +"', '"+ passwordIn +"','"+ fNameIn +"','"+ sNameIn +"') " +
                      "ON DUPLICATE KEY UPDATE password='"+ passwordIn +"', firstname='"+ fNameIn +"', lastname='"+ sNameIn +"'";  
         
         //Creating a new facebook object called fbdb 
         FacebookDB fbdb = new FacebookDB();          

         //Creating error alert dialog box 
         Alert errorAlert3 = new Alert(AlertType.ERROR);
         errorAlert3.setTitle("Dialog box");
         errorAlert3.setHeaderText("");
         errorAlert3.setContentText("Error: Cannot add null details to the database");
         
         //Error handling to check if user with null details is not added
         if(fNameIn == "" || sNameIn == "" || emailIn == "" || passwordIn == "")
         {
            errorAlert3.showAndWait();
         }
         else
         {
            //using the insertIntoDatabase() to add a new user to the user table
            fbdb.insertIntoDatabase(sql);
            System.out.println("**Details added to the user table**");
         }

         //After signup is completed it should switch back to the main GUI
         primaryStage.setScene(scene);   
      });  
       
   }
   
   public GridPane getTopPane()
   {
      //Construct topPane to be a new GridPane()
      topPane = new GridPane();                       
                                                                                                                             
      return topPane;
   }
   
   public BorderPane getBottomPane()
   {
      //Construct bottomPane to be a new BorderPane()
      bottomPane = new BorderPane();
      
      //Declaring a new ImageView and setting the image
      //to the facebook png
      ImageView imageView = new ImageView();
      imageView.setImage(new Image("image/facebook.png"));
      bottomPane.setLeft(imageView);
      
      //Setting the Border for the bottomPane
      bottomPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                                       BorderStrokeStyle.SOLID,
                                       new CornerRadii(1),
                                       new BorderWidths(1)))); 
                                                                      
      return bottomPane;
   }
   
   public BorderPane getCenterPane()
   {
      //Construct centerPane to be a new BorderPane()
      centerPane = new BorderPane();
      
      //Declaring a new ImageView and setting the image
      //to the facebook png
      ImageView imageView = new ImageView();
      imageView.setImage(new Image("image/facebook.png"));
      centerPane.setLeft(imageView);
      
      //Setting the Border for the centerPane
      centerPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                                       BorderStrokeStyle.SOLID,
                                       new CornerRadii(1),
                                       new BorderWidths(1))));
      return centerPane;
   }
   
   public VBox getRightPane()
   {
      //Construct centerPane to be a new BorderPane()
      rightPane = new VBox(1);     
                                                                                                              
      return rightPane;
   }
   
}   
   
   

