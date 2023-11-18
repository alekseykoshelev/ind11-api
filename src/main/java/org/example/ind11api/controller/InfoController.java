package org.example.ind11api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {
    private final static Logger logger = LoggerFactory.getLogger(InfoController.class);

    private final int port;

    public InfoController(@Value("${server.port}") int port) {
        this.port = port;
    }

    @GetMapping("/getPort")
    public int port() {
        return port;
    }

    @GetMapping("/test")
    public int test() {
        long startTime = System.currentTimeMillis();
        int sum = IntStream.iterate(1, a -> a + 1).limit(1_000_000).reduce(0, (a, b) -> a + b);
        logger.info("Elapsed: {}", System.currentTimeMillis() - startTime);
        return sum;
    }

}
