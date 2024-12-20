package com.example.demo.controller;

import com.example.demo.dto.ReservationRequestDto;
import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.dto.ReservationUpdateRequestDto;
import com.example.demo.enums.ReservationStatus;
import com.example.demo.service.ReservationService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public void createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        reservationService.createReservation(reservationRequestDto.getItemId(),
                                            reservationRequestDto.getUserId(),
                                            reservationRequestDto.getStartAt(),
                                            reservationRequestDto.getEndAt());
    }


//    @PatchMapping("/{id}/update-status")
//    public void updateReservation(@PathVariable Long id, @RequestBody ReservationStatus status) {
//        reservationService.updateReservationStatus(id, status);
//    }

    /**
     * 예약 상태 업데이트 API
     */
    @PatchMapping("/{reservationId}/update-status")
    public ResponseEntity<ReservationResponseDto> updateReservation(@PathVariable Long reservationId,
            @RequestBody ReservationUpdateRequestDto reservationUpdateRequestDto) {
        ReservationResponseDto reservationResponseDto = reservationService.updateReservationStatus(
            reservationId,
            reservationUpdateRequestDto.getReservationStatus()
        );

        return new ResponseEntity<>(reservationResponseDto, HttpStatus.OK);
    }


    /**
     * 예약 전체 조회 API
     */
    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> findAllWithDetails() {
        List<ReservationResponseDto> reservationResponseDto = reservationService.findAllWithDetails();

        return new ResponseEntity<>(reservationResponseDto, HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public void searchAll(@RequestParam(required = false) Long userId,
                          @RequestParam(required = false) Long itemId) {
        reservationService.searchAndConvertReservations(userId, itemId);
    }
}
