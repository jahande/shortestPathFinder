package views;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import models.ClickState;
import models.MyPolygon;
import models.Question;

public class Main extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8202121861424374585L;
	private Question question = new Question();
	private ClickState clickState = new ClickState();

	private final DrawPanel darwPanel = new DrawPanel(this);
	private final JPanel buttonPanel = new JPanel();
	private final JButton startOutterButton = new JButton();
	private final JButton endOutterButton = new JButton();
	private final JButton startInnerButton = new JButton();
	private final JButton endInnerButton = new JButton();
	private final JButton calculateButton = new JButton();
	private final JButton resetButton = new JButton();
	private final JButton specifyStartButton = new JButton();
	private final JButton specifyEndButton = new JButton();
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Main frame = new Main();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame
	 */
	public Main() {
		super();
		setBounds(100, 100, 879, 440);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			jbInit();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//
	}
	private void jbInit() throws Exception {
		getContentPane().setLayout(null);
		
		getContentPane().add(darwPanel);
		darwPanel.setBackground(Color.WHITE);
		darwPanel.setBorder(new LineBorder(Color.black, 1, false));
		darwPanel.addMouseListener(new DarwPanelMouseListener());
		darwPanel.setBounds(25, 17, 805, 279);
		
		getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(30, 316, 805, 81);
		
		buttonPanel.add(startOutterButton);
		startOutterButton.addActionListener(new StartOutterButtonActionListener());
		startOutterButton.setBounds(10, 10, 106, 26);
		startOutterButton.setText("start outter");
		
		buttonPanel.add(endOutterButton);
		endOutterButton.addActionListener(new EndOutterButtonActionListener());
		endOutterButton.setText("end outter");
		endOutterButton.setBounds(133, 10, 106, 26);
		
		buttonPanel.add(startInnerButton);
		startInnerButton.addActionListener(new StartInnerButtonActionListener());
		startInnerButton.setText("start inner");
		startInnerButton.setBounds(288, 10, 106, 26);
		
		buttonPanel.add(endInnerButton);
		endInnerButton.addActionListener(new EndInnerButtonActionListener());
		endInnerButton.setText("end inner");
		endInnerButton.setBounds(400, 10, 106, 26);
		
		buttonPanel.add(calculateButton);
		calculateButton.addActionListener(new CalculateButtonActionListener());
		calculateButton.setText("calculate");
		calculateButton.setBounds(161, 55, 106, 26);
		
		buttonPanel.add(resetButton);
		resetButton.addActionListener(new ResetButtonActionListener());
		resetButton.setText("reset");
		resetButton.setBounds(273, 55, 106, 26);
		
		buttonPanel.add(specifyStartButton);
		specifyStartButton.addActionListener(new SpecifyStartButtonActionListener());
		specifyStartButton.setText("specify start");
		specifyStartButton.setBounds(563, 10, 106, 26);
		
		buttonPanel.add(specifyEndButton);
		specifyEndButton.addActionListener(new SpecifyEndButtonActionListener());
		specifyEndButton.setText("specify end");
		specifyEndButton.setBounds(675, 10, 106, 26);
	}
	private class DarwPanelMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			darwPanel_mousePressed(e);
		}
	}
	private class StartOutterButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			startOutterButton_actionPerformed(e);
		}
	}
	private class EndOutterButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			endOutterButton_actionPerformed(e);
		}
	}
	private class StartInnerButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			startInnerButton_actionPerformed(e);
		}
	}
	private class EndInnerButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			endInnerButton_actionPerformed(e);
		}
	}
	private class SpecifyStartButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			specifyStartButton_actionPerformed(e);
		}
	}
	private class SpecifyEndButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			specifyEndButton_actionPerformed(e);
		}
	}
	private class ResetButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resetButton_actionPerformed(e);
		}
	}
	private class CalculateButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			calculateButton_actionPerformed(e);
		}
	}
	protected void darwPanel_mousePressed(MouseEvent e) {
		final int x = e.getX();
		final int y = e.getY();
		switch (this.clickState.getState()) {
		case ClickState.WAIT_FOR_END_POINT:
			this.question.setEndPoint(new Point(x,y));
			this.clickState.setState(ClickState.WAIT_FOR_ANY_START);
			this.darwPanel.repaint();
			break;
		case ClickState.WAIT_FOR_START_POINT:
			this.question.setStartPoint(new Point(x,y));
			this.clickState.setState(ClickState.WAIT_FOR_ANY_START);
			this.darwPanel.repaint();
			break;
		case ClickState.WAIT_FOR_END_OUTTER:
			this.question.getOutterPolygon().addPoint(x, y);	
			this.question.getAllpoints().add(new Point(x,y));
			break;
		case ClickState.WAIT_FOR_END_INNER:
			
			this.question.addToLastInnerPolygon(x, y);
			
			break;

		default:
			break;
		}
		repaint();
		//this.darwPanel.get
		
	}

	public Question getQuestion() {
		return question;
	}
	protected void startOutterButton_actionPerformed(ActionEvent e) {
		this.clickState.setState(ClickState.WAIT_FOR_END_OUTTER);
	}
	protected void endOutterButton_actionPerformed(ActionEvent e) {
		this.question.setOutterFinalized(true);
		this.clickState.setState(ClickState.WAIT_FOR_ANY_START);
		this.darwPanel.repaint();
	}
	protected void startInnerButton_actionPerformed(ActionEvent e) {
		this.question.setInnerFinalized(false);
		this.question.getInnerPolygons().add(new MyPolygon());
		this.clickState.setState(ClickState.WAIT_FOR_END_INNER);
	}
	protected void endInnerButton_actionPerformed(ActionEvent e) {
		this.question.setInnerFinalized(true);
		this.clickState.setState(ClickState.WAIT_FOR_ANY_START);
		this.darwPanel.repaint();
	}
	protected void specifyStartButton_actionPerformed(ActionEvent e) {
		this.clickState.setState(ClickState.WAIT_FOR_START_POINT);
	}
	protected void specifyEndButton_actionPerformed(ActionEvent e) {
		
		this.clickState.setState(ClickState.WAIT_FOR_END_POINT);
	}
	protected void resetButton_actionPerformed(ActionEvent e) {
		this.question = new Question();
		this.clickState.setState(ClickState.WAIT_FOR_ANY_START);
		this.darwPanel.repaint();
	}
	protected void calculateButton_actionPerformed(ActionEvent e) {
		this.question.calculateShortestPath();
		this.darwPanel.repaint();
		
	}

}
