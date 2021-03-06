package co.enoobong.eno.twitter.fixer;

import twitter4j.StatusListener;
import twitter4j.TwitterStream;

/**
 * @author Ibanga Enoobong I
 * @since 23-Aug-17.
 */
public class Twitter4jFixer {

    public static void addListener(TwitterStream stream, StatusListener listener) {
        stream.addListener(listener);
    }
}
