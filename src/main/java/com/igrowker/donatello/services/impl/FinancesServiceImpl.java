package com.igrowker.donatello.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.igrowker.donatello.dtos.finances.FinanceDTO;
import com.igrowker.donatello.dtos.finances.FinanceExternalDto;
import com.igrowker.donatello.mappers.FinanceMapper;
import com.igrowker.donatello.services.IAuthService;
import com.igrowker.donatello.services.IFinancesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancesServiceImpl implements IFinancesService {

    final String baseUrlPythonApi = "http://127.0.0.1:8000/api"; // TODO PASAR A properties con valores correctos!
    final String userPythonApi = "davidDonatelloPython"; // TODO PASAR A properties con valores correctos!
    final String passPythonApi = "davidDonatelloPython"; // TODO PASAR A properties con valores correctos!

    private final RestTemplate restTemplate;

    @Autowired
    FinanceMapper financeMapper;
    @Autowired
    IAuthService authService;


    private HttpHeaders getNewHeadersWithAuth(){
        HttpHeaders pythonHeaders = new HttpHeaders();
        pythonHeaders.setContentType(MediaType.APPLICATION_JSON);
        // todo verificar si necesitamos credenciales.. deberiamos !!! => pythonHeaders.add("Authorization", "Basic "+generateHash());
        return  pythonHeaders;
    }
    private String generateHash(){
        String credentials = userPythonApi+":"+passPythonApi;
        return new String(Base64.getEncoder().encode(credentials.getBytes()));
    }
    @Override
    public List<FinanceDTO> getAll(HttpHeaders headers) {
        Integer userId = authService.getLoguedUser(headers).getId();
        /*  todo verificar error
        ResponseEntity<FinanceExternalArrayDto> resp = restTemplate.exchange(
                baseUrlPythonApi+"/finances/transactions/",
                HttpMethod.GET,
                new HttpEntity<>(getNewHeadersWithAuth()),
                FinanceExternalArrayDto.class
        );
        return financeMapper.toFinanceDtoList(resp.getBody());

        */

        // solucion alternativa, para error de serializacion con restTemplate.exchange()

        ResponseEntity<String> rawResp = restTemplate.exchange(
                baseUrlPythonApi + "/finances/transactions/",// +userId, // => todo deben recibir el id user, para traer solo los que corresponde a un user en particular
                HttpMethod.GET,
                new HttpEntity<>(getNewHeadersWithAuth()),
                String.class
        );
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            // Deserializa la respuesta JSON en una lista de FinanceExternalDto
            List<FinanceExternalDto> financeExternalDtoList = objectMapper.readValue(rawResp.getBody(), new TypeReference<List<FinanceExternalDto>>(){});

            return financeMapper.toFinanceDtoList(financeExternalDtoList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FinanceDTO create(HttpHeaders headers, FinanceDTO dto) {
        Integer userId = authService.getLoguedUser(headers).getId();
        dto.setUserID(userId);

        // todo deberia validar dto

        ResponseEntity<FinanceExternalDto> resp = restTemplate.postForEntity(
                baseUrlPythonApi + "/finances/transactions/",
                dto,
                FinanceExternalDto.class);

        return financeMapper.toFinanceDto(resp.getBody());
    }

    @Override
    public FinanceDTO edit(HttpHeaders headers, Integer id, FinanceDTO dto) {
        Integer userId = authService.getLoguedUser(headers).getId();
        return null;
    }

    @Override
    public FinanceDTO delete(HttpHeaders headers, Integer id) {
        Integer userId = authService.getLoguedUser(headers).getId();

        //todo CHARLAR CON PYTHON, ELLOS DEBERIAN VERIFICAR QUE EL USUARIO ES EL DUEÑO DEL RECURSO, obtienen recurso y verifican si idUsuario corresponde, sino lanzan error? Otra opcion,
        ResponseEntity<FinanceExternalDto> resp = restTemplate.exchange(
            baseUrlPythonApi + "/finances/transactions/"+userId+"/"+id, // => todo deben recibir el id user, para verificar si es correcto devolver info..
            HttpMethod.DELETE,
            new HttpEntity<>(getNewHeadersWithAuth()),
                FinanceExternalDto.class );
        return financeMapper.toFinanceDto(resp.getBody());
    }

    @Override
    public FinanceDTO getById(HttpHeaders headers, Integer id) {
        Integer userId = authService.getLoguedUser(headers).getId();
        ResponseEntity<FinanceExternalDto> resp = restTemplate.exchange(
                baseUrlPythonApi+"/finances/transactions/"+userId+"/"+id, // => todo deben recibir el id user, para verificar si es correcto devolver info..
                HttpMethod.GET,
                new HttpEntity<>(getNewHeadersWithAuth()),
                FinanceExternalDto.class
                );
        return financeMapper.toFinanceDto(resp.getBody());
    }

    @Override
    public List<FinanceDTO> getReports(HttpHeaders headers) {
        return null;
    }
}
