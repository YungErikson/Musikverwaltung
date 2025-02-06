package musikverwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse repräsentiert das Untermenü zum Abspielen von Songs und Navigieren zu verschiedenen Abspieloptionen.
 * Benutzer können hierdurch auf die Untermenüs "Songs nach Titel", 
 * "Songs nach Künstler", "Songs nach Genre" sowie auf "Playlists" zugreifen.
 */
public class SongsAbspielenUntermenue extends JFrame {
	private List<Song> songs;
    private Map<String, List<Song>> artistPlaylists;
    private Map<String, List<Song>> genrePlaylists;
    private Map<String, List<Song>> userPlaylists;
	
    /**
     * Konstruktor zur Initialisierung des SongsAbspielenUntermenue-Objekts.
     *
     * @param previousFrame Das vorherige JFrame-Objekt, um zum Hauptmenü zurückzukehren.
     * @param songs Eine Liste von Song-Objekten, die alle verfügbaren Songs im System darstellen.
     * @param artistPlaylists Eine Map, die Künstler-Playlists speichert.
     * @param genrePlaylists Eine Map, die Genre-Playlists speichert.
     * @param userPlaylists Eine Map, die benutzerdefinierte Playlists speichert.
     */
    public SongsAbspielenUntermenue(JFrame previousFrame, List<Song> songs,
            Map<String, List<Song>> artistPlaylists, Map<String, List<Song>> genrePlaylists,
            Map<String, List<Song>> userPlaylists) {
    	
    	this.songs = songs;
        this.artistPlaylists = artistPlaylists;
        this.genrePlaylists = genrePlaylists;
        this.userPlaylists = userPlaylists;
    	
        setTitle("Songs abspielen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton songsNachTitelButton = new JButton("Songs nach Titel");
        JButton songsNachKuenstlerButton = new JButton("Songs nach Künstler");
        JButton songsNachGenreButton = new JButton("Songs nach Genre");
        JButton playlistsButton = new JButton("Playlists");
        JButton zurueckButton = new JButton("Zurück");

        Dimension buttonSize = new Dimension(175, 40);
        songsNachTitelButton.setPreferredSize(buttonSize);
        songsNachKuenstlerButton.setPreferredSize(buttonSize);
        songsNachGenreButton.setPreferredSize(buttonSize);
        playlistsButton.setPreferredSize(buttonSize);
        zurueckButton.setPreferredSize(buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(songsNachTitelButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(songsNachKuenstlerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(songsNachGenreButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(playlistsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(zurueckButton, gbc);

        songsNachTitelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SongsNachTitelUntermenue(SongsAbspielenUntermenue.this, songs);
            }
        });

        songsNachKuenstlerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SongsNachKuenstlerUntermenue(SongsAbspielenUntermenue.this, songs);
            }
        });

        songsNachGenreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SongsNachGenreUntermenue(SongsAbspielenUntermenue.this, songs);
            }
        });

        playlistsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PlaylistsUntermenue(SongsAbspielenUntermenue.this, songs, artistPlaylists, genrePlaylists, userPlaylists);
            }
        });

        zurueckButton.addActionListener(e -> {
            // Schließe das aktuelle Fenster
            dispose();
            
            // Setze das vorherige Fenster auf sichtbar, um dorthin zurückzukehren
            previousFrame.setVisible(true);
        });

        setVisible(true);
        previousFrame.setVisible(false);
    }
}
