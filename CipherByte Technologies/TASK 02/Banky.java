import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Banky{
	static Scanner sc = null;
	static Statement st = null;
	static ResultSet rs = null;
	static Connection con = null;
	static Dashboard db = null;
	static String password,userName;
	public static void logIn(){
		sc.nextLine();
		System.out.print("Enter the user name: ");
		userName = sc.nextLine();
		System.out.print("Enter the password: ");
		password = sc.nextLine();
		System.out.println();
		try{
			rs = st.executeQuery("select * from authentication where userName = '"+userName+"' and password = '"+password+"';");
			if(rs.next()){
				int cid =rs.getInt("customerId");
				db.getAccountNo(cid);
				db.choice();
			}
			else{
				System.out.println("Username and password mismatched!");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static void main(String args[]){
		String name,panNo,city;
		int customerID,password,ch;
		float balance;
		long accountNo,aadhaarNo;
		sc = new Scanner(System.in);
		try{
			String url = "jdbc:mysql://127.0.0.1:3306";
			String user = "root";
			String passwd = "root";
			con = DriverManager.getConnection(url,user,passwd);
			//System.out.println(this.con.isValid(1));
			st = con.createStatement();
			st.execute("use Banky;");
		}catch(Exception e){
			System.out.println(e);
			System.exit(1);
		}
		Signup sn = new Signup(con, st);
		db = new Dashboard(con, st);
		try{
			while(true){
				try{
					System.out.println("\nMENU\n1. CREATE AN ACCOUNT\n2. LOGIN\n3. EXIT\n");
					System.out.print("Enter your choice: ");
					ch = sc.nextInt();
				}
				catch(Exception e){
					sc.nextLine();
					System.out.println("Wrong input");
					continue;
				}
				System.out.println();
				switch(ch){
					case 1:
						sn.createAccount();
						break;
					case 2:
						logIn();
						break;
					case 3:
						System.exit(1);
					default:
						System.out.println("Wrong choice");
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try{
				con.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}