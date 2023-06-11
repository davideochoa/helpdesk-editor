package com.helpdeskeditor.application.util.vcamera;

import java.io.OutputStream;

@FunctionalInterface
public interface DataReceiver {

    public OutputStream getOutputStream(String mimeType);

}
