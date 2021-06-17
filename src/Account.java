package finalprojectoop;

import java.time.LocalDateTime;
import java.util.Scanner;

interface IAccount{
    void displayInfo();
    void deposit(double amount);
    void withdraw(double amount, int min);
    void transfer(Account account,double balance);
}
abstract class Account implements IAccount, Icsvwriter{
    private  Person person;
    private String accountType;
    private int pin;
    private final String accountNo;
    private double balance;
    private float profit;

    Account(Person person, String accountNo, int pin, String accountType, double balance){
        this.person=person;
        this.accountType = accountType;
        this.accountNo = accountNo;
        this.pin = pin;
        this.balance = balance;
        person.modifyBankAccounts(this,1);
        System.out.println("Account Created!");
        displayInfo();
    }

    Account(Person person, String regNo, int pin, String accountType, double balance, float profit){
        this(person, regNo, pin, accountType, balance);
        this.profit = profit;
    }

    protected double getBalance(){
        return balance;
    }

    protected String getAccountNo(){
        return accountNo;
    }

    protected String getAccountType(){ return accountType; }

    protected String getPersonNIC(){ return person.getNIC(); }


    public void displayInfo(){
        System.out.println("Name: " + person.getName() + "\nAccount Registration Number: "
                + accountNo + "\nAccount Balance: " + getBalance());
    }

    protected void addProfit(){
        double sum = balance*profit;
        balance += sum;
        System.out.println("Profit Rs. "+sum+" added to your balance. Balance: Rs. "+balance);
    }

    public void deposit(double amount){
        if (amount > 0){
            CSV csv = new CSV("Transaction"+fileExtension,"Account No.,Transaction,Time\n");
            csv.writeCSV(accountNo+","+"+"+amount+","+ LocalDateTime.now()+";");
            balance +=  amount;
            System.out.println("You deposited Rs. " + amount + ". Balance: Rs. " + balance);
        }
        else {
            System.out.println("You cannot deposit negative or zero amount to a account.");
        }
    }

    public void withdraw(double amount, int min){
        if ((balance-amount)>=min){
            System.out.println("Enter the pin: ");
            Scanner in = new Scanner(System.in);
            int pin = in.nextInt();
            if (pin!=this.pin){
                System.out.println("Pin not Correct!");
                return;
            }
            CSV csv = new CSV("Transaction"+fileExtension,"Account No.,Transaction,Time\n");
            csv.writeCSV(accountNo+","+"-"+amount+","+ LocalDateTime.now()+";");
            balance -= amount;
            System.out.println("You withdrew Rs. " + amount + ". Balance: Rs. " + balance);
        }
        else{
            System.out.println("You cannot withdraw Rs. " + amount +
                    ". Minimum amount that should remain in account is Rs. "+ min + ".");
        }
    }
    abstract public void transfer(Account account,double balance);
    public static String readCSV(){
        CSV csv = new CSV("Account"+fileExtension);
        return csv.readCSV();
    }

    abstract public void writeCSV();

    protected CSV getCSV(){
        CSV csv = new CSV("Account"+fileExtension,"Owner's NIC,Account No.,Account Type,Balance," +
                "Profit Date\n");
        return csv;
    }

    public abstract void withdraw(double balance);
}