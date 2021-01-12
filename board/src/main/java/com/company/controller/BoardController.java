package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.company.service.BoardService;

@Controller

public class BoardController {
	@Autowired
	private BoardService service;
}
