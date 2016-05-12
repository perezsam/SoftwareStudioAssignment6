package main.java;

import java.util.ArrayList;
import java.util.Random;

import controlP5.CheckBox;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.Textlabel;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import de.looksgood.ani.*;

/**
 * This class is for sketching outcome using Processing You can do major UI
 * control and some visualization in this class.
 */
@SuppressWarnings("serial")
public class MainApplet extends PApplet {
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";

	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;

	private final static int width = 1200, height = 800;
	public Cirlce[] cirlces;

	ControlP5 cp5;
	CheckBox checkbox;
	Textlabel myTextlabelA;
	
	public ArrayList<Float> listOfXPositions;
	public ArrayList<Float> listOfYPositions;

	public int counter;
	
	public void setup() {

		counter=0;
		
		listOfXPositions=new ArrayList<Float>();
		listOfYPositions=new ArrayList<Float>();
		
		size(width, height);
		smooth();
		
		cp5 = new ControlP5(this);
		checkbox = cp5.addCheckBox("checkBox")
		                .setPosition(10, 10)
		                .setColorForeground(color(120))
		                .setColorActive(color(255))
		                .setColorLabel(color(255))
		                .setSize(40, 40)
		                .setItemsPerRow(3)
		                .setSpacingColumn(50)
		                .setSpacingRow(20)
		                ;
		
		myTextlabelA = cp5.addTextlabel("label")
                .setText(" ")
                .setPosition(width/(2.3f),height*3/(3.2f))
                .setColorValue(0xffffff00)
                .setFont(createFont("Georgia",20))
                ;
		

		
		loadData();

		noStroke();

		// Ani.init() must be called always first!
		Ani.init(this);

		cirlces = new Cirlce[characters.size()];

		// arrange the dots in a cirlce layout
		for (int i = 0; i < cirlces.length; i++) {
			float angle = map(i, 0, cirlces.length, 0, TWO_PI);
			float delay = map(i, 0, cirlces.length, 1, 10);
			//System.out.println(delay);
			int radius = 300;
			float x = width / 2 + cos(angle) * radius;
			float y = height / 2 + sin(angle) * radius;

			listOfXPositions.add(x);
			listOfYPositions.add(y);
			
			cirlces[i] = new Cirlce(this, characters.get(i).name, characters.get(i).color, x, y, delay,i);
			characters.get(i).x=x;
			characters.get(i).y=y;
			
			checkbox.addItem(characters.get(i).name, i);
		}
		for (int i = 0; i < cirlces.length; i++) {
			System.out.println(cirlces[i].name);
		}
		
	}
	
	
	
	public void controlEvent(ControlEvent theEvent) {
		  if (theEvent.isFrom(checkbox)) {
		    //myColorBackground = 0;
		    System.out.println("got an event from "+checkbox.getName()+"\t\n");
		    // checkbox uses arrayValue to store the state of 
		    // individual checkbox-items. usage:
		    System.out.println(checkbox.getArrayValue());
		    int col = 0;
		    
		    counter=1;
		    
		    for (int i=0;i<checkbox.getArrayValue().length;i++) {
		      int n = (int)checkbox.getArrayValue()[i];
		      System.out.println(n);
		      if(n==1) {
		    	  cirlces[i].untriggerAnimation(listOfXPositions.get(i),listOfYPositions.get(i));
		      }else{
		    	  cirlces[i].triggerAnimation();
		      }
		    }
		    
		    
		    
		    System.out.println();    
		  }
		}

	public void draw() {
		background(0);
		
		if (counter>=1){
			counter++;
			myTextlabelA.setText("Drawing... please wait...");
			
			if(counter>650){
				counter=0;
				myTextlabelA.setText("");
			}
		}
		
		
		
		
		//textSize(26);
		//text(message, width/2, 3*height/4);
		
		for (int i = 0; i < cirlces.length; i++)
			cirlces[i].draw();

		if((counter>600)||(counter==0)){
			for (int i=0;i<checkbox.getArrayValue().length-1;i++) {
		    	int n1 = (int)checkbox.getArrayValue()[i];
		    	
		    	
		    	if(n1==1){
		    		for(int j=0;j<characters.get(i).getTargets().size();j++){
		    			int n2=(int)checkbox.getArrayValue()[j];
		    			
		    			if(n2==1){
		    				//both characters are in the circle
		    				stroke(204, 102, 0);
		    				line(characters.get(i).x,characters.get(i).y,characters.get(j).x,characters.get(j).y);
		    				
		    				float textCoordinateX=abs(characters.get(i).x+characters.get(j).x)/2;
		    				float textCoordinateY=abs(characters.get(i).y+characters.get(j).y)/2;
		    				text("A", textCoordinateX,textCoordinateY);
		    			}else{
		    				//stroke(0, 0, 0);
		    				//line(characters.get(i).x,characters.get(i).y,characters.get(j).x,characters.get(j).y);
		    			}
		    			
		    		}
		    	}
		    }
		}
		
		
		
		/*
		 * for(int i=0;i<characters.size();i++){ for(int
		 * j=0;j<characters.get(i).getTargets().size();j++){
		 * //System.out.println(characters.get(i).x+","+characters.get(i).y+","+
		 * characters.get(i).getTargets().get(j).x+","+characters.get(i).
		 * getTargets().get(j).y); stroke(204, 102, 0);
		 * line(characters.get(i).x,characters.get(i).y,characters.get(i).
		 * getTargets().get(j).x,characters.get(i).getTargets().get(j).y); } }
		 */
	}

	private void loadData() {
		data = loadJSONObject(path + file);
		nodes = data.getJSONArray("nodes");
		links = data.getJSONArray("links");

		characters = new ArrayList<Character>();

		for (int i = 0; i < nodes.size(); i++) {
			float angle = map(i, 0, nodes.size(), 0, TWO_PI);
			float delay = map(i, 0, nodes.size(), 1, 10);
			//System.out.println(delay);
			int radius = 300;
			float xCharacter = width / 2 + cos(angle) * radius;
			float yCharacter = height / 2 + sin(angle) * radius;
			
			
			JSONObject node = nodes.getJSONObject(i);
			String nodeName = node.getString("name");
			int nodeValue = node.getInt("value");
			String nodeColor = node.getString("colour");

			Character character = new Character(this, nodeName, nodeColor, xCharacter,yCharacter);
			character.display();

			characters.add(character);

			// System.out.println(nodeName + ", " + nodeValue + ", " +
			// nodeColor);
		}

		for (int i = 0; i < links.size(); i++) {
			JSONObject link = links.getJSONObject(i);
			int linkSource = link.getInt("source");
			int linkTarget = link.getInt("target");
			int linkValue = link.getInt("value");

			// System.out.println(linkSource + ", " + linkTarget + ", " +
			// linkValue);

			// System.out.println(characters.get(linkSource).name+" -
			// "+characters.get(linkTarget).name);

			characters.get(linkSource).addTarget(characters.get(linkTarget));
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
