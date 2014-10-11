import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameClass extends JFrame{
    private static final long serialVersionUID = 1L;
    private Random rand = new Random(System.nanoTime()+System.nanoTime());
    private int area=0,pause=0;
    private Boolean Gender;
    private long Living=0,Total=0,Female=0,Male=0;
    int Positionx[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,-2,-3,-4,-5,-6,-7,-8,-9,-10,1,2,3,4,5,6,7,8,9,10,-1,-2,-3,-4,-5,1,2,3,4,5,-1,-2,-3,-4,-5,1,2,3,4,5};
	int Positiony[]={0,-1,-2,-3,-4,-5,-6,-7,-8,-9,-10,1,2,3,4,5,6,7,8,9,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,-2,-3,-4,-5,-1,-2,-3,-4,-5,1,2,3,4,5,1,2,3,4,5};
    private int[][] color;
    private JFrame record =new JFrame();
    private JLabel rl = new JLabel();
    private JLabel rl2 = new JLabel();
    private JButton pb = new JButton();
    public FrameClass()
	{
		super("Game of Life");
		setBackground(Color.WHITE);
		record.setSize(600,100);
		record.setResizable(false);
		record.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		record.add(rl,BorderLayout.CENTER);
		rl2.setText(" RED = Male, BLUE = Female, WHITE = Vacancy // Use Drag+RMB = Generate Lives & Drag+LMB = Vacant ");
		record.add(rl2,BorderLayout.SOUTH);
		pb.setText("Pause|Play");
		pb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(pause==0)
				{
					pause=1;
					rl.setText(String.format("[Paused] Population = %s Contains Males = %s, Females = %s & Vacancy = %s",Living,Male,Female,(Total-Living)));
				}
				else
				{
					rl.setText("");
					pause=0;
				}
			}
		});
		record.add(pb,BorderLayout.NORTH);
		setResizable(false);
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent arg0){}
			public void mouseDragged(MouseEvent arg0) {
				int x = arg0.getX(),y=arg0.getY();
				int newx=0,newy=0;
				if(arg0.isMetaDown()==true)
				{
				for(int i=0;i<Positionx.length;i++)
					{
						newx=x+Positionx[i];
						newy=y+Positiony[i];
						if(color[newx][newy]>0)
						{
						if(color[newx][newy]==2)
						{
							Female--;
						}
						else{Male--;}
						color[newx][newy] = 0;
						Living--;
						showTitle();
						}	
					}
				}
				else
				{
				for(int i=0;i<Positionx.length;i++)
					{
						newx=x+Positionx[i];
						newy=y+Positiony[i];
						if(color[newx][newy]==0)
						{
						    Gender = rand.nextBoolean();
							if(Gender.booleanValue()==false)
							{
								color[newx][newy]=2;
								Female++;
							}
							else
							{
								color[newx][newy]=3;
								Male++;
							}
						Living++;
						showTitle();
						}
					}
					
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void paint(Graphics G)
	{
		for(int i=0;i<area;i++)
		{
			for(int j=0;j<area;j++)
			{
				if(color[i][j]==0)
				{
					G.setColor(Color.WHITE);
					G.drawLine(i,j,i,j);
				}
				else if(color[i][j]>=1)
				{
				if(color[i][j]==2)
				{
					   //Female
					   G.setColor(Color.BLUE);
					   G.drawLine(i,j,i,j);
				}
				else
				{
					   //Male
					   G.setColor(Color.RED);
					   G.drawLine(i,j,i,j);
					   
				}
				}
			}
		}
		
	}
	
	public void run()
	{
		try{
		   int x=0,y=0;
		    //Die
		   if(Female>=1 && Male>=1 && pause==0)
		   {
	       if(rand.nextInt(100)==rand.nextInt(100))
	       {
	    	    x=rand.nextInt(area);
	    	    y=rand.nextInt(area);
				if(color[x][y]>=1)
				{
					if(color[x][y]==2)
					{
						Female--;
					}
					else{Male--;}
				color[x][y] = 0;
				Living--;
				showTitle();
				}
			}
		    //Mass Born|Die
			else if(rand.nextBoolean()==true && rand.nextInt(1000)==786)
			{
				Boolean ch = false;
				if(rand.nextBoolean()==true)
				{
					ch=true;
				}
				int end=rand.nextInt(area+area),i=0;
				while(i!=end)
				{
				x=rand.nextInt(area);
			    y=rand.nextInt(area);
				if(ch.booleanValue()==true)
				{
					if (color[x][y]>=1)
					{
						if(color[x][y]==2)
						{
							Female--;
						}
						else{Male--;}
			    	color[x][y]=0;
					Living--;
					showTitle();
					i++;
					}
				}
				else
				{
					if (color[x][y]==0)
					{
					Gender = rand.nextBoolean();
					if(Gender.booleanValue()==false)
					{
						color[x][y]=2;
						Female++;
					}
					else
					{
						color[x][y]=3;
						Male++;
					}
					Living++;
					showTitle();
					i++;
					}
				}
				}
				}
			//Massacre or Natural Disaster
			else if(rand.nextInt(1000)==666 && rand.nextInt(1000)==666 && rand.nextInt(1000)==666)
			{
				int end=rand.nextInt((area*area)/2),i=0;
				while(i!=end)
				{
				x=rand.nextInt(area);
			    y=rand.nextInt(area);
				if (color[x][y]>=1)
				{
					if(color[x][y]==2)
					{
						Female--;
					}
					else{Male--;}
				color[x][y] = 0;
				Living--;
				showTitle();
				i++;
				}
				}
			}
		    //Born
			else if(rand.nextInt(100)==rand.nextInt(100))
			{
				x=rand.nextInt(area);
		    	y=rand.nextInt(area);
				if(color[x][y]==0)
				{
					Gender = rand.nextBoolean();
					if(Gender.booleanValue()==false)
					{
						color[x][y]=2;
						Female++;
					}
					else
					{
						color[x][y]=3;
						Male++;
					}
				Living++;
				showTitle();
				}
			}
		    }
			}catch(Exception Ex){Ex.printStackTrace();}
	}
	
	public void setData(int area,String Name)
	{
		setTitle(String.format("Game of Life: Planet - %s",Name));
		record.setTitle(String.format("Game of Life Records: Planet - %s",Name));
		this.area = area;
		setSize(area,area);
		Total=area*area;
		color = new int[this.area][this.area];
		setVisible(true);
		record.setVisible(true);
		getColor();
	}
	
	private void getColor()
	{
		for(int i=0;i<area;i++)
		{
			for(int j=0;j<area;j++)
			{
				color[i][j]=0;
			}
		}
	}
	
	private void showTitle()
	{
	    rl.setText(String.format(" Population = %s Contains Males = %s, Females = %s & Vacancy = %s",Living,Male,Female,(Total-Living)));
		repaint();
	}
    
}
