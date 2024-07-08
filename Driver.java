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

import java.util.*; 
import java.io.*;

/**
 * @author 3156908
 */

public class Driver {

    /**
     * Main Method - entry point of the program. 
     */
    public static void main(String [] args) {

        ArrayList<Media> mediaList = loadMedia("catalog2.txt");                                //Load Media.//
        
        System.out.println("Media Library - Sorted by Media Name");
        System.out.println("*************************************\n");
        Collections.sort(mediaList);    
        printMediaList(mediaList);

        System.out.println("*********************************************************"); //Sort by Artist Name.//
        System.out.println("\nMedia Library - Sorted by Artist Name:");
        System.out.println("**************************************\n");
        
        Collections.sort(mediaList, new ComparatorByArtistName());
        printMediaList(mediaList);

        Scanner keyboard = new Scanner(System.in);                                             //Loop for Menu interaction.//
        int choice;
        do {
            choice = menu(keyboard);
            System.out.println("\n");
            if(choice == 1) {
                doSearchByName(mediaList, keyboard);                                           //Search by title.//
            } else if (choice == 2) {
                dosearchByArtist(mediaList, keyboard);
            } else if(choice == 3) {
                addMedia(mediaList, keyboard);                                                 //Add New Media.//
            } else if(choice == 4) {
                System.out.println("\nThank you for coming! Goodbye!");
            }
            System.out.println("\n");
        } while(choice != 4);
    }

    /**
     * Search the media list by the artist name using Binary Search and then
     * display all the media related to that artist.
     */
    private static void dosearchByArtist(ArrayList<Media> mediaList, Scanner keyboard) {

        ArrayList<Media> list = new ArrayList<Media>();

        String name = readString(keyboard, "Please enter artist's name to search: ");          //Prompt to get the media name.//

        if(name.trim().length() == 0) {
            System.out.println("No name is entered - try again!");
            return;
        }

        Media media = new Media(name, "");                                                    //Sort and search.//
        ComparatorByArtistName cmp = new ComparatorByArtistName(); 
        Collections.sort(mediaList, cmp);
        int idx = Collections.binarySearch(mediaList, media, cmp);

        for(int i=idx; i>=0; i--) {                                                          //Fetch all the Media having same artist name.//                                                            
            if(mediaList.get(i).getArtistName().equals(name)) {                              //Search backwards.
                list.add(mediaList.get(i)); 
            } else {
                break;
            }
        }
                                                                                             //Search forward.//
        for(int i=idx+1; i<mediaList.size(); i++) {
            if(mediaList.get(i).getArtistName().equals(name)) {
                list.add(mediaList.get(i));
            } else {
                break;
            }
        }

        System.out.println("\nAll the media for artist: " + name);
        System.out.println();
        printMediaList(list);
    }

    /**
     * Function to add new Media and save it to the file. 
     */
    public static void addMedia(ArrayList<Media> mediaList, Scanner keyboard) {

        int choice = mediaMenu(keyboard);                                                   //Get the Media Type Selection.//

        if(choice == 1 || choice == 2) {

            String artistName = readString(keyboard, "Please enter main artist name: ");
            String mediaName = readString(keyboard, "Please enter Media Name: ");
            
            if(choice == 2) {

                CDMedia media = new CDMedia(artistName, mediaName);

                String song;                                                                //Get the List of Songs.//
                do {
                    song = readString(keyboard, "Enter Song title or q to stop: ");
                    if(!song.equalsIgnoreCase("q")) {
                        media.addSong(song);
                    }
                }while(!song.equalsIgnoreCase("q"));

                mediaList.add(media);
            } else {

                int year = readInt(keyboard, "Please enter release year: ");

                DVDMedia media = new DVDMedia(artistName, mediaName, year);

                String star;
                do {
                    star = readString(keyboard, "Enter Star name or q to stop: ");
                    if(!star.equalsIgnoreCase("q")) {
                        media.addStar(star);
                    }
                }while(!star.equalsIgnoreCase("q"));
                
                mediaList.add(media);
            }

            saveToFile(mediaList, keyboard);                                                //Save to File.//
            System.out.println("Media has been saved to file");
        }
    }

