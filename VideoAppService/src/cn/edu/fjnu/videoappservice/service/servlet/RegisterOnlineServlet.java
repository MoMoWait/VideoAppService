package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.edu.fjnu.videoappservice.data.Const;
import cn.edu.fjnu.videoappservice.domain.OnlineUser;
import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.service.bean.OnlineUserService;
import cn.edu.fjnu.videoappservice.service.bean.PushMessage;
import cn.edu.fjnu.videoappservice.service.bean.UserService;
/**
 * 注册在线服务
 * @author GaoFei
 *
 */
public class RegisterOnlineServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int rqUserId = Integer.parseInt(request.getParameter("user_id"));
		String rqAddress = request.getParameter("address");
		double rqLng = Double.parseDouble(request.getParameter("lng"));
		double rqLat = Double.parseDouble(request.getParameter("lat"));
		//int rqTime = Integer.parseInt(request.getParameter("time"));
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//此处写入json数据
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		OnlineUserService onlineUserService = new OnlineUserService();
		//封装在线用户对象
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setUser_id(rqUserId);
		onlineUser.setAddress(rqAddress);
		onlineUser.setLat(rqLat);
		onlineUser.setLng(rqLng);
		onlineUser.setTime((int)(System.currentTimeMillis() /  1000));
		try {
			if(!onlineUserService.isExist(onlineUser)){
				onlineUserService.save(onlineUser);
			}else{
				onlineUserService.update(onlineUser);
			}
			headObject.put("succ", contentObject);
			resultObject.put("result", headObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//发送用户注册在线推送消息
		PushMessage.sendMessage(Const.PushMessage.USER_RQ_ONLINE);
		out.print(resultObject.toString());
		out.flush();
		out.close();
	
	}

}
