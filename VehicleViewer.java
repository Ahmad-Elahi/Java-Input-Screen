import javax.swing.JFrame;

/**
   This program allows the user to view font effects.
*/
public class VehicleViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new VehicleFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Vehicle Builder");
      frame.setVisible(true);      
   }
}
