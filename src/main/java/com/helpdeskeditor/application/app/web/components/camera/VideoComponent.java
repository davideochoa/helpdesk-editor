package com.helpdeskeditor.application.app.web.components.camera;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.StreamResource;

@Tag("video")
public class VideoComponent extends Component {

    public void setSrc(StreamResource resource) {
        getElement().setAttribute("src", resource);
        getElement().setAttribute("controls", "constrols");
    }
}
