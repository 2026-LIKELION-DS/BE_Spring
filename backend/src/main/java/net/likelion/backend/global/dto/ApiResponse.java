package net.likelion.backend.global.dto;

import net.likelion.backend.global.exception.ErrorCode;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final boolean success; //성공여부
    private final String code;  //응답코드
    private final String message;  //사용자에게 보여줄 메세지
    private final T data;   // 실제 데이터

    private ApiResponse(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    //데이터 있는 성공(조회, 생성)
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, "SUCCESS", message, data);
    }
    //데이터 없는 성공(삭제 등)
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, "SUCCESS", message, null);
    }
    //Errorcode만으로 에러
    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<>(false, errorCode.name(), errorCode.getMessage(), null);
    }
    //Errorcode + 직접 메시지 만들어서 반환
    public static <T> ApiResponse<T> error(ErrorCode errorCode, String message) {
        return new ApiResponse<>(false, errorCode.name(), message, null);
    }
}
