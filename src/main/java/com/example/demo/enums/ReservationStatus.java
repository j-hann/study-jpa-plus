package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum ReservationStatus {

	PENDING(0, "대기"),
	APPROVED(1, "승인"),
	CANCELED(2, "취소"),
	EXPIRED(3, "만료");

	private final Integer statusNumber;
	private final String statusText;

	ReservationStatus(Integer statusNumber, String statusText) {
		this.statusNumber = statusNumber;
		this.statusText = statusText;
	}
}
