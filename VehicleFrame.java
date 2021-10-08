import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

/**
   Frame which contains panels to build a vehicle with desired specifications.
*/
public class VehicleFrame extends JFrame
{
  
   private JLabel label;
   private JCheckBox acCheckBox;
   private JCheckBox gpsCheckBox;
   private JCheckBox leatherCheckBox;
   private JRadioButton carButton;
   private JRadioButton suvButton;
   private JRadioButton truckButton;
   private JComboBox yearCombo;
   private ActionListener listener;
   private JLabel mileageLabel;
   private JLabel resultsLabel;
   private JTextField mileageField;
   private JTextArea results;
   private JButton button;

   /**
      Creates frame.
   */
   public VehicleFrame()
   {  
      results = new JTextArea(10, 30);
      results.setEditable(false);
      
      createControlPanel();
      createTextField();
      createPanel();
      createButton();
     
      // Creates menu      
      JMenuBar menu = new JMenuBar();     
      setJMenuBar(menu);
      menu.add(createFileMenu());
      setSize(500, 470);
   }

   /**
      Builds control panel to select vehicle specifications.
   */
   public void createControlPanel()
   {
      JPanel yearPanel = createComboBox();
      JPanel featuresPanel = createCheckBoxes();
      JPanel vehicleGroupPanel = createRadioButtons();

      // Format components
      JPanel controlPanel = new JPanel();
      controlPanel.setLayout(new GridLayout(3, 1));
      controlPanel.add(vehicleGroupPanel);
      controlPanel.add(featuresPanel);
      controlPanel.add(yearPanel);
            
      // Join each panel to pane

      add(controlPanel, BorderLayout.NORTH);
   }

   /**
      Creates radio buttons to select the vehicle type.
      @return panel that holds the radio buttons
   */
   public JPanel createRadioButtons()
   {
      carButton = new JRadioButton("Car");
      carButton.addActionListener(listener);

      suvButton = new JRadioButton("SUV");
      suvButton.addActionListener(listener);

      truckButton = new JRadioButton("Truck");
      truckButton.addActionListener(listener);
      truckButton.setSelected(true);

      // Put radio buttons in a button group

      ButtonGroup group = new ButtonGroup();
      group.add(carButton);
      group.add(suvButton);
      group.add(truckButton);

      JPanel panel = new JPanel();
      panel.add(carButton);
      panel.add(suvButton);
      panel.add(truckButton);
      panel.setBorder(new TitledBorder(new EtchedBorder(), "Vehicle Type"));

      return panel;
   }

   /**
      Creates combo box with the choices of years.
      @return panel that holds the combo box
   */
   public JPanel createComboBox()
   {
      yearCombo = new JComboBox();
      yearCombo.addItem("2000-2005");
      yearCombo.addItem("2006-2010");
      yearCombo.addItem("2011-2015");
      yearCombo.addItem("2016-2020");
      yearCombo.setEditable(false);
      yearCombo.addActionListener(listener);

      JPanel panel = new JPanel();
      panel.add(yearCombo);
      panel.setBorder(new TitledBorder(new EtchedBorder(), "Vehicle Year"));
      return panel;
   }
   
   /**
      Creates text field for mileage input.
   */
   private void createTextField()
   {
      mileageLabel = new JLabel("Mileage: ");
      mileageField = new JTextField(12);
      mileageField.setText("");
   }
   
   /**
      Creates check boxes for selecting vehicle features.
      @return panel that holds the check boxes
   */
   public JPanel createCheckBoxes()
   {
      acCheckBox = new JCheckBox("A/C");
      acCheckBox.addActionListener(listener);

      gpsCheckBox = new JCheckBox("GPS");
      gpsCheckBox.addActionListener(listener);
      
      leatherCheckBox = new JCheckBox("Leather");
      leatherCheckBox.addActionListener(listener);
      
      //put checkboxes in panel
      JPanel panel = new JPanel();
      panel.add(acCheckBox);
      panel.add(gpsCheckBox);
      panel.add(leatherCheckBox);
      panel.setBorder(new TitledBorder(new EtchedBorder(), "Vehicle Features"));

      return panel;
   }
   
   /**
      Creates results text area which displays inputted data.
   */
   class ResultsListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         //turns selected radio button into String
         String type = " ";
         if (carButton.isSelected()){ 
         type = "Car";
         }
         else if (suvButton.isSelected()){
         type = "SUV";
         }
         else if (truckButton.isSelected()){
         type = "Truck"; 
         }
         //turns selected checkboxes into String
         String feature = " ";
         int selectFeature = 0;
         int ac = 1;
         int gps = 2;
         int leather = 4;
         if (acCheckBox.isSelected()){
         selectFeature = selectFeature+ac;
         }
         if (gpsCheckBox.isSelected()){
         selectFeature = selectFeature+gps;
         }
         if (leatherCheckBox.isSelected()){
         selectFeature = selectFeature+leather;
         }
         if (selectFeature == 1){
         feature = "A/C";
         }
         if (selectFeature == 2){
         feature = "GPS";
         }
         if (selectFeature == 4){
         feature = "Leather";
         }
         if (selectFeature == 3){
         feature = "A/C, GPS";
         }
         if (selectFeature == 5){
         feature = "A/C, Leather";
         }
         if (selectFeature == 6){
         feature = "GPS, Leather";
         }
         if (selectFeature == 7){
         feature = "A/C, GPS, Leather";
         }
         //turns selected year into String
         String year = (String) yearCombo.getSelectedItem();
         //turns selected mileage into String
         String mileage = mileageField.getText();
         //prints results
         results.append("\nVehicle Type: " + type + "\nVehicle Features: " + feature + "\nVehicle Year: " + year + "\nVehicle Mileage: " + mileage + " kms" + "\n---------------------------------------------------------------------");
         results.setBorder(new TitledBorder(new EtchedBorder(), "Results"));//creates label inside text area when "Done" button clicked
      }
   }
   
   /**
      Creates button which triggers results text area.
   */
   private void createButton()
   {
      JButton button = new JButton("Done");      
      ActionListener listener = new ResultsListener();
      button.addActionListener(listener);
      add(button, BorderLayout.SOUTH);
   }
   
   /**
      Builds panel to input mileage and view results.
   */
   private void createPanel()                                                                
   {
      JPanel panel = new JPanel();
      panel.add(mileageLabel);
      panel.add(mileageField);
      JScrollPane scrollPane = new JScrollPane(results);
      panel.add(scrollPane);
      add(panel, BorderLayout.CENTER);
   }
   
   /**
      Builds File menu.
      @return menu
   */
   public JMenu createFileMenu()
   {
      JMenu menuOne = new JMenu("File");
      JMenuItem exit = new JMenuItem("Exit");      
      ActionListener listener = new ExitListener();
      exit.addActionListener(listener);
      menuOne.add(exit);
      return menuOne;
   }
   
   class ExitListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         System.exit(0);
      }
   }
}