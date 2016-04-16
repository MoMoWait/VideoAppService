/**
 * 
 */
package cn.edu.fjnu.videoappservice.data;

/**
 * @author ��������
 *
 */
public class Const {
	
	public static final String SERVER_BASIC = "http://120.24.210.186:8080/VideoAppService/";
	//һ�������
	public static final int ONEDAY_SECOND = 24*60*60;
	public interface FileType{
		//ͼƬ
		public int PHOTO = 1;
		//��Ƶ
		public int VIDEO = 2;
	}
	
	/**
	 * �û�����
	 * @author GaoFei
	 *
	 */
	public interface UserType{
		//��ͨ�û�
		public int NORMAL = 1;
		//����Ա
		public int MANAGER = 2;
	}
	
    /**
     * ������Ϣ
     */
    public interface PushMessage{
        /**ͼƬ�ϴ��ɹ�*/
        String PHOTO_UPLOAD = "photo_upload";
        /**�û���������*/
        String USER_RQ_ONLINE = "user_request_online";
        /**�û���������*/
        String USER_RQ_OFFLINE = "user_request_offline";
    }
}
