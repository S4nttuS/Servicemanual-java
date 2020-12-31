package com.etteplanmore.servicemanual.factorydevice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.PostConstruct;

import com.opencsv.CSVReader;

import java.io.FileReader;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.io.FileNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;  

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ApiModel(description="Controller for factory device endpoints")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FactoryDeviceController {

    private final FactoryDeviceRepository repository;

    FactoryDeviceController(FactoryDeviceRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Find all factory devices")
    @GetMapping("/factorydevices")
    List<FactoryDevice> all() {
        return repository.findAll();
    }

    @ApiOperation(value = "Find factory device by id")
    @GetMapping("/factorydevices/{id}")
	public
    FactoryDevice one(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new FactoryDeviceNotFoundException(id));
    }
    
    @ApiOperation(value = "Add a factory device")
    @PostMapping("/factorydevices/add")
    FactoryDevice add(@RequestBody FactoryDevice factoryDevice){
        return repository.save(factoryDevice);
    }

    @ApiOperation(value = "Edit factory device")
    @PutMapping("/factorydevices/edit")
    FactoryDevice edit(@RequestBody FactoryDevice factoryDevice){
        FactoryDevice editable = one(factoryDevice.getId());
        if(editable != null){
            return repository.save(factoryDevice);
        }
        return null;
    }

    @ApiOperation(value = "Delete a factory device")
    @DeleteMapping("/factorydevices/delete/{id}")
    FactoryDevice delete(@PathVariable Long id){
        FactoryDevice deletable = one(id);
        if(deletable != null){
            repository.delete(deletable);
        }
        return deletable;
    }
    
    @ApiOperation(value = "Paginated get all for maintenances")
    @GetMapping("/factorydevices/paginated")
    Page<FactoryDevice> all(@RequestParam(name = "page", required = false) Integer page,
        @RequestParam(name = "items", required = false) Integer numberOfItems) {
        Pageable devicesWithPages;
        if (page >= 0 && numberOfItems >= 0){
            devicesWithPages = PageRequest.of(page, numberOfItems);
        }
        else {
            devicesWithPages = PageRequest.of(0, Integer.MAX_VALUE);
        }
        Page<FactoryDevice> pageMaintenance = repository.findAll(devicesWithPages);
        return pageMaintenance;
    }

    @PostConstruct
    public void loadFromCSV() {
        /* File name of the CSV file. */
        String fileName = "seeddata.csv";
        
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String [] nextLine = reader.readNext();
            
            while ((nextLine = reader.readNext()) != null) {
            FactoryDevice device = new FactoryDevice();
            
            device.setName(nextLine[0]);
            device.setYear(Integer.parseInt(nextLine[1]));
            device.setType(nextLine[2]);
            
            repository.save(device);
            }
        }
        catch (FileNotFoundException f) {
        }
        catch (IOException i) {
        }
        catch (NumberFormatException n) {
        }
    }
}