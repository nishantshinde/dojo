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

public class HelloWorld extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        
        EventHandler toggler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	ToggleButton btn = (ToggleButton)event.getSource();
            	if(btn.isSelected()) {
            		btn.setText("*");
            	} else {
            		btn.setText(" ");
            	}
            }
        };
        
        //StackPane root = new StackPane();
        GridPane root = new GridPane();
        ToggleButton btn;
        int r = 5, c= 6;
        int gap = 4;
        Insets cellInsets = new Insets(gap,gap,gap,gap);
        for (int i=0;i<r*c;i++ ) {
        	btn = getDefaultButton(toggler);
        	root.getChildren().add(btn);
        	//root.add(btn, i%c, i/c);
        	root.setConstraints(btn, i%c, i/c, 1, 1, 
        			HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, 
        			cellInsets);
        }
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private static final int SIZE = 25;
    private ToggleButton getDefaultButton(EventHandler toggler) {
    	ToggleButton btn = new ToggleButton();
    	btn.setText(" ");
    	btn.setOnAction(toggler);
    	btn.setMinSize(SIZE, SIZE);
    	btn.setMaxSize(SIZE, SIZE);
    	return btn;
    }
}
