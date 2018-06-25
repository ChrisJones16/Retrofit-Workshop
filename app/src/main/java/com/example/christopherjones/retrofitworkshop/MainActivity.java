package com.example.christopherjones.retrofitworkshop;

import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.artist_name_editext)
    protected TextInputEditText artistName;

    @BindView(R.id.song_editext)
    protected TextInputEditText songTitle;

    private LyricsFragment lyricsFragment;

    public static final String ARTIST_NAME = "artist_name";
    public static final String SONG_TITLE = "song_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submit_button)
    protected void submitClicked(){

        if(artistName.getText().toString().isEmpty() || songTitle.getText().toString().isEmpty()) {
            //handle if missing input
            Toast.makeText(this, "All fields are Required! Try Again!", Toast.LENGTH_LONG).show();
        } else {
            //handle getting info from editTexts and passing it to the fragment for our API call
            lyricsFragment = LyricsFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString(ARTIST_NAME, artistName.getText().toString());
            bundle.putString(SONG_TITLE, songTitle.getText().toString());
            lyricsFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, lyricsFragment).commit();

        }
    }

    @Override
    public void onBackPressed() {
        if (lyricsFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(lyricsFragment).commit();
        } else {

            super.onBackPressed();
        }
    }
}
