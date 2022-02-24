package sample.api.sample.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	// 例外処理の全てのレスポンスをカスタマイズ
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request
			) {
		ApiError apiError = new ApiError(status.value(), ex.getMessage());
		return super.handleExceptionInternal(ex, apiError, headers, status, request);
	}

	// NotFoundExceprionクラスがthrowされた場合に実行
	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> handleResponseStatus(NotFoundException ex, WebRequest request) {
		System.out.println("エラー制御");
		return handleExceptionInternal(ex, null,null, HttpStatus.NOT_FOUND, request);
	}
}
