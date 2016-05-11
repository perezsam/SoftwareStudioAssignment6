package main.java;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import de.looksgood.ani.*;
/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	
	public float x, y, radius;
	public String name;
	public String color;
	
	private ArrayList<Character> targets;
	
	Ani animation;

	public Character(MainApplet parent, String name, String color, float x, float y){
		this.parent=parent;
		this.name=name;
		this.x=x;
		this.y=y;
		this.targets = new ArrayList<Character>();
		this.color=color;
		
	}

	public void display(){
		/*int r = (color & 0xFF0000) >> 16;
		int g = (color & 0xFF00) >> 8;
		int b = (color & 0xFF);
		
		this.parent.fill(r,g,b);
		this.parent.text(this.name,this.x,this.y);*/
		
	}
	

	public void addTarget(Character target){
		this.targets.add(target);
	}
	
	public ArrayList<Character> getTargets(){
		return this.targets;
	}
	
	public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
	
}
