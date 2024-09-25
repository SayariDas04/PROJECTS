import java.util.Scanner;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Currency{
	static float rupee,dollar,euro,pound,KWD;
	static int code;
	public static void currencyConvert(float rupee){
		dollar = rupee/77.34f;
		pound = rupee/94.46f;
		euro = rupee/80.36f;
		KWD = rupee/251.92f;
		System.out.println("The amount in Rupees: "+rupee);
		System.out.println("The amount in Dollar: "+dollar);
		System.out.println("The amount in Euro: "+euro);
		System.out.println("The amount in Pound: "+pound);
		System.out.println("The amount in Kuwaiti diner: "+KWD+"\n");
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Enter the currency code: \n1. Rupees\n2. Dollar\n3. Euro\n4. Pound\n5. Kuwaiti diner\n6. Exit\n");
			code = sc.nextInt();
			switch(code){
				case 1:
					System.out.println("Enter the amount in Rupees: ");
					rupee = sc.nextInt();
					currencyConvert(rupee);
					break;
				case 2:
					System.out.println("Enter the amount in Dollar: ");
					dollar = sc.nextInt();
					currencyConvert(dollar*77.34f);
					break;
				case 3:
					System.out.println("Enter the amount in Euro: ");
					euro = sc.nextInt();
					currencyConvert(euro*80.36f);
					break;
				case 4:
					System.out.println("Enter the amount in Pound: ");
					pound = sc.nextInt();
					currencyConvert(pound*94.46f);
					break;
				case 5:
					System.out.println("Enter the amount in Kuwaiti diner: ");
					KWD = sc.nextInt();
					currencyConvert(KWD*251.92f);
					break;
				case 6:
					System.exit(1);
				default:
					continue;
			}
		}while(true);
	}
}