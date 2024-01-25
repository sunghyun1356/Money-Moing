package com.MoneyMoing.MoneyServer.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.management.RuntimeErrorException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.FileSystemException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class ReservationController {
    @GetMapping("/hotels/{hotelId}/rooms/{roomNumber}/reservations")
    public List<Long> getReservaionByPaging(@PathVariable Long hotelId,
                                            @PathVariable String roomNumber,
                                            Pageable pageable)
    {
        System.out.println("Page param : " + pageable.getPageNumber());
        System.out.println("Size param : " + pageable.getPageSize());
        pageable.getSort().stream().forEach(order ->
                System.out.println("Sort param :" + order.getProperty() + " : " + order.getDirection()));
        return Collections.emptyList();
    }

}
