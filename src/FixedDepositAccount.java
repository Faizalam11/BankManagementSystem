package finalprojectoop;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FixedDepositAccount extends Account{
    
    private LocalDateTime lastDeposit;
    private int pin;
    FixedDepositAccount(Person person, String regNo, int pin, double balance) {
        super(person, regNo, pin, "Fixed Deposit" ,balance, 0.1f);
        lastDeposit = LocalDateTime.now();
        this.pin = pin;
    }
    FixedDepositAccount(Person person, String regNo, int pin, double balance, String lastDeposit) {
        super(person, regNo, pin, "Fixed Deposit" ,balance, 0.1f);
        this.lastDeposit = LocalDateTime.parse(lastDeposit);
    }
    @Override
    public void deposit(double amount) {
        if (isAllowed()) {
            super.deposit(amount);
        }
        else {
            System.out.println("You cannot deposit till 180 days after deposit.");
        }
    }

    public void withdraw(double amount) {
        if (isAllowed()) {
            super.withdraw(amount, 0);
        }
        else {
            System.out.println("You cannot withdraw till 180 days after deposit.");
        }
    }

    @Override
    public void transfer(Account account, double balance) {
        this.withdraw(balance);
        account.deposit(balance);
    }

    Boolean isAllowed(){
        LocalDateTime now = LocalDateTime.now();
        long days = ChronoUnit.DAYS.between(lastDeposit,now);
        if (days==180){
            for (int i =0;i<180;i++) {
                addProfit();
            }
            System.out.println("You can now withdraw or deposit in this account.");
            return Boolean.TRUE;
        }
        else if (days>180){
            lastDeposit = LocalDateTime.now();
            System.out.println("You can now not withdraw or deposit in this account for 180 days");
        }
        return Boolean.FALSE;
    }

    @Override
    public void writeCSV() {
        CSV csv = getCSV();
        csv.writeCSV(getPersonNIC()+","+getAccountNo()+","+getAccountType()+","+getBalance()+","+
                lastDeposit.toString()+","+pin);
    }
}
