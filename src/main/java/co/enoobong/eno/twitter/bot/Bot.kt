package co.enoobong.eno.twitter.bot

import co.enoobong.eno.twitter.fixer.Twitter4jFixer
import twitter4j.FilterQuery
import twitter4j.StallWarning
import twitter4j.Status
import twitter4j.StatusDeletionNotice
import twitter4j.StatusListener
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.TwitterStream
import twitter4j.TwitterStreamFactory
import java.lang.Exception
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener


/**
 * @author Ibanga Enoobong I
 * @since 18-Aug-17.
 */
@WebListener
class Bot : ServletContextListener {

    private val scheduler: ScheduledExecutorService by lazy {
        Executors.newSingleThreadScheduledExecutor()
    }

    private val twitter: Twitter by lazy {
        TwitterFactory.getSingleton()
    }
    override fun contextDestroyed(sce: ServletContextEvent?) {
        scheduler.shutdown()
    }

    override fun contextInitialized(sce: ServletContextEvent) {
        println("Started up!")
        //retweetMyInterests()
        scheduler.scheduleAtFixedRate({ retweetMyMentions() }, 0, 15, TimeUnit.MINUTES)
    }

    fun retweetMyMentions() {
        println("Started checking for mentions")
        try {
            twitter.timelines().mentionsTimeline
                    .asSequence()
                    .filterNot { it.isRetweeted }
                    .forEach { twitter.retweetStatus(it.id) }
        } catch (ex: TwitterException) {
            println("Something bad happened $ex")
        }
    }

    private fun retweetMyInterests() {
        println("Started Checking for my interests")

        val listener = object : StatusListener {
            override fun onTrackLimitationNotice(numberOfLimitedStatuses: Int) {
                println("onTrackLimitationNotice called")
            }

            override fun onStallWarning(warning: StallWarning?) {
                println("onStallWarning called")
            }

            override fun onException(ex: Exception) {
                println("Something terrible happened $ex")
            }

            override fun onDeletionNotice(statusDeletionNotice: StatusDeletionNotice?) {
                println("onDeletionNotice called")
            }

            override fun onStatus(status: Status) {
                if (!status.isRetweeted) {
                    twitter.retweetStatus(status.id)
                }
            }

            override fun onScrubGeo(userId: Long, upToStatusId: Long) {
                println("onScrubGeo called")
            }
        }

        val twitterStream = TwitterStreamFactory().instance
        twitterStream.addListenerFixed(listener)
        val filter = FilterQuery()
        //TODO externalize interests
        val track = arrayOf("#forLoopCU", "#ThisIsAndela")
        filter.track(*track).language("en")

        twitterStream.filter(filter)
    }

    private fun TwitterStream.addListenerFixed(listener: StatusListener) {
        Twitter4jFixer.addListener(this, listener)
    }
}
