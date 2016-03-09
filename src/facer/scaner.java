
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
        
        //conver to greyscale
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
        //find gradient
         float grad[][] = new float[imwidth][imheight];
         float surr=0;
         float max=0;
         for (int i =0;i<imwidth;i++)
            for (int j =0;j<imheight;j++)
            {
              Color  col = new Color(img.getRGB(i, j));
              int val = (int)(col.getRed());
              if( i>=1 && i<=imwidth-2 && j>=1 && j<=imheight-2)
              {
                 surr = new Color(img.getRGB(i-1,j-1)).getRed()+new Color(img.getRGB(i-1,j)).getRed()+new Color(img.getRGB(i-1,j+1)).getRed();
                 surr +=new Color(img.getRGB(i,j-1)).getRed()+new Color(img.getRGB(i,j+1)).getRed();
                 surr += new Color(img.getRGB(i+1,j-1)).getRed()+new Color(img.getRGB(i+1,j)).getRed()+new Color(img.getRGB(i+1,j+1)).getRed();
                 grad[i][j] = 8*val-(surr);
              }
              max = (max<grad[i][j])?grad[i][j]:max;
              
                
            }
        //normalise
        for (int i =0;i<imwidth;i++)
            for (int j =0;j<imheight;j++)
            {
               grad[i][j] /= max;
               if(grad[i][j]>0.6)
               {
                    Color newcol = new Color(250,0,0);
                    img.setRGB(i,j, newcol.getRGB());
                   
               }
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
