package menelaus.view;

import menelaus.model.Level;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * The panel which encompasses many other game panels.
 * @author frankegan on 4/15/16.
 * <p>
 * Define a base class for all our panels to extend. Includes constants for defining panel sizes,
 * and methods for dragging views. Will be used for handling all mous events inside of panel and delegating them to
 * their respective controllers.
 * </p>
 */
public class KabasujiPanel extends JPanel {
    /**
     * Define the start point for all panels that extend our class.
     */
    public static final int START_X = 100;
    
    /**
     * Define the start point for all panels that extend our class.
     */
    public static final int START_Y = 100;
    
    /**
     * Define the width for all panels that extend our class.
     */
    public static final int WIDTH = 1000;
    
    /**
     * Define the height for all panels that extend our class.
     */
    public static final int HEIGHT = 750;
    
    /**
     * Double Buffering technique requires an offscreen image.
     */
    Image offscreenImage;
    Graphics offscreenGraphics;
    
    /**
     * Level that is being played.
     */
    Level level;
    
    /**
     * Current mouse listeners.
     */
    MouseListener activeListener;
    
    /**
     * Current mouse listeners.
     */
    MouseMotionListener activeMotionListener;

    Graphics canvasGraphics;

    /**
     * Define a base class for all our panels to extend. Includes constants for defining panel sizes,
     * and methods for dragging views. Will be used for handling all mous events inside of panel and delegating them to
     * their respective controllers.
     */
    public KabasujiPanel() {
        setBounds(START_X, START_Y, WIDTH, HEIGHT);
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    /**
     * Properly register new listener (and unregister old one if present).
     * @param ml the given mouselistener.
     */
    public void setActiveListener(MouseListener ml) {
        this.removeMouseListener(activeListener);
        activeListener = ml;
        if (ml != null) {
            this.addMouseListener(ml);
        }
    }

    /**
     * Properly register new motion listener (and unregister old one if present).
     * @param mml the given MouseMotionListener.
     */
    public void setActiveMotionListener(MouseMotionListener mml) {
        this.removeMouseMotionListener(activeMotionListener);
        activeMotionListener = mml;
        if (mml != null) {
            this.addMouseMotionListener(mml);
        }
    }
    
	protected void paintComponent(Graphics g) {
		BufferedImage backgroundIMG = null;
		
		super.paintComponent(g);
		try {
			backgroundIMG = ImageIO.read(this.getClass().getResource("/assets/back.jpg"));
            if (backgroundIMG != null) {
                g.drawImage(backgroundIMG.getScaledInstance(1000, 750, Image.SCALE_DEFAULT), 0, 0, null);
            }
        } catch (Exception e) {
			System.out.println("KabaSuji Panel: the image isn't read");
		}		
	}
}
