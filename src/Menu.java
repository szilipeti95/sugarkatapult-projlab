import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Zotya
 * menupontok ebbol erhetok el: start, save, select, load, exit
 * tarolja hogy melyik palya van kivalsztva / hol tartunk a progressben
 */
public class Menu extends JPanel{//menupontok itt
	private static final long serialVersionUID = 1L;//ez kell JPanel miatt..
	
	//ezeket jo ha megjegyezzuk, grafikus feluleten akkor ki lehet majd jelezni
	private int progress=0;//hanyadik palya
	private String palya = "";//palya neve
	
	JButton bPlay;
	JButton bLoad;
	JButton bSave;
	JButton bSelect;
	JButton bExit;
	
	public Menu()
	{
		bPlay = new JButton("Play");
		this.add(bPlay);
		bPlay.addActionListener(new Gombnyomasok());
		
		bLoad = new JButton("Load");
		this.add(bLoad);
		bLoad.addActionListener(new Gombnyomasok());
		
		bSave = new JButton("Save");
		this.add(bSave);
		bSave.addActionListener(new Gombnyomasok());
		
		bSelect = new JButton("Select Map");
		this.add(bSelect);
		bSelect.addActionListener(new Gombnyomasok());
		
		bExit = new JButton("Exit");
		this.add(bExit);
		bExit.addActionListener(new Gombnyomasok());
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
			else if(arg0.getSource()==bSelect)
			{
				select();
			}
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
		if(palya!="")
		{
			Jatek.getInstance().start(palya);
			CardLayout cardl = (CardLayout) this.getParent().getLayout();
			cardl.show(this.getParent(), "rajzolo");
		}
		else
			JOptionPane.showMessageDialog(null, "valasszon egy palyat eloszor (Select Map)");//System.out.println("valasszon egy palyat eloszor (loadmap)");
	}
	
	/**
	 * elmenti a jelenlegi allast fajlba
	 */
	public void save() 
	{
		try
		{//fajlba ment
			BufferedWriter wr = new BufferedWriter(new FileWriter("progress.txt"));
			wr.write(progress);
			wr.close();
			JOptionPane.showMessageDialog(null, "Progress saved!");
		}
		catch (Exception ex)
		{//valamiert nem sikerult az iras, sajnos nem tudjuk menteni az allast
			JOptionPane.showMessageDialog(null, "hiba mentesnel! " + ex.getMessage());
		}
		
	}
	
	/**
	 * palya kivalaszt
	 * @return kovetkezo palya szama
	 */
	public int select() 
	{//palya szamat adja vissza
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("assets"));//TODO: map mappa
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    palya=fileChooser.getSelectedFile().getAbsolutePath();
		}
		
		
		progress=0;//TODO minden palyahoz sajat progress kene, pl fajlnevbol akar
		return 0;
	}
	
	/**
	 * progresst betolti fajlbol: hanyadik palyanal tartunk. 0 ha nincs fajl vagy hibas
	 * @return hanyadik palyanal jarunk
	 */
	public int load() 
	{
		try
		{//fajlbol betolt
			BufferedReader br = new BufferedReader(new FileReader("progress.txt"));//hiba ha nincs ilyen fajl
			progress = Integer.parseInt(br.readLine());//int konverzio, itt is johet hiba
			br.close();
			JOptionPane.showMessageDialog(null, "Progress loaded!");
		}
		catch (Exception ex)
		{//meg nincs progress fajl vagy hibas, akkor eloszor jatszik
			progress=0;
			JOptionPane.showMessageDialog(null, "Nincs meg progress! Elso palyanal kezdunk.");
		}
		
		palya = "palya"+String.valueOf(progress)+".txt";
		
		return progress;
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
	public void loadMap(String p) {
		palya = p;
	}
}
