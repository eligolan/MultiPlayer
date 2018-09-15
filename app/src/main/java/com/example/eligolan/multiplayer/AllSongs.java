package com.example.eligolan.multiplayer;

import java.util.ArrayList;

public class AllSongs {
    private ArrayList<String> rockSong;
    private ArrayList<String> popSong;
    private ArrayList<String> mainStreamSong;
    private ArrayList<String> nighteenSong;
    private ArrayList<String> eighteenSong;

    public AllSongs()
    {
        rockSong = new ArrayList<String>();
        loadRockSongs();
        popSong = new ArrayList<String>();
        loadPopSongs();
        mainStreamSong = new ArrayList<String>();
        loadMainStreamSongs();
        nighteenSong = new ArrayList<String>();
        loadNignteenSongs();
        eighteenSong = new ArrayList<String>();
        loadEighteenSongs();
    }



    private void loadRockSongs() {
        rockSong.add("http://bff.vr2.net/jazz/Dave%20Brubeck%20Quartet%20featuring%20Paul%20Desmond%20-%20Buried%20Treasures%20()/07%20%20-%20Take%20Five.mp3");
        rockSong.add("http://bff.vr2.net/Don't%20Box%20Me%20In.mp3");
    }

    private void loadPopSongs() {
        rockSong.add("http://bff.vr2.net/jazz/Dave%20Brubeck%20Quartet%20featuring%20Paul%20Desmond%20-%20Buried%20Treasures%20()/07%20%20-%20Take%20Five.mp3");
        rockSong.add("http://bff.vr2.net/Don't%20Box%20Me%20In.mp3");
    }

    private void loadMainStreamSongs() {
        rockSong.add("http://bff.vr2.net/jazz/Dave%20Brubeck%20Quartet%20featuring%20Paul%20Desmond%20-%20Buried%20Treasures%20()/07%20%20-%20Take%20Five.mp3");
        rockSong.add("http://bff.vr2.net/Don't%20Box%20Me%20In.mp3");
    }

    private void loadNignteenSongs() {
        rockSong.add("http://bff.vr2.net/jazz/Dave%20Brubeck%20Quartet%20featuring%20Paul%20Desmond%20-%20Buried%20Treasures%20()/07%20%20-%20Take%20Five.mp3");
        rockSong.add("http://bff.vr2.net/Don't%20Box%20Me%20In.mp3");
    }

    private void loadEighteenSongs() {
        rockSong.add("http://bff.vr2.net/jazz/Dave%20Brubeck%20Quartet%20featuring%20Paul%20Desmond%20-%20Buried%20Treasures%20()/07%20%20-%20Take%20Five.mp3");
        rockSong.add("http://bff.vr2.net/Don't%20Box%20Me%20In.mp3");
    }

    public ArrayList<String> getSongs(String name) {
        if(name.equals("Rock"))
            return rockSong;
        if(name.equals("Pop"))
            return popSong;
        if(name.equals("MainStream"))
            return mainStreamSong;
        if(name.equals("90's"))
            return nighteenSong;
        if(name.equals("80's"))
            return eighteenSong;
        return mainStreamSong;

    }

    public int getNextIndexSong(ArrayList<String> songs, String currenUrl)
    {
        int index = getIndex(songs,currenUrl);
        if(index == songs.size()-1)
            return 0;
        else
            return index+1;
    }


    public int getIndex(ArrayList<String> songs, String url)
    {
        for(int i=0; i< songs.size(); i++)
            if(url.equals(songs.get(i)))
                return  i;

        return 0;
    }
}
