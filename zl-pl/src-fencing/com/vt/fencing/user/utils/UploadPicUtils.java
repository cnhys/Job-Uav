package com.vt.fencing.user.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class UploadPicUtils {

	public Map<String, Object> uploadHeadSculpture(MultipartFile file, String path) throws IllegalStateException, IOException{
		Map<String, Object> map = new HashMap<String, Object>();
//		
		if (file != null) {// 判断上传的文件是否为空
			String type = null;// 文件类型
			String fileName = file.getOriginalFilename();// 文件原名称
			System.out.println("上传的文件原名称:" + fileName);
			// 判断文件类型
			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
					: null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())) {
					// 项目在容器中实际发布运行的根路径
//					String realPath = request.getSession().getServletContext().getRealPath("/");
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
					/*
					path = realPath
							+  System.getProperty("file.separator")+ trueFileName;
					*/
					map.put("trueFileName", trueFileName);
					path = path + trueFileName;
					System.out.println("存放图片文件的路径:" + path);
					// 转存文件到指定的路径
					file.transferTo(new File(path));
					System.out.println("文件成功上传到指定目录下");
				} else {
					System.out.println("不是我们想要的文件类型,请按要求重新上传");
					return null;
				}
			} else {
				System.out.println("文件类型为空");
				return null;
			}
		} else {
			System.out.println("没有找到相对应的文件");
			return null;
		}
		map.put("path", path);
		return map;
	}
	
	
}
