package kr.ac.jbnu.se.awp.gitplay4.core.r;

public abstract class ChartGeneratorBuilder {
	protected String csvPath;
	protected String outputPath;
	
	protected String chartName;
	protected String xName;
	protected String yName;
	protected double xRangeMin;
	protected double xRangeMax;
	protected String yRangeMin;
	protected String yRangeMax;
	protected double gridRange;
	
	public ChartGeneratorBuilder() {
		this(null, null);
	}
	
	public ChartGeneratorBuilder(String csvPath, String outputPath) {
		this.csvPath = csvPath;
		this.outputPath = outputPath;
		
		this.chartName = "Untitled";
		this.xName = "xAxis";
		this.yName = "yAxis";
	}
	
	public ChartGenerator build() {
		return createChratGenerator();
	}
	
	
	protected abstract ChartGenerator createChratGenerator();

	public ChartGeneratorBuilder csvPath(String csvPath) {
		this.csvPath = csvPath;
		return this;
	}

	public ChartGeneratorBuilder outputPath(String outputPath) {
		this.outputPath = outputPath;
		return this;
	}
	
	public ChartGeneratorBuilder chartName(String name) {
		this.chartName = name;
		return this;
	}
	
	public ChartGeneratorBuilder xName(String name) {
		this.xName = name;
		return this;
	}
	
	public ChartGeneratorBuilder yName(String name) {
		this.yName = name;
		return this;
	}
	
	public ChartGeneratorBuilder xRangeMin(double xRangeMin) {
		this.xRangeMin = xRangeMin;
		return this;
	}
	
	public ChartGeneratorBuilder xRangeMax(double xRangeMax) {
		this.xRangeMax = xRangeMax;
		return this;
	}
	
	public ChartGeneratorBuilder yRangeMin(String yRangeMin) {
		this.yRangeMin = yRangeMin;
		return this;
	}
	
	public ChartGeneratorBuilder yRangeMax(String yRangeMax) {
		this.yRangeMax = yRangeMax;
		return this;
	}
	
	public ChartGeneratorBuilder gridRange(double gridRange) {
		this.gridRange = gridRange;
		return this;
	}
}
