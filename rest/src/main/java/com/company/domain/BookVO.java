package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class BookVO {
	private int code;
	private String title;
	private String writer;
	private int price;
}
