package javacode;
import java.util.*;

public class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {
        Set<String> set = new HashSet<>();
        set.add(this.name);
        Song t = this.nextSong;
        while(t != null) {
            if (set.contains(t.name)) return true;
            else {
                set.add(t.name);
                t = t.nextSong;
            }
        }
        
        return false;
        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}