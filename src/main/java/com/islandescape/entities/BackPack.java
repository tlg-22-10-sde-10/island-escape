package com.islandescape.entities;

import java.util.ArrayList;
import java.util.List;

public class BackPack {

    private List<Item> items;


    public BackPack(){
        items = new ArrayList<>();
    }


    public boolean addToBackPack(Item item){
        return this.items.add(item);

    }

    public boolean removeFromBackPack(Item item){
        return this.items.remove(item);
    }

    public boolean usedItem(Item item){
        System.out.println(String.format("Used {}", item));
        return removeFromBackPack(item);
    }

    public List<Item> getItems(){
        return this.items;
    }

    public void printBackPack(){

        for(Item i : getItems()){
            System.out.println(i);
        }
    }


}
