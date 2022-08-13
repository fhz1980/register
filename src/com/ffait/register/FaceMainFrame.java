package com.ffait.register;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import com.ffait.util.*;

public class FaceMainFrame {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	static FaceService fs = new FaceService();
	private JFrame frame;
	static JLabel cameralable;
	static JLabel advlable;
	static JLabel photolable;
	static JTextArea name;
	static JTextArea idnum;
	static JTextArea sex;
	static JTextArea nation;
	static JTextArea birthday;
	static JTextArea address;
	static JTextArea org;
	static JTextArea validateTime;
	static JTextArea trainProject;
	static JLabel regId;
	static JLabel regName;
	static JLabel remmond;
	static JTextField registerId;
	static JTextField registerName;
	static JButton submit;
	static JButton registerPhoto;
	static JLabel message;
	static int flag = 0;
	static long pretime;
	static boolean state = true;
	static BufferedImage photoregister = null;
	static BufferedImage showImg = null;
	static VideoCapture camera;
	static JFrame frameThis;

	public FaceMainFrame() {
		initialize();
	}

	// 完善版本1.0
	private void initialize() {
		frame = new JFrame("培训注册系统");
		frameThis = frame;
		try {
			frame.setIconImage(ImageIO.read(new File("C:\\parameter\\nicola.jpg")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setBounds(0, 0, 1024, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		name = new JTextArea("姓名：");
		name.setFont(new Font("monospaced", Font.PLAIN, 30));
		name.setEditable(false);// 模仿JLabel 禁止编辑文字
		name.setBackground(new Color(238, 238, 238));// 设置背景色和 窗体的背景色一样
		name.setBounds(80, 20, 600, 40);

		idnum = new JTextArea("用户名：");
		idnum.setFont(new Font("monospaced", Font.PLAIN, 30));
		idnum.setEditable(false);// 模仿JLabel 禁止编辑文字
		idnum.setBackground(new Color(238, 238, 238));// 设置背景色和 窗体的背景色一样
		idnum.setBounds(50, 70, 600, 40);

		trainProject = new JTextArea("培训项目：");
		trainProject.setLineWrap(true);
		trainProject.setFont(new Font("monospaced", Font.PLAIN, 30));
		trainProject.setEditable(false); // 模仿JLabel 禁止编辑文字
		trainProject.setBackground(new Color(238, 238, 238)); // 设置背景色和 窗体的背景色一样
		trainProject.setLineWrap(true); // 激活自动换行功能
		trainProject.setWrapStyleWord(true); // 激活断行不断字功能
		trainProject.setBounds(20, 120, 630, 50);

		cameralable = new JLabel("");
		cameralable.setBounds(32, 380, 960, 540);

//设置不截取图片上传  (修改时搜索前一句，记得修改人脸框
		//窗口大小（1024*1920） 在下方添加一张图片 
		frame.setBounds(0,0, 1024, 960+960);
	   
		advlable = new JLabel(""); 
		advlable.setBounds(32, 380+540, 960, 960);
		  
		try {
		//	    	  advlable.setIcon(ImageIO.read(new  File("C:\\parameter\\advImage.jpg")));
			advlable.setIcon( new ImageIcon(ImageIO.read(new  File("C:\\parameter\\advImage.jpg")).getScaledInstance(960,960,Image.SCALE_DEFAULT))); 
		} catch (IOException e1) { // TODO Auto-generated catch
			e1.printStackTrace(); 
		}
	    frame.getContentPane().add(advlable);
	    

		photolable = new JLabel();
		photolable.setBounds(740, 30, 252, 252);

		regName = new JLabel("姓名:");
		regName = new JLabel("姓名:");
		regName.setFont(new Font("monospaced", Font.PLAIN, 30));
		regName.setBounds(80, 190, 120, 30);
		regName.setVisible(false);

		registerName = new JTextField();
		registerName.setBounds(185, 190, 120, 30);
		registerName.setVisible(false);

		regId = new JLabel("用户名:");
		regId.setFont(new Font("monospaced", Font.PLAIN, 30));
		regId.setBounds(50, 230, 200, 30);
		regId.setVisible(false);

		registerId = new JTextField();
		registerId.setBounds(185, 230, 120, 30);
		registerId.setVisible(false);

		remmond = new JLabel("身份证后六位");
		remmond.setFont(new Font("monospaced", Font.PLAIN, 20));
		remmond.setForeground(Color.red);
		remmond.setBounds(310, 230, 200, 30);
		remmond.setVisible(false);
		// 提示信息栏
		message = new JLabel("", JLabel.CENTER);
		message.setFont(new Font("黑体", Font.PLAIN, 30));
		message.setForeground(Color.RED);
		message.setBounds(20, 320, 1024, 50);

		submit = new JButton("提交");
		submit.setBounds(452 - 60, 270, 80, 50);
		submit.setVisible(false);

		registerPhoto = new JButton("重选");
		registerPhoto.setBounds(452 + 50, 270, 80, 50);
		registerPhoto.setVisible(false);

		frame.getContentPane().add(name);
		frame.getContentPane().add(idnum);
		frame.getContentPane().add(trainProject);
		frame.getContentPane().add(cameralable);
		frame.getContentPane().add(photolable);

		frame.getContentPane().add(regId);
		frame.getContentPane().add(registerName);
		frame.getContentPane().add(regName);
		frame.getContentPane().add(registerId);
		frame.getContentPane().add(submit);
		frame.getContentPane().add(message);
		frame.getContentPane().add(registerPhoto);
		frame.getContentPane().add(remmond);
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getHeight()) / 2;
		frame.setLocation(x, 0);

		// 添加提交按键监听
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = registerId.getText();
				String userName = registerName.getText();
				pretime = System.currentTimeMillis();
				state = true;
				// 注册的id name 均不为空
				if (photoregister != null && userId != null && !"".equals(userId) && !"".equals(userName)
						&& userName != null) {
					String result = fs.registerMember(photoregister, userId, userName,
							ParameterOperate.extract("roleId"));
					System.out.print(result);

					if ("ok".equals(result)) {
						message.setText("注册成功!");

						registerName.setVisible(false);
						registerId.setVisible(false);
						remmond.setVisible(false);
						regName.setVisible(false);
						regId.setVisible(false);
						submit.setVisible(false);
						registerPhoto.setVisible(false);
						// 提交注册后将信息清空
						registerId.setText("");
						registerName.setText("");
						photolable.setIcon(null);
						photoregister = null;
					} else if ("null".equals(result) || null == result)

						message.setText("注册失败!请重试或联系管理员!");
					else {

					}
				} else {
					JOptionPane.showMessageDialog(null, "请输入姓名及用户名！");
				}

			}
		});
		// 图片更新，撤销注册按钮
		registerPhoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state = true;
				Mat frame = new Mat();
				camera.read(frame);
				// 剪切图片
				
				//设置不截取图片上传
				photoregister = fs.mat2BI(frame);
//				photoregister = fs.mat2BI(frame).getSubimage(300, 90, 360, 540);

				photolable.setIcon(new ImageIcon(photoregister.getScaledInstance(180, 252, Image.SCALE_DEFAULT)));

				message.setText("正在识别中");

			}
		});

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FaceMainFrame window = new FaceMainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		pretime = System.currentTimeMillis();
//		VideoCapture camera = new VideoCapture(0);
		camera = new VideoCapture(0);

		if (!camera.isOpened()) {
			System.out.println("Camera Error");
		} else {
			Mat frame = new Mat();
			while (flag == 0) {
				if (photoregister != null) {
					photolable.setIcon(new ImageIcon(photoregister.getScaledInstance(180, 252, Image.SCALE_DEFAULT)));
				}
				camera.read(frame);
				BufferedImage bi = fs.mat2BI(frame);

				long currenttime = System.currentTimeMillis();
				if (currenttime - pretime > 10000 && state) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							// long a=System.currentTimeMillis();
							if (true) {
								if (message.getText() == null || message.getText() == "")
									message.setText("正在识别中");

								//设置不截取图片上传
								String s = fs.judgeMember(bi);
//								String s = fs.judgeMember(bi.getSubimage(300, 90, 360, 540));
								System.out.println(s);

								if ("noFace".equals(s)) {
									registerName.setVisible(false);
									registerId.setVisible(false);
									regName.setVisible(false);
									regId.setVisible(false);
									submit.setVisible(false);
									remmond.setVisible(false);
									registerPhoto.setVisible(false);

									System.out.println(photolable.getIcon());

									try {
										// 验证成功 五秒后清除信息
										ImageIO.read(new File("image.jpg"));

									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} finally {

										photolable.setIcon(null);
										photoregister = null;
									}

									System.out.println(photolable.getIcon());

									// 进入注册
								} else if ("noUser".equals(s)) {
									// 停止人脸识别
									state = false;
									// 当右上角无图时，添加图片及注册图片
									if (photolable.getIcon() == null) {
										Mat f1 = frame.clone();
										camera.read(f1);
										//设置不截取图片上传
										photoregister = fs.mat2BI(f1);
//										photoregister = fs.mat2BI(f1).getSubimage(300, 90, 360, 540);
										photolable.setIcon(new ImageIcon(
												photoregister.getScaledInstance(180, 252, Image.SCALE_DEFAULT)));

									}
									message.setText("您还没有注册，就输入姓名及用户名注册!");
									registerName.setVisible(true);
									registerId.setVisible(true);
									regName.setVisible(true);
									regId.setVisible(true);
									submit.setVisible(true);
									remmond.setVisible(true);
									registerPhoto.setVisible(true);
								} else if (null == s) {
									message.setText("未检测到人脸!");
									// 返回值 !null：验证通过
								} else if (null != s && "" != s) {
									state = true;
									registerName.setVisible(false);
									registerId.setVisible(false);
									regName.setVisible(false);
									regId.setVisible(false);
									submit.setVisible(false);
									remmond.setVisible(false);
									registerPhoto.setVisible(false);
									// System.out.println(s);

									int f1 = s.indexOf('_');
									int f2 = s.indexOf('_', f1 + 1);
									int f3 = s.indexOf('_', f2 + 1);
									int f4 = s.indexOf('_', f3 + 1);
									int f5 = s.indexOf('_', f4 + 1);

									String userId = s.substring(0, f1);
									String userCode = s.substring(f1 + 1, f2);
									String userName = s.substring(f2 + 1, f3);
									String roleId = s.substring(f3 + 1, f4);
									String photoUrl = s.substring(f4 + 1, f5);
//								        String projects = s.substring(f5+1);
									String projects = ParameterOperate.extract("projectNames");
									message.setText("身份验证成功!");

//								    
									BufferedImage photo = ImageUtils.downloadBufferedImageFromUrl(
											ParameterOperate.extract("mainService") + photoUrl, "jpg");

									ImageIcon i = new ImageIcon(photo);
									i.setImage(i.getImage().getScaledInstance(252, 252, Image.SCALE_DEFAULT));// 设置缩放
									photolable.setIcon(i);
									name.setText("姓名：" + userName);
									idnum.setText("用户名：" + userCode);
									trainProject.setText("培训项目：" + projects);

									try {
										// 验证成功 五秒后清除信息
										Thread.sleep(5000);

									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} finally {
										name.setText("姓名：");
										idnum.setText("用户名：");
										trainProject.setText("培训项目：");
										message.setText("正在识别中");
										photolable.setIcon(null);
										photoregister = null;
									}

								}

							}
						}

					}).start();
					pretime = currenttime;
				}
				showImg = ImageUtils.deepCopy(bi);

				ImageUtils.drawFace(showImg);

				cameralable.setIcon(new ImageIcon(showImg));
			}
		}
	}

}