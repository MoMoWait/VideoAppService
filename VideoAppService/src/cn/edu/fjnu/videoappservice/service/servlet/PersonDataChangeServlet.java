package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONUtil;

import org.json.JSONObject;

import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.service.bean.UserService;
import cn.edu.fjnu.videoappservice.util.CommonUtils;

/**
 * ���������޸ķ���
 * @author GaoFei
 *
 */
public class PersonDataChangeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int rqUid = Integer.parseInt(request.getParameter("id"));
		String rqKey = request.getParameter("key");
		String rqValue = request.getParameter("value");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//�˴�д��json����
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		//�����жϵĶ���
		UserService userService = new UserService();
		try {
			if(rqKey.equals("user_name") && userService.getObjectByUserName(rqValue) != null){
				headObject.put("error", "�û���" + rqValue +"�Ѿ�����ע���");
			}else if(rqKey.equals("mail") && userService.getObjectByUserName(rqValue) != null){
				headObject.put("error", "����" + rqValue +"�Ѿ�����ע���");
			}else{
				userService.changePersonData(rqUid, rqKey, rqValue);
				User user = userService.getObjectById(rqUid);
				contentObject.put("user", CommonUtils.objectToJson(user));
				headObject.put("succ", contentObject);
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
