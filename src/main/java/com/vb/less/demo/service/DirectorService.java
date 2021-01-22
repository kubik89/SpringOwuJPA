package com.vb.less.demo.service;

import com.vb.less.demo.dao.DirectoryRepository;
import com.vb.less.demo.entity.Director;
import org.apache.commons.lang3.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService implements IDirectorService {

    private DirectoryRepository directoryRepository;

    @Autowired
    public DirectorService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    @Override
    public Director saveDirector(Director director) {
        char firstLetter = director.getName().charAt(0);

        if (!CharUtils.isAsciiAlphaUpper(firstLetter)) {
            throw new IllegalArgumentException("Value must have first CAPITAL letter");
        }
        return directoryRepository.saveAndFlush(director);
    }

    @Override
    public List<Director> getAllDirectors() {
        return directoryRepository.findAll();
    }

    @Override
    public Director getDirectorById(int id) {
        return directoryRepository.getOne(id);
    }

    @Override
    public void deleteDirector(int id) {
        directoryRepository.deleteById(id);
    }

    @Override
    public Director updateDirector(int id, Director director) {
        if (directoryRepository.existsById(id)) {
            director.setId(id);
            return directoryRepository.saveAndFlush(director);
        } else {
            throw new IllegalArgumentException("No director with such id found: " + id);
        }

    }
}
