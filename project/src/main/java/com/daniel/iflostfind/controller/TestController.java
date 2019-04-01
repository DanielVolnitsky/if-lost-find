/*
 * Copyright 2007-2019 Thomson Reuters Global Resources. All rights reserved. Proprietary and confidential information of TRGR.
 * Disclosure, use, or reproduction without the written authorisation of TRGR is prohibited. $Id: TestController.java 100473
 * 4/1/2019 1:36 PM:22Z Daniel_Volnitsky $
 */

package com.daniel.iflostfind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController
{
    @GetMapping(path = {"/t"})
    public String toIndexPage()
    {

        return "test";
    }
}
