package musikverwaltung;

import java.util.Objects;

/**
 * Eine Klasse, die ein Song repräsentiert.
 */

public class Song {
    private String title; // Der Name des Songs
    private String artist; // Der Interpret des Songs
    private String genre; // Das Genre des Songs
    
    /** 
     * Konstruktor zur Initialisierung eines Song-Objekts mit Titel, Künstler und Genre.
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
     * Gibt den Titel des Songs zurück.
     * 
     * @return Der Titel des Songs.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gibt den Interpret des Songs zurück.
     * 
     * @return Der Interpret des Songs.
     */
    public String getArtist() {
        return artist;
    }
    
    /**
     * Gibt das Genre des Songs zurück.
     * 
     * @return Das Genre des Songs.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gibt eine Zeichenkette zurück, die den Song repräsentiert.
     * 
     * @return Eine Zeichenkette mit dem Titel, dem Künstler und dem Genre des Songs.
     */
    @Override
    public String toString() {
        return title + " - " + artist + " (" + genre + ")";
    }
    
    /**
     * Überprüft, ob ein anderes Objekt gleich diesem Song-Objekt ist.
     * Zwei Song-Objekte werden als gleich betrachtet, wenn sie denselben Titel, denselben Künstler und dasselbe Genre haben.
     *
     * @param o Das Objekt, mit dem dieses Song-Objekt verglichen werden soll.
     * @return true, wenn das übergebene Objekt gleich diesem Song-Objekt ist, andernfalls false.
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
     * Berechnet den Hashcode für das Song-Objekt.
     * 
     * @return Der Hashcode des Song-Objekts.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, artist, genre);
    }

}
