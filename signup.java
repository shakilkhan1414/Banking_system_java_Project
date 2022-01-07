import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class signup extends JFrame implements ActionListener,ItemListener
{
	private Frame parent;
	private JRadioButton r1,r2;
	private ButtonGroup bg;
	private JTextField nam1;
	private JTextField username;
	private JTextField email1;
	private JTextField mobile1;
	private JPasswordField password1;
	private JTextField dob1;
	private String gender1;
	public signup()
	{
		super("Signup");
		Font f=new Font("TimesRoman",Font.PLAIN,40);
		Font f1=new Font("TimesRoman",Font.BOLD,20);
		Font f2=new Font("TimesRoman",Font.PLAIN,15);
		JPanel heading;
		heading=new JPanel();
		heading.setLayout(null);
		heading.setBackground(new Color(0,0,0,150));
		heading.setBounds(0,0,900,100);
		JLabel name=new JLabel("Create new account");
		name.setForeground(Color.WHITE);
		name.setBounds(300,25,400,50);
		name.setFont(f);
		heading.add(name);
		
		JPanel head;
		head=new JPanel();
		head.setLayout(null);
		head.setBackground(new Color(180,0,0,90));
		head.setBounds(250,130,400,400);
		
		JLabel nam=new JLabel("Name:");
		nam.setForeground(Color.BLACK);
		nam.setBounds(10,20,400,50);
		nam.setFont(f1);
		head.add(nam);
		JLabel uname=new JLabel("User name:");
		uname.setForeground(Color.BLACK);
		uname.setBounds(10,60,400,50);
		uname.setFont(f1);
		head.add(uname);
		JLabel email=new JLabel("Email:");
		email.setForeground(Color.BLACK);
		email.setBounds(10,100,400,50);
		email.setFont(f1);
		head.add(email);
		JLabel mobile=new JLabel("Mobile no:");
		mobile.setForeground(Color.BLACK);
		mobile.setBounds(10,140,400,50);
		mobile.setFont(f1);
		head.add(mobile);
		JLabel Password=new JLabel("Password:");
		Password.setForeground(Color.BLACK);
		Password.setBounds(10,180,400,50);
		Password.setFont(f1);
		head.add(Password);
		JLabel gender=new JLabel("Gender:");
		gender.setForeground(Color.BLACK);
		gender.setBounds(10,220,400,50);
		gender.setFont(f1);
		head.add(gender);
		JLabel dob=new JLabel("Date of Birth:");
		dob.setForeground(Color.BLACK);
		dob.setBounds(10,260,400,50);
		dob.setFont(f1);
		head.add(dob);
		
		
		
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
		email1=new JTextField("");
		email1.setBounds(130,110,200,30);
		email1.setFont(f2);
		email1.setBackground(new Color(0,206,209,150));
		head.add(email1);
		mobile1=new JTextField("");
		mobile1.setBounds(130,150,200,30);
		mobile1.setFont(f2);
		mobile1.setBackground(new Color(0,206,209,150));
		head.add(mobile1);
		password1=new JPasswordField("");
		password1.setBounds(130,190,200,30);
		password1.setBackground(new Color(0,206,208,150));
		head.add(password1);
		dob1=new JTextField("");
		dob1.setBounds(130,270,200,30);
		dob1.setFont(f2);
		dob1.setBackground(new Color(0,207,209,150));
		head.add(dob1);
		
		
		JButton Sign=new JButton("Sign up");
		Sign.setFont(f1);
		Sign.setBounds(100,320,100,35);
		Sign.setBackground(new Color(255,255,255));
		head.add(Sign);
		JButton back=new JButton("Back");
		back.setFont(f1);
		back.setBounds(250,320,80,35);
		back.setBackground(new Color(29,201,2));
		head.add(back);
		
		r1=new JRadioButton("Male");
		r1.setBounds(130,230,100,30);
		r1.setBackground(new Color(0,206,208,150));
		head.add(r1);
		r2=new JRadioButton("Female");
		r2.setBounds(230,230,100,30);
		r2.setBackground(new Color(0,206,208,150));
		head.add(r2);
		bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		
		setSize(900,600);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(300,80);
		ImageIcon background=new ImageIcon("background3.jpg");
		Image img=background.getImage();
		Image temp=img.getScaledInstance(900,600,Image.SCALE_SMOOTH);
		background=new ImageIcon(temp);
		JLabel backg=new JLabel("",background,JLabel.CENTER);
		backg.add(head);
		backg.add(heading);
		backg.setBounds(0,0,900,600);
		add(backg);
		
		Sign.addActionListener(this);
		back.addActionListener(this);
		r1.addItemListener(this);
		r2.addItemListener(this);
		
	}
	public void setParent(Frame p){
		parent=p;
	}
	
	public void itemStateChanged(ItemEvent ex)
    {
		if(r1.isSelected())
		{
			gender1="Male";
		}
		else if(r2.isSelected())
		{
			gender1="Female";
		}
	}
    
	private boolean isEmpty(JTextField textf)
	{
		boolean flag=false;
		if(textf.getText().length()==0)flag=true;
		return flag;
	}
	private boolean isValidEmail(String valide){
		boolean flag=true;
		int atIdx=valide.indexOf("@");
		int dotIdx=valide.indexOf(".");
		if(dotIdx<atIdx)flag=false;
		return flag;
	}
	
	public void actionPerformed(ActionEvent e1)
	{
		String sig1=e1.getActionCommand();
		String msg5="";
		if(sig1.equals("Sign up"))
		{
					if(isEmpty(nam1) || isEmpty(email1) || isEmpty(username) || isEmpty(mobile1) || isEmpty(password1)|| isEmpty(dob1))
		    {
				msg5="All fields are mandatory";
			}
			else if(!isValidEmail(email1.getText()))
			{
				msg5="Invalid Email!";
			}
			else{
				dataaccess da=new dataaccess();
				String sql="insert into user(u_name,u_username,u_email,u_mobile,u_gender,u_dob,u_password) values('"+nam1.getText()+"','"+username.getText()+"','"+email1.getText()+"','"+mobile1.getText()+"','"+gender1+"','"+dob1.getText()+"','"+password1.getText()+"')";
				if(da.updateDB(sql)>0){
					msg5="Signup Complete!";
					nam1.setText(null);
					username.setText(null);
					email1.setText(null);
					mobile1.setText(null);
					password1.setText(null);
					dob1.setText(null);
				}
				else{
					msg5="Signup Error!";
				}
			}
			JOptionPane.showMessageDialog(this,msg5);
		   
		}
		else if(sig1.equals("Back"))
		{
			    parent.setVisible(true);
				this.setVisible(false);
		}
		
	}
	
}
