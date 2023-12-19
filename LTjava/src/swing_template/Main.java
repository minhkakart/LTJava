package swing_template;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
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
		
		JFrame frame = new JFrame("Swing Template");
		frame.setLayout(new java.awt.FlowLayout());
		frame.add(table);
		frame.add(button1);
		frame.add(button2);
		frame.add(deleteDatamodel2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 480);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