    /**
     * Function to save the media list to the file.  
     */
    private static void saveToFile(ArrayList<Media> mediaList, Scanner keyboard) {
        String fileName = readString(keyboard, "Please enter output file name: ");

        try {
            File file = new File(fileName);
            PrintWriter writer = new PrintWriter(new FileWriter(file));

            for(Media m: mediaList) {
                writer.println(m.getFileString());
            }

            writer.close();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Display menu for media type selection to create new media.
     */
    private static int mediaMenu(Scanner keyboard) {
        System.out.println("Select Media Type");
        System.out.println("1. DVD Media");
        System.out.println("2. CD Media");
        System.out.println("3. Back to main menu");
        int choice = 0;

        do {
            choice = readInt(keyboard, "Enter Option: ");
            if (choice < 1 || choice > 3) {
                System.out.println("Please enter choice in range (1-3)");
            }
        } while(choice < 1 || choice > 3);

        return choice; 
    } 


    /**
     * Function to Perform Sort by media name and then use binary search
     * to find the media. If found display all of its information. 
     */
    private static void doSearchByName(ArrayList<Media> mediaList, Scanner keyboard) {

        String name = readString(keyboard, "Please enter media name to search: ");                  //Prompt to get the Media Name.//

        if(name.trim().length() == 0) {
            System.out.println("No name is entered - try again");
            return;
        }
        
        Collections.sort(mediaList);                                                                //Sort and search.//
        Media media = new Media("", name);
        int idx = Collections.binarySearch(mediaList, media);
        if (idx >= 0) {
            mediaList.get(idx).print();
        } else {
            System.out.println("Media not Found with name: " + name);
        }
    } 

    /**
     * Function to read a valid Integer from the Keyboard.
     */
    private static int readInt(Scanner keyboard, String message) {
        int value = 0; 
        boolean valid = false; 

        do {
            try {
                String input = readString(keyboard, message);
                value = Integer.parseInt(input);
                valid = true; 
            } catch(Exception e) {
            }
        } while(!valid);

        return value; 
    }

    /**
     * Function to read a String from the Keyboard.
     */
    private static String readString(Scanner keyboard, String message) {
        System.out.print(message);
        return keyboard.nextLine();
    }

    /**
     * Function to display the Menu and asked the user to enter a selection. 
     * return the selection. 
     */
    private static int menu(Scanner keyboard) {
        System.out.println("\n\n1.	Search by Media Title (movie name or album name)\n"+
                "2.	Search by Artist (singer or actor)\n"+
                "3.	Add media to catalog\n"+
                "4.	Quit");
        
        int choice = 0;
        
        do {
            choice = readInt(keyboard, "Enter option: ");

            if(choice < 1 || choice > 4) {
                System.out.println("Inavlid Choice - Enter (1-4)");
            }
        } while(choice < 1 || choice > 4);

        return choice; 
    } 

    /**
     * Function to display the Media List.
     */
    private static void printMediaList(ArrayList<Media> list) {
        System.out.printf("%-5s %-30s %-30s %s%n", "Type", "Artist Name", "Media Name", "Release Year");

        for(Media m: list) {
            System.out.print(m);
        }
    }

    /**
     * Class to load the Media data from the input file and store the Media 
     * Objects into the ArrayList. 
     */
    private static ArrayList<Media> loadMedia(String fileName) {
        ArrayList<Media> list = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file); 

            while(reader.hasNextLine()) {
                String line = reader.nextLine();

                String tokens [] = line.split(" ");

                if(tokens[0].equalsIgnoreCase("C")) {                                                     //Create CD Media.//
                    CDMedia media = new CDMedia(tokens[1], tokens[2]);
                    for(int i=3; i<tokens.length; i++) {
                        media.addSong(tokens[i]);
                    }
                    list.add(media);
                } else {
                    try {
                        DVDMedia media = new DVDMedia(tokens[1], tokens[2], Integer.parseInt(tokens[3])); //Create DVD Media.//
                        for(int i=4; i<tokens.length; i++) {
                            media.addStar(tokens[i]);
                        }
                        list.add(media);
                    } catch(Exception e) {}
                }
            }

            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return list; 
    }
    
}
