import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Lead Author(s): Jiaqi Zhang
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:
 * Object-Oriented Problem Solving.
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Oracle JPanel Documentation:
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html
 *
 * Oracle ImageIcon Documentation:
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/ImageIcon.html
 *
 * Responsibilities of class:
 * BackgroundPanel draws the cat background behind the Swing controls.
 */
public class BackgroundPanel extends JPanel
{
	private static final int PANEL_GAP = 15;

	private Image backgroundImage;

	/**
	 * Creates a background panel and loads the image used by the GUI.
	 */
	public BackgroundPanel()
	{
		// I keep the image name in one place so the GUI is easy to move with
		// the project files.
		backgroundImage = new ImageIcon("cuteCatBg.png").getImage();
		setLayout(new BorderLayout(PANEL_GAP, PANEL_GAP));
	}

	/**
	 * Draws the background image.
	 *
	 * @param graphics the Graphics object used to draw the panel
	 */
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);

		if (backgroundImage != null)
		{
			int imageWidth = backgroundImage.getWidth(this);
			int imageHeight = backgroundImage.getHeight(this);

			if (imageWidth > 0 && imageHeight > 0)
			{
				tileBackground(graphics, imageWidth, imageHeight);
			}
		}
	}

	/**
	 * Repeats the image across the panel.
	 *
	 * @param graphics    object used for drawing
	 * @param imageWidth  width of one background image
	 * @param imageHeight height of one background image
	 */
	private void tileBackground(Graphics graphics, int imageWidth,
			int imageHeight)
	{
		// Tiling avoids stretching, so the cat picture still looks normal when
		// the user changes the window size.
		for (int x = 0; x < getWidth(); x += imageWidth)
		{
			for (int y = 0; y < getHeight(); y += imageHeight)
			{
				graphics.drawImage(backgroundImage, x, y, this);
			}
		}
	}
}