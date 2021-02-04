package com.yunerself.blog.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.charset.Charset;

public class Shell {
    private static final Logger LOGGER = LoggerFactory.getLogger(Shell.class);

    public static int exec(String shellFilePath, String... shellArgs) {
        LOGGER.info("shell文件:[{}], shell参数:[{}]", shellFilePath, shellArgs);

        // 除了shell参数，还有sh命令以及shell文件名(绝对路径)
        String[] shellCmd = new String[shellArgs.length + 2];
        shellCmd[0] = "sh";
        shellCmd[1] = shellFilePath;
        for (int i = 0; i < shellArgs.length; i++) {
            shellCmd[2+i] = shellArgs[i];
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(shellCmd);
            // 将ErrorStream重定向到InputStream中
            processBuilder.redirectErrorStream(true);

            Process shellProcess = processBuilder.start();
            shellProcess.waitFor();

            InputStream shellProcessInputStream = shellProcess.getInputStream();
            String shellExecInfo = IOUtils.toString(shellProcessInputStream, Charset.defaultCharset());
            LOGGER.info("shell执行结果:[{}]", shellExecInfo);

            return shellProcess.exitValue();
        } catch (Exception e) {
            LOGGER.info("shell执行异常:[{}]", e.toString());
            e.printStackTrace();
        }

        return -1;
    }
}
