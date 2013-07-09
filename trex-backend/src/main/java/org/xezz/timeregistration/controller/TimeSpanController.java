package org.xezz.timeregistration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xezz.timeregistration.dao.TimeSpanDAO;
import org.xezz.timeregistration.service.TimeSpanService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * User: Xezz
 * Date: 05.05.13
 * Time: 11:18
 */
@Controller
@RequestMapping(value = "api/timespan")
public class TimeSpanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeSpanController.class);
    private Validator validator;
    TimeSpanService service;

    @Autowired
    public void setService(TimeSpanService service) {
        this.service = service;
    }

    @Resource(name = "timeSpanValidator")
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<TimeSpanDAO> getAll() {
        LOGGER.info("Request to get all TimeSpans");
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TimeSpanDAO create(@Valid @RequestBody TimeSpanDAO timeSpanDAO) {
        LOGGER.info("Request to create a new TimeSpan");
        return service.addNew(timeSpanDAO);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TimeSpanDAO update(@Valid @RequestBody TimeSpanDAO timeSpanDAO) {
        LOGGER.info("Request to update an existing TimeSpan");
        return service.update(timeSpanDAO);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@Valid @RequestBody TimeSpanDAO timeSpanDAO) {
        LOGGER.info("Request to update an existing TimeSpan");
        service.delete(timeSpanDAO);
    }


    // TODO: Add DELETE for TimeSpan
}
