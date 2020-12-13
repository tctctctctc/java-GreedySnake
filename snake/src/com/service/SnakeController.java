package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.bean.Food;
import com.bean.Snake;

public class SnakeController {

	Random random = new Random();
	Clip bgm; // 创建音乐

	/**
	 * 
	 * @Description 对贪吃蛇蛇初始化操作的方法
	 * @author tc
	 * @data 2020年12月12日上午9:18:22
	 * @param snake 贪吃蛇对象
	 */
	public void initSnake(Snake snake) {
		int[] snakeX = snake.getSnakeX();
		int[] snakeY = snake.getSnakeY();
		snakeX[0] = 100;
		snakeY[0] = 100;
		snakeX[1] = 75;
		snakeY[1] = 100;
		snakeX[2] = 50;
		snakeY[2] = 100;
		snake.setSnakeX(snakeX);
		snake.setSnakeY(snakeY);
		snake.setLen(3);
		snake.setOrient(1);
		snake.setScore(0);
	}

	/**
	 * 
	 * @Description 对食物初始化
	 * @author tc
	 * @data 2020年12月12日上午9:18:03
	 * @param food 食物对象
	 */
	public void initFood(Food food) {
		food.setFoodX(425);
		food.setFoodY(325);
	}

	/**
	 * 
	 * @Description 控制贪吃蛇的移动
	 * @author tc
	 * @data 2020年12月12日上午9:19:02
	 * @param snake
	 * @param orient
	 */
	public void move(Snake snake, int orient) {
		int[] snakeX = snake.getSnakeX();
		int[] snakeY = snake.getSnakeY();
		int len = snake.getLen();
		if (orient == 1) {
			for (int i = len - 1; i > 0; i--) {
				snakeX[i] = snakeX[i - 1];
				snakeY[i] = snakeY[i - 1];
			}
			snakeX[0] += 25;
			if (snakeX[0] > 850)
				snakeX[0] = snakeX[0] - 850;
		} else if (orient == 2) {
			for (int i = len - 1; i > 0; i--) {
				snakeX[i] = snakeX[i - 1];
				snakeY[i] = snakeY[i - 1];
			}
			snakeY[0] += 25;
			if (snakeY[0] > 670)
				snakeY[0] = snakeY[0] - 595;
		} else if (orient == 3) {
			for (int i = len - 1; i > 0; i--) {
				snakeX[i] = snakeX[i - 1];
				snakeY[i] = snakeY[i - 1];
			}
			snakeX[0] -= 25;
			if (snakeX[0] < 25)
				snakeX[0] = snakeX[0] + 850;
		} else {
			for (int i = len - 1; i > 0; i--) {
				snakeX[i] = snakeX[i - 1];
				snakeY[i] = snakeY[i - 1];
			}
			snakeY[0] -= 25;
			if (snakeY[0] < 75)
				snakeY[0] = snakeY[0] + 595;
		}

		snake.setSnakeX(snakeX);
		snake.setSnakeY(snakeY);
	}

	/**
	 * 
	 * @Description 随机生成食物的坐标
	 * @author tc
	 * @data 2020年12月9日下午10:23:38
	 * @param snake 贪吃蛇的对象
	 */
	public void produceFood(Snake snake, Food food) {
		int foodX, foodY;
		while (true) {
			foodX = 25 + 25 * random.nextInt(34);
			foodY = 75 + 25 * random.nextInt(24);
			for (int i = 0; i < snake.getLen(); i++) {
				if (snake.getSnakeX()[i] == foodX || snake.getSnakeY()[i] == foodY)
					continue;
			}
			food.setFoodX(foodX);
			food.setFoodY(foodY);
			break;
		}
	}

	/**
	 * 
	 * @Description 碰撞检测
	 * @author tc
	 * @data 2020年12月12日上午9:53:37
	 * @param snake
	 * @return true代表蛇头碰到身体，false代表没有
	 */
	public boolean collisionDetection(Snake snake) {
		int[] snakeX = snake.getSnakeX();
		int[] snakeY = snake.getSnakeY();
		for (int i = 1; i < snake.getLen(); i++) {
			if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0])
				return true;
		}
		return false;
	}

	public void initBGM() {
		try {
			bgm = AudioSystem.getClip();
			System.out.println(this.getClass().getClassLoader());
			InputStream iStream = this.getClass().getClassLoader().getResourceAsStream("sound/bgm.wav"); // 加载音乐文件，二进制字节流
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(iStream); // 将二进制字节流转成音频字节流
			bgm.open(audioInputStream); // 打开音频流
//			bgm.start();	// 播放
		} catch (LineUnavailableException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startBGM() {
		bgm.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stopBGM() {
		bgm.stop();
	}
}
