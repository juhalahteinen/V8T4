package com.example.v8t4;

import java.util.ArrayList;

public class BottleDispenser {


    ArrayList<Bottle> pullolista = new ArrayList<Bottle>();

    private int bottles;
    float money;
    private String name;
    String volume;
    float price = 0;

    private static BottleDispenser bd = null;

    public static BottleDispenser getInstance() {
        if (bd == null) {
            bd = new BottleDispenser();
        }
        return bd;
    }

    public BottleDispenser() {

        bottles = 5;

        for (int i = 0; i < bottles; i++) {
            if (i == 0) {
                name = "PepsiMax";
                volume = "0.5";
                price = 1.80f;
            }

            if (i == 1) {
                name = "PepsiMax";
                volume = "1.5";
                price = 2.2f;
            }

            if (i == 2) {
                name = "Coca-ColaZero";
                volume = "0.5";
                price = 2.0f;
            }

            if (i == 3) {
                name = "Coca-ColaZero";
                volume = "1.5";
                price = 2.5f;
            }

            if (i == 4) {
                name = "FantaZero";
                volume = "0.5";
                price = 1.95f;
            }

            pullolista.add(i, new Bottle(name, volume, price));

        }
    }
    public void addMoney(float m) {
        money = m;
        System.out.println(money);
    }

    public int buyBottle(String bottleName, String bottleVolume, float hinta) {
        int x;

        if (money >= hinta) {
            for (x = 0; x <= (bottles - 1); x++) {
                if (bottleName.equals(pullolista.get(x).getName()) && bottleVolume.equals(pullolista.get(x).getSize())) {
                    money = money - pullolista.get(x).getCost();
                    bottles -= 1;
                    pullolista.remove(x);
                    break;
                }
            }
            return 1;
        } else {
            return 2;
        }
    }

    public float returnMoney() {
        float rahat;
        rahat = money;
        money = 0;
        return rahat;
    }
}
