import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class sqlCon {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    Scanner sc = null;

    sqlCon() {
        this.sc = new Scanner(System.in);
        String url = "jdbc:mysql://127.0.0.1:3306/?user=root";
        String user = "root";
        String password = "root";

        try {
            this.con = DriverManager.getConnection(url,user,password);
            //System.out.println(this.con.isValid(1));
            this.st = this.con.createStatement();
            this.st.execute("use library;");
        } catch (Exception var5) {
            System.out.println(var5);
        }

    }

    public void addB(String book, String author) {
        try {
			this.rs = this.st.executeQuery("SELECT * FROM books WHERE aName LIKE '%" + author + "%' AND bName LIKE '%" + book + "%';");
			if(!rs.next()){
				this.st.execute("INSERT INTO books (bName,aName) VALUES ('" + book + "','" + author + "');");
				System.out.println("Added successfully");
			}
			else
				System.out.println("Book Already in Stock");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void searchByBookName(String book) {
        try {
            this.rs = this.st.executeQuery("SELECT * FROM books WHERE bName LIKE '%" + book + "%';");
            System.out.printf("%-10s", "Book ID");
            System.out.printf("%-60s", "Book Name");
            System.out.printf("%-40s", "Author");
            System.out.println();
            System.out.println("-----------------------------------------------------------");

            while(this.rs.next()) {
                System.out.printf("%-10d",this.rs.getInt("id"));
                System.out.printf("%-60s", this.rs.getString("bName"));
                System.out.printf("%-40s", this.rs.getString("aName"));
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void searchAuthor(String author) {
        try {
            this.rs = this.st.executeQuery("SELECT * FROM books WHERE aName LIKE '%" + author + "%';");
            System.out.printf("%-10s", "Book ID");
            System.out.printf("%-60s", "Book Name");
            System.out.printf("%-40s", "Author");
            System.out.println();
            System.out.println("-----------------------------------------------------------");

            while(this.rs.next()) {
                System.out.printf("%-10d", this.rs.getInt("id"));
                System.out.printf("%-60s", this.rs.getString("bName"));
                System.out.printf("%-40s", this.rs.getString("aName"));
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void searchBoth(String book, String author) {
        try {
            this.rs = this.st.executeQuery("SELECT * FROM books WHERE aName LIKE '%" + author + "%' AND bName LIKE '%" + book + "%';");System.out.println(book + " , " + author);
            System.out.printf("%-10s", "Book ID");
            System.out.printf("%-60s", "Book Name");
            System.out.printf("%-40s", "Author");
            System.out.println();
            System.out.println("-----------------------------------------------------------");

            while(this.rs.next()) {
                System.out.printf("%-10d", this.rs.getInt("id"));
                System.out.printf("%-60s", this.rs.getString("bName"));
                System.out.printf("%-40s", this.rs.getString("aName"));
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void list() {
        try {
            rs = this.st.executeQuery("SELECT * FROM books");
            System.out.printf("%-10s", "Book ID");
            System.out.printf("%-60s", "Book Name");
            System.out.printf("%-40s", "Author");
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

            while(rs.next()) {
                int bid = rs.getInt("id");
                String b = rs.getString("bName");
                String a = rs.getString("aName");
                System.out.printf("%-10d", bid);
                System.out.printf("%-60s", b);
                System.out.printf("%-40s", a);
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void closeConnection() {
        try {
            this.con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
