package homework.store.entities;

import java.util.List;

/**
 *
 * @author YBolshakova
 */
public class Order {
    
    private int id;
    private String code;
    private Client client;
    private List<Goods> goods;

    public Order() {
    }

    public Order(int id, String code, Client client, List<Goods> goods) {
        this.id=id;
        this.code = code;
        this.client = client;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Client getCustomer() {
        return client;
    }

    public void setCustomer(Client customer) {
        this.client = customer;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", code=" + code + ", client=" + client + ", goods=" + goods + '}';
    }
    
    

}
