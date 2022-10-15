import java.time.LocalDate;
import java.time.Period;

public class age_calculation <T>extends Employee{
    public T presentDate;
    public age_calculation(T x)
    {
        presentDate = x;
    }
    public T getFirst(){
        return presentDate;
    }
    public void ageCalculation(){
        super.input();
        if(super.count==0){
            LocalDate dob = LocalDate.of(super.year,super.month,super.date);
            Period period = Period.between(dob, (LocalDate) presentDate);
            System.out.printf(""+super.name+" age  %d years %d months and %d days.",period.getYears(), period.getMonths(), period.getDays());
        }else{
            System.out.println("Your Entered Data Wrong Please Try Again");
        }
    }
}
