package hoang.duc.dung.boomoffline.exceptions;

public class BoomOfflineException extends Exception {
	public BoomOfflineException() {
	}
	
	public BoomOfflineException(String str) {
		super(str);
	}
	
	public BoomOfflineException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public BoomOfflineException(Throwable cause) {
		super(cause);
	}

}
