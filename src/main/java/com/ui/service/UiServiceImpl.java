package com.ui.service;

import com.ui.entity.Device;
import com.ui.repository.UiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UiServiceImpl implements UiService {

    @Autowired
    private UiRepository repository;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private List<Device> getDevisesBySeparator(List<Device> devices, Long separator) {

        List<Device> container = new ArrayList<>();

        for (Device device : devices) {

            String deviceTime = format.format(new Date(device.getStartSession()*1000));
            String separatorTime = format.format(new Date(separator*1000));

            if(deviceTime.equals(separatorTime))
                container.add(device);
        }

        return container;
    }

    @Override
    public Map<String, List<Device>> findBySessionPeriod(Long startSession, Long endSession, Long[] separators) {

        Map<String, List<Device>> listOfDevices = new HashMap<>();
        List<Device> distinctDevices = new ArrayList<>();

        List<Device> allDevices = repository.findByStartSessionGreaterThanAndEndSessionLessThan(startSession, endSession);
        listOfDevices.put("allDevices", allDevices);

        allDevices.stream().distinct().forEach(distinctDevices::add);
        listOfDevices.put("distinctDevices", distinctDevices);

        for(int i = 0; i < separators.length; i++) {
            listOfDevices.put(format.format(separators[i]*1000), getDevisesBySeparator(allDevices, separators[i]));
        }

        for(int i = 0; i < separators.length; i++) {
            listOfDevices.put("distinct" + format.format(separators[i]*1000), getDevisesBySeparator(distinctDevices, separators[i]));
        }

        return listOfDevices;
    }

}