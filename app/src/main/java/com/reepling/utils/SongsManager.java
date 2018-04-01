package com.reepling.utils;

import android.os.Environment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Michaël on 26/02/2018.
 */

public class SongsManager {

    public static final String TAG = SongsManager.class.getSimpleName();

    // SDCard Path
    final String MEDIA_PATH = Environment.getExternalStorageDirectory().toString();
    //final String MEDIA_PATH = System.getenv("SECONDARY_STORAGE");
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    // Constructor
    public SongsManager(){

    }

    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     * */
    public ArrayList<HashMap<String, String>> getPlayList() throws Exception{

        File home = new File(MEDIA_PATH);
        /*

        Map<String, File> externalLocations = ExternalStorage.getAllStorageLocations();
        File sdCard = externalLocations.get(ExternalStorage.SD_CARD);
        File externalSdCard = externalLocations.get(ExternalStorage.EXTERNAL_SD_CARD);
        */

        if (home.listFiles(new FileExtensionFilter()).length > 0) {
            for (File file : home.listFiles(new FileExtensionFilter())) {
                HashMap<String, String> song = new HashMap<String, String>();
                song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
                song.put("songPath", file.getPath());

                // Adding each song to SongList
                songsList.add(song);
            }
        }
        // return songs list array
        return songsList;
    }

    /**
     * Class to filter files which are having .mp3 extension
     * */
    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3") || name.endsWith(".m4a") || name.endsWith(".M4A"));
        }
    }
}
