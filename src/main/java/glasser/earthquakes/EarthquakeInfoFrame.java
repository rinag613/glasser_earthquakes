package glasser.earthquakes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EarthquakeInfoFrame {
    Feature current = new Feature();
    boolean tIsColored = false;
    boolean QIsColored = false;

    JPanel frame3;
    String[] titles = {"Magnitude", "Place", "Time", "Tsunami", "Coordinates"};

    JButton[] displays = {new JButton(), new JButton(), new JButton(), new JButton(), new JButton()};
    JButton[] quakeButtons;

    String curTitle;

    public EarthquakeInfoFrame(FeatureCollection featureCollection) {
        quakeButtons = new JButton[featureCollection.features.length];
        JFrame mainFrame = new JFrame("Wordle");
        mainFrame.setLayout(new GridLayout(3, 1));

        JPanel frame1 = new JPanel();
        frame1.setLayout(new GridLayout(1, featureCollection.features.length));


        for (int i = 0; i < featureCollection.features.length; i++) {
            quakeButtons[i] = new JButton();
            quakeButtons[i].setText("Quake " + (i + 1));
            quakeButtons[i].setBackground(Color.GRAY);
            quakeButtons[i].setOpaque(true);
            frame1.add(quakeButtons[i]);
            int ii = i;
            quakeButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.requestFocus();
                        for (int j = 0; j < quakeButtons.length; j++) {
                            quakeButtons[j].setBackground(Color.GRAY);
                            quakeButtons[j].setOpaque(true);
                        }
                        for (int j = 0; j < titles.length; j++) {
                            displays[j].setText("");
                        }
                        current = featureCollection.features[ii];
                        quakeButtons[ii].setBackground(Color.BLUE);
                        quakeButtons[ii].setOpaque(true);
                        QIsColored = true;
                }
            });
        }
        JPanel frame2 = new JPanel();
        frame2.setLayout(new GridLayout(1, titles.length));
        frame3 = new JPanel();
        frame3.setLayout(new GridLayout(1, titles.length));

        for (int i = 0; i < titles.length; i++) {
            JButton tButton = new JButton(titles[i]);
            frame2.add(tButton);
            tButton.setBackground(Color.GRAY);
            tButton.setOpaque(true);
            int ii = i;
            tButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.requestFocus();

                    tIsColored = true;
                    curTitle = tButton.getText();
                    checkChosen();

                }
            });

            for (int j = 0; j < displays.length; j++) {
                frame3.add(displays[i]);
                displays[i].setSize(30, 300);
                displays[i].setBackground(Color.GRAY);
                displays[i].setOpaque(true);
            }

        }
        frame1.setSize(500, 100);
        frame2.setSize(500, 100);
        frame3.setSize(500, 600);
        frame3.setVisible(true);
        frame2.setVisible(true);
        frame1.setVisible(true);

        mainFrame.add(frame1);
        mainFrame.add(frame2);
        mainFrame.add(frame3);
        mainFrame.setSize(1300, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);


    }

    private void checkChosen() {
        if (curTitle.equals("Magnitude")) {
            displays[0].setText(String.valueOf(current.properties.mag));
        } else if (curTitle.equals("Place")) {
            displays[1].setText(String.valueOf(current.properties.place));
        } else if (curTitle.equals("Time")) {
            displays[2].setText(String.valueOf(current.properties.time));
        } else if (curTitle.equals("Tsunami")) {
            displays[3].setText(String.valueOf(current.properties.tsunami));
        } else if (curTitle.equals("Coordinates")) {
            displays[4].setText("Lat: " + String.valueOf(current.geometry.coordinates[0])
                            + "\n Long: " + (String.valueOf(current.geometry.coordinates[1])));
        }
    }
}

