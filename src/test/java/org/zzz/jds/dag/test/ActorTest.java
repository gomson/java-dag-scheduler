/**
 * 
 */
package org.zzz.jds.dag.test;

import static org.junit.Assert.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zzz.actor.Pid;

/**
 * @author ming luo
 *
 */
public class ActorTest {

    private final String ACTOR1 = "actor1";
    private final String ACTOR2 = "actor2";
    private SimpleActor actor1 = new SimpleActor(ACTOR1);
    private SimpleActor actor2 = new SimpleActor(ACTOR2);
    Pid pid = Pid.getInstance();
	/**
	 * @throws java.lang.Exception
	 */
    @BeforeClass 
    public static void setUpBeforeClass() throws Exception {
        
    }

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
    public void setUp() throws Exception {
    }

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

    @Test
    public void testSendToActor() throws InterruptedException {
        UUID sender = UUID.randomUUID();
        String message = "tonightiscold";
        pid.send(ACTOR1, message, sender);
        TimeUnit.MILLISECONDS.sleep(20);
        log(actor1.getReceivedMsg());

        assertTrue(message.equals(actor1.getReceivedMsg()));
        assert(sender.equals(actor1.getSender()));
    }
    @Test
    public void testSendAndReceive() throws InterruptedException {
        String fromActor1 = "hellofromactor1";
        actor1.send(ACTOR2, fromActor1);
        TimeUnit.MILLISECONDS.sleep(200);
        log(actor2.getReceivedMsg());
        assertTrue(fromActor1.equals(actor2.getReceivedMsg()));
        assert(actor1.getPid().equals(actor2.getSender()));
    }
    @Test
    public void testSendAndReceive2() throws InterruptedException {
        final String ACTOR3 = "actor3";
        SimpleActor actor3 = new SimpleActor(ACTOR3);
        String fromActor2 = "hellofromactor2";
        actor2.send(ACTOR3, fromActor2);

        TimeUnit.MILLISECONDS.sleep(200);
        assertTrue(fromActor2.equals(actor3.getReceivedMsg()));
        assert(actor2.getPid().equals(actor3.getSender()));
    }
    public void log(String msg) {
        System.out.println(msg);
    }
}
