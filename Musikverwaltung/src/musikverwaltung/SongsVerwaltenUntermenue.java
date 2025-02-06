package musikverwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse repräsentiert das Untermenü zur Verwaltung von Songs.
 * Benutzer können hier auf die Untermenüs "Alle Songs", "Songs Entfernen" und "Songs hinzufügen" zugreifen.
 */
class SongsVerwaltenUntermenue extends JFrame {
	
    private List<Song> songs;
    private DefaultListModel<Song> listModel;
    private Map<String, List<Song>> artistPlaylists;
    private Map<String, List<Song>> genrePlaylists;
    private Map<String, List<Song>> userPlaylists;

    /**
     * Konstruktor zur Initialisierung des SongsVerwaltenUntermenue-Objekts.
     *
     * @param previousFrame Das vorherige JFrame-Objekt, um zum vorherigen Menü zurückzukehren.
     * @param songs Eine Liste von Song-Objekten, die alle verfügbaren Songs im System darstellen.
     * @param listModel Das DefaultListModel, das die Anzeige der Songs in der GUI verwaltet.
     * @param artistPlaylists Eine Map von Künstlern zu ihren Playlists.
     * @param genrePlaylists Eine Map von Genres zu ihren Playlists.
     * @param userPlaylists Eine Map von Benutzer-Playlists.
     */
    public SongsVerwaltenUntermenue(JFrame previousFrame, List<Song> songs, DefaultListModel<Song> listModel,
            Map<String, List<Song>> artistPlaylists, Map<String, List<Song>> genrePlaylists, Map<String, List<Song>> userPlaylists) {
    	
        setTitle("Songs verwalten");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        this.songs = songs;
        this.listModel = listModel;
        this.artistPlaylists = artistPlaylists;
        this.genrePlaylists = genrePlaylists;
        this.userPlaylists = userPlaylists;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton alleSongsButton = new JButton("Alle Songs");
        JButton songsEntfernenButton = new JButton("Songs entfernen");
        JButton songsHinzufuegenButton = new JButton("Songs hinzufügen");
        JButton zurueckButton = new JButton("Zurück");

        Dimension buttonSize = new Dimension(150, 40);
        alleSongsButton.setPreferredSize(buttonSize);
        songsEntfernenButton.setPreferredSize(buttonSize);
        songsHinzufuegenButton.setPreferredSize(buttonSize);
        zurueckButton.setPreferredSize(buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(alleSongsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(songsEntfernenButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(songsHinzufuegenButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(zurueckButton, gbc);

        alleSongsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AlleSongsUntermenue(SongsVerwaltenUntermenue.this, songs);
            }
        });

        songsEntfernenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SongEntfernenUntermenue(SongsVerwaltenUntermenue.this, songs, artistPlaylists, genrePlaylists, userPlaylists);
            }
        });

        songsHinzufuegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SongHinzufuegenUntermenue(SongsVerwaltenUntermenue.this, songs, listModel, artistPlaylists, genrePlaylists);
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

