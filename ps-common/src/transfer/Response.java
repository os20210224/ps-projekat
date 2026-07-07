package transfer;

import java.io.Serializable;
import transfer.enums.Status;

public class Response implements Serializable {
	
	private Object object;
	private Status status;

	public Response(Object object, Status status) {
		this.object = object;
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public Status getStatus() {
		return status;
	}
	
}
