package guimodule;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

public class LifeExpectancy extends PApplet{

	UnfoldingMap map;
	Map<String, Float> lifeExpByCountry;

	
	public void setup()
	{
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50	, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		lifeExpByCountry = loadLifeExpectancyFromCSV("data/LifeExpectancyWorldBank.csv");
	}
	
	public void draw()
	{
		map.draw();
	}
	
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName)
	{
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();
		String[] rows = loadStrings(fileName);
		
		for(String row : rows)
		{
			String[] columns = row.split(",");
	
			if (columns.length == 17 && !columns[4].equals("..")) 
			{
				float value = Float.parseFloat(columns[4]);
				lifeExpMap.put(columns[3], value);
			}
			
		}
		
		return lifeExpMap;
	}
}
