package musikverwaltung;

import java.util.Objects;

/**
 * Eine Klasse, die ein Song repr�sentiert.
 */

public class Song {
    private String title; // Der Name des Songs
    private String artist; // Der Interpret des Songs
    private String genre; // Das Genre des Songs
    
    /** 
     * Konstruktor zur Initialisierung eines Song-Objekts mit Titel, K�nstler und Genre.
     * 
     * @param title Name des Songs.
     * @param artist Interpret des Songs.
     * @param genre Genre des Songs.
     */
    public Song(String title, String artist, String genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    /**
     * Gibt den Titel des Songs zur�ck.
     * 
     * @return Der Titel des Songs.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gibt den Interpret des Songs zur�ck.
     * 
     * @return Der Interpret des Songs.
     */
    public String getArtist() {
        return artist;
    }
    
    /**
     * Gibt das Genre des Songs zur�ck.
     * 
     * @return Das Genre des Songs.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gibt eine Zeichenkette zur�ck, die den Song repr�sentiert.
     * 
     * @return Eine Zeichenkette mit dem Titel, dem K�nstler und dem Genre des Songs.
     */
    @Override
    public String toString() {
        return title + " - " + artist + " (" + genre + ")";
    }
    
    /**
     * �berpr�ft, ob ein anderes Objekt gleich diesem Song-Objekt ist.
     * Zwei Song-Objekte werden als gleich betrachtet, wenn sie denselben Titel, denselben K�nstler und dasselbe Genre haben.
     *
     * @param o Das Objekt, mit dem dieses Song-Objekt verglichen werden soll.
     * @return true, wenn das �bergebene Objekt gleich diesem Song-Objekt ist, andernfalls false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) &&
               Objects.equals(artist, song.artist) &&
               Objects.equals(genre, song.genre);
    }

    /**
     * Berechnet den Hashcode f�r das Song-Objekt.
     * 
     * @return Der Hashcode des Song-Objekts.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, artist, genre);
    }

}
