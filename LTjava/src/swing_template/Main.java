package swing_template;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Column 1");
		columnNames.add("Column 2");
		columnNames.add("Column 3");
		columnNames.add("Column 4");
		
		TableModelEditable model = new TableModelEditable(columnNames, 0);
		TableModelEditable model2 = new TableModelEditable(columnNames, 0);
		model2.setEditable(true);
		
		for (int i = 0; i < 20; i++) {
			Vector<String> row = new Vector<String>();
			for (int j = 0; j < 4; j++) {
				row.add("Row " + i + " Col " + j);
			}
			model.addRow(row);
		}
		
		for (int i = 0; i < 10; i++) {
			Vector<String> row = new Vector<String>();
			for (int j = 0; j < 4; j++) {
				row.add("Row " + i + " Col " + j);
			}
			model2.addRow(row);
		}
		
		
		ScrollTable table = new ScrollTable(model);
		table.setDimention(new Dimension(600, 100));
		
		JButton button2 = new JButton("Change to Model 2");
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.setModel(model2);
			}
		});
		
		JButton button1 = new JButton("Change to Model 1");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.setModel(model);
			}
		});
		
		JButton deleteDatamodel2 = new JButton("Delete data model 2");
		deleteDatamodel2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model2.setRowCount(0);
			}
		});
		
		table.setListener(new ListSelectionListener() {
            
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (!e.getValueIsAdjusting() && row != -1)
					System.out.println(table.getSelectedRow());
			}
        });
		
		JPanel sec3 = new JPanel();
		sec3.add(new JButton("Button 1"));
		
		FormSections form = new FormSections();
		form.setFormTitle("Swing Template");
		form.getTitleLabel().setFont(new Font("Times New Roman", Font.PLAIN, 20));
		form.addSection(sec3);
		form.setSize(600, 400);
		form.getTablePanel().setLayout(new java.awt.BorderLayout());
		form.getTablePanel().add(table);
		form.getSection1Panel().add(button1, "North");
		form.getSection1Panel().add(button2, "South");
		form.getSection1Panel().add(deleteDatamodel2, "East");
		form.setVisible(true);
	}

}
