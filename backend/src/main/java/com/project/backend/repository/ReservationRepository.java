package com.project.backend.repository;

import com.project.backend.model.Reservation;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

public interface ReservationRepository extends JpaAttributeConverter<Reservation, Long> {
}
