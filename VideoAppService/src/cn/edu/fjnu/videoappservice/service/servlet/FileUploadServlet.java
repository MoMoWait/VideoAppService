package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import cn.edu.fjnu.videoappservice.data.Const;
import cn.edu.fjnu.videoappservice.domain.FileUpload;
import cn.edu.fjnu.videoappservice.service.bean.FileUploadService;
import cn.edu.fjnu.videoappservice.service.bean.PushMessage;

/**
 * 文件上传服务
 * @author GaoFei
 * 
 */
public class FileUploadServlet extends HttpServlet {

	private int uid;
	private int type;
	private String url;
	private int file_size;
	private String file_name;
	private double lng;
	private double lat;
	private String address;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			String photoPath = req.getSession().getServletContext().getRealPath("/Photos");
			String videoPath = req.getSession().getServletContext().getRealPath("/videos");
			File photoDir = new File(photoPath);
			if (!photoDir.exists())
				photoDir.mkdirs();
			File videoDir = new File(videoPath);
			if(!videoDir.exists())
				videoDir.mkdirs();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			try {
				List<FileItem> items = upload.parseRequest(req);
				for (FileItem item : items) {
					if (item.isFormField()) {
						String filedName = item.getFieldName();
						if(filedName.equals("uid")){
							uid=Integer.parseInt(item.getString());
						}else if(filedName.equals("type")){
							type = Integer.parseInt(item.getString());
						}else if(filedName.equals("file_size")){
							file_size = Integer.parseInt(item.getString());
						}else if(filedName.equals("lng")){
							lng = Double.parseDouble(item.getString());
						}else if(filedName.equals("lat")){
							lat = Double.parseDouble(item.getString());
						}else if(filedName.equals("address")){
							address = item.getString("UTF-8");
							//address = new String(address.getBytes(), Charset.forName("UTF-8"));
							System.out.println(address);
						}
							
					} else {
						file_name = UUID.randomUUID().toString() + item.getName().substring(
								item.getName().lastIndexOf("."));
						if(type == Const.FileType.PHOTO){
							url = Const.SERVER_BASIC + "Photos/" + file_name;
							item.write(new File(photoDir, file_name));
						}else{
							url = Const.SERVER_BASIC + "Videos/" + file_name;
							item.write(new File(videoDir, file_name));
						}
						
					}
				}
				//存储至数据库中
				FileUploadService uploadService = new FileUploadService();
				FileUpload fileUpload = new FileUpload();
				fileUpload.setFile_name(file_name);
				fileUpload.setFile_size(file_size);
				fileUpload.setType(type);
				fileUpload.setUid(uid);
				fileUpload.setUrl(url);
				fileUpload.setLng(lng);
				fileUpload.setLat(lat);
				fileUpload.setAddress(address);
				fileUpload.setCreate_time((int)(System.currentTimeMillis() / 1000));
				uploadService.save(fileUpload);
				JSONObject resultObject = new JSONObject();
				JSONObject succObject = new JSONObject();
				JSONObject contentObject = new JSONObject();
				contentObject.put("uploadResult", "1");
				succObject.put("succ", contentObject);
				resultObject.put("result", succObject);
				PrintWriter writer = resp.getWriter();
				writer.write(resultObject.toString());
				writer.flush();
				writer.close();
				//发送图片成功上传的消息
				PushMessage.sendMessage(Const.PushMessage.PHOTO_UPLOAD);
			} catch (Exception e) {
				e.printStackTrace();
				PrintWriter writer = resp.getWriter();
				writer.write("{'result':{'error':'服务器内部错误'}}");
				writer.flush();
				writer.close();
			}

		} else {
			doGet(req, resp);
		}
	}

}
