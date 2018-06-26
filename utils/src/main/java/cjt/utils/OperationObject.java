package cjt.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class OperationObject {
    public interface Visitor{
        void visit(Object object,Object indexOrKey,Object value);
    };

    public static void doOperation(Object obj,Visitor visitor){
        if(obj instanceof Collection){
            Iterator iterator = ((Collection)obj).iterator();
            int i = 0;
            while (iterator.hasNext()){
                Object collObj = iterator.next();
                if(collObj != null && (collObj instanceof Integer || collObj instanceof Byte ||
                        collObj instanceof Short || collObj instanceof Long || collObj instanceof Float||
                        collObj instanceof Double || collObj instanceof Boolean || collObj instanceof Character ||
                        collObj instanceof String)){
                    visitor.visit(obj,i,collObj);
                }else {
                    doOperation(collObj,visitor);
                }
                i++;
            }
        }else if(obj instanceof Map){
            for(Map.Entry<String,Object> entry:((Map<String,Object>)obj).entrySet()){
                if(entry.getKey() != null && !entry.getKey().equals("class"))  {
                    if(entry.getValue() != null && (entry.getValue() instanceof Integer || entry.getValue() instanceof Byte ||
                            entry.getValue() instanceof Short ||entry.getValue() instanceof Long || entry.getValue() instanceof Float||
                            entry.getValue() instanceof Double || entry.getValue() instanceof Boolean ||entry.getValue() instanceof Character ||
                            entry.getValue() instanceof String)){
                        visitor.visit(obj,entry.getKey(),entry.getValue());
                    }else {
                        doOperation(entry.getValue(),visitor);
                    }
                }
            }
        }else if(obj !=null && obj.getClass().isArray()){
            if((obj instanceof int[])){
                for(int i = 0;i<((int[]) obj).length;i++){
                    visitor.visit(obj,i,((int[]) obj)[i]);
                }
            }else if((obj instanceof byte[])){
                for(int i = 0;i<((byte[]) obj).length;i++){
                    visitor.visit(obj,i,((byte[]) obj)[i]);
                }
            }else if((obj instanceof short[])){
                for(int i = 0;i<((short[]) obj).length;i++){
                    visitor.visit(obj,i,((short[]) obj)[i]);
                }
            }else if((obj instanceof long[])){
                for(int i = 0;i<((long[]) obj).length;i++){
                    visitor.visit(obj,i,((long[]) obj)[i]);
                }

            }else if((obj instanceof float[])){
                for(int i = 0;i<((float[]) obj).length;i++){
                    visitor.visit(obj,i,((float[]) obj)[i]);
                }
            }else if((obj instanceof double[])){
                for(int i = 0;i<((double[]) obj).length;i++){
                    visitor.visit(obj,i,((double[]) obj)[i]);
                }
            }else if((obj instanceof boolean[])){
                for(int i = 0;i<((boolean[]) obj).length;i++){
                    visitor.visit(obj,i,((boolean[]) obj)[i]);
                }
            }else if((obj instanceof char[])){
                for(int i = 0;i<((char[]) obj).length;i++){
                    visitor.visit(obj,i,((char[]) obj)[i]);
                }
            }else if((obj instanceof String[])){
                for(int i = 0;i<((String[]) obj).length;i++){
                    visitor.visit(obj,i,((String[]) obj)[i]);
                }
            }else {
                for(Object obj1:(Object[])obj){
                    doOperation(obj1,visitor);
                }
            }
        }else if(obj!=null &&
                (!(obj instanceof Integer) && !(obj instanceof Byte) &&
                        !(obj instanceof Short) && !(obj instanceof Long) &&
                        !(obj instanceof  Float) && !(obj instanceof Double) &&
                        !(obj instanceof  Boolean) && !(obj instanceof  Character) &&
                        !(obj instanceof  String)) && obj instanceof Object){
            try {
                Map<String,Object> map = PropertyUtils.describe(obj);
                for(Map.Entry<String,Object> entry:map.entrySet()){
                    if(entry.getKey() != null && !entry.getKey().equals("class"))  {
                        if(entry.getValue() != null && (entry.getValue() instanceof Integer || entry.getValue() instanceof Byte ||
                                entry.getValue() instanceof Short ||entry.getValue() instanceof Long || entry.getValue() instanceof Float||
                                entry.getValue() instanceof Double || entry.getValue() instanceof Boolean ||entry.getValue() instanceof Character ||
                                entry.getValue() instanceof String)){
                            visitor.visit(obj,entry.getKey(),entry.getValue());
                        }else {
                            doOperation(entry.getValue(),visitor);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setValue(Object object,Object indexOrKey, Object value){
        if(object instanceof Collection){
            Object[] objArr = ((Collection)object).toArray();
            for(int i = 0;i<objArr.length; i++){
                if(i == Integer.parseInt(indexOrKey.toString())){
                    objArr[i] = value;
                }
            }
            ((Collection)object).clear();
            CollectionUtils.addAll(((Collection)object),objArr);
        } else if(object instanceof Map){
            if(((Map)object).containsKey(indexOrKey)) ((Map)object).put(indexOrKey,value);
        } else if(object !=null && object.getClass().isArray()){
            if((object instanceof int[])){
                ((int[]) object)[Integer.parseInt(indexOrKey.toString())] = Integer.parseInt(value.toString());
            }else if((object instanceof byte[])){
                ((byte[]) object)[Integer.parseInt(indexOrKey.toString())] = Byte.parseByte(value.toString());
            }else if((object instanceof short[])){
                ((short[]) object)[Integer.parseInt(indexOrKey.toString())] = Short.parseShort(value.toString());
            }else if((object instanceof long[])){
                ((long[]) object)[Integer.parseInt(indexOrKey.toString())] = Long.parseLong(value.toString());
            }else if((object instanceof float[])){
                ((float[]) object)[Integer.parseInt(indexOrKey.toString())] = Float.parseFloat(value.toString());
            }else if((object instanceof double[])){
                ((double[]) object)[Integer.parseInt(indexOrKey.toString())] = Double.parseDouble(value.toString());
            }else if((object instanceof boolean[])){
                ((boolean[]) object)[Integer.parseInt(indexOrKey.toString())] = Boolean.parseBoolean(value.toString());
            }else if((object instanceof char[])){
                ((char[]) object)[Integer.parseInt(indexOrKey.toString())] = value.toString().charAt(0);
            }else if((object instanceof String[])){
                ((String[]) object)[Integer.parseInt(indexOrKey.toString())] = value.toString();
            }
        }else if(object!=null &&
                (!(object instanceof Integer) && !(object instanceof Byte) &&
                        !(object instanceof Short) && !(object instanceof Long) &&
                        !(object instanceof  Float) && !(object instanceof Double) &&
                        !(object instanceof  Boolean) && !(object instanceof  Character) &&
                        !(object instanceof  String)) && object instanceof Object){
            try {
                BeanUtils.setProperty(object,indexOrKey.toString(),value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
