import java.sql.*;
import java.util.Scanner;
public class Dashboard{
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    Scanner sc = null;
	float balance = 0f,minBalance=100f;
	long accountNo = 0L;
	String name = null;
	int ch = 0;
	Dashboard(Connection con,Statement st){
		this.con = con;
		this.st = st;
		this.sc = new Scanner(System.in);
	}
	public void getAccountNo(int cid){
		try{
		rs = st.executeQuery("select accountNo from customers where customerId ="+cid+";");
		rs.next();
		accountNo = rs.getLong("accountNo");
		}
		catch(Exception e){
		}
	}
	public void choice(){
		while(true){
			try{
				rs = st.executeQuery("select name from customers where accountNo ="+accountNo+";");
				rs.next();
				System.out.println("Welcome "+rs.getString("name")+"\nAccount no: "+accountNo);
				System.out.println("\nMENU\n1. DEPOSIT\n2. WITHDRAW\n3. FUND TRANSFER\n4. VIEW BALANCE\n5. EXIT\n");
				
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
					System.out.println();
					this.deposit(accountNo);
					break;
				case 2:
					System.out.println();
					this.withdraw(accountNo);
					break;
				case 3:
					sc.nextLine();
					System.out.print("Enter the amount to transfer: ");
					System.out.println();
					float amount = sc.nextFloat();
					sc.nextLine();
					System.out.print("Enter the account no where you want to transfer: ");
					long account2 = sc.nextLong();
					this.fundTransfer(accountNo,account2,amount);
					break;
				case 4:
					System.out.println();
					this.viewBalance(accountNo);
					break;
				case 5:
					System.exit(1);
				default:
					System.out.println("Wrong choice!");
			}
		}
	}
	public void viewBalance(long account){
        try {
			this.rs = this.st.executeQuery("SELECT balance FROM customers WHERE accountNo = "+accountNo+";");
			rs.next();
			System.out.println("Balance: "+rs.getString("balance"));
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	public void deposit(long accountNo){
		try{
			System.out.println("Enter the amount you want to credit: ");
			balance = sc.nextFloat();
			st.execute("UPDATE customers SET balance = balance +"+balance+" WHERE accountNo = "+accountNo+";");
			System.out.println("Money added successfully");
		}
		catch(Exception e){
		}
	}
	public void withdraw(long accountNo){
		try{
			System.out.print("Enter the amount you want to withdraw: ");
			balance = sc.nextFloat();
			rs = st.executeQuery("SELECT balance FROM customers WHERE accountNo = "+accountNo+";");
			rs.next();
			float balanceAvailable = rs.getFloat("balance");
			if((balanceAvailable-balance)>=minBalance){
				st.execute("UPDATE customers SET balance = balance -"+balance+" WHERE accountNo = "+accountNo+";");
			}
			else{
				System.out.println("You don't have sufficient balance!"); 
			}
		}
		catch(Exception e){
		}
	}
	public void fundTransfer(long account1,long account2,float amount){
		try{
			st.execute("update customers set balance = balance - "+amount+" where accountNo = "+account1+";");
			st.execute("update customers set balance = balance + "+amount+" where accountNo = "+account2+";");
		}
		catch(Exception ignored){}
		}
}