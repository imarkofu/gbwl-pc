package me.gbwl.pc.base;

public class Result {
	public static final int SUCCESS = 0;
	public static final int RESULT_NOLOGIN = 1;
	public static final int NOPERMISSION = 2;
	public static final int PARAMCHECKERROR = 3;
	public static final int ERROR = 4;
	int status = -1;
	String msg;
	Object value;
	String nextUrl;

	public Result() {
	}

	public Result(int status) {
		setStatus(status);
	}

	public Result(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public String getNextUrl() {
		return this.nextUrl;
	}

	public Result setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
		return this;
	}

	public int getStatus() {
		return this.status;
	}

	public Result setStatus(int status) {
		this.status = status;
		switch (status) {
		case 0:
			this.msg = "�����ɹ���";
			break;
		case 1:
			this.msg = "δ��¼";
			break;
		case 2:
			this.msg = "���¼";
			break;
		case 3:
			this.msg = "������֤����";
			break;
		default:
			this.msg = "δ֪����";
		}
		return this;
	}

	public String getMsg() {
		return this.msg;
	}

	public Result setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	@SuppressWarnings("unchecked")
	public <E> E getValue() {
		return (E) this.value;
	}

	public <E> Result setValue(E value) {
		this.value = value;
		return this;
	}

	public String toString() {
		return "Result [status=" + this.status + ", msg=" + this.msg
				+ ", value=" + this.value + ", nextUrl=" + this.nextUrl + "]";
	}
}