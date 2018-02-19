package com.imkiran.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.imkiran.bakingapp.models.Recipe;
import com.imkiran.bakingapp.models.Steps;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepsSnapFragment extends Fragment implements ExoPlayer.EventListener {

    public RecipeStepsSnapFragment() {

    }

    private ItemClickListener itemClickListener;


    public interface ItemClickListener {
        void onClick(List<Steps> stepsList, int clickedIndex);
    }


    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer simpleExoPlayer;
    private final String TAG = RecipeStepsSnapFragment.class.getSimpleName();
    private MediaSessionCompat mediaSessionCompat;
    private PlaybackStateCompat.Builder playbackStateCompatBuilder;
    private long videoPosition;
    private TextView recipeVideoInstruction;
    private ArrayList<Steps> steps = new ArrayList<>();
    private ArrayList<Recipe> recipes;
    private int clickedIndex;


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        itemClickListener = (RecipeStepsActivity) getActivity();

        if (savedInstanceState != null) {
            steps = savedInstanceState.getParcelableArrayList(getResources().getString(R.string.recipe_step_selected));
            clickedIndex = savedInstanceState.getInt(getResources().getString(R.string.clicked_index));
        } else {
            steps = getArguments().getParcelableArrayList(getResources().getString(R.string.recipe_step_selected));
            if (steps != null) {
                steps = getArguments().getParcelableArrayList(getResources().getString(R.string.recipe_step_selected));
                clickedIndex = getArguments().getInt(getResources().getString(R.string.clicked_index));
            } else {
                recipes = getArguments().getParcelableArrayList(getString(R.string.parcel_recipe));
                steps = (ArrayList<Steps>) recipes.get(0).getSteps();
                clickedIndex = 0;
            }
        }

        View rootView = layoutInflater.inflate(R.layout.fragment_recipe_steps_snap, viewGroup, false);

        simpleExoPlayerView = rootView.findViewById(R.id.playerView);
        recipeVideoInstruction = rootView.findViewById(R.id.recipe_video_instruction);
        recipeVideoInstruction.setText(steps.get(clickedIndex).getDescription());
        recipeVideoInstruction.setVisibility(View.VISIBLE);

        String videoUrl = Uri.parse(steps.get(clickedIndex).getVideoURL()).toString();

        if (!videoUrl.isEmpty()) {
            initializePlayer(Uri.parse(steps.get(clickedIndex).getVideoURL()));
            initializeMediaSession();
        } else {
            simpleExoPlayer = null;
            simpleExoPlayerView.setForeground(ContextCompat.getDrawable(getContext(), R.drawable.no_video));
            simpleExoPlayerView.setLayoutParams(new ConstraintLayout.LayoutParams(300, 300));
            recipeVideoInstruction.setTextColor(getResources().getColor(R.color.black));
        }


        Button previousStep = rootView.findViewById(R.id.previousStep);
        Button nextStep = rootView.findViewById(R.id.nextStep);

        previousStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (steps.get(clickedIndex).getId() > 0) {
                    if (simpleExoPlayer != null) {
                        simpleExoPlayer.stop();
                    }
                    itemClickListener.onClick(steps, steps.get(clickedIndex).getId() - 1);
                } else {
                    Toast.makeText(getActivity(), "You already are in the First step of the recipe", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastIndex = steps.size() - 1;
                if (steps.get(clickedIndex).getId() < steps.get(lastIndex).getId()) {
                    if (simpleExoPlayer != null) {
                        simpleExoPlayer.stop();
                    }
                    itemClickListener.onClick(steps, steps.get(clickedIndex).getId() + 1);
                } else {
                    Toast.makeText(getActivity(), "You already are in the Last step of the recipe", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootView;
    }

    private void initializePlayer(Uri uri) {
        if (simpleExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.addListener(this);
            String userAgent = Util.getUserAgent(getContext(), "sample");
            MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
        }
    }

    private void initializeMediaSession() {

        // Create a MediaSessionCompat.
        mediaSessionCompat = new MediaSessionCompat(getContext(), TAG);

        // Enable callbacks from MediaButtons and TransportControls.
        mediaSessionCompat.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Do not let MediaButtons restart the player when the app is not visible.
        mediaSessionCompat.setMediaButtonReceiver(null);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player.
        playbackStateCompatBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSessionCompat.setPlaybackState(playbackStateCompatBuilder.build());


        // MySessionCallback has methods that handle callbacks from a media controller.
        mediaSessionCompat.setCallback(new MySessionCallback());

        // Start the Media Session since the activity is active.
        mediaSessionCompat.setActive(true);

    }

    public void releasePlayer() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (simpleExoPlayer != null) {
            releasePlayer();
            mediaSessionCompat.setActive(false);
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            playbackStateCompatBuilder.setState(PlaybackStateCompat.STATE_PLAYING, simpleExoPlayer.getCurrentPosition(), 1f);
        } else if (playbackState == ExoPlayer.STATE_READY) {
            playbackStateCompatBuilder.setState(PlaybackStateCompat.STATE_PAUSED, simpleExoPlayer.getCurrentPosition(), 1f);
        }

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            simpleExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            simpleExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            simpleExoPlayer.seekTo(0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (simpleExoPlayer != null) {
            videoPosition = simpleExoPlayer.getCurrentPosition();
            outState.putLong(getResources().getString(R.string.seek_position), simpleExoPlayer.getCurrentPosition());
        }
        outState.putParcelableArrayList(getResources().getString(R.string.recipe_step_selected), steps);
        outState.putInt(getResources().getString(R.string.clicked_index), clickedIndex);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            videoPosition = savedInstanceState.getLong(getResources().getString(R.string.seek_position));
            simpleExoPlayer.seekTo(videoPosition);
        }
    }
}
