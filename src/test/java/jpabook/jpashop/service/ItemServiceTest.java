package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired ItemService itemService;

    @Test
    public void 전체조회() throws Exception {
        //given
        Book book = new Book();
        book.setAuthor("kim");
        Album album = new Album();
        album.setArtist("jo");

        //when
        itemService.saveItem(book);
        itemService.saveItem(album);
        List<Item> result = itemService.findItems();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void 단일조회() throws Exception {
        //given
        Book book = new Book();
        book.setAuthor("kim");
        Album album = new Album();
        album.setArtist("jo");

        //when
        itemService.saveItem(book);
        itemService.saveItem(album);

        //then
        Assertions.assertThat(book.getAuthor()).isEqualTo("kim");
    }

    @Test
    public void 상품_수량_조회() {
        Book book = new Book();
        book.setStockQuantity(1000);

        itemService.saveItem(book);
        book.addStock(100);

        Assertions.assertThat(book.getStockQuantity()).isEqualTo(1100);
    }
}