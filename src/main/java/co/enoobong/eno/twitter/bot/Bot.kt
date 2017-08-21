package co.enoobong.eno.twitter.bot


import twitter4j.TwitterException
import twitter4j.TwitterFactory
import javax.ejb.Schedule
import javax.ejb.Singleton
import javax.ejb.Startup


/**
 * @author Ibanga Enoobong I
 * @since 18-Aug-17.
 */
@Singleton
@Startup
class Bot {

    @Schedule(hour = "*/5")
    fun retweetMyMentions() {
        val twitter = TwitterFactory.getSingleton()
        try {
            twitter.timelines().mentionsTimeline.forEach { twitter.retweetStatus(it.id) }
        } catch (ex: TwitterException) {
            ex.printStackTrace()
        }
    }
}