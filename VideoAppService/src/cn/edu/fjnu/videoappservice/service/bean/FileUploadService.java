/**
 * 
 */
package cn.edu.fjnu.videoappservice.service.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.fjnu.videoappservice.data.Const;
import cn.edu.fjnu.videoappservice.domain.FileUpload;
import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.util.DBUtils;

/**
 * @author GaoFei
 * 对应文件表的增删该查
 */
public class FileUploadService implements BaseBenService<FileUpload> {

	@Override
	public void save(FileUpload object) {
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement("insert into file_upload(uid,type,url,file_name,file_size,lng,lat,address,create_time) values(?,?,?,?,?,?,?,?,?)");
			preparedStatement.setInt(1, object.getUid());
			preparedStatement.setInt(2, object.getType());
			preparedStatement.setString(3, object.getUrl());
			preparedStatement.setString(4, object.getFile_name());
			preparedStatement.setInt(5, object.getFile_size());
			preparedStatement.setDouble(6, object.getLng());
			preparedStatement.setDouble(7, object.getLat());
			preparedStatement.setString(8, object.getAddress());
			preparedStatement.setInt(9, object.getCreate_time());
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, null, connection);
		
	
	}

	@Override
	public void delete(FileUpload object) {
		
	}

	@Override
	public void update(FileUpload object) {
		
	}

	@Override
	public List<FileUpload> getAll() {
		return null;
	}

	@Override
	public FileUpload getObjectById(Object id) {
		return null;
	}

	@Override
	public boolean isExist(FileUpload object) {
		return false;
	}

	@Override
	public void saveAll(List<FileUpload> objects) {
		
	}

	@Override
	public void saveOrUpdateAll(List<FileUpload> objects) {
		
	}

	/**
	 * 根据时间获取用户上传的图片
	 * @param time
	 * @return
	 */
	public List<FileUpload> getFileUploadPhotosByTime(int time){
		List<FileUpload> fileUploads = new ArrayList<>();
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from file_upload where create_time >= ? and create_time < ? and type = ? order by create_time desc");
			preparedStatement.setInt(1, time);
			preparedStatement.setInt(2, time + Const.ONEDAY_SECOND);
			preparedStatement.setInt(3, Const.FileType.PHOTO);
			resultSet = preparedStatement.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		FileUpload fileUpload = null;
		try{
			while(resultSet.next()){
				fileUpload = new FileUpload();
				fileUpload.setId(resultSet.getInt(1));
				fileUpload.setUid(resultSet.getInt(2));
				fileUpload.setType(resultSet.getInt(3));
				fileUpload.setUrl(resultSet.getString(4));
				fileUpload.setFile_name(resultSet.getString(5));
				fileUpload.setFile_size(resultSet.getInt(6));
				fileUpload.setLng(resultSet.getDouble(7));
				fileUpload.setLat(resultSet.getDouble(8));
				fileUpload.setAddress(resultSet.getString(9));
				fileUpload.setCreate_time(resultSet.getInt(10));
				fileUploads.add(fileUpload);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, resultSet, connection);
		return fileUploads;
	}
}
