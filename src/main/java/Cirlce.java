package main.java;

import processing.core.PApplet;
import de.looksgood.ani.*;


public class Cirlce{
	  float x;
	  float y;
	  int diameter = 10;
	  Ani posAniX;
	  Ani posAniY;
	  String name;
	  PApplet parent;
	  public String color;
	  

	  Cirlce(PApplet parent,String name, String color,float theX, float theY, float theDelay) {
	    float duration = 2.2f;
	    this.x = theX;
	    this.y = theY;
	    this.posAniX = Ani.to(this, duration, theDelay, "x", parent.width/2, Ani.ELASTIC_OUT);
	    this.posAniY = Ani.to(this, duration, theDelay, "y", parent.height/2, Ani.ELASTIC_OUT);
	    this.name=name;
	    this.parent=parent;
	    this.color=color;
	  }

	  public void draw() {
		  int r = Integer.valueOf( color.substring( 1, 3 ), 16 );
		  int g = Integer.valueOf( color.substring( 3, 5 ), 16 );
		  int b =  Integer.valueOf( color.substring( 5, 7 ), 16 );
			
		  parent.fill(r,g,b);
		  parent.ellipse(this.x,this.y,this.diameter,this.diameter);
		  parent.text("   "+this.name,x,y);
	  }
	}