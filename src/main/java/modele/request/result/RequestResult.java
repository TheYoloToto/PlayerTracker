package modele.request.result;

public abstract class RequestResult<T> {

	private boolean success;
	private T data;

	protected RequestResult() {

	}

	protected RequestResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RequestModel [success=" + success + ", data=" + data + "]";
	}

}
