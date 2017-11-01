
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.event.*;
import javax.swing.table.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import java.io.*;
import java.util.Random;
import java.util.Arrays;


public class Gui1 extends JFrame implements ActionListener
{

	ImageIcon img1;
	JLabel Jimg2,l4,l5,l6;
	JButton b3;
	JTextField t3,t4;
	JPasswordField Jpw,Jpw2;
	JLayeredPane Jlp2;
	int flg=0;
	ImageIcon img;
	JLabel Jimg,l1,l2;
	JButton b1,b2;
	JTextField t1,t2;
	JPasswordField pass=new JPasswordField(10);
	JLayeredPane Jlp;
	Object o=new Object();
	Object o1=new Object();
	
	public Gui1()
	{
		super("Login");	
	
		ImageIcon iicon=new ImageIcon("Key_CLipart.jpg");
		Image ii=iicon.getImage();
		this.setIconImage(ii);
		setSize(500,400);
		setLocation(450,150);
		setVisible(true);
		setLayout(null);
		Jlp=getLayeredPane();
		
		img=new ImageIcon("group.gif");
		Jimg=new JLabel(img);
		Jimg.setBounds(-40,0,600,400);

		b1=new JButton("Sign Up");
		b2=new JButton("Sign In");
		b1.setBounds(290,300,90,30);
		b2.setBounds(100,300,90,30);
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		l1=new JLabel("Password");
		l1.setFont(new Font("Times New Roman",1,16));
		l2=new JLabel("Username");
		l2.setFont(new Font("Times New Roman",1,16));
		
	

		t1=new JTextField(12);
		t2=new JPasswordField(10);
	
		
		l1.setBounds(100,180,130,40);
		l2.setBounds(100,150,130,40);
	
		t1.setBounds(220,160,130,25);
	
		t2.setBounds(220,190,130,25);		
		

		Jlp.add(l1,new Integer(2));
		Jlp.add(l2,new Integer(2));
	
		Jlp.add(t1,new Integer(2));
		Jlp.add(t2,new Integer(2));
		Jlp.add(b1,new Integer(2));
		Jlp.add(b2,new Integer(2));
		Jlp.add(Jimg,new Integer(2));
		Jlp.add(pass);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		

	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		
		if(b==b1)
		{
			new Signup();
		}
		if(b==b2)
		{
			try
			{
				Mongo mongo=new Mongo("localhost",27017);
				DB db=mongo.getDB("test");
				DBCollection coll=db.getCollection("auth");
				BasicDBObject document=new BasicDBObject();
				DBCursor cursor=coll.find();
				DBObject db1;
				String value1;
				String pass2;
				value1=t1.getText();
				pass2=t2.getText();
				while(cursor.hasNext())
				{
					db1=cursor.next();
					o=db1.get("username");
					o.toString();
					o1=db1.get("password");
					o1.toString();
					if(value1.equals(o) && pass2.equals(o1))
					{
						flg=1;
						break;
					}
					else
					{
						flg=0;
					}
				}
			}
			catch(Exception et)
			{
				et.printStackTrace();
			}
			if(flg==1)
			{
				Gui2 d=new Gui2();
				this.setVisible(false);
			}
			else
			{
				
				JOptionPane.showMessageDialog(this,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE);
			}
		
		
						
		
		}
	}
	public static void main(String []ar) 
	{
		try
		{
		 	Process p;
			p = Runtime.getRuntime().exec("cmd /c  start server.bat");
		}
		catch(Exception e){}

			new Gui1();
	}
}
class Signup extends JFrame implements ActionListener
{
	ImageIcon img;
	JLabel Jimg,l1,l2,l3;
	JButton b1;
	JTextField t1,t2;
	JPasswordField Jpw,Jpw2;
	JLayeredPane Jlp;
	public Signup()
	{
		super("Sign Up");
		setSize(560,300);
		setVisible(true);
		setLocation(450,100);
		setLayout(null);

		Jlp=getLayeredPane();
		
		img=new ImageIcon("group.jpg");
		Jimg=new JLabel(img);
		Jimg.setBounds(0,0,300,300);
		
		l1=new JLabel("Enter Username");
		l2=new JLabel("Enter Password");
		l3=new JLabel("Confirm Password");
		b1=new JButton("Submit");
		t1=new JTextField(20);
		Jpw=new JPasswordField(20);
		Jpw2=new JPasswordField(20);
		
		l1.setBounds(70,50,200,40);
		l2.setBounds(70,90,200,40);
		l3.setBounds(70,135,200,40);
		b1.setBounds(200,200,90,30);
		t1.setBounds(230,60,90,25);
		Jpw.setBounds(230,100,90,25);
		Jpw2.setBounds(230,140,90,25);

		Jlp.add(l1,new Integer(2));
		Jlp.add(l2,new Integer(2));
		Jlp.add(l3,new Integer(2));
	    	Jlp.add(b1,new Integer(2));
		Jlp.add(t1,new Integer(2));
		Jlp.add(Jpw,new Integer(2));
		Jlp.add(Jpw2,new Integer(2));
		Jlp.add(Jimg,new Integer(2));
		b1.addActionListener(this);
		

	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		if(b==b1)
		{
			String value1,value2,unm;
			
			unm=t1.getText();
			int x=unm.length();
			
			value1=Jpw.getText();
			int y=value1.length();
			
			value2=Jpw2.getText();
			
			if(x>0 && y>0)
			{
				if(value1.equals(value2))
				{
				
				JOptionPane jop=new JOptionPane();
				jop.setMessage("One user created successfully");
				int reply=JOptionPane.showConfirmDialog(this,"Create the new 						user?","Confirm",JOptionPane.YES_NO_OPTION);
				if(reply==JOptionPane.YES_OPTION)
				{	
					try
					{
						
						Mongo mongo=new Mongo("localhost",27017);
						DB db=mongo.getDB("test");
						DBCollection coll=db.getCollection("auth");
						BasicDBObject document=new BasicDBObject();
						DBCursor cursor=coll.find();
						DBObject db1;
						BasicDBObject doc=new BasicDBObject();
						doc.put("username",unm);
						doc.put("password",value1);
						coll.insert(doc);
					}
					catch(Exception et)
					{
						et.printStackTrace();
					}
					
				}
				setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Password do not match","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Please enter required information","Error",JOptionPane.ERROR_MESSAGE);
		}
		}
	}
	

}

class Gui2 extends JFrame implements ActionListener
{
	ImageIcon img;
	JButton b1,b2,b3;
	JLabel Jimg;
	JLayeredPane Jlp;
	public Gui2()
	{
		super("Select");
		setSize(430,400);
		setVisible(true);
		setLocation(450,150);
		setLayout(null);

		Jlp=getLayeredPane();
		
		img=new ImageIcon("group.jpeg");
		Jimg=new JLabel(img);
		Jimg.setBounds(-10,-60,430,400);

		b1=new JButton("Group Creation");
		b2=new JButton("Group Evaluation");
		b3=new JButton("Help");
		b1.setBounds(120,180,150,35);
		b2.setBounds(120,240,150,35);
		b3.setBounds(120,300,150,35);
	
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);		
		Jlp.add(b1,new Integer(2));
		
		Jlp.add(b2,new Integer(2));
		Jlp.add(b3,new Integer(2));
		Jlp.add(Jimg,new Integer(2));
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		if(b==b1)
		{
			new Gui3();
			this.show(false);
	
		}
		if(b==b2)
		{
			new DisplayEv();
			this.show(false);
		}
		if(b==b3)
		{
			new Help();
			this.show(false);
		}
	}
}
class DisplayEv extends JFrame implements ActionListener
{
	JButton b1,b2,b3;
	JLayeredPane Jpl;
	DisplayEv()
	{
		super("SELECT CLASS");
		b1=new JButton("S.Y.");
		b2=new JButton("T.Y.");
		b3=new JButton("B.Tech");
		Jpl=getLayeredPane(); 

		b1.setBounds(150,70,100,35);
		b2.setBounds(150,140,100,35);
		b3.setBounds(150,210,100,35);
		
		Jpl.add(b1,new Integer(2));
		Jpl.add(b2,new Integer(2));
		Jpl.add(b3,new Integer(2));
		setVisible(true);
		setSize(420,400);
		setLocation(400,150);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		if(b==b1)
		{
			try
			{
				this.setVisible(false);
				SyE a1=new SyE();
				a1.Etable();
				
			}catch(Exception ae){}
		}
		if(b==b2)
		{
			try
			{
				this.setVisible(false);
				TyE a1=new TyE();
				a1.Etable();
			}
			catch(Exception eer){}
		}
		if(b==b3)
		{
			try
			{
				this.setVisible(false);
				BtE a1=new BtE();
				a1.Etable();
			}
			catch(Exception eer2){}		
		}
	}
}
class SyE  extends JFrame implements ActionListener,ListSelectionListener
{
	DBCollection coll1,coll2;
	DBCursor cu1,cu2;
	Mongo mongo;
	DB db;
	JLabel label;
	JTable tab;
	JButton b1,b2,b3,b4;
	JFrame jf;
	ImageIcon img;
	JLabel Jimg;
	JLayeredPane Jlp;
	int rowIndex2=0;
	int rowIndex=0;
	GridBagConstraints c,c1;
	JPanel p,p1;
	Object o=null,o1=null,o2=null,o3=null,o4=null,o5=null,o6,o7;
	DBObject db1,db2;
	Object ot1=null,ot2=null,ot3=null,ot4=null,ot5=null,ot6=null,ot8=null,ot7=null;
	int p2=0;
	int itr=1;
	SyE()
	{
		try
		{
			mongo=new Mongo("localhost",27017);
			db=mongo.getDB("test");
		}
		catch(Exception e)
		{
		}

		coll1=db.getCollection("group");
		coll2=db.getCollection("ESy");
		
		cu1=coll1.find();
		cu2=coll2.find();
		try
		{
			while(cu2.hasNext())
			{
				coll2.remove(cu2.next());
			}
		}
		catch(Exception e){}
		
		try
		{
			while(cu1.hasNext())
			{
				
				db1=cu1.next();
				
				o1=db1.get("group_name");
				o2=db1.get("rollno");
				o3=db1.get("name");
				o4=db1.get("cpi");
				o5=db1.get("avg_cpi");
				
				BasicDBObject docE=new BasicDBObject();
				docE.put("sr",itr);
				docE.put("group_name",o1);
				docE.put("rollno",o2);
				docE.put("name",o3);
				docE.put("cpi",o4);
				docE.put("avg_cpi",o5);
				docE.put("I_marks",o6);
				docE.put("E_marks",o7);
				
				docE.put("Total",o);

				coll2.insert(docE);
				itr++;
			}
		}catch(Exception e){}	
			
	}
	public void Etable()
	{	
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(15,15,15,15);
		c1=new GridBagConstraints();

		p=new JPanel();
		p.setLayout(new GridBagLayout());
		p1=new JPanel();
		p1.setLayout(new GridLayout());
	
		Object[][] data=null;
		Object[] columns={"Group-Name","Rollno","Name","cpi","Averagecpi","I_marks","E_marks","Total"};
		DefaultTableModel dTable=new DefaultTableModel(data,columns);
		tab=new JTable(dTable);
        	jf=new JFrame();
		b1=new JButton("I_MARKS");
		b2=new JButton("E_MARKS");
		b3=new JButton("BACK");
		b4=new JButton("FINISH");
	             
		
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		ListSelectionModel selectionModel = tab.getSelectionModel();  
	        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        	selectionModel.addListSelectionListener(this);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		JScrollPane scrollpane=new JScrollPane(tab);
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b1,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b2,c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b3,c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b4,c);

		
		p1.add(scrollpane);

		jf.setSize(1500,1200);
	
		jf.setLayout(null);
		p1.setBounds(50,20,990,650);
		p.setBounds(1100,0,140,700);
		jf.add(p1);
		jf.add(p);
		jf.show();

		Object tmprow[];
		DBObject db1;
		cu2=coll2.find().sort(new BasicDBObject("sr",1));
		try
		{
			while(cu2.hasNext())
			{
				db1=cu2.next();
				o1=db1.get("group_name");
				o2=db1.get("rollno");
				o3=db1.get("name");
				o4=db1.get("cpi");
				o5=db1.get("avg_cpi");
				o6=db1.get("I_marks");
				o7=db1.get("E_marks");
				o=db1.get("Total");
				
				tmprow=new Object[]{o1,o2,o3,o4,o5,o6,o7,o};
				dTable.addRow(tmprow);
			}
		}catch(Exception ae){}
		
	}	
	
	public void actionPerformed(ActionEvent e)
	{
		String mrk=null;
		String pr;
		int tot=0,tmptot;
		JButton b=(JButton)e.getSource();
		if(b==b1)
		{
			try
			{
				
				Object ot1=tab.getValueAt(rowIndex2,0);
				Object ot2=tab.getValueAt(rowIndex2,1);
				
				Object ot3=tab.getValueAt(rowIndex2,2);
				
				Object ot4=tab.getValueAt(rowIndex2,3);
				Object ot5=tab.getValueAt(rowIndex2,4);
				Object ot6=tab.getValueAt(rowIndex2,5);
				Object ot7=tab.getValueAt(rowIndex2,6);
				Object ot8=tab.getValueAt(rowIndex2,7);
			
				boolean btf=false;
				int flg=0;
			
				while(!btf)
				{
					mrk=JOptionPane.showInputDialog(jf,"Enter Internal marks of selected student",ot6);
					int m=Integer.parseInt(mrk);
					if(m<1 || m>50)
						JOptionPane.showMessageDialog(this,"Valid Input: Internal marks (1-50)","Error",JOptionPane.ERROR_MESSAGE);
					else
						btf=true;
				}
				tot=tot+Integer.parseInt(mrk);
				BasicDBObject docE=new BasicDBObject();
				
				BasicDBObject qry=new BasicDBObject();
				qry.put("rollno",ot2);
				DBCursor tmpcur=coll2.find(qry);
				DBObject db123=tmpcur.next();
				Object sro=db123.get("sr");
			
				BasicDBObject newDocument=new BasicDBObject();
				newDocument.put("sr",sro);
				newDocument.put("group_name",ot1);
				newDocument.put("rollno",ot2);
				newDocument.put("name",ot3);
				newDocument.put("cpi",ot4);
				newDocument.put("avg_cpi",ot5);
				newDocument.put("I_marks",mrk);
				newDocument.put("E_marks",ot7);
				newDocument.put("Total",tot);
				
				coll2.update(new BasicDBObject().append("rollno",ot2),newDocument);
				jf.setVisible(false);
				Etable();
			}
			catch(Exception e1){}
		}
		if(b==b2)
		{
			try
			{
				Object ot1=tab.getValueAt(rowIndex2,0);
				Object ot2=tab.getValueAt(rowIndex2,1);
				
				Object ot3=tab.getValueAt(rowIndex2,2);
				
				Object ot4=tab.getValueAt(rowIndex2,3);
				Object ot5=tab.getValueAt(rowIndex2,4);
				Object ot6=tab.getValueAt(rowIndex2,5);
				
				Object ot7=tab.getValueAt(rowIndex2,6);
				Object ot8=tab.getValueAt(rowIndex2,7);
				pr=ot8.toString();
				tmptot=Integer.parseInt(pr);
			
				boolean btf=false;
				int flg=0;
			
				while(!btf)
				{
					mrk=JOptionPane.showInputDialog(jf,"Enter External marks of selected student",ot7);
					int m=Integer.parseInt(mrk);
					if(m<1 || m>50)
						JOptionPane.showMessageDialog(this,"Valid Input: Internal marks (1-50)","Error",JOptionPane.ERROR_MESSAGE);
					else
						btf=true;
				}
			
				BasicDBObject docE=new BasicDBObject();
				tot=tmptot+Integer.parseInt(mrk);
				BasicDBObject qry=new BasicDBObject();
				qry.put("rollno",ot2);
				DBCursor tmpcur=coll2.find(qry);
				DBObject db123=tmpcur.next();
				Object sro=db123.get("sr");
			
				BasicDBObject newDocument=new BasicDBObject();
				newDocument.put("sr",sro);
				newDocument.put("group_name",ot1);
				newDocument.put("rollno",ot2);
				newDocument.put("name",ot3);
				newDocument.put("cpi",ot4);
				newDocument.put("avg_cpi",ot5);
				newDocument.put("I_marks",ot6);
				newDocument.put("E_marks",mrk);
				newDocument.put("Total",tot);
				
				coll2.update(new BasicDBObject().append("rollno",ot2),newDocument);
				jf.setVisible(false);
				Etable();
			}
			catch(Exception e1){}
		}
		if(b==b3)
		{
			jf.setVisible(false);
			new Gui2();
		}
		if(b==b4)
		{
			jf.setVisible(false);
			new Gui11();
		}
	}
	public void valueChanged(ListSelectionEvent e)  
    	{  
		if(!e.getValueIsAdjusting())
		{
			ListSelectionModel model=tab.getSelectionModel();
			int lead=model.getLeadSelectionIndex();
			displayRowValues(lead);
		}
       	}	  
    	private void displayRowValues(int rowIndex)  
      	{
		int columns=tab.getColumnCount();
		rowIndex2=rowIndex;  
    	}  		
}
class TyE extends JFrame implements ActionListener,ListSelectionListener
{
	DBCollection coll1,coll2;
	DBCursor cu1,cu2;
	Mongo mongo;
	DB db;
	JLabel label;
	JTable tab;
	JButton b1,b2,b3,b4;
	JFrame jf;
	ImageIcon img;
	JLabel Jimg;
	JLayeredPane Jlp;
	int rowIndex2=0;
	int rowIndex=0;
	GridBagConstraints c,c1;
	JPanel p,p1;
	Object o=null,o1=null,o2=null,o3=null,o4=null,o5=null,o6,o7;
		 DBObject db1,db2;
	Object ot1=null,ot2=null,ot3=null,ot4=null,ot5=null,ot6=null,ot8=null,ot7=null;
	int p2=0;
	int itr=1;
	TyE()
	{
		try
		{
			mongo=new Mongo("localhost",27017);
			db=mongo.getDB("test");
		}catch(Exception re){}

		coll1=db.getCollection("group11");
		coll2=db.getCollection("ETy");
		
		cu1=coll1.find().sort(new BasicDBObject("sr",1));
		cu2=coll2.find();
		try
		{
			while(cu2.hasNext())
			{
				coll2.remove(cu2.next());
			}
		}
		catch(Exception ee){}
		try
		{
			while(cu1.hasNext())
			{
				db1=cu1.next();
				
				o1=db1.get("group_name");
				o2=db1.get("rollno");
				o3=db1.get("name");
				o4=db1.get("cpi");
				o5=db1.get("avg_cpi");
				
				BasicDBObject docE=new BasicDBObject();
				docE.put("sr",itr);
				docE.put("group_name",o1);
				docE.put("rollno",o2);
				docE.put("name",o3);
				docE.put("cpi",o4);
				docE.put("avg_cpi",o5);
				docE.put("I_marks",o6);
				docE.put("E_marks",o7);
				
				docE.put("Total",o);

				coll2.insert(docE);
				itr++;
			}
		}catch(Exception eee){}	
			
	}
	public void Etable()
	{	
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(15,15,15,15);
		c1=new GridBagConstraints();

		p=new JPanel();
		p.setLayout(new GridBagLayout());
		p1=new JPanel();
		p1.setLayout(new GridLayout());
	
		Object[][] data=null;
		Object[] columns={"Group-Name","Rollno","Name","cpi","Averagecpi","I_marks","E_marks","Total"};
		DefaultTableModel dTable=new DefaultTableModel(data,columns);
		tab=new JTable(dTable);
        		jf=new JFrame();
		b1=new JButton("I_MARKS");
		b2=new JButton("E_MARKS");
		b3=new JButton("BACK");
		b4=new JButton("FINISH");
	             
		
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		ListSelectionModel selectionModel = tab.getSelectionModel();  
	        	selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        		selectionModel.addListSelectionListener(this);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		JScrollPane scrollpane=new JScrollPane(tab);
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b1,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b2,c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b3,c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b4,c);

		
		p1.add(scrollpane);

