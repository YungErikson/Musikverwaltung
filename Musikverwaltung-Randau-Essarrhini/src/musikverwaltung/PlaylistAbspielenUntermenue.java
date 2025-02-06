package musikverwaltung;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collections;

/**
 * Eine Klasse, die ein Untermenü zum Abspielen von Playlists erstellt.
 */
public class PlaylistAbspielenUntermenue extends JFrame {
    private List<Song> songs;
    private Map<String, List<Song>> artistPlaylists;
    private Map<String, List<Song>> genrePlaylists;
    private Map<String, List<Song>> userPlaylists;
    
    /**
     * Konstruktor zur Initialisierung des Untermenüs zum Abspielen von Playlists.
     *
     * @param previousFrame Das vorherige JFrame, von dem aus dieses Untermenü aufgerufen wurde.
     * @param songs Eine Liste von Songs, die verwendet werden können.
     * @param artistPlaylists Eine Map von Künstler-Playlists.
     * @param genrePlaylists Eine Map von Genre-Playlists.
     * @param userPlaylists Eine Map von benutzerdefinierten Playlists.
     */
    public PlaylistAbspielenUntermenue(JFrame previousFrame, List<Song> songs,
                                        Map<String, List<Song>> artistPlaylists, Map<String, List<Song>> genrePlaylists,
                                        Map<String, List<Song>> userPlaylists) {
        this.songs = songs;
        this.artistPlaylists = artistPlaylists;
        this.genrePlaylists = genrePlaylists;
        this.userPlaylists = userPlaylists;

        setTitle("Playlists abspielen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Sortiere die Namen der Playlists alphabetisch
        List<String> userPlaylistsSorted = new ArrayList<>(userPlaylists.keySet());
        List<String> artistPlaylistsSorted = new ArrayList<>(artistPlaylists.keySet());
        List<String> genrePlaylistsSorted = new ArrayList<>(genrePlaylists.keySet());

        Collections.sort(userPlaylistsSorted);
        Collections.sort(artistPlaylistsSorted);
        Collections.sort(genrePlaylistsSorted);

        // Kombiniere die sortierten Playlists zu einer Gesamtliste
        List<String> allPlaylists = new ArrayList<>();
        allPlaylists.addAll(userPlaylistsSorted);
        allPlaylists.addAll(artistPlaylistsSorted);
        allPlaylists.addAll(genrePlaylistsSorted);

        JComboBox<String> playlistComboBox = new JComboBox<>(allPlaylists.toArray(new String[0]));
        JButton abspielenButton = new JButton("Abspielen");
        JButton playlistAnsehenButton = new JButton("Playlist ansehen");
        JButton playlistLoeschenButton = new JButton("Playlist löschen");
        JButton zurueckButton = new JButton("Zurück");

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(abspielenButton);
        buttonPanel.add(playlistAnsehenButton);
        buttonPanel.add(playlistLoeschenButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(playlistComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(buttonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(zurueckButton, gbc);

        abspielenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hole den ausgewählten Playlist-Namen aus der playlistComboBox
                String selectedPlaylist = (String) playlistComboBox.getSelectedItem();
                
                // Überprüfe, ob eine Playlist ausgewählt wurde
                if (selectedPlaylist != null) {
                    // Überprüfe, ob die ausgewählte Playlist eine Künstler-Playlist ist
                    if (artistPlaylists.containsKey(selectedPlaylist)) {
                        // Zeige eine Meldung an, dass die Künstler-Playlist abgespielt wird
                        JOptionPane.showMessageDialog(null, "Die Künstler-Playlist '" + selectedPlaylist + "' wird abgespielt.");
                    } 
                    // Überprüfe, ob die ausgewählte Playlist eine Genre-Playlist ist
                    else if (genrePlaylists.containsKey(selectedPlaylist)) {
                        // Zeige eine Meldung an, dass die Genre-Playlist abgespielt wird
                        JOptionPane.showMessageDialog(null, "Die Genre-Playlist '" + selectedPlaylist + "' wird abgespielt.");
                    } 
                    // Überprüfe, ob die ausgewählte Playlist eine benutzerdefinierte Playlist ist
                    else if (userPlaylists.containsKey(selectedPlaylist)) {
                        // Zeige eine Meldung an, dass die benutzerdefinierte Playlist abgespielt wird
                        JOptionPane.showMessageDialog(null, "Die benutzerdefinierte Playlist '" + selectedPlaylist + "' wird abgespielt.");
                    }
                }
            }
        });
        
        playlistAnsehenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hole den ausgewählten Playlist-Namen aus der playlistComboBox
                String selectedPlaylist = (String) playlistComboBox.getSelectedItem();
                
                // Überprüfe, ob eine Playlist ausgewählt wurde
                if (selectedPlaylist != null) {
                    List<Song> playlistSongs = new ArrayList<>();
                    // Überprüfe, ob die ausgewählte Playlist eine Künstler-Playlist ist
                    if (artistPlaylists.containsKey(selectedPlaylist)) {
                        playlistSongs = artistPlaylists.get(selectedPlaylist);
                    } 
                    // Überprüfe, ob die ausgewählte Playlist eine Genre-Playlist ist
                    else if (genrePlaylists.containsKey(selectedPlaylist)) {
                        playlistSongs = genrePlaylists.get(selectedPlaylist);
                    } 
                    // Überprüfe, ob die ausgewählte Playlist eine benutzerdefinierte Playlist ist
                    else if (userPlaylists.containsKey(selectedPlaylist)) {
                        playlistSongs = userPlaylists.get(selectedPlaylist);
                    }

                    // Erstelle den Inhalt der Meldung mit den Songs in der Playlist
                    StringBuilder playlistContent = new StringBuilder("Songs in der Playlist '" + selectedPlaylist + "':\n");
                    for (Song song : playlistSongs) {
                        playlistContent.append(song.getTitle()).append(" - ").append(song.getArtist()).append(" - ").append(song.getGenre()).append("\n");
                    }

                    // Zeige eine Meldung mit dem Inhalt der Playlist an
                    JOptionPane.showMessageDialog(null, playlistContent.toString(), "Playlist ansehen", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        playlistLoeschenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hole den ausgewählten Playlist-Namen aus der playlistComboBox
                String selectedPlaylist = (String) playlistComboBox.getSelectedItem();
                
                // Überprüfe, ob eine Playlist ausgewählt wurde
                if (selectedPlaylist != null) {
                    // Überprüfe, ob die ausgewählte Playlist eine benutzerdefinierte Playlist ist
                    if (userPlaylists.containsKey(selectedPlaylist)) {
                        // Entferne die benutzerdefinierte Playlist aus der userPlaylists-Map
                        userPlaylists.remove(selectedPlaylist);
                        // Zeige eine Bestätigungsmeldung für das Löschen der Playlist an
                        JOptionPane.showMessageDialog(null, "Die benutzerdefinierte Playlist '" + selectedPlaylist + "' wurde gelöscht.");
                        // Entferne den ausgewählten Playlist-Namen aus der playlistComboBox
                        playlistComboBox.removeItem(selectedPlaylist);
                    } else {
                        // Zeige eine Fehlermeldung an, wenn versucht wird, eine nicht-benutzerdefinierte Playlist zu löschen
                        JOptionPane.showMessageDialog(null, "Nur benutzerdefinierte Playlists können gelöscht werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                }
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


