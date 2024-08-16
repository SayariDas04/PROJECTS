import java.util.Scanner;
class AddBook{
	sqlCon scon = null;
	Scanner sc = null;
	String book,author;
	AddBook(sqlCon scon){
		sc = new Scanner(System.in);
		this.scon = scon;
	}
	public void addBook(){
		System.out.println("Add a book");
		System.out.print("Enter the name of the book: ");
		book = sc.nextLine();
		System.out.println();
		System.out.print("Enter the name of the author: ");
		author = sc.nextLine();
		System.out.println();
		scon.addB(book,author);
	}
}
class Search{
	sqlCon scon = null;
	String book,author;
	int requirement;
	Scanner sc = null;
	Search(sqlCon scon){
		sc = new Scanner(System.in);
		this.scon = scon;
	}
	public void choice(){
		System.out.println("\nMENU\n1. SEARCH WITH THE NAME OF BOOK\n2. SEARCH WITH THE AUTHOR NAME\n3. SEARCH WITH BOTH BOOOK AND AUTHOR NAME");
		System.out.print("Enter your requirement: ");
		try{
			requirement = sc.nextInt();
		}
		catch(Exception e){
			System.out.println("Wrong input");
			return;
		}
		sc.nextLine();
		switch(requirement){
			case 1:
				System.out.println("Enter the name of the book: ");
				book = sc.nextLine();
				this.searchWithBookName(book);
				break;
			case 2:
				System.out.println("Enter the name of the author: ");
				author = sc.nextLine();
				this.searchWithAuthor(author);
				break;
			case 3:
				System.out.print("Enter the name of the book: ");
				book=sc.nextLine();
				System.out.println();
				System.out.print("Enter the name of the author: ");
				author=sc.nextLine();
				System.out.println();
				this.searchWithBoth(book,author);
				break;
			default:
				System.out.println("Don't you want a book?");
		}	
	}
	public void searchWithBookName(String book){
		scon.searchByBookName(book);
	}
	public void searchWithAuthor(String author){
		scon.searchAuthor(author);
	}
	public void searchWithBoth(String book,String author){
		scon.searchBoth(book,author);
	}
}
class ListBooks{
	sqlCon scon = null;
	Scanner sc = null;
	ListBooks(sqlCon scon){
		sc = new Scanner(System.in);
		this.scon = scon;
	}
	public void listOfBooks(){
		scon.list();
	}
}
public class driver{
	public static void main(String args[]){
		sqlCon scon = new sqlCon();
		AddBook ab = new AddBook(scon);
		Search s = new Search(scon);
		ListBooks lb = new ListBooks(scon);
		Scanner sc = new Scanner(System.in);
		int ch,requirement;
		String book,author;
		try{
			while(true){
				try{
					System.out.println("\nMENU\n1. ADD BOOK\n2. SEARCH BOOK\n3. LIST OF BOOKS\n4. EXIT");
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
						ab.addBook();
						break;
					case 2:
						s.choice();
						break;
					case 3:
						lb.listOfBooks();
						break;
					case 4:
						System.out.println("Exited");
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
			scon.closeConnection();
		}
	}
}