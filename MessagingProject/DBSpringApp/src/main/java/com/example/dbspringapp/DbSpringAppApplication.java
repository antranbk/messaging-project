package com.example.dbspringapp;

import com.example.dbspringapp.model.Cell;
import com.example.dbspringapp.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DbSpringAppApplication implements CommandLineRunner {

    private CellRepository cellRepository;
    @Autowired
    public DbSpringAppApplication(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DbSpringAppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //generateCellInDb();
    }
    private void generateCellInDb(){
        int index=1;
        for(int row=0;row<5;row++){
            for(int column=0;column<5;column++){
                Cell cell=new Cell(index, Arrays.asList(5.0*(column),5.0*(column+1)),Arrays.asList(5.0*(row),5.0*(row+1)));
                cellRepository.saveCell(cell);
                index++;
            }

        }
    }
}
