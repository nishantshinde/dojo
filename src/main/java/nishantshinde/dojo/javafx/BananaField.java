package nishantshinde.dojo.javafx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class BananaField extends Application {
	
    public static final int NUMBER_OF_ROWS = 12, NUMBER_OF_COLUMNS= 12;
    private static final int CELL_SIZE = 25;
    private static final int CELL_PADDING = 4;
    private static final String CELL_CONTENTS = "#"; // "#*+" 
    private static final Insets CELL_INSETS = new Insets(CELL_PADDING,CELL_PADDING,CELL_PADDING,CELL_PADDING);
    
    private final List<BananaButton> buttons = new ArrayList<BananaButton>();
    private final Map<String,BananaButton> buttonsMap = new HashMap<String, BananaButton>();
    private TextArea textArea; 
    
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

        generateBananaButtons(root, toggler);
        
        generateStringControls(root);
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void generateBananaButtons(GridPane root, EventHandler toggler) {
        BananaButton btn;
        int r,c;

        for (int i=0;i<NUMBER_OF_ROWS*NUMBER_OF_COLUMNS;i++ ) {
        	r = i/NUMBER_OF_COLUMNS; c=i%NUMBER_OF_COLUMNS;
        	btn = getDefaultButton(r,c,toggler);
        	buttons.add(btn);
        	buttonsMap.put(btn.getData(), btn);
        	root.getChildren().add(btn);
        	root.setConstraints(btn, c, r, 1, 1, 
        			HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, 
        			CELL_INSETS);
        }
    }
    
    private void generateStringControls(GridPane root) {
    	
    	Button btn = new Button(" Generate() ");
    	btn.setOnAction(generateStringEventHandler());
    	
        root.getChildren().add(btn);
        root.setConstraints(btn, 0, NUMBER_OF_ROWS, NUMBER_OF_COLUMNS+1, 1,
        		HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, 
    			CELL_INSETS);
        
        textArea = new TextArea();
        textArea.setEditable(true);
        root.getChildren().add(textArea);
        root.setConstraints(textArea, 0, NUMBER_OF_ROWS+1, NUMBER_OF_COLUMNS+1, 1,
        		HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, 
    			CELL_INSETS);

    	Button loadBtn = new Button(" Load() ");
    	loadBtn.setOnAction(loadStringEventHandler());
        root.getChildren().add(loadBtn);
        root.setConstraints(loadBtn, 0, NUMBER_OF_ROWS+2, NUMBER_OF_COLUMNS+1, 1,
        		HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, 
    			CELL_INSETS);

        
    }

    private EventHandler generateStringEventHandler() {
    	return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	StringBuilder sb = new StringBuilder("(");
            	for(BananaButton btn:buttons) {
            		if(btn.isSelected()) {
            			sb.append(btn.getData()).append(',');
            		}
            	}
            	if(sb.charAt(sb.length()-1)==',') {
            		sb.deleteCharAt(sb.length()-1);
            	}
            	sb.append(')');
            	System.out.println(sb.toString());
            	textArea.setText(sb.toString());
            }
        };
    }
    
    private EventHandler loadStringEventHandler() {
    	return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String stringContents = textArea.getText();
            	System.out.println(stringContents);
            	int index=1;
            	String s1, s2;
            	int i1, i2;
            	index = stringContents.indexOf('(',index);
                while(index>0&&index<stringContents.length()-2) {
                	index = stringContents.indexOf('(',index);
                	s1 = stringContents.substring(index+1, index=stringContents.indexOf(',',index));
                	s2 = stringContents.substring(index+1, index=stringContents.indexOf(')',index));
//                	System.out.println(s1 + " " + s2);
                	i1 = Integer.parseInt(s1);
                	i2 = Integer.parseInt(s2);
                	System.out.println(generateId(i1, i2));
                }          	
            }
        };
    }
    
    private BananaButton getDefaultButton(int r, int c, EventHandler toggler) {
    	BananaButton btn = new BananaButton(generateId(r,c));
    	btn.setText(" ");
    	btn.setOnAction(toggler);
    	btn.setMinSize(CELL_SIZE, CELL_SIZE);
    	btn.setMaxSize(CELL_SIZE, CELL_SIZE);
    	return btn;
    }
    
    class BananaButton extends ToggleButton {

    	private String data;
    	
		public BananaButton(String data) {
			super();
			this.data = data;
		}

		public String getData() {
			return data;
		}
    	
    }

    public String generateId(int r, int c) {
    	return "("+r+","+c+")";
    }
    
}