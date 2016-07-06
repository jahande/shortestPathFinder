package models;

public class ClickState {
	public static final int WAIT_FOR_ANY_START = 0;
	public static final int WAIT_FOR_END_OUTTER = 1;
	public static final int WAIT_FOR_END_INNER = 2;
	public static final int WAIT_FOR_END_POINT = 3;

	public static final int WAIT_FOR_START_POINT = 4;
	
	private int state = 0;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	

}
