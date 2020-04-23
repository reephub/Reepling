package com.reepling.fragments;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.reepling.R;
import com.reepling.fragments.adapter.ListeningSongsAdapter;
import com.reepling.utils.SongsManager;
import com.reepling.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Michaël on 08/02/2018.
 */

public class ListeningFragment extends Fragment implements ListeningSongsAdapter.ListeningSongsAdapterListener, MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private static final String TAG = ListeningFragment.class.getSimpleName();

    private Context mContext;

    //Views
    // All player buttons
    @BindView(R.id.btnPlay)
    ImageButton btnPlay;
    @BindView(R.id.btnForward)
    ImageButton btnForward;
    @BindView(R.id.btnBackward)
    ImageButton btnBackward;
    @BindView(R.id.btnNext)
    ImageButton btnNext;
    @BindView(R.id.btnPrevious)
    ImageButton btnPrevious;
    /*@Nullable @BindView(R.id.btnPlaylist) ImageButton btnPlaylist;
    @Nullable @BindView(R.id.btnRepeat) ImageButton btnRepeat;
    @Nullable @BindView(R.id.btnShuffle) ImageButton btnShuffle;*/
    @BindView(R.id.songProgressBar)
    SeekBar songProgressBar;
    @BindView(R.id.songTitle)
    TextView songTitleLabel;
    @BindView(R.id.songCurrentDurationLabel)
    TextView songCurrentDurationLabel;
    @BindView(R.id.songTotalDurationLabel)
    TextView songTotalDurationLabel;

    @BindView(R.id.playlistSongsRecyclerView)
    RecyclerView songsRecyclerView;

    private ListeningSongsAdapter adapter;

    // Media Player
    private MediaPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();
    private SongsManager songManager;
    private Utils utils;
    private int seekForwardTime = 5000; // 5000 milliseconds
    private int seekBackwardTime = 5000; // 5000 milliseconds
    private int currentSongIndex = 0;
    private boolean isShuffle = false;
    private boolean isRepeat = false;

    // Songs list
    public ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    public ListeningFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listening, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Mediaplayer
        mp = new MediaPlayer();
        songManager = new SongsManager();
        utils = new Utils();

        // Listeners
        songProgressBar.setOnSeekBarChangeListener(this); // Important
        mp.setOnCompletionListener(this); // Important

        setListeners();

        requestReadStoragePermission();

