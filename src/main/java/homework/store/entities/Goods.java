package homework.store.entities;

/**
 *
 * @author YBolshakova
 */
public class Goods {
    
    private int id;
    private String name;
    private int articul;    

    public Goods() {
    }

    public Goods(int id, String name, int articul) {
        this.id = id;
        this.name = name;
        this.articul = articul;
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

    public int getArticul() {
        return articul;
    }

    public void setArticul(int articul) {
        this.articul = articul;
    }
    
    

}
