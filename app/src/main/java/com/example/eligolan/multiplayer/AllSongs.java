package com.example.eligolan.multiplayer;

import java.util.ArrayList;

public class AllSongs {
    private ArrayList<String> rockSong;
    private ArrayList<String> popSong;
    private ArrayList<String> mainStreamSong;
    private ArrayList<String> nighteenSong;
    private ArrayList<String> eighteenSong;
    private ArrayList<String> mizrahitSong;

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
        mizrahitSong = new ArrayList<String>();
        loadMizrahitSongs();
    }



    private void loadRockSongs() {
        rockSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Nirvana%20-%20Lithium.mp3?alt=media&token=a2e80b98-5db5-4149-a2e0-4c9197c5d6d8");
        rockSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Nirvana%20-%20Smells%20Like%20Teen%20Spirit.mp3?alt=media&token=88975854-6c8b-4f55-9abc-e74c3d415b11");
        rockSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Nirvana-Come_as_you_are_lyrics.mp3?alt=media&token=7e0fc32a-95cf-4da1-b4ed-dbcb39121595");
    }

    private void loadPopSongs() {
        popSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/05%20-%20Britney%20Spears%20-%20Oops!...%20I%20Did%20It%20Again.mp3?alt=media&token=9b541557-835f-4116-889b-dc8d36ba833d");
        popSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Britney%20Spears%20-%20Crazy.mp3?alt=media&token=2a094915-b19b-401f-b3fc-6bb3b8bda78e");
        popSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Brithney%20Spears%20-%20Baby%20One%20More%20Time.mp3?alt=media&token=4f586817-e1c3-427e-a9cc-443d7e8a7036");
    }

    private void loadMainStreamSongs() {
        mainStreamSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Stephane%20Legar%20-%20Comme%20Ci%20Comme%20%C3%A7a%20(Music%20Video)%20%D7%A1%D7%98%D7%A4%D7%9F%20%D7%9C%D7%92%D7%A8%20-%20%D7%A7%D7%95%D7%9E%D7%A1%D7%99%20%D7%A7%D7%95%D7%9E%D7%A1%D7%94.mp3?alt=media&token=fee264c1-0fea-4cb1-9ee4-48eddf5c3597");
        mainStreamSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/%D7%A1%D7%98%D7%98%D7%99%D7%A7%20%D7%95%D7%91%D7%9F%20%D7%90%D7%9C%20%D7%AA%D7%91%D7%95%D7%A8%D7%99%20-%20%D7%92%D7%95%D7%9E%D7%99%D7%92%D7%9D%20(Prod.%20by%20Jordi).mp3?alt=media&token=01f37a2c-edbf-4e8e-8f95-9b15068843c1");
        mainStreamSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Mr.%20Probz%20-%20Waves%20(Robin%20Schulz%20Remix%20Radio%20Edit).mp3?alt=media&token=6ca91468-6d1e-4afe-bead-ccbd2ec6564d");
    }

    private void loadNignteenSongs() {
        nighteenSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Bon%20Jovi%20-%20Always.mp3?alt=media&token=fe6cc71b-8392-4e39-8a58-a98eec2bc35b");
        nighteenSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Bon%20Jon%20Jovi%20-%20Its%20My%20Life.mp3?alt=media&token=68a320ba-563c-4985-8907-1091fa040be0");
    }

    private void loadEighteenSongs() {
        eighteenSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Queen%20-%20Love%20of%20my%20life.mp3?alt=media&token=8e248a6f-0ad4-424f-97fe-c84c33dc606e");
        eighteenSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/Queen%20-%20We%20Are%20The%20Campions.mp3?alt=media&token=d81b9b76-f37d-4c0c-b052-d4e074985761");
    }

    private void loadMizrahitSongs() {

        mizrahitSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/%D7%9E%D7%90%D7%95%D7%A8%20%D7%90%D7%93%D7%A8%D7%99%20-%20%D7%90%D7%9C%D7%9C%D7%94%20%D7%9E%D7%A2%D7%A7%20%D7%A7%D7%9C%D7%99%D7%A4%20%D7%A8%D7%A9%D7%9E%D7%99%20(Maor%20Edri%20(Prod.%20By%20Stav%20Beger.mp3?alt=media&token=28cd5689-40b4-4a68-9808-51d679841019");
        mizrahitSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/%D7%9C%D7%99%D7%A8%D7%9F%20%D7%93%D7%A0%D7%99%D7%A0%D7%95%20-%20%D7%99%D7%A9%20%D7%99%D7%9E%D7%99%D7%9D.mp3?alt=media&token=9bfe7988-1c18-4d85-b1a8-98d0a7dac4dd");
        mizrahitSong.add("https://firebasestorage.googleapis.com/v0/b/musify-e3833.appspot.com/o/%D7%A2%D7%95%D7%9E%D7%A8%20%D7%90%D7%93%D7%9D%20-%20%D7%97%D7%91%D7%A8%D7%95%D7%AA%20%D7%A9%D7%9C%D7%9A.mp3?alt=media&token=2a6985ca-8805-49d1-acc8-f22702c6fe9b");
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
        return mizrahitSong;

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
