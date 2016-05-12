package main.java;

import processing.core.PApplet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.ControlP5;
import de.looksgood.ani.*;


public class Cirlce extends PApplet{

	  float x;
	  float y;
	  int diameter = 10;
	  Ani posAniX;
	  Ani posAniY;
	  String name;
	  PApplet parent;
	  public String color;
	  float theDelay;
	  
	  
	  
	  
	  public int indexOfCircle;
	  
	  public boolean toggleValue = false;
	  public boolean toggleValue1 = false;
	  public boolean toggleValue2 = false;
	  
	  
	  Cirlce(PApplet parent,String name, String color,float theX, float theY, float theDelay, int indexOfCirlce) {
	    float duration = 2.2f;
	    this.x = theX;
	    this.y = theY;
	   // this.posAniX = Ani.to(this, duration, theDelay, "x", parent.width/2, Ani.ELASTIC_OUT);
	    //this.posAniY = Ani.to(this, duration, theDelay, "y", parent.height/2, Ani.ELASTIC_OUT);
	    this.name=name;
	    this.parent=parent;
	    this.color=color;
	    
	    this.theDelay=theDelay;
	    
	    this.indexOfCircle=indexOfCirlce;
	  }

	  public void draw() {
		  int r = Integer.valueOf( color.substring( 1, 3 ), 16 );
		  int g = Integer.valueOf( color.substring( 3, 5 ), 16 );
		  int b =  Integer.valueOf( color.substring( 5, 7 ), 16 );
			
		  parent.fill(r,g,b);
		  parent.ellipse(this.x,this.y,this.diameter,this.diameter);
		  parent.text("   "+this.name,x,y);
		  
	  }
	  
	  public void triggerAnimation(){
		  this.posAniX = Ani.to(this, 0.1f, theDelay, "x", parent.width/(1.2f), Ani.QUINT_IN_OUT);
		  this.posAniY = Ani.to(this, 0.1f, theDelay, "y", 30f+15f*indexOfCircle, Ani.QUINT_IN_OUT);
	  }
	  
	  public void untriggerAnimation(float originalX, float originalY){
		  this.posAniX = Ani.to(this, 0.1f, theDelay, "x", originalX, Ani.QUINT_IN_OUT);
		  this.posAniY = Ani.to(this, 0.1f, theDelay, "y", originalY, Ani.QUINT_IN_OUT);
	  }
	  
	}

