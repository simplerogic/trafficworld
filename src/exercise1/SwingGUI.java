package exercise1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class SwingGUI implements ActionListener{
	private String wSimTime,wCarSpawnRate,wRedCar,wYellowCar,wBlueCar,wPurpleCar;
	
    private JFrame f = new JFrame("TEST");
	private JTextField simTime = new JTextField(10);
	private JTextField carSpawnRate = new JTextField(10);
	private JTextField redCar = new JTextField(10);
	private JTextField yellowCar = new JTextField(10);
	private JTextField blueCar = new JTextField(10);
	private JTextField purpleCar = new JTextField(10);
	private JButton write = new JButton("Write to File");
	public SwingGUI() {
		f.setLayout(new BorderLayout());
		JPanel inputspanel = new JPanel();
		inputspanel.setLayout(new FlowLayout());
		inputspanel.add(new JLabel("Sim Time"));
		inputspanel.add(simTime);
		inputspanel.add(new JLabel("Car Spawn Rate"));
		inputspanel.add(carSpawnRate);
		inputspanel.add(new JLabel("Red Percentage"));
		inputspanel.add(redCar);
		inputspanel.add(new JLabel("Yellow Percentage"));
		inputspanel.add(yellowCar);
		inputspanel.add(new JLabel("Blue Percentage"));
		inputspanel.add(blueCar);
		inputspanel.add(new JLabel("Purple Percentage"));
		inputspanel.add(purpleCar);
		f.add(inputspanel);
		JPanel writeButton = new JPanel();
		writeButton.setLayout(new FlowLayout());
		writeButton.add(write);
		write.addActionListener(this);
		f.add(writeButton, BorderLayout.SOUTH);
		
		f.setVisible(true);
		f.setSize(700, 500);

	}
	
	public static void main(String[] args){
		new SwingGUI();
	}
	@Override
	public void actionPerformed(ActionEvent eVENT) {
		if (eVENT.getSource() == write){
			wSimTime = simTime.getText().trim();
			wCarSpawnRate = carSpawnRate.getText().trim();
			wRedCar = redCar.getText().trim();
			wYellowCar = yellowCar.getText().trim();
			wBlueCar = blueCar.getText().trim();
			wPurpleCar = purpleCar.getText().trim();
			writeFile("simproperties.txt", wSimTime, wCarSpawnRate, wRedCar, wYellowCar, wBlueCar, wPurpleCar);
			
		}
	}
	public static void writeFile(String filename, String wSimTime2, String wCarSpawnRate2, String wRedCar2, String wYellowCar2, String wBlueCar2, String wPurpleCar2){
		try {
			FileWriter writer = new FileWriter(filename);
			BufferedWriter bWriter = new BufferedWriter(writer);
			bWriter.write("SimTime      =" + wSimTime2);
			bWriter.newLine();
			bWriter.write("CarSpawnRate =" + wCarSpawnRate2);
			bWriter.newLine();
			bWriter.write("RedSpawn%    =" + wRedCar2);
			bWriter.newLine();
			bWriter.write("YellowSpawn% =" + wYellowCar2);
			bWriter.newLine();
			bWriter.write("BlueSpawn%   =" + wBlueCar2);			
			bWriter.newLine();
			bWriter.write("PurpleSpawn% =" + wPurpleCar2);

			bWriter.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
