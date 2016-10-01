package com.netease;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by switch on 16/9/29.
 */
public class FileWriterServiceTest {
    private FileWriterService service = null;
    private ApplicationContext context = null;

    @Before
    public void init() {
        String resource = "application-context.xml";
        context = new ClassPathXmlApplicationContext(resource);
        service = context.getBean("fileWriterService", FileWriterService.class);
    }

    @Test
    public void test_file_path_get_from_application_context() {
        assertEquals(service.getFilePath(), "/Users/switch/Downloads//");
    }

    @Test
    public void test_file_writer_service_open_file_stream() {
        assertNotNull(service.getWriter());
    }

    @Test
    public void test_file_path_and_file_name_is_usable() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(service.getFilePath() + service.getFileName(), true));
            writer.append("123456456\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_file_writer_use_write_method() {
        // 写文件
        service.write("Good Good Study, Day Day Up\n");
        // 关闭写文件服务
        ((ConfigurableApplicationContext)context).close();
    }
}
