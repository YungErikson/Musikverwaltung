package musikverwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Eine Klasse, die ein Untermen� zum Erstellen von Playlists erstellt.
 */
public class PlaylistErstellenUntermenue extends JFrame {
    private List<Song> songs;
    private Map<String, List<Song>> artistPlaylists;
    private Map<String, List<Song>> genrePlaylists;
    private Map<String, List<Song>> userPlaylists;

    /**
     * Konstruktor zur Initialisierung des Untermen�s zum Erstellen von Playlists.
     *
     * @param previousFrame Das vorherige JFrame, von dem aus dieses Untermen� aufgerufen wurde.
     * @param songs Eine Liste von Songs, die f�r die Playlist-Auswahl zur Verf�gung stehen.
     * @param artistPlaylists Eine Map von K�nstler-Playlists.
     * @param genrePlaylists Eine Map von Genre-Playlists.
     * @param userPlaylists Eine Map von benutzerdefinierten Playlists.
     */
    public PlaylistErstellenUntermenue(JFrame previousFrame, List<Song> songs,
            Map<String, List<Song>> artistPlaylists, Map<String, List<Song>> genrePlaylists,
            Map<String, List<Song>> userPlaylists) {
        this.songs = songs;
        this.artistPlaylists = artistPlaylists;
        this.genrePlaylists = genrePlaylists;
        this.userPlaylists = userPlaylists;

        setTitle("Playlist erstellen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        List<String> songTitles = new ArrayList<>();
        for (Song song : songs) {
            songTitles.add(song.getTitle());
        }
        Collections.sort(songTitles);

        JList<String> songList = new JList<>(songTitles.toArray(new String[0]));
        songList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane songScrollPane = new JScrollPane(songList);

        JTextField playlistNameField = new JTextField();
        playlistNameField.setColumns(20);

        JButton playlistErstellenButton = new JButton("Playlist erstellen");
        JButton zurueckButton = new JButton("Zur�ck");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(songScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(playlistNameField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(playlistErstellenButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(zurueckButton, gbc);

        playlistErstellenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Hole den eingegebenen Namen der Playlist aus dem playlistNameField
                String playlistName = playlistNameField.getText();
                
                // �berpr�fe, ob der Playlist-Name nicht leer ist
                if (!playlistName.isEmpty()) {
                	// Erstelle eine Liste, um ausgew�hlte Songs zu speichern
                    List<Song> selectedSongs = new ArrayList<>();
                    
                    // Iteriere �ber die ausgew�hlten Titel in der songList
                    for (String selectedTitle : songList.getSelectedValuesList()) {
                    	// Suche nach dem ausgew�hlten Titel in der Liste der verf�gbaren Songs
                        for (Song song : songs) {
                        	// Wenn der Titel �bereinstimmt, f�ge den Song zur Liste der ausgew�hlten Songs hinzu und beende die Schleife
                            if (song.getTitle().equals(selectedTitle)) {
                                selectedSongs.add(song);
                                break;
                            }
                        }
                    }

                    // �berpr�fe, ob mindestens ein Song ausgew�hlt wurde
                    if (!selectedSongs.isEmpty()) {
                    	// Erstelle eine Kopie der ausgew�hlten Songs f�r die Playlist
                        List<Song> playlistSongs = new ArrayList<>(selectedSongs);
                        
                        // �berpr�fe, ob eine Playlist mit dem angegebenen Namen noch nicht existiert
                        if (!artistPlaylists.containsKey(playlistName) && !genrePlaylists.containsKey(playlistName)
                                && !userPlaylists.containsKey(playlistName)) {
                        	// F�ge die Playlist zur Map der benutzerdefinierten Playlists hinzu.                       	
                            userPlaylists.put(playlistName, playlistSongs);
                            // Zeige eine Erfolgsmeldung an
                            JOptionPane.showMessageDialog(null, "Die benutzerdefinierte Playlist '" + playlistName + "' wurde erstellt und hinzugef�gt.");
                        } else {
                        	// Zeige eine Fehlermeldung an, wenn eine Playlist mit dem Namen bereits existiert
                            JOptionPane.showMessageDialog(null, "Eine Playlist mit dem Namen '" + playlistName + "' existiert bereits.");
                        }
                    } else {
                    	// Zeige eine Fehlermeldung an, wenn kein Song ausgew�hlt wurde
                        JOptionPane.showMessageDialog(null, "Bitte w�hlen Sie mindestens einen Song aus.");
                    }
                } else {
                	// Zeige eine Fehlermeldung an, wenn kein Playlist-Name eingegeben wurde
                    JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Playlist-Namen ein.");
                }
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

