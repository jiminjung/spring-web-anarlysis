package kr.ac.jbnu.se.awp.gitplay4.core.r;

import kr.ac.jbnu.se.awp.gitplay4.model.ChartType;

public class LineChartGeneratorBuilder extends ChartGeneratorBuilder {

	public LineChartGeneratorBuilder() {
		this(null, null);
	}
	
	public LineChartGeneratorBuilder(String csvPath, String outputPath) {
		super(csvPath, outputPath);
	}

	@Override
	protected ChartGenerator createChratGenerator() {
		ChartGenerator toReturn = new ChartGenerator(csvPath, chartName,yRangeMax,yRangeMin, xName, yName, outputPath, ChartType.LINE);
		
		return toReturn;
	}

}
