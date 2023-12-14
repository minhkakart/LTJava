package drag;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ImageIcon imageRaw;
	ImageIcon image;

	JLabel label;

	int WIDTH;
	int HEIGHT;

	int centerX;
	int centerY;

	Point imageCorner;
	Point prevPt;

	public Panel() {
		imageRaw = new ImageIcon("src/ei.jpg");
		image = new ImageIcon(imageRaw.getImage().getScaledInstance(imageRaw.getIconWidth() / 2,
				imageRaw.getIconHeight() / 2, java.awt.Image.SCALE_SMOOTH));
		
		label = new JLabel(image);
		
		WIDTH = 200; 
		HEIGHT = 200;
		
		centerX = (WIDTH - image.getIconWidth()) / 2; 
		centerY = (WIDTH - image.getIconHeight()) / 2;
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		imageCorner = new Point(centerX, centerY);

		this.addMouseListener(new MouseClickListner());
		this.addMouseMotionListener(new MouseMotionListner());
	}
	public Panel(String imgPath) {
		imageRaw = new ImageIcon(imgPath);
		image = new ImageIcon(imageRaw.getImage().getScaledInstance(imageRaw.getIconWidth() / 2,
				imageRaw.getIconHeight() / 2, java.awt.Image.SCALE_SMOOTH));
		
		label = new JLabel(image);
		
		WIDTH = 200; 
		HEIGHT = 200;
		
		centerX = (WIDTH - image.getIconWidth()) / 2; 
		centerY = (WIDTH - image.getIconHeight()) / 2;
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		imageCorner = new Point(centerX, centerY);

		this.addMouseListener(new MouseClickListner());
		this.addMouseMotionListener(new MouseMotionListner());
	}
	
	public Panel(String imgPath, int width, int height) {
		imageRaw = new ImageIcon(imgPath);
		image = new ImageIcon(imageRaw.getImage().getScaledInstance(imageRaw.getIconWidth() / 2,
				imageRaw.getIconHeight() / 2, java.awt.Image.SCALE_SMOOTH));
		
		label = new JLabel(image);
		
		WIDTH = width; 
		HEIGHT = height;
		
		centerX = (WIDTH - image.getIconWidth()) / 2; 
		centerY = (WIDTH - image.getIconHeight()) / 2;
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		imageCorner = new Point(centerX, centerY);

		this.addMouseListener(new MouseClickListner());
		this.addMouseMotionListener(new MouseMotionListner());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());
	}

	private class MouseClickListner extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			prevPt = e.getPoint();
		}

	}

	private class MouseMotionListner extends MouseMotionAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			Point currentPt = e.getPoint();
			int currPosX = (int) imageCorner.getX();
			int currPosY = (int) imageCorner.getY();
			int translateX = (int) (currentPt.getX() - prevPt.getX());
			int translateY = (int) (currentPt.getY() - prevPt.getY());
			int imageWidth = image.getIconWidth();
			int imageHeight = image.getIconHeight();

			if ((currPosX <= 0 && currPosX + translateX <= 0) && (currPosY <= 0 && currPosY + translateY <= 0)
					&& (currPosX >= WIDTH - imageWidth && currPosX + translateX >= WIDTH - imageWidth)
					&& (currPosY >= HEIGHT - imageHeight && currPosY + translateY >= HEIGHT - imageHeight)) {
				imageCorner.translate((int) (currentPt.getX() - prevPt.getX()),
						(int) (currentPt.getY() - prevPt.getY()));
				prevPt = currentPt;
				repaint();

			}
		}

	}

}
