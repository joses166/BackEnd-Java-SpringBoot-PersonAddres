package br.com.josehamilton.api.response;

public class Response<T> {

	private T data;

	public Response() {
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}