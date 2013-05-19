package org.xezz.timeregistration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xezz.timeregistration.model.TimeSpan;
import org.xezz.timeregistration.services.TimeSpanService;

/**
 * User: Xezz
 * Date: 05.05.13
 * Time: 11:18
 */
@Controller
@RequestMapping(value = "/timespan")
public class TimeSpanController {

    @Autowired
    TimeSpanService service;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET, value = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<TimeSpan> getAll() {
        logger.info("Request to get all TimeSpans");
        return service.findAllTimeSpans();
    }

    @RequestMapping(method = RequestMethod.POST, value = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TimeSpan create(@RequestBody TimeSpan timeSpan) {
        logger.info("Request to create a new TimeSpan");
        return service.createNewTimeSpan(timeSpan);
    }

    @RequestMapping(method = RequestMethod.PUT, value = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TimeSpan update(@RequestBody TimeSpan timeSpan) {
        logger.info("Request to update an existing TimeSpan");
        return service.updateTimeSpan(timeSpan);
    }


    // TODO: Add DELETE for TimeSpan
}
