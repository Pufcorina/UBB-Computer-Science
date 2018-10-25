package model;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {
    private HashMap<Product, Integer> products;

    public Inventory() {
        this.products = new HashMap<>();
    }

    public void add(Product product, int quantity){
        if (this.products.containsKey(product)){
            this.products.replace(product, this.products.get(product) + quantity);
        }else{
            this.products.put(product, quantity);
        }

    }

    public void remove(Product product, int quantity){
        if (this.products.containsKey(product)){
            if (this.getQuantity(product) < quantity){
                throw new RuntimeException("Cannot remove desired quantity from inventory, existing quantity is not sufficient!");
            }
            this.products.replace(product, this.products.get(product) - quantity);
            if (this.getQuantity(product) == 0){
                this.products.remove(product);
            }

        }else{
            throw new RuntimeException("Cannot remove quantity of inexistent product in inventory!");
        }
    }

    public Set<Product> getProducts(){
        return this.products.keySet();
    }

    public int getQuantity(Product product) {
        return this.products.getOrDefault(product, 0);
    }

    @Override
    public String toString() {
        StringBuilder ss = new StringBuilder();
        for (Product product : this.getProducts()){
            ss.append("{").append(product.getName()).append(", ").append(this.getQuantity(product)).append("}\n");
        }

        return ss.toString();
    }
}
