package top.getidea.filesync.module;

import lombok.Data;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 09:22
 */
@Data
public class File {
    private Integer id;
    private String fileName;
    private String description;
    private String fileUrl;
    private double fileSize;
    private String fileType;
    private long gmtCreate;
    private long gmtModified;
    private String creator;
    private String token;
}
