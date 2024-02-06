package application;

import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
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

import java.lang.Math;

public class Maze
{
    private int row;
    private int col;
    private int size;
    private Cell[][] maze;
    DisjointSets mazeSet;
    
    public Maze(int row, int col) {
        this.row = row;
        this.col = col;
        size = row * col;
        maze = new Cell[row][col];
        mazeSet = new DisjointSets(size);
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                maze[i][j] = new Cell();
            }
        }
        
        maze[0][0].removeLeft();
    }
    
    public void generateMaze(Stage stage) throws InterruptedException {
        int randRow;
        int randCol;
        int randDir;
        
        int prevRow = randInt(0,row - 1);
        int prevCol = randInt(0,col - 1);
        int prevDir = randInt(0,3);
        
        int newDir;
        int reset;
        
        int count = 0;
        // up = 0 
        // left = 1 
        // down = 2 
        // right = 3
        // randRow * col + randCol
        while (count < size - 1)
        {
        	int wait = 40;
        	
            randRow = prevRow;
            randCol = prevCol;
            randDir = prevDir;
            
            newDir = randInt(0,4);
            if(newDir != 0)
                randDir = randInt(0,3);
                
            reset = randInt(0,100);
            if(reset == 0)
            {
                randRow = randInt(0,row - 1);
                randCol = randInt(0,col - 1);
                randDir = randInt(0,3);
            }
                
            
            switch(randDir) {
                case 0: { //up
                    if(randRow == 0 || mazeSet.find(randRow * col + randCol) == mazeSet.find((randRow - 1) * col + randCol))
                        break;
                    maze[randRow - 1][randCol].removeDown();
                    mazeSet.union(mazeSet.find(randRow * col + randCol), mazeSet.find((randRow - 1) * col + randCol));
                    count++;
                    prevRow = randRow - 1;
                    prevCol = randCol;
                    prevDir = 0;
                    print(stage);
                    Thread.sleep(wait);
                    break;
                }
                case 1: { //left
                    if(randCol == 0 || mazeSet.find(randRow * col + randCol) == mazeSet.find(randRow * col + (randCol - 1)))
                        break;
                    maze[randRow][randCol].removeLeft();
                    mazeSet.union(mazeSet.find(randRow * col + randCol), mazeSet.find(randRow * col + (randCol - 1)));
                    count++;
                    prevRow = randRow;
                    prevCol = randCol - 1;
                    prevDir = 1;
                    print(stage);
                    Thread.sleep(wait);
                    break;
                }
                case 2: { //down
                    if(randRow == (row - 1) || mazeSet.find(randRow * col + randCol) == mazeSet.find((randRow + 1) * col + randCol))
                        break;
                    maze[randRow][randCol].removeDown();
                    mazeSet.union(mazeSet.find(randRow * col + randCol), mazeSet.find((randRow + 1) * col + randCol));
                    count++;
                    prevRow = randRow + 1;
                    prevCol = randCol;
                    prevDir = 2;
                    print(stage);
                    Thread.sleep(wait);
                    break;
                }
                case 3: { // right
                    if(randCol == (col - 1) || mazeSet.find(randRow * col + randCol) == mazeSet.find(randRow * col + (randCol + 1)))
                        break;
                    maze[randRow][randCol + 1].removeLeft();
                    mazeSet.union(mazeSet.find(randRow * col + randCol), mazeSet.find(randRow * col + (randCol + 1)));
                    count++;
                    prevRow = randRow;
                    prevCol = randCol + 1;
                    prevDir = 3;
                    print(stage);
                    Thread.sleep(wait);
                    break;
                }
            }
        }
    }
    
    public void print(Stage stage) {
    	int offset = 30;
    	Group root = new Group();
		Scene scene = new Scene(root,offset * col + offset * 2,offset * row + offset * 2,Color.BLACK);
		
		Line topLine = new Line();
		topLine.setStartX(offset);
		topLine.setStartY(offset);
		topLine.setEndX(offset + col * offset);
		topLine.setEndY(offset);
		topLine.setStrokeWidth(3);
		topLine.setStroke(Color.BLUE);
		
		Line rightLine = new Line();
		rightLine.setStartX(offset + col * offset);
		rightLine.setStartY(offset);
		rightLine.setEndX(offset + col * offset);
		rightLine.setEndY(row * offset);
		rightLine.setStrokeWidth(3);
		rightLine.setStroke(Color.BLUE);
		
		root.getChildren().add(topLine);
		root.getChildren().add(rightLine);
		
		for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                root = maze[i][j].print(root, i, j);
            }
        }
		
		stage.setScene(scene);
		stage.show();
    }
    
    private int randInt(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
 
}
