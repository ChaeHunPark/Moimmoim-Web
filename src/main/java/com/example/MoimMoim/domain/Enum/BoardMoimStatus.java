package com.example.MoimMoim.domain.Enum;

public enum BoardMoimStatus {
    RECRUITING("recuiting"), // 모집 중
    CANCELLED("cancelled"),  // 취소됨
    COMPLETED("completed"); // 완료됨

    private String value;

    BoardMoimStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
