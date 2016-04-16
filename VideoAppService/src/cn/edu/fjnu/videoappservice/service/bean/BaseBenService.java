package cn.edu.fjnu.videoappservice.service.bean;

import java.util.List;

/**
 * ������Bean����
 * @author GaoFei
 * @param <T>
 */
public interface BaseBenService<T> {
	//����
    void save(T object);
    //ɾ��
    void delete(T object);
    //����
    void update(T object);
    //��ȡ��������
    List<T> getAll();
    //ͨ��ID��ȡ��¼
    T getObjectById(Object id);
    //�ж�ĳ�������Ƿ����
    boolean isExist(T object);
    //����ĳ���б�
    void saveAll(List<T> objects);
    //��������ĳ���б�
    void saveOrUpdateAll(List<T> objects);
}
