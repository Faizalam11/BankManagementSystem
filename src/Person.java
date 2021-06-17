package finalprojectoop;
import java.util.ArrayList;

public class Person implements Icsvwriter {
    private final String name;
    private int age;
    private String phoneNumber;
    private final String NIC;
    private final char gender;
    private String address;
    ArrayList<Account> bankAccounts = new ArrayList<Account>();
    String getName(){
        return name;
    }
    void incrementAge(){
        age++;
    }
    int getAge(){
        return age;
    }
    void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    String getPhoneNumber(){
        return phoneNumber;
    }
    String getNIC(){
        return NIC;
    }
    char getGender(){
        return gender;
    }
    void setAddress(String address){
        this.address = address;
    }
    String getAddress(){
        return address;
    }
    void modifyBankAccounts(Account acc, int addOrRemove){ // 1 add 0 remove
        if (addOrRemove==1){
            this.bankAccounts.add(acc);
        }
        else{
            for (int i=0;i<bankAccounts.size();i++){
                Account e = bankAccounts.get(i);
                if (e.getAccountNo().equals(acc.getAccountNo())){
                    bankAccounts.remove(i);
                }
            }
        }
    }

    Person(String name, int age, String PhoneNumber, String NIC, char Gender, String Address)
    {
        this.name=name;
        this.age=age;
        this.phoneNumber=PhoneNumber;
        this.NIC=NIC;
        this.gender=Gender;
        this.address=Address;
    }
    double getBalance(){
        double balance = 0;
        for (int i=0;i<bankAccounts.size();i++) {
            Account e = bankAccounts.get(i);
            balance += e.getBalance();
        }
        return balance;
    }
    public void writeCSV(){
        CSV csv = new CSV("Person"+fileExtension,"Name,Age,NIC,Gender,Phone Number,Address," +
                "Number of Bank Accounts,Bank Accounts Number,Total Balance\n");
        String bankAccountNumbers = "";
        for (int i=0;i<bankAccounts.size();i++) {
            Account e = bankAccounts.get(i);
            bankAccountNumbers += e.getAccountNo();
            bankAccountNumbers += ":";
        }
        csv.writeCSV(name+","+age+","+NIC+","+gender+","+phoneNumber+","+address+","+bankAccounts.size()+","+
                bankAccountNumbers+","+getBalance());
    }
    public static String readCSV(){
        CSV csv = new CSV("Person"+fileExtension);
        return csv.readCSV();
    }
}
