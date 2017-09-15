import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class menu {
	
	JLabel jlab;
	
	menu() {
		int dimension = 600;
		JFrame jfrm = new JFrame("Main Menu");
		jfrm.setLayout(new FlowLayout());
		jfrm.setSize(dimension,dimension);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton start = new JButton("Click to Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Process pb = new ProcessBuilder("/bin/bash", "/run.sh").start();
					} catch (Throwable e) {
					e.printStackTrace();
				}
					
			}
		});
	
		jfrm.add(start);
		jlab = new JLabel("Press the button");
		jfrm.add(jlab);
		jfrm.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new menu();
			}
		});
		
	}
}
