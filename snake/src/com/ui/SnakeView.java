package com.ui;

import javax.swing.JFrame;

public class SnakeView{

	SnakePanel mPanel = new SnakePanel(); // 画布
	
	public void enterWindow() {
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 900, 720);// 设置窗口大小和位置
		frame.setResizable(false);// 设置不能手动拖动改变窗口大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置点x退出
		frame.add(mPanel); // 添加画布
		frame.setVisible(true); // 设置窗口可见
	}

	public static void main(String[] args) {
		SnakeView snakeView = new SnakeView();
		snakeView.enterWindow();
	}
}
