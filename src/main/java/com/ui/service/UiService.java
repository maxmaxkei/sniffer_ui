package com.ui.service;

import com.ui.entity.Device;
import java.util.List;
import java.util.Map;

public interface UiService {

    Map<String, List<Device>> findBySessionPeriod(Long startSession, Long endSession, Long[] separators);


}
