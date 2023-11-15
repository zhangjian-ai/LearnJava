package com.zhangjian.dao.impl;

import com.zhangjian.dao.PoemDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

@Repository
public class PoemDaoB implements PoemDao {

    @Override
    public String read() {
        InputStream resource = this.getClass().getClassLoader().getResourceAsStream("static/清平调其一.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(resource)));

        StringBuilder builder = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
