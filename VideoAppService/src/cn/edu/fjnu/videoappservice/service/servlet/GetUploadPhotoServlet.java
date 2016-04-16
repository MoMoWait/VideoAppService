package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import cn.edu.fjnu.videoappservice.domain.FileUpload;
import cn.edu.fjnu.videoappservice.service.bean.FileUploadService;
import cn.edu.fjnu.videoappservice.util.CommonUtils;
/**
 * 获取上传的图片
 * @author GaoFei
 *
 */
public class GetUploadPhotoServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		int rqTime = Integer.parseInt(request.getParameter("create_time"));
		PrintWriter out = response.getWriter();
		//此处写入json数据
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		FileUploadService fileUploadService = new  FileUploadService();
		List<FileUpload> files = fileUploadService.getFileUploadPhotosByTime(rqTime);
		try{
			if(files == null || files.size() == 0){
				headObject.put("error", "暂无图片");
			}else{
				contentObject.put("photos", CommonUtils.listToJsonArray(files));
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
