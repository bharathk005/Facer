
package facer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class scaner implements Runnable
{
    Facer f1 = new Facer();
    @Override
    public void run() 
    {
        JFrame frame = new JFrame("image1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        BufferedImage img = null;
        
        try {
            img = ImageIO.read(new File("C:\\Users\\bharathk\\Documents\\faces\\img2.jpg"));
            } catch (IOException ex) {
            Logger.getLogger(scaner.class.getName()).log(Level.SEVERE, null, ex);
            }
        int imwidth = img.getWidth();
        int imheight= img.getHeight();

        for (int i =0;i<imwidth;i++)
            for (int j =0;j<imheight;j++)
            {
              Color  col = new Color(img.getRGB(i, j));
              int red = (int)(col.getRed()*0.299);
              int blue = (int)(col.getBlue()*0.114);
              int green = (int)(col.getGreen()*0.587);
              Color newcol = new Color(red+green+blue,red+green+blue,red+green+blue);
              img.setRGB(i,j, newcol.getRGB());
                
            }
  
        
        ImageIcon icon = new ImageIcon(img);
        JLabel label1 = new JLabel();
        label1.setIcon(icon);
        frame.getContentPane().add(label1,BorderLayout.CENTER);
        frame.pack();
      //  frame.setSize(f1.width,f1.height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
}
