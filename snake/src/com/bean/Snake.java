package com.bean;

/**
 * 
 * @Description 贪吃蛇类，封装了蛇的一些私有属性
 * @author tc  Email:935706071@qq.com
 * @version
 * @date 2020年12月9日下午4:46:58
 */
public class Snake {
	private int len;
	private int[] snakeX = new int[40];
	private int[] snakeY = new int[40];
	private int orient = 1;	// 方向，1向右，2向下，3向左，4向右
	private int score;

	public Snake() {
	}
	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public int[] getSnakeX() {
		return snakeX;
	}

	public void setSnakeX(int[] snakeX) {
		this.snakeX = snakeX;
	}

	public int[] getSnakeY() {
		return snakeY;
	}

	public void setSnakeY(int[] snakeY) {
		this.snakeY = snakeY;
	}

	public int getOrient() {
		return orient;
	}

	public void setOrient(int orient) {
		this.orient = orient;
	}
	
}
