package com.example.FirstSpringApplication.Services;

import com.example.FirstSpringApplication.Entity.Actor;
import com.example.FirstSpringApplication.Entity.Director;
import com.example.FirstSpringApplication.Repositories.DirectorPageRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Setter
@Getter
@Service
public class DirectorService {

    @Autowired
    DirectorPageRepository directorPageRepository;

    public void page(Model model, int id){
        Pageable pageable = PageRequest.of(id, 15, Sort.by(Sort.Direction.ASC, "name"));
        Page<Object[]> director = directorPageRepository.findDistinctByNameAndSurnameOrderByNameAsc(pageable);

        model.addAttribute("number", director.getNumber());
        model.addAttribute("totalPages", director.getTotalPages());
        model.addAttribute("director", director.getContent().stream().map(x ->{
            Director director1 = new Director();
            director1.setName((String)x[0]);
            director1.setSurname((String)x[1]);
            director1.setId((Integer) x[2]);
            return director1;
        }).toList());
    }
}
