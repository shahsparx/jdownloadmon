package jdownloadmon.gui.viewStates;

/**
 * Class for displaying an error status state.
 * @author Edward Larsson (edward.larsson@gmx.com)
 */
public class ErrorViewState extends ViewState {

	/** The error message to be shown. */
	private String mErrorMessage;

	/**
	 * Construct an error view state.
	 * @param error The error message to be shown.
	 */
	public ErrorViewState(String error) {
		mErrorMessage = error;
	}

	@Override
	public String toString() {
		return "error";
	}

	@Override
	public String getToolTipText() {
		return mErrorMessage;
	}
}
