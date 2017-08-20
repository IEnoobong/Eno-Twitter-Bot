package co.enoobong.eno.twitter.bot





import twitter4j.Logger
import twitter4j.StallWarning
import twitter4j.Status
import twitter4j.StatusDeletionNotice
import twitter4j.StatusListener
import twitter4j.TwitterStreamFactory
import twitter4j.UserStreamListener


/**
 * @author Ibanga Enoobong I
 * @since 18-Aug-17.
 */
object Demo {

        private val twitterStream = TwitterStreamFactory().instance
        @JvmStatic
        fun main(args: Array<String>) {

            val listener = object : StatusListener {

                override fun onStallWarning(warning: StallWarning?) {
                    println("Got stall warning:" + warning)
                }

                override fun onScrubGeo(userId: Long, upToStatusId: Long) {
                    println("Got scrub_geo event userId:$userId upToStatusId:$upToStatusId")
                }

                override fun onStatus(status: Status) {
                    println("@" + status.user.screenName + " - " + status.text)
                }

                override fun onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {
                    println("Got a status deletion notice id:" + statusDeletionNotice.statusId)
                }
                override fun onTrackLimitationNotice(numberOfLimitedStatuses: Int) {
                    println("Got track limitation notice:" + numberOfLimitedStatuses)
                }
                override fun onException(ex: Exception) {
                    ex.printStackTrace()
                }
            }

            twitterStream.addListener(listener)
            twitterStream.sample()

        }

}