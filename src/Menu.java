import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
 * JPanel leszarmazottja: hatter kirajzolas es gui elemek
 */
public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;//ez kell JPanel miatt..
	public int progress=0;//hanyadik palya. pl: 0.progress -> 1. palya.txt
	private String palya = "1. palya.txt";//palya neve, ez a default elso palya
	private BufferedImage hatter;//crazy train hatterkep
    
	JButton bPlay;//jatek elinditasara gomb
	JButton bLoad;//progress betoltesere gomb
	JButton bSave;//progress mentesere gomb
	JComboBox<String> cSelect;//a palyak kivalasztasara legordulo
	JButton bExit;//kilepesre gomb
	
	/**
	 * uj palya elerhetove tetele
	 * pl nyeres utan hivodhat meg, ha meg nincs feloldva a kovetkezo palya
	 */
    public void addProgress(){
        progress++;
        cSelect.addItem((progress+1)+". palya");//legordulobe uj elem
    }
    
    /**
     * getter a progresshez
     * @return progress hogy hanyadik palya van feloldva
     */
    public int getProgress(){
        return progress;
    }
    
    /**
     * konstruktor
     * init gui elemek
     * init hatterkep
     */
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
		
		cSelect = new JComboBox<String>();
		cSelect.addItem("1. palya");
		cSelect.setEditable(false);
		cSelect.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					palya=e.getItem().toString()+".txt";//legorduloben elem kivalasztasakor beallitjuk az uj palya helyet
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
        
	/**
	 * kirajzolas fuggveny, csak itt lehet ra rajzolni
	 * parent kirajzolast is meg kell hivni
	 * csak akkor rajzolunk hatteret, ha azt az initnel sikerult betolteni
	 */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(hatter != null){
            g.drawImage(hatter, 0, 0, null);
        }
    }
	
    /*
     * osztaly a gombra kattintasok figyelesere
     * az osszes osztaly megkapja ezt listenerkent
     * eldonti hogy mire kattintottak
     * es meghivja a megfelelo fuggvenyt
     */
	class Gombnyomasok implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource()==bPlay)
				start();
			else if(arg0.getSource()==bLoad)
				load();
			else if(arg0.getSource()==bSave)
				save();
			else if(arg0.getSource()==bExit)
				exit();
		}
    }
	
	/**
	 * elinditja a jatekot a kivalasztott palyaval
	 * nem engedi, ha meg nincs feloldva az adott palya
	 * (de ez meg van oldva hogy ilyen ne legyen)
	 */
	public void start() 
	{
		if(!"".equals(palya) && cSelect.getSelectedIndex()<=progress)
		{
			Jatek.getInstance().start("./assets/maps/"+palya);
			CardLayout cardl = (CardLayout) this.getParent().getLayout();
			cardl.show(this.getParent(), "rajzolo");//atvaltas a terepasztal nezetre
		}
		else
			JOptionPane.showMessageDialog(null, "ez a palya meg nincs feloldva");
	}
	
	/**
	 * elmenti a jelenlegi allast fajlba
	 */
	public void save() 
	{	
		JFileChooser fileChooser = new JFileChooser();//fajl kivalaszto init
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Crazy Trains Save File", "ctsf"));//szuro, hogy csak progress fajl lehessen mentheto
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//mappakat se valasszunk ki
		fileChooser.setAcceptAllFileFilterUsed(false);
		int result = fileChooser.showOpenDialog(this);//megjelenit
		if (result == JFileChooser.APPROVE_OPTION) {
		    try
			{//fajlba ir
				BufferedWriter br = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile().getAbsolutePath()+".ctsf"));
				br.write(String.valueOf(progress));//kiirjuk a progress erteket
				br.close();
				JOptionPane.showMessageDialog(null, "Progress saved: " + (progress+1) + ". map");//ertesitjuk a jatekost
			}
			catch (Exception ex)
		    {//hiba fajl iraskor, ertesitjuk a jatekost
				JOptionPane.showMessageDialog(null, "Hiba mentesnel!");
		    }
		}	
	}
	
	/**
	 * progresst betolti fajlbol: hanyadik palyanal tartunk. 0 ha nincs fajl vagy hibas
	 */
	public void load()
	{		
		JFileChooser fileChooser = new JFileChooser();//fajl kivalaszto init
		fileChooser.setCurrentDirectory(new File("."));//TODO: /maps konyvtar vagy valami
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Crazy Trains Save File", "ctsf"));//szuro, hogy csak progress fajl lehessen betoltheto
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//mappakat se valasszunk ki
		fileChooser.setAcceptAllFileFilterUsed(false);
		int result = fileChooser.showOpenDialog(this);//megjelenit
		if (result == JFileChooser.APPROVE_OPTION) {
		    try
			{//fajlbol betolt
				BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()));//hiba ha nincs ilyen fajl
				progress = Integer.parseInt(br.readLine());//int konverzio, itt is johet hiba
				br.close();
				JOptionPane.showMessageDialog(null, "Progress loaded: " + (progress+1) + ". map");//ertesitjuk a jatekost
				cSelect.removeAllItems();
				for(int i=0;i<=progress;i++)//automatikusan atvaltunk a legutobbi palyara ahol tartott a jatekos
					cSelect.addItem((i+1)+". palya");
				cSelect.setSelectedIndex(progress);
			}
			catch (Exception ex)
			{//hiba, akkor elolrol indul a progress
				progress=0;
				JOptionPane.showMessageDialog(null, "Hiba, betoltesnel! Elso palyanal kezdunk.");//ertesitjuk a jatekost
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
}
