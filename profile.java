import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class profile extends JFrame implements ActionListener{
	
	private static checkbalance c;
	private static sendmoney e9;
	private static payment pa;
	private Frame parent;
	String loaduname;
	private String uname5="";
	private JLabel name78;
	

	public profile()
	{
		super("Profile");
		
		c=new checkbalance();
		e9=new sendmoney();
		pa=new payment();
		Font f=new Font("TimesRoman",Font.BOLD,20);
		Font f1=new Font("TimesRoman",Font.PLAIN,40);
		JPanel heading;
		heading=new JPanel();
		heading.setLayout(null);
		heading.setBackground(new Color(0,0,0,180));
		heading.setBounds(0,0,900,100);
		JLabel name=new JLabel("Welcome");
		name.setForeground(Color.WHITE);
		name.setBounds(260,25,400,50);
		name.setFont(f1);
		heading.add(name);
		
		name78=new JLabel("Shakil Khan");
		name78.setForeground(Color.WHITE);
		name78.setBounds(420,25,400,50);
		name78.setFont(f1);
		heading.add(name78);
		
		JPanel head;
		head=new JPanel();
		head.setLayout(null);
		head.setBackground(new Color(0,0,0,150));
		head.setBounds(250,165,400,350);
		
		JButton checkbalance1=new JButton("Check Balance");
		checkbalance1.setFont(f);
		checkbalance1.setBounds(100,50,200,30);
		checkbalance1.setBackground(new Color(255,255,255));
		head.add(checkbalance1);
		JButton sendmoney1=new JButton("Send Money");
		sendmoney1.setFont(f);
		sendmoney1.setBounds(100,100,200,30);
		sendmoney1.setBackground(new Color(255,255,255));
		head.add(sendmoney1);
		JButton payment1=new JButton("Payment");
		payment1.setFont(f);
		payment1.setBounds(100,150,200,30);
		payment1.setBackground(new Color(255,255,255));
		head.add(payment1);
		JButton logout1=new JButton("Log out");
		logout1.setFont(f);
		logout1.setBounds(100,200,200,30);
		logout1.setBackground(new Color(239,15,15));
		head.add(logout1);
		
		setSize(900,600);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(300,80);
		ImageIcon background=new ImageIcon("background2.jpg");
		Image img=background.getImage();
		Image temp=img.getScaledInstance(900,600,Image.SCALE_SMOOTH);
		background=new ImageIcon(temp);
		JLabel backg=new JLabel("",background,JLabel.CENTER);
		backg.add(head);
		backg.add(heading);
		backg.setBounds(0,0,900,600);
		add(backg);
		checkbalance1.addActionListener(this);
		sendmoney1.addActionListener(this);
		payment1.addActionListener(this);
		logout1.addActionListener(this);
		
	}
	public void setParent(Frame p){
	parent=p;}
	
	public void loadDataa(){
		String sql="select u_name from user where u_username='"+loaduname+"'";
		
		try{
			dataaccess da=new dataaccess();
			ResultSet rs=da.getData(sql);
			
			while(rs.next())
			{
			uname5=uname5+rs.getString("u_name");
			}
			name78.setText(uname5);
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}
	
	public void actionPerformed(ActionEvent e2)
	{
		String si=e2.getActionCommand();
		if(si.equals("Check Balance"))
		    {
			this.setVisible(false);
			c.setVisible(true);
			c.setParent(this);
			c.loaduname1=loaduname;
			c.loadData();
			
		    }
		else if(si.equals("Send Money"))
			{
				this.setVisible(false);
				e9.setVisible(true);
				e9.setParent(this);
				e9.loaduname2=loaduname;
				
			}
		else if(si.equals("Payment"))
			{
				this.setVisible(false);
				pa.setVisible(true);
				pa.setParent(this);
				pa.loaduname3=loaduname;
				
			}
		else if(si.equals("Log out"))
			{
				parent.setVisible(true);
				this.setVisible(false);
				name78.setText(null);
				
			}
			
	}

}
