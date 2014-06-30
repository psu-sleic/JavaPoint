package JavaPoint;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class JavaPoint {

	public JavaPoint(){
		MyFrame frame = new MyFrame();
		frame.cycle(2000);
	}
	
	public static void main(String[] args) {
		new JavaPoint();
	}

}

class MyFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public Component[] bi;
	public String[] paths;
	public MyFrame(){
		super("Display Your Pictures");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setSize(screenSize.width,screenSize.height);
		
		File file = new File("res");
		paths = file.list();
		bi = new Component[file.list().length];
		for (int i=0;i<bi.length;i++) {
			bi[i] = new MyImage("/" + paths[i]);
		}
		
		setResizable(false);
		setVisible(true);
	}
	
	public void cycle(int duration){
		int i=0;
		boolean go = true;
		while (go) {
			add("Center", bi[i]);
			validate();
			repaint();
			try {
				Thread.sleep(duration);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			remove(bi[i]);
			i++;
			if (i==bi.length){
				i = 0;
			}
		}
	}
	
}

class MyImage extends Component {
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	public MyImage(String path){
		img = null;
		try {
			img = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
		}
	}
	public void paint(Graphics g){
		g.drawImage(img,0,0,null);
	}
}