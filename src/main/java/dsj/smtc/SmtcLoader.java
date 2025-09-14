/*
 * Copyright (c) 2025 DSJ
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package dsj.smtc;

import java.io.*;

/**
 * A smtc loader
 * @author DSJ
 */
public class SmtcLoader {
    static {
        try {
            String dll = "/natives/smtc.dll";

            InputStream in = SmtcLoader.class.getResourceAsStream(dll);

            if (in == null) {
                throw new FileNotFoundException("DLL not found: " + dll);
            }

            File tempDll = File.createTempFile("smtc", ".dll");
            tempDll.deleteOnExit();

            try (OutputStream out = new FileOutputStream(tempDll)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            System.load(tempDll.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load DLL", e);
        }
    }

    // JNI
    public static native String getSmtcInfo();
}
