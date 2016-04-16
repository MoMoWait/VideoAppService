package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import cn.edu.fjnu.videoappservice.domain.OnlineUser;
import cn.edu.fjnu.videoappservice.service.bean.OnlineUserService;
import cn.edu.fjnu.videoappservice.util.CommonUtils;

/**
 * 获取所有在线用户服务
 * @author GaoFei
 *
 */
public class GetAllOnlineUsers extends HttpServlet {

	
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
		//此处写入json数据
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		//用于判断的对象
		OnlineUserService onlineUserService = new OnlineUserService();
		try{
			List<OnlineUser> resultUsers = onlineUserService.getAll();
			if(resultUsers == null || resultUsers.size() == 0){
				headObject.put("error", "暂无在线用户");
			}else{
				contentObject.put("online_users", CommonUtils.listToJsonArray(resultUsers));
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
