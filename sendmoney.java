import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class sendmoney extends JFrame implements ActionListener
{
	private Frame parent;
	String loaduname2;
	private int balanceold1=0;
	private int balancenew1=0;
	private String balf1="";
	private int balancenew5=0;
	private int nbalanceold1=0;
	private int nbalancenew5=0;
	private JTextField nam1;
	private JTextField uamount;
	public sendmoney()
	{
		super("Send Money");
		
		Font f=new Font("TimesRoman",Font.PLAIN,40);
		Font f1=new Font("TimesRoman",Font.BOLD,20);
		Font f2=new Font("TimesRoman",Font.PLAIN,15);
		JPanel heading;
		heading=new JPanel();
		heading.setLayout(null);
		heading.setBackground(new Color(0,0,0,170));
		heading.setBounds(0,0,900,100);
		JLabel name=new JLabel("Send Money");
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
		uamount=new JTextField("");
		uamount.setBounds(130,70,200,30);
		uamount.setFont(f2);
		uamount.setBackground(new Color(0,206,209,150));
		head.add(uamount);
		
		JButton send=new JButton("Send");
		send.setFont(f1);
		send.setBounds(90,120,100,30);
		send.setBackground(new Color(255,255,255));
		head.add(send);
		JButton Back=new JButton("Back");
		Back.setFont(f1);
		Back.setBounds(210,120,100,30);
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
		send.addActionListener(this);
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
		String sql="select u_balance from user where u_username='"+loaduname2+"'";
		try{
			dataaccess da=new dataaccess();
			ResultSet rs=da.getData(sql);
			
			while(rs.next()){
				balanceold1=balanceold1+rs.getInt("u_balance");
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
				nbalanceold1=nbalanceold1+rs1.getInt("u_balance");
			}
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}
	
	public void actionPerformed(ActionEvent e5)
	{
		String sig=e5.getActionCommand();
		if(sig.equals("Send"))
		{
		   loadbalance();
		   loadbalance2();
		   
			balf1=balf1+uamount.getText();
			balancenew1=Integer.parseInt(balf1);
			balancenew5=balanceold1-balancenew1;
			
			
			nbalancenew5=nbalanceold1+balancenew1;
			
			String msg="";
			String sql2="update user set u_balance='"+balancenew5+"' where u_username='"+loaduname2+"'";
			String sql4="update user set u_balance='"+nbalancenew5+"' where u_username='"+nam1.getText()+"'";
			dataaccess de=new dataaccess();
			if(isEmpty(nam1)||isEmpty(uamount)){
				msg="All fields are mandatory";
			}
			else{
				if(de.updateDB(sql2)>0 && de.updateDB(sql4)>0)
				{
					msg="Transfer successful!";
					nam1.setText(null);
					uamount.setText(null);
				}
				else{
					msg="Enter valid username!";
				}
			}
			JOptionPane.showMessageDialog(this,msg);
			
		}
		
		else if(sig.equals("Back"))
		{
			parent.setVisible(true);
			this.setVisible(false);
			balanceold1=0;
			nbalanceold1=0;
			
		}

	}
	
	
}
