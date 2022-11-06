package kr.ac.jbnu.se.awp.gitplay4.core;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	public static final String BASE_DIR = "C:/";
	public static final String CSV_DIR = "/csv";
	public static final String CHART_DIR = "/chart";

	public static void addFile(String id, HttpServletRequest req) {
		System.out.println(id);
		String UPLOAD_DIR = id;
		// ���ε� ���?
		String uploadPath = BASE_DIR + UPLOAD_DIR;
		makeDir(uploadPath);
		uploadPath += CSV_DIR;
		makeDir(uploadPath);

		String fileName = "";
		try {
			for (Part part : req.getParts()) { // req.getParts() : Multipart�����͵��� Part��ü�� ����
				fileName = getFileName(part); // ���ϸ��� �������ִ� �޼ҵ� ����
				System.out.println(fileName);
				System.out.println(part.getHeader("content-disposition")); // part���� header ����
				if (fileName != null && !"".equals(fileName)) { // !���ʵ��� && !���Ͼƿ����ε� ������ ��
					part.write(uploadPath + File.separator + fileName); // ���� ����
					System.out.println("���ϸ� : " + fileName + "����Ϸ�?!!!");
				}

			}
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addFile(String id, MultipartFile file) {
		System.out.println(id);
		String UPLOAD_DIR = id;
		// ���ε� ���?
		String uploadPath = BASE_DIR + UPLOAD_DIR;
		makeDir(uploadPath);
		uploadPath += CSV_DIR;
		makeDir(uploadPath);

		String fileName = "";
		try {
			fileName = file.getOriginalFilename();
			File dest =  new File(uploadPath + File.separator + fileName);
			file.transferTo(dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static File getRecentChartFile(String id) {
		return getLastModified(BASE_DIR + id + CHART_DIR);
	}

	private static void makeDir(String uploadPath) {
		new File(uploadPath).mkdirs();
	}

	public static File getRecentFile(String id) {
		return getLastModified(BASE_DIR + id);
	}
	
	public static File getRecentCsv(String id) {
		return getLastModified(BASE_DIR + id + CSV_DIR);
	}

	private static String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				String filename = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
			}
		}
		return null; // filename�� ���� ���? (���ʵ� �������� ���?):
	}

	public static File getLastModified(String directoryFilePath) {
		File directory = new File(directoryFilePath);
		File[] files = directory.listFiles(File::isFile);
		long lastModifiedTime = Long.MIN_VALUE;
		File chosenFile = null;

		if (files != null) {
			for (File file : files) {
				if (file.lastModified() > lastModifiedTime) {
					chosenFile = file;
					lastModifiedTime = file.lastModified();
				}
			}
		}

		return chosenFile;
	}

	public static String getChartFolderPath(String id) {
		String directory =  BASE_DIR + id + CHART_DIR;
		makeDir(directory);
		return directory;
	}
}
