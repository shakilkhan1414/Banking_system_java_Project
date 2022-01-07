import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class addmoney extends JFrame implements ActionListener
{
	private Frame parent;
	private int balanceold=0;
	private JTextField unam1;
	private JTextField amount1;
	private int balancenew=0;
	private String balf="";
	private int balancenew2=0;
	public addmoney()
	{
		super("Add Money");
		
		Font f=new Font("TimesRoman",Font.PLAIN,40);
		Font f1=new Font("TimesRoman",Font.BOLD,20);
		Font f2=new Font("TimesRoman",Font.PLAIN,15);
		JPanel heading;
		heading=new JPanel();
		heading.setLayout(null);
		heading.setBackground(new Color(0,0,0,170));
		heading.setBounds(0,0,900,100);
		JLabel name=new JLabel("Add Money");
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
		uname.setBounds(15,70,400,50);
		uname.setFont(f1);
		head.add(uname);
		
		
		
		
		unam1=new JTextField("");
		unam1.setBounds(130,30,200,30);
		unam1.setFont(f2);
		unam1.setBackground(new Color(0,206,209,150));
		head.add(unam1);
		amount1=new JTextField("");
		amount1.setBounds(130,80,200,30);
		amount1.setFont(f2);
		amount1.setBackground(new Color(0,206,209,150));
		head.add(amount1);
		
		JButton send1=new JButton("Add Money");
		send1.setFont(f1);
		send1.setBounds(130,140,150,30);
		send1.setBackground(new Color(255,255,255));
		head.add(send1);
		JButton Back5=new JButton("Back");
		Back5.setFont(f1);
		Back5.setBounds(130,190,150,30);
		Back5.setBackground(new Color(29,201,2));
		head.add(Back5);
		
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
		send1.addActionListener(this);
		Back5.addActionListener(this);
		
	}
	
	public void setParent(Frame p){
		parent=p;
	}
	
	private boolean isEmpty(JTextField s){
		boolean flag=false;
		if(s.getText().length()==0)flag=true;
		return flag;
	}
	
	public void loadDataadmin(){
		String sql="select u_balance from user where u_username='"+unam1.getText()+"'";
		try{
			dataaccess da=new dataaccess();
			ResultSet rs=da.getData(sql);
			
			while(rs.next()){
				balanceold=balanceold+rs.getInt("u_balance");
			}
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}
	
	public void actionPerformed(ActionEvent e5)
	{
		String sig=e5.getActionCommand();
		if(sig.equals("Add Money"))
		{
			loadDataadmin();
			balf=balf+amount1.getText();
			balancenew=Integer.parseInt(balf);
			balancenew2=balanceold+balancenew;
			String msg="";
			String sql2="update user set u_balance='"+balancenew2+"' where u_username='"+unam1.getText()+"'";
			dataaccess de=new dataaccess();
			if(isEmpty(unam1)||isEmpty(amount1)){
				msg="All fields are mandatory";
			}
			else{
				if(de.updateDB(sql2)>0)
				{
					msg="Successful!";
					unam1.setText(null);
					amount1.setText(null);
				}
				else{
					msg="Enter valid username!!";
				}
			}
			JOptionPane.showMessageDialog(this,msg);
			
		}
		else if(sig.equals("Back"))
		{
			parent.setVisible(true);
			this.setVisible(false);
			balanceold=0;
			
		}

	}
	
	
}
