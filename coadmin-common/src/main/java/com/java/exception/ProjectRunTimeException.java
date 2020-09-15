package com.java.exception;

/**
 * 自定义运行期异常
 *
 * @author: jcm
 * @date: 2020/09/11
 */
public class ProjectRunTimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProjectRunTimeException(String message) {
        super(message);
    }

}
