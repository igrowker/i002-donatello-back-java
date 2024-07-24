package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.finances.FinanceDTO;
import com.igrowker.donatello.dtos.finances.FinanceIncomeDto;
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
    public ResponseEntity<Void> delete(@RequestHeader HttpHeaders headers, @PathVariable Integer id) {
        financesService.delete(headers, id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("reports/")
    public ResponseEntity<List<FinanceDTO>> getReports(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(financesService.getReports(headers), HttpStatus.OK);
    }

    @GetMapping("/incomes")
    public ResponseEntity<FinanceIncomeDto> getIncomes(@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(financesService.getIncomes(headers), HttpStatus.OK);
    }
}
