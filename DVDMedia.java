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

public class DVDMedia extends Media {
          
    private int releaseYear;                                                          
    
    private ArrayList<String> starList;                                                
    /**
     * Default constructor to setup and instantiate a DVD Media Object with 
     * default values and empty collection of Co-Start names.
     */
    public DVDMedia() {
        super(); 
        releaseYear = 0; 
        starList = new ArrayList<String>();
    }

    /**
     * Parameter constructor to setup the DVDMedia Object with given parameter
     * values for the Artist Name, Media Name and the Release Year for this DVDMedia
     * Object. 
     */
    public DVDMedia(String artistName, String mediaName, int releaseYear) {
        super(artistName, mediaName);
        this.releaseYear = releaseYear; 
        this.starList = new ArrayList<String>();
    }

    /**
     * Function to access the Release Year of this DVD Media Object. 
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Get The string to save into file for this object. 
     */
    public String getFileString() {
        String stars = "";
        for(String star: starList) {
            stars += " " + star;
        }

        return "D " + super.getFileString() + stars;
    }

    /**
     * Function to Print all the information of this media.
     */
    public void print() {
        super.print();
        System.out.println("Release Year: " + releaseYear);
        System.out.println("Co-Stars:");
        for(String star: starList) {
            System.out.println("\t" + star);
        }
        System.out.println();
    }

    /**
     * Function to set or update the Release Year of this DVD Media Object. 
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    
    /**
     * Function to get the Collection of Co-Star Names who performed in this Movie. 
     */
    public ArrayList<String> getStarList() {
        return starList;
    }

    /**
     * Function to add a new Star into the Star List. 
     */
    public void addStar(String star) {
        this.starList.add(star);
    }

    /**
     * Function to set or update the Co-Star List of Performers of this Movie. 
     */
    public void setStarList(ArrayList<String> starList) {
        this.starList = starList;
    }

    @Override
    /**
     * Function to generate the String representing of this Media Object. 
     */
    public String toString() {
        return String.format("%-5s %s %d%n", "DVD",  super.toString(), releaseYear);  
    } 
}
