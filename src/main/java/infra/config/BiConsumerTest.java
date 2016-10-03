package infra.config;

import java.util.function.BiConsumer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.LastHttpContent;

public class BiConsumerTest implements  BiConsumer<Void, Throwable>{
	private Channel channel;
	
	public BiConsumerTest(Channel channel){
		this.channel=channel;
	}
	@Override
	public void accept(Void t, Throwable u) {
		   ChannelHandlerContext ctx =channel.pipeline().context("reactiveBridge");
	       ctx.write(LastHttpContent.EMPTY_LAST_CONTENT);
	       ctx.deregister();
	       ctx.disconnect().addListener(ChannelFutureListener.CLOSE);
	}
}
