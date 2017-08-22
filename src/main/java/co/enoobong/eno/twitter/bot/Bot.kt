package co.enoobong.eno.twitter.bot

import twitter4j.TwitterException
import twitter4j.TwitterFactory
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
    override fun contextDestroyed(sce: ServletContextEvent?) {
        scheduler.shutdown()
    }

    override fun contextInitialized(sce: ServletContextEvent) {
        println("Started up!")
        scheduler.scheduleAtFixedRate({ retweetMyMentions() }, 0, 5, TimeUnit.HOURS)
    }

    fun retweetMyMentions() {
        println("Started checking for mentions")
        val twitter = TwitterFactory.getSingleton()
        try {
            twitter.timelines().mentionsTimeline
                    .asSequence()
                    .filterNot { it.isRetweeted }
                    .forEach { twitter.retweetStatus(it.id) }
        } catch (ex: TwitterException) {
            println("Something bad happened $ex")
        }
    }
}