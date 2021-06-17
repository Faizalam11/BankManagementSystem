package finalprojectoop;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Person> persons = LoadData.getPersonsData();
        ArrayList<Account> accounts = LoadData.getAccountsData();
        if (persons==null&&accounts==null) {
        persons = new ArrayList<Person>();
        accounts = new ArrayList<Account>();
        }
        System.out.println("Welcome to the Bank!");
        Boolean cond = Boolean.TRUE;
        while (cond){
            System.out.println("Press 1 to register as a person\n" +
                    "Press 2 to register an account (After Registering as a person)\n" +
                    "Press 3 to deposit money to an account\n" +
                    "Press 4 to withdraw money from an account\n" +
                    "Press 5 to transfer money from an account to another account\n" +
                    "Press 0 to exit");
            int input = in.nextInt();
            switch (input){
                case 0:
                    for (Person p: persons) {
                        p.writeCSV();
                    }
                    for (Account acc: accounts) {
                        acc.writeCSV();
                    }
                    cond = Boolean.FALSE;
                    break;
                case 1:
                    createPerson(persons);
                    break;
                case 2:
                    createAccount(accounts,persons);
                    break;
                case 3:
                    Account acc = getAccountByNIC(persons,accounts);
                    System.out.println("Enter amount to deposit:");
                    double amount = in.nextDouble();
                    acc.deposit(amount);
                    break;
                case 4:
                    Account account = getAccountByNIC(persons,accounts);
                    System.out.println("Enter amount to withdraw:");
                    double balance = in.nextDouble();
                    account.withdraw(balance);
                    break;
                case 5:
                    System.out.println("Account to transfer From: ");
                    Account source = getAccountByNIC(persons,accounts);
                    System.out.println("Account to transfer To: ");
                    Account dest = getAccountByNIC(persons,accounts);
                    System.out.println("Enter amount to transfer:");
                    double Balance = in.nextDouble();
                    source.transfer(dest,Balance);
                    break;
            }
        }
    }
    static Person getPerson(ArrayList<Person> persons, String NIC){
        for (Person p:persons) {
            if (p.getNIC().equals(NIC)){
                return p;
            }
        }
        return null;
    }
    static Account getAccountByAccountNo(ArrayList<Account> accounts, String accountNo){
        for (Account acc:accounts){
            if (acc.getAccountNo().equals(accountNo)){
                return acc;
            }
        }
        return null;
    }
    static void createPerson(ArrayList<Person> persons){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your Name: ");
        String name = in.nextLine();
        System.out.println("Enter your Gender: ");
        char gender = in.nextLine().charAt(0);
        System.out.println("Enter your Age: ");
        int age = Integer.parseInt(in.nextLine());
        System.out.println("Enter your NIC: ");
        String NIC = in.nextLine();
        System.out.println("Enter your Phone Number: ");
        String phoneNumber = in.nextLine();
        System.out.println("Enter your Address: ");
        String address = in.nextLine();
        persons.add(new Person(name,age,phoneNumber,NIC,gender,address));
    }
    static void createAccount(ArrayList<Account> accounts, ArrayList<Person> persons){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your NIC: ");
        String NIC = in.nextLine();
        Person person = getPerson(persons,NIC);
        if (person==null){
            System.out.println("Person is not Registered.");
            return;
        }
        System.out.println("Enter your Bank Account Type (Current, Saving or Fixed Deposit):");
        String accountType = in.nextLine();
        System.out.println("Enter pin for Account:");
        int pin = in.nextInt();
        System.out.println("Enter amount to deposit to Account:");
        double amount = in.nextDouble();
        String accountNo = UUID.randomUUID().toString();
        System.out.println("Your Account No is "+accountNo);
        switch (accountType){
            case "Current":
                accounts.add(new CurrentAccount(person,accountNo,pin,amount));
                break;
            case "Saving":
                accounts.add(new SavingAccount(person,accountNo,pin,amount));
                break;
            case "Fixed Deposit":
                accounts.add(new FixedDepositAccount(person,accountNo,pin,amount));
                break;
        }
    }
    static Account getAccountByNIC(ArrayList<Person> persons, ArrayList<Account> accounts){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your NIC: ");
        String NIC = in.nextLine();
        Person person = getPerson(persons,NIC);
        System.out.println("Your Bank Accounts are: ");
        for (Account acc:person.bankAccounts) {
            System.out.println(acc.getAccountType()+" Account : "+acc.getAccountNo());
        }
        System.out.println("Enter Account No:");
        String accountNo = in.nextLine();
        Account acc = getAccountByAccountNo(accounts,accountNo);
        return acc;
    }
}
