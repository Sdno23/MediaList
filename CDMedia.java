/*
 * @author XXXXX08
 *
 * @version 1.0 10/10/2020
 *
 * Title: Comparable/Comparator Interfaces AND Exception Handling
 *
 * Semester: Fall 2020
 *
 * Lecturer's Name: C. Charters
 */

import java.util.ArrayList;

/**
 * 
 * @author XXXXX08
 */

public class CDMedia extends Media {
   
    private ArrayList<String> songList;                             

    /**
     * Default constructor of the CDMedia class with empty list of songs. 
     */
    public CDMedia() {
        super();                                                  
        this.songList = new ArrayList<String>();
    }

    /**
     * Parameter constructor to construct a CDMedia object with artist name
     * and media name passed to the constructor as parameter. 
     */
    public CDMedia(String artistName, String mediaName) {
        super(artistName, mediaName);
        this.songList = new ArrayList<String>();
    }

    /**
     * Function to get the Song List in this CD Media Object. 
     */
    public ArrayList<String> getSongList() {
        return songList;
    }

    /**
     * Function to add a new song into the Song List. 
     */
    public void addSong(String song) {
        this.songList.add(song);
    }

    /**
     * Function to set or update the Song List of this CD Media.
     */
    public void setSongList(ArrayList<String> songList) {
        this.songList = songList;
    }

    /**
     * Function to Print all the information of this media.
     */
    public void print() {
        super.print();
        System.out.println("Songs:");
        for(String song: songList) {
            System.out.println("\t" + song);
        }
        System.out.println();
    }

    /**
     * Get the string to save into file for this object. 
     */
    public String getFileString() {
        String songs = "";
        for(String song: songList) {
            songs += " " + song;
        }

        return "C " + super.getFileString() + songs;
    }

    @Override
    /**
     * Function to generate the String representation of this Media Object.  
     */
    public String toString() {
        return String.format("%-5s %s%n", "CD", super.toString());
    }   

}
