package musikverwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse repr�sentiert das Untermen� zum Entfernen von Songs.
 * Benutzer k�nnen ausw�hlen, welche Songs sie entfernen m�chten,
 * die nach Best�tigung aus der gesamten Songliste und betroffenen Playlists entfernt werden.
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
     * @param previousFrame Das vorherige JFrame-Objekt, um zum Hauptmen� zur�ckzukehren.
     * @param songs Eine Liste von Song-Objekten, die alle verf�gbaren Songs im System darstellen.
     * @param artistPlaylists Eine Map, die K�nstler-Playlists speichert.
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

        JLabel selectLabel = new JLabel("W�hlen Sie Songs zum Entfernen aus:");

        JButton songEntfernenButton = new JButton("Songs entfernen");
        JButton zurueckButton = new JButton("Zur�ck");

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
            // Erhalte den Index des ausgew�hlten Songs in der songList
            int selectedIndex = songList.getSelectedIndex();
            
            // �berpr�fe, ob ein Song ausgew�hlt wurde
            if (selectedIndex >= 0) {
                // Hole den ausgew�hlten Song anhand des Index aus dem listModel
                Song selectedSong = listModel.getElementAt(selectedIndex);
                
                // Entferne den ausgew�hlten Song aus dem listModel und der songs-Liste
                listModel.removeElement(selectedSong);
                songs.remove(selectedSong);

                // Hole den K�nstler des ausgew�hlten Songs
                String artist = selectedSong.getArtist();
                
                // �berpr�fe, ob der K�nstler in artistPlaylists vorhanden ist
                if (artistPlaylists.containsKey(artist)) {
                    // Entferne den ausgew�hlten Song aus der Playlist des K�nstlers
                    artistPlaylists.get(artist).remove(selectedSong);
                }

                // Hole das Genre des ausgew�hlten Songs
                String genre = selectedSong.getGenre();
                
                // �berpr�fe, ob das Genre in genrePlaylists vorhanden ist
                if (genrePlaylists.containsKey(genre)) {
                    // Entferne den ausgew�hlten Song aus der Playlist des Genres
                    genrePlaylists.get(genre).remove(selectedSong);
                }
                
                // Durchlaufe alle benutzerdefinierten Playlists, um den ausgew�hlten Song zu entfernen
                for (List<Song> userPlaylist : userPlaylists.values()) {
                    if (userPlaylist.contains(selectedSong)) {
                        userPlaylist.remove(selectedSong);
                    }
                }
                
            } else {
                // Zeige eine Fehlermeldung an, wenn kein Song ausgew�hlt wurde
                JOptionPane.showMessageDialog(null, "Bitte w�hlen Sie einen Song zum Entfernen aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        zurueckButton.addActionListener(e -> {
            // Schlie�e das aktuelle Fenster
            dispose();
            
            // Setze das vorherige Fenster auf sichtbar, um dorthin zur�ckzukehren
            previousFrame.setVisible(true);
        });

        setVisible(true);
        previousFrame.setVisible(false);
    }
}



