package musikverwaltung;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * Diese Klasse repr�sentiert das Untermen� zum Anzeigen und Abspielen von Songs nach K�nstler sortiert.
 * Benutzer k�nnen die Songs nach K�nstler anzeigen und einzelne Songs abspielen.
 */
public class SongsNachKuenstlerUntermenue extends JFrame {
    private List<Song> songs;

    /**
     * Konstruktor zur Initialisierung des SongsNachKuenstlerUntermenue-Objekts.
     *
     * @param previousFrame Das vorherige JFrame-Objekt, um zum vorherigen Men� zur�ckzukehren.
     * @param songs Eine Liste von Song-Objekten, die alle verf�gbaren Songs im System darstellen.
     */
    public SongsNachKuenstlerUntermenue(JFrame previousFrame, List<Song> songs) {
        this.songs = songs;

        setTitle("Songs nach Titel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        DefaultListModel<Song> listModel = new DefaultListModel<>();
        JList<Song> songList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(songList);

        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator
                .comparing(Song::getArtist)
                .thenComparing(Song::getTitle));

        for (Song song : sortedSongs) {
            listModel.addElement(song);
        }
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        JButton abspielenButton = new JButton("Abspielen");
        JButton zurueckButton = new JButton("Zur�ck");

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(abspielenButton, gbc);

        gbc.gridx = 1;
        add(zurueckButton, gbc);

        abspielenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hole den ausgew�hlten Song aus der JList songList
                Song selectedSong = songList.getSelectedValue();
                
                // �berpr�fe, ob ein Song ausgew�hlt wurde
                if (selectedSong != null) {
                    // Zeige eine Meldung an, dass der ausgew�hlte Song abgespielt wird, und zeige die Details des Songs an
                    JOptionPane.showMessageDialog(null, "Song wird abgespielt:\n" + selectedSong.toString());
                } else {
                    // Zeige eine Fehlermeldung an, wenn kein Song ausgew�hlt wurde
                    JOptionPane.showMessageDialog(null, "Bitte w�hlen Sie einen Song aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
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
