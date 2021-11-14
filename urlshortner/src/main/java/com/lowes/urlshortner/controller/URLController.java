package com.lowes.urlshortner.controller;

import com.lowes.urlshortner.dao.models.URLRequest;
import com.lowes.urlshortner.services.exception.CustomException;
import com.lowes.urlshortner.services.exception.ErrorCode;
import com.lowes.urlshortner.utility.URLValidatorUtility;
import com.lowes.urlshortner.services.URLConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class URLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);

    @Autowired
    URLConverterService urlConverterService;

    public URLController(URLConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }

    @PostMapping(path = "shorten")
    public String shortenUrl(@RequestBody @Validated final URLRequest shortenRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("Received url to shorten: " + shortenRequest.getUrl());
        if (urlConverterService.urlAlreadyShortened(shortenRequest.getUrl())) {
            throw new CustomException("Url was already shortened", ErrorCode.INTERNAL_SERVER_ERROR);
        }
        String longUrl = shortenRequest.getUrl();
        if (URLValidatorUtility.validateURL(longUrl)) {
            String localURL = request.getRequestURL().toString();
            String shortenedUrl = urlConverterService.shortenURL(localURL, shortenRequest.getUrl());
            LOGGER.info("Shortened url to: " + shortenedUrl);
            return shortenedUrl;
        } else {
            throw new Exception("Please enter a valid URL");
        }
    }

    @GetMapping(value = "/{id}")
    public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
        LOGGER.debug("Received shortened url to redirect: " + id);
        String redirectUrlString = urlConverterService.getLongURLFromID(id);
        RedirectView redirectView = new RedirectView();
        if (redirectUrlString.contains("\"http://\"")) {
            redirectUrlString = "http://" + redirectUrlString;
        }
        redirectView.setUrl(redirectUrlString);
        return redirectView;
    }
}
