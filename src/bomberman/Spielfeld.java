package bomberman;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Spielfeld extends JFrame {
  private static final long serialVersionUID = 1L;
  private JPanel jPanel = new JPanel(null, true);
  private JButton startButton = new JButton();
  private JButton beendenButton = new JButton();
  private JLabel introBild = new JLabel(new ImageIcon("src/gfx/intro.jpg"));
  private JLabel solidWalls[][] = new JLabel[22][15];
  private Action moveRight,moveLeft,moveUp,moveDown, Bombe;
  private BomberMan bomberMan = new BomberMan(jPanel, this);
  

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
    startButton.setBounds(56, 680, 137, 25);
    startButton.setText("Starten");
    startButton.setMargin(new Insets(2, 2, 2, 2));
    startButton.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent evt) {
        	introBild.setVisible(false);
        	createWalls();
        	placeWalls();
        	
        	bomberMan.put(jPanel);
      }
    });        
    cp.add(startButton);
    
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
    
    
    // Moves for first player
    moveRight = new Move(bomberMan, "right",this);
    startButton.getInputMap().put(KeyStroke.getKeyStroke("D"),"moveRight");
    startButton.getActionMap().put("moveRight",moveRight);
    beendenButton.getInputMap().put(KeyStroke.getKeyStroke("D"),"moveRight");
    beendenButton.getActionMap().put("moveRight",moveRight);
    
    moveLeft = new Move(bomberMan, "left", this);
    startButton.getInputMap().put(KeyStroke.getKeyStroke("A"),"moveLeft");
    startButton.getActionMap().put("moveLeft",moveLeft);
    beendenButton.getInputMap().put(KeyStroke.getKeyStroke("A"),"moveLeft");
    beendenButton.getActionMap().put("moveLeft",moveLeft);
    
    moveUp = new Move(bomberMan, "up", this);
    startButton.getInputMap().put(KeyStroke.getKeyStroke("W"),"moveUp");
    startButton.getActionMap().put("moveUp",moveUp);
    beendenButton.getInputMap().put(KeyStroke.getKeyStroke("W"),"moveUp");
    beendenButton.getActionMap().put("moveUp",moveUp);
    
    moveDown = new Move(bomberMan, "down", this);
    startButton.getInputMap().put(KeyStroke.getKeyStroke("S"),"moveDown");
    startButton.getActionMap().put("moveDown",moveDown);
    beendenButton.getInputMap().put(KeyStroke.getKeyStroke("S"),"moveDown");
    beendenButton.getActionMap().put("moveDown",moveDown);
    
    Bombe = new Bombe(bomberMan, jPanel);
    bomberMan.setBombe((Bombe)Bombe);
    startButton.getInputMap().put(KeyStroke.getKeyStroke("released K"),"Bombe");
    startButton.getActionMap().put("Bombe",Bombe);
    beendenButton.getInputMap().put(KeyStroke.getKeyStroke("released K"),"Bombe");
    beendenButton.getActionMap().put("Bombe",Bombe);
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
  
  public JLabel[][] getSolidWalls() {
		return solidWalls;
  }
  
  public JButton getStartButton() {
	  return startButton;
  }
  
  public void setKeysBack() {
	  startButton.getActionMap().put("moveRight", moveRight);
	  startButton.getActionMap().put("moveLeft", moveLeft);
	  startButton.getActionMap().put("moveUp", moveUp);
	  startButton.getActionMap().put("moveDown", moveDown);
  }
  
  public static void main(String[] args) {
    new Spielfeld("bomberman");
  }
}