package Bomberman;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Spielfeld extends JFrame {
  private static final long serialVersionUID = 1L;
  private JPanel jPanel = new JPanel(null, true);
  private JButton solidWallsButton = new JButton();
  private JButton gitterButton = new JButton();
  private JButton jButton12 = new JButton();
  private JButton jButton13 = new JButton();
  private JButton beendenButton = new JButton();
  private JLabel introBild = new JLabel(new ImageIcon("src/gfx/intro.jpg"));
  private JLabel[] walls = new JLabel[150];

  Spielfeld(String title) {
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 960; 
    int frameHeight = 750;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);

    jPanel.setBounds(60, 60, 840, 600);
    jPanel.setBackground(Color.GRAY);
    jPanel.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 3));
    cp.add(jPanel);
    jPanel.add(introBild);
    introBild.setBounds(1,1,840,600);
    solidWallsButton.setBounds(56, 680, 137, 25);
    solidWallsButton.setText("Feste Blöcke");
    solidWallsButton.setMargin(new Insets(2, 2, 2, 2));
    solidWallsButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) {
        	introBild.setVisible(false);
        	createWalls();
        	placeWalls();
      }
    });
    cp.add(solidWallsButton);
    gitterButton.setBounds(232, 680, 137, 25);
    gitterButton.setText("Gitter");
    gitterButton.setMargin(new Insets(2, 2, 2, 2));
    gitterButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        if (true) {
        	introBild.setVisible(false);
            Graphics g = jPanel.getGraphics();
            g.setColor(Color.white);
            g.drawLine(40,40,40,560);
            g.drawLine(80,40,80,560);
            g.drawLine(120,40,120,560);
            g.drawLine(160,40,160,560);
            g.drawLine(200,40,200,560);
            g.drawLine(240,40,240,560);
            g.drawLine(280,40,280,560);
            g.drawLine(320,40,320,560);
            g.drawLine(360,40,360,560);
            g.drawLine(400,40,400,560);
            g.drawLine(440,40,440,560);
            g.drawLine(480,40,480,560);
            g.drawLine(520,40,520,560);
            g.drawLine(560,40,560,560);
            g.drawLine(600,40,600,560);
            g.drawLine(640,40,640,560);
            g.drawLine(680,40,680,560);
            g.drawLine(720,40,720,560);
            g.drawLine(760,40,760,560);
            g.drawLine(800,40,800,560);
            
            g.drawLine(40,40,800,40);
            g.drawLine(40,80,800,80);
            g.drawLine(40,120,800,120);
            g.drawLine(40,160,800,160);
            g.drawLine(40,200,800,200);
            g.drawLine(40,240,800,240);
            g.drawLine(40,280,800,280);
            g.drawLine(40,320,800,320);
            g.drawLine(40,360,800,360);
            g.drawLine(40,400,800,400);
            g.drawLine(40,440,800,440);
            g.drawLine(40,480,800,480);
            g.drawLine(40,520,800,520);
            g.drawLine(40,560,800,560);
            g.drawLine(40,600,800,600);
            g.drawLine(40,640,800,640);
            g.drawLine(40,680,800,680);
            g.drawLine(40,720,800,720);
            g.drawLine(40,760,800,760);
        	
        	
        }
      }
    });
    cp.add(gitterButton);
    jButton12.setBounds(408, 680, 137, 25);
    jButton12.setText("Button3");
    jButton12.setMargin(new Insets(2, 2, 2, 2));
    jButton12.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
    	  
      }
    });
    cp.add(jButton12);
    jButton13.setBounds(584, 680, 137, 25);
    jButton13.setText("Button4");
    jButton13.setMargin(new Insets(2, 2, 2, 2));
    jButton13.addActionListener(new ActionListener() { 
    	public void actionPerformed(ActionEvent evt) { 
      	  
        }
    });
    cp.add(jButton13);
    beendenButton.setBounds(760, 680, 137, 25);
    beendenButton.setText("Beenden");
    beendenButton.setMargin(new Insets(2, 2, 2, 2));
    beendenButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
    	  System.exit(0);
      }
    });
    cp.add(beendenButton);
//    jLabel1.setBounds(64, 8, 835, 49);
//    jLabel1.setText("Bomberman");
//    jLabel1.setFont(new Font("Calibri", Font.BOLD, 36));
//    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
//    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
//    cp.add(jLabel1);

    setVisible(true);
  }
  
  private void createWalls() {
	  for (int i=0; i<150; i++) {
		  JLabel solidWall = new JLabel(new ImageIcon("src/gfx/solid_wall.png"));
		  walls[i] = solidWall;
	  }
  }
  
  private void placeWalls() {
	  int i,j,k,l,arrayIndex;
	  
	  // obere Wand	  
	  for(i=1;i<=21; i++) {
		  jPanel.add(walls[walls.length-i]);
		  walls[walls.length-i].setBounds((40*(i-1))+1, 1, 40, 40);
	  }
	  
	  // untere Wand
	  for(j=1;j<=21; j++) {
		  jPanel.add(walls[walls.length-i-j]);
		  walls[walls.length-i-j].setBounds((40*(j-1))+1, 561, 40, 40);
	  }
	  
	  // rechte Wand
	  for(k=1;k<=21; k++) {
		  jPanel.add(walls[walls.length-i-j-k]);
		  walls[walls.length-i-j-k].setBounds(801, (40*k)+1, 40, 40);
	  }
	  
	  // linke Wand
	  for(l=1;l<=21; l++) {
		  jPanel.add(walls[walls.length-i-j-k-l]);
		  walls[walls.length-i-j-k-l].setBounds(1, (40*l)+1, 40, 40);
	  }
	  
	  // position von Rechts im Array
	  arrayIndex = i+j+k+l;
	  l=1;
	  
	  // spielfeld
	  for(i=1; i<=9; i++) {
		  for(k=1; k<=6; k++) {
			  jPanel.add(walls[walls.length-arrayIndex-l]);
			  walls[walls.length-arrayIndex-l].setBounds(80*i, 80*k, 40, 40);
			  l++;
		  }
	  }
  }

  public static void main(String[] args) {
    new Spielfeld("bomberman");
  }
}