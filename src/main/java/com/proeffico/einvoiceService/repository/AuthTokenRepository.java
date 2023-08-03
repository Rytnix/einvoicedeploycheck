package com.proeffico.einvoiceService.repository;

import com.proeffico.einvoiceService.entity.AuthTokenResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository< AuthTokenResponse,Long> {

}
