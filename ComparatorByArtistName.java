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

import java.util.Comparator;

/**
 * 
 * @author 3156908
 */

public class ComparatorByArtistName implements Comparator<Media> {

    @Override
    /**
     * Function to compare two Media Objects with respect to Artist Name 
     * of those media object. 
     */
    public int compare(Media mediaLeft, Media mediaRight) {
        return mediaLeft.getArtistName().compareTo(mediaRight.getArtistName());
    }
    
}
