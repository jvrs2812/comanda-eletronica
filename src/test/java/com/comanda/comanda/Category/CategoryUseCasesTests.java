package com.comanda.comanda.Category;


import com.comanda.comanda.Category.Domain.CategoryGetDto;
import com.comanda.comanda.Category.Exception.CategoryException;
import com.comanda.comanda.Category.UseCase.Categories;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.ResponseSchema;
import com.comanda.comanda.utils.commom.ResponsePageable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CategoryUseCasesTests {

    private ResponsePageable<CategoryGetDto> categoryGetDtoMock;
    void mockCategoryGetDto(){
        List<CategoryGetDto> dto = new ArrayList<CategoryGetDto>();

        dto.add(new CategoryGetDto(UUID.randomUUID().toString(), "title example", "description example",
                new Date(), new Date()));

        this.categoryGetDtoMock = new ResponsePageable<CategoryGetDto>(dto, 1, 10);
    }

    CategoryGetDto mockCategoryById(String id){
        return new CategoryGetDto(id, ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.any(Date.class), ArgumentMatchers.any(Date.class));
    }

    @BeforeEach
    void setUp(){
        mockCategoryGetDto();
    }

    @Test
    void getAllCategories(){
        Categories _cat = mock(Categories.class);

        when(_cat.getAll(0)).thenReturn(categoryGetDtoMock);

        ResponsePageable<CategoryGetDto> response = _cat.getAll(0);

        assertEquals(categoryGetDtoMock, response);
    }

    @Test
    void getCategoryByIdCorrect() throws ComandaException {
        Categories _cat = mock(Categories.class);

        String id = UUID.randomUUID().toString();

        CategoryGetDto mock = mockCategoryById(id);

        when(_cat.getIdCategory(id)).thenReturn(mock);

        CategoryGetDto response = _cat.getIdCategory(id);

        assertEquals(mock.getId(), response.getId());

        assertEquals(mock, response);
    }

    @Test
    void getCategoryInvalidId() throws ComandaException {
        Categories _cat = mock(Categories.class);

        String id = "id_invalid";

        when(_cat.getIdCategory(id)).thenThrow(new ComandaException(CategoryException.CATEGORY_ID_EXCEPTION));


    }




}
