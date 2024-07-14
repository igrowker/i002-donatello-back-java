package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.finances.FinanceDTO;
import com.igrowker.donatello.services.IFinancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/finances/")
public class FinancesController {
    /*
    'api/finances/transactions/', views.FinanzaListCreate.as_view(), name='finanza-list-create'),
    'api/finances/transactions/<int:pk>/', views.FinanzaDetail.as_view(), name='finanza-detail'),
    'api/finances/reports/', views.FinanceReport.as_view(), name='finance-report'),
     */
    @Autowired
    private IFinancesService financesService;


    @GetMapping("transactions/")
    public ResponseEntity<List<FinanceDTO>> getAll(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(financesService.getAll(headers), HttpStatus.OK);
    }
    @PostMapping("transactions/")
    public ResponseEntity<FinanceDTO> create(@RequestHeader HttpHeaders headers, @RequestBody FinanceDTO dto) {
        return new ResponseEntity<>(financesService.create(headers, dto), HttpStatus.OK);
    }
    @GetMapping("transactions/{id}")
    public ResponseEntity<FinanceDTO> create(@RequestHeader HttpHeaders headers, @PathVariable Integer id) {
        return new ResponseEntity<>(financesService.getById(headers, id), HttpStatus.OK);
    }
    @PutMapping("transactions/{id}")
    public ResponseEntity<FinanceDTO> edit(@RequestHeader HttpHeaders headers, @PathVariable Integer id, @RequestBody FinanceDTO dto) {
        return new ResponseEntity<>(financesService.edit(headers, id, dto), HttpStatus.OK);
    }
    @DeleteMapping("transactions/{id}")
    public ResponseEntity<FinanceDTO> delete(@RequestHeader HttpHeaders headers, @PathVariable Integer id) {
        return new ResponseEntity<>(financesService.delete(headers, id), HttpStatus.OK);
    }
}
