package top.getidea.filesync;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.getidea.filesync.mapper")
public class FileSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileSyncApplication.class, args);
	}

}
