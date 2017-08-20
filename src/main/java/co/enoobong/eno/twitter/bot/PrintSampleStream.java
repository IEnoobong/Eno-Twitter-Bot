package co.enoobong.eno.twitter.bot;

import java.util.Arrays;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 * @author Ibanga Enoobong I
 * @since 19-Aug-17.
 */
public final class PrintSampleStream {
    /**
     * Main entry of this application.
     *
     * @param args arguments doesn't take effect with this example
     * @throws TwitterException when Twitter service or network is unavailable
     */
    public static void main(String[] args) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        String[] track = {"TEDxYaba", "forLoopKampala", "forLoopKLA", "NIF17", "GDGUnilag", "learnGit", "TEDxYaba17", "Android", "Java", };

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener listener = new StatusListener() {
          @Override
          public void onStatus(Status status) {


                      try {
                          twitter.retweetStatus(status.getId());
                      } catch (TwitterException e) {
                          e.printStackTrace();
                      }
                      




              System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());

          }

          @Override
          public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            //                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
          }

          @Override
          public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            //                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
          }

          @Override
          public void onScrubGeo(long userId, long upToStatusId) {
            //                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
          }

          @Override
          public void onStallWarning(StallWarning warning) {
            //                System.out.println("Got stall warning:" + warning);
          }

          @Override
          public void onException(Exception ex) {
            ex.printStackTrace();
          }
        };

        twitterStream.addListener(listener);
        twitterStream.filter(track);
        twitterStream.sample("en");
    }
}