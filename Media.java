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

public class Media implements Comparable<Media> {

    private String artistName;
    private String mediaName;

    /**
     * Default Constructor that instantiate a Media Object with default values. 
     */
    public Media() {
        this.artistName = ""; 
        this.mediaName = ""; 
    }

    /**
     * Parameter constructor for the Media class to receive artist name and the
     * media name as parameter to initialize its attributes. 
     */
    public Media(String artistName, String mediaName) {
        this.artistName = artistName;
        this.mediaName = mediaName;
    }

    /**
     * Function to access the Artist's Name of this Media Object. 
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Function to set or update the Artist's Name for this Media Object. 
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Function to access the Media Name of this Media Object. 
     */
    public String getMediaName() {
        return mediaName;
    }

    /**
     * Function to set or update the Media Name of this Media Object. 
     */
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    @Override
    /**
     * Function to generate the String representing of this Media Object. 
     */
    public String toString() {
        return String.format("%-30s %-30s", artistName, mediaName); 
    }

    /**
     * Get The string to save into file for this object. 
     */
    public String getFileString() {
        return artistName + " " + mediaName;
    }

    /**
     * Function to Print all the information of this media.
     */
    public void print() {
        System.out.println("Media Name: " + mediaName);
        System.out.println("Main Artist Name: " + artistName);
    }

    @Override
    /**
     * Function to compare two Media Objects with respect to the Media Name.
     */
    public int compareTo(Media media) {
        return mediaName.compareTo(media.mediaName);
    }
}

