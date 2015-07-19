package in.ganeshsharma.AgeCalculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main {
	
	JFrame frame;
	JLabel welcome, name, DOB, asOnDate;
	JTextField txtName;
	JButton btnCalculator, btnExit, btnReset;
	private DateButton dobButton;
	private DateButton asOnButton;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	String warningMessage;
	int asOnDay,asOnMonth,asOnYear,dobDay,dobMonth,dobYear,newDay,newMonth,newYear;
	public Main(){
		frame = new JFrame("Age Calculator Program");
		frame.setSize(d.width/3, d.height/3);
		//frame.setSize(d.width, d.height);
		frame.setLocation(d.width/5, d.height/5);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcome = new JLabel("Age Calculator Software");
		name = new JLabel("Name : ");
		DOB = new JLabel("DOB : ");
		asOnDate = new JLabel("As on Date : ");
		txtName = new JTextField(200);
		btnCalculator = new JButton("Calculate");
		btnCalculator.setForeground(Color.BLUE);
		//btnReset = new JButton("Reset");
		btnExit = new JButton("Exit");
		btnExit.setForeground(Color.RED);
		dobButton = new DateButton();
		dobButton.setForeground(Color.blue);
		asOnButton = new DateButton();
        asOnButton.setForeground(Color.blue);
        welcome.setBounds(40, 40, 300, 30);
        name.setBounds(40, 80, 150, 30);
        txtName.setBounds(200, 80, 250, 30);
        DOB.setBounds(40, 120, 150, 30);
        dobButton.setBounds(200, 120, 110, 30);
        asOnDate.setBounds(40, 160, 150, 30);
        asOnButton.setBounds(200, 160, 110, 30);
        //btnCalculator.setBounds(80, 220, 100, 30);
        //btnReset.setBounds(190, 220, 100, 30);
        //btnExit.setBounds(300, 220, 100, 30);
        btnCalculator.setBounds(80, 220, 100, 30);
        btnExit.setBounds(210, 220, 100, 30);
        btnCalculator.addActionListener(new CalculatorClass());
        //btnReset.addActionListener(new ResetClass());
        btnExit.addActionListener(new ExitClass());
        frame.add(welcome);
        frame.add(name);
        frame.add(txtName);
        frame.add(DOB);
        frame.add(dobButton);
        frame.add(asOnDate);
        frame.add(asOnButton);
        frame.add(btnCalculator);
        //frame.add(btnReset);	
        frame.add(btnExit);
        frame.setVisible(true);    
	}
	public boolean isCorrect(){
		if(txtName.getText().trim().equals("")){
			warningMessage = "Please enter your name first !";
			txtName.setFocusable(true);
			txtName.setText("");
			return false;
		}
		if(asOnButton.getDate().before(dobButton.getDate())){
			warningMessage = "Please enter a valid As On Date or DOB !";
			asOnButton.setFocusable(true);
			return false;
		}
		return true;
	}
	/*class ResetClass implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			txtName.setText("");	
		}
	}*/
	class ExitClass implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			System.exit(0);
		}
	}
	class CalculatorClass implements ActionListener{
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent ae){
			asOnDay = asOnButton.getDate().getDate();
			asOnMonth = asOnButton.getDate().getMonth()+1;
			asOnYear = asOnButton.getDate().getYear()+1900;
			dobDay = dobButton.getDate().getDate();
			dobMonth = dobButton.getDate().getMonth()+1;
			dobYear = dobButton.getDate().getYear()+1900;
			
			boolean status = isCorrect();
			if(status){
								
				if(asOnDay>=dobDay)
					newDay=asOnDay-dobDay;
				else
				{
					newDay=(asOnDay+30)-dobDay;
					asOnMonth--;
				}
				if(asOnMonth>=dobMonth)
					newMonth=asOnMonth-dobMonth;
				else
				{
					newMonth=asOnMonth+12-dobMonth;
					asOnYear--;
				}
				newYear=asOnYear-dobYear;
				String asOnDateOutput = (asOnButton.getDate().getYear()+1900)+"-"+(asOnButton.getDate().getMonth()+1)+"-"+asOnButton.getDate().getDate();
				String output =txtName.getText().trim().toUpperCase()+", your age as on "+asOnDateOutput+" is "+newYear+" Years "+newMonth+" Months and "+newDay+" Days";
				JOptionPane.showMessageDialog(null,output,"YOUR AGE !",JOptionPane.INFORMATION_MESSAGE);
				
			}else{
				JOptionPane.showMessageDialog(null,warningMessage,"Warning",JOptionPane.WARNING_MESSAGE);
				//txtName.setText("");
			}
		}
	}
	
	public static void main(String args[]){
		new Main();
	}
}
