package musikverwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse repräsentiert das Untermenü zum Entfernen von Songs.
 * Benutzer können auswählen, welche Songs sie entfernen möchten,
 * die nach Bestätigung aus der gesamten Songliste und betroffenen Playlists entfernt werden.
 */
public class SongEntfernenUntermenue extends JFrame {
    private List<Song> songs;
    private DefaultListModel<Song> listModel;
    private JList<Song> songList;
    private Map<String, List<Song>> artistPlaylists;
    private Map<String, List<Song>> genrePlaylists;
    private Map<String, List<Song>> userPlaylists;

    /**
     * Konstruktor zur Initialisierung des SongEntfernenUntermenue-Objekts.
     *
     * @param previousFrame Das vorherige JFrame-Objekt, um zum Hauptmenü zurückzukehren.
     * @param songs Eine Liste von Song-Objekten, die alle verfügbaren Songs im System darstellen.
     * @param artistPlaylists Eine Map, die Künstler-Playlists speichert.
     * @param genrePlaylists Eine Map, die Genre-Playlists speichert.
     * @param userPlaylists Eine Map, die benutzerdefinierte Playlists speichert.
     */
    public SongEntfernenUntermenue(JFrame previousFrame, List<Song> songs,
                                   Map<String, List<Song>> artistPlaylists, Map<String, List<Song>> genrePlaylists, Map<String, List<Song>> userPlaylists) {
        setTitle("Songs entfernen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel selectLabel = new JLabel("Wählen Sie Songs zum Entfernen aus:");

        JButton songEntfernenButton = new JButton("Songs entfernen");
        JButton zurueckButton = new JButton("Zurück");

        listModel = new DefaultListModel<>();
        this.songs = songs;
        this.artistPlaylists = artistPlaylists;
        this.genrePlaylists = genrePlaylists;
        
        songs.sort(Comparator.comparing(Song::getTitle));
        
        for (Song song : songs) {
            listModel.addElement(song);
        }

        songList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(songList);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(selectLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(songEntfernenButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(zurueckButton, gbc);

        songEntfernenButton.addActionListener(e -> {
            // Erhalte den Index des ausgewählten Songs in der songList
            int selectedIndex = songList.getSelectedIndex();
            
            // Überprüfe, ob ein Song ausgewählt wurde
            if (selectedIndex >= 0) {
                // Hole den ausgewählten Song anhand des Index aus dem listModel
                Song selectedSong = listModel.getElementAt(selectedIndex);
                
                // Entferne den ausgewählten Song aus dem listModel und der songs-Liste
                listModel.removeElement(selectedSong);
                songs.remove(selectedSong);

                // Hole den Künstler des ausgewählten Songs
                String artist = selectedSong.getArtist();
                
                // Überprüfe, ob der Künstler in artistPlaylists vorhanden ist
                if (artistPlaylists.containsKey(artist)) {
                    // Entferne den ausgewählten Song aus der Playlist des Künstlers
                    artistPlaylists.get(artist).remove(selectedSong);
                }

                // Hole das Genre des ausgewählten Songs
                String genre = selectedSong.getGenre();
                
                // Überprüfe, ob das Genre in genrePlaylists vorhanden ist
                if (genrePlaylists.containsKey(genre)) {
                    // Entferne den ausgewählten Song aus der Playlist des Genres
                    genrePlaylists.get(genre).remove(selectedSong);
                }
                
                // Durchlaufe alle benutzerdefinierten Playlists, um den ausgewählten Song zu entfernen
                for (List<Song> userPlaylist : userPlaylists.values()) {
                    if (userPlaylist.contains(selectedSong)) {
                        userPlaylist.remove(selectedSong);
                    }
                }
                
            } else {
                // Zeige eine Fehlermeldung an, wenn kein Song ausgewählt wurde
                JOptionPane.showMessageDialog(null, "Bitte wählen Sie einen Song zum Entfernen aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
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



