package homework.store.entities;

import java.util.List;

/**
 *
 * @author YBolshakova
 */
public class Order {
    
    private int id;
    private int code;
    private Customer customer;
    private List<Goods> goods;

    public Order() {
    }

    public Order(int id, int code, Customer customer, List<Goods> goods) {
        this.id = id;
        this.code = code;
        this.customer = customer;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
    
    

}
