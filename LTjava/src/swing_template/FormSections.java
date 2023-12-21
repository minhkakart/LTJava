package swing_template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormSections extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4695787034554643675L;

	protected final int DEFAULT_WIDTH = 800;
	protected final int DEFAULT_HEIGHT = 600;
	protected final double MARGIN = 0.05;

	protected JPanel sectionPanelWrapper;
	protected JPanel sectionsPanel;
	protected JPanel buttonsPanel;
	protected JPanel tablePanel;

	protected JPanel titlePanel;
	protected JPanel marginRightPanel;
	protected JPanel marginLeftPanel;

	protected JPanel formPanel;
	protected JPanel section1Panel;
//	protected JPanel section2Panel;
	
	protected JLabel title;

	public FormSections() {
		super();
		initialize();
	}

	protected void initialize() {
		this.setLayout(new BorderLayout());
		title = new JLabel("");
		
		sectionPanelWrapper = new JPanel(new BorderLayout());
		sectionsPanel = new JPanel(new BorderLayout(0, 20));
		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonsPanel.setPreferredSize(new Dimension(0, 50));
		tablePanel = new JPanel(new GridLayout(1, 1, 5, 5));

		titlePanel = new JPanel();
		marginRightPanel = new JPanel();
		marginLeftPanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension((int) (DEFAULT_WIDTH * MARGIN), (int) (DEFAULT_HEIGHT * MARGIN)));
		marginRightPanel
				.setPreferredSize(new Dimension((int) (DEFAULT_WIDTH * MARGIN), (int) (DEFAULT_HEIGHT * MARGIN)));
		marginLeftPanel
				.setPreferredSize(new Dimension((int) (DEFAULT_WIDTH * MARGIN), (int) (DEFAULT_HEIGHT * MARGIN)));

		formPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		section1Panel = new JPanel(new GridLayout(0, 2, 10, 10));
//		section2Panel = new JPanel(new GridLayout(0, 2, 10, 10));
		

		titlePanel.add(title);
		
		sectionPanelWrapper.add(titlePanel, BorderLayout.NORTH);
		sectionPanelWrapper.add(marginRightPanel, BorderLayout.EAST);
		sectionPanelWrapper.add(marginLeftPanel, BorderLayout.WEST);
		sectionPanelWrapper.add(sectionsPanel, BorderLayout.CENTER);

		sectionsPanel.add(formPanel, BorderLayout.CENTER);
		sectionsPanel.add(buttonsPanel, BorderLayout.SOUTH);
		formPanel.add(section1Panel);
//		formPanel.add(section2Panel);
		

		// Set colors
		sectionsPanel.setBackground(Color.WHITE);
		titlePanel.setBackground(Color.MAGENTA);
		marginRightPanel.setBackground(Color.BLUE);
		marginLeftPanel.setBackground(Color.GREEN);
		formPanel.setBackground(Color.YELLOW);

		tablePanel.setBackground(Color.BLACK);

		this.add(sectionPanelWrapper, BorderLayout.CENTER);
		this.add(tablePanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
//		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public void addSection(JPanel section) {
		this.formPanel.add(section);
	}
	
	public void addButtons(JButton... buttons) {
		for (JButton button : buttons) {
			this.buttonsPanel.add(button);
		}
	}

	public JPanel getSectionsPanel() {
		return this.sectionsPanel;
	}
	
	public JPanel getButtonsPanel() {
		return this.buttonsPanel;
	}

	public JPanel getTablePanel() {
		return this.tablePanel;
	}

	public JPanel getSection1Panel() {
		return this.section1Panel;
	}
	
	public JPanel getTitlePanel() {
		return this.titlePanel;
	}

//	public JPanel getSection2Panel() {
//		return this.section2Panel;
//	}
	
	public JLabel getTitleLabel() {
		return this.title;
	}

	public void setSize(int width, int height) {
		super.setSize(width, height);
//		titlePanel.setPreferredSize(new Dimension((int) (width * MARGIN), (int) (height * MARGIN)));
		marginRightPanel.setPreferredSize(new Dimension((int) (width * MARGIN), (int) (height * MARGIN)));
		marginLeftPanel.setPreferredSize(new Dimension((int) (width * MARGIN), (int) (height * MARGIN)));
	}
	
	public void setFormTitle(String title) {
		this.title.setText(title);
	}

}
