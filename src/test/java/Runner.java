import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * TestRunner
 *
 * @author
 * @create 2018-01-27 19:57
 **/
public class Runner {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    /**
     * 从.txt中读取文件
     *
     */
    @Test
    public void testReadFromFile(){
        RandomAccessFile  aFile = null;
        try {
           aFile  = new RandomAccessFile("G:/nio-data.txt","rw");

           FileChannel inChannel = aFile.getChannel();

           ByteBuffer buffer = ByteBuffer.allocate(80);

           int byteRead = inChannel.read(buffer);

           byte[] arr = new byte[1024];

           int i = 0;

           //读数据
           while(byteRead != -1){
               buffer.flip();

               while(buffer.hasRemaining()){
                   arr[i] = buffer.get();
                   i++;
               }

               buffer.clear();
               byteRead = inChannel.read(buffer);
           }

            byte[] arrToPrint = Arrays.copyOf(arr,i);

            String str = new String(arrToPrint,"gbk");

            System.out.println(str);

            inChannel.close();

            System.out.print("写入数据 完成");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                aFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 向.txt中写文件
     *
     */
    @Test
    public void testWriteToFile(){

        RandomAccessFile  aFile = null;

        // \r\n是换行
        String str = "\r\n准备写入 文档中的数据。。。。。";


        try {
            aFile  = new RandomAccessFile("G:/nio-data.txt","rw");

            FileChannel channel = aFile.getChannel();

            /*byte[] byteToWrite = str.getBytes("GBK");

            ByteBuffer buffer = ByteBuffer.allocate(byteToWrite.length);

            buffer.put(byteToWrite);

            //翻转，然指针回到初始位置
            //limit = position;
            //position = 0;
            //mark = -1;
            buffer.flip();*/

            //将channel指针移动到文件最后
            channel.position(channel.size());
            channel.write(ByteBuffer.wrap(str.getBytes("GBK")));

            channel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {

                aFile.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public InetSocketAddress createInetSocketAddress(String address) throws MalformedURLException {
        String urlStr = address;
        URL url = new URL(urlStr);
        String host = url.getHost();
        int port = url.getPort();
        if (port == -1){
            port = 80;
        }
        return new InetSocketAddress(host, port);
    }


    @Test
    public void testSocketChannnel(){

        SocketChannel socketChannel =null;

        String address = "http://www.baidu.com";

        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(createInetSocketAddress(address));

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int byteRead = socketChannel.read(buffer);

            while(byteRead != -1){

                buffer.flip();

                if(buffer.hasRemaining()){
                    System.out.print((char)buffer.get());
                }

                buffer.clear();

                byteRead = socketChannel.read(buffer);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {

                socketChannel.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public  void testIpAddress(){
        new InetSocketAddress("192.168.1.1",80);

        String host = "http://192.168.1.1";

        Character.digit(host.charAt(0), 16);
    }
}
