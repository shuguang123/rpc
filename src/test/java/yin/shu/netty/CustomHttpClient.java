package yin.shu.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.StandardSocketOptions;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * SocketChannel 异步访问web资源
 *
 * @author
 * @create 2018-01-27 22:47
 **/
public class CustomHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(CustomHttpClient.class);

    private final ByteArrayOutputStream baHtmlPage = new ByteArrayOutputStream();

    private final ByteBuffer buffer = ByteBuffer.allocate(128 * 1024);

    private String address = "http://192.168.1.1";

    private String htmlPage = null;

    //调用方法
    public void startCient(){

        Selector selector =null;

        SocketChannel socketChannel =null;
        try {

            selector = Selector.open();
            socketChannel = SocketChannel.open();

            if(selector.isOpen() && socketChannel.isOpen()){
                //设置非阻塞
                socketChannel.configureBlocking(false);
                socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
                socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
                socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);

                socketChannel.connect(createInetSocketAddress(address));

                // register the current channel with the given selector
                socketChannel.register(selector, SelectionKey.OP_CONNECT);

                while(true){
                    // wait for incomming events
                    int num = selector.selectNow();

                    if (num==0) {
                        //Thread.yield();
                        Thread.sleep(2000);
                        System.out.println("sleep: 2 sec");
                        continue;
                    }

                    // there is something to process on selected keys
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                    while(keys.hasNext()){
                        SelectionKey key = (SelectionKey) keys.next();

                        // prevent the same key from coming up again
                        keys.remove();

                        if (!key.isValid()) {
                            continue;
                        }

                        if (key.isConnectable() && socketChannel.finishConnect()) {
                            System.out.println("Key: OP_CONNECT");
                            // reset the byte-array
                            baHtmlPage.reset();

                            // Connected --> Send the HTTP request
                            key.interestOps(SelectionKey.OP_WRITE);
                        } else if (key.isReadable()) {
                            System.out.println("Key: OP_READ");
                            if (readResponse(key)) {
                                logger.info("finished reading, htmlpage:{}"+ htmlPage);
                            } else {
                                key.interestOps(SelectionKey.OP_READ);
                            }
                            // Once read is done --> we are done
                            //key.interestOps(SelectionKey.OP_WRITE);
                        } else if (key.isWritable()) {
                            System.out.println("Key: OP_WRITE");
                            if (writeHttpRequest(key)) {
                                // HTTP request is sent --> Get the response
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                }
            }else {
                System.out.println("The server socket channel or selector cannot be opened!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socketChannel.close();
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public InetSocketAddress createInetSocketAddress(String address) throws MalformedURLException {
        URL url = new URL(address);
        String host = url.getHost();

        System.out.println(host);
        int port = url.getPort();
        if (port == -1){
            port = 80;
        }
        return new InetSocketAddress(host, port);
    }

    private boolean readResponse(SelectionKey key) throws IOException {
        boolean done = false;
        SocketChannel socketChannel = (SocketChannel) key.channel();

        int numRead = -1;
        do {
            buffer.clear();
            numRead = socketChannel.read(buffer);

            baHtmlPage.write(buffer.array(), 0, numRead);
            System.out.println("Server sent:" + new String(buffer.array(), 0, numRead, "UTF-8") );
        } while(numRead>0);
        if (numRead == -1) {
            System.out.println("Connection closed by: " + socketChannel.getRemoteAddress());
            key.cancel();
            socketChannel.close();
            htmlPage = baHtmlPage.toString("UTF-8");
            done = true;
        }
        return done;
    }

    private boolean writeHttpRequest(SelectionKey key) throws IOException {
        boolean done = false;

        SocketChannel socketChannel = (SocketChannel) key.channel();
        String request =
                "GET / HTTP/1.1\r\n" +
                        "Host: www.baidu.com\r\n" +
                        "Cache-Control: no-cache\r\n\r\n";

        // UTF-8
        ByteBuffer randomBuffer = ByteBuffer.wrap(request.getBytes("UTF-8"));
        int rem = randomBuffer.remaining();
        int num = socketChannel.write(randomBuffer);

        if (rem==num) {
            done = true;
            System.out.printf("Request written:%s\n", request);
        }
        return done;
    }

    public static void main(String[] args) {
        new CustomHttpClient().startCient();
    }
}
