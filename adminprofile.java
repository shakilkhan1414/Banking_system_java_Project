import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class adminprofile extends JFrame implements ActionListener{
	
	private static addmoney am;
	private static showinfo sai;
	private Frame parent;
	

	public adminprofile()
	{
		super("Admin Profile");
		
		am=new addmoney();
		sai=new showinfo();
	
		Font f=new Font("TimesRoman",Font.BOLD,20);
		Font f1=new Font("TimesRoman",Font.PLAIN,40);
		JPanel heading;
		heading=new JPanel();
		heading.setLayout(null);
		heading.setBackground(new Color(0,0,0,180));
		heading.setBounds(0,0,900,100);
		JLabel name=new JLabel("Administrator");
		name.setForeground(Color.WHITE);
		name.setBounds(320,25,400,50);
		name.setFont(f1);
		heading.add(name);
		
		JPanel head;
		head=new JPanel();
		head.setLayout(null);
		head.setBackground(new Color(0,0,0,150));
		head.setBounds(250,165,400,350);
		
		JButton showinfo=new JButton("Show all Info.");
		showinfo.setFont(f);
		showinfo.setBounds(100,50,200,30);
		showinfo.setBackground(new Color(255,255,255));
		head.add(showinfo);
		JButton addmoney=new JButton("Add Money");
		addmoney.setFont(f);
		addmoney.setBounds(100,100,200,30);
		addmoney.setBackground(new Color(255,255,255));
		head.add(addmoney);
	
		JButton logout2=new JButton("Log out");
		logout2.setFont(f);
		logout2.setBounds(100,150,200,30);
		logout2.setBackground(new Color(239,15,15));
		head.add(logout2);
		
		setSize(900,600);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(300,80);
		ImageIcon background=new ImageIcon("background4.jpg");
		Image img=background.getImage();
		Image temp=img.getScaledInstance(900,600,Image.SCALE_SMOOTH);
		background=new ImageIcon(temp);
		JLabel backg=new JLabel("",background,JLabel.CENTER);
		backg.add(head);
		backg.add(heading);
		backg.setBounds(0,0,900,600);
		add(backg);
		showinfo.addActionListener(this);
		addmoney.addActionListener(this);
		logout2.addActionListener(this);
		
	}
	public void setParent(Frame p){
	parent=p;}
	
	public void actionPerformed(ActionEvent e)
	{
		String si=e.getActionCommand();
		if(si.equals("Show all Info."))
		    {
			this.setVisible(false);
			sai.setVisible(true);
			sai.setParent(this);
			sai.loadinfo();
			
		    }
		else if(si.equals("Add Money"))
			{
				this.setVisible(false);
				am.setVisible(true);
				am.setParent(this);
				
			}
		
		else if(si.equals("Log out"))
			{
				parent.setVisible(true);
				this.setVisible(false);
				
			}
			
	}

}
