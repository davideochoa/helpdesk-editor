package com.helpdeskeditor.application.app.web.components.camera;

import com.helpdeskeditor.application.util.vcamera.VCamera;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCameraView extends Div implements HasDataReceiver {

    @Id("camera")
    private VCamera camera = new VCamera();

    File latest;

    public AbstractCameraView() {
        camera.setReceiver(this);

        Map<String, Object> previewOpts = new HashMap<>();
        previewOpts.put("video", true);

        Map<String, Object> recOpts = new HashMap<>();
        recOpts.put("video", true);
        recOpts.put("audio", true);
        camera.setOptions(previewOpts, recOpts);
    }

    public VCamera getCamera() {
        return camera;
    }

    @Override
    public File getLatest() {
        return latest;
    }

    @Override
    public void setLatest(File file) {
        latest = file;
    }

}
