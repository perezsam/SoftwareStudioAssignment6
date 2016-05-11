package main.java;

import java.util.ArrayList;
import java.util.Random;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import de.looksgood.ani.*;



/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
		
	private final static int width = 1200, height = 650;
	public Cirlce[] cirlces;
	
	
	public void setup() {

		size(width, height);
		smooth();
		loadData();
		
		noStroke();

		  // Ani.init() must be called always first!
		  Ani.init(this);

		 cirlces = new Cirlce[characters.size()];
		  
		  // arrange the dots in a cirlce layout
		  for(int i=0; i<cirlces.length; i++) {
		    float angle = map(i,0,cirlces.length,0,TWO_PI);
		    float delay = map(i,0,cirlces.length,1,10);
		    int radius = 300;
		    float x = width/2 + cos(angle)*radius;
		    float y = height/2 + sin(angle)*radius;
		    
		    
		    cirlces[i] = new Cirlce(this,characters.get(i).name,characters.get(i).color,x,y,delay);
		  }
		  for(int i=0; i<cirlces.length; i++) {
			    System.out.println(cirlces[i].name);			    
		  }
		
	}
	
	

	public void draw() {
		background(255);
		for(int i=0; i<cirlces.length; i++) cirlces[i].draw();
		
		/*for(int i=0;i<characters.size();i++){
			for(int j=0;j<characters.get(i).getTargets().size();j++){
				//System.out.println(characters.get(i).x+","+characters.get(i).y+","+characters.get(i).getTargets().get(j).x+","+characters.get(i).getTargets().get(j).y);
				stroke(204, 102, 0);
				line(characters.get(i).x,characters.get(i).y,characters.get(i).getTargets().get(j).x,characters.get(i).getTargets().get(j).y);
			}
		}*/
	}

	private void loadData(){
		data = loadJSONObject(path+file);
		nodes= data.getJSONArray("nodes");
		links= data.getJSONArray("links");
		
		characters = new ArrayList<Character>();
		
		for (int i = 0; i < nodes.size(); i++) {
			JSONObject node = nodes.getJSONObject(i);
			String nodeName = node.getString("name");
			int nodeValue = node.getInt("value");
			String nodeColor = node.getString("colour");
			
			Character character = new Character(this, nodeName, nodeColor,randInt(100, width-100),randInt(100, height-100));
			character.display();
			
			characters.add(character);
			
			//System.out.println(nodeName + ", " + nodeValue + ", " + nodeColor);
		}
		
		for (int i = 0; i < links.size(); i++) {
			JSONObject link = links.getJSONObject(i);
			int linkSource = link.getInt("source");
			int linkTarget = link.getInt("target");
			int linkValue = link.getInt("value");
			
			//System.out.println(linkSource + ", " + linkTarget + ", " + linkValue);
			
			//System.out.println(characters.get(linkSource).name+" - "+characters.get(linkTarget).name);
			
			characters.get(linkSource).addTarget(characters.get(linkTarget));
		}
	}
	
	public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
