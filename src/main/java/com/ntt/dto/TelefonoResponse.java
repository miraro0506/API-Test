package com.ntt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoResponse {

    private String numero;
    private String codigoCiudad;
    private String codigoPais;

}