/*
        // Adding menuItems to ListView
        adapter = new ListeningSongsAdapter(mContext, getSongsFromFiles(), this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        songsRecyclerView.setLayoutManager(mLayoutManager);
        songsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        songsRecyclerView.setAdapter(adapter);*/

    }

    /**
     * Requesting multiple permissions (storage and location) at once
     * This uses multiple permission model from dexter
     * On permanent denial opens settings dialog
     */
    private void requestReadStoragePermission() {

        Dexter.withActivity((AppCompatActivity) mContext)
                .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Utils.showActionInToast(mContext, "Read External Storage permission granted");
                        setUi();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            // navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(mContext, "Error occurred! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }).check();
    }

    public void setUi() {

        // Adding menuItems to ListView
        adapter = new ListeningSongsAdapter(mContext, getSongsFromFiles(), this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        songsRecyclerView.setLayoutManager(mLayoutManager);
        songsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        songsRecyclerView.setAdapter(adapter);
    }

    public ArrayList<HashMap<String, String>> getSongsFromFiles() {

        ArrayList<HashMap<String, String>> songsListData = new ArrayList<HashMap<String, String>>();

        // get all songs from sdcard
        try {
            this.songsList = songManager.getPlayList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // looping through playlist
        // creating new HashMap
        // adding HashList to ArrayList
        songsListData.addAll(songsList);

        return songsListData;
    }

    public void setListeners() {

        /**
         * Play button click event
         * plays a song and changes button to pause image
         * pauses a song and changes button to play image
         * */
        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // check for already playing
                if (mp.isPlaying()) {
                    if (mp != null) {
                        mp.pause();
                        // Changing button image to play button
                        btnPlay.setImageResource(R.drawable.btn_play);
                    }
                } else {
                    // Resume song
                    if (mp != null) {
                        mp.start();
                        // Changing button image to pause button
                        btnPlay.setImageResource(R.drawable.btn_pause);
                    }
                }

            }
        });


        /**
         * Forward button click event
         * Forwards song specified seconds
         * */
        btnForward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // get current song position
                int currentPosition = mp.getCurrentPosition();
                // check if seekForward time is lesser than song duration
                if (currentPosition + seekForwardTime <= mp.getDuration()) {
                    // forward song
                    mp.seekTo(currentPosition + seekForwardTime);
                } else {
                    // forward to end position
                    mp.seekTo(mp.getDuration());
                }
            }
        });


        /**
         * Backward button click event
         * Backward song to specified seconds
         * */
        btnBackward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // get current song position
                int currentPosition = mp.getCurrentPosition();
                // check if seekBackward time is greater than 0 sec
                if (currentPosition - seekBackwardTime >= 0) {
                    // forward song
                    mp.seekTo(currentPosition - seekBackwardTime);
                } else {
                    // backward to starting position
                    mp.seekTo(0);
                }

            }
        });


        /**
         * Next button click event
         * Plays next song by taking currentSongIndex + 1
         * */
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // check if next song is there or not
                if (currentSongIndex < (songsList.size() - 1)) {
                    playSong(currentSongIndex + 1);
                    currentSongIndex = currentSongIndex + 1;
                } else {
                    // play first song
                    playSong(0);
                    currentSongIndex = 0;
                }

            }
        });


        /**
         * Back button click event
         * Plays previous song by currentSongIndex - 1
         * */
        btnPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (currentSongIndex > 0) {
                    playSong(currentSongIndex - 1);
                    currentSongIndex = currentSongIndex - 1;
                } else {
                    // play last song
                    playSong(songsList.size() - 1);
                    currentSongIndex = songsList.size() - 1;
                }

            }
        });
    }

    /**
     * Receiving song index from playlist view
     * and play the song
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 100) {
            currentSongIndex = data.getExtras().getInt("songIndex");
            // play selected song
            playSong(currentSongIndex);
        }
    }


    @Override
    public void onSongSelected(String song) {
        Toast.makeText(mContext, "Selected: " + song + ",   ++++ " + song, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSongSelected(int position) {
        playSong(position);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        // check for repeat is ON or OFF
        if (isRepeat) {
            // repeat is on play same song again
            playSong(currentSongIndex);
        } else if (isShuffle) {
            // shuffle is on - play a random song
            Random rand = new Random();
            currentSongIndex = rand.nextInt((songsList.size() - 1) - 0 + 1) + 0;
            playSong(currentSongIndex);
        } else {
            // no repeat or shuffle ON - play next song
            if (currentSongIndex < (songsList.size() - 1)) {
                playSong(currentSongIndex + 1);
                currentSongIndex = currentSongIndex + 1;
            } else {
                // play first song
                playSong(0);
                currentSongIndex = 0;
            }
        }
    }

    /**
     * Function to play a song
     *
     * @param songIndex - index of song
     */
    public void playSong(int songIndex) {
        // Play song
        try {
            mp.reset();
            mp.setDataSource(songsList.get(songIndex).get("songPath"));
            mp.prepare();
            mp.start();
            // Displaying Song title
            String songTitle = songsList.get(songIndex).get("songTitle");
            songTitleLabel.setText(songTitle);

            // Changing Button Image to pause image
            btnPlay.setImageResource(R.drawable.btn_pause);

            // set Progress bar values
            songProgressBar.setProgress(0);
            songProgressBar.setMax(100);

            // Updating progress bar
            updateProgressBar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Update timer on seekbar
     */
    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mp.getDuration();
            long currentDuration = mp.getCurrentPosition();

            // Displaying Total Duration time
            songTotalDurationLabel.setText("" + utils.milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            songCurrentDurationLabel.setText("" + utils.milliSecondsToTimer(currentDuration));

            // Updating progress bar
            int progress = (int) (utils.getProgressPercentage(currentDuration, totalDuration));
            //Log.i("Progress", ""+progress);
            songProgressBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };


    /**
     *
     * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    /**
     * When user starts moving the progress handler
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    /**
     * When user stops moving the progress hanlder
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mp.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }

}