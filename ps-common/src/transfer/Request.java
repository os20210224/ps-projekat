package transfer;

import java.io.Serializable;
import transfer.enums.Operation;

public class Request implements Serializable {
	
	private Object object;
	private Operation op;

	public Request(Object object, Operation op) {
		this.object = object;
		this.op = op;
	}

	public Object getObject() {
		return object;
	}

	public Operation getOperation() {
		return op;
	}
	
}
