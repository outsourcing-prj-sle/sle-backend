package egovframework.example.cmmn.service;

import org.springframework.http.HttpStatus;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultVO<T> {
    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

    public void ResultDto(final HttpStatus statusCode, final String resultMsg) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultData = null;
    }

    public static<T> ResultVO<T> res(final HttpStatus statusCode, final String resultMsg) {
        return res(statusCode, resultMsg, null);
    }

    public static<T> ResultVO<T> res(final HttpStatus statusCode, final String resultMsg, final T t) {
        return ResultVO.<T>builder()
                .resultData(t)
                .statusCode(statusCode)
                .resultMsg(resultMsg)
                .build();
    }
}