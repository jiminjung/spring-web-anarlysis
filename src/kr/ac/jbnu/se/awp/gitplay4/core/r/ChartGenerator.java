package kr.ac.jbnu.se.awp.gitplay4.core.r;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
//import javax.servlet.http.HttpServletRequest;

import kr.ac.jbnu.se.awp.gitplay4.model.ChartType;

public class ChartGenerator {
	// this class is parent class of line, bar, ~ class
	// getting parameter, preprocessing

	// for using R, Rconnection is necessary. it is used like using other R server.
	RConnection connection = null;

	// to get parameter example, it will be changed to get parameter by other class
	// the number of necessary parameters is different between graphs
	protected String downloadPath = "C:/Users/jimin/vscode-workspace/Web_with_R/userFile/";
	protected String userIP = "3530916043";
	protected String userfilename = "3530916043.csv";
	protected String saveDirectoryPath;
	protected String rfilePath = "\"" + downloadPath + "\"";
	protected String imageTitle = "Untitled";
	protected String imageName = imageTitle.concat(".png");
	protected String x = "수집시간";
	protected String y = "온도";
	protected String xName = "Time";
	protected String yName = "Temperature";
	protected String outlierName = "온도";
	protected String outlierRange_start = "30";
	protected String outlierRange_end = "50";
	protected final ChartType chartType;
	protected String ymax=String.valueOf(Double.MAX_VALUE), ymin=String.valueOf(Double.MIN_VALUE);

//	public ChartGenerator(String csvPath, String chartName, String xAxis, String yAxis, String saveDirectoryPath, ChartType chartType) {
//		this(csvPath, chartName, Double.MIN_VALUE, Double.MAX_VALUE, xAxis, yAxis, saveDirectoryPath, chartType);
//	}
	
	public ChartGenerator(String csvPath, String chartName, String ymax, String ymin, String xAxis, String yAxis, String saveDirectoryPath, ChartType chartType) {
		this.downloadPath = csvPath.replace("\\", "/");
		this.rfilePath = "\"" + downloadPath + "\"";
		this.imageTitle = chartName;
		this.x = xAxis;
		this.y = yAxis;
		this.xName = xAxis;
		this.yName = yAxis;
		this.ymax = ymax;
		this.ymin = ymin;
		this.saveDirectoryPath = saveDirectoryPath.replace("\\", "/");
		this.saveDirectoryPath = "\"" + saveDirectoryPath + "\"";
		this.chartType = chartType;
		System.out.println(this.saveDirectoryPath);
	}

	public void generate() {

		try {
			connection = new RConnection();

			System.out.println("csv_data <- read.csv(" + rfilePath + ",header=TRUE)");
			connection.eval("csv_data <- read.csv(" + rfilePath + ",header=TRUE)");
			System.out.println("names(csv_data) <- gsub(\"[.]\",\" \",names(csv_data))");
			connection.eval("names(csv_data) <- gsub(\"[.]\",\" \",names(csv_data))");
			
			if(ymax!=""&&ymin!="") {
				System.out.println("csv_data <- subset(csv_data, "+yName+"<"+ymax+" & \""+yName+"\">"+ymin+")");
				connection.eval("csv_data <- subset(csv_data, \""+yName+"\"<="+ymax+" & \""+yName+"\">="+ymin+")");
			}
			
			connection.eval("library(ggplot2)");
			connection.eval("library(dplyr)");

			
			String evalParam = getEvalParam();
			String evalChartType = getEvalChartType();

			connection.eval(evalParam + evalChartType + "+ ggtitle(\"" + imageTitle + "\")" + "+ xlab(\"" + xName
					+ "\")+ylab(\"" + yName + "\")" + "+ theme_light()"
					+ "+ theme(plot.title = element_text(size=20, hjust=0.5))");

			connection.eval("setwd(" + saveDirectoryPath + ")");
			connection.eval("ggsave(filename=\"" + imageName + "\", plot=a, width=12,height=6)");

			System.out.println(imageName + ", "+ chartType+ " Making Clear");

		} catch (RserveException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	// it also will be changed because of difference of the number of x,y parameters
	// maybe this method will have parameter like outlierName and Range

	private String getEvalParam() {
		String toReturn = "";
		
		switch (this.chartType) {
		case LINE:
			toReturn = "a<-csv_data%>% ggplot(aes(`"+x+"`, `"+y+"`, group=1))";
			break;
		case BAR:
			toReturn = "a<-csv_data%>% ggplot(aes(`" + y + "`, group=1))";
			break;
		case HISTOGRAM:
			toReturn = "a<-csv_data%>% ggplot(aes(`" + y + "`, group=1))";
			break;
		case BOX:
			toReturn = "a<-csv_data%>% ggplot(aes(`" + x + "`, `" + y + "`, group=1))";
			break;
		default:
			break;
		}
		return toReturn;
	}

	private String getEvalChartType() {
		String toReturn = "";
		
		switch (this.chartType) {
		case LINE:
			toReturn = "+ geom_line(color = 'coral')";
			break;
		case BAR:
			toReturn = "+ geom_bar(color = 'coral')";
			break;
		case HISTOGRAM:
			toReturn = "+ geom_histogram(color = 'red')";
			break;
		case BOX:
			toReturn = "+ geom_boxplot(color = 'coral')";
			break;
		default:
			break;
		}
		
		return toReturn;
	}

	public void preprocess() {

		try {
			connection = new RConnection();

			connection.eval("csv_data <- read.csv(" + rfilePath + ",header=TRUE)");
			connection.eval("csv_data <- subset(csv_data, " + outlierName + ">" + outlierRange_start + " &"
					+ outlierName + "<" + outlierRange_end + ")");
			connection.eval("setwd(\"" + downloadPath + "/" + userIP + "\")");
			connection.eval("write.csv(csv_data, file = \"" + userfilename + "\", row.names=FALSE)");

			System.out.println("Preprocessing clear");

		} catch (RserveException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

}
