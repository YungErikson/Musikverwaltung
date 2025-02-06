package musikverwaltung;

import java.util.ArrayList;
import java.util.List;

/**
 * Eine Klasse, die eine Playlist von Songs repr�sentiert.
 */
public class Playlist {
    private String name; // Der Name der Playlist
    private List<Song> songs; // Eine Liste von Songs in der Playlist

    /**
     * Konstruktor f�r die Playlist-Klasse.
     *
     * @param name Der Name der Playlist.
     */
    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    /**
     * Gibt den Namen der Playlist zur�ck.
     *
     * @return Der Name der Playlist.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gibt die Liste von Songs in der Playlist zur�ck.
     *
     * @return Die Liste von Songs.
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * F�gt einen Song zur Playlist hinzu.
     *
     * @param song Der Song, der zur Playlist hinzugef�gt werden soll.
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * Entfernt einen Song aus der Playlist.
     *
     * @param song Der Song, der aus der Playlist entfernt werden soll.
     */
    public void removeSong(Song song) {
        songs.remove(song);
    }
}