		jf.setSize(1500,1200);
	
		jf.setLayout(null);
		p1.setBounds(50,20,990,650);
		p.setBounds(1100,0,140,700);
		jf.add(p1);
		jf.add(p);
		jf.show();

		Object tmprow[];
		DBObject db1;
		cu2=coll2.find().sort(new BasicDBObject("sr",1));
		try
		{
			while(cu2.hasNext())
			{
				db1=cu2.next();
				o1=db1.get("group_name");
				o2=db1.get("rollno");
				o3=db1.get("name");
				o4=db1.get("cpi");
				o5=db1.get("avg_cpi");
				o6=db1.get("I_marks");
				o7=db1.get("E_marks");
				o=db1.get("Total");
				System.out.println("gh"+o1);
				tmprow=new Object[]{o1,o2,o3,o4,o5,o6,o7,o};
				dTable.addRow(tmprow);
			}
		}catch(Exception ae){}
	}	
	public void actionPerformed(ActionEvent e)
	{
		String mrk=null;
		String pr;
		int tot=0,tmptot;
		JButton b=(JButton)e.getSource();
		if(b==b1)
		{
			try
			{
				Object ot1=tab.getValueAt(rowIndex2,0);
				Object ot2=tab.getValueAt(rowIndex2,1);
				System.out.println(ot2);
				Object ot3=tab.getValueAt(rowIndex2,2);
				System.out.println(ot3);
				Object ot4=tab.getValueAt(rowIndex2,3);
				Object ot5=tab.getValueAt(rowIndex2,4);
				Object ot6=tab.getValueAt(rowIndex2,5);
				Object ot7=tab.getValueAt(rowIndex2,6);
				Object ot8=tab.getValueAt(rowIndex2,7);
			
				boolean btf=false;
				int flg=0;
				
				while(!btf)
				{
					 mrk=JOptionPane.showInputDialog(jf,"Enter Internal marks of selected student",ot6);
					int m=Integer.parseInt(mrk);
					if(m<1 || m>50)
						JOptionPane.showMessageDialog(this,"Valid Input: Internal marks (1-50)","Error",JOptionPane.ERROR_MESSAGE);
					else
						btf=true;
				}
				tot=tot+Integer.parseInt(mrk);
				BasicDBObject docE=new BasicDBObject();
			
				BasicDBObject qry=new BasicDBObject();
				qry.put("rollno",ot2);
				DBCursor tmpcur=coll2.find(qry);
				DBObject db123=tmpcur.next();
				Object sro=db123.get("sr");
			
				BasicDBObject newDocument=new BasicDBObject();
				newDocument.put("sr",sro);
				newDocument.put("group_name",ot1);
				newDocument.put("rollno",ot2);
				newDocument.put("name",ot3);
				newDocument.put("cpi",ot4);
				newDocument.put("avg_cpi",ot5);
				newDocument.put("I_marks",mrk);
				newDocument.put("E_marks",ot7);
				newDocument.put("Total",tot);
			
				coll2.update(new BasicDBObject().append("rollno",ot2),newDocument);
				jf.setVisible(false);
				Etable();
			}
			catch(Exception e1){}
		}
		if(b==b2)
		{
			try
			{
				Object ot1=tab.getValueAt(rowIndex2,0);
				Object ot2=tab.getValueAt(rowIndex2,1);
				System.out.println(ot2);
				Object ot3=tab.getValueAt(rowIndex2,2);
				System.out.println(ot3);
				Object ot4=tab.getValueAt(rowIndex2,3);
				Object ot5=tab.getValueAt(rowIndex2,4);
				Object ot6=tab.getValueAt(rowIndex2,5);
				Object ot7=tab.getValueAt(rowIndex2,6);
				Object ot8=tab.getValueAt(rowIndex2,7);
				pr=ot8.toString();
				tmptot=Integer.parseInt(pr);
				boolean btf=false;
				int flg=0;
				
				while(!btf)
				{
					 mrk=JOptionPane.showInputDialog(jf,"Enter Internal marks of selected student",ot7);
					int m=Integer.parseInt(mrk);
					if(m<1 || m>50)
						JOptionPane.showMessageDialog(this,"Valid Input: Internal marks (1-50)","Error",JOptionPane.ERROR_MESSAGE);
					else
						btf=true;
				}
				tot=tmptot+tot+Integer.parseInt(mrk);
				BasicDBObject docE=new BasicDBObject();
			
				BasicDBObject qry=new BasicDBObject();
				qry.put("rollno",ot2);
				DBCursor tmpcur=coll2.find(qry);
				DBObject db123=tmpcur.next();
				Object sro=db123.get("sr");
			
				BasicDBObject newDocument=new BasicDBObject();
				newDocument.put("sr",sro);
				newDocument.put("group_name",ot1);
				newDocument.put("rollno",ot2);
				newDocument.put("name",ot3);
				newDocument.put("cpi",ot4);
				newDocument.put("avg_cpi",ot5);
				newDocument.put("I_marks",ot6);
				newDocument.put("E_marks",mrk);
				newDocument.put("Total",tot);
			
				coll2.update(new BasicDBObject().append("rollno",ot2),newDocument);
				jf.setVisible(false);
				Etable();
			}
			catch(Exception e1){}
		}
		if(b==b3)
		{
			jf.setVisible(false);
			new Gui2();
		}
		if(b==b4)
		{
			jf.setVisible(false);
			new Gui11();
		}
	}
	public void valueChanged(ListSelectionEvent e)  
    	{  
		if(!e.getValueIsAdjusting())
		{
			ListSelectionModel model=tab.getSelectionModel();
			int lead=model.getLeadSelectionIndex();
			displayRowValues(lead);
		}
       	}	  
    	private void displayRowValues(int rowIndex)  
      	{
		int columns=tab.getColumnCount();
		rowIndex2=rowIndex;  System.out.println("rowindex:"+rowIndex2);
    	}  		
}
class BtE extends JFrame implements ActionListener,ListSelectionListener
{
	DBCollection coll1,coll2;
	DBCursor cu1,cu2;
	Mongo mongo;
	DB db;
	JLabel label;
	JTable tab;
	JButton b1,b2,b3,b4;
	JFrame jf;
	ImageIcon img;
	JLabel Jimg;
	JLayeredPane Jlp;
	int rowIndex2=0;
	int rowIndex=0;
	GridBagConstraints c,c1;
	JPanel p,p1;
	Object o=null,o1=null,o2=null,o3=null,o4=null,o5=null,o6,o7;
		 DBObject db1,db2;
	Object ot1=null,ot2=null,ot3=null,ot4=null,ot5=null,ot6=null,ot8=null,ot7=null;
	int p2=0;
	int itr=1;
	BtE()
	{
		try
		{
			mongo=new Mongo("localhost",27017);
			db=mongo.getDB("test");
		}catch(Exception re){}

		coll1=db.getCollection("group22");
		coll2=db.getCollection("EBt");
		
		cu1=coll1.find().sort(new BasicDBObject("sr",1));
		cu2=coll2.find();
		try
		{
			while(cu2.hasNext())
			{
				coll2.remove(cu2.next());
			}
		}
		catch(Exception ee){}
		try
		{
			while(cu1.hasNext())
			{
				db1=cu1.next();
				
				o1=db1.get("group_name");
				o2=db1.get("rollno");
				o3=db1.get("name");
				o4=db1.get("cpi");
				o5=db1.get("avg_cpi");
				
				BasicDBObject docE=new BasicDBObject();
				docE.put("sr",itr);
				docE.put("group_name",o1);
				docE.put("rollno",o2);
				docE.put("name",o3);
				docE.put("cpi",o4);
				docE.put("avg_cpi",o5);
				docE.put("I_marks",o6);
				docE.put("E_marks",o7);
				
				docE.put("Total",o);

				coll2.insert(docE);
				itr++;
			}
		}catch(Exception eee){}	
	}
	public void Etable()
	{	
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(15,15,15,15);
		c1=new GridBagConstraints();

		p=new JPanel();
		p.setLayout(new GridBagLayout());
		p1=new JPanel();
		p1.setLayout(new GridLayout());
	
		Object[][] data=null;
		Object[] columns={"Group-Name","Rollno","Name","cpi","Averagecpi","I_marks","E_marks","Total"};
		DefaultTableModel dTable=new DefaultTableModel(data,columns);
		tab=new JTable(dTable);
        		jf=new JFrame();
		b1=new JButton("I_MARKS");
		b2=new JButton("E_MARKS");
		b3=new JButton("BACK");
		b4=new JButton("FINISH");
	             
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		ListSelectionModel selectionModel = tab.getSelectionModel();  
	        	selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        		selectionModel.addListSelectionListener(this);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		JScrollPane scrollpane=new JScrollPane(tab);
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b1,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b2,c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b3,c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b4,c);

		
		p1.add(scrollpane);

		jf.setSize(1500,1200);
	
		jf.setLayout(null);
		p1.setBounds(50,20,990,650);
		p.setBounds(1100,0,140,700);
		jf.add(p1);
		jf.add(p);
		jf.show();

