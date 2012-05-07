package Bomberman;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 03.05.2012
  * @author
  */

public class Spielfeld extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// Anfang Attribute
  private JPanel jPanel1 = new JPanel(null, true);
//  private JPanel jPanel2 = new JPanel(null, true);
  private JButton jButton1 = new JButton();
  private JButton jButton11 = new JButton();
  private JButton jButton12 = new JButton();
  private JButton jButton13 = new JButton();
  private JButton jButton14 = new JButton();
//  private JLabel jLabel1 = new JLabel();
  private JLabel bild1 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild2 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild3 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild4 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild5 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild6 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild7 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild8 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild9 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild10 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild11 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild12 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild13 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild14 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild15 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild16 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild17 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild18 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild19 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild20 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild21 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild22 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild23 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild24 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild25 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild26 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild27 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild28 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild29 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild30 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild31 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild32 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild33 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild34 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild35 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild36 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild37 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild38 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild39 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild40 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild41 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild42 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild43 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild44 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild45 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild46 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild47 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild48 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild49 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild50 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild51 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild52 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild53 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild54 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild55 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild56 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild57 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild58 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild59 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild60 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild61 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild62 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild63 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild64 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild65 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild66 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild67 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild68 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild69 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild70 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild71 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild72 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild73 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild74 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild75 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild76 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild77 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild78 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild79 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild80 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild81 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild82 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild83 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild84 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild85 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild86 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild87 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild88 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild89 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild90 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild91 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild92 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild93 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild94 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild95 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild96 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild97 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild98 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild99 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild100 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild101 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild102 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild103 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild104 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild105 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild106 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild107 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild108 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild109 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild110 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild111 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild112 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild113 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild114 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild115 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild116 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild117 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild118 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild119 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild120 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild121 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild122 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/solid_wall.png"));
  private JLabel bild123 = new JLabel(new ImageIcon("/home/franki/workspace/gruppe07/src/gfx/intro.jpg"));

  
  // Ende Attribute

  public Spielfeld(String title) {
    // Frame-Initialisierung
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
    // Anfang Komponenten

    jPanel1.setBounds(60, 60, 840, 600);
    jPanel1.setBackground(Color.GRAY);
    jPanel1.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 3));
    cp.add(jPanel1);
    jPanel1.add(bild123);
    bild123.setBounds(1,1,840,600);
    jButton1.setBounds(56, 680, 137, 25);
    jButton1.setText("Feste Blöcke");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
        if (true) {
        	bild123.setVisible(false);
            jPanel1.add(bild1);
            bild1.setBounds(81,81,39,39);
            jPanel1.add(bild2);
            bild2.setBounds(161,81,39,39);
            jPanel1.add(bild3);
            bild3.setBounds(241,81,39,39);
            jPanel1.add(bild4);
            bild4.setBounds(321,81,39,39);
            jPanel1.add(bild5);
            bild5.setBounds(401,81,39,39);
            jPanel1.add(bild6);
            bild6.setBounds(481,81,39,39);
            jPanel1.add(bild7);
            bild7.setBounds(561,81,39,39);
            jPanel1.add(bild8);
            bild8.setBounds(641,81,39,39);
            jPanel1.add(bild9);
            bild9.setBounds(721,81,39,39);
            
            jPanel1.add(bild10);
            bild10.setBounds(81,161,39,39);
            jPanel1.add(bild11);
            bild11.setBounds(161,161,39,39);
            jPanel1.add(bild12);
            bild12.setBounds(241,161,39,39);
            jPanel1.add(bild13);
            bild13.setBounds(321,161,39,39);
            jPanel1.add(bild14);
            bild14.setBounds(401,161,39,39);
            jPanel1.add(bild15);
            bild15.setBounds(481,161,39,39);
            jPanel1.add(bild16);
            bild16.setBounds(561,161,39,39);
            jPanel1.add(bild17);
            bild17.setBounds(641,161,39,39);
            jPanel1.add(bild18);
            bild18.setBounds(721,161,39,39);
            
            jPanel1.add(bild19);
            bild19.setBounds(81,241,39,39);
            jPanel1.add(bild20);
            bild20.setBounds(161,241,39,39);
            jPanel1.add(bild21);
            bild21.setBounds(241,241,39,39);
            jPanel1.add(bild22);
            bild22.setBounds(321,241,39,39);
            jPanel1.add(bild23);
            bild23.setBounds(401,241,39,39);
            jPanel1.add(bild24);
            bild24.setBounds(481,241,39,39);
            jPanel1.add(bild25);
            bild25.setBounds(561,241,39,39);
            jPanel1.add(bild26);
            bild26.setBounds(641,241,39,39);
            jPanel1.add(bild27);
            bild27.setBounds(721,241,39,39);
            
            jPanel1.add(bild28);
            bild28.setBounds(81,321,39,39);
            jPanel1.add(bild29);
            bild29.setBounds(161,321,39,39);
            jPanel1.add(bild30);
            bild30.setBounds(241,321,39,39);
            jPanel1.add(bild31);
            bild31.setBounds(321,321,39,39);
            jPanel1.add(bild32);
            bild32.setBounds(401,321,39,39);
            jPanel1.add(bild33);
            bild33.setBounds(481,321,39,39);
            jPanel1.add(bild34);
            bild34.setBounds(561,321,39,39);
            jPanel1.add(bild35);
            bild35.setBounds(641,321,39,39);
            jPanel1.add(bild36);
            bild36.setBounds(721,321,39,39);
            
            jPanel1.add(bild37);
            bild37.setBounds(81,401,39,39);
            jPanel1.add(bild38);
            bild38.setBounds(161,401,39,39);
            jPanel1.add(bild39);
            bild39.setBounds(241,401,39,39);
            jPanel1.add(bild40);
            bild40.setBounds(321,401,39,39);
            jPanel1.add(bild41);
            bild41.setBounds(401,401,39,39);
            jPanel1.add(bild42);
            bild42.setBounds(481,401,39,39);
            jPanel1.add(bild43);
            bild43.setBounds(561,401,39,39);
            jPanel1.add(bild44);
            bild44.setBounds(641,401,39,39);
            jPanel1.add(bild45);
            bild45.setBounds(721,401,39,39);
            
            jPanel1.add(bild46);
            bild46.setBounds(81,481,39,39);
            jPanel1.add(bild47);
            bild47.setBounds(161,481,39,39);
            jPanel1.add(bild48);
            bild48.setBounds(241,481,39,39);
            jPanel1.add(bild49);
            bild49.setBounds(321,481,39,39);
            jPanel1.add(bild50);
            bild50.setBounds(401,481,39,39);
            jPanel1.add(bild51);
            bild51.setBounds(481,481,39,39);
            jPanel1.add(bild52);
            bild52.setBounds(561,481,39,39);
            jPanel1.add(bild53);
            bild53.setBounds(641,481,39,39);
            jPanel1.add(bild54);
            bild54.setBounds(721,481,39,39);
            
            jPanel1.add(bild55);
            bild55.setBounds(1,1,39,39);
            jPanel1.add(bild56);
            bild56.setBounds(41,1,39,39);
            jPanel1.add(bild57);
            bild57.setBounds(81,1,39,39);
            jPanel1.add(bild58);
            bild58.setBounds(121,1,39,39);
            jPanel1.add(bild59);
            bild59.setBounds(161,1,39,39);
            jPanel1.add(bild60);
            bild60.setBounds(201,1,39,39);
            jPanel1.add(bild61);
            bild61.setBounds(241,1,39,39);
            jPanel1.add(bild62);
            bild62.setBounds(281,1,39,39);
            jPanel1.add(bild63);
            bild63.setBounds(321,1,39,39);
            jPanel1.add(bild64);
            bild64.setBounds(361,1,39,39);
            jPanel1.add(bild65);
            bild65.setBounds(401,1,39,39);
            jPanel1.add(bild66);
            bild66.setBounds(441,1,39,39);
            jPanel1.add(bild67);
            bild67.setBounds(481,1,39,39);
            jPanel1.add(bild68);
            bild68.setBounds(521,1,39,39);
            jPanel1.add(bild69);
            bild69.setBounds(561,1,39,39);
            jPanel1.add(bild70);
            bild70.setBounds(601,1,39,39);
            jPanel1.add(bild71);
            bild71.setBounds(641,1,39,39);
            jPanel1.add(bild72);
            bild72.setBounds(681,1,39,39);
            jPanel1.add(bild73);
            bild73.setBounds(721,1,39,39);
            jPanel1.add(bild74);
            bild74.setBounds(761,1,39,39);
            jPanel1.add(bild75);
            bild75.setBounds(801,1,39,39);
            
            jPanel1.add(bild76);
            bild76.setBounds(1,561,39,39);
            jPanel1.add(bild77);
            bild77.setBounds(41,561,39,39);
            jPanel1.add(bild78);
            bild78.setBounds(81,561,39,39);
            jPanel1.add(bild79);
            bild79.setBounds(121,561,39,39);
            jPanel1.add(bild80);
            bild80.setBounds(161,561,39,39);
            jPanel1.add(bild81);
            bild81.setBounds(201,561,39,39);
            jPanel1.add(bild82);
            bild82.setBounds(241,561,39,39);
            jPanel1.add(bild83);
            bild83.setBounds(281,561,39,39);
            jPanel1.add(bild84);
            bild84.setBounds(321,561,39,39);
            jPanel1.add(bild85);
            bild85.setBounds(361,561,39,39);
            jPanel1.add(bild86);
            bild86.setBounds(401,561,39,39);
            jPanel1.add(bild87);
            bild87.setBounds(441,561,39,39);
            jPanel1.add(bild88);
            bild88.setBounds(481,561,39,39);
            jPanel1.add(bild89);
            bild89.setBounds(521,561,39,39);
            jPanel1.add(bild90);
            bild90.setBounds(561,561,39,39);
            jPanel1.add(bild91);
            bild91.setBounds(601,561,39,39);
            jPanel1.add(bild92);
            bild92.setBounds(641,561,39,39);
            jPanel1.add(bild93);
            bild93.setBounds(681,561,39,39);
            jPanel1.add(bild94);
            bild94.setBounds(721,561,39,39);
            jPanel1.add(bild95);
            bild95.setBounds(761,561,39,39);
            jPanel1.add(bild96);
            bild96.setBounds(801,561,39,39);
            
            jPanel1.add(bild97);
            bild97.setBounds(1,41,39,39);
            jPanel1.add(bild98);
            bild98.setBounds(1,81,39,39);
            jPanel1.add(bild99);
            bild99.setBounds(1,121,39,39);
            jPanel1.add(bild100);
            bild100.setBounds(1,161,39,39);
            jPanel1.add(bild101);
            bild101.setBounds(1,201,39,39);
            jPanel1.add(bild102);
            bild102.setBounds(1,241,39,39);
            jPanel1.add(bild103);
            bild103.setBounds(1,281,39,39);
            jPanel1.add(bild104);
            bild104.setBounds(1,321,39,39);
            jPanel1.add(bild105);
            bild105.setBounds(1,361,39,39);
            jPanel1.add(bild106);
            bild106.setBounds(1,401,39,39);
            jPanel1.add(bild107);
            bild107.setBounds(1,441,39,39);
            jPanel1.add(bild108);
            bild108.setBounds(1,481,39,39);
            jPanel1.add(bild109);
            bild109.setBounds(1,521,39,39);
            
            jPanel1.add(bild110);
            bild110.setBounds(801,41,39,39);
            jPanel1.add(bild111);
            bild111.setBounds(801,81,39,39);
            jPanel1.add(bild112);
            bild112.setBounds(801,121,39,39);
            jPanel1.add(bild113);
            bild113.setBounds(801,161,39,39);
            jPanel1.add(bild114);
            bild114.setBounds(801,201,39,39);
            jPanel1.add(bild115);
            bild115.setBounds(801,241,39,39);
            jPanel1.add(bild116);
            bild116.setBounds(801,281,39,39);
            jPanel1.add(bild117);
            bild117.setBounds(801,321,39,39);
            jPanel1.add(bild118);
            bild118.setBounds(801,361,39,39);
            jPanel1.add(bild119);
            bild119.setBounds(801,401,39,39);
            jPanel1.add(bild120);
            bild120.setBounds(801,441,39,39);
            jPanel1.add(bild121);
            bild121.setBounds(801,481,39,39);
            jPanel1.add(bild122);
            bild122.setBounds(801,521,39,39);
        }
      }
    });
    cp.add(jButton1);
    jButton11.setBounds(232, 680, 137, 25);
    jButton11.setText("Gitter");
    jButton11.setMargin(new Insets(2, 2, 2, 2));
    jButton11.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton11_ActionPerformed(evt);
        if (true) {
        	bild123.setVisible(false);
            Graphics g = jPanel1.getGraphics();
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
    cp.add(jButton11);
    jButton12.setBounds(408, 680, 137, 25);
    jButton12.setText("Button3");
    jButton12.setMargin(new Insets(2, 2, 2, 2));
    jButton12.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton12_ActionPerformed(evt);
        if (true) {
        }
      }
    });
    cp.add(jButton12);
    jButton13.setBounds(584, 680, 137, 25);
    jButton13.setText("Button4");
    jButton13.setMargin(new Insets(2, 2, 2, 2));
    jButton13.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton13_ActionPerformed(evt);
      }
    });
    cp.add(jButton13);
    jButton14.setBounds(760, 680, 137, 25);
    jButton14.setText("Beenden");
    jButton14.setMargin(new Insets(2, 2, 2, 2));
    jButton14.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton14_ActionPerformed(evt);
        if (true) {
        	System.exit(0);
        }
      }
    });
    cp.add(jButton14);
//    jLabel1.setBounds(64, 8, 835, 49);
//    jLabel1.setText("Bomberman");
//    jLabel1.setFont(new Font("Calibri", Font.BOLD, 36));
//    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
//    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
//    cp.add(jLabel1);
    //Ende Komponenten

    setVisible(true);
  }

  // Anfang Methoden
  public void jButton1_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfuegen
  }

  public void jButton11_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfuegen
  }

  public void jButton12_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfuegen
  }

  public void jButton13_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfuegen
  }

  public void jButton14_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfuegen
  }

  // Ende Methoden

  public static void main(String[] args) {
    new Spielfeld("bomberman");
  }
}