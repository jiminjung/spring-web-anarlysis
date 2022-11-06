package kr.ac.jbnu.se.awp.gitplay4.core.r;

import kr.ac.jbnu.se.awp.gitplay4.model.ChartType;

public class BoxChartGeneratorBuilder extends ChartGeneratorBuilder {

	public BoxChartGeneratorBuilder() {
		this(null, null);
	}
	
	public BoxChartGeneratorBuilder(String csvPath, String outputPath) {
		super(csvPath, outputPath);
	}

	@Override
	protected ChartGenerator createChratGenerator() {
		ChartGenerator toReturn = new ChartGenerator(csvPath, chartName,yRangeMax,yRangeMin,  xName, yName, outputPath, ChartType.BOX);
		
		return toReturn;
	}

}
