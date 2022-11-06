package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.jbnu.se.awp.gitplay4.core.FileManager;


@Controller
@RequestMapping(value = "/image/**")
public class ImageController {
	
	@RequestMapping(value = "**/generatedChart", method = RequestMethod.GET)
	public void setImageFileById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = (String) req.getSession().getAttribute("id");
		File file = FileManager.getRecentChartFile(id);
		String filePath = file.getPath();
		String url = "file:///" + filePath;
		URL fileUrl = new URL(url);
		System.out.println(url);
		fileUrl.openStream().transferTo(resp.getOutputStream());
	}
}
