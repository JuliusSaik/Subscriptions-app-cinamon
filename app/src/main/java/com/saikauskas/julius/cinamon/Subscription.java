package com.saikauskas.julius.cinamon;

import android.content.res.ColorStateList;
import android.support.v7.widget.CardView;

public class Subscription {

    public int id;
    private String subscriptionName, subscriptionCycle;
    private double subscriptionPrice;
    private int subscribtionImage, subscriptionColor;

    public Subscription(int id, String subscriptionName, String subscriptionCycle, double subscriptionPrice, int subscribtionImage, int subscriptionColor) {
        this.id = id;
        this.subscriptionName = subscriptionName;
        this.subscriptionCycle = subscriptionCycle;
        this.subscriptionPrice = subscriptionPrice;
        this.subscribtionImage = subscribtionImage;
        this.subscriptionColor = subscriptionColor;
    }


    public int getId() {
        return id;
    }


    public String getSubscriptionName() {
        return subscriptionName;
    }

    public String getSubscriptionCycle() {
        return subscriptionCycle;
    }

    public double getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public int getSubscribtionImage() {
        return subscribtionImage;
    }
    public int getSubscriptionColor() {
        return subscriptionColor;
    }

}
