package views;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5455276733224716887L;
	
	private Main main;
	public DrawPanel(Main main) {
		this.main = main;
	}

	@Override
    public void paint(Graphics g)
    {
        super.paint(g);
        main.getQuestion().draw(g);
        

    }
}
