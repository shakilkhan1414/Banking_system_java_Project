import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class checkbalance extends JFrame implements ActionListener{
	
    private Frame parent;
	private JLabel bal;
	String loaduname1;
	private int balance8=0;
	public checkbalance()
	{
		super("Balance");
		
		Font f=new Font("TimesRoman",Font.BOLD,20);
		Font f1=new Font("TimesRoman",Font.PLAIN,40);
		JPanel heading;
		heading=new JPanel();
		heading.setLayout(null);
		heading.setBackground(new Color(0,0,0,180));
		heading.setBounds(0,0,900,100);
		JLabel name=new JLabel("Account Balance");
		name.setForeground(Color.WHITE);
		name.setBounds(320,25,400,50);
		name.setFont(f1);
		heading.add(name);
		
		JPanel head;
		head=new JPanel();
		head.setLayout(null);
		head.setBackground(new Color(0,0,0,180));
		head.setBounds(250,165,400,350);
		
		bal=new JLabel();
		bal.setForeground(Color.WHITE);
		bal.setBounds(150,20,100,50);
		bal.setFont(f);
		head.add(bal);
		
		JLabel ball=new JLabel("Your Balance : ");
		ball.setForeground(Color.WHITE);
		ball.setBounds(20,20,200,50);
		ball.setFont(f);
		head.add(ball);
		
		JButton Back=new JButton("Back");
		Back.setFont(f);
		Back.setBounds(150,100,100,30);
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
		Back.addActionListener(this);
		
	}
	
	public void setParent(Frame p){
		parent=p;
	}
	
	public void loadData(){
		String sql="select u_balance from user where u_username='"+loaduname1+"'";
		
		try{
			dataaccess da=new dataaccess();
			ResultSet rs=da.getData(sql);
			
			while(rs.next())
			{
			balance8=balance8+rs.getInt("u_balance");
			}
			bal.setText(String.valueOf(balance8));
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}

	
	public void actionPerformed(ActionEvent e4)
	{
		String sig=e4.getActionCommand();
		if(sig.equals("Back"))
		    {
			this.setVisible(false);
			parent.setVisible(true);
			balance8=0;
			
		    }
	}

}
