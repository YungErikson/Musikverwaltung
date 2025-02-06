package musikverwaltung;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class Main {
	
	/**
     * Der Einstiegspunkt des Programms.
     * Erstellt die Standard-Songs und startet das Hauptmenü.
     *
     * @param args Die Befehlszeilenargumente (werden in diesem Fall nicht verwendet).
     */	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	// Erstelle eine Liste von Standard-Songs
            List<Song> defaultSongs = createDefaultSongs();
            
            // Starte das Hauptmenü mit den Standard-Songs
            new Hauptmenue(defaultSongs);
        });
    }
	
	/**
     * Erstellt eine Liste von Standard-Songs.
     *
     * @return Eine Liste von Standard-Songs.
     */	
	private static List<Song> createDefaultSongs() {
        List<Song> defaultSongs = new ArrayList<>();

        // Füge Standard-Songs zur Liste hinzu
        defaultSongs.add(new Song("Hot Pepper", "Aylex", "Hip Hop"));
        defaultSongs.add(new Song("Ways", "Aylex", "Hip Hop"));
        defaultSongs.add(new Song("Keep your Head up", "Chillheimer", "Hip Hop"));
        defaultSongs.add(new Song("Mellow", "Patrick Schlebes", "Hip Hop"));
        defaultSongs.add(new Song("Chill Lofi", "Patrick Schlebes", "Hip Hop"));
        defaultSongs.add(new Song("Straight Ahead", "Abi Linden", "Rock"));
        defaultSongs.add(new Song("Quentin", "Niko", "Rock"));
        defaultSongs.add(new Song("Call It A Day", "Lorenz Schultze", "Rock"));
        defaultSongs.add(new Song("Positive Impact", "Dag Reinbott", "Pop"));
        defaultSongs.add(new Song("Corporation", "Aylex", "Pop"));
        defaultSongs.add(new Song("My Sunshine", "SmarTune Music", "Pop"));

        return defaultSongs;
    }

}
