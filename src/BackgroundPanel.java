import javax.swing.*;
import java.awt.*;

/**
 * Lead Author(s): Jiaqi Zhang
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:
 * Object-Oriented Problem Solving.
 * Retrieved from:
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Oracle Java Documentation:
 * https://docs.oracle.com/javase/8/docs/api/
 * 
 * Responsibilities of class:
 * BackgroundPanel draws a background image for the GUI.
 */
public class BackgroundPanel extends JPanel
{
	private Image backgroundImage;

	public BackgroundPanel()
	{
		// Put cuteCatBg.jpg in the project root folder, not inside src.
		backgroundImage = new ImageIcon("cuteCatBg.png").getImage();

		setLayout(new BorderLayout(15, 15));
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (backgroundImage != null)
		{
			int imageWidth = backgroundImage.getWidth(this);
			int imageHeight = backgroundImage.getHeight(this);

			if (imageWidth > 0 && imageHeight > 0)
			{
				// Draw the image repeatedly so it keeps its original size.
				for (int x = 0; x < getWidth(); x += imageWidth)
				{
					for (int y = 0; y < getHeight(); y += imageHeight)
					{
						g.drawImage(backgroundImage, x, y, this);
					}
				}
			}
		}
	}
}