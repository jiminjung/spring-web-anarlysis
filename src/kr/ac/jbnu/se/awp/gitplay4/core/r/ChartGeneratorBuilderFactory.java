package kr.ac.jbnu.se.awp.gitplay4.core.r;

import kr.ac.jbnu.se.awp.gitplay4.model.ChartType;

public class ChartGeneratorBuilderFactory {
	static public ChartGeneratorBuilder createBuilder(ChartType type) {
		switch (type) {
		case LINE:
			return new LineChartGeneratorBuilder();
		case BAR:
			return new BarChartGeneratorBuilder();
		case HISTOGRAM:
			return new HistogramChartGeneratorBuilder();
		case BOX:
			return new BoxChartGeneratorBuilder();
		case PIE:
			return null;
		default:
			return null;
		}
	}
}
