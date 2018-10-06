package com.example.eligolan.multiplayer;

public class Song {
    private String url;
    private int photo;
    private String name;
    private  int i;

    public Song(String url, int photo,String name, int i)
    {
        this.photo = photo;
        this.url = url;
        this.name = name;
        this.i = i;
    }

    int getPhoto()
    {
        return photo;
    }

    String getUrl()
    {
        return url;
    }

    String getName()
    {
        return name;
    }
    int getI()
    {
        return i;
    }


}
