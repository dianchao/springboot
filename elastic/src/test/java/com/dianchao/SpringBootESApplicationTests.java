package com.dianchao;

import com.dianchao.bean.Article;
import com.dianchao.bean.Book;
import com.dianchao.repository.BookRepository;
import com.google.gson.Gson;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootESApplicationTests {
    @Autowired
    JestClient jestClient;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void contextLoads(){
        //1.生成文档内容
        Article article = new Article(1, "张三", "好消息", "hello world");

        //2.构建索引
        Index index = new Index.Builder(article).index("articles").type("news").build();

        //3.执行
        try {
            DocumentResult execute = jestClient.execute(index);
            System.out.println(execute);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void search(){
        //搜索表达式
        String searchExp = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        //构建搜索
        Search search = new Search.Builder(searchExp).addIndex("articles").addType("news").build();

        //执行
        try {
            SearchResult result = jestClient.execute(search);
            String jsonString = result.getJsonString();
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01(){
        Book book = new Book(1, "三国演义", "罗贯中");
        bookRepository.index(book);
    }

    @Test
    public void test02(){
        List<Book> books = bookRepository.findByBookNameLike("三国");
        for(Book book: books){
            System.out.println(book);
        }
    }
}
