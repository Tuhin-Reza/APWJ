import java.time.LocalDate;
import java.util.Scanner;

public class Employee extends Person {
    int count=0;
    public void  input(){
        LocalDate now = LocalDate.now();
        int present_year=now.getYear();
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter Name");
        name = myObj.nextLine();

        System.out.println("Enter Birth Year (Less Then Present Year "+present_year+")");
        year = myObj.nextInt();

        System.out.println("Enter Birth Month (Month must be 1 to 12)");
        month = myObj.nextInt();

        System.out.println("Enter Birth Date (Date must be 1 to 31)");
        date = myObj.nextInt();

       if(present_year<year){
            count++;
            System.out.println("Your birth year must be less then "+ present_year+" ");
        }else if(month<1 || month>12){
           count++;
           System.out.println("Your birth month must be greater then 0 and less then 12  ");
        }else if(date<1 || date>=31) {
           count++;
           System.out.println("Your birth date must be greater then 0 and less then 31  ");
       }
    }
    public void display(){
        System.out.println(("Working"));
    }
}
