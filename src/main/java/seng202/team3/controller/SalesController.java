package seng202.team3.controller;

import seng202.team3.view.BusinessApp;

import java.io.IOException;

public class SalesController {

    public void backButtonPressed() throws IOException {
        System.out.println("Back button pressed");
        BusinessApp.loadMainPage();
    }
}