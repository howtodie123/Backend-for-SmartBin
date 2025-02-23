package com.example.demo.service.impl;

import com.example.demo.entity.warning;
import com.example.demo.repository.WarningRepository;
import com.example.demo.service.WarningService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarningServiceImpl implements WarningService {


    private final WarningRepository warningRepository;

    public WarningServiceImpl(WarningRepository warningRepository) {this.warningRepository = warningRepository;}

    @Override
    public List<warning> getAllWarnings() {
        return warningRepository.findAll();
    }

    @Override
    public List<warning> getWarningsByIdBin(Integer idbin) {
        return warningRepository.findByIdbin(idbin);
    }

    @Override
    public warning createWarning(warning warning) {
        return warningRepository.save(warning);
    }

    @Override
    public String deleteWarningById(Integer id) {
        Optional<warning> warningOptional = warningRepository.findById(id);
        if (warningOptional.isPresent()) {
            warningRepository.deleteById(id);
            return "Warning with ID " + id + " deleted successfully.";
        } else {
            return "Warning not found.";
        }
    }

    @Override
    public String deleteAllWarnings() {
        warningRepository.deleteAll();
        return "All warnings deleted successfully.";
    }
}
