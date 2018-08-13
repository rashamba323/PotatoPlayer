package com.potato.data;

import java.io.Serializable;

public class OnlineListItem implements Serializable {
    public String title;

    public OnlineListItem(){
    }

    public OnlineListItem(String string){
        this.title = string;
    }

    public String getTitle() {
        return title;
    }
}
