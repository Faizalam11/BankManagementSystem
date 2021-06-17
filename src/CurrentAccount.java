package finalprojectoop;
public class CurrentAccount extends Account {
    private int pin;
    CurrentAccount(Person person, String regNo, int pin, double balance) {
        super(person, regNo, pin, "Current", balance, 0);
        this.pin = pin;
    }

    @Override
    public void transfer(Account account, double balance) {
        this.withdraw(balance);
        account.deposit(balance);
    }

    public void withdraw(double amount) {
        super.withdraw(amount, 0);
    }

    @Override
    public void writeCSV() {
        CSV csv = getCSV();
        csv.writeCSV(getPersonNIC()+","+getAccountNo()+","+getAccountType()+","+getBalance()+","+
                "Null"+","+pin);
    }
}
