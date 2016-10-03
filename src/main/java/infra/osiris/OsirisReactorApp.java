package infra.osiris;

import infra.config.B2SkyHttpServer;	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
/**
 * B2Sky Osiris API Gateway Application.
 * @author Philip
 * @version 1.5
 * @since 06/2016
 */
@SpringBootApplication
@ComponentScan("infra.config")
@EnableAutoConfiguration() 
public class OsirisReactorApp {	
	static final Logger LOG = LoggerFactory.getLogger(OsirisReactorApp.class);
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("reactor.tcp.ioThreadCount", "2");
	    ApplicationContext  ctx = SpringApplication.run(OsirisReactorApp.class, args);
		
		LOG.info("Setting HTTP Server");
		B2SkyHttpServer httpServer =(B2SkyHttpServer) ctx.getBean("b2SkyHttpServer");
		httpServer.b2SkyReactorServer().get("/test", httpServer.getHandlerTest());
	
		LOG.info("HTTP Get Endpoints started");
		httpServer.b2SkyReactorServer().
		start().doOnSuccess(a ->{LOG.info("Osiris HTTP Server started");}).subscribe();
		Thread.sleep(100000);
	}
}
