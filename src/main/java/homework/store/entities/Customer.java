package homework.store.entities;

/**
 *
 * @author YBolshakova
 */
public class Customer {
        
    private int id;
    private String name;
    private int personalNumber;

    public Customer() {
    }

    public Customer(int id, String name, int personalNumber) {
        this.id = id;
        this.name = name;
        this.personalNumber = personalNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }
    
    

}
