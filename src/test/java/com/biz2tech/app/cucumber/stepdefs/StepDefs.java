package com.biz2tech.app.cucumber.stepdefs;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.biz2tech.app.FragrancenetserviceApp;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = FragrancenetserviceApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
