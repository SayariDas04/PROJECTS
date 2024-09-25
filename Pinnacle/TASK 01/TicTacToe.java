import javax.swing.*;
import java.awt.*;
import java.net.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.*;

public class TicTacToe extends JFrame implements ActionListener{
	private int click = 0;
	String currentPlayer = "X";
	private JPanel p1,p2,pLeft,p3,p4,p5;
	private JLabel l1,l2,left,l3,l4,l5,l6,lO,lX;
	private JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,br;
	private JButton[] btns;
	private ArrayList<JButton> b;
	
	TicTacToe(){
		ImageIcon i1,i2;
		b = new ArrayList<JButton>();
		
		String r1,r2,r3;
		r1 = "Player 1 won!";
		r2 = "Player 2 won!";
		r3 = "Draw!";
		
		pLeft = new JPanel();
		pLeft.setBackground(new Color(255,238,130));
		pLeft.setBounds(0,0,300,25);
		i1 = new ImageIcon("./images/left.png");
		left =new JLabel();		
		left.setIcon(i1);
		left.setBounds(0,0,25,25);
		left.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ae){
				System.exit(0);
			}
		});
		l3 = new JLabel(" Exit ");
		l3.setForeground(Color.BLUE);
		l3.setBounds(30,0,25,25);
		l3.setFont(new Font("Arial",Font.BOLD,20));
		l3.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ae){
				System.exit(0);
			} // 
		});
		pLeft.add(left);
		pLeft.add(l3);
		add(pLeft);
		p1 = new JPanel();
		p1.setBounds(0,30,300,80);
		p1.setBackground(Color.PINK);
		l1 = new JLabel("Player 1: X");
		l1.setBounds(20,20,130,60);
		l1.setFont(new Font("Arial",Font.PLAIN,20));
		l2 = new JLabel("Player 2: O");
		l2.setBounds(160,20,130,60);
		l2.setFont(new Font("Arial",Font.PLAIN,20));
		p1.setForeground(Color.WHITE);
		p1.add(l1);
		p1.add(l2);
		p1.setLayout(null);
		add(p1);
		
		p3 = new JPanel();
		p3.setBackground(new Color(123, 201, 199));
		p3.setBounds(10,120,280,100);
		l4 = new JLabel("Enjoy the game!");
		l4.setFont(new Font("Arial",Font.BOLD,24));
		l4.setForeground(Color.WHITE);
		p3.add(l4);
		l5 = new JLabel("Tic Tac Toe");
		l5.setFont(new Font("Arial",Font.BOLD,24));
		l5.setForeground(Color.WHITE);
		p3.add(l5);
		add(p3);
		
		
		p4 =new JPanel();
		p4.setBounds(10,450,280,40);
		br = new JButton("Reset");
		br.setBounds(180,5,80,30);
		br.setBackground(Color.PINK); 
		br.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				for(JButton i:b){   
					i.setText("PRESS");
					i.setBackground(new Color(209, 224, 72));
					i.setForeground(Color.BLACK);
					i.setFont(new Font("Arial",Font.BOLD,14));
					i.setEnabled(true);
					p2.add(i);
				}
				currentPlayer = "X";
				click = 0;
				l6.setText("");
				
			}
		});
		l6 = new JLabel();
		l6.setFont(new Font("Arial",Font.BOLD,20));
		l6.setForeground(new Color(116, 252, 223));
		l6.setBounds(20,5,160,30);
		p4.setBackground(Color.BLACK);
		p4.add(l6);
		p4.add(br);
		p4.setLayout(null);
		add(p4); 
		
		p2 = new JPanel();
		p2.setBounds(10,230,280,200);
		p2.setBackground(Color.WHITE);
		
		b1 = new JButton("PRESS");
		b1.setBounds(10,0,80,120);
		b1.setBackground(new Color(209, 224, 72));
		b1.setFont(new Font("Arial",Font.BOLD,14));
		b1.addActionListener(this);
		p2.add(b1);
		b2 = new JButton("PRESS");
		b2.setBounds(100,0,80,120);
		b2.setBackground(new Color(209, 224, 72));
		b2.setFont(new Font("Arial",Font.BOLD,14));
		b2.addActionListener(this);
		p2.add(b2);
		b3 = new JButton("PRESS");
		b3.setBounds(190,0,80,120);
		b3.setBackground(new Color(209, 224, 72));
		b3.setFont(new Font("Arial",Font.BOLD,14));
		b3.addActionListener(this);
		p2.add(b3);
		b4 = new JButton("PRESS");
		b4.setBounds(10,70,80,120);
		b4.setBackground(new Color(209, 224, 72));
		b4.setFont(new Font("Arial",Font.BOLD,14));
		b4.addActionListener(this);
		p2.add(b4);
		b5 = new JButton("PRESS");
		b5.setBounds(100,70,80,120);
		b5.setBackground(new Color(209, 224, 72));
		b5.setFont(new Font("Arial",Font.BOLD,14));
		b5.addActionListener(this);
		p2.add(b5);
		b6 = new JButton("PRESS");
		b6.setBounds(190,70,80,120);
		b6.setBackground(new Color(209, 224, 72));
		b6.setFont(new Font("Arial",Font.BOLD,14));
		b6.addActionListener(this);
		p2.add(b6);
		b7 = new JButton("PRESS");
		b7.setBounds(10,140,80,120);
		b7.setBackground(new Color(209, 224, 72));
		b7.setFont(new Font("Arial",Font.BOLD,14));
		b7.addActionListener(this);
		p2.add(b7);
		b8 = new JButton("PRESS");
		b8.setBounds(100,140,80,120);
		b8.setBackground(new Color(209, 224, 72));
		b8.setFont(new Font("Arial",Font.BOLD,14));
		b8.addActionListener(this);
		p2.add(b8);
		b9 = new JButton("PRESS");
		b9.setBounds(190,140,80,120);
		b9.setBackground(new Color(209, 224, 72));
		b9.setFont(new Font("Arial",Font.BOLD,14));
		b9.addActionListener(this);
		p2.add(b9);
		p2.setLayout(new GridLayout(3,3));
		add(p2);
		b.add(b1);b.add(b2);b.add(b3);
		b.add(b4);b.add(b5);b.add(b6);
		b.add(b7);b.add(b8);b.add(b9);
		
		i2 = new ImageIcon("./images/logo.png");
		setIconImage(i2.getImage());
		
		setUndecorated(true);
		setSize(300,500);
		setBackground(Color.BLACK);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	} //reset
	
	private boolean checkForWin() {
		if (b1.getText().equals(currentPlayer) && b2.getText().equals(currentPlayer) && b3.getText().equals(currentPlayer)) return true;
		if (b4.getText().equals(currentPlayer) && b5.getText().equals(currentPlayer) && b6.getText().equals(currentPlayer)) return true;
		if (b7.getText().equals(currentPlayer) && b8.getText().equals(currentPlayer) && b9.getText().equals(currentPlayer)) return true;

		if (b1.getText().equals(currentPlayer) && b4.getText().equals(currentPlayer) && b7.getText().equals(currentPlayer)) return true;
		if (b2.getText().equals(currentPlayer) && b5.getText().equals(currentPlayer) && b8.getText().equals(currentPlayer)) return true;
		if (b3.getText().equals(currentPlayer) && b6.getText().equals(currentPlayer) && b9.getText().equals(currentPlayer)) return true;

		if (b1.getText().equals(currentPlayer) && b5.getText().equals(currentPlayer) && b9.getText().equals(currentPlayer)) return true;
		if (b3.getText().equals(currentPlayer) && b5.getText().equals(currentPlayer) && b7.getText().equals(currentPlayer)) return true;
		System.out.println("No win");
		return false;
	}
	
	private boolean isBoardFull() {
		for(JButton i: b){
			System.out.println(i.getText());
			if(i.getText().equals("PRESS")) return false;
		}
		return true;
	}
	
	public void actionPerformed(ActionEvent ae){
		click+=1;
		((JButton)ae.getSource()).setEnabled(false);
		if(click%2 == 0){
			((JButton)ae.getSource()).setText("O");
		}
		else{
			((JButton)ae.getSource()).setText("X");
		}
		((JButton)ae.getSource()).setFont(new Font("Arial",Font.BOLD,24));
		((JButton)ae.getSource()).setForeground(new Color(250,5,5));
		((JButton)ae.getSource()).setBackground(Color.WHITE);
		if (checkForWin()) {
			if(currentPlayer.equals("X"))
				l6.setText("Player 1 Won");
			else
				l6.setText("Player 2 Won");
			for(JButton i: b){
				i.setEnabled(false);
			}
		}
		else if (isBoardFull()) {
			l6.setText("It's a draw");
		} 
		currentPlayer = currentPlayer.equals("X") ? "O" : "X";
		
	}

			
	public static void main(String args[]){
		new TicTacToe();
	}
	
}