package finalprojectoop;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SavingAccount extends Account{
    private LocalDateTime profitTime;
    private int pin;
    SavingAccount(Person person, String regNo, int pin, double balance) {
        super(person, regNo, pin, "Saving" , balance, 0.02f);
        profitTime = LocalDateTime.now();
        this.pin = pin;
    }
    SavingAccount(Person person, String regNo, int pin, double balance, String profitTime) {
        super(person, regNo, pin, "Saving" , balance, 0.02f);
        this.profitTime = LocalDateTime.parse(profitTime);
    }



    @Override
    protected void addProfit() {
        LocalDateTime now = LocalDateTime.now();
        long days = ChronoUnit.DAYS.between(profitTime,now);
        if (days>=30){
            for (int i = 0; i < 30; i++) {
                super.addProfit();
            }
            profitTime = LocalDateTime.now();
        }
    }

    public void withdraw(double amount) {
        super.withdraw(amount, 500);
    }

    @Override
    public void transfer(Account account, double balance) {
        this.withdraw(balance);
        account.deposit(balance);
    }

    @Override
    public void writeCSV() {
        CSV csv = getCSV();
        csv.writeCSV(getPersonNIC()+","+getAccountNo()+","+getAccountType()+","+getBalance()+","+
                profitTime.toString()+","+pin);
    }
}
