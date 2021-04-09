package cgpa_calculation;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CGPA_calculation extends JFrame implements ActionListener {

    String[] tableTitle = {"Course Code", "Course Title", "Credit", "GPA"};
    String[] tableVal = new String[4];
    Container container;
    Font font1,font2;
    JLabel headLavel,CcodeL,CtitleL,creditL,gpaL;
    JTextField CcodeTF,CtitleTF,creditTF,gpaTF;
    JButton addB,updateB,deleteB,clearB,calculateB;
    JTable table;
    JScrollPane scrollP;
    DefaultTableModel tableModel;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    
    CGPA_calculation() {
        container = getContentPane();
        container.setBackground(Color.YELLOW);
        container.setLayout(null);

        font1 = new Font("Arial", Font.BOLD + Font.ITALIC, 20);
        font2 = new Font("Arial", Font.BOLD, 14);

        JLabel headLabel = new JLabel("         INFORMATION");
        headLabel.setBounds(250, 20, 300, 50);
        headLabel.setFont(font1);
        container.add(headLabel);

        CcodeL = new JLabel("Course code ");
        CcodeL.setBounds(50, 80, 150, 30);
        CcodeL.setFont(font2);
        container.add(CcodeL);

        CtitleL = new JLabel("Course Title ");
        CtitleL.setBounds(50, 130, 150, 30);
        CtitleL.setFont(font2);
        container.add(CtitleL);

        creditL = new JLabel("Credit     ");
        creditL.setBounds(50, 180, 150, 30);
        creditL.setFont(font2);
        container.add(creditL);

        gpaL = new JLabel("GPA    ");
        gpaL.setBounds(50, 230, 150, 30);
        gpaL.setFont(font2);
        container.add(gpaL);

        CcodeTF = new JTextField();
        CcodeTF.setBounds(150, 80, 250, 30);
        CcodeTF.setFont(font2);
        container.add(CcodeTF);

        CtitleTF = new JTextField();
        CtitleTF.setBounds(150, 130, 250, 30);
        CtitleTF.setFont(font2);
        container.add(CtitleTF);

        creditTF = new JTextField();
        creditTF.setBounds(150, 180, 250, 30);
        creditTF.setFont(font2);
        container.add(creditTF);

        gpaTF = new JTextField();
        gpaTF.setBounds(150, 230, 250, 30);
        gpaTF.setFont(font2);
        container.add(gpaTF);

        addB = new JButton("Add");
        addB.setBounds(450, 80, 100, 30);
        addB.setFont(font2);
        container.add(addB);

        updateB = new JButton("Update");
        updateB.setBounds(450, 130, 100, 30);
        updateB.setFont(font2);
        container.add(updateB);

        deleteB = new JButton("Delete");
        deleteB.setBounds(450, 180, 100, 30);
        deleteB.setFont(font2);
        container.add(deleteB);

        clearB = new JButton("Clear");
        clearB.setBounds(450, 230, 100, 30);
        clearB.setFont(font2);
        container.add(clearB);

        calculateB = new JButton("Calculate CGPA");
        calculateB.setBounds(300, 300, 150, 50);
        calculateB.setFont(font2);
        container.add(calculateB);

        table = new JTable();

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(tableTitle);

        table.setModel(tableModel);
        table.setFont(font2);
        table.setSelectionBackground(Color.GREEN);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);

//        ta.setEnabled(false);
        scrollP = new JScrollPane(table);
        scrollP.setBounds(10, 360, 740, 265);
        container.add(scrollP);

        addB.addActionListener(this);
        updateB.addActionListener(this);
        deleteB.addActionListener(this);
        clearB.addActionListener(this);
        calculateB.addActionListener(this);

        table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent m) {
                int rowNumber = table.getSelectedRow();

                String Ccode = tableModel.getValueAt(rowNumber, 0).toString();
                String Ctitle = tableModel.getValueAt(rowNumber, 1).toString();
                String credit = tableModel.getValueAt(rowNumber, 2).toString();
                String gpa = tableModel.getValueAt(rowNumber, 3).toString();

                CcodeTF.setText(Ccode);
                CtitleTF.setText(Ctitle);
                creditTF.setText(credit);
                gpaTF.setText(gpa);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addB) {
            
            tableVal[0] = CcodeTF.getText();
            tableVal[1] = CtitleTF.getText();
            tableVal[2] = creditTF.getText();
            tableVal[3] = gpaTF.getText();
            tableModel.addRow(tableVal);

        } else if (ae.getSource() == clearB) {
            
            CcodeTF.setText("");
            CtitleTF.setText("");
            creditTF.setText("");
            gpaTF.setText("");
            
        } else if (ae.getSource() == deleteB) {
            
            int rowNumber = table.getSelectedRow();
            if (rowNumber >= 0) {
               tableModel.removeRow(rowNumber);
            } else {
                JOptionPane.showMessageDialog(null, "No row has selected");
            }
            
        } else if (ae.getSource() == updateB) {
            
            int rowNumber = table.getSelectedRow();
            String Ccode = CcodeTF.getText();
            String Ctitle = CtitleTF.getText();
            String credit = creditTF.getText();
            String gpa = gpaTF.getText();
            tableModel.setValueAt(Ccode, rowNumber, 0);
            tableModel.setValueAt(Ctitle, rowNumber, 1);
            tableModel.setValueAt(credit, rowNumber, 2);
            tableModel.setValueAt(gpa, rowNumber, 3);

        } else if (ae.getSource() == calculateB) {
            
            CGPA();
            
        }
    }
    
    public void CGPA(){
        
        double totalCredit = 0, credit,gpa ,total=0;
        int  row = 0;
            String credit2,gpa2;
            int totalRow = tableModel.getRowCount();
                   
            for(row=0; row<totalRow; row++) {
                
                credit2 = tableModel.getValueAt(row, 2).toString();
                credit = Double.parseDouble(credit2);
                
                gpa2 = tableModel.getValueAt(row, 3).toString();
                gpa = Double.parseDouble(gpa2);
                
                totalCredit = totalCredit + credit;
                total += credit * gpa;
            }
            double cgpa = total/totalCredit;
            JOptionPane.showMessageDialog(null, "YOUR CGPA is : "+df2.format(cgpa));
            
    }

    public static void main(String[] args) {
        CGPA_calculation f = new CGPA_calculation();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(100, 0, 780, 690);
        f.setTitle("CGPA Counter App");
    }

}