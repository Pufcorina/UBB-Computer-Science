package model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Transaction extends Inventory implements Runnable {
    private String name;
    private float totalPrice = 0.0f;
    private Boolean inventoryChanged;
    private Inventory deposit;
    private Lock _mutex = new ReentrantLock();

    public Transaction(Inventory deposit, String name) {
        this.deposit = deposit;
        this.name = name;
    }

    @Override
    public void run() {
        for (Product product : this.getProducts()){
            _mutex.lock();
            try {
                deposit.remove(product, this.getQuantity(product));
                System.out.println(this.name + ": took " + product.getName() + " -> " + String.valueOf(this.getQuantity(product)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            _mutex.unlock();
        }
    }

    @Override
    public void add(Product product, int quantity) {
        super.add(product, quantity);
        inventoryChanged = true;
    }

    @Override
    public void remove(Product product, int quantity) {
        super.remove(product, quantity);
        inventoryChanged = true;
    }

    public String getName() {
        return this.name;
    }

    public float getTotalPrice() {
        if (inventoryChanged == null) return 0.0f;
        if (inventoryChanged) {
            this.totalPrice = 0;
            for (Product product : this.getProducts()){
                this.totalPrice += this.getQuantity(product) * product.getPrice();
            }
            this.inventoryChanged = false;
        }

        return this.totalPrice;
    }
}
