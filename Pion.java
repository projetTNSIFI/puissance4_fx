package com;

import javafx.scene.shape.Circle;

public class Pion extends Circle{
	public int i;
	public int j;
	
	public Pion(int centerX, int centerY, int radius, int i, int j)
	{
		super(centerX,centerY,radius);
		this.i = i;
		this.j = j;
	}
	
}
