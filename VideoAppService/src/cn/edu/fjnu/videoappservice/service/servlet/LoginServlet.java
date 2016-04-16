package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.service.bean.UserService;
import cn.edu.fjnu.videoappservice.util.CommonUtils;

public class LoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String rqUserName = request.getParameter("user_name");
		String rqPassword = request.getParameter("password");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//�˴�д��json����
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		//�����жϵĶ���
		User user = new User();
		user.setUser_name(rqUserName);
		user.setPassword(rqPassword);
		UserService userService = new UserService();
		boolean isRight = userService.isExist(user);
		try {
			if(isRight){
				contentObject.put("user", CommonUtils.objectToJson(userService.getObjectByUserName(rqUserName)));
			//	contentObject.put("type", new UserService().getObjectByUserName(rqUserName).getType());
				headObject.put("succ", contentObject);
			}else{
				headObject.put("error", "�û������������");
			}
			resultObject.put("result", headObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(resultObject.toString());
		out.flush();
		out.close();
	}
		
	
}
