package com.heroku.devcenter;

/**
 * Created with IntelliJ IDEA.
 * User: Artur
 * Date: 9/20/13
 * Time: 12:14 AM
 * To change this template use File | Settings | File Templates.
 */
public enum User {
    USER_1(1),USER_2(2),USER_3(3);

    User(int data) {
        switch (data){
            case 1:
                this.id=1;
                this.size=32;
                this.amountPeople=5;
            break;
            case 2:
                this.id=data;
                this.size=40;
                this.amountPeople=5;
                break;
            case 3:
                this.id=data;
                this.size=51;
                this.amountPeople=5;
                break;
        }
    }


    public int getId() {
        return id;
    }

    int id;
    int size;
    int amountPeople;

}
