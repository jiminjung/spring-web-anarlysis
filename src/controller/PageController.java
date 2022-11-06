package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.jbnu.se.awp.gitplay4.core.FileManager;
import kr.ac.jbnu.se.awp.gitplay4.core.UserManager;
import kr.ac.jbnu.se.awp.gitplay4.core.r.ChartGenerator;
import kr.ac.jbnu.se.awp.gitplay4.core.r.ChartGeneratorBuilder;
import kr.ac.jbnu.se.awp.gitplay4.core.r.ChartGeneratorBuilderFactory;
import kr.ac.jbnu.se.awp.gitplay4.model.ChartType;
import kr.ac.jbnu.se.awp.gitplay4.model.Login;
import kr.ac.jbnu.se.awp.gitplay4.model.RegistrationException;

@Controller
public class PageController {
	String message = "";

	private static final String ATTRIBUTE_ID = "id";
	private static final String ATTRIBUTE_PASSWORD = "password";
	private static final String ATTRIBUTE_CHART_TYPE = "chartType";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage(Locale locale, Model model) {
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(Locale locale, Model model, HttpServletRequest req) {
		if (isValidAccess(req.getSession())) {
			return "redirect:/upload";
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Login login, HttpServletRequest req) {
		String id = login.getId();
		String password = login.getPassword();
		HttpSession session = req.getSession();
		req.setAttribute(ATTRIBUTE_ID, id);
		req.setAttribute(ATTRIBUTE_PASSWORD, password);

		if (isValidAccess(session)) {
			return "redirect:/upload";
		} else {
//			session.setAttribute(ATTRIBUTE_ID, null);
//			session.setAttribute(ATTRIBUTE_ID, null);
			session.setAttribute("message", "올바른 ID와 패스워드가 아닙니다");
//			return "redirect:/login";
			return "redirect:/upload";
		}
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegestrationPage() {
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@RequestParam(name = "id") String id, @RequestParam(name = "password") String password,
			@RequestParam(name = "passwordConfirm") String passwordConfirm) {

		UserManager manager = UserManager.getInstance();
		try {
			manager.registerUser(id, password);
		} catch (RegistrationException e) {
			e.printStackTrace();
			return "registration";
		}
		return "login";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String getUploadPage() {
		return "upload_form";
	}

	@RequestMapping(value = "generate", method = RequestMethod.POST)
	public String generate(@RequestParam(name = "chartName", required = false) String chartName,
			@RequestParam(name = "xAxis", required = false) String xAxis, @RequestParam(name = "yAxis") String yAxis,
			@RequestParam(name = "ymax", required = false) String ymax,
			@RequestParam(name = "ymin", required = false) String ymin, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");

		String path;

		path = FileManager.getRecentCsv(id).getPath();
		String saveDirectoryPath = FileManager.getChartFolderPath(id);

		ChartType type = (ChartType) session.getAttribute("chartType");
		ChartGeneratorBuilder builder = ChartGeneratorBuilderFactory.createBuilder(type);
		ChartGenerator generator = builder.csvPath(path).chartName(chartName).yRangeMax(ymax).yRangeMin(ymin)
				.outputPath(saveDirectoryPath).xName(xAxis).yName(yAxis).build();

		generator.generate();

		return "extract_image";
	}

	@RequestMapping(value = "configuration", method = RequestMethod.POST)
	public String configuration(HttpServletRequest req) {

		HttpSession session = req.getSession();
		System.out.println(req.getParameter("chartType"));
		String chartType = req.getParameter("chartType");

		ChartType type = null;
		switch (chartType) {
		case "BAR":
			type = ChartType.BAR;
			break;
		case "HISTOGRAM":
			type = ChartType.HISTOGRAM;
			break;
		case "LINE":
			type = ChartType.LINE;
			break;
		case "BOX":
			type = ChartType.BOX;
			break;
		case "PIE":
			type = ChartType.PIE;
			break;
		}
		session.setAttribute(ATTRIBUTE_CHART_TYPE, type);

		return "configuration_form";
	}

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public String selectChartDoPost(HttpServletRequest req) {

		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println(id);
		FileManager.addFile(id, req);

		return "select_charttype";
	}

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public void fileDownlad(@PathVariable(value = "id") String id, HttpServletResponse response) {

		System.out.println("aa" + id);
		File file = FileManager.getRecentChartFile(id);
		String filePath = file.getPath();
		String fileName = "Generated Chart.png";
		String contentType = "image/png";
		long fileLength = file.length();

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		// 그 정보들을 가지고 reponse의 Header에 세팅한 후

		try (FileInputStream fis = new FileInputStream(filePath); OutputStream out = response.getOutputStream();) {
			// saveFileName을 파라미터로 넣어 inputStream 객체를 만들고
			// response에서 파일을 내보낼 OutputStream을 가져와서
			int readCount = 0;
			byte[] buffer = new byte[1024];
			// 파일 읽을 만큼 크기의 buffer를 생성한 후
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
				// outputStream에 씌워준다
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private boolean isValidAccess(HttpSession session) {
		String id = (String) session.getAttribute("id");
		String password = (String) session.getAttribute("password");

		if (id == null || password == null) {
			return false;
		}

		return UserManager.getInstance().isValid(id, password);
	}
}