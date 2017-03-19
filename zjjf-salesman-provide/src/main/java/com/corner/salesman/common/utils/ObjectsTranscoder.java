package com.corner.salesman.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectsTranscoder {
	private static Logger logger = LoggerFactory.getLogger(ListTranscoder.class);  
	
	public static byte[] serialize(List<Object> value) {  
        if (value == null) {  
            throw new NullPointerException("Can't serialize null");  
        }  
        byte[] rv=null;  
        ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;  
        try {  
            bos = new ByteArrayOutputStream();  
            os = new ObjectOutputStream(bos);  
            for(Object obj : value){  
                os.writeObject(obj);  
            }  
            os.writeObject(null);  
            os.close();  
            bos.close();  
            rv = bos.toByteArray();  
        } catch (IOException e) {  
            throw new IllegalArgumentException("Non-serializable object", e);  
        } finally {  
            close(os);  
            close(bos);  
        }  
        return rv;  
    }  

    public static List<Object> deserialize(byte[] in) {  
        List<Object> list = new ArrayList<Object>();  
        ByteArrayInputStream bis = null;  
        ObjectInputStream is = null;  
        try {  
            if(in != null) {  
                bis=new ByteArrayInputStream(in);  
                is=new ObjectInputStream(bis);  
                while (true) {  
                	Object obj = is.readObject();  
                    if(obj == null){  
                        break;  
                    }else{  
                        list.add(obj);  
                    }  
                }  
                is.close();  
                bis.close();  
            }  
        } catch (IOException e) {  
            logger.warn("Caught IOException decoding %d bytes of data",  
                    in == null ? 0 : in.length, e);  
        } catch (ClassNotFoundException e) {  
            logger.warn("Caught CNFE decoding %d bytes of data",  
                    in == null ? 0 : in.length, e);  
        } finally {  
            close(is);  
            close(bis);  
        }  
        return list;  
    }  
    
    public static void close(Closeable closeable) {  
        if (closeable != null) {  
            try {  
                closeable.close();  
            } catch (Exception e) {  
                logger.info("Unable to close %s", closeable, e);  
            }  
        }  
    } 
}
