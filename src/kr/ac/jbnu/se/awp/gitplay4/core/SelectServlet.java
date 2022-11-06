package kr.ac.jbnu.se.awp.gitplay4.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.jbnu.se.awp.gitplay4.model.ChartType;

@WebServlet(name = "SelectServlet", urlPatterns = { "/SelectServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class SelectServlet  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		System.out.println(req.getParameter("chartType"));
		String chartType = req.getParameter("chartType");
		
		ChartType type = null;
		switch(chartType) {
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
		session.setAttribute("chartType", type);
		this.getServletContext().getRequestDispatcher("/configuration").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/configuration").forward(req, resp);
	}
}
