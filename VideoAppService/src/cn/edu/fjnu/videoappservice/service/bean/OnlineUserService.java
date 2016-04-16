package cn.edu.fjnu.videoappservice.service.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.fjnu.videoappservice.domain.OnlineUser;
import cn.edu.fjnu.videoappservice.util.DBUtils;

public class OnlineUserService implements BaseBenService<OnlineUser> {

	@Override
	public void save(OnlineUser object) {
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("insert into online_user(user_id,address,lng,lat,time) values(?,?,?,?,?)");
			preparedStatement.setInt(1, object.getUser_id());
			preparedStatement.setString(2, object.getAddress());
			preparedStatement.setDouble(3, object.getLng());
			preparedStatement.setDouble(4, object.getLat());
			preparedStatement.setInt(5, object.getTime());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBUtils.closeConn(preparedStatement, null, connection);
	}

	@Override
	public void delete(OnlineUser object) {
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("delete from online_user where user_id = ?");
			preparedStatement.setInt(1, object.getUser_id());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBUtils.closeConn(preparedStatement, null, connection);
	}

	@Override
	public void update(OnlineUser object) {
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("update online_user(address,lng,lat,time) values(?,?,?,?) where user_id  = ?");
			preparedStatement.setString(1, object.getAddress());
			preparedStatement.setDouble(2, object.getLng());
			preparedStatement.setDouble(3, object.getLng());
			preparedStatement.setInt(4, object.getTime());
			preparedStatement.setInt(5, object.getUser_id());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, null, connection);
	}

	@Override
	public List<OnlineUser> getAll() {
		List<OnlineUser> users = new ArrayList<>();
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection
					.prepareStatement("select * from online_user");
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		OnlineUser onlineUser = null;
		try {
			while (resultSet.next()) {
				onlineUser = new OnlineUser();
				onlineUser.setId(resultSet.getInt(1));
				onlineUser.setUser_id(resultSet.getInt(2));
				onlineUser.setAddress(resultSet.getString(3));
				onlineUser.setLng(resultSet.getDouble(4));
				onlineUser.setLat(resultSet.getDouble(5));
				onlineUser.setTime(resultSet.getInt(6));
				users.add(onlineUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBUtils.closeConn(preparedStatement, resultSet, connection);

		return users;
	}

	@Override
	public OnlineUser getObjectById(Object id) {
		return null;
	}

	@Override
	public boolean isExist(OnlineUser object) {
		// 这里只是根据用户ID判断
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection
					.prepareStatement("select * from online_user where user_id = ?");
			preparedStatement.setInt(1, object.getUser_id());
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean result = false;
		try {
			result = resultSet.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtils.closeConn(preparedStatement, resultSet, connection);
		return result;
	}

	@Override
	public void saveAll(List<OnlineUser> objects) {

	}

	@Override
	public void saveOrUpdateAll(List<OnlineUser> objects) {

	}

}
