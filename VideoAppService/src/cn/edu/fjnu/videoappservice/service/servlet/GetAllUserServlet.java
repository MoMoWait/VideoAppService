package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.service.bean.UserService;
import cn.edu.fjnu.videoappservice.util.CommonUtils;

/**
 * ��ȡ�����û�
 * @author GaoFei
 *
 */
public class GetAllUserServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//�˴�д��json����
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		//�����жϵĶ���
		UserService userService = new UserService();
		try{
			List<User> resultUsers = userService.getAll();
			if(resultUsers == null || resultUsers.size() == 0){
				headObject.put("error", "�û���ȡʧ��");
			}else{
				contentObject.put("users", CommonUtils.listToJsonArray(resultUsers));
				headObject.put("succ", contentObject);
			}
			resultObject.put("result", headObject);
		}catch (Exception e){
			e.printStackTrace();
		}
		out.print(resultObject.toString());
		out.flush();
		out.close();
	}

}
