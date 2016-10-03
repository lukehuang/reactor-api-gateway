/**
 * 
 */
package infra.config;

import java.net.InetSocketAddress;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.config.ServerOptions;
import reactor.ipc.netty.http.HttpChannel;
import reactor.ipc.netty.http.HttpServer;

/**
 * HTTP Server 
 * @author Wennerstrom
 * @version 1.0
 * @since 09/2016
 */
@Component
public class B2SkyHttpServer {
	static final Logger LOG = LoggerFactory.getLogger(B2SkyHttpServer.class);
	
	
	private HttpServer httpServer;
	
	private ServerOptions options;

	public B2SkyHttpServer(){
		try {
			setupServer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setupServer() throws InterruptedException {
	    options = ServerOptions.create().listen(new InetSocketAddress("0.0.0.0", Integer.valueOf(8783)))
				 .keepAlive(false);
		
		httpServer = HttpServer.create(options);
		
		LOG.info("Osiris Http Server: " + this.options.listenAddress().getHostName());
		LOG.info("Osiris Http Server[Port]: " + this.options.listenAddress().getPort());
	}
	
	public HttpServer b2SkyReactorServer(){
		return this.httpServer;
	}

	/**
	 * get handler
	 * @return
	 **/
	public Function<? super HttpChannel, ? extends Publisher<Void>> getHandlerTest() {
		return channel -> {
			LOG.info(" Mensagem recebida from thread", Thread.currentThread());
			return channel.removeTransferEncodingChunked()
					.sendString(Mono.just("<teste>Ola</teste>"))
					.doAfterTerminate(new BiConsumerTest(channel.delegate()));
		};
	}
}
