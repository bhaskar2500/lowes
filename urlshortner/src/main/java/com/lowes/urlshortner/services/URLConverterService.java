package com.lowes.urlshortner.services;

import com.lowes.urlshortner.services.exception.CustomException;
import com.lowes.urlshortner.services.exception.ErrorCode;
import com.lowes.urlshortner.utility.ConverterUtility;
import com.lowes.urlshortner.dao.models.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.*;

@Service
public class URLConverterService {
    private final Logger logger = LoggerFactory.getLogger(URLConverterService.class);

    @Autowired
    URLRepository urlRepository;

    public String shortenURL(String localURL, String longUrl) throws Exception {
        try {
            logger.info("Shortening {}", longUrl);
            Long id = urlRepository.count() + 1L;
            String uniqueID = ConverterUtility.createUniqueID(id);
            urlRepository.save(new URL(id, "url:" + id, longUrl));
            String baseString = formatLocalURLFromShortener(localURL);
            String shortenedURL = baseString + uniqueID;
            return shortenedURL;
        } catch (Exception ex) {
            logger.info(String.format("Exception occurede while shortening URL: %s", longUrl));
            throw new CustomException(ex, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public String getLongURLFromID(String uniqueID) throws Exception {
        try {
            Long dictionaryKey = ConverterUtility.getKeyFromUniqueID(uniqueID);
            Optional<URL> url = urlRepository.findById(dictionaryKey);
            String longUrl = url != null ? url.get().getLongUrl() : null;
            logger.info("Converting shortened URL back to {}", longUrl);
            return longUrl;
        } catch (Exception ex) {
            logger.info(String.format("Exception occured while creating unique id: %s", uniqueID));
            throw new CustomException(ex, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private String formatLocalURLFromShortener(String localURL) throws CustomException {
        try {
            URI addressComponents = new URI(localURL);
            java.net.URL url = addressComponents.toURL();
            return url.getProtocol() + "://" + url.getHost() + ":"+ url.getPort() + "/";
        } catch (Exception ex) {
            logger.info(String.format("Exception while formatting url: %s", localURL));
            throw new CustomException(ex, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean urlAlreadyShortened(String longUrl) {
        URL url = urlRepository.findURLByLongUrl(longUrl);
        return (url != null);
    }

}
