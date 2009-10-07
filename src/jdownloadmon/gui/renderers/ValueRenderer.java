package jdownloadmon.gui.renderers;

/**
 * A value renderer, used for rendering a long, and has a getter and a setter for that long.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class ValueRenderer extends TextRenderer {

	/** The value this renderer displays. */
	private long mValue;

	/**
	 * Construct a value renderer.
	 * @param text The text that gets displayed using this renderer.
	 * @param value The value contained in this renderer.
	 */
	public ValueRenderer(String text, long value) {
		super(text);
		mValue = value;
	}

	/**
	 * @return The value wrapped in this renderer, as a <tt>long</tt>.
	 */
	public long getValue() {
		return mValue;
	}

	/**
	 * Set the value of this renderer to a new value.
	 * @param value The <tt>long</tt> value to set to.
	 */
	public void setValue(long value) {
		mValue = value;
	}

	@Override
	public int compareTo(DownloadRenderer o) {
		if (o instanceof ValueRenderer) {
			return (int) (mValue - ((ValueRenderer) o).mValue);
		}

		return super.compareTo(o);
	}
}
