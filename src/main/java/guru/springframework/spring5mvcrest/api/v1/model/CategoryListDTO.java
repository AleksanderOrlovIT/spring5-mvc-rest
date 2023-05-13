package guru.springframework.spring5mvcrest.api.v1.model;

import guru.springframework.spring5mvcrest.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDTO {
    List<CategoryDTO> categories;
}
