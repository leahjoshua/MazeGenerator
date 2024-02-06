package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cell
{
    private boolean wallLeft;
    private boolean wallDown;
    private boolean visited;
    
    public Cell()
    {
        wallLeft = true;
        wallDown = true;
        visited = false;
    }
    
    public void visitedTrue()
    {
        visited = true;
    }
    
    public boolean isVisited()
    {
        return visited;
    }
    
    public void removeLeft()
    {
        wallLeft = false;
    }
    
    public void removeDown()
    {
        wallDown = false;
    }
    
    public void print()
    {
        if(wallLeft)
            System.out.print("|");
        else
            System.out.print(" ");
            
        if(wallDown)
            System.out.print("_");
        else
            System.out.print(" ");
    }
    
    public Group print(Group root, int row, int col)
    {
    	int offset = 30;
    	Line lineLeft = new Line();
    	Line lineRight = new Line();
		
        if(wallLeft)
        {
        	lineLeft.setStartX(offset + col * offset);
        	lineLeft.setStartY(offset + row * offset);
        	lineLeft.setEndX(offset + col * offset);
        	lineLeft.setEndY(offset + row * offset + offset);
        	lineLeft.setStrokeWidth(3);
    		lineLeft.setStroke(Color.BLUE);
        }
        //else
        //    System.out.print(" ");
            
        if(wallDown)
        {
        	lineRight.setStartX(offset + col * offset);
        	lineRight.setStartY(offset + row * offset + offset);
        	lineRight.setEndX(offset + col * offset + offset);
        	lineRight.setEndY(offset + row * offset + offset);
        	lineRight.setStrokeWidth(3);
    		lineRight.setStroke(Color.BLUE);
        }
        //else
        //    System.out.print(" ");
        
        root.getChildren().add(lineLeft);
        root.getChildren().add(lineRight);
        
        return root;
    }
}