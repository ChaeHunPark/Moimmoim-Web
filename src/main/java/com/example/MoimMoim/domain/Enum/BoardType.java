package com.example.MoimMoim.domain.Enum;

public enum BoardType {
    GENERAL("general"), // 모집 중
    MOIM("moim");  // 취소됨

    private String value;

    BoardType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
