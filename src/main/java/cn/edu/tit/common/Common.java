/**
 * 存放通用工具
 */
package cn.edu.tit.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Component
public  class  Common {
	// 使用日志工厂获取日志对象
    private static Log log = LogFactory.getLog(Common.class);
	/**
	 * 创建随机串
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 把map转换成对象
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 * 
	 * 把Map转换成指定类型
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T toBean(Map map, Class<T> clazz) {
		try {
			/*
			 * 1. 通过参数clazz创建实例 2. 使用BeanUtils.populate把map的数据封闭到bean中
			 */
			T bean = clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	 
	/**
	 * springmvc的MultipartFile进行文件上传
	 * @param file
	 * @param request
	 */
	public static void springFileUpload(HttpServletRequest request){
		
        // 转换request，解析出request中的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取文件map集合
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName = null;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
        	if (entity!=null) {// 判断上传的文件是否为空
        		// 获取单个文件
	            MultipartFile mf = entity.getValue();
	            // 获得原始文件名
	            fileName = mf.getOriginalFilename();
                String path=null;// 文件路径
                String type=null;// 文件类型
              //  String fileName=file.getOriginalFilename();// 文件原名称
                //System.out.println("上传的文件原名称:"+fileName);
                // 判断文件类型
                type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
                if (type!=null) {// 判断文件类型是否为空
//                        // 项目在容器中实际发布运行的根路径
//                        String realPath=request.getSession().getServletContext().getRealPath("/");
//                        // 自定义的文件名称
//                        String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;
//                        // 设置存放图片文件的路径
//                        path=realPath+/*System.getProperty("file.separator")+*/trueFileName;
                    	path = readProperties("path")+"/"+fileName;
                    
//                        System.out.println("存放图片文件的路径:"+path);
                        // 转存文件到指定的路径
                        try {
    						mf.transferTo(new File(path));
    						
    					} catch (Exception e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    						System.out.println("上传失败");
    					} 
                        System.out.println("文件成功上传到指定目录下");
                }else {
                    System.out.println("文件类型为空");
                }
            }else {
                System.out.println("没有找到相对应的文件");
            }
        }
		
	}

	/**
	 * java文件传输工具
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] fileFactory(HttpServletRequest request, String id) {
		try {
			Map<String, Object> formdata = new HashMap<String, Object>(); // 要返回的map,存储的是要转换的类信息
			List<File> returnFileList = new ArrayList<>(); // 要返回的文件集合
			String path = readProperties("path");
			if(!"".equals(id) && id != null){
				path +="/"+id;
			}
			// 创建工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
			List<FileItem> items = upload.parseRequest(request);// 得到所有的字段
			for (FileItem fi : items) {
				if (!fi.isFormField()) { // 判断是否是普通表单字段
					String fileName="";
					if(fi.getName()!=null && !"".equals(fi.getName())){
						int count = fi.getName().lastIndexOf(".");
						 fileName = System.currentTimeMillis()+fi.getName().substring(count);
					}
					if (!fileName.isEmpty()) {
						File fullFile = new File(new String(fileName.getBytes(), "utf-8")); // 解决文件名乱码问题,获得文件内容
						File savedFile = new File(path, fullFile.getName()); // 为文件设置存储路径
						// 文件夹不存在时创建文件夹
						File fileParent = savedFile.getParentFile();  
						if(!fileParent.exists()){  
						    fileParent.mkdirs();  
						}  
						savedFile.createNewFile();
						
						fi.write(savedFile); // 存储文件
						returnFileList.add(savedFile); // 保存要返回的文件集合
					}
				} else {
					formdata.put(fi.getFieldName(), fi.getString("UTF-8")); // 普通表单字段封装在map里，通过它转换为所需的类
				}
			}
			Object[] obj = new Object[]{returnFileList,formdata};
			return obj;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 读取properties的字段
	 * @param readKey 字段名
	 * @return
	 */
	public static String readProperties(String readKey){
		try {
			Properties properties = new Properties();
		    // 使用ClassLoader加载properties配置文件生成对应的输入流
		    InputStream in = Common.class.getClassLoader().getResourceAsStream("common.properties");
		    // 使用properties对象加载输入流
		    properties.load(in);
		    //获取key对应的value值
		    return properties.getProperty(readKey);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @author wenli
	 * @return
	 * 时间戳转成string 类型
	 * yyyy/MM/dd
	 */
	public static String TimestamptoString() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());  
        String tsStr = "";  
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        try {  
            tsStr = sdf.format(timestamp);  
            System.out.println(tsStr);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		return tsStr;
	}
	/**
	 * @author wenli
	 * @param info
	 * @return
	 * @throws NoSuchAlgorithmException
	 * 密码加密，单向加密MD5算法
	 */
	public static String eccryptMD5(String info) throws NoSuchAlgorithmException{
		//根据MD5算法生成MessageDigest对象
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] srcBytes = info.getBytes();
		//使用srcBytes更新摘要
		md5.update(srcBytes);
		//完成哈希计算，得到result
		byte[] resultBytes = md5.digest();
		StringBuffer stringBuffer =new StringBuffer();
		for (int i = 0; i < resultBytes.length; i++) {
			stringBuffer.append(resultBytes[i]);
		}
		
		return eccryptSHA(stringBuffer.toString());
	}
	/**
	 * @author wenli
	 * @param info
	 * @return
	 * @throws NoSuchAlgorithmException
	 * sha加密算法
	 */
	public static String eccryptSHA(String info) throws NoSuchAlgorithmException{
		MessageDigest sha = MessageDigest.getInstance("SHA");
		byte[] srcBytes = info.getBytes();
		//使用srcBytes更新摘要
		sha.update(srcBytes);
		//完成哈希计算，得到result
		byte[] resultBytes = sha.digest();
		StringBuffer stringBuffer =new StringBuffer();
		for (int i = 0; i < resultBytes.length; i++) {
			stringBuffer.append(resultBytes[i]);
		}
		
		return stringBuffer.toString();
	}
	
	
	/**
	 * 写一个共通的post提交方法
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
		public static String httpsRequest(String requestUrl, String requestMethod, String outputStr){
			try {
				URL url = new URL(requestUrl);
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				// 设置请求方式（GET/POST）
				conn.setRequestMethod(requestMethod);
				conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
				// 当outputStr不为null时向输出流写数据
				if (null != outputStr) {
					OutputStream outputStream = conn.getOutputStream();
					// 注意编码格式
						outputStream.write(outputStr.getBytes("UTF-8"));
						outputStream.close();
					}
					// 从输入流读取返回内容
					InputStream inputStream = conn.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					String str = null;
					StringBuffer buffer = new StringBuffer();
					while ((str = bufferedReader.readLine()) != null) {
						buffer.append(str);
					}
					// 释放资源
					bufferedReader.close();
					inputStreamReader.close();
					inputStream.close();
					inputStream = null;
					conn.disconnect();
					return buffer.toString();
					} catch (ConnectException ce) {
						System.out.println("连接超时：{}");
					} catch (Exception e) {
						System.out.println("https请求异常：{}");
					}
					return null;
				}
		
		
		//json转map,这个小工具是我从网上找的,谢谢作者
		public static Map<String, Object> parseJSON2Map(JSONObject json) {
		        Map<String, Object> map = new HashMap<String, Object>();
		        // 最外层解析
		        for (String k : getAllKeys(json)) {
		            Object v = json.get(k);
		            // 如果内层还是数组的话，继续解析
		            if (v instanceof JSONArray) {
		                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Iterator<Object> it = ((JSONArray) v).iterator();
		                while (it.hasNext()) {
		                    JSONObject json2 = (JSONObject)it.next();
		                    list.add(parseJSON2Map(json2));
		                }
		                map.put(k.toString(), list);
		            } else {
		                map.put(k.toString(), v);
		            }
		        }
		        return map;
		} 
		
		/**
		 * 得到JSONObject里的所有key
		 * @param jsonObject JSONObject实例对象
		 * @return Set
		 */
		public static Set<String> getAllKeys(JSONObject jsonObject) {
			return getAllKeys(jsonObject.toString());
		}
		 
		/**
		 * 从JSON字符串里得到所有key
		 * @param jsonString json字符串
		 * @return Set
		 */
		public static Set<String> getAllKeys(String jsonString) {
			Set<String> set = new HashSet<>();
			//按照","将json字符串分割成String数组
			String[] keyValue = jsonString.split(",");
			for(int i=0; i<keyValue.length; i++) {
				String s = keyValue[i];
				//找到":"所在的位置，然后截取
				int index = s.indexOf(":");
				//第一个字符串因带有json的"{"，需要特殊处理
				if(i==0) {
					set.add(s.substring(2, index-1));
				} else {
					set.add(s.substring(1, index-1));
				}
			}
			return set;
		}

		
		
		

}
