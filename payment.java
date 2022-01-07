import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class payment extends JFrame implements ActionListener
{
	private Frame parent;
	String loaduname3;
	private int paymentbal=0;
	private int paymentbal1=0;
	private String string5="";
	private int paymentbalnew=0;
	private int paymentold1=0;
	private int newpaymentbal=0;
	private JTextField nam1;
	private JTextField username;
	
	public payment()
	{
		super("Payment");
		
		Font f=new Font("TimesRoman",Font.PLAIN,40);
		Font f1=new Font("TimesRoman",Font.BOLD,20);
		Font f2=new Font("TimesRoman",Font.PLAIN,15);
		JPanel heading;
		heading=new JPanel();
		heading.setLayout(null);
		heading.setBackground(new Color(0,0,0,180));
		heading.setBounds(0,0,900,100);
		JLabel name=new JLabel("Payment");
		name.setForeground(Color.WHITE);
		name.setBounds(360,25,400,50);
		name.setFont(f);
		heading.add(name);
		
		JPanel head;
		head=new JPanel();
		head.setLayout(null);
		head.setBackground(new Color(0,0,0,200));
		head.setBounds(250,165,400,350);
		
		JLabel nam=new JLabel("User name:");
		nam.setForeground(Color.WHITE);
		nam.setBounds(15,20,400,50);
		nam.setFont(f1);
		head.add(nam);
		JLabel uname=new JLabel("Amount:");
		uname.setForeground(Color.WHITE);
		uname.setBounds(15,60,400,50);
		uname.setFont(f1);
		head.add(uname);
		
		
		
		
		nam1=new JTextField("");
		nam1.setBounds(130,30,200,30);
		nam1.setFont(f2);
		nam1.setBackground(new Color(0,206,209,150));
		head.add(nam1);
		username=new JTextField("");
		username.setBounds(130,70,200,30);
		username.setFont(f2);
		username.setBackground(new Color(0,206,209,150));
		head.add(username);
		
		JButton makepayment=new JButton("Make Payment");
		makepayment.setFont(f1);
		makepayment.setBounds(100,140,200,30);
		makepayment.setBackground(new Color(255,255,255));
		head.add(makepayment);
		JButton Back=new JButton("Back");
		Back.setFont(f1);
		Back.setBounds(100,190,200,30);
		Back.setBackground(new Color(29,201,2));
		head.add(Back);
		
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
		makepayment.addActionListener(this);
		Back.addActionListener(this);
		
	}
	
	public void setParent(Frame p){
		parent=p;
	}
	
	private boolean isEmpty(JTextField s){
		boolean flag=false;
		if(s.getText().length()==0)flag=true;
		return flag;
	}
	
	public void loadbalance(){
		String sql="select u_balance from user where u_username='"+loaduname3+"'";
		try{
			dataaccess da=new dataaccess();
			ResultSet rs=da.getData(sql);
			
			while(rs.next()){
				paymentbal=paymentbal+rs.getInt("u_balance");
			}
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}
	
	public void loadbalance2(){
		String sql3="select u_balance from user where u_username='"+nam1.getText()+"'";
		try{
			dataaccess df=new dataaccess();
			ResultSet rs1=df.getData(sql3);
			
			while(rs1.next()){
				paymentold1=paymentold1+rs1.getInt("u_balance");
			}
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}
	
	
	
	public void actionPerformed(ActionEvent e6)
	{
		String sig=e6.getActionCommand();
		if(sig.equals("Make Payment"))
		{
			loadbalance();
		   loadbalance2();
		   
			string5=string5+username.getText();
			paymentbal1=Integer.parseInt(string5);
			paymentbalnew=paymentbal-paymentbal1;
			
			
			newpaymentbal=paymentold1+paymentbal1;
			
			String msg="";
			String sql2="update user set u_balance='"+paymentbalnew+"' where u_username='"+loaduname3+"'";
			String sql4="update user set u_balance='"+newpaymentbal+"' where u_username='"+nam1.getText()+"'";
			dataaccess de=new dataaccess();
			if(isEmpty(nam1)||isEmpty(username)){
				msg="All fields are mandatory";
			}
			else{
				if(de.updateDB(sql2)>0 && de.updateDB(sql4)>0)
				{
					msg="Payment successful!";
					nam1.setText(null);
					username.setText(null);
				}
				else{
					msg="Enter valid username!";
				}
			}
			JOptionPane.showMessageDialog(this,msg);
		}
		else if(sig.equals("Back"))
		{
			
			this.setVisible(false);
			parent.setVisible(true);
			paymentbal=0;
			paymentold1=0;
		}

	}
	
	
}
