package com.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.bean.Food;
import com.bean.Snake;
import com.service.SnakeController;

/**
 * 
 * @Description 自定义的画布类，继承自JPanel
 * @author tc Email:935706071@qq.com
 * @version
 * @date 2020年12月8日下午7:33:50
 */
public class SnakePanel extends JPanel implements KeyListener, ActionListener {

	ImageIcon titleIcon = new ImageIcon("static/image/title.jpg");
	ImageIcon bodyIcon = new ImageIcon("static/image/body.png");
	ImageIcon lefIcon = new ImageIcon("static/image/left.png");
	ImageIcon upIcon = new ImageIcon("static/image/up.png");
	ImageIcon righIcon = new ImageIcon("static/image/right.png");
	ImageIcon downIcon = new ImageIcon("static/image/down.png");
	ImageIcon foodIcon = new ImageIcon("static/image/food.png");
	Snake snake = new Snake();
	SnakeController snakeController = new SnakeController();
	Food food = new Food();
	boolean isFailed = false;
	boolean isStart = false;
	boolean isFirstStart = true;
	Timer timer = new Timer(100, this); // 定时器，在指定时间触发一个或多个Action，类需要实现ActionListenner接口

	public SnakePanel() {
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
		snakeController.initSnake(snake); // 对蛇初始化
		snakeController.initFood(food);
		snakeController.initBGM();
	}

	/**
	 * 
	 * @Description 重写的paintComponent方法，用来绘制组件
	 * @author tc
	 * @data 2020年12月8日下午7:38:53
	 * @param g 画笔对象
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		titleIcon.paintIcon(this, g, 25, 11);
		g.fillRect(25, 75, 850, 600);
		g.setColor(Color.white);
		g.drawString("Len " + snake.getLen(), 750, 35);
		g.drawString("Score " + snake.getScore(), 750, 50);

		if (snake.getOrient() == 1)
			righIcon.paintIcon(this, g, snake.getSnakeX()[0], snake.getSnakeY()[0]);
		else if (snake.getOrient() == 2) {
			downIcon.paintIcon(this, g, snake.getSnakeX()[0], snake.getSnakeY()[0]);
		} else if (snake.getOrient() == 3) {
			lefIcon.paintIcon(this, g, snake.getSnakeX()[0], snake.getSnakeY()[0]);
		} else {
			upIcon.paintIcon(this, g, snake.getSnakeX()[0], snake.getSnakeY()[0]);
		}

		for (int i = 1; i < snake.getLen(); i++) {
			bodyIcon.paintIcon(this, g, snake.getSnakeX()[i], snake.getSnakeY()[i]);
		}

		// 开始游戏提示
		if (isStart == false && isFirstStart) {
			g.setFont(new Font("楷体", Font.BOLD, 40));
			g.setColor(Color.white);
			g.drawString("开始游戏(space)", 300, 300);
		}

		// 继续游戏提示
		if (isStart == false && !isFirstStart) {
			g.setFont(new Font("楷体", Font.BOLD, 40));
			g.setColor(Color.white);
			g.drawString("继续游戏(space)", 300, 300);
		}

		// 重新开始提示
		if (isFailed == true) {
			g.setFont(new Font("楷体", Font.BOLD, 40));
			g.setColor(Color.white);
			g.drawString("重新开始(space)", 300, 300);
		}

		foodIcon.paintIcon(this, g, food.getFoodX(), food.getFoodY());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE) {
			if (isFailed) {
				isFailed = !isFailed;
				snakeController.initSnake(snake);
			} else {
				isStart = !isStart;
				isFirstStart = false;
			}
			if(isStart)
				snakeController.startBGM();
			else {
				snakeController.stopBGM();;
			}
			repaint();
		} else if (keyCode == KeyEvent.VK_UP) {
			if (snake.getOrient() != 2)
				snake.setOrient(4);
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			if (snake.getOrient() != 3)
				snake.setOrient(1);
		} else if (keyCode == KeyEvent.VK_DOWN) {
			if (snake.getOrient() != 4)
				snake.setOrient(2);
		} else if (keyCode == KeyEvent.VK_LEFT) {
			if (snake.getOrient() != 1)
				snake.setOrient(3);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (isStart && !isFailed) {

			if (snake.getSnakeX()[0] == food.getFoodX() && snake.getSnakeY()[0] == food.getFoodY()) {
				snake.setLen(snake.getLen() + 1);
				snake.setScore(snake.getScore() + 10);
				snakeController.produceFood(snake, food);
			}
			
			snakeController.move(snake, snake.getOrient());
		}

		isFailed = snakeController.collisionDetection(snake); // 检测蛇头是否碰撞身体

		repaint();
		timer.restart();
	}
}
