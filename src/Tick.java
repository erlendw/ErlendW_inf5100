import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Tick {
	String	symbol;
	Date		timestamp;
	Double	open;
	Double	high;
	Double	low;
	Double	close;
	Double	volume;
	Double	weighted_price;

	public Tick(String s) {
	    symbol = s;
	}

	public Tick(String s, Date t, double o, double h, double l, double c, double v, double w_p) {
    symbol = s;
		timestamp = t;
		open = o;
		high = h;
		low = l;
		close = c;
		volume = v;
		weighted_price = w_p;
	}

	public String getSymbol() { return symbol; }
	public Date   getTimestamp() { return timestamp; }
	public double getOpen() { return open; }
	public double getHigh() { return high; }
	public double getLow() { return low; }
	public double getClose() { return close; }
	public double getVolume() { return volume; }
	public double getWeightedPrice() { return weighted_price; }

	public void setTimestamp(Date t) { timestamp = t; }
	public void setOpen(double o) { open = o; }
	public void setHigh(double h) { high = h; }
	public void setLow(double l) { low = l; }
	public void setClose(double c) { close = c; }
	public void setVolume(double v) { volume = v; }
	public void setWeightedPrice(double w_p) { weighted_price = w_p; }

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    return
			"Symbol: " + symbol + "  " +
			"Timestamp: " + dateFormat.format(timestamp) + "   " +
			"Open: " + open.toString() + "   \t   " +
			"High: " + high.toString() + "    \t    " +
			"Low: " + low.toString() + "    \t    " +
			"Close: " + close.toString() + "   \t   " +
			"Volume: " + volume.toString() + "   \t   " +
			"Weighted Price: " + weighted_price.toString();
	}
}
