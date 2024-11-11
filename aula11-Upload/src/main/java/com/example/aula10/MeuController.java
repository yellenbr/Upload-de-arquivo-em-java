/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.aula10;

import com.example.dto.PessoaDTO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pessoa")
public class MeuController {
    
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void insere(@RequestPart("nome") String nome,
            @RequestPart("email") String email,
            @RequestPart("telefone") String tel, 
            @RequestPart("document") MultipartFile arquivo) {
        System.out.println(arquivo.getContentType());
        System.out.println(arquivo.getSize());

        if (arquivo.getSize() > 500000L) {
            throw new RuntimeException("Arquivo maior do que o limite de tamanho permitido");
        }

        File uploadFile = new File(arquivo.getOriginalFilename());
        try (
                InputStream fileContent = arquivo.getInputStream(); 
                OutputStream outputStream = Files.newOutputStream(uploadFile.toPath())) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("Arquivo salvo em: " + uploadFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + uploadFile.getAbsolutePath());
        }
    }

}
