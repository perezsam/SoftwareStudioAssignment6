package main.java;

import processing.core.PApplet;
import de.looksgood.ani.*;


public class AniTest2 extends PApplet {

	public static Cirlce2[] cirlces = new Cirlce2[50];

	public void setup() {
	  size(512,512);
	  smooth();
	  noStroke();

	  // Ani.init() must be called always first!
	  Ani.init(this);

	  // arrange the dots in a cirlce layout
	  for(int i=0; i<cirlces.length; i++) {
	    float angle = map(i,0,cirlces.length,0,TWO_PI);
	    float delay = map(i,0,cirlces.length,1,10);
	    int radius = 200;
	    float x = width/2 + cos(angle)*radius;
	    float y = height/2 + sin(angle)*radius;
	    cirlces[i] = new Cirlce2(this,x,y,delay);
	  }
	}

	public void draw() {
	  background(255);
	  // draw the circles
	  for(int i=0; i<cirlces.length; i++) cirlces[i].draw();
	}
	
}

class Cirlce2 {
	  float x;
	  float y;
	  int diameter = 10;
	  Ani posAniX;
	  Ani posAniY;
	  PApplet parent;

	  Cirlce2(PApplet parent,float theX, float theY, float theDelay) {
	    float duration = 2.2f;
	    x = theX;
	    y = theY;
	    //posAniX = Ani.to(this, duration, theDelay, "x", 512/2, Ani.ELASTIC_OUT);
	    //posAniY = Ani.to(this, duration, theDelay, "y", 512/2, Ani.ELASTIC_OUT);
	    this.parent=parent;
	  }

	  public void draw() {
	    int hex = 0xFF9ACD32;
	    int r = (hex & 0xFF0000) >> 16;
	    int g = (hex & 0xFF00) >> 8;
	    int b = (hex & 0xFF);
	    try{
	    	parent.fill(154,205,50);
		    parent.ellipse(x,y,diameter,diameter);
		    parent.text("   text",x,y);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	  }
	  
	  public void mouseReleased(){
		  System.out.println("click");
	  }
	}
