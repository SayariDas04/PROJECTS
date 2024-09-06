import java.sql.*;
import java.util.Scanner;
class Signup{
	String name,panNo,city,userName,password;
	long aadhaarNo;
	float minBalance=100f,balance;
	int ch;
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    Scanner sc = null;
	Signup(Connection con,Statement st){
		this.con = con;
		this.st = st;
		sc = new Scanner(System.in);
	}
	public void createAccount(){
		System.out.print("Enter the customer's name: ");
		name = sc.nextLine();
		System.out.println();
		System.out.print("Enter the city name: ");
		city = sc.nextLine();
		System.out.println();
		do{
			try{
				System.out.print("Enter the Aadhaar no: ");
				String temp = sc.nextLine();
				if (temp.length() == 12){
					aadhaarNo = Long.parseLong(temp);
					break;
				}
			}
			catch(Exception ignored){}
		}while(true);
		System.out.println();
		do{
			try{
				System.out.print("Enter the Pan Card no: ");
				String temp = sc.nextLine();
				if(temp.length()!=10){
					throw new Exception();
				}
				for(int i=0;i<temp.length();i++){
					// substring(start, end, jump)
					// charAt(index)
					if(i<5 && Character.isAlphabetic(temp.charAt(i))){
						continue;
					}
					if(i>=5 && i<9 && Character.isDigit(temp.charAt(i))){
						continue;
					}
					if(i==9 && Character.isAlphabetic(temp.charAt(i))){
						continue;
					}
					throw new Exception();
				}
				panNo = temp;
				break;
			}
			catch(Exception ignored){}
		}while(true);
		System.out.println();
		do{
			System.out.println("Enter the Opening Balance: ");
			balance = sc.nextFloat();
			sc.nextLine();
			if(balance >= minBalance)
				break;
			else{
				System.out.println("Minimum Balance required: "+minBalance);
				System.out.println("Continue with account opening? [yes / no]");
				String choice = sc.nextLine();
				if (choice.equalsIgnoreCase("yes")||choice.equalsIgnoreCase("y")){
					continue;
				}
				else{
					return;
				}
			}
		}while(balance < minBalance);
		long accountNo = getNewAccountNumber();
		try{
			st.execute("INSERT INTO customers (accountNo,name,balance,panNo,aadhaarNo,city) VALUES ("+accountNo+",'"+name+"',"+balance+",'"+panNo+"','"+aadhaarNo+"','"+city+"');");
			System.out.println("Account created successfully");
			rs = st.executeQuery("SELECT customerId from customers where accountNo = "+accountNo+";");
			rs.next();
			int cid=rs.getInt("customerId");
			System.out.print("Enter a username: ");
			userName = sc.nextLine();
			System.out.println();
			do{
				System.out.print("Enter a password: ");
				password = sc.nextLine();
				System.out.println();
				System.out.print("Enter the password again: ");
				String p = sc.nextLine();
				if(password.equals(p)){
					break;
				}
			}while(true);
			st.execute("insert into authentication (customerId, userName, password) values ("+cid+",'"+userName+"','"+password+"');");
			System.out.println("Registered successfully");	
		}
		catch(Exception e){
		}
	}
	public long getNewAccountNumber(){
		try{
			rs = st.executeQuery("SELECT * FROM customers;");
			if (rs.next()){
				while(!rs.isLast())
					rs.next();
				long acc = Long.parseLong(rs.getString("accountNo"));
				return (acc+1);
			}
		}
		catch(Exception e){
		}
		return 5443000000002L;
	}
}