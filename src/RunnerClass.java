import javax.swing.JOptionPane;


public class RunnerClass{

	public static void main(String[] args)
	{
	FrameClass fc = new FrameClass();
	try{
	JOptionPane.showMessageDialog(null,"Game of Life Developed by: Zoeb Chhatriwala");
	int Delay = Integer.parseInt(JOptionPane.showInputDialog("Enter Delay Time (ms): ",null));
	fc.setData(Integer.parseInt(JOptionPane.showInputDialog("Enter Size of Window (100-1000): ",null)),JOptionPane.showInputDialog("Enter Your Planet Name: ",null));
	while(true)
	{
		fc.run();
		Thread.sleep(Delay);
	}
	}catch(Exception ex){
		JOptionPane.showMessageDialog(null, "Opps!!! Something Went Wrong");
		fc.dispose();
	}
	}
}