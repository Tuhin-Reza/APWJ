import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        LocalDate now = LocalDate.now();
        age_calculation<LocalDate> p = new age_calculation<>(LocalDate.now());
        System.out.println(p.getFirst());
        p.ageCalculation();
    }
}