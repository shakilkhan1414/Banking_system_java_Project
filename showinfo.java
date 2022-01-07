import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class showinfo extends JFrame implements ActionListener{
	

	private Frame parent;
	private JTextArea tta;
	

	public showinfo()
	{
		super("Show info");
		
		Font f=new Font("TimesRoman",Font.BOLD,20);
		Font f1=new Font("TimesRoman",Font.PLAIN,40);
		Font f2=new Font("TimesRoman",Font.PLAIN,20);
		JPanel heading;
		heading=new JPanel();
		heading.setLayout(null);
		heading.setBackground(new Color(0,0,0,180));
		heading.setBounds(0,0,900,100);
		JLabel name=new JLabel("User's Information");
		name.setForeground(Color.WHITE);
		name.setBounds(300,25,400,50);
		name.setFont(f1);
		heading.add(name);
		
		JPanel head;
		head=new JPanel();
		head.setLayout(null);
		head.setBackground(new Color(0,0,0,150));
		head.setBounds(40,140,800,400);
		
		JButton infoback=new JButton("Back");
		infoback.setFont(f);
		infoback.setBounds(270,350,200,30);
		infoback.setBackground(new Color(29,201,2));
		head.add(infoback);
		
		tta=new JTextArea();
		tta.setFont(f2);
		tta.setBounds(10,10,780,400);
		tta.setEditable(false);
		tta.setBackground(new Color(224,224,224));
		head.add(tta);
		
		
		
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
		infoback.addActionListener(this);
		
	}
	public void setParent(Frame p){
	parent=p;}
	
	public void loadinfo(){
		String sql="select u_name,u_username,u_email,u_mobile,u_gender,u_dob,u_balance from user";
		try{
			dataaccess da=new dataaccess();
			ResultSet rs=da.getData(sql);
			String data="";
			while(rs.next()){
				data=data+rs.getString("u_name")+"-";
				data=data+rs.getString("u_username")+"-";
				data=data+rs.getString("u_email")+"-";
				data=data+rs.getString("u_mobile")+"-";
				data=data+rs.getString("u_gender")+"-";
				data=data+rs.getString("u_dob")+"-";
				data=data+rs.getString("u_balance")+"\n";
			}
			tta.setText(data);
		}
		catch(Exception ex){
			System.out.println("Exception in home");
		}
	}
	
	public void actionPerformed(ActionEvent e2)
	{
		String si=e2.getActionCommand();
		if(si.equals("Back"))
		    {
	            parent.setVisible(true);
	     		this.setVisible(false);
	 		
		    }
		
			
	}

}
