/**
 * 
 */
package cn.edu.fjnu.videoappservice.util;

import java.lang.reflect.Field;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * ͨ�ù�����
 *
 */
public class CommonUtils {
	/**
	 * ����������ת����json����
	 * @param objects
	 * @return
	 */
	public static JSONArray listToJsonArray(List<?> objects){
		JSONArray arrays = new JSONArray();
		for(Object object : objects){
			JSONObject jsonObject = new JSONObject();
			Class<?> objectClass = object.getClass();
			Field[] fields = objectClass.getDeclaredFields();
			for(Field field:fields){
				try{
					field.setAccessible(true);
					jsonObject.put(field.getName(), field.get(object));
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			arrays.put(jsonObject);
		}
		return arrays;
	}
	
	/**
	 * ������ת��ΪObject
	 * @param object
	 * @return
	 */
	public static JSONObject objectToJson(Object object){
		JSONObject jsonObject = new JSONObject();
		Class<?> objectClass = object.getClass();
		Field[] fields = objectClass.getDeclaredFields();
		for(Field field:fields){
			try{
				field.setAccessible(true);
				jsonObject.put(field.getName(), field.get(object));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return jsonObject;
	}
}
