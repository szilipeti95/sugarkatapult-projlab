import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Zotya
 * menupontok ebbol erhetok el: start, save, select, load, exit
 * tarolja hogy melyik palya van kivalsztva / hol tartunk a progressben
 */
public class Menu extends JPanel{//menupontok itt
	private static final long serialVersionUID = 1L;//ez kell JPanel miatt..
	
	//ezeket jo ha megjegyezzuk, grafikus feluleten akkor ki lehet majd jelezni
	public static int progress=0;//hanyadik palya
	private String palya = "1. palya.txt";//palya neve
	private BufferedImage hatter;
    
	JButton bPlay;
	JButton bLoad;
	JButton bSave;
	//JButton bSelect;
	JComboBox cSelect;
	JButton bExit;
	
	public Menu()
	{
		bPlay = new JButton("Play");
		this.add(bPlay);
		bPlay.addActionListener(new Gombnyomasok());
		
		bLoad = new JButton("Load progress");
		this.add(bLoad);
		bLoad.addActionListener(new Gombnyomasok());
		
		bSave = new JButton("Save progress");
		this.add(bSave);
		bSave.addActionListener(new Gombnyomasok());
		
		/*bSelect = new JButton("Select Map");
		this.add(bSelect);
		bSelect.addActionListener(new Gombnyomasok());*/
		cSelect = new JComboBox<String>();
		cSelect.addItem("1. palya");
		cSelect.setEditable(false);
		cSelect.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED)
					palya=e.getItem().toString()+".txt";
			}});
		this.add(cSelect);
		
		bExit = new JButton("Exit");
		this.add(bExit);
		bExit.addActionListener(new Gombnyomasok());
                               
        try{
            hatter = ImageIO.read(new File("assets\\menu.png"));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
                
                
                
	}
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(hatter != null){
                g.drawImage(hatter, 0, 0, null);
            }
        }
	
	class Gombnyomasok implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource()==bPlay)
			{
				start();
			}
			else if(arg0.getSource()==bLoad)
			{
				load();
			}
			else if(arg0.getSource()==bSave)
			{
				save();
			}
			/*else if(arg0.getSource()==bSelect)
			{
				select();
			}*/
			else if(arg0.getSource()==bExit)
			{
				exit();
			}
		}

    }
	
        
	/**
	 * progress alapjan eloallitja a palya nevet es elinditja a jatekot
	 */
	public void start() 
	{//uj Jatek
		//uj jatek inditasa
		if(palya!="" && cSelect.getSelectedIndex()<=progress)
		{
			Jatek.getInstance().start(palya);
			CardLayout cardl = (CardLayout) this.getParent().getLayout();
			cardl.show(this.getParent(), "rajzolo");
		}
		else
			JOptionPane.showMessageDialog(null, "ez a palya meg nincs feloldva");//System.out.println("valasszon egy palyat eloszor (loadmap)");
	}
	
	/**
	 * elmenti a jelenlegi allast fajlba
	 */
	public void save() 
	{
		/*try
		{//fajlba ment
			BufferedWriter wr = new BufferedWriter(new FileWriter("progress.ctsf"));
			wr.write(progress);
			wr.close();
			JOptionPane.showMessageDialog(null, "Progress saved!");
		}
		catch (Exception ex)
		{//valamiert nem sikerult az iras, sajnos nem tudjuk menteni az allast
			JOptionPane.showMessageDialog(null, "hiba mentesnel! " + ex.getMessage());
		}*/
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));//TODO: map mappa
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Crazy Trains Save File", "ctsf"));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    try
			{//fajlba ir
				BufferedWriter br = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile().getAbsolutePath()+".ctsf"));
				br.write(String.valueOf(progress));
				br.close();
				JOptionPane.showMessageDialog(null, "Progress saved: " + (progress+1) + ". map");
			}
			catch (Exception ex)
			{//meg nincs progress fajl vagy hibas, akkor eloszor jatszik
				JOptionPane.showMessageDialog(null, "Hiba mentesnel!");
			}
		}	
	}
	
	/**
	 * palya kivalaszt
	 * @return kovetkezo palya szama
	 */
	/*public int select() 
	{//palya szamat adja vissza
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));//TODO: map mappa
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    palya=fileChooser.getSelectedFile().getAbsolutePath();
		}
		
		
		progress=0;//TODO minden palyahoz sajat progress kene, pl fajlnevbol akar
		return 0;
	}*/
	
	/**
	 * progresst betolti fajlbol: hanyadik palyanal tartunk. 0 ha nincs fajl vagy hibas
	 */
	public void load() 
	{		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));//TODO: map mappa
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Crazy Trains Save File", "ctsf"));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    try
			{//fajlbol betolt
				BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()));//hiba ha nincs ilyen fajl
				progress = Integer.parseInt(br.readLine());//int konverzio, itt is johet hiba
				br.close();
				JOptionPane.showMessageDialog(null, "Progress loaded: " + (progress+1) + ". map");
				cSelect.removeAllItems();
				for(int i=0;i<=progress;i++)
					cSelect.addItem((i+1)+". palya");
				cSelect.setSelectedIndex(progress);
			}
			catch (Exception ex)
			{//meg nincs progress fajl vagy hibas, akkor eloszor jatszik
				progress=0;
				JOptionPane.showMessageDialog(null, "Nincs meg progress! Elso palyanal kezdunk.");
			}
		}		
	}
	
	/**
	 * kilep az egeszbol
	 */
	public void exit() 
	{
		System.exit(0);
	}

	/**
	 * csak eltarolja a map fajl nevet, foleg protoban kell
	 * @param p Eleresi ut
	 */
	/*public void loadMap(String p) {
		palya = p;
	}*/
}