		Object tmprow[];
		DBObject db1;
		cu2=coll2.find().sort(new BasicDBObject("sr",1));
		try
		{
			while(cu2.hasNext())
			{
				db1=cu2.next();
				o1=db1.get("group_name");
				o2=db1.get("rollno");
				o3=db1.get("name");
				o4=db1.get("cpi");
				o5=db1.get("avg_cpi");
				o6=db1.get("I_marks");
				o7=db1.get("E_marks");
				o=db1.get("Total");
				System.out.println("gh"+o1);
				tmprow=new Object[]{o1,o2,o3,o4,o5,o6,o7,o};
				dTable.addRow(tmprow);
			}
		}catch(Exception ae){}
	}	
	public void actionPerformed(ActionEvent e)
	{
		String mrk=null;
		String pr;
		int tot=0,tmptot;
		JButton b=(JButton)e.getSource();
		if(b==b1)
		{
			try
			{
				Object ot1=tab.getValueAt(rowIndex2,0);
				Object ot2=tab.getValueAt(rowIndex2,1);
				
				Object ot3=tab.getValueAt(rowIndex2,2);
				
				Object ot4=tab.getValueAt(rowIndex2,3);
				Object ot5=tab.getValueAt(rowIndex2,4);
				Object ot6=tab.getValueAt(rowIndex2,5);
				Object ot7=tab.getValueAt(rowIndex2,6);
				Object ot8=tab.getValueAt(rowIndex2,7);
			
				boolean btf=false;
				int flg=0;
				
				while(!btf)
				{
					 mrk=JOptionPane.showInputDialog(jf,"Enter Internal marks of selected student",ot6);
					int m=Integer.parseInt(mrk);
					if(m<1 || m>50)
						JOptionPane.showMessageDialog(this,"Valid Input: Internal marks (1-50)","Error",JOptionPane.ERROR_MESSAGE);
					else
						btf=true;
				}
				tot=tot+Integer.parseInt(mrk);
				BasicDBObject docE=new BasicDBObject();
			
				BasicDBObject qry=new BasicDBObject();
				qry.put("rollno",ot2);
				DBCursor tmpcur=coll2.find(qry);
				DBObject db123=tmpcur.next();
				Object sro=db123.get("sr");
			
				BasicDBObject newDocument=new BasicDBObject();
				newDocument.put("sr",sro);
				newDocument.put("group_name",ot1);
				newDocument.put("rollno",ot2);
				newDocument.put("name",ot3);
				newDocument.put("cpi",ot4);
				newDocument.put("avg_cpi",ot5);
				newDocument.put("I_marks",mrk);
				newDocument.put("E_marks",ot7);
				newDocument.put("Total",tot);
			
				coll2.update(new BasicDBObject().append("rollno",ot2),newDocument);
				jf.setVisible(false);
				Etable();
			}
			catch(Exception e1){}
		}
		if(b==b2)
		{
			try
			{
				Object ot1=tab.getValueAt(rowIndex2,0);
				Object ot2=tab.getValueAt(rowIndex2,1);
				
				Object ot3=tab.getValueAt(rowIndex2,2);
				
				Object ot4=tab.getValueAt(rowIndex2,3);
				Object ot5=tab.getValueAt(rowIndex2,4);
				Object ot6=tab.getValueAt(rowIndex2,5);
				Object ot7=tab.getValueAt(rowIndex2,6);
				Object ot8=tab.getValueAt(rowIndex2,7);
				pr=ot8.toString();
				tmptot=Integer.parseInt(pr);
				boolean btf=false;
				int flg=0;
				
				while(!btf)
				{
					mrk=JOptionPane.showInputDialog(jf,"Enter Internal marks of selected student",ot7);
					int m=Integer.parseInt(mrk);
					if(m<1 || m>50)
						JOptionPane.showMessageDialog(this,"Valid Input: Internal marks (1-50)","Error",JOptionPane.ERROR_MESSAGE);
					else
						btf=true;
				}
				tot=tmptot+tot+Integer.parseInt(mrk);
				BasicDBObject docE=new BasicDBObject();
			
				BasicDBObject qry=new BasicDBObject();
				qry.put("rollno",ot2);
				DBCursor tmpcur=coll2.find(qry);
				DBObject db123=tmpcur.next();
				Object sro=db123.get("sr");
			
				BasicDBObject newDocument=new BasicDBObject();
				newDocument.put("sr",sro);
				newDocument.put("group_name",ot1);
				newDocument.put("rollno",ot2);
				newDocument.put("name",ot3);
				newDocument.put("cpi",ot4);
				newDocument.put("avg_cpi",ot5);
				newDocument.put("I_marks",ot6);
				newDocument.put("E_marks",mrk);
				newDocument.put("Total",tot);
			
				coll2.update(new BasicDBObject().append("rollno",ot2),newDocument);
				jf.setVisible(false);
				Etable();
			}
			catch(Exception e1){}	
		}
		if(b==b3)
		{
			jf.setVisible(false);
			new Gui2();
		}
		if(b==b4)
		{
			jf.setVisible(false);
			new Gui11();
		}
	}
	public void valueChanged(ListSelectionEvent e)  
    	{  
		if(!e.getValueIsAdjusting())
		{
			ListSelectionModel model=tab.getSelectionModel();
			int lead=model.getLeadSelectionIndex();
			displayRowValues(lead);
		}
       	}	  
    	private void displayRowValues(int rowIndex)  
      	{
		int columns=tab.getColumnCount();
		rowIndex2=rowIndex;  System.out.println("rowindex:"+rowIndex2);
    	}  		
}
class Gui3 extends JFrame implements ActionListener
{
	ImageIcon img;
	JComboBox jc;
	JButton b1,b2;
	JLabel Jimg,l1;
	JLayeredPane Jlp;
	public Object item;
	public Gui3()
	{
		super("Select Class");
		setSize(430,400);
		setLocation(450,150);
		setVisible(true);
		setLayout(null);

		Jlp=getLayeredPane();
		
		img=new ImageIcon("class.jpg");
		Jimg=new JLabel(img);
		Jimg.setBounds(0,0,430,400);

		b1=new JButton("NEXT");
		b1.setBounds(120,200,150,35);
		b1.addActionListener(this);
	
		l1=new JLabel("Select Class");
		l1.setFont(new Font("Algerian",1,25));
		l1.setForeground(Color.WHITE);

		l1.setBounds(50,100,200,40);
		jc= new JComboBox();
		jc.addItem("S.Y");
		jc.addItem("T.Y");
		jc.addItem("B.Tech");
		jc.setBackground(Color.WHITE);
		jc.setBounds(250, 105,80, 25);
		jc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	
		Jlp.add(l1,new Integer(2));
		Jlp.add(b1,new Integer(2));
		Jlp.add(jc,new Integer(2));
		Jlp.add(Jimg,new Integer(2));
	}
	public void actionPerformed(ActionEvent e)
	{
		item=jc.getSelectedItem();
		String c=item.toString();
		
		System.out.println("item:"+item);
		if(item.equals("S.Y"))
		{
			try
			{
				new Fy(c);
				show(false);
			}
			catch(Exception e2)
			{
				e2.getMessage();
			}
		}
		else if(item.equals("T.Y"))
		{
			try
			{
				new Sy(c);
				show(false);
			}
			catch(Exception e2)
			{
				e2.getMessage();
			}
		}
		else if(item.equals("B.Tech"))
		{
			try
			{
				new Ty(c);
				show(false);
			}
			catch(Exception e2)
			{
				e2.getMessage();
			}
		}
	}
}
class Gui7 extends JFrame implements ActionListener
{	
	ImageIcon img;
	JLabel Jimg,l1,l2,l3,l4,l5,l6,l7;
	JButton b1,b2;
	JTextField t1,t2,t3,t4;
	double avg2,min,max;
	String clss=null;
	JPasswordField Jpw,Jpw2;
	JLayeredPane Jlp;
	public Gui7(double avg2,String cls)
	{
		super("Criteria");
		setSize(500,500);
		setLocation(450,100);
		setVisible(true);
		setLayout(null);
		min=avg2-0.5;
		max=avg2+0.5;
		clss=cls;
		Jlp=getLayeredPane();
	
		b1=new JButton("CONTINUE");
		b2=new JButton("RESET");
		b1.setBounds(90,375,100,30);
		b2.setBounds(280,375,100,30);
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		l1=new JLabel("Average Pointer     :");
		l1.setFont(new Font("Times New Roman",1,16));
		l1.setForeground(Color.BLACK);

		l2=new JLabel("No. of Students     :");
		l2.setFont(new Font("Times New Roman",1,16));
		l2.setForeground(Color.BLACK);

		l3=new JLabel("in each group");
		l3.setFont(new Font("Times New Roman",1,16));
		l3.setForeground(Color.BLACK);

		
		l6=new JLabel("Criteria:  MIN      : ");
		l6.setFont(new Font("Times New Roman",1,16));
		l6.setForeground(Color.BLACK);


		l7=new JLabel("         MAX        :");
		l7.setFont(new Font("Times New Roman",1,16));
		l7.setForeground(Color.BLACK);

		t1=new JTextField(12);
		t2=new JTextField("3");
		t3=new JTextField(Double.toString(min));
		t4=new JTextField(Double.toString(max));
		String val=Double.toString(avg2);
		t1.setText(val);
		Jpw=new JPasswordField(20);
	
		l1.setBounds(110,100,210,40);
		l2.setBounds(110,150,190,40);
		l3.setBounds(110,165,190,40);
		l6.setBounds(110,210,390,40);
		l7.setBounds(125,260,390,40);
		t1.setBounds(270,110,65,25);
		t2.setBounds(270,165,65,25);
		t3.setBounds(270,217,65,25);
		t4.setBounds(270,267,65,25);
		Jpw.setBounds(160,60,90,25);
		
		Jlp.add(l1,new Integer(2));
		Jlp.add(l2,new Integer(2));
		Jlp.add(l3,new Integer(2));
		Jlp.add(l6,new Integer(2));
		Jlp.add(l7,new Integer(2));
		Jlp.add(t1,new Integer(2));
		Jlp.add(t2,new Integer(2));
		Jlp.add(t3,new Integer(2));
		Jlp.add(t4,new Integer(2));
		Jlp.add(b1,new Integer(2));
		Jlp.add(b2,new Integer(2));
	
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		boolean bt=false;
		boolean bt1=false;

		if(b==b1)
		{
			String itm=t2.getText();
			int stu=Integer.parseInt(itm);
			String itm1=t3.getText();
			String itm2=t4.getText();
			String itm3=t1.getText();
			double avg1=Double.parseDouble(itm3);
			double min1=Double.parseDouble(itm1);
			double max1=Double.parseDouble(itm2);
			while(!bt)
			{
			if(stu<3 || stu>3)
			{
				JOptionPane.showMessageDialog(this,"Number or students in a group should be 3 or 4 ","Error",JOptionPane.ERROR_MESSAGE);	
				t2.setText("");
				t2.requestFocus();
				String tmp=JOptionPane.showInputDialog(this, "Enter no of student");
				stu=Integer.parseInt(tmp);
			}
			else
				bt=true;
			}
			while(!bt1)
			{
			if(min1<0.0 || max1>9.9)
			{
				JOptionPane.showMessageDialog(this,"Min or max range is out of range ","Error",JOptionPane.ERROR_MESSAGE);		
				t3.setText("");
				t4.setText("");
				t3.requestFocus();
				String tmp=JOptionPane.showInputDialog(this, "Enter min");
				String tmp1=JOptionPane.showInputDialog(this, "Enter max");
				min1=Double.parseDouble(tmp);
				max1=Double.parseDouble(tmp1);
			}
			else
				bt1=true;
			}
			if(clss.equals("S.Y") || clss.equals("T.Y"))
			{
				try
				{
					this.setVisible(false);
					new FacTable(clss,avg1,min1,max1);
					
				}
				catch(Exception ecs)
				{
					ecs.getMessage();
				}
			}
			else if(clss.equals("B.Tech"))
			{
				try
				{
					this.setVisible(false);
					new SenTable(clss,avg1,min1,max1);
					new SenTable();
				}
				catch(Exception ecs)
				{
					ecs.getMessage();
				}	
			}
		}
		if(b==b2)
		{
			t1.setText("");
			Jpw.setText("");
			t1.requestFocus();
			t2.setText("");
			t3.setText("");
			t4.setText("");
		}
	}
}
class Fy extends JFrame implements ListSelectionListener,ActionListener
{ 	
	JLabel label;
	JTable tab;
	JButton b1,b2,b3,b4,b5;
	JFrame jf;
	ImageIcon img;
	JLabel Jimg;
	JLayeredPane Jlp;

