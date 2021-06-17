package finalprojectoop;

import java.util.ArrayList;

public class LoadData {
    static ArrayList<Person> getPersonsData(){
        ArrayList<Person> persons = new ArrayList<Person>();
        String data = Person.readCSV();
        if (data==null){
            return null;
        }
        String[] Data = data.split(";");
        for (int i = 1; i < Data.length; i++) {
            String[] DataSegments = Data[i].split(",");
            persons.add(new Person(DataSegments[0],Integer.parseInt(DataSegments[1]),
                    DataSegments[4],DataSegments[2],DataSegments[3].charAt(0),DataSegments[5]));
        }
        return persons;
    }
    static ArrayList<Account> getAccountsData(){
        ArrayList<Person> persons = getPersonsData();
        ArrayList<Account> accounts = new ArrayList<Account>();
        String data = Account.readCSV();
        if (data==null){
            return null;
        }
        String[] Data = data.split(";");
        Person person=null;
        for (int i = 1; i < Data.length; i++) {
            String[] DataSegments = Data[i].split(",");
            for (int j = 0; j < persons.size(); j++) {
                person = persons.get(j);
                if (person.getNIC().equals(DataSegments[1])){
                    break;
                }
                person = null;
            }
            if (person==null){
                continue;
            }
            switch (DataSegments[2]){
                case "Current":
                    accounts.add(new CurrentAccount(person,DataSegments[1],Integer.parseInt(DataSegments[5]) ,
                            Double.parseDouble(DataSegments[3])));
                    break;
                case "Saving":
                    accounts.add(new SavingAccount(person,DataSegments[1], Integer.parseInt(DataSegments[5]),
                            Double.parseDouble(DataSegments[3]), DataSegments[4]));
                    break;
                case "Fixed Deposit":
                    accounts.add(new FixedDepositAccount(person,DataSegments[1], Integer.parseInt(DataSegments[5]),
                            Double.parseDouble(DataSegments[3]), DataSegments[4]));
                    break;
            }
        }
        return accounts;
    }

}