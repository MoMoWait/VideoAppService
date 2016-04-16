package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.edu.fjnu.videoappservice.data.Const;
import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.service.bean.UserService;
/**
 * �û�ע�����
 * @author GaoFei
 *
 */
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String rqUserName = request.getParameter("user_name");
		String rqPassword = request.getParameter("password");
		String rqMail = request.getParameter("mail");
		String rqNickName = request.getParameter("nick_name");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//�˴�д��json����
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		UserService userService = new UserService();
		//�����жϵĶ���
		User registerUser = userService.getObjectByUserName(rqUserName);
		//���������Ψһ
		User mailUser = userService.getObjectByMail(rqMail);
		try {
			if(registerUser == null && mailUser == null){
				User user = new User();
				user.setMail(rqMail);
				user.setNick_name(rqNickName);
				user.setPassword(rqPassword);
				user.setType(Const.UserType.NORMAL);
				user.setUser_name(rqUserName);
				new UserService().save(user);
				headObject.put("succ", contentObject);
			}else if(registerUser != null){
				headObject.put("error", "�û���" + rqUserName + "�Ѿ�����ע����");
			}else if(mailUser != null){
				headObject.put("error", "�����" + rqMail +"�Ѿ�����ע����");
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
