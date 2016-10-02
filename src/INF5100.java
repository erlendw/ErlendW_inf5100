import com.espertech.esper.client.*;

import java.util.StringTokenizer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class INF5100 {
	public static class CEPListener implements UpdateListener {
		public void update(EventBean[] newData, EventBean[] oldData) {
			System.out.println("EVENT! " + newData[0].getUnderlying());
		}
	}

	public static void main(String[] args) {
		// Setup
		Configuration cepConfig = new Configuration();
		cepConfig.addEventType("StockTick", Tick.class.getName());
		EPServiceProvider cep = EPServiceProviderManager.getProvider("INF5100", cepConfig);
		EPRuntime cepRT = cep.getEPRuntime();

		EPAdministrator cepAdm = cep.getEPAdministrator();

		for(int queryCounter = 1; queryCounter <= 1; queryCounter++) {
			System.out.println("------------------");
			System.out.println("PERFORMING QUERY " + queryCounter);
			System.out.println("------------------");

			try {
				String query = new Scanner(new File("query_" + queryCounter + ".epl")).useDelimiter("\\Z").next();
				cepAdm.destroyAllStatements();
				EPStatement cepStatement = cepAdm.createEPL(query);
				cepStatement.addListener(new CEPListener());
			} catch(Exception e) {
				System.err.println("Error: " + e.getMessage());
			}

			// Generate ticks
			try {
				File file = new File("BTC.csv");
				BufferedReader reader  = new BufferedReader(new FileReader(file));
				String line = null;

				while((line = reader.readLine()) != null) {
					Tick tick = new Tick("BTC");
					StringTokenizer tokenizer = new StringTokenizer(line, ",");

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						tick.setTimestamp(dateFormat.parse(tokenizer.nextToken()));
					} catch(Exception e) {
						System.err.println("Error: " + e.getMessage());
					}

					tick.setOpen(Double.parseDouble(tokenizer.nextToken()));
					tick.setHigh(Double.parseDouble(tokenizer.nextToken()));
					tick.setLow(Double.parseDouble(tokenizer.nextToken()));
					tick.setClose(Double.parseDouble(tokenizer.nextToken()));
					tick.setVolume(Double.parseDouble(tokenizer.nextToken()));
					tick.setWeightedPrice(Double.parseDouble(tokenizer.nextToken()));

					//System.out.println("TICK!  " + tick);
					cepRT.sendEvent(tick);
				}

				reader.close();
			} catch(Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}
}
