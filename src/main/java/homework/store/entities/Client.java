package homework.store.entities;

/**
 *
 * @author YBolshakova
 */
public class Client {
        
    private int id;
    private String name;
    private String phoneNumber;

    public Client() {
    }

    public Client(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    

    public Client(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public String getPersonalNumber() {
        return phoneNumber;
    }

    public void setPersonalNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + '}';
    }
    
    
    
    

}
