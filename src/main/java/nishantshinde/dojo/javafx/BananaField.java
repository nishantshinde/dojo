package nishantshinde.dojo.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;

public class BananaField extends Application {
	
    public static final int NUMBER_OF_ROWS = 10, NUMBER_OF_COLUMNS= 10;
    private static final int CELL_SIZE = 25;
    private static final int CELL_PADDING = 4;
    private static final String CELL_CONTENTS = "#"; // "#*+" 
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Banana Trees ");
        
        EventHandler toggler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	ToggleButton btn = (ToggleButton)event.getSource();
            	if(btn.isSelected()) {
            		btn.setText(CELL_CONTENTS);
            	} else {
            		btn.setText(" ");
            	}
            }
        };
        
        //StackPane root = new StackPane();
        GridPane root = new GridPane();
        ToggleButton btn;
        
        Insets cellInsets = new Insets(CELL_PADDING,CELL_PADDING,CELL_PADDING,CELL_PADDING);
        for (int i=0;i<NUMBER_OF_ROWS*NUMBER_OF_COLUMNS;i++ ) {
        	btn = getDefaultButton(toggler);
        	root.getChildren().add(btn);
        	//root.add(btn, i%c, i/c);
        	root.setConstraints(btn, i%NUMBER_OF_COLUMNS, i/NUMBER_OF_COLUMNS, 1, 1, 
        			HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, 
        			cellInsets);
        }
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    private ToggleButton getDefaultButton(EventHandler toggler) {
    	ToggleButton btn = new ToggleButton();
    	btn.setText(" ");
    	btn.setOnAction(toggler);
    	btn.setMinSize(CELL_SIZE, CELL_SIZE);
    	btn.setMaxSize(CELL_SIZE, CELL_SIZE);
    	return btn;
    }
    
}