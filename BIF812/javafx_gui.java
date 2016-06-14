package assignment;
/*
I declare that the attached assignment is my own work in accordance
with Seneca Academic Policy. No part of this assignment has been
copied manually or electronically from any other source (including web
sites) or distributed to other students.
Name : Keshav Dial
Student ID: 250526958
*/


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class Assignment2 extends Application{
	/*
	 * Main Method: 
	 * Launches the stand-alone JavaFX GUI Application
	 * Note: that launch can only be called once otherwise
	 * an exception will be called.
	 */
		public static void main(String[] args){
			
			launch (args);
		}
		/*
		 * This method is used to set the default settings in the GUI.
		 * It accepts a CheckBox and a corresponding boolean value
		 * that will indicate whether or not it is checked. 
		 * It accepts a ComboBox (a drop down menu) and the corresponding
		 * default value associated within that ComboBox. 
		 * It accepts a Toggle Group (Group of radio buttons) and the corresponding
		 * default radio button of the Toggle Group.
		 * It accepts two TextArea's, input and output, both of which it will
		 * set to an empty String to clear the contents.  
		 */
		public void setDefaults(CheckBox check, boolean checkValue,
			ComboBox<Integer> dropDown, int	dropDownValue,
			ToggleGroup radioGroup, RadioButton radioValue,
			TextArea input, TextArea output){
				dropDown.setValue(dropDownValue);
				check.setSelected(checkValue);
				radioGroup.selectToggle(radioValue);
				String empty = "";
				input.setText(empty);
				output.setText(empty);
		}
		
		@Override
		public void start(Stage primaryStage) throws Exception {
			
			/*
			 * Sets the Title of the Stage (Title of the Application)
			 */
			primaryStage.setTitle("Java Assignment 2");
			
			/*
			 * GRID: 
			 * Creates Grid, sets padding and constrains the Columns to certain sizes (col1 and col2)
			 */
			final GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets (25,25,25,25));
			ColumnConstraints col1 = new ColumnConstraints();
			col1.setPercentWidth(30);
			ColumnConstraints col2 = new ColumnConstraints();
			col2.setPercentWidth(40);
			grid.getColumnConstraints().addAll(col2,col1,col1,col1);
			
			/*
			 * SCENE: 
			 * Creates a new scene (equivalent to JPanel in Swing). 
			 * Only one scene is allowed on Stage at a time.
			 */
			Scene scene = new Scene(grid, 700, 525);
			
			/*
			 * HEADER: 
			 * Creates the header on the scene that says "Java Assignment 2"
			 */
			Text scenetitle = new Text("Java Assignment 2");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 0, 0, 2, 1);//Adds Header at Col 0, Row 0. Allows overflow onto 2 columns, forces into 1 row. 
			
			/*
			 * DNA INPUT SECTION:
			 * Creates the Text Label and Text Area
			 */
			Label rawDNASequence = new Label("Enter a Raw DNA Sequence");
			grid.add(rawDNASequence, 0, 1, 2, 1);
			final TextArea inputDNA = new TextArea();
			inputDNA.setStyle("-fx-font-family:Monaco, MONOSPACE");//Sets font to Monospace font 'Monaco'
			grid.add(inputDNA, 0, 2, 2, 2);//Adds InputDNA TextArea at Col 0, Row 2. Allows overflow onto 2 columns and 2 rows.
			
			/*
			 * CHARACTER LENGTH SECTION:
			 * Creates the Label and the Drop Down
			 */
			Label characterSelection = new Label ("Line Character Length");
			grid.add(characterSelection, 2, 1);
			//ComboBox (drop-down) requires an ObservableList instance as input for its selection options 
			ObservableList<Integer> options =
				FXCollections.observableArrayList(
					40, 50, 60, 70
				);
			final ComboBox<Integer> charDropDown = new ComboBox<Integer>(options);
			grid.add(charDropDown, 2, 2);
			
			/*
			 * CHARACTER CASE SELECTION: 
			 * Creates the Radio Group of Buttons for Upper and Lower Case. 
			 * Labels are built into instances (ex.setText).
			 */
			Label caseLabel = new Label("Toggle Case");
			grid.add(caseLabel, 3, 1);
			
			final ToggleGroup caseGroup = new ToggleGroup();
			final RadioButton upper = new RadioButton();
			upper.setText("Upper");
			upper.setToggleGroup(caseGroup);
			final RadioButton lower = new RadioButton();
			lower.setText("Lower");
			lower.setToggleGroup(caseGroup);
			grid.add(upper, 3, 2);
			grid.add(lower, 3, 3);
			
			/*
			 * INCLUDE SPACES SECTION: 
			 * Creates the CheckBox for including spaces. Label is Built-in. 
			 */
			final CheckBox fastaCheck = new CheckBox("Include Spaces");
			fastaCheck.setIndeterminate(false);
			grid.add(fastaCheck,2,3 );
			
			/*
			 * OUTPUT SECTION: 
			 * Creates the Output DNA TextArea and the Label.
			 */
			Label formmatedSequence = new Label("Formatted Sequence");
			grid.add(formmatedSequence, 0, 5, 1, 1);
			final TextArea outputDNA = new TextArea();
			outputDNA.setStyle("-fx-font-family:Monaco, MONOSPACE");
			grid.add(outputDNA, 0, 6, 4, 1);
			
			//Sets the Defaults values using the setDefaults Method
			setDefaults(fastaCheck, false, charDropDown, 60, caseGroup, lower, inputDNA, outputDNA);
			
			/*
			 * STATISTICS SECTION: 
			 * Creates the a "Statistics" label that will be updated upon submission 
			 */
			final Label totals = new Label("Statistics:");
			grid.add(totals, 0, 7, 4, 2);

			
			/*
			 * BUTTONS SECTION: 
			 * Creates the Submit and Reset Buttons, and their corresponding ActionEvents
			 */
			Button submit = new Button("Submit");
			//HBox is used here to create a new alignment pattern on Row 10 (BOTTOM_LEFT vs grid's CENTER)
			HBox bottomLayoutPane = new HBox(10);
			bottomLayoutPane.setAlignment(Pos.BOTTOM_LEFT);
			//adds the submit button to the HBox
			bottomLayoutPane.getChildren().add(submit);
			//adds the HBox to the grid
			grid.add(bottomLayoutPane, 0, 10);
			
			/*
			 * SUBMIT BUTTON:
			 * First fetches the InputDNA's String and confirms that string is not empty;
			 * performs a RegEx match to confirm all characters are either 'A', 'C', 'T', 
			 * 'G' (case insensitive).  If the string is empty or there are any 'illegal'
			 * characters, then an Alert is shown describing the error. Else, the statistics
			 * are calculated and the placeholder label is filled.
			 * Following the calculation, the user selected settings are checked and the 
			 * outputDNA TextArea is formatted accordingly.
			 */
			submit.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent e){
					String inputSeq = inputDNA.getText();					
					//STRING PROCESSING
					String regEx = "[^actg]";
					Pattern r = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
					Matcher regEx101 = r.matcher(inputSeq);

					if(regEx101.find()){
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("ERROR");
						alert.setHeaderText("Problem with Raw DNA Sequence!");
						alert.setContentText("Your Input DNA seuquence contains illegal characters. Limit your sequence to 'a','c','t' and 'g'");
						alert.showAndWait();
						
					}else if(inputSeq.isEmpty()){
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("ERROR");
						alert.setHeaderText("Problem with Raw DNA Sequence!");
						alert.setContentText("You did not provide an Input");
						alert.showAndWait();
					}else{
						//STATISTICS CALCULATION
						int aCounter, cCounter, tCounter, gCounter; 
						aCounter = cCounter = tCounter = gCounter = 0;		
						
						//Change all to lower case.
						inputSeq = inputSeq.replaceAll("A","a");
						inputSeq = inputSeq.replaceAll("C","c");
						inputSeq = inputSeq.replaceAll("G","g");
						inputSeq = inputSeq.replaceAll("T","t");
						
						for( int i=0; i<inputSeq.length(); i++ ) {
						    if(inputSeq.charAt(i) == 'a' ) {
						        aCounter++;
						    } else if(inputSeq.charAt(i)=='c'){
						    	cCounter++;
						    } else if(inputSeq.charAt(i)=='g'){
						    	gCounter++;
						    } else{
						    	tCounter++;
						    }
						}
						double percentA = ((double) aCounter)*100/((double)inputSeq.length());
						double percentC = ((double) cCounter)*100/((double)inputSeq.length());
						double percentG = ((double) gCounter)*100/((double)inputSeq.length());
						double percentT = ((double) tCounter)*100/((double)inputSeq.length());
						String calculations = "Statistics: # of A's: "+Integer.toString(aCounter)+" ("+Integer.toString((int)percentA)+
									  "%)\t# of C's: "+Integer.toString(cCounter)+" ("+Integer.toString((int)percentC)+
									  "%)\t# of G's: "+Integer.toString(gCounter)+" ("+Integer.toString((int)percentG)+
									  "%)\t# of T's: "+Integer.toString(tCounter)+" ("+Integer.toString((int)percentT)+
									  "%)\nSequence length: "+Integer.toString(inputSeq.length());
						
						totals.setText(calculations);
						
						//OUTPUT SEQUENCE
						
						//CASE
						if(caseGroup.getSelectedToggle() == upper){
							inputSeq = inputSeq.replaceAll("a","A");
							inputSeq = inputSeq.replaceAll("c","C");
							inputSeq = inputSeq.replaceAll("g","G");
							inputSeq = inputSeq.replaceAll("t","T");
						}
											
						//Adding LineBreaks
						int indexOnBreak = charDropDown.getValue();
						String RegExBreak = ".{"+Integer.toString(indexOnBreak)+"}";
						inputSeq = inputSeq.replaceAll(RegExBreak, "$0\n");
						
						//Adding Spaces
						if(fastaCheck.isSelected()){
							inputSeq = inputSeq.replaceAll(".{10}", "$0 ");
						}
						outputDNA.setText(inputSeq);
					}
				}
			});
			
			/*
			 * RESET BUTTON:
			 * Creates the Reset Button. 
			 * Creates an HBox to induce another alignment on row 10, (BOTTOM_RIGHT).
			 * Adds the Reset Button to the HBox, and adds the HBox to the Grid.
			 * When the Reset Button is pressed, an Alert is called that displays
			 * YESNOCANCEL options. Unless YES is pressed, no action is performed.
			 * If Yes is pressed, the setDefaults method is performed and the GUI
			 * returns to its default settings.
			 */
			Button reset = new Button("Reset");
			HBox leftLayoutPane = new HBox(10);
			leftLayoutPane.setAlignment(Pos.BOTTOM_RIGHT);
			leftLayoutPane.getChildren().add(reset);
			grid.add(leftLayoutPane, 3, 10);
			reset.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					Alert confirm = new Alert(AlertType.CONFIRMATION, "Are you sure you want to clear your current settings?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
					confirm.setTitle("CONFIRMATION");
					confirm.showAndWait().ifPresent(response -> {
						if(response == ButtonType.YES){
							setDefaults(fastaCheck, false, charDropDown, 60, caseGroup, lower, inputDNA, outputDNA);
						}
					});
				}
			});
			//grid.setGridLinesVisible(true);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		
}
