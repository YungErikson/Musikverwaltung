package musikverwaltung;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Diese Klasse repr�sentiert das Hauptmen� der Anwendung,
 * von dem aus Benutzer verschiedene Aktionen ausf�hren k�nnen.
 * Benutzer k�nnen in die Untermen�s "Songs abspielen" und
 * "Songs verwalten" oder das Programm schlie�en.
 */
public class Hauptmenue {
    private List<Song> songs; // Liste aller verf�gbaren Songs
    private DefaultListModel<Song> listModel; // Modell f�r die JList-Anzeige der Songs
    private Map<String, List<Song>> artistPlaylists; // Map der K�nstler-Playlists
    private Map<String, List<Song>> genrePlaylists; // Map der Genre Playlists
    private Map<String, List<Song>> userPlaylists; // Map der benutzerdefinierten Playlists
    
    /**
     * Konstruktor f�r das Hauptmen�.
     * Initialisiert die Songs, die Playlists und die GUI.
     *
     * @param songs Die Liste der verf�gbaren Songs.
     */
    public Hauptmenue(List<Song> songs) {
        this.songs = songs;
        this.listModel = new DefaultListModel<>();
        this.artistPlaylists = new HashMap<>();
        this.genrePlaylists = new HashMap<>();
        this.userPlaylists = new HashMap<>();
        createDefaultPlaylists();
        initializeGUI();
    }

    
    /**
     * Erstellt die Standard-Playlists basierend auf den verf�gbaren Songs.
     */
    private void createDefaultPlaylists() {
        for (Song song : songs) {
            String artist = song.getArtist();
            String genre = song.getGenre();

            // Erstelle die Playlist f�r den K�nstler, wenn sie nicht vorhanden ist
            if (!artistPlaylists.containsKey(artist)) {
                artistPlaylists.put(artist, new ArrayList<>());
            }
            artistPlaylists.get(artist).add(song);

            // Erstelle die Playlist f�r das Genre, wenn sie nicht vorhanden ist
            if (!genrePlaylists.containsKey(genre)) {
                genrePlaylists.put(genre, new ArrayList<>());
            }
            genrePlaylists.get(genre).add(song);
        }
    }

    /**
     * Initialisiert die GUI des Hauptmen�s.
     */    
    private void initializeGUI() {
    	// Erstelle das Hauptfenster
        JFrame frame = new JFrame("Hauptmen�");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new GridBagLayout()); 

        // Erzeuge Constraints f�r das GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Erstelle Buttons f�r verschiedene Aktionen
        JButton songsAbspielenButton = new JButton("Songs abspielen");
        JButton songsVerwaltenButton = new JButton("Songs verwalten");
        JButton programmSchliessenButton = new JButton("Programm schlie�en");
        
        // Setze die Gr��e der Buttons
        Dimension buttonSize = new Dimension(200, 50);
        songsAbspielenButton.setPreferredSize(buttonSize);
        songsVerwaltenButton.setPreferredSize(buttonSize);
        programmSchliessenButton.setPreferredSize(buttonSize);

        
        // F�ge die Buttons zum Hauptfenster hinzu
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(songsAbspielenButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(songsVerwaltenButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(programmSchliessenButton, gbc);

        
        // F�ge Aktionen f�r die Buttons hinzu
        songsAbspielenButton.addActionListener(e -> {
        	// �ffne das Untermen� zum Abspielen von Songs
            new SongsAbspielenUntermenue(frame, songs, artistPlaylists, genrePlaylists, userPlaylists);
        });

        songsVerwaltenButton.addActionListener(e -> {
        	// �ffne das Untermen� zur Verwaltung von Songs und Playlists
            new SongsVerwaltenUntermenue(frame, songs, listModel, artistPlaylists, genrePlaylists, userPlaylists);
        });

        programmSchliessenButton.addActionListener(e -> {
        	// Schlie�e das Programm
            frame.dispose();
        });
        
        // Platziere das Hauptfenster zentriert auf dem Bildschirm
        frame.setLocationRelativeTo(null);
        
        // Zeige das Hauptfenster an
        frame.setVisible(true);
    }
    
}

