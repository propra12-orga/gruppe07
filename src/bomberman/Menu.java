package bomberman;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu implements ActionListener {
     
	 private Spielfeld spielfeld;
     private Container container;
     private JFrame chooseframe = new JFrame("Bomberman");
     private String levelpath;
     private String[] level = {"Level 1","Level 2","Level 3","Level 4","Level 5","Zufallsgenerator"};
     private JComboBox<Object> levelbox = new JComboBox<Object>(level);
     private String gamemode;
     
     // MenuBar
     private JMenuBar menueLeiste;
     
     // Menu
     private JMenu spiel;
     private JMenu neu;
     
     // MenuItem
     private JMenuItem oneplayer;
     private JMenuItem twoplayer;
     private JMenuItem network;
     private JMenuItem restart;
     private JMenuItem beenden;

     
     public Menu(Spielfeld spielfeld) {
          this.spielfeld = spielfeld;
          container = spielfeld.getContentPane();
          
          // Menüleiste erzeugen
          menueLeiste = new JMenuBar();
          menueLeiste.setBounds(0, 0, 960, 25);
          
          // Menüelemente erzeugen
          spiel = new JMenu("Spiel");
          neu = new JMenu("Neues Spiel");
          
          // Untermenüelemente erzeugen
          restart = new JMenuItem("Neustart");
          restart.addActionListener(this);
          restart.setEnabled(false);
          beenden = new JMenuItem("Beenden");
          beenden.addActionListener(this);
          oneplayer = new JMenuItem("Einzelspieler ...");
          oneplayer.addActionListener(this);
          twoplayer = new JMenuItem("2 Spieler ...");
          twoplayer.addActionListener(this);
          network = new JMenuItem("Netzwerk ...");
          network.addActionListener(this);
          
          
          // Menueelemente hinzufügen
          menueLeiste.add(spiel);
          
          // Untermenue "Spiel"
          spiel.add(neu);
          spiel.add(restart);
          spiel.add(beenden);
          
          //Untermenue "Neues Spiel"
          neu.add(oneplayer);
          neu.add(twoplayer);
          neu.add(network);
          
          container.add(menueLeiste, 0);
     }
     
     public void actionPerformed(ActionEvent object) {
         if (object.getSource() == restart && gamemode.equals("2PGame")){
       	  spielfeld.prepareForRestart();
       	  spielfeld.start2PGame(levelpath);
         } else if (object.getSource() == restart && gamemode.equals("Random2PGame")) {
        	 spielfeld.prepareForRestart();
        	 spielfeld.startRandomlevel2PGame();
         } else if (object.getSource() == restart && gamemode.equals("Random1PGame")) {
        	 spielfeld.prepareForRestart();
        	 spielfeld.startRandomlevel1PGame();
         } else if (object.getSource() == restart && gamemode.equals("1PGame")){
          	  spielfeld.prepareForRestart();
           	  spielfeld.start1PGame(levelpath);
             }
         if (object.getSource() == oneplayer){
       	  chooseLevelFor1P();
         }
          if (object.getSource() == twoplayer){
        	  chooseLevelFor2P();
          }
          if (object.getSource() == network){
        	  spielfeld.startNetworkGame();
        	  neu.setEnabled(false);
          }
          if (object.getSource() == beenden){
			if (spielfeld.servermode) {
				spielfeld.getBombserver().sendPrintln("beendet");
				spielfeld.getBombserver().setAction("beendet");
			}

			if (spielfeld.clientmode) {
				spielfeld.getBombclient().sendPrintln("beendet");
				spielfeld.getBombclient().setAction("beendet");
			}
			System.exit(0);
		}
     }
     
		public void chooseLevelFor2P() {
			
			// Richte JFrame ein
			chooseframe.setSize(300, 100);
			chooseframe.setResizable(false);
			chooseframe.setLocationRelativeTo(chooseframe.getParent());
			chooseframe.getContentPane().setLayout(null);
			
			chooseframe.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			    	
			    }
			});
			
			JButton abbrechen = new JButton();
			abbrechen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					chooseframe.setVisible(false);
					chooseframe.dispose();
				}
			});
			abbrechen.setText("Abbrechen");
			abbrechen.setBounds(160, 40, 100, 25);
				
			levelbox.setSelectedItem( "Level 1" );
		    levelbox.setMaximumRowCount( 6 );
		    levelbox.setBounds(140, 10, 130, 20);
			int number = levelbox.getSelectedIndex() + 1;
			levelpath = "src/readSpielfeld/level" + number + ".txt";
		    
			// Richte Ausgabetext ein
			JLabel text = new JLabel("Level auswaehlen:");
			text.setBounds(20, 15, 150, 10);
			
			JButton ok = new JButton();
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					chooseframe.setVisible(false);
					chooseframe.dispose();
					int number = levelbox.getSelectedIndex() + 1;
					if (number <= 5) {
						levelpath = "src/readSpielfeld/level" + number + ".txt";
						spielfeld.start2PGame(levelpath);
						gamemode = "2PGame";
					} else {
						spielfeld.startRandomlevel2PGame();
						gamemode = "Random2PGame";
					}
					restart.setEnabled(true);
					neu.setEnabled(false);
				}
			});
			ok.setText("OK");
			ok.setBounds(30, 40, 100, 25);
			
			Container cp = chooseframe.getContentPane();
			cp.add(abbrechen);
			cp.add(text);
			cp.add(levelbox);
			cp.add(ok);
			
			chooseframe.setVisible(true);
		}
		
		public void chooseLevelFor1P() {
			
			// Richte JFrame ein
			chooseframe.setSize(300, 100);
			chooseframe.setResizable(false);
			chooseframe.setLocationRelativeTo(chooseframe.getParent());
			chooseframe.getContentPane().setLayout(null);
			
			chooseframe.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			    	
			    }
			});
			
			JButton abbrechen = new JButton();
			abbrechen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					chooseframe.setVisible(false);
					chooseframe.dispose();
				}
			});
			abbrechen.setText("Abbrechen");
			abbrechen.setBounds(160, 40, 100, 25);
				
			levelbox.setSelectedItem( "Level 1" );
		    levelbox.setMaximumRowCount( 6 );
		    levelbox.setBounds(140, 10, 130, 20);
			int number = levelbox.getSelectedIndex() + 1;
			levelpath = "src/readSpielfeld/level" + number + ".txt";
		    
			// Richte Ausgabetext ein
			JLabel text = new JLabel("Level auswaehlen:");
			text.setBounds(20, 15, 150, 10);
			
			JButton ok = new JButton();
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					chooseframe.setVisible(false);
					chooseframe.dispose();
					int number = levelbox.getSelectedIndex() + 1;
					if (number <= 5) {
						levelpath = "src/readSpielfeld/level" + number + ".txt";
						spielfeld.start1PGame(levelpath);
						gamemode = "1PGame";
					} else {
						spielfeld.startRandomlevel1PGame();
						gamemode = "Random1PGame";
					}
					restart.setEnabled(true);
					neu.setEnabled(false);
				}
			});
			ok.setText("OK");
			ok.setBounds(30, 40, 100, 25);
			
			Container cp = chooseframe.getContentPane();
			cp.add(abbrechen);
			cp.add(text);
			cp.add(levelbox);
			cp.add(ok);
			
			chooseframe.setVisible(true);
		}
}