	GridBagConstraints c,c1;
	JPanel p,p1;
	DB db;
	DBCollection coll;
	int rowindex2=0;	
	double d;
	double avg; 
	String cls2;
	int rowIndex;
	Fy(String cls)
	{
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.EAST;
		c.insets=new Insets(15,15,15,15);
		c1=new GridBagConstraints();

		p=new JPanel();
		p.setLayout(new GridBagLayout());
		p1=new JPanel();
		p1.setLayout(new GridLayout());

		cls2=cls;
		Object[][] data=null;
		Object[] columns={"rollno","name","cpi"};
		label=new JLabel();
		DefaultTableModel dTable=new DefaultTableModel(data,columns);
	
		tab=new JTable(dTable);
        	jf=new JFrame();
		b1=new JButton("ADD");
		b2=new JButton("REMOVE");
		b3=new JButton("UPDATE");
		b4=new JButton("CONTINUE");
	                   b5=new JButton("FIND");
		
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		ListSelectionModel selectionModel = tab.getSelectionModel();  
	        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        	selectionModel.addListSelectionListener(this);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		Mongo mongo=null;
		try
		{
			mongo=new Mongo("localhost",27017);

		}
		catch(Exception e)
		{
			e.getMessage();
		}
		db=mongo.getDB("test");
		coll=db.getCollection("fy");
		BasicDBObject document=new BasicDBObject();
		DBCursor cursor=coll.find();	
		DBObject db1;
		Object tmprow[];
		Object o,o1,o2;
		try
		{
			while(cursor.hasNext())
			{
				db1=cursor.next();
				o=db1.get("rollno");
				o.toString();
				o1=db1.get("name");
				o1.toString();
				o2=db1.get("fypi");
				o2.toString();
				tmprow=new Object[]{o,o1,o2};
				dTable.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}
		tab.setBounds(50,50,60,80);
		JScrollPane scrollpane=new JScrollPane(tab);
		
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b1,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		
		p.add(b2,c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		
		p.add(b3,c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		
		p.add(b4,c);

		p1.add(scrollpane);
		jf.setSize(1500,1200);
	
		jf.setLayout(null);
		p1.setBounds(50,20,990,650);
		p.setBounds(1100,0,140,700);
		jf.add(p1);
		jf.add(p);
		jf.show();


	}
	public void actionPerformed(ActionEvent ae)
	{
		String nm=null;
		String rollno=null;
		String point=null;
		JButton tmp=(JButton)ae.getSource();
		if(tmp==b1)
		{
			boolean btf=false;
			boolean btf2=false;
			boolean btf3=false;
			while(!btf)
			{
				rollno = JOptionPane.showInputDialog(jf, "Enter roll no of student");
			
				BasicDBObject query = new BasicDBObject();

			        query.put("rollno",rollno);
		
			        DBCursor cur= coll.find(query);

				if(rollno.equals("0") || cur.length()>0)
				{
					JOptionPane.showMessageDialog(this,"Rollnumber already exist or you have inputed invalid rollnumber ","Error",JOptionPane.ERROR_MESSAGE);
			
				}
				else
					btf=true;
			}
			while(!btf2)
			{
				nm=JOptionPane.showInputDialog(jf, "Enter name of student");
				if(nm.equals(null))
				{
					JOptionPane.showMessageDialog(this,"Input Valid name","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf2=true;	
			}
			while(!btf3)
			{
				point=JOptionPane.showInputDialog(jf, "Enter cpi student");
				d=Double.parseDouble(point);	
				if(d<1 || d>10)
				{
					JOptionPane.showMessageDialog(this,"Input Valid cpi(0.1-10)","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf3=true;
			}				
			BasicDBObject doc=new BasicDBObject();
			doc.put("rollno",rollno);
			doc.put("name",nm);
			doc.put("fypi",d);
			coll=db.getCollection("fy");
			coll.insert(doc);	
				
			try
			{
				jf.show(false);
				new Fy(cls2);
			}
			catch(Exception eee)
			{
				eee.getMessage();
			}
		}
		if(tmp==b2)
		{
			Object ot = tab.getValueAt(rowindex2, 0);
			ot.toString(); 
			BasicDBObject document = new BasicDBObject();
			document.put("rollno", ot);
			coll.remove(document);
			try
			{
				jf.show(false);
				new Fy(cls2);				
			}
			catch(Exception ce)
			{
				ce.getMessage();
			}		
		}

		if(tmp==b3)
		{
			Object ot = tab.getValueAt(rowindex2, 0);
			String roll=ot.toString();
			Object ot1 = tab.getValueAt(rowindex2, 1);
			ot1.toString();
			Object ot2 = tab.getValueAt(rowindex2, 2);
			ot2.toString();
			
			boolean btf=false;
			boolean btf2=false;
			boolean btf3=false;
			int flg=0;
			while(!btf)
			{
				rollno= JOptionPane.showInputDialog(jf, "Enter roll no of student",ot);
				
				BasicDBObject query = new BasicDBObject();
				 query.put("rollno",new BasicDBObject("$ne",roll));
		
			       	 DBCursor cur= coll.find(query);
				try
				{
					while(cur.hasNext())
					{
						DBObject db1;
						db1=cur.next();
						Object or=db1.get("rollno");
						or.toString();
						if(rollno.equals(or))
						{
							flg=0;
							break;
						}
						else
							flg=1;
					}
				}
				catch(Exception ie)
				{
					ie.getMessage();
				}
				if(rollno.equals("0") || flg==0)
				{
					JOptionPane.showMessageDialog(this,"Rollnumber already exist or you have inputed invalid 							rollnumber ","Error",JOptionPane.ERROR_MESSAGE);
			
				}
				else
					btf=true;
			}
			while(!btf2)
			{
				nm=JOptionPane.showInputDialog(jf, "Enter name of student",ot1);
				if(nm.equals(null))
				{
					JOptionPane.showMessageDialog(this,"Input Valid 	name","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf2=true;	
			}
			while(!btf3)
			{
				point=JOptionPane.showInputDialog(jf, "Enter cpi student",ot2);
				d=Double.parseDouble(point);	
				if(d<1 || d>10)
				{
					JOptionPane.showMessageDialog(this,"Input Valid cpi(0.1-10)","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf3=true;
			}

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("rollno",rollno);
			newDocument.put("name",nm);
			newDocument.put("fypi",d);
			coll.update(new BasicDBObject().append("rollno",roll), newDocument);
			
			try
			{
				jf.show(false);
				new Fy(cls2);				
			}
			catch(Exception ce2)
			{
				ce2.getMessage();
			}
		}

		if(tmp==b4)
		{
			DBObject db1;
			
			DBCursor cur2=coll.find();
			Object fpi;
			double pt;
			double tot=0.0;
			avg=0.0;
			int cnt=0;
			
			try
			{
				while(cur2.hasNext())
				{
					db1=cur2.next();
					fpi=db1.get("fypi");
					pt=Double.parseDouble(fpi.toString());
					tot+=pt;
				}
				cnt=cur2.count();
				avg=tot/cnt;
				avg=Math.round( avg * 100.0 ) / 100.0;
			}
			catch(Exception ec)
			{
				ec.getMessage();
			}
			jf.setVisible(false);
			new Gui7(avg,cls2);
		}
							

	}
	public void valueChanged(ListSelectionEvent e)  
    	{  
	        	if(!e.getValueIsAdjusting())  
        		{  
            			ListSelectionModel model = tab.getSelectionModel();  
            			int lead = model.getLeadSelectionIndex();  
          	 	 	displayRowValues(lead);  
        		}	  
    	}	  
   	private void displayRowValues(int rowIndex)  	
                 {  
    	    int columns = tab.getColumnCount();  
	    b2.enable();
	    b3.enable();
	    rowindex2=rowIndex;	
    	}  
  
}
class Ty extends JFrame implements ListSelectionListener,ActionListener
{ 
	JLabel label;
	JTable tab;
	JButton b1;
	JButton b2;
	JButton b3;	
	JButton b4;
	JFrame jf;
	GridBagConstraints c,c1;
	JPanel p,p1;

	DB db;
	DBCollection coll;
	int rowindex2=0;	
	double d,avg;
	int rowIndex;
	String cls2;
	Ty(String cls)
	{
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(15,15,15,15);
		c1=new GridBagConstraints();

		p=new JPanel();
		p.setLayout(new GridBagLayout());
		p1=new JPanel();
		p1.setLayout(new GridLayout());

		cls2=cls;
		Object[][] data=null;
		Object[] columns={"rollno","name","cpi"};
		label=new JLabel();
		DefaultTableModel dTable=new DefaultTableModel(data,columns);
		 
		tab=new JTable(dTable);
        	jf=new JFrame();
		b1=new JButton("ADD");
		b2=new JButton("REMOVE");
		b3=new JButton("UPDATE");
		b4=new JButton("CONTINUE");
	
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		ListSelectionModel selectionModel = tab.getSelectionModel();  
	        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        	selectionModel.addListSelectionListener(this);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		Mongo mongo=null;
		try
		{
			mongo=new Mongo("localhost",27017);

		}
		catch(Exception e)
		{
			e.getMessage();
		}
		db=mongo.getDB("test");
		coll=db.getCollection("third");
		BasicDBObject document=new BasicDBObject();
		DBCursor cursor=coll.find();	
		DBObject db1;
		Object tmprow[];
		Object o,o1,o2;
		try
		{
			while(cursor.hasNext())
			{
				db1=cursor.next();
				o=db1.get("rollno");
				o.toString();
				o1=db1.get("name");
				o1.toString();
				o2=db1.get("cpi");
				o2.toString();
				tmprow=new Object[]{o,o1,o2};
				dTable.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}

		tab.setBounds(50,50,60,80);
		JScrollPane scrollpane=new JScrollPane(tab);
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.gridheight=1;
	
		p.add(b1,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		
		p.add(b2,c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		
		p.add(b3,c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		
		p.add(b4,c);

		p1.add(scrollpane);
		jf.setSize(1500,1200);
	
		jf.setLayout(null);
		p1.setBounds(50,20,990,650);
		p.setBounds(1100,0,140,700);
		jf.add(p1);
		jf.add(p);
		jf.show();


}
	public void actionPerformed(ActionEvent ae)
	{
		String nm=null;
		String rollno=null;
		String point=null;
		JButton tmp=(JButton)ae.getSource();
		if(tmp==b1)
		{
			boolean btf=false;
			boolean btf2=false;
			boolean btf3=false;
			while(!btf)
			{
				rollno = JOptionPane.showInputDialog(jf, "Enter roll no of student");
			
				BasicDBObject query = new BasicDBObject();

			        query.put("rollno",rollno);
		
			        DBCursor cur= coll.find(query);

				if(rollno.equals("0") || cur.length()>0)
				{
					JOptionPane.showMessageDialog(this,"Rollnumber already exist or you have inputed invalid rollnumber ","Error",JOptionPane.ERROR_MESSAGE);
			
				}
				else
					btf=true;
			}
			while(!btf2)
			{
				nm=JOptionPane.showInputDialog(jf, "Enter name of student");
				if(nm.equals(null))
				{
					JOptionPane.showMessageDialog(this,"Input Valid name","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf2=true;	
			}
			while(!btf3)
			{
				point=JOptionPane.showInputDialog(jf, "Enter cpi student");
				d=Double.parseDouble(point);	
				if(d<1 || d>10)
				{
					JOptionPane.showMessageDialog(this,"Input Valid cpi(0.1-10)","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf3=true;
			}				
			BasicDBObject doc=new BasicDBObject();
			doc.put("rollno",rollno);
			doc.put("name",nm);
			doc.put("cpi",d);
			coll=db.getCollection("third");
			coll.insert(doc);	
				
			try
			{
				jf.show(false);
				new Ty(cls2);
			}
			catch(Exception eee)
			{
				eee.getMessage();
			}
		}
		if(tmp==b2)
		{
			Object ot = tab.getValueAt(rowindex2, 0);
			ot.toString(); 
			BasicDBObject document = new BasicDBObject();
			document.put("rollno", ot);
			coll.remove(document);
			try
			{
				jf.show(false);
				new Ty(cls2);				
			}
			catch(Exception ce)
			{
				ce.getMessage();
			}
            		
				
		}

		if(tmp==b3)
		{
			Object ot = tab.getValueAt(rowindex2, 0);
			String roll=ot.toString();
			Object ot1 = tab.getValueAt(rowindex2, 1);
			ot1.toString();
			Object ot2 = tab.getValueAt(rowindex2, 2);
			ot2.toString();
			
			boolean btf=false;
			boolean btf2=false;
			boolean btf3=false;
			int flg=0;
			while(!btf)
			{
				rollno= JOptionPane.showInputDialog(jf, "Enter roll no of student",ot);
				
				BasicDBObject query = new BasicDBObject();
					
				
			        query.put("rollno",new BasicDBObject("$ne",roll));
		
			        DBCursor cur= coll.find(query);


				try
				{
					while(cur.hasNext())
					{
						DBObject db1;
						db1=cur.next();
						Object or=db1.get("rollno");
						or.toString();
						if(rollno.equals(or))
						{
							flg=0;
							break;
						}
						else
							flg=1;
							
					}
				}
				catch(Exception ie)
				{
					ie.getMessage();
				}
				if(rollno.equals("0") || flg==0)
				{
					JOptionPane.showMessageDialog(this,"Rollnumber already exist or you have inputed invalid rollnumber ","Error",JOptionPane.ERROR_MESSAGE);
			
				}
				else
					btf=true;
			}
			while(!btf2)
			{
				nm=JOptionPane.showInputDialog(jf, "Enter name of student",ot1);
				if(nm.equals(null))
				{
					JOptionPane.showMessageDialog(this,"Input Valid name","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf2=true;	
			}
			while(!btf3)
			{
				point=JOptionPane.showInputDialog(jf, "Enter cpi student",ot2);
				d=Double.parseDouble(point);	
				if(d<1 || d>10)
				{
					JOptionPane.showMessageDialog(this,"Input Valid cpi(0.1-10)","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf3=true;
			}

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("rollno",rollno);
			newDocument.put("name",nm);
			newDocument.put("cpi",point);
			coll.update(new BasicDBObject().append("rollno",roll), newDocument);
			
			try
			{
				jf.show(false);
				new Ty(cls2);				
			}
			catch(Exception ce2)
			{
				ce2.getMessage();
			}
		}
		if(tmp==b4)
		{
			DBObject db1;
			DBCursor cur2=coll.find();
			Object fpi;
			double pt;
			double tot=0.0;
			avg=0.0;
			int cnt=0;
			try
			{
				while(cur2.hasNext())
				{
					db1=cur2.next();
					fpi=db1.get("cpi");
					pt=Double.parseDouble(fpi.toString());
					System.out.println(pt);
					tot+=pt;
					
				}
				cnt=cur2.count();
				avg=tot/cnt;
				avg=Math.round( avg * 100.0 ) / 100.0;
				System.out.println("avg:"+avg);
			}
			catch(Exception ec)
			{
				ec.getMessage();
			}
			jf.setVisible(false);
			new Gui7(avg,cls2);
		}
	}
	public void valueChanged(ListSelectionEvent e)  
    	{  
        	if(!e.getValueIsAdjusting())  
        	{  
            		ListSelectionModel model = tab.getSelectionModel();  
            		int lead = model.getLeadSelectionIndex();  
           	 	displayRowValues(lead);  
        	}	  
    	}	  
   
   	private void displayRowValues(int rowIndex)  
       	 {  
    	    int columns = tab.getColumnCount();  
	    b2.enable();
	    b3.enable();
	    rowindex2=rowIndex;	
    	}  
  
}

class Sy extends JFrame implements ListSelectionListener,ActionListener
{ 
	JLabel label;
	JTable tab;
	JButton b1;
	JButton b2;
	JButton b3;	
	JButton b4;
	JFrame jf;
	GridBagConstraints c,c1;
	JPanel p,p1;

	DB db;
	DBCollection coll;
	int rowindex2=0;	
	double d,avg;
	int rowIndex;
	String cls2;
	Sy(String cls)
	{
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(15,15,15,15);
		c1=new GridBagConstraints();

		p=new JPanel();
		p.setLayout(new GridBagLayout());
		p1=new JPanel();
		p1.setLayout(new GridLayout());


		cls2=cls;
		Object[][] data=null;
		Object[] columns={"rollno","name","cpi"};
		label=new JLabel();
		DefaultTableModel dTable=new DefaultTableModel(data,columns);
		
		tab=new JTable(dTable);
        	jf=new JFrame();
		b1=new JButton("ADD");
		b2=new JButton("REMOVE");
		b3=new JButton("UPDATE");
		b4=new JButton("CONTINUE");
		
	
		
		
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		ListSelectionModel selectionModel = tab.getSelectionModel();  
	        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        	selectionModel.addListSelectionListener(this);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		Mongo mongo=null;
		try
		{
			mongo=new Mongo("localhost",27017);

		}
		catch(Exception e)
		{
			e.getMessage();
		}
		db=mongo.getDB("test");
		coll=db.getCollection("sy");
		BasicDBObject document=new BasicDBObject();
		DBCursor cursor=coll.find();	
		DBObject db1;
		Object tmprow[];
		Object o,o1,o2;
		try
		{
			while(cursor.hasNext())
			{
				db1=cursor.next();
				o=db1.get("rollno");
				o.toString();
				o1=db1.get("name");
				o1.toString();
				o2=db1.get("cpi");
				o2.toString();
				tmprow=new Object[]{o,o1,o2};
				dTable.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}

		tab.setBounds(50,50,60,80);
		JScrollPane scrollpane=new JScrollPane(tab);
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b1,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=2;
		c.gridheight=1;
		p.add(b2,c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=2;
		c.gridheight=1;
		p.add(b3,c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=2;
		c.gridheight=1;
		p.add(b4,c);

		p1.add(scrollpane);
		jf.setSize(1500,1200);
	
		jf.setLayout(null);
		p1.setBounds(50,20,990,650);
		p.setBounds(1100,0,140,700);
		jf.add(p1);
		jf.add(p);
		jf.show();


	}
	public void actionPerformed(ActionEvent ae)
	{
		String nm=null;
		String rollno=null;
		String point=null;
		JButton tmp=(JButton)ae.getSource();
		if(tmp==b1)
		{
			boolean btf=false;
			boolean btf2=false;
			boolean btf3=false;
			while(!btf)
			{
				rollno = JOptionPane.showInputDialog(jf, "Enter roll no of student");
			
				BasicDBObject query = new BasicDBObject();

			        query.put("rollno",rollno);
		
			        DBCursor cur= coll.find(query);

				if(rollno.equals("0") || cur.length()>0)
				{
					JOptionPane.showMessageDialog(this,"Rollnumber already exist or you have inputed invalid rollnumber ","Error",JOptionPane.ERROR_MESSAGE);
			
				}
				else
					btf=true;
			}
			while(!btf2)
			{
				nm=JOptionPane.showInputDialog(jf, "Enter name of student");
				if(nm.equals(null))
				{
					JOptionPane.showMessageDialog(this,"Input Valid name","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf2=true;	
			}
			while(!btf3)
			{
				point=JOptionPane.showInputDialog(jf, "Enter cpi student");
				d=Double.parseDouble(point);	
				if(d<1 || d>10)
				{
					JOptionPane.showMessageDialog(this,"Input Valid cpi(0.1-10)","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf3=true;
			}				
			BasicDBObject doc=new BasicDBObject();
			doc.put("rollno",rollno);
			doc.put("name",nm);
			doc.put("cpi",d);
			coll=db.getCollection("sy");
			coll.insert(doc);	
				
			try
			{
				jf.show(false);
				new Sy(cls2);
			}
			catch(Exception eee)
			{
				eee.getMessage();
			}
		}
		if(tmp==b2)
		{
			Object ot = tab.getValueAt(rowindex2, 0);
			ot.toString(); 
			BasicDBObject document = new BasicDBObject();
			document.put("rollno", ot);
			coll.remove(document);
			try
			{
				jf.show(false);
				new Sy(cls2);				
			}
			catch(Exception ce)
			{
				ce.getMessage();
			}
            		
				
		}

		if(tmp==b3)
		{
			Object ot = tab.getValueAt(rowindex2, 0);
			String roll=ot.toString();
			Object ot1 = tab.getValueAt(rowindex2, 1);
			ot1.toString();
			Object ot2 = tab.getValueAt(rowindex2, 2);
			ot2.toString();
			
			boolean btf=false;
			boolean btf2=false;
			boolean btf3=false;
			int flg=0;
			while(!btf)
			{
				rollno= JOptionPane.showInputDialog(jf, "Enter roll no of student",ot);
				
				BasicDBObject query = new BasicDBObject();
					
				
			        query.put("rollno",new BasicDBObject("$ne",roll));
		
			        DBCursor cur= coll.find(query);


				try
				{
					while(cur.hasNext())
					{
						DBObject db1;
						db1=cur.next();
						Object or=db1.get("rollno");
						or.toString();
						if(rollno.equals(or))
						{
							flg=0;
							break;
						}
						else
							flg=1;
							
					}
				}
				catch(Exception ie)
				{
					ie.getMessage();
				}
				if(rollno.equals("0") || flg==0)
				{
					JOptionPane.showMessageDialog(this,"Rollnumber already exist or you have inputed invalid rollnumber ","Error",JOptionPane.ERROR_MESSAGE);
			
				}
				else
					btf=true;
	
				
			}
			while(!btf2)
			{
				nm=JOptionPane.showInputDialog(jf, "Enter name of student",ot1);
				if(nm.equals(null))
				{
					JOptionPane.showMessageDialog(this,"Input Valid name","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf2=true;	
			}
			while(!btf3)
			{
				point=JOptionPane.showInputDialog(jf, "Enter cpi student",ot2);
				d=Double.parseDouble(point);	
				if(d<1 || d>10)
				{
					JOptionPane.showMessageDialog(this,"Input Valid cpi(0.1-10)","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					btf3=true;
			}

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("rollno",rollno);
			newDocument.put("name",nm);
			newDocument.put("cpi",point);
			coll.update(new BasicDBObject().append("rollno",roll), newDocument);
			
			try
			{
				jf.show(false);
				new Sy(cls2);				
			}
			catch(Exception ce2)
			{
				ce2.getMessage();
			}
		}
		if(tmp==b4)
		{
			
			DBObject db1;
			DBCursor cur2=coll.find();
			Object fpi;
			double pt;
			double tot=0.0;
			avg=0.0;
			int cnt=0;
			try
			{
				while(cur2.hasNext())
				{
					db1=cur2.next();
					fpi=db1.get("cpi");
					pt=Double.parseDouble(fpi.toString());
					tot+=pt;
					
				}
				cnt=cur2.count();
				avg=tot/cnt;
				avg=Math.round( avg * 100.0 ) / 100.0;
				System.out.println("avg:"+avg);
			}
			catch(Exception ec)
			{
				ec.getMessage();
			}
			jf.setVisible(false);
			new Gui7(avg,cls2);
		}
	}
	public void valueChanged(ListSelectionEvent e)  
    	{  
        	if(!e.getValueIsAdjusting())  
        	{  
            		ListSelectionModel model = tab.getSelectionModel();  
            		int lead = model.getLeadSelectionIndex();  
           	 	displayRowValues(lead);  
        	}	  
    	}	  
   
   	private void displayRowValues(int rowIndex)  
        {  
    	    int columns = tab.getColumnCount();  
	    b2.enable();
	    b3.enable();
	    rowindex2=rowIndex;	
    	}  
  
}

class FacTable extends JFrame implements ListSelectionListener,ActionListener 
{
	JButton b1,b2,b3,b4,b5;
	JTable tab2;
	JFrame jf2,jf3;
	JLabel l1;	
	DB db;
	DBCollection coll;
	GridBagConstraints c,c1;
	JPanel p,p1;
	int rowindex2=0;
	String cs=null;
	double a1,m1,m2;
	double ga,la;
	Mongo mongo=null;

	final static int interval=1000;
	int i,grp;
	int grp_flg=0;

	JProgressBar prg=new JProgressBar(0,20);
	
	Timer t=new Timer(interval,new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			if(i==20)
			{
				t.stop();
				prg.setString("completed!");
			
				l1.setText(""+grp+" Groups are going to form");
				
				
				b5.setEnabled(true);

				l1.setEnabled(true);
			}
			else
			{
				i=i+4;
				prg.setValue(i);
			}

		}
	}
	);

	FacTable(String cs1,double a2,double m3,double m4)
	{
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(15,15,15,15);
		c1=new GridBagConstraints();

		p=new JPanel();
		p.setLayout(new GridBagLayout());
		p1=new JPanel();
		p1.setLayout(new GridLayout());
		cs=cs1;
		a1=a2;
		m1=m3;
		m2=m4;	
		Object[][] data=null;
		Object[] columns={"Faculty-Name","Faculty-Status"};
		System.out.println("class:"+cs+"avg:"+a1+"min:"+m1+"max:"+m2);
		DefaultTableModel dTable=new DefaultTableModel(data,columns);
		
		tab2=new JTable(dTable);
        	jf2=new JFrame();
		jf3=new JFrame();
		jf3.setVisible(false);
		b1=new JButton("ADD");
		b2=new JButton("REMOVE");
		b3=new JButton("UPDATE");
		b4=new JButton("CONTINUE");
		b5=new JButton("PROCEED");
		l1=new JLabel();
		l1.setEnabled(false);
		b5.setEnabled(false);
		
	
		jf2.setDefaultCloseOperation(jf2.EXIT_ON_CLOSE);
		jf3.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		ListSelectionModel selectionModel = tab2.getSelectionModel();  
	        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        	selectionModel.addListSelectionListener(this);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		Mongo mongo=null;
		try
		{
			mongo=new Mongo("localhost",27017);

		}
		catch(Exception e)
		{
			e.getMessage();
		}
		db=mongo.getDB("test");
		coll=db.getCollection("faculty");
		BasicDBObject document=new BasicDBObject();
		DBCursor cursor=coll.find();	
		DBObject db1;
		Object tmprow[];
		Object o,o1;
		try
		{
			while(cursor.hasNext())
			{
				db1=cursor.next();
				o=db1.get("facname");
				o.toString();
				o1=db1.get("facstatus");
				o1.toString();
				
				tmprow=new Object[]{o,o1};
				dTable.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}

		JScrollPane scrollpane=new JScrollPane(tab2);
	
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b1,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=2;
		c.gridheight=1;
		p.add(b2,c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=2;
		c.gridheight=1;
		p.add(b3,c);
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=2;
		c.gridheight=1;
		p.add(b4,c);

		c.gridx=0;
		c.gridy=5;
		c.gridwidth=2;
		c.gridheight=1;
		p.add(b5,c);		
	
		p1.add(scrollpane);
		jf2.setSize(1000,600);
		jf2.setLocation(200,60);
		jf2.setLayout(null);
		p1.setBounds(60,90,500,200);
		p.setBounds(700,-50,140,700);
		jf2.add(p1);
		jf2.add(p);
		jf2.show();
		jf3.setSize(100,50);
		jf3.setLayout(null);
		jf3.setLocation(450,150);
		prg.setBounds(100,150,50,30);
		jf3.add(l1);
		jf3.add(prg);
		
		l1.setBounds(80,200,50,30);
		jf3.add(b5);
		b1.setBounds(170,200,50,30);
		jf2.show();	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String nm=null;
		char ch=' ';
		String sta=null;
		JButton tmp=(JButton)ae.getSource();
		if(tmp==b1)
		{
			boolean btf=false;
			DBCollection colls=db.getCollection("senior");
			nm = JOptionPane.showInputDialog(jf2, "Enter name of faculty");
			while(!btf)
			{
				
				 
				sta=JOptionPane.showInputDialog(jf2, "Enter faculty-status\ns-senior\nj-junior");
				ch=sta.charAt(0);
				if(ch=='s' || ch=='j' || ch=='S' || ch=='J')
					btf=true;
				else
					JOptionPane.showMessageDialog(this,"Faculty status should be S or J","Error",JOptionPane.ERROR_MESSAGE);
			}
			if(ch=='s'||ch=='S')
			{
				
				sta="senior";
				BasicDBObject newDocument = new BasicDBObject();
				newDocument.put("facname",nm);
				newDocument.put("facstatus",sta);
				colls.insert(newDocument);
			
			}
			else if(ch=='j'||ch=='J')
			{
				sta="junior";			
				BasicDBObject doc=new BasicDBObject();
				doc.put("facname",nm);
				doc.put("facstatus",sta);
				coll=db.getCollection("faculty");
				coll.insert(doc);
			}	
				
				
			try
			{
				jf2.show(false);
				new FacTable(cs,a1,m1,m2);
			}
			catch(Exception eee)
			{
				eee.getMessage();
			}

	   	}
		if(tmp==b2)
		{
			Object ot = tab2.getValueAt(rowindex2, 0);
			ot.toString(); 
			BasicDBObject document = new BasicDBObject();
			document.put("facname", ot);
			coll.remove(document);
			try
			{
				jf2.show(false);
				new FacTable(cs,a1,m1,m2);				
			}
			catch(Exception ce)
			{
				ce.getMessage();
			}	
		}

		if(tmp==b3)
		{
			Object ot = tab2.getValueAt(rowindex2, 0);
			String name=ot.toString();
			Object ot1 = tab2.getValueAt(rowindex2, 1);
			String st=ot1.toString();
			char ch1=' ';
			boolean btf=false;
			DBCollection colls=db.getCollection("senior");

			name= JOptionPane.showInputDialog(jf2, "Enter name of faculty",ot);
			while(!btf)
			{
				st=JOptionPane.showInputDialog(jf2, "Enter faculty-status\ns-senior\nj-junior");
				ch1=st.charAt(0);
				if(ch1=='s' || ch1=='j' || ch1=='S' || ch1=='J')
					btf=true;
				else
					JOptionPane.showMessageDialog(this,"Faculty status should be S or J","Error",JOptionPane.ERROR_MESSAGE);
									
			}
			if(ch1=='s'||ch1=='S')
			{	
				st="senior";
				BasicDBObject doc=new BasicDBObject();
				doc.put("facname",name);
				doc.put("facstatus",st);
				
				BasicDBObject newDocument = new BasicDBObject();
				newDocument.put("facname",name);
				newDocument.put("facstatus",st);
				colls.insert(newDocument);
				colls.update(new BasicDBObject().append("facname",ot), newDocument);
				coll.update(new BasicDBObject().append("facname",ot), doc);
			}
			else
			{
				st="junior";
				BasicDBObject doc=new BasicDBObject();
				doc.put("facname",name);
				doc.put("facstatus",st);
				coll.update(new BasicDBObject().append("facname",ot), doc);
			}
			try
			{
				jf2.show(false);
				new FacTable(cs,a1,m1,m2);				
			}
			catch(Exception ce2)
			{
				ce2.getMessage();
			}
		}
		if(tmp==b4)
		{
			jf2.setVisible(false);
			jf3.setLayout(new FlowLayout());
			
			jf3.setVisible(true);
			jf3.setSize(450,150);
			i=0;
			t.start();
			b4.setEnabled(false);
			
			prg.setValue(0);
			prg.setStringPainted(true);
			prg.setBackground(Color.ORANGE);
			prg.setString("Collecting Required Information...");

			if(cs.equals("S.Y"))
			{
				DBCursor cu1=null;
				DBCursor cu2=null;
				int cnt=1;
				DB db=null;
				DBCollection coll;
				DBCollection coll1;
				Object o,o1,o2;
				DBObject db1;
				double d;
				int x;
				BasicDBObject doc=new BasicDBObject();
				
				try
				{
					mongo=new Mongo("localhost",27017);
					db=mongo.getDB("test");
				
					coll=db.getCollection("fy");
					coll1=db.getCollection("tmp");
					
					cu1=coll1.find();
					cu2=coll.find().sort(new BasicDBObject("fypi",-1));
					try
					{
						while(cu1.hasNext())
						{
							coll1.remove(cu1.next());
						}
					}
					catch(Exception e)
					{
						e.getMessage();
					}
					try
					{
						x=cu2.count();
						grp=x/3;
						if(grp>26)
						{
							grp=26;
							grp_flg=1;
						}
						
						while(cu2.hasNext())
						{
							String count=null;
							db1=cu2.next();
							o=db1.get("rollno");
							o1=db1.get("name");
							o2=db1.get("fypi");
							String pt=o2.toString();
							d=Double.parseDouble(pt);
							
							BasicDBObject newdoc=new BasicDBObject();
							
							newdoc.put("sr",cnt);
							newdoc.put("rollno",o.toString());
							newdoc.put("name",o1.toString());
							newdoc.put("fypi",d);
							coll1.insert(newdoc);
							
							cnt++;
						}
						
					}
					catch(Exception ee)
					{
						ee.getMessage();
					}					
				}
				catch(Exception e)
				{
					e.getMessage();
				}
			}
			else if(cs.equals("T.Y"))
			{				
				DBCursor cu1=null;
				DBCursor cu2=null;
				int cnt=1;
				DB db=null;
				DBCollection coll;
				DBCollection coll1;
				Object o,o1,o2;
				DBObject db1;
				double d;
				int x;
				
				BasicDBObject doc=new BasicDBObject();
				
				try
				{
					mongo=new Mongo("localhost",27017);
					db=mongo.getDB("test");
				
					coll=db.getCollection("sy");
					coll1=db.getCollection("tmp1");
					
					cu1=coll1.find();
					cu2=coll.find().sort(new BasicDBObject("cpi",-1));
					
					try
					{
						while(cu1.hasNext())
						{
							coll1.remove(cu1.next());
						}
					}
					catch(Exception e)
					{
						e.getMessage();
					}
					try
					{
						x=cu2.count();
						grp=x/3;
						if(grp>26)
						{
							grp=26;
							grp_flg=1;
						}
						while(cu2.hasNext())
						{
							db1=cu2.next();
							o=db1.get("rollno");
							o1=db1.get("name");
							o2=db1.get("cpi");
							String pt=o2.toString();
							d=Double.parseDouble(pt);
							
							BasicDBObject newdoc=new BasicDBObject();
							newdoc.put("sr",cnt);
							newdoc.put("rollno",o.toString());
							newdoc.put("name",o1.toString());
							newdoc.put("cpi",d);
						
							coll1.insert(newdoc);
							cnt++;
						}
					
						
					}
					catch(Exception ee)
					{
						ee.getMessage();
					}	
				}
				catch(Exception e)
				{
					e.getMessage();
				}
			}
		}

		if(tmp==b5)
		{
			try
			{
				jf3.setVisible(false);
				new GroupForm(cs,m1,m2);
			}
			catch(Exception e){}
		}
	}
	public void valueChanged(ListSelectionEvent e)  
    	{  
        	if(!e.getValueIsAdjusting())  
        	{  
            		ListSelectionModel model = tab2.getSelectionModel();  
            		int lead = model.getLeadSelectionIndex();  
           	 	displayRowValues(lead);  
        	}	  
    	}	  
   
   	private void displayRowValues(int rowIndex)  
	        {  
    	    int columns = tab2.getColumnCount();  
	    b2.enable();
	    b3.enable();
	    rowindex2=rowIndex;	
    	}  
}


class SenTable extends JFrame implements ListSelectionListener,ActionListener 
{
	JButton b1,b2,b3,b4,b5;
	JTable tab2;
	GridBagConstraints c,c1;
	JPanel p,p1;
	JFrame jf2,jf3,jf;
	JLabel l1;
	JLayeredPane Jlp;	
	DB db;
	DBCollection coll;
	int rowindex2=0;
	String cs,cs2;
	double a1,m1,m2;
	Mongo mongo;
	DBCursor faccur;
	DBCollection coll1;
	final static int interval=1000;
	int i,grp;
	int grp_flg=0;

	JProgressBar prg=new JProgressBar(0,20);
	
	Timer t=new Timer(interval,new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			if(i==20)
			{
				t.stop();
				b4.setEnabled(true);
				
				prg.setString("completed!");
				
				l1.setText(""+grp+" Groups are going to form");
				
				
				b5.setEnabled(true);

				l1.setEnabled(true);

				
			}
			else
			{
				i=i+4;
				prg.setValue(i);
			}

		}
	}
	);

	SenTable(String cs1,double a2,double m3,double m4)
	{
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(15,15,15,15);
		c1=new GridBagConstraints();

		p=new JPanel();
		p.setLayout(new GridBagLayout());
		p1=new JPanel();
		p1.setLayout(new GridLayout());
		jf=new JFrame();	
		p.setVisible(true);
		p1.setVisible(true);
		cs=cs1;
		cs2=cs1;
		a1=a2;
		m1=m3;
		m2=m4;
		System.out.println(cs+" "+a1+" "+m1+" "+m2);
		System.out.println("cs2:"+cs2);
		Object[][] data=null;
		Object[] columns={"Faculty-Name","Faculty-Status"};
		
		DefaultTableModel dTable=new DefaultTableModel(data,columns);
		
		System.out.println(cs+" "+m1+" "+m2);
		tab2=new JTable(dTable);
        	jf2=new JFrame();
		jf3=new JFrame();
		b1=new JButton("ADD");
		b2=new JButton("REMOVE");
		
		b4=new JButton("CONTINUE");
		b5=new JButton("PROCEED");
		b5.setEnabled(false);
		
		l1=new JLabel();
		l1.setEnabled(false);
		
	
		b2.disable();

		
		jf2.setDefaultCloseOperation(jf2.EXIT_ON_CLOSE);
		jf3.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		ListSelectionModel selectionModel = tab2.getSelectionModel();  
	        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        	selectionModel.addListSelectionListener(this);

		b1.addActionListener(this);
		b2.addActionListener(this);
		
		b4.addActionListener(this);
		b5.addActionListener(this);

		
		
		Mongo mongo=null;
		try
		{
			mongo=new Mongo("localhost",27017);

		}
		catch(Exception e)
		{
			e.getMessage();
		}
		db=mongo.getDB("test");
		coll=db.getCollection("senior");
		coll1=db.getCollection("faculty");
		BasicDBObject document=new BasicDBObject();
		DBCursor cursor=coll.find();
			
		DBObject db1;
		Object tmprow[];
		Object o,o1;
		try
		{
			while(cursor.hasNext())
			{
				db1=cursor.next();
				o=db1.get("facname");
				o.toString();
				o1=db1.get("facstatus");
				o1.toString();
				
				tmprow=new Object[]{o,o1};
				dTable.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}
		
		JScrollPane scrollpane=new JScrollPane(tab2);
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b1,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b2,c);
		
		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b4,c);

		
		p1.add(scrollpane);

		jf.setSize(1500,1200);
	
		jf.setLayout(null);
		p1.setBounds(50,20,990,650);
		p.setBounds(1100,0,140,700);
		jf.add(p1);
		jf.add(p);
		jf.show();

		
	}
	SenTable()
	{
		
	}


	
	public void actionPerformed(ActionEvent ae)
	{
		String nm=null;
		char ch=' ';
		String sta=null;
		JButton tmp=(JButton)ae.getSource();
		if(tmp==b1)
		{
			boolean btf=false;
			
			nm = JOptionPane.showInputDialog(jf2, "Enter name of faculty");
			
			sta="senior";

					
			BasicDBObject doc=new BasicDBObject();
			doc.put("facname",nm);
			doc.put("facstatus",sta);
			
			coll=db.getCollection("senior");
			coll.insert(doc);	
			coll1.insert(doc);
				
			try
			{
				jf2.setVisible(false);
				new SenTable(cs,a1,m1,m2);
			}
			catch(Exception eee)
			{
				eee.getMessage();
			}

	   	}
		if(tmp==b2)
		{
			Object ot = tab2.getValueAt(rowindex2, 0);
			ot.toString(); 
			BasicDBObject document = new BasicDBObject();
			document.put("facname", ot);
			coll.remove(document);
				
			try
			{
				jf2.setVisible(false);
				new SenTable(cs,a1,m1,m2);
			}
			catch(Exception eee)
			{
				eee.getMessage();
			}
	
		}

		if(tmp==b4)
		{

			
			jf.show(false);
			jf2.setVisible(false);
			int cnt=1;
			
			jf3.setLayout(new FlowLayout());
			jf3.setSize(300,300);
			jf3.setVisible(true);
			jf3.add(prg);
			jf3.add(b5);
			jf3.add(l1);

			i=0;
			t.start();
			b4.setEnabled(false);
			

			prg.setValue(0);
			prg.setStringPainted(true);
			prg.setBackground(Color.ORANGE);
			prg.setString("Collecting Required Information...");

			

			DBCursor cu1=null;
			DBCursor cu2=null;
				
			DB db=null;
			DBCollection coll;
			DBCollection coll1;
			Object o,o1,o2;
			DBObject db1;
			double d;
			int x;	
			BasicDBObject doc=new BasicDBObject();
				
			try
			{
				mongo=new Mongo("localhost",27017);
				db=mongo.getDB("test");
				
					
				
				coll=db.getCollection("third");
					
				coll1=db.getCollection("tmp2");
					
				cu1=coll1.find();
				cu2=coll.find().sort(new BasicDBObject("cpi",-1));
				
				try
				{
					while(cu1.hasNext())
					{
						coll1.remove(cu1.next());
					}
				}
				catch(Exception e)
				{
					e.getMessage();
				}
				try
				{
					x=cu2.count();
					grp=x/3;
					if(grp>26)
					{
						grp=26;
						grp_flg=1;
					}

					while(cu2.hasNext())
					{
						
						db1=cu2.next();
						o=db1.get("rollno");
						o1=db1.get("name");
						o2=db1.get("cpi");
						String pt=o2.toString();
						d=Double.parseDouble(pt);
						
							
							
						BasicDBObject newdoc=new BasicDBObject();
						newdoc.put("sr",cnt);
						newdoc.put("rollno",o.toString());
						newdoc.put("name",o1.toString());
						newdoc.put("cpi",d);
						
						coll1.insert(newdoc);

						cnt++;
					}
					
					System.out.println("complete");
				}
				catch(Exception ee)
				{
					ee.getMessage();
				}	
					
			}			
			catch(Exception e)
			{
				e.getMessage();
			}
			
		}
		if(tmp==b5)
		{
			
			try
			{
				jf3.setVisible(false);
				new GroupForm(cs,m1,m2);
			}
			catch(Exception e)
			{
				
			}
		}

		
			
	}
	public void valueChanged(ListSelectionEvent e)  
    	{  
        	if(!e.getValueIsAdjusting())  
        	{  
            		ListSelectionModel model = tab2.getSelectionModel();  
            		int lead = model.getLeadSelectionIndex();  
           	 	displayRowValues(lead);  
        	}	  
    	}	  
   
   	private void displayRowValues(int rowIndex)  
        {  
    	    int columns = tab2.getColumnCount();  
	    b2.enable();
	    
	    rowindex2=rowIndex;	
    	}  
}




class GroupForm extends JFrame implements ActionListener
{
	
	String class_nm;
	String pt=null;
	double min,max,ncpi,ocpi;
	JFrame jf;
	Random r;
	final static int interval=1000;
	int i,tot_cnt,grp;
	int x,y,z,k,m,n,p;
	int it=1;
	int tot=0,ran;
	char rch;
	JButton b1;
	
	Mongo mongo;
	DB db;
	DBCollection coll,collgrp,collgrp2,collgrp3;
	DBCursor cu,cu1,cu2,cu3,cu4,tmp,cur,cu5,cu6;
	DBObject db4;

	JProgressBar prg=new JProgressBar(0,20);
	
	Timer t=new Timer(interval,new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			if(i==20)
			{
				t.stop();
				
				prg.setString("completed!");
				b1.setEnabled(true);
			}
			else
			{
				i=i+2;
				prg.setValue(i);
			}

		}
	}
	);
	GroupForm(String cs1,double m1,double m2)
	{
		class_nm=cs1;
		min=m1;
		max=m2;
		
		r=new Random();
		b1=new JButton("NEXT");
		jf=new JFrame();
		b1.setEnabled(false);
		b1.addActionListener(this);
		jf.setLayout(new FlowLayout());
		jf.setSize(400,400);
		jf.setLocation(450,150);
		jf.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.add(prg);
		jf.add(b1);
		
		t.start();
		prg.setValue(0);
		prg.setStringPainted(true);
		prg.setBackground(Color.GREEN);
		prg.setString("Forming the groups...");

		try
		{
			mongo=new Mongo("localhost",27017);
			db=mongo.getDB("test");
		}
		catch(Exception e)
		{
		}
	
		if(class_nm.equals("S.Y"))
		{
			coll=db.getCollection("tmp");
			collgrp=db.getCollection("group");	
			cu=coll.find();
			cu4=collgrp.find();
			tot_cnt=cu.count();
			grp=tot_cnt/3;
			k=tot_cnt%3;
			p=k+3;
			m=grp-1;
			n=(tot_cnt-2*grp)-1;
		
			int val=0;
			
			Object o1=null;
			Object o2=null;
			Object o3=null;
			Object o4=null;
			Object o5=null;
			Object o6=null;
			Object o7=null;
			Object o8=null;
			Object o9=null;
			Object o10=null;
			Object o11=null;
			Object o12=null;
			double d1,d2,d3,d4,newavg,old;
			DBObject db1,db2,db3;
			
			try
			{
				while(cu4.hasNext())
				{
					collgrp.remove(cu4.next());
				}
				pro(0);	
				
				while(cu.length()>0)
				{
					old=0.0;
					ran=r.nextInt(grp)+65;
					val=(ran-65)*3;
					rch=(char)ran;
					
					BasicDBObject query = new BasicDBObject();
					query.put("group_name",rch);
					cur= collgrp.find(query);
				
					try
					{
						cu=coll.find();
						db1=cu.next();
						o4=db1.get("fypi");
						pt=o4.toString();
						ncpi=Double.parseDouble(pt);
					}
					
					catch(Exception e){}
										
					try
					{
						cur=collgrp.find();
						cur.skip(val);
						
					}
					catch(Exception e){}
					
					db1=cur.next();
					o1=db1.get("rollno");
					o2=db1.get("name");
					o3=db1.get("cpi");
					pt=o3.toString();
					ocpi=Double.parseDouble(pt);
					old=old+ocpi;

					db2=cur.next();
					o4=db2.get("rollno");
					o5=db2.get("name");
					o6=db2.get("cpi");
					pt=o6.toString();
					ocpi=Double.parseDouble(pt);
					old=old+ocpi;

					db3=cur.next();
					o7=db3.get("rollno");
					o8=db3.get("name");
					o9=db3.get("cpi");
					pt=o9.toString();
					ocpi=Double.parseDouble(pt);
					old=old+ocpi;
						
					newavg=(old+ncpi)/4;
					newavg=Math.round( newavg * 100.0 ) / 100.0;						
					BasicDBObject doc=new BasicDBObject();
					doc.put("group_name",rch);
					doc.put("rollno",o1);
					doc.put("name",o2);
					doc.put("cpi",o3);
					doc.put("avg_cpi",newavg);
					
					collgrp.insert(doc);

					BasicDBObject doc1=new BasicDBObject();
						
					doc1.put("rollno",o4);
					doc1.put("name",o5);
					doc1.put("cpi",o6);
						
					collgrp.insert(doc1);

					BasicDBObject doc2=new BasicDBObject();
						
					doc2.put("rollno",o7);
					doc2.put("name",o8);
					doc2.put("cpi",o9);
						
					collgrp.insert(doc2);
					
					cu=coll.find();
						
					db1=cu.next();
					o10=db1.get("rollno");
					o11=db1.get("name");
					o12=db1.get("fypi");
					pt=o12.toString();
					ocpi=Double.parseDouble(pt);

					BasicDBObject doc3=new BasicDBObject();
						
					doc3.put("rollno",o10);
					doc3.put("name",o11);
					doc3.put("cpi",o12);
						
					collgrp.insert(doc3);
					try
					{
						cur=collgrp.find();
						cur.skip(val);
					}
					catch(Exception e){}
					
					for(int j=0;j<3;j++)
					{
						collgrp.remove(cur.next());	
					}
					cu=coll.find();
					coll.remove(cu.next());
					cu=coll.find();
				}
			}
			catch(Exception e){}
			}
	
/*******************************************************************************************/
		if(class_nm.equals("T.Y"))
		{
			DBCursor grpsort;
			coll=db.getCollection("tmp1");
			collgrp=db.getCollection("group1");	
			collgrp2=db.getCollection("group11");	
			collgrp3=db.getCollection("group111");	
			cu=coll.find();
			cu4=collgrp.find();
			cu5=collgrp2.find();
			cu6=collgrp3.find();
			tot_cnt=cu.count();
			grp=tot_cnt/3;
			k=tot_cnt%3;
			p=k+3;
			m=grp-1;
			n=(tot_cnt-2*grp)-1;
			int val=0;
			int srno=0;
			int nsrno=0;
			String forsr;
				
			Object o1=null;
			Object o2=null;
			Object o3=null;
			Object o4=null;
			Object o5=null;
			Object o6=null;
			Object o7=null;
			Object o8=null;
			Object o9=null;
			Object o10=null;
			Object o11=null;
			Object o12=null;
			Object o13=null,o14=null,o15=null,o16=null;
			double d1,d2,d3,d4,old;
			Double newavg;
			DBObject db1,db2,db3;
			
			try
			{
		
				while(cu4.hasNext())
				{
					collgrp.remove(cu4.next());
				}
				while(cu5.hasNext())
				{
					collgrp2.remove(cu5.next());
				}
				while(cu6.hasNext())
				{
					collgrp3.remove(cu6.next());
				}
				pro2(0);

				cu.close();
				cu=coll.find();
				if(cu.length()>0)
				{
					cu.close();
					cu=coll.find();				
					while(cu.length()>0)
					{
						srno=0;
						nsrno=0;
						cu.close();
						old=0.0;
						ran=r.nextInt(grp)+65;
						System.out.println("ran:"+ran);
						val=(ran-65)*3;
						rch=(char)ran;
						System.out.println(rch);
						BasicDBObject query = new BasicDBObject();
						query.put("group_name",rch);
						cur= collgrp.find(query);
						cur.close();

						try
						{
								cu=coll.find();
							
							db1=cu.next();
							o4=db1.get("cpi");
							pt=o4.toString();
							ncpi=Double.parseDouble(pt);
							System.out.println(ncpi);
						}
						
						catch(Exception e){}
						
						try
						{
							cu.close();
							cur=collgrp.find().sort(new BasicDBObject("sr",1));
							cur.skip(val);
						}
						catch(Exception e){}
						
						for(int j=0;j<3;j++)
						{
							db1=cur.next();
							o1=db1.get("cpi");
							pt=o1.toString();
							ocpi=Double.parseDouble(pt);
							old=old+ocpi;
						}
						newavg=(old+ncpi)/4;	
						cur.close();
						try
						{
							cur=collgrp.find().sort(new BasicDBObject("sr",1));
							cur.skip(val);
						}
						catch(Exception e){}
						
						for(int j=0;j<3;j++)
						{
							db1=cur.next();
							o11=db1.get("sr");
							forsr=o11.toString();
							srno=Integer.parseInt(forsr);
							o1=db1.get("rollno");
							o2=db1.get("name");
							o3=db1.get("cpi");
							pt=o3.toString();
							ocpi=Double.parseDouble(pt);
							old=old+ocpi;
							
							collgrp.remove(cur.curr());	
							
							BasicDBObject doc=new BasicDBObject();
							doc.put("sr",srno+75);
							doc.put("group_name",rch);
							doc.put("rollno",o1);
							doc.put("name",o2);
							doc.put("cpi",o3);
							doc.put("avg_cpi",newavg);
						
							collgrp.insert(doc);
				
						}
						
						cur.close();
						cu.close();
						cu=coll.find();
							
						db1=cu.next();
						o1=db1.get("rollno");
						o2=db1.get("name");
						o3=db1.get("cpi");
						BasicDBObject doc=new BasicDBObject();
						
						nsrno=srno+76;
						
						doc.put("sr",nsrno);
						doc.put("rollno",o1);
						doc.put("name",o2);
						doc.put("cpi",o3);
						
						collgrp.insert(doc);

						cur.close();
	
						coll.remove(cu.curr());
						cu=coll.find();
						cur.close();
					}
				}
			}
		catch(Exception e){}
			
			grpsort=collgrp.find().sort(new BasicDBObject("sr",1));
			DBObject copyob;
			try
			{
				while(grpsort.hasNext())
				{
					copyob=grpsort.next();
					o1=copyob.get("sr");
					o2=copyob.get("group_name");
					o3=copyob.get("rollno");
					o4=copyob.get("name");
					o5=copyob.get("cpi");
					o6=copyob.get("avg_cpi");

					BasicDBObject doc=new BasicDBObject();
					doc.put("sr",o1);
					doc.put("group_name",o2);
					doc.put("rollno",o3);
					doc.put("name",o4);
					doc.put("cpi",o5);
					doc.put("avg_cpi",o6);

					collgrp2.insert(doc);

				}
			}
			catch(Exception e){}
			
			grpsort.close();
			grpsort=collgrp2.find().sort(new BasicDBObject("sr",1));
		
			try
			{
				while(grpsort.hasNext())
				{
					copyob=grpsort.next();
					o1=copyob.get("sr");
					o2=copyob.get("group_name");
					o3=copyob.get("rollno");
					o4=copyob.get("name");
					o5=copyob.get("cpi");
					o6=copyob.get("avg_cpi");

					BasicDBObject doc=new BasicDBObject();
					doc.put("sr",o1);
					doc.put("group_name",o2);
					doc.put("rollno",o3);
					doc.put("name",o4);
					doc.put("cpi",o5);
					doc.put("avg_cpi",o6);

					collgrp3.insert(doc);

				}
			}
			catch(Exception e)
			{
			}
			grpsort.close();
			
		}

		if(class_nm.equals("B.Tech"))
		{
			
			DBCursor grpsort;
			coll=db.getCollection("tmp2");
			collgrp=db.getCollection("group2");	
			collgrp2=db.getCollection("group22");	
			collgrp3=db.getCollection("group222");	
			cu=coll.find();
			cu4=collgrp.find();
			cu5=collgrp2.find();
			cu6=collgrp3.find();
			tot_cnt=cu.count();
			grp=tot_cnt/3;
			k=tot_cnt%3;
			p=k+3;
			m=grp-1;
			n=(tot_cnt-2*grp)-1;
		
			int val=0;
			
			int srno=0;
			int nsrno=0;
			String forsr;
				
			Object o1=null;
			Object o2=null;
			Object o3=null;
			Object o4=null;
			Object o5=null;
			Object o6=null;
			Object o7=null;
			Object o8=null;
			Object o9=null;
			Object o10=null;
			Object o11=null;
			Object o12=null;
			Object o13=null,o14=null,o15=null,o16=null;
			double d1,d2,d3,d4,old;
			Double newavg;
			DBObject db1,db2,db3;
			
			try
			{
		
				while(cu4.hasNext())
				{
					collgrp.remove(cu4.next());
				}
				while(cu5.hasNext())
				{
					collgrp2.remove(cu5.next());
				}
				while(cu6.hasNext())
				{
					collgrp3.remove(cu6.next());
				}
				System.out.println("before pro2");
				pro2(0);

				
				cu.close();
				cu=coll.find();
				if(cu.length()>0)
				{
					cu.close();
					cu=coll.find();				
					while(cu.length()>0)
					{
						srno=0;
						nsrno=0;
						cu.close();
						old=0.0;
						ran=r.nextInt(grp)+65;
						System.out.println("ran:"+ran);
						val=(ran-65)*3;
						rch=(char)ran;
						System.out.println(rch);
						BasicDBObject query = new BasicDBObject();
						query.put("group_name",rch);
						cur= collgrp.find(query);
						cur.close();

						try
						{
							cu=coll.find();
						
							db1=cu.next();
							o4=db1.get("cpi");
							pt=o4.toString();
							ncpi=Double.parseDouble(pt);
							System.out.println(ncpi);
						}
						
						catch(Exception e){}
						
						try
						{
							cu.close();
							cur=collgrp.find().sort(new BasicDBObject("sr",1));
							cur.skip(val);
						}
						catch(Exception e){}
						for(int j=0;j<3;j++)
						{
							db1=cur.next();
							o1=db1.get("cpi");
							pt=o1.toString();
							ocpi=Double.parseDouble(pt);
							old=old+ocpi;
						}
							
						newavg=(old+ncpi)/4;	
						cur.close();
						try
						{
							cur=collgrp.find().sort(new BasicDBObject("sr",1));
							cur.skip(val);
						
						}
						catch(Exception e){}
						
						for(int j=0;j<3;j++)
						{
							db1=cur.next();
							o11=db1.get("sr");
							forsr=o11.toString();
							srno=Integer.parseInt(forsr);
							o1=db1.get("rollno");
							o2=db1.get("name");
							o3=db1.get("cpi");
							pt=o3.toString();
							ocpi=Double.parseDouble(pt);
							old=old+ocpi;
							
							collgrp.remove(cur.curr());	
							
							BasicDBObject doc=new BasicDBObject();
							doc.put("sr",srno+75);
							doc.put("group_name",rch);
							doc.put("rollno",o1);
							doc.put("name",o2);
							doc.put("cpi",o3);
							doc.put("avg_cpi",newavg);
						
							collgrp.insert(doc);
				
						}
						
						cur.close();
						cu.close();
						cu=coll.find();
							
						db1=cu.next();
						o1=db1.get("rollno");
						o2=db1.get("name");
						o3=db1.get("cpi");
						BasicDBObject doc=new BasicDBObject();
						
						nsrno=srno+76;
						
						doc.put("sr",nsrno);
						doc.put("rollno",o1);
						doc.put("name",o2);
						doc.put("cpi",o3);
						
						collgrp.insert(doc);

						cur.close();
	
						coll.remove(cu.curr());
						cu=coll.find();
						cur.close();
					}
				}
			}
			catch(Exception e){}
			
			grpsort=collgrp.find().sort(new BasicDBObject("sr",1));
			DBObject copyob;
			try
			{
				while(grpsort.hasNext())
				{
					copyob=grpsort.next();
					o1=copyob.get("sr");
					o2=copyob.get("group_name");
					o3=copyob.get("rollno");
					o4=copyob.get("name");
					o5=copyob.get("cpi");
					o6=copyob.get("avg_cpi");

					BasicDBObject doc=new BasicDBObject();
					doc.put("sr",o1);
					doc.put("group_name",o2);
					doc.put("rollno",o3);
					doc.put("name",o4);
					doc.put("cpi",o5);
					doc.put("avg_cpi",o6);

					collgrp2.insert(doc);

				}
			}
			catch(Exception e){}
			
			grpsort.close();
			grpsort=collgrp2.find().sort(new BasicDBObject("sr",1));
		
			try
			{
				while(grpsort.hasNext())
				{
					copyob=grpsort.next();
					o1=copyob.get("sr");
					o2=copyob.get("group_name");
					o3=copyob.get("rollno");
					o4=copyob.get("name");
					o5=copyob.get("cpi");
					o6=copyob.get("avg_cpi");

					BasicDBObject doc=new BasicDBObject();
					doc.put("sr",o1);
					doc.put("group_name",o2);
					doc.put("rollno",o3);
					doc.put("name",o4);
					doc.put("cpi",o5);
					doc.put("avg_cpi",o6);

					collgrp3.insert(doc);

				}
			}
			catch(Exception e){}
			
			grpsort.close();
			
		}
	}

	public int getx()
	{
		int a=0;
		
		x=r.nextInt(m+1)+1;
		BasicDBObject query1 = new BasicDBObject();
		query1.put("sr",x);
		cu1= coll.find(query1);
		a=cu1.itcount();
		
		if(a>0)
		{
			return x;
		}
		else
		{
			return -1;
		}
	}
	public int gety()
	{
		int b=0;
		y=r.nextInt(m+1)+grp+1;
						
		BasicDBObject query2 = new BasicDBObject();
		query2.put("sr",y);
		cu2= coll.find(query2);
		b=cu2.itcount();
		if(b>0)
			return y;
		else 	
			return -1;		
	}

	public int getz()
	{
		int c=0;
		z=r.nextInt(n+1)+2*grp+1;
		BasicDBObject query3 = new BasicDBObject();
		query3.put("sr",z);
						
		cu3= coll.find(query3);
		c=cu3.itcount();
		if(c>0)
			return z;
		else
			return -1;		

	}

	public void pro(int pot)
	{
		int nx=-1,ny=-1,nz=-1;
		int chrval[]=new int[26];
		int val=65;
		int point=pot;
		int flg=0,k=3;
		char tmp;
		double d1,d2,d3,d4,avg;
		Object o1=null;
		Object o2=null;
		Object o3=null;
		Object o4=null;
		Object o5=null;
		Object o6=null;
		
		for(int j=0;j<26;j++)
		{
			chrval[j]=val;
			val=val+1;
		}
	
		try
		{
			
			while(cu.length()>=p)	
			{
				flg=0;
			
				for(int j=0;j<500 && flg==0 ;j++)
				{
					nx=getx();
					if(nx==-1)
					  flg=0;
					else
					  flg=1;	
				}	
				flg=0;
			
				for(int j=0;j<500 && flg==0 ;j++)
				{
					ny=gety();
					if(ny==-1)
					  flg=0;
					else
					  flg=1;
				}
				flg=0;
			
				for(int j=0;j<500 && flg==0 ;j++)
				{
					nz=getz();
					if(nz==-1)
					  flg=0;
					else
					  flg=1;
				}		
						

				
				DBObject db1=cu1.curr();
				o1=db1.get("fypi");

				DBObject db2=cu2.curr();
				o2=db2.get("fypi");
						
				DBObject db3=cu3.curr();
				o3=db3.get("fypi");
						
				pt=o1.toString();
				d1=Double.parseDouble(pt);

				pt=o2.toString();
				d2=Double.parseDouble(pt);

				pt=o3.toString();
				d3=Double.parseDouble(pt);

				d4=(d1+d2+d3)/3;
				
				if(d4>=min && d4<=max)
				{
					avg=d4;
					avg=Math.round( avg * 100.0 ) / 100.0;
					db4=cu1.curr();
					o4=db4.get("rollno");
					o5=db4.get("name");
					o6=db4.get("fypi");

					tmp=(char)chrval[point];
					point=point+1;
					
					BasicDBObject doc1=new BasicDBObject();
					doc1.put("group_name",tmp);
					doc1.put("rollno",o4);						
					doc1.put("name",o5);
					doc1.put("cpi",o6);
					doc1.put("avg_cpi",avg);
							
					collgrp.insert(doc1);	

					db4=cu2.curr();
					o4=db4.get("rollno");
					o5=db4.get("name");
					o6=db4.get("fypi");

					BasicDBObject doc2=new BasicDBObject();
					doc2.put("rollno",o4);						
					doc2.put("name",o5);
					doc2.put("cpi",o6);
							
					collgrp.insert(doc2);

					db4=cu3.curr();
					o4=db4.get("rollno");
					o5=db4.get("name");
					o6=db4.get("fypi");

					BasicDBObject doc3=new BasicDBObject();
					doc3.put("rollno",o4);						
					doc3.put("name",o5);
					doc3.put("cpi",o6);
					
		
					collgrp.insert(doc3);
				
					coll.remove(cu1.curr());						
					coll.remove(cu2.curr());
					coll.remove(cu3.curr());
					
					pro(point);
				}
				else
				{
					
					pro(point);
					
				}
				
				cu=coll.find();
			}	
				
		}
		catch(Exception e){}
	}
/*************************************************************************************/

public void pro2(int pot)
{
		int nx=-1,ny=-1,nz=-1;
		int chrval[]=new int[26];
		int val=65;
		int point=pot;
		int flg=0,k=2;
		char tmp;
		double d1,d2,d3,d4,avg;
		Object o1=null;
		Object o2=null;
		Object o3=null;
		Object o4=null;
		Object o5=null;
		Object o6=null;
		
		System.out.println("i m in pro2");
		for(int j=0;j<26;j++)
		{
			chrval[j]=val;
			val=val+1;
		}
	
		try
		{
			System.out.println(p);
			while(cu.length()>=k)	
			{
				flg=0;
			
				for(int j=0;j<500 && flg==0 ;j++)
				{
					nx=getx();
					if(nx==-1)
					  flg=0;
					else
					  flg=1;	
				}	
				flg=0;
			
				for(int j=0;j<500 && flg==0 ;j++)
				{
					ny=gety();
					if(ny==-1)
					  flg=0;
					else
					  flg=1;
				}
				flg=0;
			
				for(int j=0;j<500 && flg==0 ;j++)
				{
					nz=getz();
					if(nz==-1)
					  flg=0;
					else
					  flg=1;
				}		
				
				System.out.println("x:"+nx+"y:"+ny+"z:"+nz);
				DBObject db1=cu1.curr();
				o1=db1.get("cpi");

				DBObject db2=cu2.curr();
				o2=db2.get("cpi");
						
				DBObject db3=cu3.curr();
				o3=db3.get("cpi");
						
				pt=o1.toString();
				d1=Double.parseDouble(pt);

				pt=o2.toString();
				d2=Double.parseDouble(pt);

				pt=o3.toString();
				d3=Double.parseDouble(pt);

				d4=(d1+d2+d3)/3;
				System.out.println("x:"+x+" cpi:"+o1);
				System.out.println("y:"+y+" cpi:"+o2);
				System.out.println("z:"+z+" cpi:"+o3);
				

				if(d4>=min && d4<=max)
				{
					avg=d4;
					avg=Math.round( avg * 100.0 ) / 100.0;
					db4=cu1.curr();
					o4=db4.get("rollno");
					o5=db4.get("name");
					o6=db4.get("cpi");

					tmp=(char)chrval[point];
					point=point+1;
					
					BasicDBObject doc1=new BasicDBObject();
					doc1.put("sr",it);
					doc1.put("group_name",tmp);
					doc1.put("rollno",o4);						
					doc1.put("name",o5);
					doc1.put("cpi",o6);
					doc1.put("avg_cpi",avg);
							
					collgrp.insert(doc1);	
					
					it++;
					
					db4=cu2.curr();
					o4=db4.get("rollno");
					o5=db4.get("name");
					o6=db4.get("cpi");

					BasicDBObject doc2=new BasicDBObject();
					doc2.put("sr",it);
					doc2.put("rollno",o4);						
					doc2.put("name",o5);
					doc2.put("cpi",o6);
							
					collgrp.insert(doc2);
					it++;

					db4=cu3.curr();
					o4=db4.get("rollno");
					o5=db4.get("name");
					o6=db4.get("cpi");

					BasicDBObject doc3=new BasicDBObject();
					doc3.put("sr",it);
					doc3.put("rollno",o4);						
					doc3.put("name",o5);
					doc3.put("cpi",o6);
					
					collgrp.insert(doc3);
					it++;
					coll.remove(cu1.curr());						
					coll.remove(cu2.curr());
					coll.remove(cu3.curr());
					System.out.println("formed");
					pro2(point);
				}
				else
				{
					System.out.println("not formed");
					pro2(point);	
				}
				cu=coll.find();
			}	
		}
		catch(Exception e){}
	}

	public void actionPerformed(ActionEvent ae)
	{
		
		String tmp=(String)ae.getActionCommand();
		if(tmp.equals("NEXT"))
		{
			
			try
			{
				jf.setVisible(false);
				new DisplayGroup(class_nm);
			}
			catch(Exception e){}
		}	
	}
}
class DisplayGroup extends JFrame implements ActionListener,ListSelectionListener
{
	String class_nm=null;
	JFrame jf=new JFrame();
	Object[][] data;

	JTable table;
	JButton b1,b2,b3;
	JPanel p1,p;
	GridBagConstraints c,c1;
	JLayeredPane Jlp;
	Mongo mongo;
	DBCollection coll,coll1,coll2,coll3;
	DB db;
	DBCursor cur,cur1,cur2,cur3;
	DefaultTableModel dTable;
	JScrollPane scrollpane;
	DisplayGroup(String cls_nm)
	{
		c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(15,15,15,15);
		c1=new GridBagConstraints();

		p=new JPanel();
		p.setLayout(new GridBagLayout());
		p1=new JPanel();
		p1.setLayout(new GridLayout());


		class_nm=cls_nm;
		data=null;
		Object[] columns={"Group-Name","Rollno","Name","cpi","Averagecpi"};
		dTable=new DefaultTableModel(data,columns);
		
		table=new JTable(dTable);
		b1=new JButton("Assign the GUIDES");
	
	
		ListSelectionModel selectionModel = table.getSelectionModel();  
	        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        	selectionModel.addListSelectionListener(this);
		b1.addActionListener(this);


		JScrollPane scrollpane=new JScrollPane(table);
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b1,c); 

	
		p1.add(scrollpane);

		jf.setSize(1500,1200);
	
		jf.setLayout(null);
		p1.setBounds(50,20,990,650);
		p.setBounds(1100,0,140,700);
		jf.add(p1);
		jf.add(p);
		jf.show();
		try
		{
			mongo=new Mongo("localhost",27017);
		}
		catch(Exception e){}
		
		if(class_nm.equals("S.Y"))
		{
			gosy();
		}
		if(class_nm.equals("T.Y"))
		{
			goty();
		}
		if(class_nm.equals("B.Tech"))
		{
			gobt();
		}
		
	}
	void gosy()
	{
		Object tmprow[];
		Object o,o1,o2,o3,o4;
		db=mongo.getDB("test");
		coll=db.getCollection("group");
		cur=coll.find();
		DBObject db1;
		try
		{
			while(cur.hasNext())
			{
				db1=cur.next();
				o=db1.get("group_name");
				o1=db1.get("rollno");
				o2=db1.get("name");
				o3=db1.get("cpi");
				o4=db1.get("avg_cpi");

				
				tmprow=new Object[]{o,o1,o2,o3,o4};
				dTable.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}
		
	}
	void goty()
	{
		Object tmprow[];
		Object o,o1,o2,o3,o4;
		db=mongo.getDB("test");
		coll=db.getCollection("group11");
		cur=coll.find().sort(new BasicDBObject("sr",1));
		DBObject db1;
		try
		{
			while(cur.hasNext())
			{
				db1=cur.next();
				o=db1.get("group_name");
				o1=db1.get("rollno");
				o2=db1.get("name");
				o3=db1.get("cpi");
				o4=db1.get("avg_cpi");

				
				tmprow=new Object[]{o,o1,o2,o3,o4};
				dTable.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}
		
	}
	void gobt()
	{
		Object tmprow[];
		Object o,o1,o2,o3,o4;
		db=mongo.getDB("test");
		coll=db.getCollection("group22");
		cur=coll.find().sort(new BasicDBObject("sr",1));
		DBObject db1;
		try
		{
			while(cur.hasNext())
			{
				db1=cur.next();
				o=db1.get("group_name");
				o1=db1.get("rollno");
				o2=db1.get("name");
				o3=db1.get("cpi");
				o4=db1.get("avg_cpi");

				
				tmprow=new Object[]{o,o1,o2,o3,o4};
				dTable.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}
		
	}
	void assign()
	{
		if(class_nm.equals("S.Y"))
		{
			
			int len,len1;
			coll1=db.getCollection("group");
			coll2=db.getCollection("faculty");
			coll3=db.getCollection("guide1");
			cur1=coll1.find();
			cur2=coll2.find();
			cur3=coll3.find();
			
			len=cur1.length();
			len1=cur2.length();
			DBObject db=null,db1=null;
			int cnt=0;
			int it=1;
			int p=len%3;
			int q=len-(4*p);
			String guides[]=new String[len1];
			DBObject dbb;
			try
			{
				cur2=coll2.find();
				for(int j=0;j<len1;j++)
				{
					
					dbb=cur2.next();
					Object gu=dbb.get("facname");
					guides[j]=gu.toString();
					
				}
			}
			catch(Exception e)
			{
			}
			
			Object o=null,o1,o2,o3,o4,o5;
			cur2=coll2.find();
			cur1=coll1.find();
			try
			{
				while(cur3.hasNext())
				{
					coll3.remove(cur3.next());
				}
			}
			catch(Exception e)
			{
			}
			
			String tmp=null;
		
			for(int j=0;j<q;j++)
			{
			
				if(j%3==0)
				{
					try
					{
						db1=cur1.next();
						
						o1=db1.get("group_name");
						o2=db1.get("rollno");	
						o3=db1.get("name");
						o4=db1.get("cpi");
						o5=db1.get("avg_cpi");
						
						BasicDBObject doc=new BasicDBObject();
						doc.put("sr",it);
						doc.put("group_name",o1);
						doc.put("rollno",o2);
						doc.put("name",o3);
						doc.put("cpi",o4);
						doc.put("avg_cpi",o5);
						doc.put("guide",guides[cnt]);

					
						coll3.insert(doc);
						cnt++;
						if(cnt==len1)
						{
							cnt=0;	
						}
						it++;
					}
					catch(Exception e)
					{
					}
					
				}
				else
				{	
					db1=cur1.next();
					o2=db1.get("rollno");	
					o3=db1.get("name");
					o4=db1.get("cpi");
					BasicDBObject doc=new BasicDBObject();
					doc.put("sr",it);	
					doc.put("rollno",o2);
					doc.put("name",o3);
					doc.put("cpi",o4);
						
					coll3.insert(doc);
					it++;
				}
				
			}
			for(int j=0;j<(4*p);j++)
			{
				if(j%4==0)
				{
					try
					{
						db1=cur1.next();
						o1=db1.get("group_name");
						o2=db1.get("rollno");	
						o3=db1.get("name");
						o4=db1.get("cpi");
						o5=db1.get("avg_cpi");
						
						BasicDBObject doc=new BasicDBObject();
						doc.put("sr",it);
						doc.put("group_name",o1);
						doc.put("rollno",o2);
						doc.put("name",o3);
						doc.put("cpi",o4);
						doc.put("avg_cpi",o5);
						doc.put("guide",guides[cnt]);
							
						coll3.insert(doc);
						cnt++;
						if(cnt==len1)
						{
							cnt=0;	
						}	
						it++;
					}
					catch(Exception e)
					{
					}
				}
				else
				{	
					db1=cur1.next();
					o2=db1.get("rollno");	
					o3=db1.get("name");
					o4=db1.get("cpi");
					BasicDBObject doc=new BasicDBObject();
					doc.put("sr",it);	
					doc.put("rollno",o2);
					doc.put("name",o3);
					doc.put("cpi",o4);
						
					coll3.insert(doc);
					it++;
				}
			}
		}
		if(class_nm.equals("T.Y"))
		{
			int len,len1;
			coll1=db.getCollection("group11");
			coll2=db.getCollection("faculty");
			coll3=db.getCollection("guide2");
			cur1=coll1.find().sort(new BasicDBObject("sr",1));
			cur2=coll2.find();
			cur3=coll3.find();
			
			len=cur1.length();
			len1=cur2.length();
			DBObject db=null,db1=null;
			int cnt=0;
			int it=1;
			int p=len%3;
			int q=len-(4*p);
			String guides[]=new String[len1];
			DBObject dbb;
			try
			{
				cur2=coll2.find();
				for(int j=0;j<len1;j++)
				{
					
					dbb=cur2.next();
					Object gu=dbb.get("facname");
					guides[j]=gu.toString();
					System.out.println(guides[j]);	
				}
			}
			catch(Exception e)
			{
			}
			
			Object o=null,o1,o2,o3,o4,o5;
			cur2=coll2.find();
			cur1=coll1.find().sort(new BasicDBObject("sr",1));
			try
			{
				while(cur3.hasNext())
				{
					coll3.remove(cur3.next());
				}
			}
			catch(Exception e)
			{
			}
			System.out.println("len:"+len);
			String tmp=null;
		
			for(int j=0;j<q;j++)
			{
				if(j%3==0)
				{
					try
					{
						db1=cur1.next();
						
						o1=db1.get("group_name");
						o2=db1.get("rollno");	
						o3=db1.get("name");
						o4=db1.get("cpi");
						o5=db1.get("avg_cpi");
						
						BasicDBObject doc=new BasicDBObject();
						doc.put("sr",it);
						doc.put("group_name",o1);
						doc.put("rollno",o2);
						doc.put("name",o3);
						doc.put("cpi",o4);
						doc.put("avg_cpi",o5);
						doc.put("guide",guides[cnt]);

						coll3.insert(doc);
						cnt++;
						if(cnt==len1)
						{
							cnt=0;	
						}
						it++;
					}
					catch(Exception e){}
					
				}
				else
				{	
					db1=cur1.next();
					o2=db1.get("rollno");	
					o3=db1.get("name");
					o4=db1.get("cpi");
					BasicDBObject doc=new BasicDBObject();
					doc.put("sr",it);	
					doc.put("rollno",o2);
					doc.put("name",o3);
					doc.put("cpi",o4);
						
					coll3.insert(doc);
					it++;
				}
			}
			for(int j=0;j<(4)*p;j++)
			{
				if(j%4==0)
				{
					try
					{
						db1=cur1.next();
						
						o1=db1.get("group_name");
						o2=db1.get("rollno");	
						o3=db1.get("name");
						o4=db1.get("cpi");
						o5=db1.get("avg_cpi");
						
						BasicDBObject doc=new BasicDBObject();
						doc.put("sr",it);
						doc.put("group_name",o1);
						doc.put("rollno",o2);
						doc.put("name",o3);
						doc.put("cpi",o4);
						doc.put("avg_cpi",o5);
						doc.put("guide",guides[cnt]);
							
						coll3.insert(doc);
						cnt++;
						if(cnt==len1)
						{
							cnt=0;	
						}	
						it++;
					}
					catch(Exception e){}
					
				}
				else
				{	
					db1=cur1.next();
					o2=db1.get("rollno");	
					o3=db1.get("name");
					o4=db1.get("cpi");
					BasicDBObject doc=new BasicDBObject();
					doc.put("sr",it);	
					doc.put("rollno",o2);
					doc.put("name",o3);
					doc.put("cpi",o4);
						
					coll3.insert(doc);
					it++;
				}		
			}
		}

		if(class_nm.equals("B.Tech"))
		{
			
			int len,len1;
			coll1=db.getCollection("group22");
			coll2=db.getCollection("senior");
			coll3=db.getCollection("guide3");
			cur1=coll1.find().sort(new BasicDBObject("sr",1));
			cur2=coll2.find();
			cur3=coll3.find();
			
			len=cur1.length();
			len1=cur2.length();
			DBObject db=null,db1=null;
			int cnt=0;
			int it=1;
			int p=len%3;
			int q=len-(4*p);
			String guides[]=new String[len1];
			DBObject dbb;
			try
			{
				cur2=coll2.find();
				for(int j=0;j<len1;j++)
				{
					dbb=cur2.next();
					Object gu=dbb.get("facname");
					guides[j]=gu.toString();
					System.out.println(guides[j]);	
				}
			}
			catch(Exception e)
			{
			}
			
			Object o=null,o1,o2,o3,o4,o5;
			cur2=coll2.find();
			cur1=coll1.find().sort(new BasicDBObject("sr",1));
			try
			{
				while(cur3.hasNext())
				{
					coll3.remove(cur3.next());
				}
			}
			catch(Exception e)
			{
			}
			System.out.println("len:"+len);
			String tmp=null;
		
			for(int j=0;j<q;j++)
			{
				if(j%3==0)
				{
					try
					{
						db1=cur1.next();
						
						o1=db1.get("group_name");
						o2=db1.get("rollno");	
						o3=db1.get("name");
						o4=db1.get("cpi");
						o5=db1.get("avg_cpi");
						
						BasicDBObject doc=new BasicDBObject();
						doc.put("sr",it);
						doc.put("group_name",o1);
						doc.put("rollno",o2);
						doc.put("name",o3);
						doc.put("cpi",o4);
						doc.put("avg_cpi",o5);
						doc.put("guide",guides[cnt]);

					
						coll3.insert(doc);
						cnt++;
						if(cnt==len1)
						{
							cnt=0;	
						}
						it++;
					}
					catch(Exception e){}
					
				}
				else
				{	
				
					db1=cur1.next();
					o2=db1.get("rollno");	
					o3=db1.get("name");
					o4=db1.get("cpi");
					BasicDBObject doc=new BasicDBObject();
					doc.put("sr",it);	
					doc.put("rollno",o2);
					doc.put("name",o3);
					doc.put("cpi",o4);
						
					coll3.insert(doc);
					it++;
				}
				
			}
			for(int j=0;j<(4)*p;j++)
			{
				if(j%4==0)
				{
					try
					{
						db1=cur1.next();
						
						o1=db1.get("group_name");
						o2=db1.get("rollno");	
						o3=db1.get("name");
						o4=db1.get("cpi");
						o5=db1.get("avg_cpi");
						
						BasicDBObject doc=new BasicDBObject();
						doc.put("sr",it);
						doc.put("group_name",o1);
						doc.put("rollno",o2);
						doc.put("name",o3);
						doc.put("cpi",o4);
						doc.put("avg_cpi",o5);
						doc.put("guide",guides[cnt]);
							
						coll3.insert(doc);
						cnt++;
						if(cnt==len1)
						{
							cnt=0;	
						}	
						it++;
					}
					catch(Exception e){}
					
				}
				else
				{	
					db1=cur1.next();
					o2=db1.get("rollno");	
					o3=db1.get("name");
					o4=db1.get("cpi");
					BasicDBObject doc=new BasicDBObject();
					doc.put("sr",it);	
					doc.put("rollno",o2);
					doc.put("name",o3);
					doc.put("cpi",o4);
						
					coll3.insert(doc);
					it++;
				}
			}
		}
		else
		{
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		JButton tmp=(JButton)ae.getSource();
		if(tmp==b1)
		{	
			assign();
			jf.setVisible(false);	
			new DisplayGuides(class_nm);
		}
	}
	public void valueChanged(ListSelectionEvent e)  
    	{  
        	  
    	}	  
   
   	private void displayRowValues(int rowIndex)  
       	 {  
    	
    	}  
		
}
 
class DisplayGuides  extends JFrame implements ActionListener,ListSelectionListener
{
	String class_nm=null;
	JFrame jf=new JFrame();
	Object[][] data2;

	JTable table2;
	JButton b1,b2,b3;
	Mongo mongo;
	GridBagConstraints c,c1;
	JLayeredPane Jlp;
	JPanel p,p1;
	
	DBCollection coll,coll1,coll2,coll3;
	DB db;
	DBCursor cur,cur1,cur2,cur3;
	DefaultTableModel dTable2;
	JScrollPane scrollpane2;
	DisplayGuides(String cls_nm)
	{

		c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(15,15,15,15);
		c1=new GridBagConstraints();

		p=new JPanel();
		p.setLayout(new GridBagLayout());
		p1=new JPanel();
		p1.setLayout(new GridLayout());
	
		
		class_nm=cls_nm;
		data2=null;
		Object[] columns2={"Group-Name","Rollno","Name","cpi","Averagecpi","Guide"};
		dTable2=new DefaultTableModel(data2,columns2);
		
		table2=new JTable(dTable2);
		b1=new JButton("Convert to PDF");
		b2=new JButton("FINISH");
		
		
		b1.addActionListener(this);
		b2.addActionListener(this);

		JScrollPane scrollpane=new JScrollPane(table2);
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b1,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=0.1;
		c.weighty=0.0;
		p.add(b2,c);
		
		p1.add(scrollpane);

		jf.setSize(1500,1200);
	
		jf.setLayout(null);
		p1.setBounds(50,20,990,650);
		p.setBounds(1100,0,140,700);
		jf.add(p1);
		jf.add(p);
		jf.show();
	
		try
		{
			mongo=new Mongo("localhost",27017);
		}
		catch(Exception e)
		{

		}
		if(class_nm.equals("S.Y"))
		{
			gosy();
		}
		if(class_nm.equals("T.Y"))
		{
			goty();
		}
		if(class_nm.equals("B.Tech"))
		{
			gobt();
		}
		
	}
	void gosy()
	{
		Object tmprow[];
		Object o,o1,o2,o3,o4,o5;
		db=mongo.getDB("test");
		coll=db.getCollection("guide1");
		cur=coll.find().sort(new BasicDBObject("sr",1));
		DBObject db1;
		try
		{
			cur=coll.find().sort(new BasicDBObject("sr",1));
			while(cur.hasNext())
			{
				db1=cur.next();
				o=db1.get("group_name");
				o1=db1.get("rollno");
				o2=db1.get("name");
				o3=db1.get("cpi");
				o4=db1.get("avg_cpi");
				o5=db1.get("guide");
				
				tmprow=new Object[]{o,o1,o2,o3,o4,o5};
				dTable2.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}
		
	}
	void goty()
	{
		Object tmprow[];
		Object o,o1,o2,o3,o4,o5;
		db=mongo.getDB("test");
		coll=db.getCollection("guide2");
		cur=coll.find().sort(new BasicDBObject("sr",1));
		DBObject db1;
		try
		{
			cur=coll.find().sort(new BasicDBObject("sr",1));
			while(cur.hasNext())
			{
				db1=cur.next();
				o=db1.get("group_name");
				o1=db1.get("rollno");
				o2=db1.get("name");
				o3=db1.get("cpi");
				o4=db1.get("avg_cpi");
				o5=db1.get("guide");
				
				tmprow=new Object[]{o,o1,o2,o3,o4,o5};
				dTable2.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}
		
	}
	void gobt()
	{
		Object tmprow[];
		Object o,o1,o2,o3,o4,o5;
		db=mongo.getDB("test");
		coll=db.getCollection("guide3");
		cur=coll.find().sort(new BasicDBObject("sr",1));
		DBObject db1;
		try
		{
			cur=coll.find().sort(new BasicDBObject("sr",1));
			while(cur.hasNext())
			{
				db1=cur.next();
				o=db1.get("group_name");
				o1=db1.get("rollno");
				o2=db1.get("name");
				o3=db1.get("cpi");
				o4=db1.get("avg_cpi");
				o5=db1.get("guide");
				
				tmprow=new Object[]{o,o1,o2,o3,o4,o5};
				dTable2.addRow(tmprow);
			}
		}
		catch(Exception ee)
		{
			ee.getMessage();
		}
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		JButton b=(JButton)ae.getSource();
		if(b==b2)
		{		
			jf.setVisible(false);
			new Gui11();
		}

	}
	public void valueChanged(ListSelectionEvent e)  
	{  
       	}	  
   
   	private void displayRowValues(int rowIndex)  
        	{  
    	}  
}

class Gui11 extends JFrame 
{
	ImageIcon img;
	JLabel l1,Jimg;
	JLayeredPane Jlp;
	public Gui11()
	{
		super("THANK YOU");
		setSize(850,530);
		setVisible(true);
		setLayout(null);
		setLocation(180,100);
		Jlp=getLayeredPane();
		
		img=new ImageIcon("thank.jpg");
		Jimg=new JLabel(img);
		Jimg.setBounds(-30,0,900,500);
		Jlp.add(Jimg,new Integer(2));
	}
	
}
class Help extends JFrame
{
	public JTabbedPane jtp;
	public GroupFormer ip;
	public GroupEvaluator cp;
	public HelpPanel hp;
	public Back bk;
	JFrame jf;
	public Help()
	{
		super("GrFE");
		jtp=new JTabbedPane();
		
		ip=new GroupFormer();
		cp=new GroupEvaluator();
		hp=new HelpPanel();
		jf=new JFrame();
		bk=new Back(jf);
		
		jtp.addTab("Group Former",ip);
		jtp.addTab("Group Evaluator",cp);
		jtp.addTab("About Software",hp);
		jtp.addTab("Return",bk);
		jtp.setBounds(0,0,350,350);
		
		jf.add(jtp);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		jf.setSize(800,550);
		jf.setVisible(true);
	}
}	

	class GroupFormer extends JPanel 
	{
		ImageIcon img;
		JLabel Jimg;
		JPasswordField Jpw,Jpw2;
		public GroupFormer()
		{
			img=new ImageIcon("GroupFormer.png");
			Jimg=new JLabel(img);
			Jimg.setBounds(-5,0,500,400);
			add(Jimg);
		}
	}
	class GroupEvaluator extends JPanel
	{
		ImageIcon img;
		JLabel Jimg;
		JPasswordField Jpw,Jpw2;
		public GroupEvaluator()
		{
			img=new ImageIcon("Evaluator.png");
			Jimg=new JLabel(img);
			Jimg.setBounds(-5,0,500,400);
			add(Jimg);
		}
	}
	class HelpPanel extends JPanel
	{
		ImageIcon img;
		JLabel Jimg;
		JPasswordField Jpw,Jpw2;
		public HelpPanel()
		{
			img=new ImageIcon("GrF&E.png");
			Jimg=new JLabel(img);
			Jimg.setBounds(-5,0,500,400);
			add(Jimg);
		}
	}
	class Back extends JPanel implements ActionListener
	{
		JButton b1;
		JLabel la;
		JFrame jf;
		public Back(JFrame jf1)
		{
			jf=jf1;
			la=new JLabel("Click on Back Button to go  back");
			la.setBounds(100,100,30,30);
			b1=new JButton("BACK");
			b1.setBounds(100,200,20,20);
			add(la);
			add(b1);
			b1.addActionListener(this);
		
		}
		public void actionPerformed(ActionEvent e)
		{
			JButton b=(JButton)e.getSource();
			if(b==b1)
			{
				jf.setVisible(false);
				new Gui2();
			}
		}
	}