package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //resume
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Lower the volume, keep playing
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        //top back button
        //getActionBar().setDisplayHomeAsUpEnabled(true);


        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        //words.add(new Color());

//        words.add(new Word("Hello. (literally: good day)","Bonjour")); // done
//        words.add(new Word("My name is... (literally: I call myself)","Je m'appelle..."));
//        words.add(new Word("What is your name?","Comment vous appelezvous?"));
//        words.add(new Word("Please speak slowly.","Parlez lentement."));
//        words.add(new Word("I don't understand.","Je ne comprends pas."));
//        words.add(new Word("Thank you.","Merci"));
//        words.add(new Word("You' re welcome","De rien."));
//        words.add(new Word("Excuse me.","Excusez-moi."));
//        words.add(new Word("I love you. (informal)","Je t'aime."));
//        words.add(new Word("I want to be with you.","Je veux être avec toi."));
//        words.add(new Word("How are you? (formal or plural)","Comment allez-vous?"));
//        words.add(new Word("I am from...","Je suis de..."));
//        words.add(new Word("I would like...","Je voudrais..."));
//        words.add(new Word("Hi! / Bye! (informal)","Salut!"));  // done
//        words.add(new Word("How are you? (informal)","Ça va?"));
//        words.add(new Word("Where is...?","Où est-ce que se trouve...?"));
//        words.add(new Word("How much does it cost?","Combien coûte-t-il?"));
//        words.add(new Word("Please.","S'il vous plaît."));
//        words.add(new Word("Don't worry. (informal)","Ne t'en fais pas."));
//        words.add(new Word("Don’t worry. (formal)","Ne vous en faites pas."));
//        words.add(new Word("No.","Non."));
//        words.add(new Word("OK","D'accord"));
//        words.add(new Word("I don't understand.","Je ne comprends pas."));
//        words.add(new Word("Do you have...","Avez-vous..."));
//        words.add(new Word("Do you speak English?","Parlez-vous anglais?"));
//        words.add(new Word("What did you say?","Qu'est-ce que vous avez dit?"));
//        words.add(new Word("Where are the bathrooms?","Qu sont les toilettes?"));
//        words.add(new Word("No problem.","Ce n'est pas grave."));
//        words.add(new Word("I’m fine.","Je vais bien."));
//        words.add(new Word("Let’s go!","Allons-y!"));
//        words.add(new Word("Do you speak English? (informal)","Tu parles anglais?"));
//        words.add(new Word("I don't know.","Je ne sais pas."));
//        words.add(new Word("Goodbye","Au revoir."));   // done


        // doing extra..........
        words.add(new Word("Hello. (literally: good day)", "Bonjour", R.raw.hello)); // done
        words.add(new Word("See you Later", "Â tout à l'heure", R.raw.seeyoulater));
        words.add(new Word("See you Tomorrow", "à demain", R.raw.seeyoutomorrow));
        words.add(new Word("Goodbye", "Au revoir", R.raw.goodbye));
        words.add(new Word("How old are you?", "Tu as quel âge?", R.raw.howoldru));
        words.add(new Word("I am ____ years old", "J'ai ___ ans", R.raw.iamyearsold));
        words.add(new Word("How are you/How's it going?", "Comment ça va?", R.raw.howareyou));
        words.add(new Word("Hi! / Bye! (informal)", "Salut!", R.raw.hi_bye));  // done
        words.add(new Word("Very well", "Tres bien", R.raw.verywell));
        words.add(new Word("Not Bad", "Pas mal", R.raw.notbad));
        words.add(new Word("What is your name?", "Tu t'appelles comment?", R.raw.whatisyourname));
        words.add(new Word("My name is____", "Je m'appelle___", R.raw.mynameis));
        words.add(new Word("Yes", "Oui", R.raw.yes));
        words.add(new Word("I do", "Moi Si", R.raw.ido));
        words.add(new Word("I don't", "Moi Non", R.raw.idont));
        words.add(new Word("What classes do you have?", "Tu as quels cours?", R.raw.whatclassesyouhave));
        words.add(new Word("In the Morning", "Le Matin", R.raw.inthemorning));
        words.add(new Word("In the Afternoon", "L'après-midi", R.raw.intheafternoon));
        words.add(new Word("Today", "Aujourd'hui", R.raw.today));
        words.add(new Word("Tomorrow", "Demain", R.raw.tomorrow));
        words.add(new Word("I'm hungry", "J'ai faim", R.raw.imhungry));
        words.add(new Word("I'm thirsty", "J'ai soif", R.raw.imthrusty));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        // WordAdapter adapter = new WordAdapter(this, words);
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback


                    releaseMediaPlayer();

                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmAudioResourceId());
                    mMediaPlayer.start();
                }
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
