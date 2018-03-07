package yin.shu.netty.decoder;

import yin.shu.netty.serialize.User;

import java.io.*;

public class ByteObjConveter{

    /**
     * 将对象转换成 byte
     *
     * @param user
     * @return
     */
    public static byte[] objectToByte(User user){
        byte[] bytes =null;

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ObjectOutputStream op = null;

        try {

            op = new ObjectOutputStream(out);

            op.writeObject(user);

            bytes = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                op.close();

                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }


    public  static Object  byteToObject(byte[] bytes){
        Object obj = null;
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = null;

        try {
            oi = new ObjectInputStream(bi);

            obj = oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                oi.close();

                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

}
