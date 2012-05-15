package bomberman;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Spielfeld extends JFrame {
  private static final long serialVersionUID = 1L;
  private JPanel jPanel = new JPanel(null, true);
  private JButton solidWallsButton = new JButton();
  private JButton beendenButton = new JButton();
  private JLabel introBild = new JLabel(new ImageIcon("src/gfx/intro.jpg"));
  JLabel solidWalls[][] = new JLabel[22][15];
  private BomberMan bomberMan = new BomberMan();

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

    Color DARKGREEN = new Color(51,153,0);
    jPanel.setBounds(60, 60, 840, 600);
    jPanel.setBackground(DARKGREEN);
    jPanel.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 3));
    cp.add(jPanel);
    jPanel.add(introBild);
    introBild.setBounds(1,1,840,600);
    solidWallsButton.setBounds(56, 680, 137, 25);
    solidWallsButton.setText("Starten");
    solidWallsButton.setMargin(new Insets(2, 2, 2, 2));
    solidWallsButton.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent evt) {
        	introBild.setVisible(false);
        	createWalls();
        	placeWalls();
        	
        	bomberMan.put(jPanel);
      }
    });        
    cp.add(solidWallsButton);
    
    beendenButton.setBounds(760, 680, 137, 25);
    beendenButton.setText("Beenden");
    beendenButton.setMargin(new Insets(2, 2, 2, 2));
    beendenButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
    	  System.exit(0);
      }
    });
    cp.add(beendenButton);
    setVisible(true);
    
    Action moveRight = new Move(bomberMan, "right",this);
    solidWallsButton.getInputMap().put(KeyStroke.getKeyStroke("released D"),"moveRight");
    solidWallsButton.getActionMap().put("moveRight",moveRight);
    
    Action moveLeft = new Move(bomberMan, "left", this);
    solidWallsButton.getInputMap().put(KeyStroke.getKeyStroke("released A"),"moveLeft");
    solidWallsButton.getActionMap().put("moveLeft",moveLeft);
    
    Action moveUp = new Move(bomberMan, "up", this);
    solidWallsButton.getInputMap().put(KeyStroke.getKeyStroke("released W"),"moveUp");
    solidWallsButton.getActionMap().put("moveUp",moveUp);
    
    Action moveDown = new Move(bomberMan, "down", this);
    solidWallsButton.getInputMap().put(KeyStroke.getKeyStroke("released S"),"moveDown");
    solidWallsButton.getActionMap().put("moveDown",moveDown);   
  }
  
  private void createWalls() {
	  for (int i=0; i<15; i++) {
		  for (int j=0;j<22;j++) {
		  JLabel solidWall = new JLabel(new ImageIcon("src/gfx/solid_wall.png"));
		  solidWalls[j][i] = solidWall;
		  solidWalls[j][i].setName("walkable");
		  }
	  }
  }
  
  private void placeWalls() {
	  
	  // obere Wand	  
	  for(int i=1;i<22; i++) {
		  jPanel.add(solidWalls[i][0]);
		  solidWalls[i][0].setBounds((40*(i-1))+1, 1, 40, 40);
	  }
	  
	  // untere Wand
	  for(int i=1;i<22; i++) {
		  jPanel.add(solidWalls[i][14]);
		  solidWalls[i][14].setBounds((40*(i-1))+1, 561, 40, 40);
	  }
	  
	  // rechte Wand
	  for(int i=1;i<14; i++) {
		  jPanel.add(solidWalls[21][i]);
		  solidWalls[21][i].setBounds(801, (40*i)+1, 40, 40);
	  }
	  
	  // linke Wand
	  for(int i=1;i<14; i++) {
		  jPanel.add(solidWalls[0][i]);
		  solidWalls[0][i].setBounds(1, (40*i)+1, 40, 40);
	  }
	    
	  
	  // Spielfeld
	  for (int i=1;i<19;i++) {
		  for (int j=1;j<13;j++) {
			  
			  if(i%2==0 && j%2==0) {
			  	  jPanel.add(solidWalls[i][j]);
				  solidWalls[i][j].setBounds(40*i, 40*j, 40, 40);
				  solidWalls[i][j].setName("nowalkable");
			  }

		  }
	  }

  }
  
  
  public static void main(String[] args) {
    new Spielfeld("bomberman");
  }
}