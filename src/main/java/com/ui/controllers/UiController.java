package com.ui.controllers;

import com.ui.entity.Device;
import com.ui.service.UiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UiController {

    @Autowired
    private UiServiceImpl uiService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Map<String, List<Device>> getListOfDevices(@RequestParam Long startSession, @RequestParam Long endSession, @RequestParam Long[] separators) {
        return uiService.findBySessionPeriod(startSession, endSession, separators);
    }

}
