package com.lowes.urlshortner.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class URLConvertServiceTests {

    @InjectMocks
    URLConverterService converterService;

    @Mock
    URLRepository repository;

    @Test
    public void urlIsShortened() throws Exception {
        Long count = 2L;
        when(repository.count()).thenReturn(count);
        String shortUrl = converterService.shortenURL("localURL", "longURL");
        assertTrue(shortUrl.length() > 0);
    }
}
