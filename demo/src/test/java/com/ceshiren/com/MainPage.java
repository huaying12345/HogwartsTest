package com.ceshiren.com;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class MainPage {

    public MainPage() {
    }

    public ContactPage addMember(){
        return new ContactPage();
    }
}
