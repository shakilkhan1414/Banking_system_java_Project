import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class login extends JFrame implements ActionListener{
	private static signup s5;
	private static profile q;
	private JTextField username;
	private JPasswordField password;
	public static adminprofile ap; 

	public login()
	{
		super("Login");
		
		Font f=new Font("TimesRoman",Font.PLAIN,40);
		Font f1=new Font("TimesRoman",Font.BOLD,20);
		Font f2=new Font("TimesRoman",Font.PLAIN,15);
		Font f3=new Font("TimesRoman",Font.PLAIN,20);
		Font f4=new Font("TimesRoman",Font.PLAIN,35);
		s5=new signup();
		q=new profile();
		ap=new adminprofile();
		JPanel heading;
		heading=new JPanel();
		heading.setLayout(null);
		heading.setBackground(new Color(0,0,0,120));
		heading.setBounds(0,0,900,100);
		JLabel name=new JLabel("Welcome");
		name.setForeground(Color.WHITE);
		name.setBounds(370,25,400,50);
		name.setFont(f);
		heading.add(name);
		
		JPanel head;
		head=new JPanel();
		head.setLayout(null);
		head.setBackground(new Color(0,0,0,150));
		head.setBounds(300,140,300,400);
		
		JLabel nnam=new JLabel("Username");
		nnam.setForeground(new Color(255,255,255));
		nnam.setBounds(35,63,400,60);
		nnam.setFont(f3);
		head.add(nnam);
		JLabel unamer=new JLabel("Password");
		unamer.setForeground(new Color(255,255,255));
		unamer.setBounds(35,133,400,60);
		unamer.setFont(f3);
		head.add(unamer);
		
		JLabel headd=new JLabel("LOGIN");
		headd.setForeground(new Color(255,255,255));
		headd.setBounds(100,15,200,30);
		headd.setFont(f4);
		head.add(headd);
		
		username=new JTextField("");
		username.setBounds(35,110,220,30);
		username.setFont(f3);
		username.setBackground(new Color(224,224,224));
		head.add(username);
		password=new JPasswordField("");
		password.setBounds(35,180,220,30);
		password.setFont(f2);
		password.setBackground(new Color(224,224,224));
		head.add(password);
		
		JButton login=new JButton("Log in");
		login.setBounds(35,230,220,35);
		login.setFont(f1);
		login.setBackground(new Color(51,204,255));
		head.add(login);
		JButton Signup=new JButton("Sign up");
		Signup.setBounds(35,280,220,35);
		Signup.setFont(f1);
		Signup.setBackground(new Color(255,68,0));
		head.add(Signup);
		
		setSize(900,600);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(300,80);
		ImageIcon background=new ImageIcon("background5.jpg");
		Image img=background.getImage();
		Image temp=img.getScaledInstance(900,600,Image.SCALE_SMOOTH);
		background=new ImageIcon(temp);
		JLabel backg=new JLabel("",background,JLabel.CENTER);
		backg.add(head);
		backg.add(heading);
		backg.setBounds(0,0,900,600);
		add(backg);
		login.addActionListener(this);
		Signup.addActionListener(this);

		
	}
	
	private boolean checkAuth()
	{
		boolean flag=false;
		String ndb=username.getText();
		String pdb=password.getText();
		String sql="select * from user where u_username='"+ndb+"' and u_password='"+pdb+"'";
		System.out.println(sql);
		try{
			dataaccess da=new dataaccess();
			ResultSet rs=da.getData(sql);
			while(rs.next()){
					flag=true;
			}
		}
		catch(Exception ex){
			System.out.println("Exception occurred");
		}
		return flag;
	}
	
	private boolean checkAuth1()
	{
		boolean flag=false;
		String ndb=username.getText();
		String pdb=password.getText();
		String sql="select * from admin where a_username='"+ndb+"' and a_password='"+pdb+"'";
		try{
			dataaccess da=new dataaccess();
			ResultSet rs=da.getData(sql);
			while(rs.next()){
					flag=true;
			}
		}
		catch(Exception ex){
			System.out.println("Exception occurred");
		}
		return flag;
	}
	
	

	public void actionPerformed(ActionEvent e)
	{
		String sig=e.getActionCommand();
		String dbun=username.getText();
		String msg9="";
		if(sig.equals("Log in"))
		    {
				if(dbun.equals("admin581")|| dbun.equals("admin585")|| dbun.equals("admin587") )
				{
					if(checkAuth1())
			          {
			        	this.setVisible(false);
			            ap.setVisible(true);
				        ap.setParent(this);
			          }
			        else
					{
						msg9="Incorrect username or password! ";
					JOptionPane.showMessageDialog(this,msg9);
					}
				}
				else{
			         if(checkAuth())
			          {
			        	this.setVisible(false);
			            q.setVisible(true);
			         	q.setParent(this);
						q.loaduname=username.getText();
						q.loadDataa();
			          }
			        else{
			   
			         	msg9="Incorrect username or password! ";
						JOptionPane.showMessageDialog(this,msg9);
		        	}
				}
			          
		    }
		else if(sig.equals("Sign up"))
			{
				this.setVisible(false);
			    s5.setVisible(true);
				s5.setParent(this);
			}
	
	}

}
