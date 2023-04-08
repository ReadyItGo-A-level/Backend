package com.alevel.backend.domain.response;

public class ResponseMessage {
    public static final String SUCCESS = "성공";
    public static final String FAIL = "실패";
    public static final String INVALIDATED_USER = "해당 사용자가 존재하지 않습니다";
    public static final String INVALIDATED_POST = "해당 피드가 존재하지 않습니다";
    public static final String INVALIDATED_REVIEW = "리뷰가 존재하지 않습니다";
    public static final String INVALIDATED_ALCOHOL = "해당 술이 존재하지 않습니다";
    public static final String WITHDRAWN_USER = "탈퇴한 사용자입니다";
    public static final String UNAUTHORIZED_ACCESS = "권한이 없습니다.";
    public static final String INVALIDATED_TOKEN = "토큰이 일치하지 않습니다.";
    public static final String INVALIDATED_PASSWORD = "패스워드가 일치하지 않습니다";
    public static final String DUPLICATED_USERNAME = "이미 존재하는 유저명입니다.";
    public static final String DUPLICATED_EMAIL = "이미 존재하는 이메일입니다.";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String EXCEEDED_NUMBER = "입력 가능한 글자수를 초과했습니다.";
    public static final String BAD_REQUEST_INPUT_RANGE = "입력값 범위가 잘못되었습니다.";
    public static final String DB_ERROR = "데이터베이스 에러";
}
